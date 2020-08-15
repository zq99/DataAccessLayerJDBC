package com.company.datalayer.model;

import com.company.common.Iterator;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class DataTable {

    //Hierarchy from lowest to top is: DataCell -> DataRow -> DataTable

    private final ArrayList<DataRow> dataRows = new ArrayList<>();

    public void addRow(DataRow dataRow){
        dataRows.add(dataRow);
    }

    public void clearTable(){
        dataRows.clear();
    }

    public long getRowCount(){
        return dataRows.size();
    }

    public DataRow getRow(int index){
        if(isValidIndexValue(index)){
            return dataRows.get(index);
        }
        return null;
    }

    public void deleteRow(int index){
        if(isValidIndexValue(index)){
            dataRows.remove(index);
        }
    }

    public boolean isPopulated(){
        return getRowCount() > 0;
    }

    private boolean isValidIndexValue(int index){
        return index>=0 && index<dataRows.size();
    }

    //@Override
    public Iterator<?> getIterator() {
        return new DataTableIterator();
    }

    private class DataTableIterator implements Iterator {

        int index=0;

        @Override
        public boolean hasNext() {
            return index < dataRows.size();
        }

        @Override
        public DataRow next() {
            if(this.hasNext()){
                return dataRows.get(index++);
            }
            return null;
        }
    }
}
