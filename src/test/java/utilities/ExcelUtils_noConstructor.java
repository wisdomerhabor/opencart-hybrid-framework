package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils_noConstructor {

	// Best practice: Define/Declare global variables and classes
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public static String data;
	public static DataFormatter formatter;

	// 1.
	// static -> class object not required to call method i.e new ExcelUtils_v2
	public int getRowCount(String filepath, String sheetname) throws IOException {
		// open file in read mode > fi
		fi = new FileInputStream(filepath);

		// open wb and get sheet
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetname);

		// get row count
		int rowCount = sheet.getLastRowNum();

		// Teardown
		wb.close();
		fi.close();

		return rowCount;
	}

	// 2.
	public int getCellCount(String filepath, String sheetname, int rowNum) throws IOException {
		// open file in read mode > fi
		fi = new FileInputStream(filepath);

		// open wb and get sheet
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetname);

		// get row
		row = sheet.getRow(rowNum);

		// get cell count for selected row
		int cellCount = row.getLastCellNum();

		// Teardown
		wb.close();
		fi.close();

		return cellCount;
	}

	// 3. Read/get cell data
	public String readCellData(String filepath, String sheetname, int rowNum, int cellNum) throws IOException {
		try {
			// Open file in read mode
			fi = new FileInputStream(filepath);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(sheetname);

			// Get row (handle null case)
			row = sheet.getRow(rowNum);
			if (row != null) {
				// Get cell (handle null case)
				cell = row.getCell(cellNum);
				if (cell != null) {
					// Read cell data
					formatter = new DataFormatter();
					data = formatter.formatCellValue(cell);
				}
			}
		} catch (Exception e) {
			System.out.println("Error reading cell data: " + e.getMessage());
		} finally {
			// Cleanup resources
			if (wb != null)
				wb.close();
			if (fi != null)
				fi.close();
		}

		return data;
	}

	// 4. Update cell data
	public String setCellData(String filepath, String sheetname, int rowNum, int cellNum, String data)
			throws IOException {
		// open file in read mode > fi
		fi = new FileInputStream(filepath);

		// open wb and get sheet
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetname);

		// get row
		row = sheet.getRow(rowNum);

		// create cell and set cell value with data passed
		cell = row.createCell(cellNum);
		cell.setCellValue(data);

		// change file mode to write mode > fo
		fo = new FileOutputStream(filepath);
		wb.write(fo);

		// Teardown
		wb.close();
		fi.close();
		fo.close();

		return data;
	}

	// 5.
	public void fillGreenColour(String filepath, String sheetname, int rowNum, int cellNum) throws IOException {
		// open file in read mode > fi
		fi = new FileInputStream(filepath);

		// open wb and get sheet
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetname);

		// get row
		row = sheet.getRow(rowNum);

		// get cell
		cell = row.getCell(cellNum);

		// Create cell style
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// set created style
		cell.setCellStyle(style);

		// Write changes
		fo = new FileOutputStream(filepath);
		wb.write(fo);

		// Teardown
		wb.close();
		fi.close();
		fo.close();
	}

	// 6.
	public void fillRedColour(String filepath, String sheetname, int rowNum, int cellNum) throws IOException {
		// open file in read mode > fi
		fi = new FileInputStream(filepath);

		// open wb and get sheet
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetname);

		// get row
		row = sheet.getRow(rowNum);

		// get cell
		cell = row.getCell(cellNum);

		// Create cell style
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// set created style
		cell.setCellStyle(style);

		// Write changes
		fo = new FileOutputStream(filepath);
		wb.write(fo);

		// Teardown
		wb.close();
		fi.close();
		fo.close();
	}
}
