package mobileapp.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


import java.io.File;

public class XLSTestDataLoader {
	public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) throws Exception {
		String[][] tabArray = null;

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName);
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();

		Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);

		endRow = tableEnd.getRow();
		endCol = tableEnd.getColumn();
		System.out.println("Test:  " + tableName + " startRow=" + startRow
				+ ", endRow=" + endRow + ", " + "startCol=" + startCol
				+ ", endCol=" + endCol);
		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;

		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}

		return (tabArray);
	}

	public Object[][] getDataWithoutCell(String xlFilePath, String sheetName) {
		Xls_Reader excel = new Xls_Reader(xlFilePath);
		int rows = excel.getRowCount(sheetName); // Get Row Count
		int cols = excel.getColumnCount(sheetName); // Get Col Count
		Object data[][] = new Object[rows - 1][cols]; // -1

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum,
						rowNum); // -2
			}
		}
		return data;

	}
}
