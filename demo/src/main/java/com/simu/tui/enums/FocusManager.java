package com.simu.tui.enums;

public class FocusManager<E extends Enum<E>> {

    private final E[] values;
    private E currentFocus;

    public FocusManager(Class<E> enumClass, E initialFocus) {
        this.values = enumClass.getEnumConstants();
        this.currentFocus = initialFocus;
    }

    public E getCurrentFocus() {
        return currentFocus;
    }

    public void setFocus(E focus) {
        this.currentFocus = focus;
    }

    public boolean isFocused(E focus) {
        return currentFocus == focus;
    }

    public void next() {
        int nextIndex = (currentFocus.ordinal() + 1) % values.length;
        currentFocus = values[nextIndex];
    }

    public void previous() {
        int previousIndex =
                (currentFocus.ordinal() - 1 + values.length) % values.length;

        currentFocus = values[previousIndex];
    }
}