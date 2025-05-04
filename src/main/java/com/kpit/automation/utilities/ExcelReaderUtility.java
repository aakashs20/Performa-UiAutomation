/**
 * This class provides methods for reading data from an Excel file.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since   2023-12-01
 */
package com.kpit.automation.utilities;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for reading data from an Excel file. This class extends
 * {@link LoggerUtility}
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class ExcelReaderUtility extends LoggerUtility {

	/**
	 * The Logger instance for this class.
	 */
	private Logger log = getLogger(ExcelReaderUtility.class);

	/**
	 * A private variable to keep track of the current row being read from the Excel
	 * sheet.
	 */
	private int currentRow = 1; // Starts from row 1, assuming that row 0 is the header.

	/**
	 * This method starts reading data from the specified Excel file and sheet. Each
	 * row for one Test method. It throws {@link IOException} and
	 * {@link FileNotFoundException} if there is an error reading the file.
	 *
	 * @param filePath  The path to the Excel file.
	 * @param sheetName The name of the sheet to read from.
	 * @return A 2D array containing the data from the specified sheet.
	 * @throws IOException           if there is an error reading the file
	 * @throws FileNotFoundException if the file is not found
	 */
	public Object[][] testData(String filePath, String sheetName) throws IOException, FileNotFoundException {

		File file = new File(filePath);
		FileInputStream fis = null;
		Workbook workbook = null;
		Sheet sheet = null;
		Object[][] data = null;

		try {
			fis = new FileInputStream(file); // Opening the Excel file
			workbook = WorkbookFactory.create(fis); // Creating workbook from FileInputStream
			sheet = workbook.getSheet(sheetName); // Getting the specified sheet from workbook

			int rowCount = sheet.getLastRowNum(); // Getting the last row number
			int colCount = sheet.getRow(0).getLastCellNum(); // Getting the last column number of the first row

			data = new Object[1][colCount]; // Only one row data for each test method

			if (currentRow <= rowCount) { // Checking if current row is within the sheet range
				Row row = sheet.getRow(currentRow); // Getting the current row
				if (row != null) {
					for (int j = 0; j < colCount; j++) { // Iterating over columns
						Cell cell = row.getCell(j); // Getting cell at specified row and column
						if (cell != null) {
							data[0][j] = cell.toString(); // Converting cell value to string and storing in data array
						} else {
							data[0][j] = ""; // Or any default value you want for null cells
						}
					}
					currentRow++; // Moving to the next row
				}
			}

		} catch (Exception ex) { // Catching any exceptions
			ex.printStackTrace(); // Printing stack trace
			log.error(ex); // Logging the exception
		} finally {
			if (fis != null) { // Checking if FileInputStream is not null
				fis.close(); // Closing FileInputStream
			}
		}

		return data;
	}

	/**
	 * This method reads all data from the specified Excel file and sheet. It throws
	 * {@link IOException} and {@link FileNotFoundException} if there is an error
	 * reading the file.
	 *
	 * @param filePath  The path to the Excel file.
	 * @param sheetName The name of the sheet to read from.
	 * @return A 2D array containing all the data from the specified sheet.
	 * @throws IOException           if there is an error reading the file
	 * @throws FileNotFoundException if the file is not found
	 */
	public Object[][] testDataOccurance(String filePath, String sheetName) throws IOException, FileNotFoundException {

		File file = new File(filePath);
		FileInputStream fis = null;
		Workbook workbook = null;
		Sheet sheet = null;
		Object[][] data = null;

		try {
			fis = new FileInputStream(file); // Opening the Excel file
			workbook = WorkbookFactory.create(fis); // Creating workbook from FileInputStream
			sheet = workbook.getSheet(sheetName); // Getting the specified sheet from workbook

			int rowCount = sheet.getLastRowNum(); // Getting the last row number
			int colCount = sheet.getRow(0).getLastCellNum(); // Getting the last column number of the first row

			data = new Object[rowCount][colCount]; // Initializing data array with the size of the sheet

			for (int i = 0; i < rowCount; i++) { // Iterating over rows
				Row row = sheet.getRow(i + 1); // Start from row 1 (assuming row 0 is the header)
				for (int j = 0; j < colCount; j++) { // Iterating over columns
					Cell cell = row.getCell(j); // Getting cell at specified row and column
					if (cell == null) {
						data[i][j] = ""; // Setting empty string if cell is null
					} else {
						data[i][j] = cell.toString(); // Converting cell value to string and storing in data array
					}
				}
			}

		} catch (Exception ex) { // Catching any exceptions
			ex.printStackTrace(); // Printing stack trace
			log.error(ex); // Logging the exception
		} finally {
			if (fis != null) { // Checking if FileInputStream is not null
				fis.close(); // Closing FileInputStream
			}
		}

		return data;
	}

	private int currentRow1 = 1; // Starts from row 1, assuming that row 0 is the header.

	public Map<String, String> testDataMap(String filePath, String sheetName)
			throws IOException, FileNotFoundException {

		File file = new File(filePath);
		FileInputStream fis = null;
		Workbook workbook = null;
		Sheet sheet = null;
		Map<String, String> data = new HashMap<>();

		try {
			fis = new FileInputStream(file); // Opening the Excel file
			workbook = WorkbookFactory.create(fis); // Creating workbook from FileInputStream
			sheet = workbook.getSheet(sheetName); // Getting the specified sheet from workbook

			int rowCount = sheet.getLastRowNum(); // Getting the last row number
			int colCount = sheet.getRow(0).getLastCellNum(); // Getting the last column number of the first row

			if (currentRow1 <= rowCount) { // Checking if current row is within the sheet range
				Row row = sheet.getRow(currentRow1); // Getting the current row
				if (row != null) {
					for (int j = 0; j < colCount; j++) { // Iterating over columns
						Cell cell = row.getCell(j); // Getting cell at specified row and column
						String header = sheet.getRow(0).getCell(j).toString(); // Getting header from the first row
						if (cell != null) {
							data.put(header, cell.toString()); // Adding cell value to the map with header as key
						} else {
							data.put(header, ""); // Or any default value you want for null cells
						}
					}
					currentRow1++; // Moving to the next row
				}
			}

		} catch (Exception ex) { // Catching any exceptions
			ex.printStackTrace(); // Printing stack trace
			log.error(ex); // Logging the exception
		} finally {
			if (fis != null) { // Checking if FileInputStream is not null
				fis.close(); // Closing FileInputStream
			}
		}

		return data;
	}
}