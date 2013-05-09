package jira.plugin.syntaxhighlighter.macro;

import java.util.Map;

import com.atlassian.jira.ComponentManager;
import com.atlassian.plugin.webresource.UrlMode;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;

public class SyntaxHighlighterMacro extends BaseMacro {

	private static final String HIGHLIGHT = "highlight";

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
		tmpBuffer.append("<div style='margin-left: 20px;'>");
		tmpBuffer.append("<pre class='brush: " + getBrush(parameters) + "; " + getHighlight(parameters) + "toolbar: false;'>");
		tmpBuffer.append(body);
		tmpBuffer.append("</pre>");
		tmpBuffer.append("<img onload='SyntaxHighlighter.highlight();' style='display:none;' " +
				" src='" + getBlankImageUrl() + "'/>");
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
			return HIGHLIGHT + " : " + parameters.get(HIGHLIGHT) + "; ";
		} else {
			return "";
		}
	}	
	
	@SuppressWarnings("rawtypes")
	public String getBrush(Map parameters) {
		
		String tmpMode = "plain";
		
		if (parameters.containsKey("0")) {
			String tmpParam = (String) parameters.get("0");
			if ( 
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
		
		return tmpMode;
	}


}
