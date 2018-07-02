package com.FABE.util;  

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class Logger extends PrintStream {

	final StringBuilder buf;
	final PrintStream underlying;

	Logger(StringBuilder sb, OutputStream os, PrintStream ul) {
		super(os);
		this.buf = sb;
		this.underlying = ul;
	}

	public static Logger create(PrintStream toLog) {
		try {
			final StringBuilder sb = new StringBuilder();
			Field f = FilterOutputStream.class.getDeclaredField("out");
			f.setAccessible(true);
			OutputStream psout = (OutputStream) f.get(toLog);
			return new Logger(sb, new FilterOutputStream(psout) {
				public void write(int b) throws IOException {
					super.write(b);
					sb.append((char) b);
				}
			}, toLog);
		} catch (NoSuchFieldException shouldNotHappen) {
		} catch (IllegalArgumentException shouldNotHappen) {
		} catch (IllegalAccessException shouldNotHappen) {
		}
		return null;
	}
}
