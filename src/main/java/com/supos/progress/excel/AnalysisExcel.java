package com.supos.progress.excel;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnalysisExcel {
    public static String readXls(String path){
        String text = "";
        try {
            FileInputStream inputStream = new FileInputStream(path);
            HSSFWorkbook excel = new HSSFWorkbook(inputStream);
            //获取第一个sheet
            HSSFSheet sheet0 = excel.getSheetAt(0);
            for (Iterator rowIterator=sheet0.rowIterator();rowIterator.hasNext();){
                HSSFRow row = (HSSFRow)rowIterator.next();
                for (Iterator iterator=row.cellIterator();iterator.hasNext();){
                    HSSFCell cell = (HSSFCell)iterator.next();
                    //根据单元的类型，读取相应的结果
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                        text+=cell.getStringCellValue()+"\t";
                    }else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                        text+=cell.getNumericCellValue()+"\t";
                    }else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                        text+=cell.getCellFormula()+"\t";
                    }
                }
                text+="\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static List<JSONObject> readXlsx(String path){
        List<JSONObject> list = new ArrayList<>();
        try {
            OPCPackage opcPackage = OPCPackage.open(path);
            XSSFWorkbook excel = new XSSFWorkbook(opcPackage);
            //获取第一个sheet
            XSSFSheet sheet0 = excel.getSheetAt(0);
            for (int rowNum = 1;rowNum<= sheet0.getLastRowNum();rowNum++){
                JSONObject jsonObject = new JSONObject();
                XSSFRow row = sheet0.getRow(rowNum);
                if (row == null){
                    continue;
                }
                XSSFCell cell = row.getCell(0);
                jsonObject.put("name",cell.getStringCellValue());
                cell = row.getCell(1);
                jsonObject.put("time",cell.getNumericCellValue());
                list.add(jsonObject);
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
