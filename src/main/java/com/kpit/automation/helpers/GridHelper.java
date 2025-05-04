package com.kpit.automation.helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GridHelper {

	/**
	 * Get the header element based on the header name.
	 */
	public WebElement getHeaderElementByName(List<WebElement> columnHeaders, String headerName) {
		for (WebElement header : columnHeaders) {
			if (header.getText().equalsIgnoreCase(headerName)) {
				return header;
			}
		}
		throw new NoSuchElementException("Column header not found: " + headerName);
	}

	/**
	 * Get the column index based on the header element.
	 */
	public int getColumnIndexByHeaderElement(List<WebElement> columnHeaders, WebElement headerElement) {
		return columnHeaders.indexOf(headerElement);
	}

	/**
	 * Get column values based on the column index.
	 */
	public List<String> getColumnValuesByIndex(List<WebElement> gridCells, List<WebElement> columnHeaders,
			int columnIndex) {
		List<String> columnValues = new ArrayList<>();
		for (int i = columnIndex; i < gridCells.size(); i += columnHeaders.size()) {
			columnValues.add(gridCells.get(i).getText());
		}
		return columnValues;
	}

	/**
	 * Get column values by header name.
	 */
	public List<String> getColumnValuesByHeaderName(List<WebElement> columnHeaders, List<WebElement> gridCells,
			String headerName) {
		try {
			WebElement headerElement = getHeaderElementByName(columnHeaders, headerName);
			int columnIndex = getColumnIndexByHeaderElement(columnHeaders, headerElement);
			return getColumnValuesByIndex(gridCells, columnHeaders, columnIndex);
		} catch (Exception e) {
			System.err.println(
					"Error occurred while getting column values for header '" + headerName + "': " + e.getMessage());
			return new ArrayList<>();
		}
	}
	
	
	public List<WebElement> getColumnElementsByIndex(List<WebElement> gridCells, List<WebElement> columnHeaders,
			int columnIndex) {
		List<WebElement> columnValues = new ArrayList<>();
		for (int i = columnIndex; i < gridCells.size(); i += columnHeaders.size()) {
			columnValues.add(gridCells.get(i));
		}
		return columnValues;
	}

	public List<WebElement> getColumnElementsByHeaderName(List<WebElement> columnHeaders, List<WebElement> gridCells,
			String headerName) {
		try {
			WebElement headerElement = getHeaderElementByName(columnHeaders, headerName);
			int columnIndex = getColumnIndexByHeaderElement(columnHeaders, headerElement);
			return getColumnElementsByIndex(gridCells, columnHeaders, columnIndex);
		} catch (Exception e) {
			System.err.println(
					"Error occurred while getting column values for header '" + headerName + "': " + e.getMessage());
			return new ArrayList<>();
		}
	}
}
