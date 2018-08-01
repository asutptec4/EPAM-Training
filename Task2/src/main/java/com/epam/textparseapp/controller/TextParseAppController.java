package com.epam.textparseapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.textparseapp.entity.TextElement;
import com.epam.textparseapp.exception.TextFileException;
import com.epam.textparseapp.service.parser.BlockTextParser;
import com.epam.textparseapp.service.parser.SentenceParser;
import com.epam.textparseapp.service.parser.WordParser;
import com.epam.textparseapp.service.parser.WordReplaceParser;
import com.epam.textparseapp.util.TextFileManager;

/**
 * Main controller used for parse input text and transform according tasks.
 * 
 * @version 1 27.07.2018
 * @author Alexander Shishonok
 */
public class TextParseAppController {

    private static final Logger LOGGER = LogManager
	    .getLogger(TextParseAppController.class);

    public static void main(String[] args) {
	LOGGER.debug("Application started ...");

	String content = null;
	try {
	    content = TextFileManager
		    .getTextFromFile("textfiles/text_sample.txt");
	} catch (TextFileException e) {
	    LOGGER.error("Could not get text", e);
	}
	// Parse text to composite structure
	TextElement te = new BlockTextParser(
		new SentenceParser(new WordParser())).parse(content);
	// Restore from composite structure
	try {
	    TextFileManager.saveTextToFile("textfiles/output.txt", te.print());
	} catch (TextFileException e) {
	    LOGGER.error("Could not save text", e);
	}
	// Replace word in sentencess
	te = new BlockTextParser(new SentenceParser(new WordReplaceParser()))
		.parse(content);
	try {
	    TextFileManager.saveTextToFile("textfiles/output_ex5.txt",
		    te.print());
	} catch (TextFileException e) {
	    LOGGER.error("Could not save text", e);
	}

	LOGGER.debug("Application closed.");
    }

}
