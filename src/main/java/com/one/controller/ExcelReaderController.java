package com.one.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jay time: 2016年7月14日
 */
@RestController
@RequestMapping("file")
public class ExcelReaderController {

	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;


	@RequestMapping(value = "test", method = RequestMethod.GET)
	@ResponseBody
	public List<List<String>> saveFile() throws Exception {
		List<List<String>> list = new ArrayList();
		try {
			// 同时支持Excel 2003、2007
			InputStream inp = new FileInputStream("e:\\test.xls");
			Workbook workbook = new HSSFWorkbook(inp); // 这种方式 Excel
														// 2003/2007/2010
														// 都是可以处理的
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			// 遍历每个Sheet

			for (int s = 0; s < 1; s++) {

				Sheet sheet = workbook.getSheetAt(s);
				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
				// 遍历每一行
				for (int r = 0; r < rowCount; r++) {
					List<String> listItem = new ArrayList();
					Row row = sheet.getRow(r);
					int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
					// 遍历每一列
					for (int c = 0; c < cellCount; c++) {
						Cell cell = row.getCell(c);
						int cellType = cell.getCellType();
						String cellValue = null;
						switch (cellType) {
						case Cell.CELL_TYPE_STRING: // 文本
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC: // 数字、日期
							if (DateUtil.isCellDateFormatted(cell)) {
								// cellValue =
								// cell.getDateCellValue().toString(); //日期型
							} else {
								Double reseve3 = Double.parseDouble(cell.getNumericCellValue() + "");
								int b = reseve3.intValue();
								cellValue = String.valueOf(b); // 数字
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN: // 布尔型
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_BLANK: // 空白
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_ERROR: // 错误
							cellValue = "错误";
							break;
						case Cell.CELL_TYPE_FORMULA: // 公式
							cellValue = "错误";
							break;
						default:
							cellValue = "错误";
						}

						listItem.add(cellValue);
					}
					System.out.println();
					list.add(listItem);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
