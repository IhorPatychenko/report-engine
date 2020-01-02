package styles;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import positioning.HorizontalRange;
import positioning.Position;
import positioning.RectangleRange;
import positioning.Tuple;
import positioning.VerticalRange;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReportStylesBuilder<T extends ReportStyle> {

    public enum StylePriority {
        PRIORITY1, PRIORITY2, PRIORITY3, PRIORITY4
    }

    private Class<T> forClass;
    private StylePriority priority;
    private Collection<ReportStyleBuilder<T>> styleBuilders = new ArrayList<>();
    private ReportStyleBuilder<T> styleBuilder;

    public ReportStylesBuilder(Class<T> forClass, StylePriority priority){
        this.forClass = forClass;
        this.priority = priority;
    }

    public ReportStylesBuilder<T> setForegroundColor(Color foregroundColor) {
        this.styleBuilder.setForegroundColor(foregroundColor);
        return this;
    }

    public ReportStylesBuilder<T> setFontColor(Color fontColor) {
        this.styleBuilder.setFontColor(fontColor);
        return this;
    }

    public ReportStylesBuilder<T> setFillPattern(FillPatternType fillPattern) {
        this.styleBuilder.setFillPattern(fillPattern);
        return this;
    }

    public ReportStylesBuilder<T> setBoldFont(Boolean boldFont) {
        this.styleBuilder.setBoldFont(boldFont);
        return this;
    }

    public ReportStylesBuilder<T> setItalicFont(Boolean italicFont) {
        this.styleBuilder.setItalicFont(italicFont);
        return this;
    }

    public ReportStylesBuilder<T> setUnderlineFont(FontUnderline underlineFont) {
        this.styleBuilder.setUnderlineFont(underlineFont);
        return this;
    }

    public ReportStylesBuilder<T> setStrikeoutFont(Boolean strikeoutFont) {
        this.styleBuilder.setStrikeoutFont(strikeoutFont);
        return this;
    }

    public ReportStylesBuilder<T> setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.styleBuilder.setHorizontalAlignment(horizontalAlignment);
        return this;
    }

    public ReportStylesBuilder<T> setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.styleBuilder.setVerticalAlignment(verticalAlignment);
        return this;
    }

    public ReportStylesBuilder<T> setBorderTop(BorderStyle borderTop) {
        this.styleBuilder.setBorderTop(borderTop);
        return this;
    }

    public ReportStylesBuilder<T> setBorderRight(BorderStyle borderRight) {
        this.styleBuilder.setBorderRight(borderRight);
        return this;
    }

    public ReportStylesBuilder<T> setBorderBottom(BorderStyle borderBottom) {
        this.styleBuilder.setBorderBottom(borderBottom);
        return this;
    }

    public ReportStylesBuilder<T> setBorderLeft(BorderStyle borderLeft) {
        this.styleBuilder.setBorderLeft(borderLeft);
        return this;
    }

    public ReportStylesBuilder<T> setBorder(BorderStyle border) {
        return setBorderTop(border)
                .setBorderBottom(border)
                .setBorderLeft(border)
                .setBorderRight(border);
    }

    public ReportStylesBuilder<T> setBorderColor(Color color){
        this.styleBuilder.setBorderColor(color);
        return this;
    }

    public ReportStylesBuilder<T> newStyle(Tuple tuple) {
        return newStyle(tuple, false);
    }

    public ReportStylesBuilder<T> newStyle(Tuple tuple, boolean clonePreviousStyle) {
        styleBuilder = new ReportStyleBuilder<>(forClass, tuple, clonePreviousStyle);
        styleBuilders.add(styleBuilder);
        return this;
    }

    public Collection<T> getStyles(){
        return styleBuilders.stream()
                .map(ReportStyleBuilder::buildStyle)
                .collect(Collectors.toList());
    }

    private Collection<ReportStyleBuilder<T>> getStyleBuilders(){
        return styleBuilders;
    }

    public StylePriority getPriority(){
        return priority;
    }

    public void mergeStyles(ReportStylesBuilder<T> other) {
        if(other != null){
            styleBuilders.addAll(other.getStyleBuilders());
        }
    }

    private static class ReportStyleBuilder<T> {

        private Class<T> forClass;

        private Color foregroundColor;
        private Color fontColor;
        private FillPatternType fillPattern = FillPatternType.SOLID_FOREGROUND;
        private Boolean boldFont;
        private Boolean italicFont;
        private FontUnderline underlineFont;
        private Boolean strikeoutFont;
        private HorizontalAlignment horizontalAlignment;
        private VerticalAlignment verticalAlignment;
        private BorderStyle borderTop;
        private BorderStyle borderRight;
        private BorderStyle borderBottom;
        private BorderStyle borderLeft;
        private Color borderColor;
        private Tuple tuple;
        private boolean clonePreviousStyle;

        ReportStyleBuilder(Class<T> forClass, Tuple tuple, boolean clonePreviousStyle){
            this.forClass = forClass;
            this.tuple = tuple;
            this.clonePreviousStyle = clonePreviousStyle;
        }

        void setForegroundColor(Color foregroundColor) {
            this.foregroundColor = foregroundColor;
        }

        void setFontColor(Color fontColor) {
            this.fontColor = fontColor;
        }

        void setFillPattern(FillPatternType fillPattern) {
            this.fillPattern = fillPattern;
        }

        void setBoldFont(Boolean boldFont) {
            this.boldFont = boldFont;
        }

        void setItalicFont(Boolean italicFont) {
            this.italicFont = italicFont;
        }

        void setUnderlineFont(FontUnderline underlineFont) {
            this.underlineFont = underlineFont;
        }

        void setStrikeoutFont(Boolean strikeoutFont) {
            this.strikeoutFont = strikeoutFont;
        }

        void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
            this.horizontalAlignment = horizontalAlignment;
        }

        void setVerticalAlignment(VerticalAlignment verticalAlignment) {
            this.verticalAlignment = verticalAlignment;
        }

        void setBorderTop(BorderStyle borderTop) {
            this.borderTop = borderTop;
        }

        void setBorderRight(BorderStyle borderRight) {
            this.borderRight = borderRight;
        }

        void setBorderBottom(BorderStyle borderBottom) {
            this.borderBottom = borderBottom;
        }

        void setBorderLeft(BorderStyle borderLeft) {
            this.borderLeft = borderLeft;
        }

        void setBorderColor(Color color) {
            this.borderColor = color;
        }

        T buildStyle() {
            ReportStyle reportStyle = new ReportStyle()
                    .setClonePreviousStyle(clonePreviousStyle)
                    .setForegroundColor(foregroundColor)
                    .setFontColor(fontColor)
                    .setFillPattern(fillPattern)
                    .setBoldFont(boldFont)
                    .setItalicFont(italicFont)
                    .setUnderlineFont(underlineFont)
                    .setStrikeoutFont(strikeoutFont)
                    .setHorizontalAlignment(horizontalAlignment)
                    .setVerticalAlignment(verticalAlignment)
                    .setBorderTop(borderTop)
                    .setBorderBottom(borderBottom)
                    .setBorderLeft(borderLeft)
                    .setBorderRight(borderRight)
                    .setBorderColor(borderColor);
            if (forClass.equals(HorizontalRangedStyle.class)) {
                HorizontalRangedStyle hrs = new HorizontalRangedStyle(reportStyle);
                hrs.setRange((HorizontalRange) tuple);
                return forClass.cast(hrs);
            } else if (forClass.equals(VerticalRangedStyle.class)) {
                VerticalRangedStyle vrs = new VerticalRangedStyle(reportStyle);
                vrs.setRange((VerticalRange) tuple);
                return forClass.cast(vrs);
            } else if (forClass.equals(PositionedStyle.class)) {
                PositionedStyle ps = new PositionedStyle(reportStyle);
                ps.setPosition((Position) tuple);
                return forClass.cast(ps);
            } else if(forClass.equals(RectangleRangedStyle.class)){
                RectangleRangedStyle rrs = new RectangleRangedStyle(reportStyle);
                rrs.setRange((RectangleRange) tuple);
                return forClass.cast(rrs);
            }
            return null;
        }
    }

}
