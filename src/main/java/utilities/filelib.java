package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class filelib implements constants {

	public static FileInputStream fis;
	public static Workbook wb;
	public static Sheet sh;
	public static Row r;
	public static Cell c;
	public static Properties prop;
	
	
	public static String getDataFromExcel(String path, String sheet, int row, int cell) throws Throwable
	{
		 fis = new FileInputStream(excelPath);
		 wb = WorkbookFactory.create(fis);
		 sh = wb.getSheet(sheet);
		 r = sh.getRow(row);
		 c = r.getCell(cell);
		String value = c.getStringCellValue().toString();
		return value;
	}
	
	public static int getrowcount(String path, String sheet) throws Throwable
	{
		fis = new FileInputStream(excelPath);
		wb = WorkbookFactory.create(fis);
		int rCount = wb.getSheet(sheet).getLastRowNum();
		return rCount;
	}
	
	public static int getcellcount(String path, String sheet, int row) throws Throwable
	{
		fis = new FileInputStream(excelPath);
		wb = WorkbookFactory.create(fis);
		int cCount = wb.getSheet(sheet).getRow(row).getLastCellNum();
		return cCount;
	}
	
	public String getproperty(String key) throws Throwable 
	{
		prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
		
	}
	
	public String readpropertydata(String proppath, String key) throws Throwable 
	{
		FileInputStream fis  = new FileInputStream(configpath);
		Properties prop = new Properties();
		prop.load(fis);
		String propvalue = prop.getProperty(key, "incorrect");
		return propvalue;
	}
	
}
