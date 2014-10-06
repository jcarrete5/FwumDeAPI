package com.fwumdegames.games.framework;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * To represent an INI file
 * @author Ryan Goldstein
 */
public class Ini
{
	/*
	 * INI file format-
	 * [Section]
	 * Key = Value
	 * Key = Value
	 * [End]
	 * [Section]
	 * Key = Vale
	 * [End]
	 * 
	 * Sections may not be repeated
	 * Keys may not be repeated within the same section
	 */
	Section[] sections;
	File file;
	/**
	 * Creates an INI file reader
	 * @param file The ini file to read
	 * @throws IOException
	 */
	public Ini(File file) throws IOException
	{
		//Get a buffered reader for the ini file
		if(!file.exists())
			file.createNewFile();
		FileReader read = new FileReader(file);
		BufferedReader reader = new BufferedReader(read);
		String line,contents = "";
		this.file = file;
		
		//Read all file lines
		while((line = reader.readLine()) != null)
			contents += line + "\n";
		reader.close();
		
		//Cut the contents into chunk
		String[] chunks = contents.split("\\[");
		sections = new Section[chunks.length];
		
		//Turn the chunks into section
		for(int i = 0; i < chunks.length; i++)
		{
			sections[i] = new Section();
			sections[i].fromChunk(chunks[i].split("\n"));
		}
	}
	
	/**
	 * Gets the keys from a section of the INI file
	 * @param section The section to get keys from
	 * @return An array of keys paired with values
	 * @throws SectionNotFoundException If the section does not exist
	 */
	public String[] getKeypairs(String section) throws SectionNotFoundException
	{
		String[] keys = getSection(section).keys;
		String[] values = getSection(section).values;
		String[] pairs = new String[keys.length];
		for(int i = 0; i < pairs.length; i++)
			pairs[i] = keys[i] + "=" + values[i];
		return pairs;
	}
	
	/**
	 * Get an array of all the keys in the INI file
	 * @param section The section to get keys from
	 * @return The array of keys in the INI file
	 */
	public String[] getKeys(String section) throws SectionNotFoundException
	{
		Section sect = getSection(section);
		return sect.keys;
	}
	
	/**
	 * Returns the titles of all sections
	 * @return String array of all titles
	 */
	public String[] getTitle()
	{
		String[] titles = new String[sections.length];
		for(int i = 0; i < titles.length; i++)
			titles[0] = sections[0].title;
		return titles;
	}
	
	/**
	 * Reads a value from an ini file
	 * @param section The section to look in
	 * @param key The key to look for
	 * @return The value from the key
	 * @throws KeyNotFoundException 
	 */
	public String getValue(String section, String key) throws SectionNotFoundException, KeyNotFoundException
	{
		String[] keys = getKeys(section);
		String[] values = getSection(section).values;
		String value;
		int keyValue = -1;
		
		//Get key index
		boolean found = false;
		for(int i = 0; i < keys.length && !found; i++)
		{
			if(keys[i].equals(key))
			{
				found = true;
				keyValue = i;
			}
		}
		try
		{
			value = values[keyValue];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			throw new KeyNotFoundException();
		}
		return value;
	}
	
	private Section getSection(String title) throws SectionNotFoundException
	{
		Section match = null;
		try
		{
			for(int i = 0; i < sections.length && match == null; i++)
				if(sections[i].title.equals(title))
					match = sections[i];
		}
		catch(NullPointerException e)
		{
			throw new SectionNotFoundException();
		}
		return match;
	}
	/**
	 * Sets the value of a key in a section
	 * @param section The section to set in
	 * @param key The key to set
	 * @param newValue The value to enter
	 * @throws IOException The exception is from the buffered reader
	 */
	public void setValue(String section, String key, String newValue) throws IOException
	{
		boolean found = false;
		
		for(int i = 0; i < sections.length && !found; i++)
		{
			if(sections[i].title.equals(section))
			{
				found = true;
				sections[i].setValue(key, newValue);
			}
		}
		if(!found)
		{
			sections = expand(sections, 1);
			int newsect = sections.length - 1;
			sections[newsect] = new Section();
			sections[newsect].title = section;
			sections[newsect].setValue(key, newValue);
		}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String fullFile = "";
			for(int i = 1; i < sections.length; i++)
			{
				fullFile += "[" + sections[i].title + "]\n";
				for(int n = 0; n < sections[i].keys.length; n++)
					fullFile += sections[i].keys[n] + "=" + sections[i].values[n] + "\n";
			}
			writer.write(fullFile);
			writer.close();
		
	}
	
	private Section[] expand(Section[] smaller, int amt)
	{
		Section[] expanded = new Section[smaller.length + amt];
		for(int i = 0; i < smaller.length; i++)
			expanded[i] = smaller[i];
		return expanded;
	}
	
	public class SectionNotFoundException extends Exception
	{		private static final long serialVersionUID = 1L;}
	
	public class KeyNotFoundException extends Exception
	{		private static final long serialVersionUID = 1L;}
	
	private class Section
	{
		public String title;
		public String[] keys;
		public String[] values;
		
		public Section()
		{
			this.title = null;
			this.keys = null;
			this.values = null;
		}
		
		public void fromChunk(String[] chunk)
		{
			int equalsIndex,numpairs;
			numpairs = chunk.length - 1;
			//Initialize section information
			title = chunk[0];
			title = title.replace("[","");
			title = title.replace("]","");
			keys = new String[numpairs];
			values = new String[numpairs];
			
			for(int i = 1; i < chunk.length; i++)
			{
				equalsIndex = chunk[i].indexOf("=");
				keys[i - 1] = chunk[i].substring(0,equalsIndex);
				values[i - 1] = chunk[i].substring(equalsIndex + 1);
			}
		}
		
		public void setValue(String key, String newValue)
		{
			boolean found = false;
			for(int i = 0; keys != null && i < keys.length && !found; i++)
			{
				if(keys[i].equals(key))
				{
					found = true;
					values[i] = newValue;
				}
			}
			if(!found)
			{
				if(keys == null)
				{
					keys = new String[0];
					values = new String[0];
				}
				keys = expand(keys, 1);
				values = expand(values,1);
				keys[keys.length - 1] = key;
				values[values.length - 1] = newValue;
			}
		}
		
		public String[] expand(String[] smaller, int amt)
		{
			String[] expanded = new String[smaller.length + amt];
			for(int i = 0; i < smaller.length; i++)
				expanded[i] = smaller[i];
			return expanded;
		}
	}
}
