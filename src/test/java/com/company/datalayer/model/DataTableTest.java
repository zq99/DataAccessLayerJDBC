package com.company.datalayer.model;

import com.company.common.Iterator;
import junit.framework.TestCase;

public class DataTableTest extends TestCase {

    private final DataTable dataTable = new DataTable();
    private int totalRows;
    private static final String[] names = {"Bill Gates","Elon Musk","Jeff Bezos","Tim Cook"};
    private static final String[] company = {"Microsoft","Tesla","Amazon","Apple"};
    private static final String header_name = "name";
    private static final String header_company = "company";

    public DataTableTest(){
        populateTableObject();
    }

    private void populateTableObject(){
        assertEquals(names.length,company.length);
        totalRows = names.length;
        for (int i=0; i<totalRows; i++) {
            DataCell dataName = new DataCell(header_name,names[i]);
            DataCell dataCompany = new DataCell(header_company,company[i]);
            DataRow dataRow = new DataRow();
            dataRow.add(dataName);
            dataRow.add(dataCompany);
            dataTable.addRow(dataRow);
        }
    }

    public void testPopulation(){
        assertTrue(dataTable.isPopulated());
        assertEquals(totalRows,dataTable.getRowCount());
    }

    public void testClear(){
        assertTrue(dataTable.isPopulated());
        dataTable.clearTable();
        assertEquals(0,dataTable.getRowCount());
        populateTableObject();
        assertEquals(totalRows,dataTable.getRowCount());
        assertTrue(dataTable.isPopulated());
    }

    public void testGetRow(){
        assertTrue(dataTable.isPopulated());
        DataRow dataRow = dataTable.getRow(0);
        assertTrue(dataRow.isPopulated());
        assertEquals("Bill Gates",dataRow.getValue(header_name));
        assertEquals("Microsoft",dataRow.getValue(header_company));
    }

    public void testIterate(){
        int index = 0;
        assertEquals(names.length,company.length);
        for(Iterator<?> iterator = dataTable.getIterator(); iterator.hasNext();){
            DataRow row = (DataRow) iterator.next();
            assertEquals(names[index],row.getValue(header_name));
            assertEquals(company[index],row.getValue(header_company));
            index++;
        }
    }
}