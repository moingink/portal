package com.yonyou.util;

/** 
 * @author zzh
 * @version 创建时间：2016年10月31日
 * 类说明 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yonyou.util.jdbc.BaseDao;

public class ExcelTools {
	public static ConcurrentHashMap<String, List<Map<String, String>>> parse(
			String fileName) throws BussnissException {

		try {
			return parse(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public static ConcurrentHashMap<String, List<Map<String, String>>> parse(
			InputStream fileInputStream) throws BussnissException {
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
		ConcurrentHashMap<String, String> temp = null;
		ConcurrentHashMap<String, String> key = new ConcurrentHashMap<String, String>();

		ConcurrentHashMap<String, List<Map<String, String>>> result = new ConcurrentHashMap<String, List<Map<String, String>>>();
		try {
			wb = PoiUtils.getWorkbook(fileInputStream);
			if (wb == null) {
				throw new Exception("Workbook is null");
			}

			sheet = wb.getSheetAt(0);

			HSSFRow dataSourceRow = sheet.getRow(0);
			String dataSourceCode = PoiUtils.getCellStrValue(dataSourceRow
					.getCell((short) 0));
			System.out.println("dataSourceCode" + dataSourceCode);
			if (null == dataSourceCode || "".equals(dataSourceCode.trim())) {
				throw new BussnissException("第一行第一列没有配置数据源编码！");
			}

			result.put(dataSourceCode, data);
			// ConcurrentHashMap<String,String> dataSource =
			// DataSourceUtil.getDataSource(dataSourceCode);
			// if(null==dataSource ){
			// throw new BussnissException("系统没有配置["+dataSourceCode+"]编码的数据源！");
			// }

			HSSFRow headRowCell = sheet.getRow(1);
			System.out.println("headRowCell:");
			for (int j = 0; j < headRowCell.getLastCellNum(); j++) {
				HSSFCell cell = headRowCell.getCell((short) j);
				key.put("" + j, PoiUtils.getCellStrValue(headRowCell
						.getCell((short) j)));
				System.out.println("headRowCell"
						+ PoiUtils.getCellStrValue(headRowCell
								.getCell((short) j)));
			}

			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				temp = new ConcurrentHashMap<String, String>();

				int cnum = row.getLastCellNum();
				for (int j = 0; j < cnum; j++) {
					HSSFCell cell = row.getCell((short) j);
					if (j == 0) {
						if (cell == null) {
							break;
						}
						if ((PoiUtils.getCellStrValue(cell) == null)
								|| (PoiUtils.getCellStrValue(cell).equals(""))) {
							break;
						}
					}

					if (cell != null) {
						String msg = PoiUtils.getCellStrValue(cell);
						if ((msg == null) || (msg.length() == 0)) {
							msg = "";
						}
						temp.put(key.get(""+j), msg);// key.get(""+j)
						System.out.println("dataRowCell"
								+ PoiUtils.getCellStrValue(cell));
					}

				}

				data.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BussnissException("");
		}
		if (data.size() == 0) {
			throw new BussnissException("没有读取到业务数据！");
		}
		return result;
	}

	public static void main(String arg[]) {
		try {
			File file = new File("D://upload");
			String[] str = null;
			List<Map<String, String>> entityList;
			ConcurrentHashMap<String, List<Map<String,String>>> tempresult = null;
			entityList=tempresult.get("");
			if (file.isDirectory()) {
				str = file.list();
				for (int i = 0; i < str.length; i++) {
					if (str[i].endsWith(".xls")) {
						tempresult = ExcelTools.parse(file.getPath() + "//"
								+ str[i]);
						//BaseDao.getBaseDao().insert(tempresult.keys().nextElement(), tempresult.get(tempresult.keys().nextElement()));
					}
				}
			}

		} catch (BussnissException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}