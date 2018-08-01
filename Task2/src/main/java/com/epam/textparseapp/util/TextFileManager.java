package com.epam.textparseapp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.textparseapp.exception.TextFileException;

/**
 * A utility class for working with text files.
 * 
 * @version 1 30.07.2018
 * @author Alexander Shishonok
 */
public class TextFileManager {

    private static final Logger LOGGER = LogManager
	    .getLogger(TextFileManager.class);

    /**
     * Method receive text content from file.
     * 
     * @param filePath
     *            path to file.
     * @return the {@code String} with file's content.
     * @throws TextFileException
     */
    public static String getTextFromFile(String filePath)
	    throws TextFileException {
	StringBuilder builder = new StringBuilder();
	try (BufferedReader reader = new BufferedReader(
		new FileReader(new File(filePath)))) {
	    String line;
	    while ((line = reader.readLine()) != null) {
		builder.append(line).append("\n");
	    }
	} catch (FileNotFoundException e) {
	    throw new TextFileException("File not found", e);
	} catch (IOException e) {
	    throw new TextFileException("Fail to read from file", e);
	}
	LOGGER.debug("Text loaded from file - " + filePath);
	return builder.toString();
    }

    /**
     * Method save text data to file.
     * 
     * @param filePath
     *            path to file.
     * @param text
     *            the {@code String} data.
     * @throws TextFileException
     */
    public static void saveTextToFile(String filePath, String text)
	    throws TextFileException {
	try (FileWriter writer = new FileWriter(new File(filePath))) {
	    writer.write(text);
	} catch (IOException e) {
	    throw new TextFileException("Fail to write to file", e);
	}
	LOGGER.debug("Text saved to file - " + filePath);
    }
}
