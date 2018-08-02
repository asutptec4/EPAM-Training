package com.epam.textparseapp.service.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.textparseapp.entity.CompoundTextElement;
import com.epam.textparseapp.entity.SimpleTextElement;
import com.epam.textparseapp.entity.TextElement;
import com.epam.textparseapp.util.RegExpBundle;

/**
 * Class used for parsing sentence as word and punctuation.
 * 
 * @version 1 31.07.2018
 * @author Alexander Shishonok
 */
public class WordParser extends BaseTextParser {

    public WordParser() {
    }

    public WordParser(BaseTextParser next) {
	super(next);
    }

    @Override
    public TextElement parse(String content) {
	CompoundTextElement textElement = new CompoundTextElement();
	Matcher wordMatcher = Pattern
		.compile(RegExpBundle.WORD.getExpression()).matcher(content);
	int end = 0;
	while (wordMatcher.find()) {
	    if (wordMatcher.start() > end) {
		textElement.add(new SimpleTextElement(
			content.substring(end, wordMatcher.start())));
	    }
	    String textblock = wordMatcher.group();
	    textElement.add(parseNext(textblock));
	    end = wordMatcher.end();
	}
	if (content.length() > end) {
	    textElement.add(new SimpleTextElement(
		    content.substring(end, content.length())));
	}
	return textElement;
    }

}
