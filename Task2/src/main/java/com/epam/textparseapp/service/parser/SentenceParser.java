package com.epam.textparseapp.service.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.textparseapp.entity.CompoundTextElement;
import com.epam.textparseapp.entity.SimpleTextElement;
import com.epam.textparseapp.entity.TextElement;
import com.epam.textparseapp.util.RegExpBundle;

/**
 * Class used for parsing paragraphs as sentences.
 * 
 * @version 1 31.07.2018
 * @author Alexander Shishonok
 */
public class SentenceParser extends BaseTextParser {

    public SentenceParser() {
    }

    public SentenceParser(BaseTextParser next) {
	super(next);
    }

    @Override
    public TextElement parse(String content) {
	CompoundTextElement textElement = new CompoundTextElement();
	Matcher sentenceMatcher = Pattern
		.compile(RegExpBundle.SENTENCE.getExpression()).matcher(content);
	while (sentenceMatcher.find()) {
	    String textblock = sentenceMatcher.group();
	    textElement.add(parseNext(textblock));
	}
	textElement.add(new SimpleTextElement(
		RegExpBundle.BLOCK_SEPARATOR.getExpression()));
	return textElement;
    }

}
