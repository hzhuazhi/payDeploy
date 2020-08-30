package com.xn.common.util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportData {
	/**
	 * 输出excel表格文件
	 * @param list:数据
	 * @param titles:excel第一行名称
	 * @param titleCode:Map的key
	 * @param filename:文件名
	 * @param response
	 */
	public static void exportExcel(List<Map<String,Object>> list,
			String[] titles, String[] titleCode, String filename,
			HttpServletResponse response) {
		WritableWorkbook workbook;
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("utf-8"), "ISO8859-1")
			+ ".xls");
			OutputStream os = response.getOutputStream();
			workbook = Workbook.createWorkbook(os);
			// 创建新的一页
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			
			for (int i = 0; i < titles.length; i++) {
				Label label = new Label(i, 0, titles[i]);
				sheet.addCell(label);
			}
			// 内容数据
			for (int i = 0; i < list.size(); i++) { // 一行
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				// 一列
				for (int j = 0; j < titles.length; j++) {
					Label label = null;
					Object param = map.get(titleCode[j]);
					if (map.get(titleCode[j]) != null || titleCode[j] != null) {
						jxl.write.Number number = null;
						// 如果数值为整型
						if (param instanceof Integer) {
							int value = ((Integer) param).intValue();
							number = new jxl.write.Number(j, i + 1, value);
							sheet.addCell(number);
							// 如果数值为字符串型
						} else if (param instanceof String) {
							String s = (String) param;
							// 因数据库类型为字符型，但是输出需数字型，因此需要特殊判断处理
							if ("price".equals(titleCode[j])
									|| "chrate".equals(titleCode[j])) {
								int n = Integer.parseInt(s);
								number = new jxl.write.Number(j, i + 1, n);
								sheet.addCell(number);
							} else {
								label = new Label(j, i + 1, s);
								sheet.addCell(label);
							}
							// 如果数值为Double型
						} else if (param instanceof Double) {
							double d = ((Double) param).doubleValue();
							number = new jxl.write.Number(j, i + 1, d);
							sheet.addCell(number);
							// 如果数值为Float 型
						} else if (param instanceof Float) {
							float f = ((Float) param).floatValue();
							number = new jxl.write.Number(j, i + 1, f);
							sheet.addCell(number);
							// 如果数值为Long 型
						} else if (param instanceof Long) {
							long l = ((Long) param).longValue();
							number = new jxl.write.Number(j, i + 1, l);
							sheet.addCell(number);
							// 如果数值为BigDecimal 型
						} else if (param instanceof BigDecimal) {
							long b = ((BigDecimal) param).longValue();
							number = new jxl.write.Number(j, i + 1, b);
							sheet.addCell(number);
							// 如果数值为 Date 型
						} else if (param instanceof Date) {
							Date date = (Date) param;
							String newDate = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss").format(date);
							label = new Label(j, i + 1, newDate);
							sheet.addCell(label);
						}
					} else {
						label = new Label(j, i + 1, "");
						sheet.addCell(label);
					}
				}
			}
			workbook.write();
			workbook.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public static Map convertBean(Object bean)  
     throws IntrospectionException, IllegalAccessException, InvocationTargetException {  
		 Class type = bean.getClass();  
		 Map returnMap = new HashMap();  
		 BeanInfo beanInfo = Introspector.getBeanInfo(type);  
		
		 PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
		 for (int i = 0; i< propertyDescriptors.length; i++) {  
		     PropertyDescriptor descriptor = propertyDescriptors[i];  
		     String propertyName = descriptor.getName();  
		     if (!propertyName.equals("class")) {  
		         Method readMethod = descriptor.getReadMethod();  
		         Object result = readMethod.invoke(bean, new Object[0]);  
		         if (result != null) {  
		             returnMap.put(propertyName, result);  
		         } else {  
		             returnMap.put(propertyName, "");  
		         }  
		     }  
		 }  
 return returnMap;  
}  

}
