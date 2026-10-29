package com.simu.tui.screen;

import com.simu.abstracts.Screen;
import com.simu.controller.GameController;
import com.simu.definition.CreatureDefinition;
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
import dev.tamboui.tui.event.KeyCode;
import dev.tamboui.tui.event.KeyEvent;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.block.BorderType;
import dev.tamboui.widgets.block.Borders;
import dev.tamboui.widgets.block.Title;
import dev.tamboui.widgets.paragraph.Paragraph;
import dev.tamboui.widgets.select.Select;
import dev.tamboui.widgets.select.SelectState;
import dev.tamboui.widgets.table.Row;
import dev.tamboui.widgets.table.Table;
import dev.tamboui.widgets.table.TableState;

import java.util.List;

public class CreatureSelectionScreen implements Screen {

    private enum FocusedSelector {
        PLAYER_A,
        PLAYER_B
    }

    private final ScreenNavigator screenNavigator;
    private final GameController gameController;

    private final List<CreatureDefinition> listCreatureAvailable;

    private final SelectState creatureSelectStateA;
    private final SelectState creatureSelectStateB;

    private final Select selectCreatureA;
    private final Select selectCreatureB;

    private final TableState tableStateA;
    private final TableState tableStateB;

    private FocusedSelector focusedSelector;

    private boolean creatureAConfirmed;
    private boolean creatureBConfirmed;

    public CreatureSelectionScreen(
            ScreenNavigator screenNavigator,
            GameController gameController
    ) {
        this.screenNavigator = screenNavigator;
        this.gameController = gameController;

        this.listCreatureAvailable =
                this.gameController.getCreaturesDefinition();

        List<String> creatureNames =
                this.listCreatureAvailable
                        .stream()
                        .map(CreatureDefinition::name)
                        .toList();

        this.creatureSelectStateA =
                new SelectState(creatureNames, 0);

        this.creatureSelectStateB =
                new SelectState(creatureNames, 0);

        this.selectCreatureA = Select.builder()
                .leftIndicator("< ")
                .rightIndicator(" >")
                .selectedColor(Color.CYAN)
                .indicatorColor(Color.DARK_GRAY)
                .build();

        this.selectCreatureB = Select.builder()
                .leftIndicator("< ")
                .rightIndicator(" >")
                .selectedColor(Color.CYAN)
                .indicatorColor(Color.DARK_GRAY)
                .build();

        this.tableStateA = new TableState();
        this.tableStateB = new TableState();

        this.focusedSelector = FocusedSelector.PLAYER_A;

        this.creatureAConfirmed = false;
        this.creatureBConfirmed = false;
    }

    @Override
    public boolean handleEvents(Event event) {

        if (!(event instanceof KeyEvent keyEvent)) {
            return false;
        }

        if (keyEvent.isCharIgnoreCase('a')) {
            this.focusedSelector = FocusedSelector.PLAYER_A;
            return true;
        }

        if (keyEvent.isCharIgnoreCase('d')) {
            this.focusedSelector = FocusedSelector.PLAYER_B;
            return true;
        }


        if (keyEvent.code() == KeyCode.ENTER) {

            switch (this.focusedSelector) {

                case PLAYER_A -> {
                    this.creatureAConfirmed =
                            !this.creatureAConfirmed;
                }

                case PLAYER_B -> {
                    this.creatureBConfirmed =
                            !this.creatureBConfirmed;
                }
            }

            return true;
        }

        if (keyEvent.code() == KeyCode.LEFT) {

            switch (this.focusedSelector) {

                case PLAYER_A -> {
                    if (!this.creatureAConfirmed) {
                        this.creatureSelectStateA
                                .selectPrevious();

                        return true;
                    }
                }

                case PLAYER_B -> {
                    if (!this.creatureBConfirmed) {
                        this.creatureSelectStateB
                                .selectPrevious();

                        return true;
                    }
                }
            }

            return false;
        }

        if (keyEvent.code() == KeyCode.RIGHT) {

            switch (this.focusedSelector) {

                case PLAYER_A -> {
                    if (!this.creatureAConfirmed) {
                        this.creatureSelectStateA
                                .selectNext();

                        return true;
                    }
                }

                case PLAYER_B -> {
                    if (!this.creatureBConfirmed) {
                        this.creatureSelectStateB
                                .selectNext();

                        return true;
                    }
                }
            }

            return false;
        }

        return false;
    }

    @Override
    public void show(Frame frame) {

        Layout layout = Layout.horizontal()
                .constraints(
                        Constraint.percentage(50),
                        Constraint.percentage(50)
                );

        List<Rect> areasList =
                layout.split(frame.area());

        int selectedIndexA =
                this.creatureSelectStateA.selectedIndex();

        int selectedIndexB =
                this.creatureSelectStateB.selectedIndex();

        CreatureDefinition selectedCreatureA =
                this.listCreatureAvailable.get(selectedIndexA);

        CreatureDefinition selectedCreatureB =
                this.listCreatureAvailable.get(selectedIndexB);

        boolean playerAHasFocus =
                this.focusedSelector
                        == FocusedSelector.PLAYER_A;

        boolean playerBHasFocus =
                this.focusedSelector
                        == FocusedSelector.PLAYER_B;

        this.renderedBlock(
                areasList.getFirst(),
                frame,
                selectedCreatureA,
                this.creatureSelectStateA,
                this.selectCreatureA,
                this.tableStateA,
                playerAHasFocus,
                this.creatureAConfirmed
        );

        this.renderedBlock(
                areasList.getLast(),
                frame,
                selectedCreatureB,
                this.creatureSelectStateB,
                this.selectCreatureB,
                this.tableStateB,
                playerBHasFocus,
                this.creatureBConfirmed
        );
    }

    private void renderedBlock(
            Rect area,
            Frame frame,
            CreatureDefinition creature,
            SelectState selectState,
            Select select,
            TableState tableState,
            boolean hasFocus,
            boolean isConfirmed
    ) {


        Color borderColor = isConfirmed
                ? Color.GREEN
                : hasFocus
                ? Color.CYAN
                : Color.DARK_GRAY;

        int selectWidth = Math.min(
                select.width(selectState),
                area.width()
        );

        Rect centeredSelectArea = new Rect(
                area.x()
                        + ((area.width() - selectWidth) / 2),
                area.y(),
                selectWidth,
                1
        );

        Block mainContainerBlock = Block.builder()
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .borderColor(borderColor)
                .build();

        Rect innerMainContainer =
                mainContainerBlock.inner(area);

        Layout internLayout = Layout.vertical()
                .constraints(
                        Constraint.percentage(60),
                        Constraint.percentage(40)
                );

        List<Rect> internalAreas =
                internLayout.split(innerMainContainer);

        Rect creatureViewArea =
                internalAreas.getFirst();

        Rect creatureStatsArea =
                internalAreas.getLast();

        Block creatureViewImage = Block.builder()
                .borders(Borders.NONE)
                .padding(10)
                .build();

        Block creatureViewData = Block.builder()
                .title(
                        new Title(
                                Line.from("Stats"),
                                Alignment.LEFT,
                                Overflow.WRAP_WORD
                        )
                )
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .borderColor(borderColor)
                .build();

        int paragraphHeight = 3;

        Rect centeredLogoArea = new Rect(
                creatureViewArea.x(),
                creatureViewArea.y()
                        + (
                        (
                                creatureViewArea.height()
                                        - paragraphHeight
                        ) / 2
                ),
                creatureViewArea.width(),
                paragraphHeight
        );

        Paragraph text = Paragraph.builder()
                .text(
                        Text.from(
                                creature.name()
                                        + " view is not available..."
                        )
                )
                .overflow(Overflow.WRAP_WORD)
                .alignment(Alignment.CENTER)
                .build();

        Rect innerTable =
                creatureViewData.inner(creatureStatsArea);

        /*
         * Contenedores.
         */
        frame.renderWidget(
                mainContainerBlock,
                area
        );

        frame.renderWidget(
                creatureViewImage,
                creatureViewArea
        );

        frame.renderWidget(
                creatureViewData,
                creatureStatsArea
        );

        /*
         * Contenido.
         */
        frame.renderWidget(
                text,
                centeredLogoArea
        );

        frame.renderStatefulWidget(
                this.createTableStats(creature),
                innerTable,
                tableState
        );


        frame.renderStatefulWidget(
                select,
                centeredSelectArea,
                selectState
        );
    }

    private Table createTableStats(
            CreatureDefinition creature
    ) {

        List<Row> rows = List.of(
                Row.from(
                        "HP",
                        String.valueOf(creature.hp())
                ),
                Row.from(
                        "Attack",
                        String.valueOf(creature.attack())
                ),
                Row.from(
                        "Defense",
                        String.valueOf(creature.defense())
                ),
                Row.from(
                        "Special atk",
                        String.valueOf(
                                creature.specialAttack()
                        )
                ),
                Row.from(
                        "Special def",
                        String.valueOf(
                                creature.specialDefense()
                        )
                ),
                Row.from(
                        "Speed",
                        String.valueOf(creature.speed())
                )
        );

        Row header =
                Row.from("Attribute", "Value");

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