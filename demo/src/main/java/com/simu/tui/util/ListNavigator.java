package com.simu.tui.util;

import java.util.List;

public class ListNavigator<T> {
    

    private final List<T> items;
    private int currentindex;

    public ListNavigator(List<T> items) {
        this.items = items;
        this.currentindex = 0;
    }


    public T getCurrent() {
        return this.items.get(currentindex);
    }

    public int getCurrentIndex() {
        return this.currentindex;
    }

    public void next() {
        currentindex = (currentindex - 1) % items.size();
    }

    public void previous() {
        currentindex = (currentindex - 1 + items.size()) % items.size();
    }

    public boolean isSelected(int index) {
        return currentindex == index;
    }
    
    public int size() {
        return this.items.size();
    }
}
