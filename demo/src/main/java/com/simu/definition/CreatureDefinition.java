package com.simu.definition;

import com.simu.core.enums.ElementType;

import java.util.List;

public record CreatureDefinition(
        String name,
        double hp,
        double attack,
        double defense,
        double specialAttack,
        double specialDefense,
        double speed,
        List<ElementType> elementTypes
) {
}
