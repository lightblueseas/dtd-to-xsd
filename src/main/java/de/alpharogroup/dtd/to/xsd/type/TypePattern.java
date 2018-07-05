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
package de.alpharogroup.dtd.to.xsd.type;

import java.util.regex.Pattern;


/**
 * The class TypePattern.
 *
 * @author Asterios Raptis
 */
public class TypePattern
{

	/** The pattern. */
	private Pattern pattern;

	/** The xsd type. */
	private String xsdType;

	/**
	 * Instantiates a new xsd type pattern.
	 */
	public TypePattern()
	{
	}

	/**
	 * Instantiates a new type pattern.
	 *
	 * @param pattern
	 *            the pattern
	 * @param xsdType
	 *            the xsd type
	 */
	public TypePattern(final Pattern pattern, final String xsdType)
	{
		this.pattern = pattern;
		this.xsdType = xsdType;
	}

	/**
	 * Gets the pattern.
	 *
	 * @return the pattern
	 */
	public Pattern getPattern()
	{
		return this.pattern;
	}

	/**
	 * Gets the xsd type.
	 *
	 * @return the xsd type
	 */
	public String getXsdType()
	{
		return this.xsdType;
	}

	/**
	 * Match.
	 *
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public boolean match(final String name)
	{
		if (this.pattern == null)
		{
			return false;
		}

		return this.pattern.matcher(name).matches();
	}

	/**
	 * Sets the pattern.
	 *
	 * @param pattern
	 *            the new pattern
	 */
	public void setPattern(final Pattern pattern)
	{
		this.pattern = pattern;
	}

	/**
	 * Sets the xsd type.
	 *
	 * @param xsdType
	 *            the new xsd type
	 */
	public void setXsdType(final String xsdType)
	{
		this.xsdType = xsdType;
	}
}