package com.example.cbtapp.activityLog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XMLParser {

    public XMLParser() throws ParserConfigurationException, IOException, SAXException {
    }

    /**
     * Get the value in a tag attribute pair in some element.
     *
     * @param element current xml element
     * @param tag to sort by
     * @param attribute to get value from
     * @return the value stored at the tag attribute pair of the given element
     */
    public static String getTagAttributeElement(Element element, String tag, String attribute) {
        return element.getElementsByTagName(tag).item(0).getAttributes().getNamedItem(attribute)
                .getNodeValue();
    }


    //Import an XML file from a fileName
    public static Document importXmlFile (File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        return document;
    }

    // Export a Document object to an XML file
    public static void exportXmlFile (Document document, File file) throws TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

    // Method to get a NodeList of elements with a specified tag name from a parent element
    public static NodeList getChildElementsByTagName (Element parent, String tagName){
        return parent.getElementsByTagName(tagName);
    }

    public static HashMap<String, Object> convertXmlToVariables (Document document){
        Element rootElement = document.getDocumentElement();
        HashMap<String, Object> variables = new HashMap<>();
        NodeList childNodes = rootElement.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element childElement = (Element) childNodes.item(i);
                String varName = childElement.getNodeName();
                String varValue = childElement.getTextContent();
                variables.put(varName, varValue);

                NodeList attributeDetails = childNodes.item(i).getChildNodes();
                for (int j = 0; j < attributeDetails.getLength(); j++) {
                    if (attributeDetails.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        Element attributeElement = (Element) attributeDetails;
                        System.out.println("  " + attributeElement.getTagName() + ":" +
                                attributeElement.getAttribute(""));
                    }
                }
            }

        }
        return variables;
    }
}
