package jira.plugin.syntaxhighlighter.macro;

import java.util.Map;

import com.atlassian.jira.ComponentManager;
import com.atlassian.plugin.webresource.UrlMode;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;

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
	private static final String FIRST_LINE = "first-line";
	private static final String HIDE_LINENUM = "hide-linenum";
	/**
	 * Character ({@value}) used to separate ranges of line numbers.
	 */
	private static final char RANGE_SEPARATOR = '-';

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
	public String execute(Map parameters, String body,
			RenderContext renderContext) throws MacroException {

		StringBuffer tmpBuffer = new StringBuffer();
		
		//Title
		if (parameters.containsKey(TITLE)){
			tmpBuffer.append("<div style='margin-left: 1em; margin-top:1em;'><div class='syntaxhighlighter'><code>");
			tmpBuffer.append(parameters.get(TITLE).toString());
			tmpBuffer.append("</code></div></div>");
		}
		
		//Code		
		tmpBuffer.append("<div style='margin-left: 1em;'>");
		tmpBuffer.append("<pre class='" + 
				getBrush(parameters) + 
				getFirstLine(parameters) + 
				getHighlight(parameters) + 
				getHideLineNum(parameters) + 
				"toolbar: false;'>");
		tmpBuffer.append(body);
		tmpBuffer.append("</pre>");
		tmpBuffer.append("<img onload='SyntaxHighlighter.highlight();' style='display:none;' " +
				"src='" + getBlankImageUrl() + "'/>");
		tmpBuffer.append("</div>");
		
		return tmpBuffer.toString();
		
	}
	
	public String getBlankImageUrl(){
		WebResourceManager tmpWebResourceManager = ComponentManager.getInstance().getWebResourceManager();
		String url = tmpWebResourceManager.getStaticPluginResource("jira.plugin.syntaxhighlighter.macro.syntaxplugin:images", "blank.png", UrlMode.AUTO);
		
		return url;
	}

	@SuppressWarnings("rawtypes")
	public String getHighlight(Map parameters) {
		if ( parameters.containsKey(HIGHLIGHT)){
			String paramValue = parameters.get(HIGHLIGHT).toString();
			paramValue = expandRanges(paramValue);
			return HIGHLIGHT + " : " + paramValue + "; ";
		} else {
			return "";
		}
	}	

	@SuppressWarnings("rawtypes")
	public String getFirstLine(Map parameters) {
		if ( parameters.containsKey(FIRST_LINE)){
			return FIRST_LINE + " : " + parameters.get(FIRST_LINE) + "; ";
		} else {
			return "";
		}
	}	

	@SuppressWarnings("rawtypes")
	public String getHideLineNum(Map parameters) {
		if ( parameters.containsValue(HIDE_LINENUM) || 
				( parameters.containsKey(HIDE_LINENUM) && parameters.get(HIDE_LINENUM).equals("true") ) ||
				( parameters.containsKey(HIDE_LINENUM) && parameters.get(HIDE_LINENUM).equals("yes") ) ){
			return "gutter : false; ";
		} else {
			return "";
		}
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
	public String expandRanges(String ranges) {
		String[] parts;
		String ret = "";
		
		if (ranges.startsWith("[") && ranges.endsWith("]")) {
			parts = ranges.substring(1, ranges.length()-1).split(",");
			for (String part : parts) {
				if (part.indexOf(RANGE_SEPARATOR) > -1) {
					if (ret.length() > 0) {ret += ",";};
					ret += rangeToSequence(part);
				} else {
					if (ret.length() > 0) {ret += ",";};
					ret += part;
				}
			}
			return "[" + ret + "]";
		} else {
			return ranges;
		}
	}
	
	/**
	 * Makes a sequence of numbers out of a given range. For Example, "1-3" will
	 * produce "1,2,3". A valid range consists of two numbers separated by the
	 * {@link #RANGE_SEPARATOR}. The second number has to greater than the first.
	 * 
	 * @param range
	 *            The range the sequence should be made of.
	 * @return A comma-separated list of numbers or the value of range if any
	 *         error occurs.
	 */
	public String rangeToSequence(String range) {
		String[] parts;
		String ret = "";
		int sequenceStart, sequenceEnd;
		
		parts = range.split(String.valueOf(RANGE_SEPARATOR));
		
		if (parts.length == 2) {
			try {  
				sequenceStart = Integer.parseInt(parts[0]);
				sequenceEnd = Integer.parseInt(parts[1]);
			}  
			catch(NumberFormatException nfe) {  
				return range;
			}
			
			if (sequenceStart < sequenceEnd) {
				for (int i = sequenceStart; i < sequenceEnd; i++) {
					ret += String.valueOf(i) + ",";
				}
				ret += String.valueOf(sequenceEnd);
				
				return ret;
			} else {
				return range;
			}
		} else {
			return range;
		}
	}
	
	
	/**
	 * Returns the brush to be used for the language specified as parameter in {code:...}. Returns plain as default if 
	 * language is not specified or unknown.
	 * 
	 * @param parameters Map of parameters 
	 * @return brush name
	 */
	@SuppressWarnings("rawtypes")
	public String getBrush(Map parameters) {
		
		String tmpMode = "plain";
		
		if (parameters.containsKey("0")) {
			String tmpParam = (String) parameters.get("0");
			if ( 
					"gherkin".equals(tmpParam) || 
					"erlang".equals(tmpParam) || 
					"diff".equals(tmpParam) || 					
					"sql".equals(tmpParam) || 
					"css".equals(tmpParam) || 
					"php".equals(tmpParam) || 
					"ruby".equals(tmpParam) || 
					"perl".equals(tmpParam) || 
					"javafx".equals(tmpParam) || 
					"java".equals(tmpParam) ||
					"tcl".equals(tmpParam) ||
					"scala".equals(tmpParam) ||
					"bash".equals(tmpParam) 
					) {
				tmpMode = (String) parameters.get("0");
			}
			else if (
					"csharp".equals(tmpParam) || 
					"cs".equals(tmpParam) || 
					"c#".equals(tmpParam)  
					) {
				tmpMode = "csharp";
			}
			else if (
					"c".equals(tmpParam) || 
					"c++".equals(tmpParam) || 
					"cpp".equals(tmpParam)  
					) {
				tmpMode = "cpp";
			}
			else if (
					"delphi".equals(tmpParam) || 
					"pas".equals(tmpParam) || 
					"pascal".equals(tmpParam)  
					) {
				tmpMode = "pascal";
			}
			else if (
					"d".equals(tmpParam) || 
					"di".equals(tmpParam)
					) {
				tmpMode = "d";
			}
			else if (
					"objc".equals(tmpParam) || 
					"obj-c".equals(tmpParam)  
					) {
				tmpMode = "objc";
			}
			else if (
					"js".equals(tmpParam) || 
					"javascript".equals(tmpParam) || 
					"jscript".equals(tmpParam)  
					) {
				tmpMode = "js";
			}
			else if (
					"py".equals(tmpParam) || 
					"python".equals(tmpParam) 
					) {
				tmpMode = "python";
			}
			else if (
					"vb".equals(tmpParam) || 
					"vbnet".equals(tmpParam) 
					) {
				tmpMode = "vb";
			}
			else if (
					"xml".equals(tmpParam) || 
					"xhtml".equals(tmpParam) || 
					"xslt".equals(tmpParam) || 
					"html".equals(tmpParam)  
					) {
				tmpMode = "xml";
			}
		}
		
		return "brush: " + tmpMode + "; ";
	}


}
