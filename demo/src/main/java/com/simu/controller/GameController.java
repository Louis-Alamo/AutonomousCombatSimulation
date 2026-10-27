package com.simu.controller;

import com.simu.definition.CreatureDefinition;
import com.simu.definition.CreatureFileReader;
import com.simu.definition.MoveDefinition;
import com.simu.definition.MoveFileReader;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

public class GameController {

    private final CreatureFileReader creatureFileReader;
    private final MoveFileReader moveFileReader;

    public GameController(CreatureFileReader creatureFileReader, MoveFileReader moveFileReader) {
        this.creatureFileReader = creatureFileReader;
        this.moveFileReader = moveFileReader;
    }


    public List<CreatureDefinition> getCreaturesDefinition() throws IOException {
        return this.creatureFileReader.read();
    }

    public List<MoveDefinition> getMovesDefinition() throws IOException {
        return this.moveFileReader.read();
    }
}
