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

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import java.util.List;

import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLParseException;
import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.dtd.to.xsd.configuration.Configuration;
import de.alpharogroup.dtd.to.xsd.type.TypePattern;

/**
 * The unit test class for the class {@link Parser}
 */
public class ParserTest
{

	/**
	 * Test method for
	 * {@link Parser#error(String, String, org.apache.xerces.xni.parser.XMLParseException)}
	 */
	@Test(expectedExceptions = XNIException.class)
	public final void testError()
	{
		Parser parser = new Parser(new Configuration());
		assertNotNull(parser);
		parser.error("foo", "Bar", new XMLParseException(new XMLLocator()
		{

			@Override
			public String getBaseSystemId()
			{
				return null;
			}

			@Override
			public int getCharacterOffset()
			{
				return 0;
			}

			@Override
			public int getColumnNumber()
			{
				return 0;
			}

			@Override
			public String getEncoding()
			{
				return null;
			}

			@Override
			public String getExpandedSystemId()
			{
				return null;
			}

			@Override
			public int getLineNumber()
			{
				return 0;
			}

			@Override
			public String getLiteralSystemId()
			{
				return null;
			}

			@Override
			public String getPublicId()
			{
				return null;
			}

			@Override
			public String getXMLVersion()
			{
				return null;
			}

		}, "foo"));
	}

	/**
	 * Test method for
	 * {@link Parser#error(String, String, org.apache.xerces.xni.parser.XMLParseException)}
	 */
	@Test(expectedExceptions = XNIException.class)
	public final void testFatalError()
	{
		Parser parser = new Parser(new Configuration());
		assertNotNull(parser);
		parser.fatalError("foo", "Bar", new XMLParseException(new XMLLocator()
		{

			@Override
			public String getBaseSystemId()
			{
				return null;
			}

			@Override
			public int getCharacterOffset()
			{
				return 0;
			}

			@Override
			public int getColumnNumber()
			{
				return 0;
			}

			@Override
			public String getEncoding()
			{
				return null;
			}

			@Override
			public String getExpandedSystemId()
			{
				return null;
			}

			@Override
			public int getLineNumber()
			{
				return 0;
			}

			@Override
			public String getLiteralSystemId()
			{
				return null;
			}

			@Override
			public String getPublicId()
			{
				return null;
			}

			@Override
			public String getXMLVersion()
			{
				return null;
			}

		}, "foo"));
	}

	/**
	 * Test method for {@link Parser} constructors and methods
	 */
	@Test
	public final void testParser()
	{
		Parser model = new Parser(new Configuration());
		assertNotNull(model);
		List<TypePattern> typePatterns = ListFactory.newArrayList(TypePattern.builder().build());
		model.addXsdTypePattern(typePatterns);
		model.addXsdTypePattern(TypePattern.builder().build());
		model.endParameterEntity("foo", null);
		String targetNamespace = model.getTargetNamespace();
		assertNull(targetNamespace);
		model.startParameterEntity("", new XMLResourceIdentifier()
		{

			@Override
			public String getBaseSystemId()
			{
				return null;
			}

			@Override
			public String getExpandedSystemId()
			{
				return null;
			}

			@Override
			public String getLiteralSystemId()
			{
				return null;
			}

			@Override
			public String getNamespace()
			{
				return null;
			}

			@Override
			public String getPublicId()
			{
				return null;
			}

			@Override
			public void setBaseSystemId(String systemId)
			{
			}

			@Override
			public void setExpandedSystemId(String systemId)
			{
			}

			@Override
			public void setLiteralSystemId(String systemId)
			{
			}

			@Override
			public void setNamespace(String namespace)
			{
			}

			@Override
			public void setPublicId(String publicId)
			{
			}
		}, "UTF-8", null);

		model.warning("foo", "bar", new XMLParseException(new XMLLocator()
		{

			@Override
			public String getBaseSystemId()
			{
				return null;
			}

			@Override
			public int getCharacterOffset()
			{
				return 0;
			}

			@Override
			public int getColumnNumber()
			{
				return 0;
			}

			@Override
			public String getEncoding()
			{
				return null;
			}

			@Override
			public String getExpandedSystemId()
			{
				return null;
			}

			@Override
			public int getLineNumber()
			{
				return 0;
			}

			@Override
			public String getLiteralSystemId()
			{
				return null;
			}

			@Override
			public String getPublicId()
			{
				return null;
			}

			@Override
			public String getXMLVersion()
			{
				return null;
			}

		}, "foo"));


	}

}
