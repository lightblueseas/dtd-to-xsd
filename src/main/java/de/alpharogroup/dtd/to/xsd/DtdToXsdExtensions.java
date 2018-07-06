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
package de.alpharogroup.dtd.to.xsd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.xerces.xni.parser.XMLInputSource;

import de.alpharogroup.dtd.to.xsd.type.TypePattern;
import de.alpharogroup.dtd.to.xsd.writer.Writer;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link DtdToXsdExtensions} provides algorithms for convert old dtd files to the new xsd
 * format.
 *
 * @author Asterios Raptis
 */
@UtilityClass
public final class DtdToXsdExtensions
{

	/**
	 * Convert the given dtd file to the new xsd format.
	 *
	 * @param dtdfile
	 *            the dtd file to convert
	 * @param xsdfile
	 *            the xsd file to write
	 * @return the resulted xsd file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File convert(final @NonNull File dtdfile, final @NonNull File xsdfile)
		throws IOException
	{
		OutputStream outStream = new FileOutputStream(xsdfile);
		final Writer writer = new Writer();
		writer.setOutStream(outStream);
		writer.parse(new XMLInputSource(null, dtdfile.getAbsolutePath(), null));
		return xsdfile;
	}

	/**
	 * Convert the given the dtd file(that is the path as String) to the new xsd format.
	 *
	 * @param dtdfile
	 *            the dtd file to convert
	 * @param xsdfile
	 *            the xsd file to write
	 * @return the resulted xsd file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File convert(final @NonNull String dtdfile, final @NonNull File xsdfile)
		throws IOException
	{
		OutputStream outStream = new FileOutputStream(xsdfile);
		final Writer writer = new Writer();
		writer.setOutStream(outStream);
		writer.parse(new XMLInputSource(null, dtdfile, null));
		return xsdfile;
	}

	/**
	 * Convert the given the dtd file(that is the path as String) to the new xsd format with target
	 * namespace.
	 *
	 * @param targetNamespace
	 *            the target namespace
	 * @param listXsdTypePattern
	 *            the list xsd type pattern
	 * @param dtdfile
	 *            the dtd file to convert
	 * @param xsdfile
	 *            the xsd file to write
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void convert(final @NonNull String targetNamespace,
		final List<TypePattern> listXsdTypePattern, final @NonNull String dtdfile,
		final @NonNull File xsdfile) throws IOException
	{
		OutputStream outStream = new FileOutputStream(xsdfile);
		final Writer writer = new Writer();
		writer.setTargetNamespace(targetNamespace);
		writer.addXsdTypePattern(listXsdTypePattern);
		writer.setOutStream(outStream);
		writer.parse(new XMLInputSource(null, dtdfile, null));
	}

	/**
	 * Convert the given dtd file to the new xsd format with target namespace.
	 *
	 * @param targetNamespace
	 *            the target namespace
	 * @param listXsdTypePattern
	 *            the list xsd type pattern
	 * @param dtdfile
	 *            the dtd file to convert
	 * @param xsdfile
	 *            the xsd file to write
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void convert(final @NonNull String targetNamespace,
		final List<TypePattern> listXsdTypePattern, final @NonNull String dtdfile,
		final @NonNull String xsdfile) throws IOException
	{
		OutputStream outStream = new FileOutputStream(xsdfile);
		final Writer writer = new Writer();
		writer.setTargetNamespace(targetNamespace);
		writer.addXsdTypePattern(listXsdTypePattern);
		writer.setOutStream(outStream);
		writer.parse(new XMLInputSource(null, dtdfile, null));
	}

	/**
	 * Convert the given the dtd file(that is the path as String) to the new xsd format.
	 *
	 * @param dtdfile
	 *            the dtd file to convert
	 * @param xsdfile
	 *            the xsd file to write
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void convert(final String dtdfile, final String xsdfile) throws IOException
	{
		OutputStream outStream = new FileOutputStream(xsdfile);
		final Writer writer = new Writer();
		writer.setOutStream(outStream);
		writer.parse(new XMLInputSource(null, dtdfile, null));
	}

}
