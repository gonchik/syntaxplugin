package jira.plugin.syntaxhighlighter.macro;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.plugin.PluginController;

public class SyntaxHighlighterInstallationSupport implements InitializingBean, DisposableBean {

	private static final String STANDARD_JIRA_CODE_MACRO = "com.atlassian.jira.plugin.system.renderers.wiki.macros:code";
	private static final String STANDARD_JIRA_NOFORMAT_MACRO = "com.atlassian.jira.plugin.system.renderers.wiki.macros:noformat";
	
	private final PluginController pluginController;
	
	public SyntaxHighlighterInstallationSupport(PluginController pc){
		this.pluginController = pc; 
	}
	
    /**
     * Called when JIRA Syntax Highlighter is being disabled or removed. Enables JIRA standard 
     * Wiki Renderer Macro Plugin for {code} and {noformat}.
     * 
     * @throws Exception
     */
	public void destroy() throws Exception {
		pluginController.enablePluginModule(STANDARD_JIRA_CODE_MACRO);
		pluginController.enablePluginModule(STANDARD_JIRA_NOFORMAT_MACRO);		
	}

	/**
	 * Called when JIRA Syntax Highlighter has been enabled. Disables JIRA standard Wiki Renderer 
	 * Macro Plugin for {code} and {noformat}.
	 * 
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		pluginController.disablePluginModule(STANDARD_JIRA_CODE_MACRO);
		pluginController.disablePluginModule(STANDARD_JIRA_NOFORMAT_MACRO);
	}

}
