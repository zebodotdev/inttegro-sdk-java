package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChimeEmailSchemaKind {
    @JsonProperty("gmail_view_action") GMAIL_VIEW_ACTION,
    @JsonProperty("schema_org_order") SCHEMA_ORG_ORDER,
    @JsonProperty("schema_org_invoice") SCHEMA_ORG_INVOICE
}
