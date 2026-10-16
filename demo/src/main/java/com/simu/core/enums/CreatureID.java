package com.simu.core.enums;

public enum CreatureID {

    PIKACHU("Pikachu"),
    CHARMANDER("Charmander"),
    BULBASAUR("Bulbasaur"),
    SQUIRTLE("Squirtle");

    private final String displayName;

    CreatureID(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
