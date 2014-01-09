package jira.plugin.syntaxhighlighter.macro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.plugin.PluginController;

public class SyntaxHighlighterInstallationSupport implements InitializingBean, DisposableBean {

	private static final String STANDARD_JIRA_CODE_MACRO = "com.atlassian.jira.plugin.system.renderers.wiki.macros:code";
	private static final String STANDARD_JIRA_NOFORMAT_MACRO = "com.atlassian.jira.plugin.system.renderers.wiki.macros:noformat";
	
	private final PluginController pluginController;
	private static final Logger log = LoggerFactory.getLogger(SyntaxHighlighterInstallationSupport.class);
	
	public SyntaxHighlighterInstallationSupport(PluginController pc){
		this.pluginController = pc; 
	}
	
    /**
     * Called when the plugin is being disabled or removed.
     * @throws Exception
     */
	public void destroy() throws Exception {
		pluginController.enablePluginModule(STANDARD_JIRA_CODE_MACRO);
		pluginController.enablePluginModule(STANDARD_JIRA_NOFORMAT_MACRO);		
		log.info("JIRA Syntax Highlighter destroy called; JIRA standard Wiki Renderer Macros Plugin code and noformat modules re-enabled");
	}

	/**
	 * Called when the plugin has been enabled.
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		pluginController.disablePluginModule(STANDARD_JIRA_CODE_MACRO);
		pluginController.disablePluginModule(STANDARD_JIRA_NOFORMAT_MACRO);
		log.info("JIRA Syntax Highlighter enabled; JIRA standard Wiki Renderer Macros Plugin code and noformat modules disabled");
	}

}
