package com.jasonalexllc.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 * Outputs information to a file or to the console.
 * @author Jason Carrete
 * @since Aug 16, 2014
 */
public class Log
{
	private static PrintWriter out;
	
	/**
	 * Default file name of the log
	 */
	public static String fileName = "log.txt";
	
	static
	{
		log("START OF SESSION...");
		log("START OF SESSION...", fileName);
	}
	
	/**
	 * Ends the current logging session
	 */
	public static void end()
	{
		log("END OF SESSION...");
		log("END OF SESSION...", fileName);
	}
	
	/**
	 * Logs the specified String {@code msg} to the specified file with {@code fileName}.
	 * @param msg Data to be written to the file.
	 * @param fileName The file the data is written to.
	 */
	public static void log(String msg, String fileName)
	{		
		//create an output stream to the file if one already hasn't been created or the file to write to changes
		if(out == null || !Log.fileName.equals(fileName))
		{
			Log.fileName = fileName;
			File log = new File(fileName);
			try
			{
				log.createNewFile();
				out = new PrintWriter(new BufferedWriter(new FileWriter(log, true)), true);
			}
			catch(IOException ex)
			{
				logError(ex);
				System.exit(1);
			}
		}
		
		out.println("[" + ZonedDateTime.now() + "]" + msg);
	}
	
	/**
	 * Logs {@code msg} to the console.
	 * @param msg Data to be written.
	 */
	public static void log(String msg)
	{
		System.out.println("[" + ZonedDateTime.now() + "]" + msg);
	}
	
	/**
	 * Logs the specified String {@code msg} as an error to the File with {@code fileName}.
	 * @param msg
	 * @param fileName
	 */
	public static void logError(String msg, String fileName)
	{
		log("[ERROR]" + msg, fileName);
	}
	
	/**
	 * Logs the specified Throwable {@code exception} as an error to the File with {@code fileName}.
	 * @param exception
	 * @param fileName
	 */
	public static void logError(Throwable exception, String fileName)
	{
		logError(exception.toString(), fileName);
	}
	
	/**
	 * Logs the specified String {@code msg} as an error to the console.
	 * @param msg String to be logged as an error.
	 */
	public static void logError(String msg)
	{
		System.out.println("[" + ZonedDateTime.now() + "][ERROR]" + msg);
	}
	
	/**
	 * Logs the specified Throwable {@code exception} as an error to the console.
	 * @param exception Throwable to be logged as an error.
	 */
	public static void logError(Throwable exception)
	{
		System.out.print("[" + ZonedDateTime.now() + "][ERROR]");
		exception.printStackTrace();
	}
}
