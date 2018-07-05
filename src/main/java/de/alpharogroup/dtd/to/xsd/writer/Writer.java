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
package de.alpharogroup.dtd.to.xsd.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;

import de.alpharogroup.dtd.to.xsd.configuration.Configuration;
import de.alpharogroup.dtd.to.xsd.parser.Parser;
import de.alpharogroup.dtd.to.xsd.type.TypePattern;


/**
 * The class Writer.
 *
 * @author Asterios Raptis
 */
public class Writer
{

	/** The dtd2 xsd configuration. */
	private final Configuration dtd2XSDConfiguration;

	/** The dtd2 xsd parser. */
	private final Parser dtd2XSDParser;

	/** The out stream. */
	private OutputStream outStream;

	/**
	 * Instantiates a new dtd2 xsd writer.
	 */
	public Writer()
	{
		this.dtd2XSDConfiguration = new Configuration();
		this.dtd2XSDParser = new Parser(this.dtd2XSDConfiguration);
	}

	/**
	 * Adds the xsd type pattern.
	 *
	 * @param list
	 *            the list
	 */
	public void addXsdTypePattern(final List<TypePattern> list)
	{
		this.dtd2XSDParser.addXsdTypePattern(list);
	}

	/**
	 * Adds the xsd type pattern.
	 *
	 * @param xsdTypePattern
	 *            the xsd type pattern
	 */
	public void addXsdTypePattern(final TypePattern xsdTypePattern)
	{
		this.dtd2XSDParser.addXsdTypePattern(xsdTypePattern);
	}

	/**
	 * Gets the out stream.
	 *
	 * @return the out stream
	 */
	public OutputStream getOutStream()
	{
		return this.outStream;
	}

	/**
	 * Gets the target namespace.
	 *
	 * @return the target namespace
	 */
	public String getTargetNamespace()
	{
		return this.dtd2XSDParser.getTargetNamespace();
	}

	/**
	 * Parses the.
	 *
	 * @param xmlInputSource
	 *            the xml input source
	 * @throws XNIException
	 *             the xNI exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void parse(final XMLInputSource xmlInputSource) throws XNIException, IOException
	{
		this.dtd2XSDParser.parse(xmlInputSource);
		this.dtd2XSDParser.writeXsd(this.outStream);
	}

	/**
	 * Sets the out stream.
	 *
	 * @param outStream
	 *            the new out stream
	 */
	public void setOutStream(final OutputStream outStream)
	{
		this.outStream = outStream;
	}

	/**
	 * Sets the target namespace.
	 *
	 * @param targetNamespace
	 *            the new target namespace
	 */
	public void setTargetNamespace(final String targetNamespace)
	{
		this.dtd2XSDParser.setTargetNamespace(targetNamespace);
	}
}