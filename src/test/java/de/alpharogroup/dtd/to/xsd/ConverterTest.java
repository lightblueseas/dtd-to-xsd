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

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

public class ConverterTest
{

	File dataset = new File(PathFinder.getSrcTestResourcesDir(), "result.xsd");
	File datasetDtd = new File(PathFinder.getSrcTestResourcesDir(), "dataset.dtd");

	File propertiesXsd = new File(PathFinder.getSrcTestResourcesDir(), "properties.xsd");
	File propertiesDtd = new File(PathFinder.getSrcTestResourcesDir(), "properties.dtd");

	@BeforeMethod
	public void setUp() throws Exception
	{

	}

	@AfterMethod
	public void tearDown() throws Exception
	{

	}

	@Test
	public void testConvertStringFile()
	{
		Converter.convert(datasetDtd, dataset);
		Converter.convert(propertiesDtd, propertiesXsd);
	}

}
