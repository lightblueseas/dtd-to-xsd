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

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.dtd.to.xsd.type.TypePattern;
import de.alpharogroup.file.checksum.ChecksumExtensions;
import de.alpharogroup.file.search.PathFinder;

/**
 * The unit test class for the class {@link DtdToXsdExtensions}.
 */
public class DtdToXsdExtensionsTest extends AbstractTestCase<File, File>
{

	/** The actual dataset xsd that will be returned from the conversion. */
	File actualDatasetXsd;

	/** The actual properties xsd that will be returned from the conversion. */
	File actualPropertiesXsd;

	/** The dataset dtd that will be used for input argument in the conversion. */
	File datasetDtd;

	/**
	 * The expected dataset with namespace xsd that will be compared with the actual dataset xsd.
	 */
	File expectedDatasetWithNamespaceXsd;

	/** The expected dataset xsd that will be compared with the actual dataset xsd. */
	File expectedDatasetXsd;

	/**
	 * The expected properties with namespace xsd that will be compared with the actual properties
	 * xsd.
	 */
	File expectedPropertiesWithNamespaceXsd;

	/** The expected properties xsd that will be compared with the actual properties xsd. */
	File expectedPropertiesXsd;

	/** The properties dtd that will be used for input argument in the conversion. */
	File propertiesDtd;

	/**
	 * Sets up method will be invoked before every unit test method
	 *
	 * @throws Exception
	 *             is thrown if an exception occurs
	 */
	@Override
	@BeforeMethod
	public void setUp() throws Exception
	{
		datasetDtd = new File(PathFinder.getSrcTestResourcesDir(), "dataset.dtd");
		actualDatasetXsd = new File(PathFinder.getSrcTestResourcesDir(), "actual-dataset.xsd");
		expectedDatasetXsd = new File(PathFinder.getSrcTestResourcesDir(), "expected-dataset.xsd");
		expectedDatasetWithNamespaceXsd = new File(PathFinder.getSrcTestResourcesDir(),
			"expected-dataset-with-namespace.xsd");

		propertiesDtd = new File(PathFinder.getSrcTestResourcesDir(), "properties.dtd");
		actualPropertiesXsd = new File(PathFinder.getSrcTestResourcesDir(),
			"actual-properties.xsd");
		expectedPropertiesXsd = new File(PathFinder.getSrcTestResourcesDir(),
			"expected-properties.xsd");
		expectedPropertiesWithNamespaceXsd = new File(PathFinder.getSrcTestResourcesDir(),
			"expected-properties-with-namespace.xsd");
	}

	@Override
	@AfterMethod
	public void tearDown() throws Exception
	{
		actualDatasetXsd.delete();
		actualPropertiesXsd.delete();
	}

	/**
	 * Test method for {@link DtdToXsdExtensions#convert(File, File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testConvertFileFile() throws IOException
	{
		actual = DtdToXsdExtensions.convert(datasetDtd, actualDatasetXsd);
		expected = expectedDatasetXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));

		actual = DtdToXsdExtensions.convert(propertiesDtd, actualPropertiesXsd);
		expected = expectedPropertiesXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));
	}

	/**
	 * Test method for {@link DtdToXsdExtensions#convert(String, File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testConvertStringFile() throws IOException
	{
		actual = DtdToXsdExtensions.convert(datasetDtd.getAbsolutePath(), actualDatasetXsd);
		expected = expectedDatasetXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));

		actual = DtdToXsdExtensions.convert(propertiesDtd.getAbsolutePath(), actualPropertiesXsd);
		expected = expectedPropertiesXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));
	}

	/**
	 * Test method for {@link DtdToXsdExtensions#convert(String, List, String, File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testConvertStringListOfTypePatternStringFile() throws IOException
	{
		String targetNamespace;
		List<TypePattern> listXsdTypePattern;
		String dtdfile;

		targetNamespace = "foo";
		listXsdTypePattern = ListFactory.newArrayList();
		dtdfile = datasetDtd.getAbsolutePath();
		actual = actualDatasetXsd;
		DtdToXsdExtensions.convert(targetNamespace, listXsdTypePattern, dtdfile, actual);
		expected = expectedDatasetWithNamespaceXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));

		targetNamespace = "bar";
		listXsdTypePattern = ListFactory.newArrayList();
		dtdfile = propertiesDtd.getAbsolutePath();
		actual = actualPropertiesXsd;
		DtdToXsdExtensions.convert(targetNamespace, listXsdTypePattern, dtdfile, actual);
		expected = expectedPropertiesWithNamespaceXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));
	}

	/**
	 * Test method for {@link DtdToXsdExtensions#convert(String, List, String, String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testConvertStringListOfTypePatternStringString() throws IOException
	{
		String targetNamespace;
		List<TypePattern> listXsdTypePattern;
		String dtdfile;
		String xsdfile;

		targetNamespace = "foo";
		listXsdTypePattern = ListFactory.newArrayList();
		dtdfile = datasetDtd.getAbsolutePath();
		xsdfile = actualDatasetXsd.getAbsolutePath();
		DtdToXsdExtensions.convert(targetNamespace, listXsdTypePattern, dtdfile, xsdfile);
		actual = actualDatasetXsd;
		expected = expectedDatasetWithNamespaceXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));

		targetNamespace = "bar";
		listXsdTypePattern = ListFactory.newArrayList();
		dtdfile = propertiesDtd.getAbsolutePath();
		xsdfile = actualPropertiesXsd.getAbsolutePath();
		DtdToXsdExtensions.convert(targetNamespace, listXsdTypePattern, dtdfile, xsdfile);
		actual = actualPropertiesXsd;
		expected = expectedPropertiesWithNamespaceXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));
	}

	/**
	 * Test method for {@link DtdToXsdExtensions#convert(String, String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testConvertStringString() throws IOException
	{
		DtdToXsdExtensions.convert(datasetDtd.getAbsolutePath(),
			actualDatasetXsd.getAbsolutePath());
		actual = actualDatasetXsd;
		expected = expectedDatasetXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));

		DtdToXsdExtensions.convert(propertiesDtd.getAbsolutePath(),
			actualPropertiesXsd.getAbsolutePath());
		actual = actualPropertiesXsd;
		expected = expectedPropertiesXsd;

		assertEquals(ChecksumExtensions.getCheckSumAdler32(actual),
			ChecksumExtensions.getCheckSumAdler32(expected));
	}

	/**
	 * Test method for {@link DtdToXsdExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(DtdToXsdExtensions.class);
	}

}
