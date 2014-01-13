package jira.plugin.syntaxhighlighter.macro;

import java.util.Map;

/**
 * Copyright (c) 2012, 2013, 2014 by Holger Schimanski
 * 
 * Macro plugin class for layout of noformat in description, comments etc. of JIRA issues. 
 * See {@link https://marketplace.atlassian.com/plugins/jira.plugin.syntaxhighlighter.macro.syntaxplugin} for more details. 
 * 
**/
public class SyntaxHighlighterNoformatMacro extends SyntaxHighlighterMacro {

	/**
	 * Returns plain as brush to use for {noformat} macro. 
	 * 
	 * @param parameters Map of parameters 
	 * @return brush 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String getBrush(Map parameters) {
		return "brush: plain; ";
	}

	
}
