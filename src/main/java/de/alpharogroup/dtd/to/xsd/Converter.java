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
package de.alpharogroup.dtd.to.xsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;

import de.alpharogroup.dtd.to.xsd.type.TypePattern;
import de.alpharogroup.dtd.to.xsd.writer.Writer;

/**
 * The class Converter.
 *
 * @author Asterios Raptis
 */
public final class Converter
{

	/**
	 * Convert.
	 *
	 * @param dtdfile
	 *            the dtdfile
	 * @param xsdfile
	 *            the xsdfile
	 * @return the file
	 */
	public static File convert(final File dtdfile, final File xsdfile)
	{
		OutputStream outStream = null;
		if (xsdfile != null)
		{
			try
			{
				outStream = new FileOutputStream(xsdfile);
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace(System.err);
			}
		}

		final Writer writer = new Writer();
		writer.setOutStream(outStream);
		try
		{
			writer.parse(new XMLInputSource(null, dtdfile.getAbsolutePath(), null));
		}
		catch (final XNIException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return xsdfile;
	}

	/**
	 * Convert.
	 *
	 * @param dtdfile
	 *            the dtdfile
	 * @param xsdfile
	 *            the xsdfile
	 * @return the file
	 */
	public static File convert(final String dtdfile, final File xsdfile)
	{
		OutputStream outStream = null;
		if (xsdfile != null)
		{
			try
			{
				outStream = new FileOutputStream(xsdfile);
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace(System.err);
			}
		}

		final Writer writer = new Writer();
		writer.setOutStream(outStream);
		try
		{
			writer.parse(new XMLInputSource(null, dtdfile, null));
		}
		catch (final XNIException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return xsdfile;
	}

	/**
	 * Convert.
	 *
	 * @param targetNamespace
	 *            the target namespace
	 * @param listXsdTypePattern
	 *            the list xsd type pattern
	 * @param dtdfile
	 *            the dtdfile
	 * @param xsdfile
	 *            the xsdfile
	 */
	public static void convert(final String targetNamespace,
		final List<TypePattern> listXsdTypePattern, final String dtdfile, final File xsdfile)
	{
		OutputStream outStream = null;
		if (xsdfile != null)
		{
			try
			{
				outStream = new FileOutputStream(xsdfile);
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace(System.err);
				return;
			}
		}

		final Writer writer = new Writer();
		writer.setTargetNamespace(targetNamespace);
		writer.addXsdTypePattern(listXsdTypePattern);
		writer.setOutStream(outStream);
		try
		{
			writer.parse(new XMLInputSource(null, dtdfile, null));
		}
		catch (final XNIException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Convert.
	 *
	 * @param targetNamespace
	 *            the target namespace
	 * @param listXsdTypePattern
	 *            the list xsd type pattern
	 * @param dtdfile
	 *            the dtdfile
	 * @param xsdfile
	 *            the xsdfile
	 */
	public static void convert(final String targetNamespace,
		final List<TypePattern> listXsdTypePattern, final String dtdfile, final String xsdfile)
	{
		OutputStream outStream = null;
		if (xsdfile != null)
		{
			try
			{
				outStream = new FileOutputStream(xsdfile);
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace(System.err);
				return;
			}
		}

		final Writer writer = new Writer();
		writer.setTargetNamespace(targetNamespace);
		writer.addXsdTypePattern(listXsdTypePattern);
		writer.setOutStream(outStream);
		try
		{
			writer.parse(new XMLInputSource(null, dtdfile, null));
		}
		catch (final XNIException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Convert.
	 *
	 * @param dtdfile
	 *            the dtdfile
	 * @param xsdfile
	 *            the xsdfile
	 */
	public static void convert(final String dtdfile, final String xsdfile)
	{
		OutputStream outStream = null;
		if (xsdfile != null)
		{
			try
			{
				outStream = new FileOutputStream(xsdfile);
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace(System.err);
				return;
			}
		}

		final Writer writer = new Writer();
		writer.setOutStream(outStream);
		try
		{
			writer.parse(new XMLInputSource(null, dtdfile, null));
		}
		catch (final XNIException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates a new dtd2 xsd converter.
	 */
	private Converter()
	{
	}
}
