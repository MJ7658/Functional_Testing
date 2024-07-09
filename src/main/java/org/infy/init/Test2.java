package org.infy.init;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test2 {

	public static void main(String[] args) throws Exception {
		// Create a new Excel workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		// Encrypt the cell value
		String key = "Sathesh"; // replace with your actual secret key
		String valueToEncrypt = "value"; // replace with your actual value
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(valueToEncrypt.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encrypted);

		// Write the encrypted value to the first cell
		Row row = sheet.createRow(0); // create the first row
		Cell cell = row.createCell(0); // create the first cell
		cell.setCellValue(encryptedValue);

		// Save the Excel workbook
		String path = System.getProperty("user.dir") + "//Data//LockedExcelNew.xlsx";
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}
}
