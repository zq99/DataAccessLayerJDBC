package com.company.datalayer.model;

import junit.framework.TestCase;

public class DataRowTest extends TestCase {

    private final DataRow dataRow;

    public DataRowTest(){
        dataRow = new DataRow();
        DataCell dataCell1 = new DataCell("ID","1");
        DataCell dataCell2 = new DataCell("Colour","Blue");
        dataRow.add(dataCell1);
        dataRow.add(dataCell2);
    }

    public void testAddingData() {
        assertTrue(dataRow.isPopulated());
        assertEquals(2,dataRow.getColumnCount());
        assertEquals("1,Blue", dataRow.toString());
        assertEquals("ID = 1,Colour = Blue", dataRow.toString(true));
        assertEquals(2,dataRow.getColumnCount());
        assertEquals("1",dataRow.getValue("ID"));
        assertEquals("Blue",dataRow.getValue("Colour"));
        assertEquals("Blue",dataRow.getValueAtColumnIndex(1));
        assertEquals("1",dataRow.getValueAtColumnIndex(0));
        dataRow.clearRow();
        assertEquals(0,dataRow.getColumnCount());
    }

}