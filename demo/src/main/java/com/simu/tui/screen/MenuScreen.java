package com.simu.tui.screen;



import com.simu.abstracts.Screen;
import com.simu.tui.enums.ScreenType;
import dev.tamboui.layout.Constraint;
import dev.tamboui.layout.Layout;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Overflow;
import dev.tamboui.style.Style;
import dev.tamboui.terminal.Frame;
import dev.tamboui.text.Text;
import dev.tamboui.tui.event.Event;
import dev.tamboui.tui.event.KeyCode;
import dev.tamboui.tui.event.KeyEvent;

import dev.tamboui.widgets.list.ListState;
import dev.tamboui.widgets.list.ListWidget;
import dev.tamboui.widgets.paragraph.Paragraph;


import java.util.ArrayList;
import java.util.List;


public class MenuScreen implements Screen {


    private ListState state = new ListState();
    private ListWidget listWidget;
    private final ScreenNavigator screenNavigator;


    List<String> optionsMenu = new ArrayList<>(
            List.of(
                    "IA vs IA",
                    "Player vs IA",
                    "Player vs Player",
                    "Options",
                    "Exit")
    );

    public MenuScreen(ScreenNavigator screenNavigator) {
        this.screenNavigator = screenNavigator;
        this.state.select(0);

         this.listWidget = ListWidget.builder()
                .items(optionsMenu.toArray(String[]::new))
                .highlightStyle(Style.EMPTY.reversed())
                .highlightSymbol(">> ")
                .build();
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

            } else if (keyEvent.code() == KeyCode.ENTER) {
                return this.optionSelecterList(this.optionsMenu.get(this.state.selected()));

            }

        }

        return false;
    }


    @Override
    public void show(Frame frame) {

        Layout layout = Layout.horizontal()
                .constraints(
                        Constraint.percentage(30),
                        Constraint.percentage(70)
                );

        List<Rect> areas = layout.split(frame.area());

        Layout layoutMenu = Layout.vertical()
                .constraints(
                        Constraint.fill(),
                        Constraint.percentage(30),
                        Constraint.fill()
                );

        Rect menuArea = layoutMenu.split(areas.getFirst()).get(1);

        Rect area = areas.getLast();


        //Text reducedLogoText = Text.from(AsciiLogos.CAT_BANNER_BRAILE);
        Text reducedLogoText = Text.from("TAMBO UI && JAVA SE");


        int reducedLogoWidth = Math.min(reducedLogoText.width(), area.width());
        int reducedLogoHeight = Math.min(reducedLogoText.height(), area.height());

        int reducedX = area.x() + Math.max(0, (area.width() - reducedLogoWidth) / 2);
        int reducedY = area.y() + Math.max(0, (area.height() - reducedLogoHeight) / 2);

        Rect reducedLogoArea = new Rect(
                reducedX,
                reducedY,
                reducedLogoWidth,
                reducedLogoHeight
        );

        Paragraph reducedLogo = Paragraph.builder()
                .text(reducedLogoText)
                .overflow(Overflow.CLIP)
                .build();

        frame.renderWidget(reducedLogo, reducedLogoArea);


        //this.select.render(area, frame.buffer(), selectState);
        this.listWidget.render(menuArea, frame.buffer(), this.state);

    }



    public boolean optionSelecterList (String option) {
        if (option.equalsIgnoreCase("IA vs IA")) {
            this.screenNavigator.navigateTo(ScreenType.CREATURE_SELECTION_SCREEN);
            return true;
        }

        return false;

    }

}
