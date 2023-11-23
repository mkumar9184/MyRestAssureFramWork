package api.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.validator.PublicClassValidator;

public class XLUtility {
	
	public FileInputStream fi;
	public OutputStream fo;
	public XSSFWorkbook Workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle cellStyle;
	String path;
	
	public XLUtility(String path) {
		this.path=path;
	}
	
	
	public int getRowCount(String sheetname) throws IOException {
		
		fi= new FileInputStream(path);
		
		Workbook = new XSSFWorkbook(fi);
		sheet = Workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		Workbook.close();
		fi.close();
		
		return rowcount;
		
		
	}
	
	public int getCellCount(String sheetname, int rownum) throws IOException {
		
		fi= new FileInputStream(path);
		
		Workbook = new XSSFWorkbook(fi);
		sheet = Workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellCount= row.getLastCellNum();
		Workbook.close();
		fi.close();
		
		return cellCount;
		
		
	}
	
	public String getCellData(String sheetname, int rownum,int cellnumber) throws IOException {
		
		String data ;
		
		fi= new FileInputStream(path);
		
		Workbook = new XSSFWorkbook(fi);
		sheet = Workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell= row.getCell(cellnumber);
		
		DataFormatter formatter = new DataFormatter();
		
		
		try {
			data= formatter.formatCellValue(cell);
		} catch (Exception e) {
			// TODO: handle exception
			data= "";
			
		}
		
		
		
		Workbook.close();
		fi.close();
		
		return data;
		
		
	}
	
	
public void setCellData(String sheetname, int rownum,int column, String data) throws IOException {
		
		
	File xfile = new File(path);
	
	if(!xfile.exists()) {		//if file is not exist create new file
		Workbook =new XSSFWorkbook();
		fo= new FileOutputStream(path);
		Workbook.write(fo);
	}
	
	fi= new FileInputStream(path);
	Workbook = new XSSFWorkbook(fi);
	
	if(Workbook.getSheetIndex(sheetname)==-1) 
			Workbook.createSheet(sheetname);
	sheet = Workbook.getSheet(sheetname);

	if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
	row= sheet.getRow(rownum);
	
	cell = row.createCell(column);
	cell.setCellValue(data);
	fo= new FileOutputStream(path);
	Workbook.write(fo);
	Workbook.close();
	fo.close();
	fi.close();
	
}	

}
