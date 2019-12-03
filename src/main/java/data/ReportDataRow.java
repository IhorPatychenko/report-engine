package data;

import cell.ReportDataColumn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportDataRow {
    private List<ReportDataColumn> columns = new ArrayList<>();

    public ReportDataRow() {}

    public List<ReportDataColumn> getColumns() {
        return columns;
    }

    public void addColumn(ReportDataColumn column){
        this.columns.add(column);
    }

}
