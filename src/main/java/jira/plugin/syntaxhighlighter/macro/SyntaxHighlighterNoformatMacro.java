package jira.plugin.syntaxhighlighter.macro;

import java.util.Map;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.plugin.PluginController;

public class SyntaxHighlighterNoformatMacro extends SyntaxHighlighterMacro {

	private static final String STANDARD_JIRA_NOFORMAT_MACRO = "com.atlassian.jira.plugin.system.renderers.wiki.macros:noformat";

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

	
    /**
     * Called when JIRA Syntax Highlighter {noformat} macro is being disabled or removed. Enables JIRA standard 
     * Wiki Renderer Macro Plugin for {noformat}.
     * 
     * @throws Exception
     */
	@Override
	public void destroy() throws Exception {
		PluginController tmpPluginController = ComponentAccessor.getComponent(PluginController.class);
		tmpPluginController.enablePluginModule(STANDARD_JIRA_NOFORMAT_MACRO);
	}

	/**
	 * Called when JIRA Syntax Highlighter {code} macro has been enabled. Disables JIRA standard Wiki 
	 * Renderer Macro Plugin for {noformat}.
	 * 
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		PluginController tmpPluginController = ComponentAccessor.getComponent(PluginController.class);
		tmpPluginController.disablePluginModule(STANDARD_JIRA_NOFORMAT_MACRO);
	}
	
	
}
