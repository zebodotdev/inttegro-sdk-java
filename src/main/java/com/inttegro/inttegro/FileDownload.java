package com.inttegro.inttegro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDownload {
    private final byte[] data;

    public FileDownload(byte[] data) {
        this.data = data;
    }

    public byte[] data() {
        return data;
    }

    public void saveTo(Path path) throws IOException {
        Files.write(path, data);
    }
}
