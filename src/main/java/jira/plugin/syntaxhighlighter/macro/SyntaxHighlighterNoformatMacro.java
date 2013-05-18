package jira.plugin.syntaxhighlighter.macro;

import java.util.Map;

public class SyntaxHighlighterNoformatMacro extends SyntaxHighlighterMacro {

	@SuppressWarnings("rawtypes")
	@Override
	public String getBrush(Map parameters) {
		return "brush: plain; ";
	}

	
}
