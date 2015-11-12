package com.chetan.readxml.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.chetan.readxml.models.Book;

public class MyXMLReader {

	public MyXMLReader() {

	}

	public static void parseXMLFile(String xmlFilePath)
			throws ParserConfigurationException, SAXException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		File file = null;
		MySAXHandler mySAXHandler = new MySAXHandler();
		try {
			file = getFileFromAbsoluteXMLPath(xmlFilePath);
			saxParser.parse(file, mySAXHandler);
			for (Book book : mySAXHandler.books) {
				System.out.println(book);
			}
		} catch (IOException e) {
			System.out
					.println("IO Error occured: There was an error while accessing the file");
			e.printStackTrace();
		}

	}

	/* assuming that an absolute path to the File is specified */
	private static File getFileFromAbsoluteXMLPath(String xmlFilePath) {
		File file = new File(xmlFilePath);
		return file;
	}
}
