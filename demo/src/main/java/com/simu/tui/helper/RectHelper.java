package com.simu.tui.helper;

import dev.tamboui.layout.Constraint;
import dev.tamboui.layout.Direction;
import dev.tamboui.layout.Layout;
import dev.tamboui.layout.Rect;

import java.util.Arrays;
import java.util.List;


public class RectHelper {


    private RectHelper(){}

    public static Rect center(Rect parentRec, int width, int height) {

        int safewidth = Math.min(width, parentRec.width());
        int safeHeight = Math.min(height, parentRec.height());

        int centeredX = parentRec.x()
                + ((parentRec.width() - safewidth) /2);

        int centeredY = parentRec.y()
                + ((parentRec.height() - safeHeight) / 2);


        return new Rect(
              centeredX,
              centeredY,
              safewidth,
              safeHeight
        );
    }

    public static Rect topCenter(Rect parentRect, int width, int height) {

        int safewidth = Math.min(width, parentRect.width());
        int safeHeight = Math.min(height, parentRect.height());

        int x = parentRect.x()
                + ((parentRect.width() - safewidth) / 2);

        int y = parentRect.y();

        return new Rect(
                x,
                y,
                safewidth,
                safeHeight
        );
    }

    public static Rect bottomCenter(Rect parentRect, int width, int height) {

        int safeWidth = Math.min(width, parentRect.width());
        int safeHeight = Math.min(height, parentRect.height());

        int x = parentRect.x()
                + ((parentRect.width() - safeWidth) / 2);

        int y = parentRect.y() +
                (parentRect.height() - safeHeight);

        return new Rect(
                x,
                y,
                safeWidth,
                safeHeight
        );
    }

    public static List<Rect> splitHorizontal(Rect parentArea, int ... splitPercentages) {

        Layout layout = Layout.horizontal()
                .constraints(
                        Arrays.stream(splitPercentages).mapToObj(Constraint::percentage).toArray(Constraint[]::new)
                );

        return layout.split(parentArea);

    }

    public static List<Rect> splitVertical(Rect parentRect, int ... splitPercentages) {

        Layout layout = Layout.vertical()
                .constraints(
                        Arrays.stream(splitPercentages).mapToObj(Constraint::percentage).toArray(Constraint[]::new)
                );

        return layout.split(parentRect);
    }


    public static List<Rect> split(Rect parentRect, int percentage, int splits, Direction direction) {

        int [] splitPercentages = new int[splits];
        Arrays.fill(splitPercentages, percentage);

        return Direction.HORIZONTAL == direction
                ? RectHelper.splitHorizontal(parentRect, splitPercentages)
                : RectHelper.splitVertical(parentRect, splitPercentages);

    }
}
