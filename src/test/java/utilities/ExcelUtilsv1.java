package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilsv1 {

//	Excel file >>> Workbook >>> Sheets >>> Rows >>> Cells (Columns)

	public void readExcelData() throws IOException {

//	public static void main(String[] args) throws IOException {

		// =============== Load Excel file in read mode ==================
//		String $filepath = "\\testData\\opencart_testdata.xlsx";
		String $filepath = "\\testData\\caldata.xlsx";
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + $filepath);

		// =============== Get the workbook from the loaded file ==================
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// =============== Extract sheet from the workbook ==================
//		XSSFSheet sheet = workbook.getSheetAt(0);

		XSSFSheet sheet = workbook.getSheet("Sheet1");

		// =============== Get the number of active rows and cells/columns
		// ==================
		int totalNumOfRows = sheet.getLastRowNum(); // last row num count from 1 => XXX

		int totalNumOfCells = sheet.getRow(1).getLastCellNum(); // row zero is header

		System.out.println("Total number of rows in Excel sheet: " + totalNumOfRows);
		System.out.println("Total number of cells per row: " + totalNumOfCells);

		// ====================Intiatlize 2D array to store Row and Cell data
		// ====================================
//		String[][] data2 = new String[totalNumOfRows - 1][totalNumOfCells];

		// =============== Get data from Excel rows and cells ==================

		// Using for 2 for loops

		for (int r = 1; r <= totalNumOfRows; r++) { // Start from 1 to skip the header row
			// used <= because last row num count from 1 => XXX
			// Get row
			XSSFRow currentRowAtIndexR = sheet.getRow(r);

			for (int c = 0; c < totalNumOfCells; c++) { // reading cell data from current row

				XSSFCell currentCell = currentRowAtIndexR.getCell(c);
				String currentCelltoString = currentCell.toString(); // Convert xls cell data type to String format

				System.out.print("Current Cell is : " + currentCelltoString + "\t"); // \t -> tab space after each cell

				// Store Excel Data as a 2D array
//				data2[r-1][c] = currentCelltoString;

			}
			System.out.println(); // new line after each row
		}
		// TEARDOWN
		// To prevent memory leaks we have to close the workbook and file once we are
		// done reading it
		workbook.close();
		file.close();
//		return data2;
		System.out.println("**** File was read successfully ****");
	}

	public void excelWriter_StaticData() throws IOException {

		// Create new excel file using FileOutputStream
		String $filepath = "\\testData\\createdWorkbook.xlsx";
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + $filepath);

		// Create new workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create new sheet
		XSSFSheet sheet = workbook.createSheet(); // sheet0 is default when nothing is passed

		// Create row(r)
		XSSFRow row1 = sheet.createRow(0);
		// Create cells in row(r) and pass in static data entries
		row1.createCell(0).setCellValue("username");
		row1.createCell(1).setCellValue("password");
		row1.createCell(2).setCellValue("expected results");

		// Create row(r)
		XSSFRow row2 = sheet.createRow(1);
		// Create cells in row(r) and pass in entries
		row2.createCell(0).setCellValue("wisdomochus1@gmail.com");
		row2.createCell(1).setCellValue("Testing!23");
		row2.createCell(2).setCellValue("success");

		// Create row(r)
		XSSFRow row3 = sheet.createRow(2);
		// Create cells in row(r) and pass in entries
		row3.createCell(0).setCellValue("xyz@gmail.com");
		row3.createCell(1).setCellValue("Testing!23");
		row3.createCell(2).setCellValue("failed");

		// save and write workbook data to file
		workbook.write(file);

		// teardown
		workbook.close();
		file.close();

		System.out.println("**** File was created successfully ****");

	}

	public void excelWriter_DynamicData() throws IOException {

		// Create new excel file using FileOutputStream
		String $filepath = "\\testData\\createdDynamicWorkbook.xlsx";
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + $filepath);

		// Create new workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create new sheet
		XSSFSheet sheet = workbook.createSheet("DynamicData"); // sheet0 is default when nothing is passed

		// Scanner class is used to accept user inputs dynamically
		Scanner scanner = new Scanner(System.in); // pass input stream .in

		// Accept input from the user
		System.out.println("Enter number of rows : ");
		int numOfRows = scanner.nextInt();

		System.out.println("Enter number of cells : ");
		int numOfCells = scanner.nextInt();

		// Create nested for loop for rows and cells
		for (int r = 0; r < numOfRows; r++) {

			// Create row on each iteration
			XSSFRow currentRow = sheet.createRow(r);

			for (int c = 0; c < numOfCells; c++) {

				// create cell for each index
				XSSFCell currentCell = currentRow.createCell(c);

				// accept user input
				System.out.println("Set cell value : ");
				String cellValue = scanner.next();
				// update cell with user input
				currentCell.setCellValue(cellValue);
			}
		}

		// save and write workbook data to file
		workbook.write(file);

		// teardown
		scanner.close();
		workbook.close();
		file.close();
		
		System.out.println("**** File was created successfully ****");

	}

	public static void main(String[] args) throws IOException {

		ExcelUtilsv1 ut = new ExcelUtilsv1(); // Instanctiate method
		ut.readExcelData(); // Call method
//		ut.excelWriter_StaticData();
//		ut.excelWriter_DynamicData();

	}

}
