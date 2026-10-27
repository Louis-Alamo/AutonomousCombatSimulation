package com.simu.tui.screen;



import com.simu.abstracts.Screen;
import dev.tamboui.layout.Constraint;
import dev.tamboui.layout.Layout;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Color;
import dev.tamboui.style.Overflow;
import dev.tamboui.style.Style;
import dev.tamboui.terminal.Frame;
import dev.tamboui.text.Text;
import dev.tamboui.tui.event.Event;
import dev.tamboui.tui.event.KeyCode;
import dev.tamboui.tui.event.KeyEvent;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.block.Borders;
import dev.tamboui.widgets.list.ListState;
import dev.tamboui.widgets.list.ListWidget;
import dev.tamboui.widgets.paragraph.Paragraph;
import dev.tamboui.widgets.select.Select;
import dev.tamboui.widgets.select.SelectState;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MenuScreen implements Screen {

    /*
    private static final String LOGO = """
⡿⣡⣿⣿⡟⡼⡁⠁⣰⠂⡾⠉⢨⣿⠃⣿⡿⠍⣾⣟⢤⣿⢇⣿⢇⣿⣿⢿
⣱⣿⣿⡟⡐⣰⣧⡷⣿⣴⣧⣤⣼⣯⢸⡿⠁⣰⠟⢀⣼⠏⣲⠏⢸⣿⡟⣿
⣿⣿⡟⠁⠄⠟⣁⠄⢡⣿⣿⣿⣿⣿⣿⣦⣼⢟⢀⡼⠃⡹⠃⡀⢸⡿⢸⣿
⣿⣿⠃⠄⢀⣾⠋⠓⢰⣿⣿⣿⣿⣿⣿⠿⣿⣿⣾⣅⢔⣕⡇⡇⡼⢁⣿⣿
⣿⡟⠄⠄⣾⣇⠷⣢⣿⣿⣿⣿⣿⣿⣿⣭⣀⡈⠙⢿⣿⣿⡇⡧⢁⣾⣿⣿
⣿⡇⠄⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢻⠇⠄⠄⢿⣿⡇⢡⣾⣿⣿⣿
⣿⣷⢰⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⢰⣧⣀⡄⢀⠘⡿⣰⣿⣿⣿⣿⣿
⢹⣿⢸⣿⣿⠟⠻⢿⣿⣿⣿⣿⣿⣿⣿⣶⣭⣉⣤⣿⢈⣼⣿⣿⣿⣿⣿⣿
⢸⠇⡜⣿⡟⠄⠄⠄⠈⠙⣿⣿⣿⣿⣿⣿⣿⣿⠟⣱⣻⣿⣿⣿⣿⣿⠟⠁
⠄⣰⡗⠹⣿⣄⠄⠄⠄⢀⣿⣿⣿⣿⣿⣿⠟⣅⣥⣿⣿⣿⣿⠿⠋⠄⠄⣾
⠜⠋⢠⣷⢻⣿⣿⣶⣾⣿⣿⣿⣿⠿⣛⣥⣾⣿⠿⠟⠛⠉⠄⠄
""".stripTrailing();
    */

    private static final String REDUCED_BANNER = """
         ·░▓▓▒░·
        ·███▒··:·
        ·▓██:                 ··:::░░░░░·
         :▒▓▒·  ·:░▒▒▓▓▒▒▓▓██▓▓▓▒▒▒▓▓▓▓▓▓:
          ··░░░▒▓▓▒▓███▓▒▒▒▓▓▓▓▓██▓▒▒▒▓▓▓▓░
   ·░▒▒▓▓▓▓▒▓██▓▒▒▓▓███████████▓▒▒██▓▒▓▓▒▓▓:
  ·░▒▓███▓▒███▒▓██▒▓█████████████▓▒▒██▓▓█▒▓▓·
  :▒▓▒▒█▓▓██▓▒████░█████▓██████████▓▒▓█▓▒▓▓▒:
  :▓▒▒░▒▓███▒▓████░██████▓▓██████▓▓▓▓▒▒██▓▒▒░:··
  ░▒▓▒░▒███▓░█████░▒████░▓█▓▓▓▓█████▓▓▒▒▓▓▓░▒░·
  :▒▒▒▒████▒▒████▓▓▒▓███▓░▒▓█▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓███▓:
   ░▒█░████░▒▓██▓▒██▓▒███▓▒█▓▓▓▓▓▓▓▓░░░▒▒▓▒▒▒▓▓██·
  ·░▓▒▒███▓░▒█▓▒▒█████▓▓██▓▒██▓::::░░▒▒░▓███▒░▒▒▓░
 :::░▓███▓░▒▓▒░▓███▓▒▓██▓▓▓▓▒▓█▓▒░░::::░░▒███▓░▓▒:
   ░███▓▒░▒░░▒▒██▒:·:▒██▓▒▒▓█████████▒▒▒░▒▓██▓▒▓▒
  ░██▓░▒██▒░▒▒▒▒:·:▒██████████████████▓▒▒▒▒█▓▒▒:
  ▒██░░███░▓▒░░:░▓██████████████████▓▒█▓▒▒▓▒▒▒·
  ·▓▓░▒▒▓█▒▒▓▒▒▓▒████████████▓▒▓▓▒▒▓▒███▓▒░:░▒:
    ::░▒▒▒▒▒▒▒▓██▒██████████▒▒█▓▓▓▓▓░██████▒·
       ·▒▒░▓████▓░▒▒▒▒▒▒████░█▒▒▒▒▓██░▒░▒▓:·
       ···▓█▒▒▒▒░▓▓▓▓▓▓▓░███░██▓▓▓███▓▓▓▓▓·
           ··▒▓▓░▓██████▓░█████████████▓▓▓:
            :▓▓▓▒▒▒▒▒▒▓█▓░█████████████▓▓▓:
""".stripTrailing();

    private static final String BRAILLE_BANNER = REDUCED_BANNER
            .replace('·', '⠄')
            .replace(':', '⣡')
            .replace('░', '⣢')
            .replace('▒', '⣇')
            .replace('▓', '⣾')
            .replace('█', '⣿');

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
        this.state.select(0);

         this.listWidget = ListWidget.builder()
                .items(optionsMenu.toArray(String[]::new))
                .highlightStyle(Style.EMPTY.reversed())
                .highlightSymbol(">> ")
                .build();
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

        /*
        Text logoText = Text.from(LOGO);

        int logoWidth = Math.min(logoText.width(), area.width());
        int logoHeight = Math.min(logoText.height(), area.height());

        int x = area.x() + Math.max(0, (area.width() - logoWidth) / 2);
        int y = area.y() + Math.max(0, (area.height() - logoHeight) / 2);

        Rect logoArea = new Rect(x, y, logoWidth, logoHeight);


        Paragraph logo = Paragraph.builder()
                .text(logoText)
                .overflow(Overflow.CLIP)
                .build();

        frame.renderWidget(logo, logoArea);
        */

        /* Permite volver al diseño anterior con sombreado si se desea.
        Text reducedLogoText = Text.from(REDUCED_BANNER);
        */
        Text reducedLogoText = Text.from(BRAILLE_BANNER);

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
