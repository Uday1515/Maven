package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	 XSSFWorkbook wb;
	 //creating constructor for reading excel path
	 public ExcelFileUtil(String excelpath) throws Throwable
	 {
		 FileInputStream fi = new FileInputStream(excelpath);
		 wb = new XSSFWorkbook(fi);
	 }
	//count no of rows in a sheet
	 public int rowCount(String SheetName)
	 {
		 return wb.getSheet(SheetName).getLastRowNum();
		 
	 }
	//count no of cells in a row
	 public int cellCount(String SheetName)
	 {
		 return wb.getSheet(SheetName).getRow(0).getLastCellNum();
	 }

	//read cell data
	 public String getCellData(String SheetName,int row,int column)
	 {
		 String data="";
		 if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		 {
			 int celldata =(int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
			 data = String.valueOf(celldata);		 
		 }
		 else
		 {
			 data=wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
			
		 }
		return data;	 
	 }
	 //set cell data
	 @SuppressWarnings("deprecation")
	public void setCellData(String SheetName,int row,int column,String status,String writeexcel)
	  throws Throwable{
		 // get sheet from wb
		 XSSFSheet ws = wb.getSheet(SheetName);
		 //get row in a sheet
		 XSSFRow rowNum = ws.getRow(row);
		 //create cell in a row
		 XSSFCell cell = rowNum.createCell(column);
		 //write your status
		 cell.setCellValue(status);
		 if(status.equalsIgnoreCase("Pass"))
		 {
			 XSSFCellStyle style = wb.createCellStyle();
			 XSSFFont font = wb.createFont();
			 //.colour text
		     font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
		     font.setBold(true);
		     font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		     style.setFont(font);
		     rowNum.getCell(column).setCellStyle(style);
		 }
		 else if(status.equalsIgnoreCase("Fail"))
		 {
			 XSSFCellStyle style = wb.createCellStyle();
			 XSSFFont font = wb.createFont();
			 //.colour text
		     font.setColor(IndexedColors.RED.getIndex());
		     font.setBold(true);
		     font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		     style.setFont(font);
		     rowNum.getCell(column).setCellStyle(style);
		 }
		 else if(status.equalsIgnoreCase("Blocked"))
		 {
			 XSSFCellStyle style = wb.createCellStyle();
			 XSSFFont font = wb.createFont();
			 //.colour text
		     font.setColor(IndexedColors.DARK_BLUE.getIndex());
		     font.setBold(true);
		     font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		     style.setFont(font);
		     rowNum.getCell(column).setCellStyle(style);
		 }
		 FileOutputStream fo = new FileOutputStream(writeexcel);
		 wb.write(fo);


}
	 public static void main(String[] args) throws Throwable {
		
		 ExcelFileUtil xl= new ExcelFileUtil("D://Data.xlsx");
		 int rc= xl.rowCount("Logindata");
		 int cc = xl.cellCount("Logindata");
		 System.out.println(rc+"  "+cc);
		 for(int i=1;i<rc;i++)
		 {
			 String user= xl.getCellData("Logindata", i, 0);
			 String pass = xl.getCellData("Logindata", i, 1);
			 System.out.println(user+"  "+pass);
			 xl.setCellData("Logindata", i, 2, "Pass", "D://Resultspas.xlsx");
		 }
		 }
		 
		 
		 
		 
		 
	}
	 
