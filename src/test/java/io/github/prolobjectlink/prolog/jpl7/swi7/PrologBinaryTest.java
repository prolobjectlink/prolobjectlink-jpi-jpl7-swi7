/*
 * #%L
 * prolobjectlink-jpi-jpl7-swi7
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package io.github.prolobjectlink.prolog.jpl7.swi7;

import static io.github.prolobjectlink.prolog.PrologLogger.FILE_NOT_FOUND;
import static io.github.prolobjectlink.prolog.PrologLogger.IO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.prolobjectlink.prolog.jpl7.swi7.SwiProlog7Console;

public class PrologBinaryTest extends PrologBaseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void pllink() throws FileNotFoundException, IOException {

		// files existence
		File bat = new File("bin/pllink.bat");
		File sh = new File("bin/pllink.sh");
		File src = new File("src/build/filters/bin.properties");
		assertTrue(bat.exists());
		assertTrue(sh.exists());
		assertTrue(src.exists());

		// build properties test
		Properties bin = new Properties();
		bin.load(new FileReader(src));
		String script = bin.getProperty("Main.FileName");
		assertEquals("pllink", script);
		String main = bin.getProperty("Main.Class");
		assertEquals(SwiProlog7Console.class.getName(), main);

		//
		String line = null;
		StringBuilder b = null;
		FileReader reader = null;
		BufferedReader buffer = null;

		try {
			reader = new FileReader(bat);
			buffer = new BufferedReader(reader);
			line = buffer.readLine();
			b = new StringBuilder();
			while (line != null) {
				b.append(line);
				line = buffer.readLine();
			}

			assertTrue(b.toString().contains(SwiProlog7Console.class.getName()));

			reader = new FileReader(sh);
			buffer = new BufferedReader(reader);
			line = buffer.readLine();
			b = new StringBuilder();
			while (line != null) {
				b.append(line);
				line = buffer.readLine();
			}

			assertTrue(b.toString().contains(SwiProlog7Console.class.getName()));

		} catch (FileNotFoundException e) {
			provider.getLogger().error(getClass(), FILE_NOT_FOUND, e);
		} catch (IOException e) {
			provider.getLogger().error(getClass(), IO, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					provider.getLogger().error(getClass(), IO, e);
				}
			}
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					provider.getLogger().error(getClass(), IO, e);
				}
			}
		}

	}

	@Test
	public final void install() throws FileNotFoundException, IOException {

		// files existence
		File bat = new File("bin/pllink.bat");
		File sh = new File("bin/pllink.sh");
		assertTrue(bat.exists());
		assertTrue(sh.exists());

		String line = null;
		StringBuilder b = null;
		FileReader reader = null;
		BufferedReader buffer = null;

		try {
			reader = new FileReader(bat);
			buffer = new BufferedReader(reader);
			line = buffer.readLine();
			b = new StringBuilder();
			while (line != null) {
				b.append(line);
				line = buffer.readLine();
			}

			assertTrue(b.toString().contains(SwiProlog7Console.class.getName()));

			reader = new FileReader(sh);
			buffer = new BufferedReader(reader);
			line = buffer.readLine();
			b = new StringBuilder();
			while (line != null) {
				b.append(line);
				line = buffer.readLine();
			}

			assertTrue(b.toString().contains(SwiProlog7Console.class.getName()));

		} catch (FileNotFoundException e) {
			provider.getLogger().error(getClass(), FILE_NOT_FOUND, e);
		} catch (IOException e) {
			provider.getLogger().error(getClass(), IO, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					provider.getLogger().error(getClass(), IO, e);
				}
			}
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					provider.getLogger().error(getClass(), IO, e);
				}
			}
		}
	}

}
