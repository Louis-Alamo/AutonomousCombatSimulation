package com.simu.definition;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MoveFileReader {

    private final String resourcePath = "data/move-data.json";
    private final JsonMapper jsonMapper;


    public MoveFileReader() {
        this.jsonMapper = JsonMapper.builder().build();
    }


    public List<MoveDefinition> read() throws IOException {

        try (InputStream inputStream = MoveFileReader.class.getClassLoader().getResourceAsStream(this.resourcePath)) {

            if (inputStream == null) {
                throw new IOException(" resource file was not found: " + this.resourcePath);
            }

            return this.jsonMapper.readValue(
                    inputStream,
                    new TypeReference<List<MoveDefinition>>() {
                    }
            );
        }

    }
}
