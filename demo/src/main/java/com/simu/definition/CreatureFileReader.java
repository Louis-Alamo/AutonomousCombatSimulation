package com.simu.definition;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class CreatureFileReader {

     private final JsonMapper jsonMapper;
     private final String resourcePath = "data/creatures-data.json";

     public CreatureFileReader() {
         this.jsonMapper = JsonMapper.builder().build();
     }


     public List<CreatureDefinition> read() throws IOException{

          try (InputStream inputStream = CreatureFileReader.class.getClassLoader().getResourceAsStream(this.resourcePath)) {


               if (inputStream == null) {
                    throw new IOException("Creature resource file was not found: " + this.resourcePath);
               }


               return this.jsonMapper.readValue(
                       inputStream,
                       new TypeReference<List<CreatureDefinition>>() {
                       }
               );
          }

     }
}
