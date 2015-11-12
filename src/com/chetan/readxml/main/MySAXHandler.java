/**
 * 
 */
package com.chetan.readxml.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.chetan.readxml.models.Book;

/**
 * @author MAC This is a SAXHandler i.e a listener which will listen and act on
 *         different events which occur as the XML is being read by the parser.
 *
 */
public class MySAXHandler extends DefaultHandler {

	/**
	 * This class will be the handler for the events generated while the XML is
	 * being read
	 */
	public List<Book> books = new ArrayList<Book>();
	private Stack<String> elementStack = new Stack<String>();
	private Stack<Object> objectStack = new Stack<Object>();

	public MySAXHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("start document   : ");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("end document     : ");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		this.elementStack.push(qName);

		if ("book".equals(qName)) {
			Book book = new Book();
			this.objectStack.push(book);
			this.books.add(book);
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		this.elementStack.pop();

		if ("book".equals(qName)) {
			Object object = this.objectStack.pop();
			Book book = (Book) object;

		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String value = new String(ch, start, length).trim();
		if (value.length() == 0)
			return; // ignore white space

		if ("author".equals(currentElement())) {
			Book book = (Book) this.objectStack.peek();
			book.setAuthor(value);
		} else if ("title".equals(currentElement())) {
			Book book = (Book) this.objectStack.peek();
			book.setTitle(value);
		} else if ("genre".equals(currentElement())) {
			Book book = (Book) this.objectStack.peek();
			book.setGenre(value);
		} else if ("price".equals(currentElement())) {
			Book book = (Book) this.objectStack.peek();
			book.setPrice(Double.parseDouble(value));
		} else if ("publish_date".equals(currentElement())) {
			Book book = (Book) this.objectStack.peek();
			book.setPublish_date(value);
		} else if ("description".equals(currentElement())) {
			Book book = (Book) this.objectStack.peek();
			book.setDescription(value);
		}

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {

		super.ignorableWhitespace(ch, start, length);
	}

	private String currentElement() {
		return this.elementStack.peek();
	}

}
