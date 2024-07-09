package org.infy.init;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test3 {

	static String Application;

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		String appName = excelRead(11);
		System.out.println(appName);
	}

	public static String excelRead(int value) throws IOException, GeneralSecurityException {

		String path = System.getProperty("user.dir") + "//Data//LockedExcel.xlsx";
		POIFSFileSystem fs = new POIFSFileSystem(new File(path));
		EncryptionInfo info = new EncryptionInfo(fs);
		Decryptor d = Decryptor.getInstance(info);
		d.verifyPassword("02@Taskover");
		Workbook wb = new XSSFWorkbook(d.getDataStream(fs));
		Sheet sheet1 = wb.getSheetAt(0);

		int row = sheet1.getLastRowNum() - sheet1.getFirstRowNum();

		for (int i = 1; i <= row; i++) {
			Application = sheet1.getRow(i).getCell(value).getStringCellValue();
		}
		wb.close();
		return Application;
	}
}
