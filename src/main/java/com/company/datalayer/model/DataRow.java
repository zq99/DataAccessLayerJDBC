package com.company.datalayer.model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
public class DataRow{

    private final LinkedHashMap<String, DataCell> dataRowMap = new LinkedHashMap<>();

    public void add(DataCell dataCell){
        this.dataRowMap.put(dataCell.getColumnHeader(),dataCell);
    }

    public String getValue(String columnName){
        if (this.dataRowMap.containsKey(columnName)) {
            return this.dataRowMap.get(columnName).getCellValue();
        }
        return null;
    }

    public int getColumnCount(){
        return this.dataRowMap.size();
    }

    public boolean isPopulated(){
        return this.dataRowMap.size() > 0;
    }

    public void clearRow(){
        this.dataRowMap.clear();
    }

    public String toString(){
        return toString(false);
    }

    public String toString(boolean showColumnNames){
        StringBuilder sb = new StringBuilder();
        if(isPopulated()){
            for (Map.Entry<String, DataCell> entry : dataRowMap.entrySet() )
            {
                DataCell dataCell = entry.getValue();
                String columnName = entry .getKey();
                String cell = showColumnNames ? (columnName + " = " + dataCell.getCellValue()) : dataCell.getCellValue();
                sb.append(sb.length() > 0 ? "," + cell : cell);
            }
        }
        return sb.toString();
    }

    public String getValueAtColumnIndex(int index){
        int count = 0;
        String value = "";
        if(isPopulated()){
            for (Map.Entry<String, DataCell> entry : dataRowMap.entrySet() )
            {
                if(count == index) {
                    DataCell dataCell = entry.getValue();
                    value = dataCell.getCellValue();
                    break;
                }
                count++;
            }
        }
        return value;
    }

}
