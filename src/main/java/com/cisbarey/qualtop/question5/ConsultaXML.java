package com.cisbarey.qualtop.question5;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cisbarey.qualtop.ExamenException;

public class ConsultaXML {

	public static void main(String[] args) throws ExamenException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		try {
			builder = factory.newDocumentBuilder();
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("employees.xml");
			doc = builder.parse(is);
			// Create XPathFactory object
			XPathFactory xpathFactory = XPathFactory.newInstance();
			// Create XPath object
			XPath xpath = xpathFactory.newXPath();
			String name = getEmployeeNameById(doc, xpath, 4);
			System.out.println("Employee Name with ID 4: " + name);
			List<String> names = getEmployeeNameWithAge(doc, xpath, 30);
			System.out.println("Employees with 'age>30' are:" + Arrays.toString(names.toArray()));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new ExamenException(e.getMessage());
		}
	}

	private static List<String> getEmployeeNameWithAge(Document doc, XPath xpath, int age) throws ExamenException {
		List<String> list = new ArrayList<>();
		try {
			XPathExpression expr = xpath.compile("/Employees/Employee[age>" + age + "]/name/text()");
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++)
				list.add(nodes.item(i).getNodeValue());
		} catch (XPathExpressionException e) {
			throw new ExamenException(e.getMessage());
		}
		return list;
	}

	private static String getEmployeeNameById(Document doc, XPath xpath, int id) throws ExamenException {
		String name = null;
		try {
			XPathExpression expr = xpath.compile("/Employees/Employee[@id='" + id + "']/name/text()");
			name = (String) expr.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			throw new ExamenException(e.getMessage());
		}
		return name;
	}

}
