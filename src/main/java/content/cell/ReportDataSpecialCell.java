package content.cell;

import annotations.ReportSpecialCell;

public class ReportDataSpecialCell {
    private String targetId;
    private ReportSpecialCell.ValueType valueType;
    private String value;
    private int columnIndex;

    public ReportDataSpecialCell(final String targetId, final ReportSpecialCell.ValueType valueType, final String value) {
        this.targetId = targetId;
        this.valueType = valueType;
        this.value = value;
    }

    public String getTargetId() {
        return targetId;
    }

    public ReportSpecialCell.ValueType getValueType() {
        return valueType;
    }

    public String getValue() {
        return value;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public ReportDataSpecialCell setColumnIndex(final int columnIndex) {
        this.columnIndex = columnIndex;
        return this;
    }
}