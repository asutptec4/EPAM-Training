package com.epam.textparseapp.service.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.textparseapp.entity.CompoundTextElement;
import com.epam.textparseapp.entity.SimpleTextElement;
import com.epam.textparseapp.entity.TextElement;
import com.epam.textparseapp.util.RegExpBundle;

/**
 * Class used for parsing string data by blocks as paragraphs and listings.
 * 
 * @version 1 31.07.2018
 * @author Alexander Shishonok
 */
public class BlockTextParser extends BaseTextParser {

    public BlockTextParser() {
    }

    public BlockTextParser(BaseTextParser next) {
	super(next);
    }

    @Override
    public TextElement parse(String content) {
	CompoundTextElement textElement = new CompoundTextElement();
	Matcher blockMatcher = Pattern
		.compile(RegExpBundle.BLOCK.getExpression()).matcher(content);
	while (blockMatcher.find()) {
	    String textblock = blockMatcher.group();
	    if (Pattern.matches(RegExpBundle.PARAGRAPH.getExpression(),
		    textblock)) {
		textElement.add(parseNext(textblock)); // if paragraph
	    } else {
		textElement.add(new SimpleTextElement(textblock)); // if listing
	    }
	}
	return textElement;
    }

}