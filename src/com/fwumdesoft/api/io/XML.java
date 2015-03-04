package com.fwumdesoft.api.io;

import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * Simple way to create and use XML files
 * @author Jason Carrete
 * @since Aug 7, 2014
 */
public class XML implements Document
{	
	private Document xmlFile;
	
	/**
	 * Instantiate an object that contains an XML document using the specified path
	 * @param xmlPath the path to the XML file that should be read
	 * @param setValidating if set to true, the parser will validate the XML using the specified DTD or schema
	 */
	public XML(String xmlPath, boolean setValidating) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(setValidating);
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(getErrorHandler());
		xmlFile = builder.parse(new InputSource(xmlPath));
	}
	
	/**
	 * Instantiate an object that contains an XML document using the specified path
	 * @param xmlPath the path to the XML file that should be read
	 */
	public XML(String xmlPath) throws ParserConfigurationException, SAXException, IOException
	{
		this(xmlPath, true);
	}
	
	private ErrorHandler getErrorHandler()
	{
		return new ErrorHandler()
		{
			public void warning(SAXParseException exception) throws SAXException
			{
				exception.printStackTrace();
				throw new SAXException();
			}

			public void error(SAXParseException exception) throws SAXException
			{
				exception.printStackTrace();
				throw new SAXException();
			}

			public void fatalError(SAXParseException exception) throws SAXException
			{
				exception.printStackTrace();
				throw new SAXException();
			}
		};
	}

	public String getNodeName()
	{
		return xmlFile.getNodeName();
	}

	public String getNodeValue() throws DOMException
	{
		return xmlFile.getNodeValue();
	}

	public void setNodeValue(String nodeValue) throws DOMException
	{
		xmlFile.setNodeValue(nodeValue);
	}

	public short getNodeType()
	{
		return xmlFile.getNodeType();
	}

	public Node getParentNode()
	{
		return xmlFile.getParentNode();
	}

	public NodeList getChildNodes()
	{
		return xmlFile.getChildNodes();
	}

	public Node getFirstChild()
	{
		return xmlFile.getFirstChild();
	}

	public Node getLastChild()
	{
		return xmlFile.getLastChild();
	}

	public Node getPreviousSibling()
	{
		return xmlFile.getPreviousSibling();
	}

	public Node getNextSibling()
	{
		return xmlFile.getNextSibling();
		
	}

	public NamedNodeMap getAttributes()
	{
		return xmlFile.getAttributes();
	}

	public Document getOwnerDocument()
	{
		return xmlFile.getOwnerDocument();
	}

	public Node insertBefore(Node newChild, Node refChild) throws DOMException
	{
		return xmlFile.insertBefore(newChild, refChild);
	}

	public Node replaceChild(Node newChild, Node oldChild) throws DOMException
	{
		return xmlFile.replaceChild(newChild, oldChild);
	}

	public Node removeChild(Node oldChild) throws DOMException
	{
		return xmlFile.removeChild(oldChild);
	}

	public Node appendChild(Node newChild) throws DOMException
	{
		return xmlFile.appendChild(newChild);
	}

	public boolean hasChildNodes()
	{
		return xmlFile.hasChildNodes();
	}

	public Node cloneNode(boolean deep)
	{
		return xmlFile.cloneNode(deep);
	}

	public void normalize()
	{
		xmlFile.normalize();
	}
	
	public boolean isSupported(String feature, String version)
	{
		return xmlFile.isSupported(feature, version);
	}

	public String getNamespaceURI()
	{
		return xmlFile.getNamespaceURI();
	}

	public String getPrefix()
	{
		return xmlFile.getPrefix();
	}

	public void setPrefix(String prefix) throws DOMException
	{
		xmlFile.setPrefix(prefix);
	}

	public String getLocalName()
	{
		return xmlFile.getLocalName();
	}

	public boolean hasAttributes()
	{
		return xmlFile.hasAttributes();
	}

	public String getBaseURI()
	{
		return xmlFile.getBaseURI();
	}

	public short compareDocumentPosition(Node other) throws DOMException
	{
		return xmlFile.compareDocumentPosition(other);
	}

	public String getTextContent() throws DOMException
	{
		return xmlFile.getTextContent();
	}

	public void setTextContent(String textContent) throws DOMException
	{
		xmlFile.setTextContent(textContent);
	}

	public boolean isSameNode(Node other)
	{
		return xmlFile.isSameNode(other);
	}

	public String lookupPrefix(String namespaceURI)
	{
		return xmlFile.lookupPrefix(namespaceURI);
	}

	public boolean isDefaultNamespace(String namespaceURI)
	{
		return xmlFile.isDefaultNamespace(namespaceURI);
	}

	public String lookupNamespaceURI(String prefix)
	{
		return xmlFile.lookupNamespaceURI(prefix);
	}

	public boolean isEqualNode(Node arg)
	{
		return xmlFile.isEqualNode(arg);
	}

	public Object getFeature(String feature, String version)
	{
		return xmlFile.getFeature(feature, version);
	}

	public Object setUserData(String key, Object data, UserDataHandler handler)
	{
		return xmlFile.setUserData(key, data, handler);
	}

	public Object getUserData(String key)
	{
		return xmlFile.getUserData(key);
	}

	public DocumentType getDoctype()
	{
		return xmlFile.getDoctype();
	}

	public DOMImplementation getImplementation()
	{
		return xmlFile.getImplementation();
	}

	public Element getDocumentElement()
	{
		return xmlFile.getDocumentElement();
	}

	public Element createElement(String tagName) throws DOMException
	{
		return xmlFile.createElement(tagName);
	}

	public DocumentFragment createDocumentFragment()
	{
		return xmlFile.createDocumentFragment();
	}

	public Text createTextNode(String data)
	{
		return xmlFile.createTextNode(data);
	}

	public Comment createComment(String data)
	{
		return xmlFile.createComment(data);
	}

	public CDATASection createCDATASection(String data) throws DOMException
	{
		return xmlFile.createCDATASection(data);
	}

	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException
	{
		return xmlFile.createProcessingInstruction(target, data);
	}

	public Attr createAttribute(String name) throws DOMException
	{
		return xmlFile.createAttribute(name);
	}

	public EntityReference createEntityReference(String name) throws DOMException
	{
		return xmlFile.createEntityReference(name);
	}

	public NodeList getElementsByTagName(String tagname)
	{
		return xmlFile.getElementsByTagName(tagname);
	}

	public Node importNode(Node importedNode, boolean deep) throws DOMException
	{
		return xmlFile.importNode(importedNode, deep);
	}

	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException
	{
		return xmlFile.createElementNS(namespaceURI, qualifiedName);
	}

	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException
	{
		return xmlFile.createAttributeNS(namespaceURI, qualifiedName);
	}

	public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
	{
		return xmlFile.getElementsByTagNameNS(namespaceURI, localName);
	}

	public Element getElementById(String elementId)
	{
		return xmlFile.getElementById(elementId);
	}

	public String getInputEncoding()
	{
		return xmlFile.getInputEncoding();
	}

	public String getXmlEncoding()
	{
		return xmlFile.getXmlEncoding();
	}

	public boolean getXmlStandalone()
	{
		return xmlFile.getXmlStandalone();
	}

	public void setXmlStandalone(boolean xmlStandalone) throws DOMException
	{
		xmlFile.setXmlStandalone(xmlStandalone);
	}

	public String getXmlVersion()
	{
		return xmlFile.getXmlVersion();
	}

	public void setXmlVersion(String xmlVersion) throws DOMException
	{
		xmlFile.setXmlVersion(xmlVersion);
	}

	public boolean getStrictErrorChecking()
	{
		return xmlFile.getStrictErrorChecking();
	}

	public void setStrictErrorChecking(boolean strictErrorChecking)
	{
		xmlFile.setStrictErrorChecking(strictErrorChecking);
	}

	public String getDocumentURI()
	{
		return xmlFile.getDocumentURI();
	}

	public void setDocumentURI(String documentURI)
	{
		xmlFile.setDocumentURI(documentURI);
	}

	public Node adoptNode(Node source) throws DOMException
	{
		return xmlFile.adoptNode(source);
	}

	public DOMConfiguration getDomConfig()
	{
		return xmlFile.getDomConfig();
	}

	public void normalizeDocument()
	{
		xmlFile.normalizeDocument();
	}

	public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException
	{
		return xmlFile.renameNode(n, namespaceURI, qualifiedName);
	}
}
