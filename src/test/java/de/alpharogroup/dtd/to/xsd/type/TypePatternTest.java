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
package de.alpharogroup.dtd.to.xsd.type;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.evaluators.EqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link TypePattern}
 */
public class TypePatternTest
{

	/**
	 * Test method for {@link TypePattern} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		TypePattern model = new TypePattern();
		assertNotNull(model);
		model = new TypePattern(Pattern.compile("(xsd|xml)"), "value");
		assertNotNull(model);
		model = TypePattern.builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link TypePattern#equals(Object)} , {@link TypePattern#hashCode()} and
	 * {@link TypePattern#toString()}
	 *
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
		InstantiationException, ClassNotFoundException, IOException
	{
		boolean actual;
		boolean expected;
		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString(TypePattern.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link TypePattern#match(String)}.
	 */
	@Test
	public final void testMatch() throws Exception
	{
		boolean actual;
		boolean expected;
		TypePattern typePattern;

		typePattern = new TypePattern(Pattern.compile("'.*'"), "");
		actual = typePattern.match("'xsd'");
		expected = true;
		assertEquals(expected, actual);

		typePattern = new TypePattern(null, "");
		actual = typePattern.match("'xsd'");
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link TypePattern}
	 */
	@Test
	public void testWithBeanTester()
	{
		Configuration configuration = new ConfigurationBuilder()
			.overrideFactory("pattern", new Factory<Pattern>()
			{

				@Override
				public Pattern create()
				{
					return Pattern.compile("(xsd|xml)");
				}

			}).build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(TypePattern.class, configuration);
		beanTester.testBean(TypePattern.class);
	}

}
