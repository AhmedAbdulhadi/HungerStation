package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	int index =0;
	int column=0;
	String name;
	public static ArrayList<String> data = new ArrayList<String>();

	
	public void extract() throws IOException {
	//Startegy #1 (Create instance of WorkBook)
	
	//send by user book1.xlsx
	FileInputStream fis = new FileInputStream("Book1.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	
	//Staraegy #2 Get All Sheets
	int totalNumberOfSheets = workbook.getNumberOfSheets();
	
	for (int i = 0; i < totalNumberOfSheets; i++) {
		System.out.println("Sheet name is: " +workbook.getSheetName(i));
		
		//Send by user sheet name
		//Startegy #3 get specific sheet
		if(workbook.getSheetName(i).equalsIgnoreCase("addPlaces")) {
			XSSFSheet objSheet = workbook.getSheetAt(i);
			
			//Strategy #4 get All Rows
			Iterator<Row> rows = objSheet.iterator(); 
			Row headerRow = rows.next();
			
			//Startegy #5 Get All cells 
			Iterator<Cell> headerCell = headerRow.cellIterator();
			
			while(headerCell.hasNext()) {
				
				//Startegy #6 Point the first cell
				Cell value = headerCell.next();
				System.out.println("Header is: " +value.toString());
				
				//This is to verify the "testcase" column even if the person changes the column index
				if(value.getStringCellValue().equalsIgnoreCase("testcase")) {
					
					column = index;
					System.out.println("Column index is " + column);
				}
				
				index++;
			}
			
			while(rows.hasNext()) {
				
				Row objRow = rows.next();
				
				System.out.println("Column name is " + objRow.getCell(column).getStringCellValue());
				//It will be send from user / Testcase name (login)
				if(objRow.getCell(column).getStringCellValue().equalsIgnoreCase("login")) {
					
					//run test
					System.out.println("Here we goo!!");
					
					Iterator<Cell> rowCell = objRow.cellIterator();
					rowCell.next();
					while(rowCell.hasNext()) {
						
						Cell cellValue = rowCell.next();
						if(cellValue.getCellType()==CellType.STRING)
						data.add(cellValue.getStringCellValue());
						
						else if (cellValue.getCellType() == CellType.NUMERIC)
						data.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));
						
						
						System.out.println(data.toString());
					}
				
				}
				
			}
		}
		
		
	}
	
}
}
