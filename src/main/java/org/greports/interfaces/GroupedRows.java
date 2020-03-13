package org.greports.interfaces;

import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public interface GroupedRows {
    Map<String, Predicate<Integer>> isGroupStartRow();
    Map<String, Predicate<Integer>> isGroupEndRow();
    Map<String, BooleanSupplier> isDefaultCollapsed();
}
