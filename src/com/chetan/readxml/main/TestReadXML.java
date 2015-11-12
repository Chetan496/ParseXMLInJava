package com.chetan.readxml.main;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TestReadXML {

	public TestReadXML() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			MyXMLReader.parseXMLFile("/home/MAC/Documents/books.xml");

		} catch (ParserConfigurationException e) {
			System.out.println("Error while configuring the Parser");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Error while Parsing");
			e.printStackTrace();
		}

	}

}
