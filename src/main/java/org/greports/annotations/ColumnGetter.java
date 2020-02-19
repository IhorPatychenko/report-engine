package org.greports.annotations;

import org.greports.engine.ValueType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Repeatable(value = ColumnGetters.class)
public @interface ColumnGetter {
    /**
     * Indicates the report's name which contains the column.
     *
     * @return {@link String}
     */
    String[] reportName();

    /**
     * Position of column in the report.
     * The columns will be ordered from lowest to highest position index.
     * In case of need to add a new column among the existing ones,
     * the floating part can be used to indicate the desired position.
     *
     * @return float
     */
    float position();

    /**
     * Column title. This text string will be used to search for
     * the corresponding translation in the translation file located in
     * the directory provided by the {@link Configuration#translationsDir()}
     *
     * @return {@link String}
     */
    String title() default "";

    /**
     * A {@link Converter} which will be used if case
     * the conversion is needed. The array should contain only one converter or be empty.
     * @return {@link Converter}
     */
    Converter[] typeConverter() default {};

    /**
     * Visualisation format to be displayed.
     * You can find information on how to create your own org.greports.styles
     * by going through this <a href="http://poi.apache.org/components/spreadsheet/quick-guide.html#DataFormats">link</a>
     *
     * @return {@link String}
     */
    String format() default "";

    /**
     * The {@link ValueType} of the column.
     *
     * @return {@link ValueType}
     */
    ValueType valueType() default ValueType.LITERAL;

    /**
     * Column ID which will be used by other column if that one
     * is of formula type. Also can be used by {@link SpecialColumn} and {@link SpecialRowCell}
     *
     * @see ValueType#FORMULA
     * @return {@link String}
     */
    String id() default "";

    /**
     * The value indicates whether the column has to fit the width of the longest cell.
     * This functionality is very expensive due to the large number of calculations to be performed.
     * Use only when necessary.
     *
     * @return {@link boolean}
     */
    boolean autoSizeColumn() default false;
}