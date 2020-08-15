package com.company.datalayer.model;

public class DataCell {

    private final String columnHeader;
    private final String cellValue;

    public DataCell(String columnHeader, String cellValue) {
        this.columnHeader = columnHeader;
        this.cellValue = cellValue;
    }

    public String getColumnHeader() {
        return columnHeader;
    }

    public String getCellValue() {
        return cellValue;
    }

}
