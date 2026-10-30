package com.simu.tui.screen;

import com.simu.abstracts.Screen;
import com.simu.tui.helper.RectHelper;
import dev.tamboui.layout.Alignment;
import dev.tamboui.layout.Direction;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Color;
import dev.tamboui.style.Overflow;
import dev.tamboui.terminal.Frame;
import dev.tamboui.text.Text;
import dev.tamboui.tui.event.Event;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.block.BorderType;
import dev.tamboui.widgets.block.Borders;
import dev.tamboui.widgets.paragraph.Paragraph;

import java.util.Arrays;
import java.util.List;

public class MoveSelectionScreen implements Screen {

    private final ScreenNavigator screenNavigator;

    public MoveSelectionScreen(ScreenNavigator screenNavigator) {
        this.screenNavigator = screenNavigator;
    }




    @Override
    public boolean handleEvents(Event event) {
        return false;
    }

    @Override
    public void show(Frame frame) {

        Rect mainArea = frame.area();
        Block mainBlockContainer = Block.bordered();
        Rect innerArea = mainBlockContainer.inner(mainArea);


        //Creamos los paneles


        List<Rect> resultSplit = RectHelper.splitVertical(innerArea, 85, 15);

        Rect footer = resultSplit.get(1);


        List<Rect> resultSplitMainContent = RectHelper.splitHorizontal(resultSplit.get(0), 20, 80);

        Rect mainContentPanel = resultSplitMainContent.get(1);
        Rect queuePanel = resultSplitMainContent.get(0);

        //Creacion de blocks temporales
        Block blockMainContentPanel = Block.builder()
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .borderColor(Color.CYAN)
                .build();

        Block blockQueuePanel = Block.builder()
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .borderColor(Color.CYAN)
                .build();
        renderQueuePanel(blockQueuePanel.inner(queuePanel), frame);


        Block blockFooter = Block.builder()
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .borderColor(Color.CYAN)
                .build();



        //Renderizado de elementos
        frame.renderWidget(
                mainBlockContainer,
                mainArea
        );

        frame.renderWidget(blockFooter, footer);
        frame.renderWidget(blockMainContentPanel, mainContentPanel);
        frame.renderWidget(blockQueuePanel, queuePanel);




    }


    public void renderQueuePanel (Rect parentRec, Frame frame) {

        String[] creaturesNames = {"Charmander", "Squirtle", "Pikachu", "Bulbasaur"};

        int cantElements = creaturesNames.length;
        int percentage = 100 / 4;

        List<Rect> listRects = parentRec.width() > parentRec.height()
                ? RectHelper.split(parentRec, percentage, cantElements, Direction.HORIZONTAL)
                : RectHelper.split(parentRec, percentage, cantElements, Direction.VERTICAL);

        String textElement = "[Water][Fire]";
        for (int i = 0; i < creaturesNames.length; i++) {

            String text = creaturesNames[i];
            Block mainBlock = Block.bordered();

            Rect innerArea = mainBlock.inner(listRects.get(i));

            List<Rect> rects = RectHelper.split(innerArea, 50, 2, Direction.VERTICAL);

            Rect rectUp = RectHelper.bottomCenter(rects.get(0), text.length(), 1);
            Rect rectDown = RectHelper.topCenter(rects.get(1), textElement.length(), 1);

            Paragraph paragraphUp = Paragraph.builder()
                    .text(Text.from(text))
                    .overflow(Overflow.WRAP_WORD)
                    .alignment(Alignment.CENTER)
                    .build();

            Paragraph paragraphDown = Paragraph.builder()
                    .text(Text.from(textElement))
                    .overflow(Overflow.WRAP_WORD)
                    .alignment(Alignment.CENTER)
                    .build();


            frame.renderWidget(mainBlock, listRects.get(i));
            frame.renderWidget(paragraphUp, rectUp);
            frame.renderWidget(paragraphDown, rectDown);
        }




    }

}
