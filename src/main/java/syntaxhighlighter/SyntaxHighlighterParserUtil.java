package syntaxhighlighter;

import java.util.List;

import syntaxhighlighter.beans.CodeContainer;
import syntaxhighlighter.brush.Brush;

/**
 * Utility class to create list of code rows with list of code per row
 * using SyntaxHighlighterParser and a given brush. 
 * 
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class SyntaxHighlighterParserUtil {

	public static final String STYLE_SCRIPT = "font-weight: bold;";
	public static final String STYLE_CONSTANTS = "color: #0066cc;";
	public static final String STYLE_FUNCTIONS = "color: #ff1493;";
	public static final String STYLE_VALUE = "color: #009900;";
	public static final String STYLE_VARIABLE = "color: #aa7700;";
	public static final String STYLE_PREPROCESSOR = "color: gray;";
	public static final String STYLE_KEYWORD = "color: #006699; font-weight: bold;";
	public static final String STYLE_STRING = "color: blue;";
	public static final String STYLE_COMMENTS = "color: #008200;";
	public static final String STYLE_PLAIN = "color: black;";
	public static final String STYLE_COLOR1 = STYLE_PREPROCESSOR;
	public static final String STYLE_COLOR2 = STYLE_FUNCTIONS;
	public static final String STYLE_COLOR3 = "color: red;";
	

	public static CodeContainer brush(String aText, Brush aBrush) {

		//Remove leading line feed
		if ( aText.startsWith("\n")) {
			aText = aText.substring(1);
		} else if ( aText.startsWith("\r\n")) {
			aText = aText.substring(2);
		}
		
		//Remove trailing line feed
		if ( aText.endsWith("\n")) {
			aText = aText.substring(0, aText.length()-1);
		} else if ( aText.endsWith("\r\n")) {
			aText = aText.substring(0, aText.length()-2);
		}
		
		//Parse text
		SyntaxHighlighterParser aParser = new SyntaxHighlighterParser(aBrush);
		List<ParseResult> aResultList = aParser.parse(null, aText);

		//Create code container based on parse results
		CodeContainer container = new CodeContainer();

		int currentIndex = 0;
		for (ParseResult parseResult : aResultList) {
			if (parseResult.getOffset() >= currentIndex) {

				String plainText = aText.substring(currentIndex, parseResult.getOffset());
				addCodeAndRowElements(container, plainText, "plain");

				String formattedText = aText.substring(parseResult.getOffset(), parseResult.getOffset() + parseResult.getLength());
				addCodeAndRowElements(container, formattedText, parseResult.getStyleKeysString());
				
				currentIndex = parseResult.getOffset() + parseResult.getLength();
			}
		}

		String plainTextRemaining = aText.substring(currentIndex);
		addCodeAndRowElements(container, plainTextRemaining, "plain");

		return container;

	}

	private static void addCodeAndRowElements(CodeContainer container, String plainText, String styleKey) {

		String style = "";
		if ( styleKey.equals("plain")){
			style = STYLE_PLAIN;
		} else if ( styleKey.equals("comments")){
			style = STYLE_COMMENTS;
		} else if ( styleKey.equals("string")){
			style = STYLE_STRING;
		} else if ( styleKey.equals("keyword")){
			style = STYLE_KEYWORD;
		} else if ( styleKey.equals("preprocessor")){
			style = STYLE_PREPROCESSOR;
		} else if ( styleKey.equals("variable")){
			style = STYLE_VARIABLE;
		} else if ( styleKey.equals("value")){
			style = STYLE_VALUE;
		} else if ( styleKey.equals("functions")){
			style = STYLE_FUNCTIONS;
		} else if ( styleKey.equals("constants")){
			style = STYLE_CONSTANTS;
		} else if ( styleKey.equals("script")){
			style = STYLE_SCRIPT;
		} else if ( styleKey.equals("color1")){
			style = STYLE_COLOR1;
		} else if ( styleKey.equals("color2")){
			style = STYLE_COLOR2;
		} else if ( styleKey.equals("color3")){
			style = STYLE_COLOR3;
		}
		
		plainText = plainText.replaceAll("\r\n", "\n");
		
		while (!plainText.equals("")) {

			if (!plainText.contains("\n")) {
				container.addCode(style, plainText);
				plainText = "";
			} else if (plainText.startsWith("\n")) {
				container.newCodeRow();
				if (plainText.equals("\n")) {
					plainText = "";
				} else {
					plainText = plainText.substring(1);
				}
			} else {
				container.addCode(style, plainText.substring(0, plainText.indexOf("\n")));
				plainText = plainText.substring(plainText.indexOf("\n"));
			}
		}

	}

}
