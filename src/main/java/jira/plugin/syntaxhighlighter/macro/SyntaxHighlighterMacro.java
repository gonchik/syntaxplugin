package jira.plugin.syntaxhighlighter.macro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atlassian.jira.issue.fields.renderer.IssueRenderContext;
import org.apache.commons.lang3.StringEscapeUtils;

import syntaxhighlighter.SyntaxHighlighterParserUtil;
import syntaxhighlighter.beans.CodeContainer;
import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.BrushBash;
import syntaxhighlighter.brush.BrushCSharp;
import syntaxhighlighter.brush.BrushCpp;
import syntaxhighlighter.brush.BrushCss;
import syntaxhighlighter.brush.BrushDelphi;
import syntaxhighlighter.brush.BrushDiff;
import syntaxhighlighter.brush.BrushErlang;
import syntaxhighlighter.brush.BrushJScript;
import syntaxhighlighter.brush.BrushJava;
import syntaxhighlighter.brush.BrushJavaFX;
import syntaxhighlighter.brush.BrushPerl;
import syntaxhighlighter.brush.BrushPhp;
import syntaxhighlighter.brush.BrushPlain;
import syntaxhighlighter.brush.BrushPython;
import syntaxhighlighter.brush.BrushRuby;
import syntaxhighlighter.brush.BrushScala;
import syntaxhighlighter.brush.BrushSql;
import syntaxhighlighter.brush.BrushVb;
import syntaxhighlighter.brush.BrushXml;
import syntaxhighlighter.brush.custom.BrushD;
import syntaxhighlighter.brush.custom.BrushGherkin;
import syntaxhighlighter.brush.custom.BrushObjC;
import syntaxhighlighter.brush.custom.BrushPuppet;
import syntaxhighlighter.brush.custom.BrushTcl;

import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.velocity.VelocityManager;

/**
 * Copyright (c) 2012, 2013, 2014 by Holger Schimanski
 * 
 * Macro plugin class for syntax highlighting of source code in description, comments etc. of JIRA issues. 
 * See {@link https://marketplace.atlassian.com/plugins/jira.plugin.syntaxhighlighter.macro.syntaxplugin} for more details. 
 * 
**/
public class SyntaxHighlighterMacro extends BaseMacro {

	private static final String HIGHLIGHT = "highlight";
	private static final String TITLE = "title";
	private static final String FIRST_LINE = "first-line"; //Deprecated
	private static final String FIRSTLINE = "firstline"; //Use instead of first-line, default is 1
	private static final String SHOW_LINENUMS = "linenumbers"; //default is false
	private static final String COLLAPSE = "collapse"; //default is false
	private static final String TITLE_BACKGROUND_COLOR = "titleBGColor";
	private static final String TITLE_COLOR = "titleColor";
	private static final String WRAP_LINES = "wrap";
	
	/**
	 * Character ({@value}) used to separate ranges of line numbers.
	 */
	private static final String RANGE_SEPARATOR = "-";
	
	private final I18nResolver i18nResolver;
	private final VelocityManager velocityManager;

	public SyntaxHighlighterMacro(I18nResolver i18nResolver, VelocityManager velocityManager){
		this.i18nResolver = i18nResolver;
		this.velocityManager = velocityManager;
	}
	
	public boolean hasBody() {
		return true;
	}

	public RenderMode getBodyRenderMode() {
		return RenderMode.allow(RenderMode.F_HTMLESCAPE);
	}

	public boolean isInline() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	public String execute(Map parameters, String body, RenderContext renderContext) throws MacroException {

		if (Boolean.TRUE.equals(renderContext.getParam(IssueRenderContext.WYSIWYG_PARAM))) {
			return renderForWysiwyg(body);
		}

		//Syntax highlighting with brush
		Brush tmpBrush = getBrush(parameters);
	    CodeContainer tmpCodeContainer = SyntaxHighlighterParserUtil.brush(body, tmpBrush);
	    
	    //First line and hide line num parameter
	    tmpCodeContainer.setShowLineNums(getShowLineNums(parameters));
	    tmpCodeContainer.setFirstLine(getFirstLine(parameters));

	    tmpCodeContainer.setWrapLines(getWrapLines(parameters));

	    //Highlighting of code rows
	    List<Integer> highlighted = getHighlight(parameters);
	    for (Integer tmpLineNumHighlighted : highlighted) {
	    	int tmpCodeRowNumHighlighted = tmpLineNumHighlighted - tmpCodeContainer.getFirstLine();
	    	if ( tmpCodeRowNumHighlighted < tmpCodeContainer.getCodeRows().size() ){
	    		tmpCodeContainer.getCodeRows().get(tmpCodeRowNumHighlighted).setHighlighted(true);
	    	}
	    }
	    
	    //Put code container as param for velocity
	    Map<String,Object> contextParameters = new HashMap<>();
	    contextParameters.put("codeContainer", tmpCodeContainer);
	    contextParameters.put("codeTitle", getTitle(parameters));
	    contextParameters.put("codeTitleColor", getTitleColor(parameters));
	    contextParameters.put("codeTitleBackgroundColor", getTitleBackgroundColor(parameters));
	    if (getCollapse(parameters)){
		    contextParameters.put("codeCollapsed", i18nResolver.getText("common.concepts.showall"));
	    }

	    //Get HTML rendering using velocity templates
		StringBuilder codeBody = new StringBuilder();
		codeBody.append(velocityManager.getBody("templates/", "code.vm", contextParameters));
		
		return codeBody.toString();
		
	}

	protected String renderForWysiwyg(String body) {
		// TODO add parameters
		// TODO add <panel-title>
		return "<pre class=\"code panel\">" + body + "</pre>";
	}


	@SuppressWarnings("rawtypes")
	public String getTitle(Map parameters) {
		if ( parameters.containsKey(TITLE)){
			String tmpTitle = parameters.get(TITLE).toString();
			return StringEscapeUtils.escapeHtml4(tmpTitle);
		} 
		return null;
	}	

	
	@SuppressWarnings("rawtypes")
	public String getTitleColor(Map parameters) {
		if ( parameters.containsKey(TITLE_COLOR)){
			String tmpTitleColor = parameters.get(TITLE_COLOR).toString();
			
			if ( isValidColor(tmpTitleColor)){
				return tmpTitleColor;
			}
		} 
		return "#333";
	}	
	
	
	@SuppressWarnings("rawtypes")
	public String getTitleBackgroundColor(Map parameters) {
		if ( parameters.containsKey(TITLE_BACKGROUND_COLOR)){
			String tmpTitleBGColor = parameters.get(TITLE_BACKGROUND_COLOR).toString();
			
			if ( isValidColor(tmpTitleBGColor)){
				return tmpTitleBGColor;
			}
		} 
		return "#f5f5f5";
	}	
	
	
	private boolean isValidColor(String aColorString) {
		return aColorString.matches("[a-zA-Z]*") || aColorString.matches("#[a-fA-F0-9]{3}") || aColorString.matches("#[a-fA-F0-9]{6}");
	}
	
	
	@SuppressWarnings("rawtypes")
	public List<Integer> getHighlight(Map parameters) {
		List<Integer> ret = new ArrayList<>();
		if ( parameters.containsKey(HIGHLIGHT)){
			String paramValue = parameters.get(HIGHLIGHT).toString();
			ret.addAll(expandRanges(paramValue));
		}
		return ret;
	}	

	
	@SuppressWarnings("rawtypes")
	public int getFirstLine(Map parameters) {
		try{
			if ( parameters.containsKey(FIRSTLINE)){
				int firstLine = Integer.parseInt(parameters.get(FIRSTLINE).toString());
				return firstLine;
			} else if ( parameters.containsKey(FIRST_LINE)){
				int firstLine = Integer.parseInt(parameters.get(FIRST_LINE).toString());
				return firstLine;
			}

		}
		catch(NumberFormatException e){
			//TODO Log debug
		}
		
		return 1;
	}	

	
	@SuppressWarnings("rawtypes")
	public boolean getCollapse(Map parameters) {
		return parameters.containsValue(COLLAPSE) ||
				(parameters.containsKey(COLLAPSE) && parameters.get(COLLAPSE).equals("true")) ||
				(parameters.containsKey(COLLAPSE) && parameters.get(COLLAPSE).equals("yes"));
	}	
	
	
	@SuppressWarnings("rawtypes")
	public boolean getShowLineNums(Map parameters) {
		return parameters.containsValue(SHOW_LINENUMS) ||
				(parameters.containsKey(SHOW_LINENUMS) && parameters.get(SHOW_LINENUMS).equals("true")) ||
				(parameters.containsKey(SHOW_LINENUMS) && parameters.get(SHOW_LINENUMS).equals("yes"));
	}	
	
	@SuppressWarnings("rawtypes")
	public boolean getWrapLines(Map parameters) {
		return parameters.containsValue(WRAP_LINES) ||
				(parameters.containsKey(WRAP_LINES) && parameters.get(WRAP_LINES).equals("true")) ||
				(parameters.containsKey(WRAP_LINES) && parameters.get(WRAP_LINES).equals("yes"));
	}

	/**
	 * Scans a given string of line numbers for occurrences of a range (eg- 1-3).
	 * Any ranges found will be expanded to sequences.
	 * 
	 * @param ranges
	 *            Comma-separated list of line numbers and ranges in any
	 *            combination. String is expected to be surrounded by [ and ].
	 * @return ranges with all ranges expanded to sequences. Any other token
	 *         will remain unchanged.
	 */
	public List<Integer> expandRanges(String ranges) {
		String[] parts;
		List<Integer> ret = new ArrayList<>();
		
		if (ranges.isEmpty() || ranges.equals("[]")) return ret;
		
		if (ranges.startsWith("[") && ranges.endsWith("]")) {
			ranges = ranges.substring(1, ranges.length()-1);
		} 
		parts = ranges.split(",");
		for (String part : parts) {
			if (part.contains(RANGE_SEPARATOR)) {
				ret.addAll(rangeToSequence(part));
			} else {
				try{
					ret.add(new Integer(part));
				}
				catch(NumberFormatException e){
					//Nothing to do...
					//TODO log debug number format exception
				}
			}
		}
		return ret;
		
	}
	
	/**
	 * Makes a sequence of numbers out of a given range. For Example, "1-3" will
	 * produce "1,2,3". A valid range consists of two numbers separated by the
	 * {@link #RANGE_SEPARATOR}. The second number has to be greater than the first.
	 * 
	 * @param range String representation of the range to expand to sequence
	 * @return A list of Integers or an empty list if any error occurs.
	 */
	public List<Integer> rangeToSequence(String range) {
		String[] parts;
		List<Integer> ret = new ArrayList<>();
		int sequenceStart, sequenceEnd;
		
		parts = range.split(String.valueOf(RANGE_SEPARATOR));
		
		try {
			if (parts.length == 2) {
				sequenceStart = Integer.parseInt(parts[0]);
				sequenceEnd = Integer.parseInt(parts[1]);

				for (int i = sequenceStart; i <= sequenceEnd; i++) {
					ret.add(i);
				}
			} else {
				//TODO log.debug wrong string
			}
		} catch (NumberFormatException nfe) {
			//TODO Log debug numberformat exception
		}

		return ret;
	}
	
	
	/**
	 * Returns the brush to be used for the language specified as parameter in {code:...}. Returns plain as default if 
	 * language is not specified or unknown.
	 * 
	 * @param parameters Map of parameters 
	 * @return brush name
	 */
	@SuppressWarnings("rawtypes")
	public Brush getBrush(Map parameters) {
		
		if (parameters.containsKey("0")) {
			String tmpParam = (String) parameters.get("0");
			if ( "erlang".equals(tmpParam) ) {
				return new BrushErlang();
			}
			else if ( "diff".equals(tmpParam) ) {
				return new BrushDiff();
			}
			else if ( "sql".equals(tmpParam) ) {
				return new BrushSql();
			}
			else if ( "css".equals(tmpParam) ) {
				return new BrushCss();
			}
			else if ( "php".equals(tmpParam) ) {
				return new BrushPhp();
			}
			else if ( "ruby".equals(tmpParam) ) {
				return new BrushRuby();
			}
			else if ( "perl".equals(tmpParam) ) {
				return new BrushPerl();
			}
			else if ( "javafx".equals(tmpParam) ) {
				return new BrushJavaFX();
			}
			else if ( "java".equals(tmpParam) ) {
				return new BrushJava();
			}
			else if ( "scala".equals(tmpParam) ) {
				return new BrushScala();
			}
			else if ( "bash".equals(tmpParam) ) {
				return new BrushBash();
			}
			else if ( "gherkin".equals(tmpParam) ) {
				return new BrushGherkin();
			}
			else if ( "tcl".equals(tmpParam) ) {
				return new BrushTcl();
			}
			else if ( 
					"puppet".equals(tmpParam) ||
					"pp".equals(tmpParam)
					) {
				return new BrushPuppet();
			}
			else if (
					"csharp".equals(tmpParam) || 
					"cs".equals(tmpParam) || 
					"c#".equals(tmpParam)  
					) {
				return new BrushCSharp();
			}
			else if (
					"c".equals(tmpParam) || 
					"c++".equals(tmpParam) || 
					"cpp".equals(tmpParam)  
					) {
				return new BrushCpp();
			}
			else if (
					"delphi".equals(tmpParam) || 
					"pas".equals(tmpParam) || 
					"pascal".equals(tmpParam)  
					) {
				return new BrushDelphi();
			}
			else if (
					"d".equals(tmpParam) || 
					"di".equals(tmpParam)
					) {
				return new BrushD();
			}
			else if (
					"objc".equals(tmpParam) || 
					"obj-c".equals(tmpParam)  
					) {
				return new BrushObjC();
			}
			else if (
					"js".equals(tmpParam) || 
					"javascript".equals(tmpParam) || 
					"jscript".equals(tmpParam)  
					) {
				return new BrushJScript();
			}
			else if (
					"py".equals(tmpParam) || 
					"python".equals(tmpParam) 
					) {
				return new BrushPython();
			}
			else if (
					"vb".equals(tmpParam) || 
					"vbnet".equals(tmpParam) 
					) {
				return new BrushVb();
			}
			else if (
					"xml".equals(tmpParam) || 
					"xhtml".equals(tmpParam) || 
					"xslt".equals(tmpParam) || 
					"html".equals(tmpParam)  
					) {
				return new BrushXml();
			}
		}
		
		return new BrushPlain();
	}


}
