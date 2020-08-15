package com.company.datalayer.model;

import junit.framework.TestCase;

public class DataCellTest extends TestCase {

    private final DataCell dataCell;

    public DataCellTest(){
        dataCell = new DataCell("Name","Bill Gates");
    }

    public void testGetColumnHeader() {
        assertEquals("Name",dataCell.getColumnHeader());
    }

    public void testGetCellValue() {
        assertEquals("Bill Gates",dataCell.getCellValue());
    }
}