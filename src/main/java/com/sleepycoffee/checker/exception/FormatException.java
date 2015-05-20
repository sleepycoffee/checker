package com.sleepycoffee.checker.exception;

/**
 * Signals that a format error has occurred. 
 */
public class FormatException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Constructs a FormatException with null as its detail message.
     */
    public FormatException() {}
    /**
     * Constructs a FormatException with the specified detail message.
     * @param s         the String containing a detail message
     */
    public FormatException(String s) { super(s); }
}
