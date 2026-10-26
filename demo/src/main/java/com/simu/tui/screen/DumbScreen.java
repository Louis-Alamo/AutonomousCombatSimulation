package com.simu.tui.screen;

import com.simu.abstracts.Screen;
import dev.tamboui.layout.Alignment;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Overflow;
import dev.tamboui.terminal.Frame;
import dev.tamboui.text.Text;
import dev.tamboui.tui.event.Event;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.paragraph.Paragraph;


public class DumbScreen implements Screen {


    @Override
    public void show (Frame frame) {

        Rect area = frame.area();

        Block block = Block.bordered();
        Rect innerArea = block.inner(area);

        int paragraphHeight = 1;

        Rect centeredArea = new Rect(
                innerArea.x(),
                innerArea.y() + ((innerArea.height() - paragraphHeight) / 2),
                innerArea.width(),
                paragraphHeight
        );

        Paragraph text = Paragraph.builder()
                .text(Text.from("This is a dumb screen..."))
                .overflow(Overflow.WRAP_WORD)
                .alignment(Alignment.CENTER)
                .build();

        frame.renderWidget(block, area);
        frame.renderWidget(text, centeredArea);

    }

    @Override
    public boolean handleEvents(Event event) {
        return false;
    }
}
