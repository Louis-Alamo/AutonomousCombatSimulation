package com.simu.definition;

import com.simu.core.enums.ElementType;
import com.simu.core.enums.MoveCategory;

public record MoveDefinition(
        String moveName,
        double movePower,
        MoveCategory category,
        int currentPP,
        int pp,
        double accuracy,
        ElementType elementType
) {
}
