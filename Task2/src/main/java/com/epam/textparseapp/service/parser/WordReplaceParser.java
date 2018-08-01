package com.epam.textparseapp.service.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.textparseapp.entity.CompoundTextElement;
import com.epam.textparseapp.entity.TextElement;
import com.epam.textparseapp.util.RegExpBundle;

/**
 * Class used for parsing sentence as word and replace first and last world
 * (Task 2 Ex 5).
 * 
 * @version 1 01.08.2018
 * @author Alexander Shishonok
 */
public class WordReplaceParser extends BaseTextParser {

    public WordReplaceParser() {
    }

    public WordReplaceParser(BaseTextParser next) {
	super(next);
    }

    @Override
    public TextElement parse(String content) {
	TextElement textElement = new CompoundTextElement();
	Pattern wordPattern = Pattern
		.compile(RegExpBundle.WORD.getExpression());
	Matcher wordMatcher = wordPattern.matcher(content);
	int end = 0;
	int firstwordposition = 0;
	ArrayList<String> array = new ArrayList<String>();
	// Parse string content to array of String
	while (wordMatcher.find()) {
	    if (wordMatcher.start() > end) {
		array.add(content.substring(end, wordMatcher.start()));
	    }
	    array.add(wordMatcher.group());
	    if (end == 0) {
		firstwordposition = array.size() - 1;
	    }
	    end = wordMatcher.end();
	}
	int lastwordposition = array.size() - 1;
	// Add last punctuation char to array
	if (content.length() > end) {
	    array.add(content.substring(end, content.length()));
	}
	// Replace first and last word
	String temp = array.get(lastwordposition);
	array.set(lastwordposition, array.get(firstwordposition));
	array.set(firstwordposition, temp);
	// Add child element
	for (String str : array) {
	    textElement.add(parseNext(str));
	}
	return textElement;
    }

}
