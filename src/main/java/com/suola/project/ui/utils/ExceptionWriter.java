package com.suola.project.ui.utils;

import java.io.PrintWriter;
import java.io.Writer;

public class ExceptionWriter extends PrintWriter {
	public ExceptionWriter(Writer writer) {
		super(writer);
	}

	private String wrapAroundWithNewlines(String stringWithoutNewlines) {
		return ("\n" + stringWithoutNewlines + "\n");
	}

	/*
	 * Convert a stacktrace into a string
	 */
	public String getExceptionAsString(Throwable throwable) {
		throwable.printStackTrace(this);

		String exception = super.out.toString();

		return (wrapAroundWithNewlines(exception));
	}
}
