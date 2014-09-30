package com.fwumdegames.io;

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

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNodeName()
	 */
	public String getNodeName()
	{
		return xmlFile.getNodeName();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNodeValue()
	 */
	public String getNodeValue() throws DOMException
	{
		return xmlFile.getNodeValue();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#setNodeValue(java.lang.String)
	 */
	public void setNodeValue(String nodeValue) throws DOMException
	{
		xmlFile.setNodeValue(nodeValue);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNodeType()
	 */
	public short getNodeType()
	{
		return xmlFile.getNodeType();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getParentNode()
	 */
	public Node getParentNode()
	{
		return xmlFile.getParentNode();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getChildNodes()
	 */
	public NodeList getChildNodes()
	{
		return xmlFile.getChildNodes();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getFirstChild()
	 */
	public Node getFirstChild()
	{
		return xmlFile.getFirstChild();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getLastChild()
	 */
	public Node getLastChild()
	{
		return xmlFile.getLastChild();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getPreviousSibling()
	 */
	public Node getPreviousSibling()
	{
		return xmlFile.getPreviousSibling();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNextSibling()
	 */
	public Node getNextSibling()
	{
		return xmlFile.getNextSibling();
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getAttributes()
	 */
	public NamedNodeMap getAttributes()
	{
		return xmlFile.getAttributes();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getOwnerDocument()
	 */
	public Document getOwnerDocument()
	{
		return xmlFile.getOwnerDocument();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#insertBefore(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	public Node insertBefore(Node newChild, Node refChild) throws DOMException
	{
		return xmlFile.insertBefore(newChild, refChild);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#replaceChild(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException
	{
		return xmlFile.replaceChild(newChild, oldChild);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#removeChild(org.w3c.dom.Node)
	 */
	public Node removeChild(Node oldChild) throws DOMException
	{
		return xmlFile.removeChild(oldChild);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#appendChild(org.w3c.dom.Node)
	 */
	public Node appendChild(Node newChild) throws DOMException
	{
		return xmlFile.appendChild(newChild);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#hasChildNodes()
	 */
	public boolean hasChildNodes()
	{
		return xmlFile.hasChildNodes();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#cloneNode(boolean)
	 */
	public Node cloneNode(boolean deep)
	{
		return xmlFile.cloneNode(deep);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#normalize()
	 */
	public void normalize()
	{
		xmlFile.normalize();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#isSupported(java.lang.String, java.lang.String)
	 */
	public boolean isSupported(String feature, String version)
	{
		return xmlFile.isSupported(feature, version);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNamespaceURI()
	 */
	public String getNamespaceURI()
	{
		return xmlFile.getNamespaceURI();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getPrefix()
	 */
	public String getPrefix()
	{
		return xmlFile.getPrefix();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#setPrefix(java.lang.String)
	 */
	public void setPrefix(String prefix) throws DOMException
	{
		xmlFile.setPrefix(prefix);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getLocalName()
	 */
	public String getLocalName()
	{
		return xmlFile.getLocalName();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#hasAttributes()
	 */
	public boolean hasAttributes()
	{
		return xmlFile.hasAttributes();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getBaseURI()
	 */
	public String getBaseURI()
	{
		return xmlFile.getBaseURI();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#compareDocumentPosition(org.w3c.dom.Node)
	 */
	public short compareDocumentPosition(Node other) throws DOMException
	{
		return xmlFile.compareDocumentPosition(other);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getTextContent()
	 */
	public String getTextContent() throws DOMException
	{
		return xmlFile.getTextContent();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#setTextContent(java.lang.String)
	 */
	public void setTextContent(String textContent) throws DOMException
	{
		xmlFile.setTextContent(textContent);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#isSameNode(org.w3c.dom.Node)
	 */
	public boolean isSameNode(Node other)
	{
		return xmlFile.isSameNode(other);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#lookupPrefix(java.lang.String)
	 */
	public String lookupPrefix(String namespaceURI)
	{
		return xmlFile.lookupPrefix(namespaceURI);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#isDefaultNamespace(java.lang.String)
	 */
	public boolean isDefaultNamespace(String namespaceURI)
	{
		return xmlFile.isDefaultNamespace(namespaceURI);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#lookupNamespaceURI(java.lang.String)
	 */
	public String lookupNamespaceURI(String prefix)
	{
		return xmlFile.lookupNamespaceURI(prefix);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#isEqualNode(org.w3c.dom.Node)
	 */
	public boolean isEqualNode(Node arg)
	{
		return xmlFile.isEqualNode(arg);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getFeature(java.lang.String, java.lang.String)
	 */
	public Object getFeature(String feature, String version)
	{
		return xmlFile.getFeature(feature, version);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#setUserData(java.lang.String, java.lang.Object, org.w3c.dom.UserDataHandler)
	 */
	public Object setUserData(String key, Object data, UserDataHandler handler)
	{
		return xmlFile.setUserData(key, data, handler);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getUserData(java.lang.String)
	 */
	public Object getUserData(String key)
	{
		return xmlFile.getUserData(key);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getDoctype()
	 */
	public DocumentType getDoctype()
	{
		return xmlFile.getDoctype();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getImplementation()
	 */
	public DOMImplementation getImplementation()
	{
		return xmlFile.getImplementation();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getDocumentElement()
	 */
	public Element getDocumentElement()
	{
		return xmlFile.getDocumentElement();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createElement(java.lang.String)
	 */
	public Element createElement(String tagName) throws DOMException
	{
		return xmlFile.createElement(tagName);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createDocumentFragment()
	 */
	public DocumentFragment createDocumentFragment()
	{
		return xmlFile.createDocumentFragment();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createTextNode(java.lang.String)
	 */
	public Text createTextNode(String data)
	{
		return xmlFile.createTextNode(data);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createComment(java.lang.String)
	 */
	public Comment createComment(String data)
	{
		return xmlFile.createComment(data);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createCDATASection(java.lang.String)
	 */
	public CDATASection createCDATASection(String data) throws DOMException
	{
		return xmlFile.createCDATASection(data);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createProcessingInstruction(java.lang.String, java.lang.String)
	 */
	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException
	{
		return xmlFile.createProcessingInstruction(target, data);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createAttribute(java.lang.String)
	 */
	public Attr createAttribute(String name) throws DOMException
	{
		return xmlFile.createAttribute(name);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createEntityReference(java.lang.String)
	 */
	public EntityReference createEntityReference(String name) throws DOMException
	{
		return xmlFile.createEntityReference(name);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getElementsByTagName(java.lang.String)
	 */
	public NodeList getElementsByTagName(String tagname)
	{
		return xmlFile.getElementsByTagName(tagname);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#importNode(org.w3c.dom.Node, boolean)
	 */
	public Node importNode(Node importedNode, boolean deep) throws DOMException
	{
		return xmlFile.importNode(importedNode, deep);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createElementNS(java.lang.String, java.lang.String)
	 */
	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException
	{
		return xmlFile.createElementNS(namespaceURI, qualifiedName);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createAttributeNS(java.lang.String, java.lang.String)
	 */
	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException
	{
		return xmlFile.createAttributeNS(namespaceURI, qualifiedName);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getElementsByTagNameNS(java.lang.String, java.lang.String)
	 */
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
	{
		return xmlFile.getElementsByTagNameNS(namespaceURI, localName);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getElementById(java.lang.String)
	 */
	public Element getElementById(String elementId)
	{
		return xmlFile.getElementById(elementId);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getInputEncoding()
	 */
	public String getInputEncoding()
	{
		return xmlFile.getInputEncoding();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getXmlEncoding()
	 */
	public String getXmlEncoding()
	{
		return xmlFile.getXmlEncoding();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getXmlStandalone()
	 */
	public boolean getXmlStandalone()
	{
		return xmlFile.getXmlStandalone();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#setXmlStandalone(boolean)
	 */
	public void setXmlStandalone(boolean xmlStandalone) throws DOMException
	{
		xmlFile.setXmlStandalone(xmlStandalone);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getXmlVersion()
	 */
	public String getXmlVersion()
	{
		return xmlFile.getXmlVersion();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#setXmlVersion(java.lang.String)
	 */
	public void setXmlVersion(String xmlVersion) throws DOMException
	{
		xmlFile.setXmlVersion(xmlVersion);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getStrictErrorChecking()
	 */
	public boolean getStrictErrorChecking()
	{
		return xmlFile.getStrictErrorChecking();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#setStrictErrorChecking(boolean)
	 */
	public void setStrictErrorChecking(boolean strictErrorChecking)
	{
		xmlFile.setStrictErrorChecking(strictErrorChecking);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getDocumentURI()
	 */
	public String getDocumentURI()
	{
		return xmlFile.getDocumentURI();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#setDocumentURI(java.lang.String)
	 */
	public void setDocumentURI(String documentURI)
	{
		xmlFile.setDocumentURI(documentURI);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#adoptNode(org.w3c.dom.Node)
	 */
	public Node adoptNode(Node source) throws DOMException
	{
		return xmlFile.adoptNode(source);
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getDomConfig()
	 */
	public DOMConfiguration getDomConfig()
	{
		return xmlFile.getDomConfig();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#normalizeDocument()
	 */
	public void normalizeDocument()
	{
		xmlFile.normalizeDocument();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#renameNode(org.w3c.dom.Node, java.lang.String, java.lang.String)
	 */
	public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException
	{
		return xmlFile.renameNode(n, namespaceURI, qualifiedName);
	}
}
