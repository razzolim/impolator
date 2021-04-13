package com.cloud.impolator.api.v1.extract;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.web.multipart.MultipartFile;

public class UtilsExtract {
	
	
	public static String getTextFromCoordinate(MultipartFile file,int x,int y,int width,int height) throws IOException {
		String result = "";

	 File fileConvert = convert(file);
		try (PDDocument document = PDDocument.load(fileConvert)) {

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				// Rectangle rect = new Rectangle(260, 35, 70, 10);
				Rectangle rect = new Rectangle(x,y,width,height);
				stripper.addRegion("class1", rect);
				PDPage firstPage = document.getPage(0);
				stripper.extractRegions( firstPage );
				// System.out.println("Text in the area:" + rect);
				result = stripper.getTextForRegion("class1");

			}
		} catch (IOException e){
			System.err.println("Exception while trying to read pdf document - " + e);
		}
		return result.trim();
	}
	
	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
