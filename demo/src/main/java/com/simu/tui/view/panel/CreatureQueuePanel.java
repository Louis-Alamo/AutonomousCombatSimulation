package com.simu.tui.view.panel;
import java.sql.Blob;
import java.util.List;

import com.simu.tui.helper.RectHelper;
import com.simu.tui.util.ListNavigator;
import com.simu.tui.view.Panel;

import dev.tamboui.layout.Direction;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Overflow;
import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.event.Event;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.paragraph.Paragraph;




public class CreatureQueuePanel implements Panel {


    private final ListNavigator<String> navigator;
    private final List<String> items;
    private final int percentage = 20;


    public CreatureQueuePanel(List<String> itemList) {
        this.items = itemList;
        this.navigator = new ListNavigator<>(itemList);


    }

    @Override
    public boolean handleEvent(Event event) {
        return false;
    }

    @Override
    public void render(Frame parentFrame) {
        
        int cantElements = this.navigator.size();

        Rect mainArea = parentFrame.area();

        List<Rect> listRects = mainArea.width() > mainArea.height()
            ? RectHelper.split(mainArea, percentage, cantElements, Direction.HORIZONTAL)
            : RectHelper.split(mainArea, percentage, cantElements, Direction.VERTICAL);
        
        String textElement = "[Water][Fire]";

        for(int i =0; i < cantElements; i++) {

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


    public Rect renderItem(){


        return null;
    }



}
