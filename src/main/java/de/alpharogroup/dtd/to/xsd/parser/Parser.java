/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.dtd.to.xsd.parser;

import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.xerces.dom.CoreDOMImplementationImpl;
import org.apache.xerces.parsers.XMLDocumentParser;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.dtd.to.xsd.type.TypePattern;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link Parser} is for parsing dtd document to a xsd document.
 *
 * @author Asterios Raptis
 */
@Slf4j
public class Parser extends XMLDocumentParser
	implements
		XMLDTDHandler,
		XMLDTDContentModelHandler,
		XMLErrorHandler
{

	/** The Constant XSD_SCHEMA. */
	private static final String XSD_SCHEMA = "xsd:schema";

	/** The Constant HTTP_WWW_W3_ORG_2001_XML_SCHEMA. */
	private static final String HTTP_WWW_W3_ORG_2001_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	/** The Constant FORMAT_PRETTY_PRINT. */
	private static final String FORMAT_PRETTY_PRINT = "format-pretty-print";

	/** The Constant UTF_8. */
	private static final String UTF_8 = "UTF-8";

	/** The Constant ATTRIBUTE_PUBLIC. */
	private static final String ATTRIBUTE_PUBLIC = "public";

	/** The Constant XSD_NOTATION_LOWERCASE. */
	private static final String XSD_NOTATION_LOWERCASE = "xsd:notation";

	/** The Constant PCDATA. */
	private static final String PCDATA = "PCDATA";

	/** The Constant XSD_CHOICE. */
	private static final String XSD_CHOICE = "xsd:choice";

	/** The Constant XSD_SEQUENCE. */
	private static final String XSD_SEQUENCE = "xsd:sequence";

	/** The Constant ATTRIBUTE_DEFAULT. */
	private static final String ATTRIBUTE_DEFAULT = "default";

	/** The Constant OPTIONAL. */
	private static final String OPTIONAL = "optional";

	/** The Constant ATTRIBUTE_FIXED. */
	private static final String ATTRIBUTE_FIXED = "fixed";

	/** The Constant FIXED_TYPE. */
	private static final String FIXED_TYPE = "#FIXED";

	/** The Constant REQUIRED. */
	private static final String REQUIRED = "required";

	/** The Constant USE. */
	private static final String USE = "use";

	/** The Constant REQUIRED_TYPE. */
	private static final String REQUIRED_TYPE = "#REQUIRED";

	/** The Constant XSD_SIMPLE_TYPE. */
	private static final String XSD_SIMPLE_TYPE = "xsd:simpleType";

	/** The Constant ATTRIBUTE_VALUE. */
	private static final String ATTRIBUTE_VALUE = "value";

	/** The Constant XSD_ENUMERATION. */
	private static final String XSD_ENUMERATION = "xsd:enumeration";

	/** The Constant BASE. */
	private static final String BASE = "base";

	/** The Constant XSD_RESTRICTION. */
	private static final String XSD_RESTRICTION = "xsd:restriction";

	/** The Constant ENUMERATION_TYPE. */
	private static final String ENUMERATION_TYPE = "ENUMERATION";

	/** The Constant NAME. */
	private static final String NAME = "name";

	/** The Constant XSD_ATTRIBUTE. */
	private static final String XSD_ATTRIBUTE = "xsd:attribute";

	/** The Constant MAX_OCCURS. */
	private static final String MAX_OCCURS = "maxOccurs";

	/** The Constant MIN_OCCURS. */
	private static final String MIN_OCCURS = "minOccurs";

	/** The Constant ATTRIBUTE_REF. */
	private static final String ATTRIBUTE_REF = "ref";

	/** The Constant XSD_ELEMENT. */
	private static final String XSD_ELEMENT = "xsd:element";

	/** The Constant MIXED. */
	private static final String MIXED = "mixed";

	/** The Constant ATTRIBUTE_TYPE. */
	private static final String ATTRIBUTE_TYPE = "type";

	/** The Constant XSD_COMPLEX_TYPE. */
	private static final String XSD_COMPLEX_TYPE = "xsd:complexType";

	/** The Constant XSD_NOTATION. */
	private static final String XSD_NOTATION = "xsd:NOTATION";

	/** The Constant XSD_NMTOKENS. */
	private static final String XSD_NMTOKENS = "xsd:NMTOKENS";

	/** The Constant XSD_NMTOKEN. */
	private static final String XSD_NMTOKEN = "xsd:NMTOKEN";

	/** The Constant XSD_ENTITIES. */
	private static final String XSD_ENTITIES = "xsd:ENTITIES";

	/** The Constant XSD_ENTITY. */
	private static final String XSD_ENTITY = "xsd:ENTITY";

	/** The Constant XSD_IDREFS. */
	private static final String XSD_IDREFS = "xsd:IDREFS";

	/** The Constant XSD_STRING. */
	private static final String XSD_STRING = "xsd:string";

	/** The Constant XSD_IDREF. */
	private static final String XSD_IDREF = "xsd:IDREF";

	/** The Constant XSD_ID. */
	private static final String XSD_ID = "xsd:ID";

	/** This Constant is for the dtd name NOTATION. */
	private static final String DTD_NOTATION = "NOTATION";

	/** This Constant is for the dtd name ENTITIES. */
	private static final String DTD_ENTITIES = "ENTITIES";

	/** This Constant is for the dtd name ENTITY. */
	private static final String DTD_ENTITY = "ENTITY";

	/** This Constant is for the dtd name NMTOKENS. */
	private static final String DTD_NMTOKENS = "NMTOKENS";

	/** This Constant is for the dtd name NMTOKEN. */
	private static final String DTD_NMTOKEN = "NMTOKEN";

	/** This Constant is for the dtd name IDREFS. */
	private static final String DTD_IDREFS = "IDREFS";

	/** This Constant is for the dtd name IDREF. */
	private static final String DTD_IDREF = "IDREF";

	/** This Constant is for the dtd name ID. */
	private static final String DTD_ID = "ID";

	/** This Constant is for the dtd name CDATA. */
	private static final String DTD_CDATA = "CDATA";

	/** The type. */
	private String type = "";

	/** The dom impl. */
	private CoreDOMImplementationImpl domImpl;

	/** The doc. */
	private Document doc;

	/** The root. */
	private Element root;

	/** The stack elements. */
	private final Stack<Element> stackElements;

	/** The target namespace. */
	private String targetNamespace;

	/** The set xml resource identifier. */
	private final Set<String> setXmlResourceIdentifier;

	/** The has parsed. */
	private boolean hasParsed = false;

	/** The parsed stack. */
	private final Stack<Boolean> parsedStack;

	/** The group depth. */
	private int groupDepth = 0;

	/** The list group types. */
	private final List<Short> listGroupTypes;

	/** The list group elements. */
	private final List<List<Element>> listGroupElements;

	/** The all elements. */
	private final Map<String, Element> allElements;

	/** The data type map. */
	private final Map<String, String> dataTypeMap;

	/** The list xsd type pattern. */
	private final List<TypePattern> listXsdTypePattern;

	/**
	 * Instantiates a new dtd to xsd parser.
	 *
	 * @param configuration
	 *            the configuration
	 */
	public Parser(final XMLParserConfiguration configuration)
	{
		super(configuration);
		configuration.setErrorHandler(this);
		this.dataTypeMap = MapFactory.newLinkedHashMap();
		this.dataTypeMap.put(DTD_ID, XSD_ID);
		this.dataTypeMap.put(DTD_IDREF, XSD_IDREF);
		this.dataTypeMap.put(DTD_CDATA, XSD_STRING);
		this.dataTypeMap.put(DTD_IDREFS, XSD_IDREFS);
		this.dataTypeMap.put(DTD_ENTITY, XSD_ENTITY);
		this.dataTypeMap.put(DTD_ENTITIES, XSD_ENTITIES);
		this.dataTypeMap.put(DTD_NMTOKEN, XSD_NMTOKEN);
		this.dataTypeMap.put(DTD_NMTOKENS, XSD_NMTOKENS);
		this.dataTypeMap.put(DTD_NOTATION, XSD_NOTATION);
		this.allElements = MapFactory.newLinkedHashMap();
		this.setXmlResourceIdentifier = SetFactory.newLinkedHashSet();
		this.listGroupTypes = ListFactory.newArrayList();
		this.listGroupElements = ListFactory.newArrayList();
		this.listXsdTypePattern = ListFactory.newArrayList();
		this.parsedStack = new Stack<>();
		this.stackElements = new Stack<>();
	}

	/**
	 * Adds the xsd type pattern.
	 *
	 * @param list
	 *            the list
	 */
	public void addXsdTypePattern(final List<TypePattern> list)
	{
		this.listXsdTypePattern.addAll(list);
	}

	/**
	 * Adds the xsd type pattern.
	 *
	 * @param xsdTypePattern
	 *            the xsd type pattern
	 */
	public void addXsdTypePattern(final TypePattern xsdTypePattern)
	{
		this.listXsdTypePattern.add(xsdTypePattern);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void attributeDecl(final String ename, final String aname, final String atype,
		final String[] enums, final String dtype, final XMLString dvalue, final XMLString nondvalue,
		final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Element attr = newAttribute(ename, aname, atype, enums, dtype, dvalue, nondvalue,
			augs);

		final Element element = this.allElements.get(ename);
		if (element != null)
		{
			if (element.getChildNodes().getLength() > 0)
			{
				final Element child = (Element)element.getChildNodes().item(0);
				child.appendChild(attr);
			}
			else
			{
				final Element complexType = this.doc.createElement(XSD_COMPLEX_TYPE);
				element.appendChild(complexType);
				complexType.appendChild(attr);

				if (XSD_STRING.equals(element.getAttribute(ATTRIBUTE_TYPE)))
				{
					complexType.setAttribute(MIXED, "true");
				}

				element.removeAttribute(ATTRIBUTE_TYPE);
			}
		}
	}

	/**
	 * Combine sequence list.
	 *
	 * @param listElement
	 *            the list element
	 * @return the list
	 */
	private List<Element> combineSequenceList(final List<Element> listElement)
	{
		boolean needCombin = false;
		int i = 0;
		final Map<String, List<Element>> mapRefList = new LinkedHashMap<>();
		for (final Element element : listElement)
		{
			if (XSD_ELEMENT.equals(element.getTagName()))
			{
				final String ref = element.getAttribute(ATTRIBUTE_REF);
				List<Element> reflist = mapRefList.get(ref);
				if (reflist == null)
				{
					reflist = new ArrayList<>(1);
					reflist.add(element);
					mapRefList.put(ref, reflist);
				}
				else
				{
					reflist.add(element);
					needCombin = true;
				}
			}
			else
			{
				final List<Element> reflist = new ArrayList<>(1);
				reflist.add(element);
				mapRefList.put(element.getTagName() + i, reflist);
				++i;
			}
		}

		if (!needCombin)
		{
			return listElement;
		}

		final List<Element> returnList = new ArrayList<>();
		for (final List<Element> refList : mapRefList.values())
		{
			if (refList.size() == 1)
			{
				returnList.add(refList.get(0));
			}
			else
			{
				final Element last = refList.get(refList.size() - 1);
				int minOccurs = 1;
				try
				{
					minOccurs = Integer.parseInt(last.getAttribute(MIN_OCCURS));
					minOccurs += refList.size() - 1;
					last.setAttribute(MIN_OCCURS, Integer.toString(minOccurs));
				}
				catch (final NumberFormatException ne)
				{
					minOccurs += refList.size() - 1;
					last.setAttribute(MIN_OCCURS, Integer.toString(minOccurs));
					last.setAttribute(MAX_OCCURS, Integer.toString(minOccurs));
				}
				returnList.add(last);
			}
		}

		return returnList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void comment(final XMLString text, final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Comment comment = this.doc.createComment(text.toString());
		this.stackElements.peek().appendChild(comment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void element(final String name, final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Element element = this.doc.createElement(XSD_ELEMENT);
		element.setAttribute(ATTRIBUTE_REF, this.type + name);

		if (this.groupDepth > 0)
		{
			List<Element> listElement = null;
			if (this.listGroupElements.size() < this.groupDepth)
			{
				listElement = new ArrayList<>();
				this.listGroupElements.add(listElement);
			}
			else
			{
				listElement = this.listGroupElements.get(this.groupDepth - 1);
			}
			listElement.add(element);
		}
		this.stackElements.add(element);
		this.allElements.put(name, element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endContentModel(final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Element element = this.stackElements.pop();
		this.stackElements.peek().appendChild(element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDTD(final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		if (log.isDebugEnabled())
		{
			printDomNode(this.root);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endGroup(final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		this.groupDepth -= 1;

		final short type = this.listGroupTypes.remove(this.groupDepth);
		final List<Element> listElement = this.listGroupElements.remove(this.groupDepth);
		this.stackElements.removeAll(listElement);

		if (listElement.size() == 1 && listElement.get(0).getTagName().equals(PCDATA))
		{
			this.stackElements.peek().setAttribute(ATTRIBUTE_TYPE, XSD_STRING);
		}
		else
		{
			final Element seq_cho = newSequenceOrChoice(type, listElement);
			if (this.groupDepth == 0)
			{
				final Element complexType = this.doc.createElement(XSD_COMPLEX_TYPE);
				complexType.appendChild(seq_cho);
				this.stackElements.peek().appendChild(complexType);
			}
			else
			{
				this.listGroupElements.get(this.groupDepth - 1).add(seq_cho);
				this.stackElements.add(seq_cho);
			}
		}
		if (log.isDebugEnabled())
		{
			printDomNode(this.stackElements.peek());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endParameterEntity(final String name, final Augmentations augs) throws XNIException
	{
		if (!this.parsedStack.isEmpty())
		{
			this.hasParsed = this.parsedStack.pop();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(final String domain, final String key, final XMLParseException exception)
		throws XNIException
	{
		throw new XNIException("domain=" + domain + ", key=" + key + ", exception=" + exception,
			exception);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fatalError(final String domain, final String key, final XMLParseException exception)
		throws XNIException
	{
		throw new XNIException("domain=" + domain + ", key=" + key + ", exception=" + exception,
			exception);
	}

	/**
	 * Gets the target namespace.
	 *
	 * @return the target namespace
	 */
	public String getTargetNamespace()
	{
		return this.targetNamespace;
	}

	/**
	 * Creates the attribute.
	 *
	 * @param ename
	 *            the ename
	 * @param aname
	 *            the aname
	 * @param atype
	 *            the atype
	 * @param enums
	 *            the enums
	 * @param dtype
	 *            the dtype
	 * @param dvalue
	 *            the dvalue
	 * @param nondvalue
	 *            the nondvalue
	 * @param augs
	 *            the augs
	 * @return the element
	 */
	private Element newAttribute(final String ename, final String aname, final String atype,
		final String[] enums, final String dtype, final XMLString dvalue, final XMLString nondvalue,
		final Augmentations augs)
	{
		final Element attr = this.doc.createElement(XSD_ATTRIBUTE);
		attr.setAttribute(NAME, aname);

		if (ENUMERATION_TYPE.equals(atype) || DTD_NOTATION.equals(atype))
		{
			if (enums != null)
			{
				final String typeName = ename + aname.substring(0, 1).toUpperCase()
					+ aname.substring(1);
				attr.setAttribute(ATTRIBUTE_TYPE, this.type + typeName);

				Element simpleType = this.allElements.get(typeName);
				if (simpleType == null)
				{
					simpleType = this.doc.createElement(XSD_SIMPLE_TYPE);
					simpleType.setAttribute(NAME, typeName);
					final Element restriction = this.doc.createElement(XSD_RESTRICTION);
					if (ENUMERATION_TYPE.equals(atype))
					{
						restriction.setAttribute(BASE, XSD_STRING);
					}
					else
					{
						restriction.setAttribute(BASE, XSD_NOTATION);
					}

					for (final String value : enums)
					{
						final Element enumeration = this.doc.createElement(XSD_ENUMERATION);
						enumeration.setAttribute(ATTRIBUTE_VALUE, value);
						restriction.appendChild(enumeration);
					}
					simpleType.appendChild(restriction);
					this.root.appendChild(simpleType);
					this.allElements.put(typeName, simpleType);
				}
			}
		}
		else
		{
			String xsdType = null;
			for (final TypePattern xsdPattern : this.listXsdTypePattern)
			{
				if (xsdPattern.match(aname))
				{
					xsdType = xsdPattern.getXsdType();
					break;
				}
			}

			if (xsdType == null)
			{
				xsdType = this.dataTypeMap.get(atype);
			}

			if (xsdType != null)
			{
				attr.setAttribute(ATTRIBUTE_TYPE, xsdType);
			}

		}

		boolean fixed = false;
		if (REQUIRED_TYPE.equals(dtype))
		{
			attr.setAttribute(USE, REQUIRED);
		}
		else if (FIXED_TYPE.equals(dtype))
		{
			attr.setAttribute(ATTRIBUTE_FIXED, dvalue.toString());
			fixed = true;
		}
		else
		{
			attr.setAttribute(USE, OPTIONAL);
		}

		if (!fixed && dvalue != null)
		{
			attr.setAttribute(ATTRIBUTE_DEFAULT, dvalue.toString());
		}

		return attr;
	}

	/**
	 * Creates the sequence or choice.
	 *
	 * @param type
	 *            the type
	 * @param listElement
	 *            the list element
	 * @return the element
	 */
	private Element newSequenceOrChoice(final short type, final List<Element> listElement)
	{
		Element seq_cho = null;
		switch (type)
		{
			case 1 :
				seq_cho = this.doc.createElement(XSD_SEQUENCE);
				break;
			case 0 :
				seq_cho = this.doc.createElement(XSD_CHOICE);
		}

		List<Element> realListElement = listElement;
		if (type == 1)
		{
			realListElement = combineSequenceList(listElement);
		}

		for (final Element child : realListElement)
		{
			seq_cho.appendChild(child);
		}

		return seq_cho;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notationDecl(final String name, final XMLResourceIdentifier identifier,
		final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Element notation = this.doc.createElement(XSD_NOTATION_LOWERCASE);
		notation.setAttribute(NAME, name);
		if (identifier != null && identifier.getLiteralSystemId() != null)
		{
			notation.setAttribute(ATTRIBUTE_PUBLIC, identifier.getLiteralSystemId());
		}

		this.stackElements.peek().appendChild(notation);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void occurrence(final short type, final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Element element = this.stackElements.peek();
		setOccursAttributes(element, type);
		if (log.isDebugEnabled())
		{
			printDomNode(element);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pcdata(final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Element pcdata = this.doc.createElement(PCDATA);
		if (this.groupDepth > 0)
		{
			final List<Element> listElement = this.listGroupElements.get(this.groupDepth - 1);
			listElement.add(pcdata);
		}
		this.stackElements.add(pcdata);
	}

	/**
	 * Prints the dom node.
	 *
	 * @param node
	 *            the node
	 */
	private void printDomNode(final Node node)
	{
		final Writer out = new StringWriter();
		final LSSerializer writer = this.domImpl.createLSSerializer();
		final LSOutput output = this.domImpl.createLSOutput();
		output.setCharacterStream(out);
		output.setEncoding(UTF_8);
		writer.getDomConfig().setParameter(FORMAT_PRETTY_PRINT, Boolean.TRUE);
		writer.write(node, output);
		final String str = output.getCharacterStream().toString();
		log.debug(str);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void separator(final short type, final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		if (this.listGroupTypes.get(this.groupDepth - 1) != type)
		{
			this.listGroupTypes.set(this.groupDepth - 1, type);
		}
	}

	/**
	 * Sets the occurs attributes.
	 *
	 * @param element
	 *            the element
	 * @param type
	 *            the type
	 */
	private void setOccursAttributes(final Element element, final short type)
	{
		switch (type)
		{
			case 2 :
				element.setAttribute(MIN_OCCURS, "0");
				element.setAttribute(MAX_OCCURS, "1");
				break;
			case 3 :
				element.setAttribute(MIN_OCCURS, "0");
				element.setAttribute(MAX_OCCURS, "unbounded");
				break;
			case 4 :
				element.setAttribute(MIN_OCCURS, "1");
				element.setAttribute(MAX_OCCURS, "unbounded");
		}
	}

	/**
	 * Sets the target namespace.
	 *
	 * @param targetNamespace
	 *            the new target namespace
	 */
	public void setTargetNamespace(final String targetNamespace)
	{
		this.targetNamespace = targetNamespace;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startContentModel(final String ename, final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		final Element element = this.doc.createElement(XSD_ELEMENT);
		element.setAttribute(NAME, ename);

		this.stackElements.add(element);
		this.allElements.put(ename, element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDTD(final XMLLocator locator, final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		DOMImplementationRegistry registry = null;
		try
		{
			registry = DOMImplementationRegistry.newInstance();

			this.domImpl = (CoreDOMImplementationImpl)registry.getDOMImplementation("XML 3.0");

			this.doc = this.domImpl.createDocument(HTTP_WWW_W3_ORG_2001_XML_SCHEMA, XSD_SCHEMA,
				null);

			this.root = this.doc.getDocumentElement();
			if (this.targetNamespace != null)
			{
				this.root.setAttribute("targetNamespace", this.targetNamespace);
				this.root.setAttribute("xmlns:t", this.targetNamespace);
				this.type = "t:";
			}
			else
			{
				this.root.setAttribute("elementFormDefault", "qualified");
				this.root.setAttribute("attributeFormDefault", "unqualified");
				this.type = "";
			}

			this.stackElements.add(this.root);
		}
		catch (final Exception e)
		{
			throw new XNIException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startGroup(final Augmentations augs) throws XNIException
	{
		if (this.hasParsed)
		{
			return;
		}

		this.listGroupElements.add(new ArrayList<Element>());
		this.listGroupTypes.add(Short.valueOf("1"));
		this.groupDepth += 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startParameterEntity(final String name, final XMLResourceIdentifier identifier,
		final String encoding, final Augmentations augs) throws XNIException
	{
		if (this.setXmlResourceIdentifier.contains(name))
		{
			this.parsedStack.add(this.hasParsed);
			this.hasParsed = true;
		}
		else
		{
			this.parsedStack.add(this.hasParsed);
			this.hasParsed = false;
			this.setXmlResourceIdentifier.add(name);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warning(final String domain, final String key, final XMLParseException exception)
		throws XNIException
	{
		log.warn("domain=" + domain + ", key=" + key + ", exception=" + exception, exception);
	}

	/**
	 * Write xsd.
	 *
	 * @param out
	 *            the out
	 */
	public void writeXsd(final OutputStream out)
	{
		final LSSerializer writer = this.domImpl.createLSSerializer();
		final LSOutput output = this.domImpl.createLSOutput();
		output.setByteStream(out);
		output.setEncoding(UTF_8);
		writer.getDomConfig().setParameter(FORMAT_PRETTY_PRINT, Boolean.TRUE);
		writer.write(this.root, output);
	}

}