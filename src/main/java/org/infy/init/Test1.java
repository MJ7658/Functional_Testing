package org.infy.init;

import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Test1 {

	public static void main(String[] args) throws IOException {
		System.setProperty("TESSDATA_PREFIX", "D:\\IDP\\FunctionalScriptIDP\\tessdata");
		ITesseract tr = new Tesseract();
		try {
			String value = tr.doOCR(new File("D:\\IDP\\FunctionalScriptIDP\\tessdata\\cd.png"));
			System.out.println(value);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
	}

}
