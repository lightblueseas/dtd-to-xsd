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
package de.alpharogroup.dtd.to.xsd.configuration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import java.util.Locale;

import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link Configuration}
 */
public class ConfigurationTest
{

	/**
	 * Test method for {@link Configuration} constructors
	 */
	@Test
	public final void testConstructors()
	{
		Configuration configuration = new Configuration();
		assertNotNull(configuration);
		XMLDocumentHandler documentHandler = configuration.getDocumentHandler();
		assertNull(documentHandler);
		Locale locale = configuration.getLocale();
		assertNotNull(locale);
		configuration.setLocale(Locale.ENGLISH);
		locale = configuration.getLocale();
		assertNotNull(locale);
		assertEquals(locale, Locale.ENGLISH);
		XMLEntityResolver entityResolver;
		entityResolver = configuration.getEntityResolver();
		assertNull(entityResolver);
		entityResolver = new XMLEntityManager();
		configuration.setEntityResolver(new XMLEntityManager());
		XMLEntityResolver actual = configuration.getEntityResolver();
		assertNotNull(actual);
		XMLDTDContentModelHandler dtdContentModelHandler = configuration
			.getDTDContentModelHandler();
		assertNull(dtdContentModelHandler);
		XMLDTDHandler dtdHandler = configuration.getDTDHandler();
		assertNull(dtdHandler);
		XMLErrorHandler errorHandler = configuration.getErrorHandler();
		assertNull(errorHandler);
	}

}
