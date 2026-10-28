package com.simu.tui.screen;

import com.simu.abstracts.Screen;
import com.simu.controller.GameController;
import com.simu.definition.CreatureDefinition;
import dev.tamboui.buffer.Buffer;
import dev.tamboui.layout.Alignment;
import dev.tamboui.layout.Constraint;
import dev.tamboui.layout.Layout;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Color;
import dev.tamboui.style.Overflow;
import dev.tamboui.terminal.Frame;
import dev.tamboui.text.Line;
import dev.tamboui.text.Text;
import dev.tamboui.tui.event.Event;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.block.BorderType;
import dev.tamboui.widgets.block.Borders;
import dev.tamboui.widgets.block.Title;
import dev.tamboui.widgets.paragraph.Paragraph;
import dev.tamboui.widgets.table.Row;
import dev.tamboui.widgets.table.Table;
import dev.tamboui.widgets.table.TableState;

import java.util.List;

public class CreatureSelectionScreen implements Screen {

    private final ScreenNavigator screenNavigator;
    private final GameController gameController;


    //
    private final TableState tableState = new TableState();

    public CreatureSelectionScreen(ScreenNavigator screenNavigator, GameController gameController) {
        this.screenNavigator = screenNavigator;
        this.gameController = gameController;
    }

    @Override
    public boolean handleEvents(Event event) {

        return false;

    }

    @Override
    public void show(Frame frame) {

        Rect area = frame.area();

        Layout layout = Layout.horizontal().constraints(
                Constraint.percentage(50),
                Constraint.percentage(50)
        );

        List<Rect> areasList = layout.split(frame.area());

        List<CreatureDefinition> creatureDefinitions = this.gameController.getCreaturesDefinition();

        this.renderedBlock(areasList.getFirst(), frame.buffer(), frame, creatureDefinitions.get(0));
        this.renderedBlock(areasList.getLast(), frame.buffer(), frame, creatureDefinitions.get(1));

//        Block block = Block.bordered();
//        Rect innerArea = block.inner(area);
//
//        int paragraphHeight = 1;
//
//        Rect centeredArea = new Rect(
//                innerArea.x(),
//                innerArea.y() + ((innerArea.height() - paragraphHeight) / 2),
//                innerArea.width(),
//                paragraphHeight
//        );
//
//        Paragraph text = Paragraph.builder()
//                .text(Text.from("This is a dumb screen..."))
//                .overflow(Overflow.WRAP_WORD)
//                .alignment(Alignment.CENTER)
//                .build();
//
//        frame.renderWidget(block, area);
//        frame.renderWidget(text, centeredArea);


    }


    private void renderedBlock(Rect area, Buffer buffer, Frame frame, CreatureDefinition creature) {

        Block mainContainerBlock = Block.builder()
                .title(new Title(Line.from("  " + creature.name() + "  "), Alignment.CENTER, Overflow.WRAP_WORD))
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .build();

        Rect innerMainContainer = mainContainerBlock.inner(area);

        Layout internLayout = Layout.vertical()
                        .constraints(
                                Constraint.percentage(60),
                                Constraint.percentage(40)
                        );

        List<Rect> interAreasList = internLayout.split(innerMainContainer);

        Block creatureViewImage = Block.builder()
                .borders(Borders.NONE)
                .padding(10)
                .build();

        Block creatureViewData = Block.builder()
                .title(new Title(Line.from("Stats"), Alignment.LEFT, Overflow.WRAP_WORD))
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .borderColor(Color.CYAN)
                .build();

        int paragraphHeight = 3;

        Rect innerAreaCreatureView = interAreasList.getFirst();



        Rect centeredLogeArea = new Rect(
                innerAreaCreatureView.x(),
                innerAreaCreatureView.y() + ((innerAreaCreatureView.height() - paragraphHeight) /2),
                innerAreaCreatureView.width(),
                paragraphHeight
        );

        Paragraph text = Paragraph.builder()
                .text(Text.from(creature.name() + " view is not available..."))
                .overflow(Overflow.WRAP_WORD)
                .alignment(Alignment.CENTER)
                .build();


        Rect innerTable = creatureViewData.inner(interAreasList.getLast());

        frame.renderWidget(text, centeredLogeArea);

        frame.renderWidget(creatureViewImage, interAreasList.getFirst());
        frame.renderWidget(creatureViewData, interAreasList.getLast());
        frame.renderStatefulWidget(
                this.createTableStats(creature),
                innerTable,
                this.tableState
        );

        frame.renderWidget(mainContainerBlock, area);

//        creatureViewImage.render(interAreasList.getFirst(), buffer);
//        creatureViewData.render(interAreasList.getLast(), buffer);
//        mainContainerBlock.render(area, buffer);


    }


    private Table createTableStats (CreatureDefinition creature) {

        List<Row> rows = List.of(
                Row.from("Hp", String.valueOf(creature.hp())),
                Row.from("Attack", String.valueOf(creature.attack())),
                Row.from("Defense", String.valueOf(creature.defense())),
                Row.from("Special atk", String.valueOf(creature.specialAttack())),
                Row.from("Special def", String.valueOf(creature.specialDefense())),
                Row.from("Speed", String.valueOf(creature.speed()))
        );

        Row header = Row.from("Attribute", "Value");

        return Table.builder()
                .header(header)
                .rows(rows)
                .widths(
                        Constraint.percentage(70),
                        Constraint.percentage(30)
                )
                .columnSpacing(1)
                .build();



    }

}
