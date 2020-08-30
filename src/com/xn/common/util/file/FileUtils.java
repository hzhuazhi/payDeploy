package com.xn.common.util.file;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	public static Logger log = Logger.getLogger(FileUtils.class);

	public static String getXmlContent(File xmlFile) {
		try {
			Document document = new SAXReader().read(xmlFile);
			return document.asXML();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getFileContent(String fileName) {

		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			File f = new File(fileName);

			reader = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
				fileContent.append("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();
	}

	public static String getFileContent(InputStream is) {

		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
				fileContent.append("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();

	}

	public static boolean setFileContent(String path, String content) {
		boolean flag = false;
		DataOutputStream dos = null;
		try {
			if (content != null && content.length() >= 0) {
				byte abyte[] = content.getBytes();
				dos = new DataOutputStream(new FileOutputStream(path));
				dos.write(abyte, 0, abyte.length);
				dos.flush();

				flag = true;
			}
		} catch (FileNotFoundException e) {
			log.error("fnfe:" + e);
		} catch (IOException e) {
			log.error("ioe:" + e);
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dos = null;
			}
		}
		return flag;
	}

	public static String getFileExt(String fileName) {
		if (fileName == null)
			return "";

		String ext = "";
		int lastIndex = fileName.lastIndexOf(".");
		if (lastIndex >= 0) {
			ext = fileName.substring(lastIndex + 1).toLowerCase();
		}

		return ext;
	}

	/**
	 * 得到不包含后缀的文件名字。
	 * 描述
	 * @return
	 */
	public static String getRealName(String name) {
		if (name == null) {
			return "";
		}

		int endIndex = name.lastIndexOf(".");
		if (endIndex == -1) {
			return null;
		}
		return name.substring(0, endIndex);
	}

	public static String upload2File(MultipartFile file, String uploadPath) {
		// 检查扩展名
		String fileName = file.getOriginalFilename();
		String fileExt = FileUtils.getFileExt(fileName);
		//文件名
		String newFileName = System.currentTimeMillis() + "." + fileExt;

		File uploadedFile = new File(uploadPath, newFileName);
		try {
			org.apache.commons.io.FileUtils.writeByteArrayToFile(uploadedFile, file.getBytes());
		} catch (Exception e) {
			if (uploadedFile.exists()) {
				uploadedFile.delete();
			}
			return "";
		}

		return newFileName;
	}

	/**
	 * 删除单个文件
	 * @param   sPath    被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 创建文件目录
	 * @param filePath
	 * @return
	 */
	public static boolean isExist(String filePath) {
		if (filePath != null && !"".equals(filePath)) {
			filePath = filePath.replace("\\", "/");
		}
		String dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
		File f = new File(dirPath);
		// 创建文件夹
		if (!f.exists()) {
			f.mkdirs();
		}
		File fp = new File(filePath);
		if (!fp.exists()) {
			return true; // 文件不存在，执行下载功能
		} else {
			return false; // 文件存在不做处理
		}
	}

}
