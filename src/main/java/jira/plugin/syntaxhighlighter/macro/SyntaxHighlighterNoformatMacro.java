package jira.plugin.syntaxhighlighter.macro;

import java.util.Map;

import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.velocity.VelocityManager;

import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.BrushPlain;

/**
 * Copyright (c) 2012, 2013, 2014 by Holger Schimanski
 * 
 * Macro plugin class for layout of noformat in description, comments etc. of JIRA issues. 
 * See {@link https://marketplace.atlassian.com/plugins/jira.plugin.syntaxhighlighter.macro.syntaxplugin} for more details. 
 * 
**/
public class SyntaxHighlighterNoformatMacro extends SyntaxHighlighterMacro {

	public SyntaxHighlighterNoformatMacro(I18nResolver i18nResolver, VelocityManager velocityManager){
		super(i18nResolver, velocityManager);
	}
	/**
	 * Returns plain as brush to use for {noformat} macro. 
	 * 
	 * @param parameters Map of parameters 
	 * @return brush 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Brush getBrush(Map parameters) {
		return new BrushPlain();
	}

	@Override
	protected String renderForWysiwyg(String body) {
		// TODO add parameters
		// TODO add <panel-title>
		return "<pre class=\"noformat panel\">" + body + "</pre>";
	}



}
