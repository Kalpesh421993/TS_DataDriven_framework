package com.test.Utilities;

import java.io.FileInputStream;

import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {


	private FileInputStream inputStream;
	private XSSFWorkbook workBook;
	private XSSFSheet excelSheet;
	private XSSFCell cell;
	private Logger log;
	
	public ReadExcelFile(Logger log) {
		this.log = log;
		log.info("ReadExcelFile");
	}

	public String getCellValue(String fileName, String sheetName, int rowNo, int cellNo/*column no.*/)
	{
		try
		{

			inputStream = new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet =  workBook.getSheet(sheetName);
			cell = workBook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);

			return cell.getStringCellValue();

		}

		catch (Exception e)

		{
			e.printStackTrace();
			return "";

		}
	}

	public int getRowCount (String fileName, String sheetName)
	{
		try
		{
			inputStream = new FileInputStream(fileName);
			//create XSSFWorkBook Class object for excel file manipulation

			workBook = new XSSFWorkbook(inputStream);
			excelSheet =  workBook.getSheet(sheetName);
			log.info("Rows: " + excelSheet.getLastRowNum());

			//get total no. of rows
			int ttlRows = excelSheet.getLastRowNum() + 1;

			return ttlRows;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}

	}

	public int getColCount (String fileName, String sheetName)
	{
		try
		{
			inputStream = new FileInputStream(fileName);
			//create XSSFWorkBook Class object for excel file manipulation
			workBook = new XSSFWorkbook(inputStream);
			excelSheet =  workBook.getSheet(sheetName);

			//get total no. of columns
			int ttlCells = excelSheet.getRow(0).getLastCellNum();
			log.info("Cells: " + ttlCells);
			return ttlCells;
		}catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}