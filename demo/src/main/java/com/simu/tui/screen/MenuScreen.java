package com.simu.tui.screen;



import com.simu.abstracts.Screen;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Color;
import dev.tamboui.style.Style;
import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.event.Event;
import dev.tamboui.tui.event.KeyCode;
import dev.tamboui.tui.event.KeyEvent;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.block.Borders;
import dev.tamboui.widgets.list.ListState;
import dev.tamboui.widgets.list.ListWidget;
import dev.tamboui.widgets.select.Select;
import dev.tamboui.widgets.select.SelectState;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MenuScreen implements Screen {

    private SelectState selectState;
    private  Select select;

    private ListState state = new ListState();
    private ListWidget listWidget;



    List<String> optionsMenu = new ArrayList<>(
            List.of(
                    "IA vs IA",
                    "Player vs IA",
                    "Player vs Player",
                    "Options",
                    "Exit")
    );

    public MenuScreen() {
        this.selectState = new SelectState(this.optionsMenu, 0);
        this.select = Select.builder()
                .leftIndicator("< ")
                .rightIndicator(" >")
                .selectedColor(Color.CYAN)
                .indicatorColor(Color.DARK_GRAY)
                .build();

        this.state.select(0);

         this.listWidget = ListWidget.builder()
                .items(optionsMenu.toArray(String[]::new))
                .highlightStyle(Style.EMPTY.reversed())
                .highlightSymbol(">> ")
                .block(Block.builder()
                        .title("Select one option")
                        .borders(Borders.ALL)
                        .build())
                .build();
    }


    @Override
    public void show(Frame frame) {

        Rect area = frame.area();

        //this.select.render(area, frame.buffer(), selectState);
        this.listWidget.render(area, frame.buffer(), this.state);

    }

    @Override
    public boolean handleEvents(Event event) {
        if (event instanceof KeyEvent keyEvent) {
            if (keyEvent.code() == KeyCode.DOWN) {
                this.state.selectNext(this.optionsMenu.size());
                return true;

            } else if (keyEvent.code() == KeyCode.UP) {
                this.state.selectPrevious();
                return true;
            }

        }

        return false;
    }
}
