JIRA Syntax Highlighter PlugIn
================================

![JIRA Syntax Highlighter Banner](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/syntaxplugin_banner_460x225.jpg)

The JIRA Syntax Highlighter PlugIn replaces the standard formatting macros {noformat} and {code}. It gives you a more advanced layout and support for additional languages like C#, PHP, Ruby, C++ etc. The plugin solves 50 votes for [JRA-21067](https://jira.atlassian.com/browse/JRA-21067), [JRA-23604](https://jira.atlassian.com/browse/JRA-23604) and [JRA-25703](https://jira.atlassian.com/browse/JRA-25703).

[![Additional languages](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Additional_languages_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Additional_languages.png)
[![Highlight multiple lines](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Highlight_multiple_lines_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Highlight_multiple_lines.png)
[![Noformat layout](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Highlight_lines_and_horizontal_scrolling_in_noformat_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Highlight_lines_and_horizontal_scrolling_in_noformat.png)

Syntax highlighting is available in standard view issue screen and in preview mode during create, edit or comment of an issue (see screenshots).

[![Edit issue with macro syntax](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Edit_issue_with_macro_syntax_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Edit_issue_with_macro_syntax.png)
[![Edit issue with preview](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Edit_issue_with_preview_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Edit_issue_with_preview.png)

It is also available in emails, activity streams and JIRA Agile planning and work mode.

[![JIRA Agile planning mode](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Syntax_highlighting_in_Greenhopper_Rapidboard_planning_mode_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Syntax_highlighting_in_Greenhopper_Rapidboard_planning_mode.png)
[![JIRA Agile Rapidboard](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Syntax_highlighting_in_Greenhopper_Rapidboard_Work_mode_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/Syntax_highlighting_in_Greenhopper_Rapidboard_Work_mode.png)

The following languages are supported.

*    Plain text renderer {code} resp. {noformat}
*    Java {code:java}
*    JavaScript {code:js}
*    JavaFX {code:javafx}
*    SQL {code:sql}
*    CSS {code:css}
*    XML {code:xml}, XSLT {code:xslt}, XHTML {code:xhtml}, HTML {code:html}
*    PHP {code:php}
*    Ruby {code:ruby}
*    Perl {code:perl}
*    C# {code:c#} or {code:csharp}
*    C+, C {code:c+} or {code:cpp} resp. {code:c}
*    VB {code:vb}, VB.NET {code:vbnet}
*    Python {code:py} or {code:python}
*    Pascal {code:pas} or {code:pascal}, Delphi {code:delphi}
*    Objective-C {code:objc} or {code:obj-c} 
*    Scala {code:scala}
*    D {code:d} 
*    Bash {code:bash} resp. Shell {code:sh}
*    Diff {code:diff}

Planned for next release

*    Tcl {code:tcl} (has been part of version 1.9.x)
*    Gherkin {code:gherkin} (has been part of version 1.9.x)
*    Puppet {code:puppet} or {code:pp}

Also highlighting of single or multiple lines is supported by using e.g.

*    {code:java|highlight=11}
*    {noformat:highlight=4,8-10} 

Line numbers can be switched on, collapse, starting line number or title can be set e.g.

*    {code:css|linenumbers|title=general.css file from root folder|collapse}
*    {code:sql|firstline=10}

The JIRA Syntax Highlighter PlugIn is based on [SyntaxHighligher by Alex Gorbatchev](http://alexgorbatchev.com/SyntaxHighlighter) and [Chan Wai Shing](https://code.google.com/p/java-syntax-highlighter/). JIRA macro plugin classes have been added and also Java classes and templates for HTML output.

JIRA Syntax Highlighter PlugIns is released under both MIT license and GNU General Public License (GPL) Version 3.

Known Issues
------------

Some minor layout issues in Outlook 2003 and Outlook 2010.


Installation
------------

For installation please add the plugin using the plugin manager or install it manually. Then you need to deactive JIRAs standard macros for code and noformat:   

* Go to "Administration" > "Plugins" resp. "Manage Plugins" or "Manage Add-ons"
* Scroll down to "System Plugins" resp. "System Add-ons" section and click on "Show System Plugins" / "Show System Add-ons". 
* Then scroll down and click on "Wiki Renderer Macro Plugin" to show details of this plugin
* Then click on "Manage plugin modules" resp. "7 of 8 modules activated" to show details about plugin modules. 
* Now deactivate "noformat" and "code" modules (see screenshot).

If you don't deactivate JIRAs standard macros for code and noformat as described above, you will see the error message "Unable to find source-code formatter for language..." when using one of the new programming languages supported by JIRA Syntax Highlighter like Phython, C# etc. resp. the new layout is not used.

[![JIRA with standard noformat and code macros disabled](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/JIRA_5.0_with_standard_noformat_and_code_macros_disabled_thumb.png)](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/docs/JIRA_5.0_with_standard_noformat_and_code_macros_disabled.png)

If not done already you need to switch on the Wiki Style Renderer in Field Configurations for the corresponding text fields like Description and Comment.

*    [Specifying Field Behaviour](http://confluence.atlassian.com/display/JIRA/Specifying+Field+Behaviour#SpecifyingFieldBehaviour-ChangingaFieldsRenderer)
*    [Configuring Renderers](http://confluence.atlassian.com/display/JIRA/Configuring+Renderers)

[![Link to Marketplace](https://bitbucket.org/hski/syntaxplugin-public/raw/master/images/marketplace_available_dark_180x80.png)](https://marketplace.atlassian.com/plugins/jira.plugin.syntaxhighlighter.macro.syntaxplugin)
