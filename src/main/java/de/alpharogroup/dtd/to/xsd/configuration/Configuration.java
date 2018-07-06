/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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
package de.alpharogroup.dtd.to.xsd.configuration;

import java.io.EOFException;
import java.io.IOException;
import java.util.Locale;

import org.apache.xerces.impl.XMLDTDScannerImpl;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.ParserConfigurationSettings;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

/**
 * The class {@link Configuration} holds information for parsing a dtd document to an xsd document.
 *
 * @author Asterios Raptis
 */
public class Configuration extends ParserConfigurationSettings implements XMLParserConfiguration
{

	/** The Constant HTTP_APACHE_ORG_XML_PROPERTIES_LOCALE. */
	private static final String HTTP_APACHE_ORG_XML_PROPERTIES_LOCALE = "http://apache.org/xml/properties/locale";

	/** The Constant HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ERROR_HANDLER. */
	private static final String HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";

	/** The Constant HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ERROR_REPORTER. */
	private static final String HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";

	/** The Constant HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ENTITY_RESOLVER. */
	private static final String HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";

	/** The Constant HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ENTITY_MANAGER. */
	private static final String HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";

	/** The Constant HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_SYMBOL_TABLE. */
	private static final String HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";

	/** The Constant HTTP_XML_ORG_SAX_FEATURES_VALIDATION. */
	private static final String HTTP_XML_ORG_SAX_FEATURES_VALIDATION = "http://xml.org/sax/features/validation";

	/** The entity manager. */
	private final XMLEntityManager entityManager = new XMLEntityManager();

	/** The error reporter. */
	private final XMLErrorReporter errorReporter = new XMLErrorReporter();

	/** The scanner. */
	private final XMLDTDScannerImpl scanner = new XMLDTDScannerImpl();

	/** The symbol table. */
	private final SymbolTable symbolTable = new SymbolTable();

	/** The xml document handler. */
	private XMLDocumentHandler xmlDocumentHandler;

	/** The xml dtd content model handler. */
	private XMLDTDContentModelHandler xmlDTDContentModelHandler;

	/** The xml dtd handler. */
	private XMLDTDHandler xmlDTDHandler;

	/** The xml entity resolver. */
	private XMLEntityResolver xmlEntityResolver;

	/** The xml error handler. */
	private XMLErrorHandler xmlErrorHandler;

	/**
	 * Instantiates a new dtd2 xsd configuration.
	 */
	public Configuration()
	{
		final String[] featureNames = { HTTP_XML_ORG_SAX_FEATURES_VALIDATION };
		final Boolean[] featureValues = { Boolean.FALSE };
		addRecognizedFeatures(featureNames);
		for (int i = 0; i < featureNames.length; ++i)
		{
			final Boolean featureValue = featureValues[i];
			if (featureValue != null)
			{
				final String featureName = featureNames[i];
				setFeature(featureName, featureValue);
			}

		}

		final String[] propertyNames = { HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_SYMBOL_TABLE,
				HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ENTITY_MANAGER,
				HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ENTITY_RESOLVER,
				HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ERROR_REPORTER,
				HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ERROR_HANDLER,
				HTTP_APACHE_ORG_XML_PROPERTIES_LOCALE };

		final Object[] propertyValues = { this.symbolTable, this.entityManager, null,
				this.errorReporter, null, Locale.getDefault() };

		addRecognizedProperties(propertyNames);
		for (int i = 0; i < propertyNames.length; ++i)
		{
			final Object propertyValue = propertyValues[i];
			if (propertyValue != null)
			{
				final String propertyName = propertyNames[i];
				setProperty(propertyName, propertyValue);
			}
		}
	}

	/**
	 * Gets the document handler.
	 *
	 * @return the document handler
	 * @see XMLParserConfiguration#getDocumentHandler()
	 */
	@Override
	public XMLDocumentHandler getDocumentHandler()
	{
		return this.xmlDocumentHandler;
	}

	/**
	 * Gets the dTD content model handler.
	 *
	 * @return the dTD content model handler
	 * @see XMLParserConfiguration#getDTDContentModelHandler()
	 */
	@Override
	public XMLDTDContentModelHandler getDTDContentModelHandler()
	{
		return this.xmlDTDContentModelHandler;
	}

	/**
	 * Gets the dTD handler.
	 *
	 * @return the dTD handler
	 * @see XMLParserConfiguration#getDTDHandler()
	 */
	@Override
	public XMLDTDHandler getDTDHandler()
	{
		return this.xmlDTDHandler;
	}

	/**
	 * Gets the entity resolver.
	 *
	 * @return the entity resolver
	 * @see XMLParserConfiguration#getEntityResolver()
	 */
	@Override
	public XMLEntityResolver getEntityResolver()
	{
		return this.xmlEntityResolver;
	}

	/**
	 * Gets the error handler.
	 *
	 * @return the error handler
	 * @see XMLParserConfiguration#getErrorHandler()
	 */
	@Override
	public XMLErrorHandler getErrorHandler()
	{
		return this.xmlErrorHandler;
	}

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 * @see XMLParserConfiguration#getLocale()
	 */
	@Override
	public Locale getLocale()
	{
		Locale locale = (Locale)getProperty(HTTP_APACHE_ORG_XML_PROPERTIES_LOCALE);
		return locale;
	}

	/**
	 * Parses the given {@link XMLInputSource}
	 *
	 * @param source
	 *            the source
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * 
	 * @see XMLParserConfiguration#parse(XMLInputSource)
	 */
	@Override
	public void parse(final XMLInputSource source) throws IOException
	{
		this.scanner.reset(this);
		this.entityManager.reset(this);
		this.errorReporter.reset(this);
		this.scanner.setInputSource(source);
		try
		{
			this.scanner.scanDTDExternalSubset(true);
		}
		catch (final EOFException e)
		{
		}
	}

	/**
	 * Sets the document handler.
	 *
	 * @param handler
	 *            the new document handler
	 * @see XMLParserConfiguration#setDocumentHandler(XMLDocumentHandler)
	 */
	@Override
	public void setDocumentHandler(final XMLDocumentHandler handler)
	{
		this.xmlDocumentHandler = handler;
	}

	/**
	 * Sets the dTD content model handler.
	 *
	 * @param handler
	 *            the new dTD content model handler
	 * @see XMLParserConfiguration#setDTDContentModelHandler(XMLDTDContentModelHandler)
	 */
	@Override
	public void setDTDContentModelHandler(final XMLDTDContentModelHandler handler)
	{
		this.xmlDTDContentModelHandler = handler;
		this.scanner.setDTDContentModelHandler(this.xmlDTDContentModelHandler);
	}

	/**
	 * Sets the DTD handler.
	 *
	 * @param handler
	 *            the new DTD handler
	 * @see XMLParserConfiguration#setDTDHandler(XMLDTDHandler)
	 */
	@Override
	public void setDTDHandler(final XMLDTDHandler handler)
	{
		this.xmlDTDHandler = handler;
		this.scanner.setDTDHandler(this.xmlDTDHandler);
	}

	/**
	 * Sets the entity resolver.
	 *
	 * @param resolver
	 *            the new entity resolver
	 * @see XMLParserConfiguration#setEntityResolver(XMLEntityResolver)
	 */
	@Override
	public void setEntityResolver(final XMLEntityResolver resolver)
	{
		this.xmlEntityResolver = resolver;
		setProperty(HTTP_APACHE_ORG_XML_PROPERTIES_INTERNAL_ENTITY_RESOLVER,
			this.xmlEntityResolver);
	}

	/**
	 * Sets the error handler.
	 *
	 * @param handler
	 *            the new error handler
	 * @see XMLParserConfiguration#setErrorHandler(XMLErrorHandler)
	 */
	@Override
	public void setErrorHandler(final XMLErrorHandler handler)
	{
		this.xmlErrorHandler = handler;
	}

	/**
	 * Sets the locale.
	 *
	 * @param locale
	 *            the new locale
	 * @throws XNIException
	 *             thrown if the parser does not support the specified locale
	 * @see XMLParserConfiguration#setLocale(Locale)
	 */
	@Override
	public void setLocale(final Locale locale) throws XNIException
	{
		setProperty(HTTP_APACHE_ORG_XML_PROPERTIES_LOCALE, locale);
		this.errorReporter.setLocale(locale);
	}

}