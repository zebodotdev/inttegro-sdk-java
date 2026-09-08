package com.inttegro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomDataTest {
    private final ObjectMapper json = new ObjectMapper();

    @Test
    void validatesCopiesAndSerializesCustomData() throws Exception {
        Map<String, String> source = new java.util.HashMap<>();
        source.put("campaign", "launch");
        CustomData data = CustomData.of(source);
        source.put("campaign", "mutated");

        assertEquals("launch", data.get("campaign"));
        assertEquals("{\"campaign\":\"launch\"}", json.writeValueAsString(data));
        assertThrows(UnsupportedOperationException.class, () -> data.values().put("x", "y"));
        assertThrows(NullPointerException.class, () -> data.set("invalid", null));
    }

    @Test
    void serializesPatchRemovalAsNull() throws Exception {
        CustomDataPatch patch = new CustomDataPatch().set("segment", "vip").unset("old_note");
        @SuppressWarnings("unchecked")
        Map<String, String> decoded = json.readValue(json.writeValueAsBytes(patch), Map.class);
        assertEquals("vip", decoded.get("segment"));
        assertNull(decoded.get("old_note"));
    }

    @Test
    void preservesStructuredCustomDataInput() throws Exception {
        CustomDataInput input = new CustomDataInput()
            .set("campaign", "launch")
            .set("attribution", Map.of("channel", "partner"));

        assertEquals("partner", input.get("attribution").get("channel").asText());
        assertEquals(
            "{\"campaign\":\"launch\",\"attribution\":{\"channel\":\"partner\"}}",
            json.writeValueAsString(input)
        );
        assertThrows(UnsupportedOperationException.class, () -> input.values().clear());
    }
}
