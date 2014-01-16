= JIRA Syntax Highlighter PlugIn

{{images/syntaxplugin_banner_460x225.jpg}}

The JIRA Syntax Highlighter PlugIn replaces the standard formatting macros {noformat} and {code}. It gives you a more advanced layout and support for additional languages like C#, PHP, Ruby, C++ etc. The plugin solves 50 votes for [[https://jira.atlassian.com/browse/JRA-21067|JRA-21067]], [[https://jira.atlassian.com/browse/JRA-23604|JRA-23604]] and [[https://jira.atlassian.com/browse/JRA-25703|JRA-25703]].

[[docs/Additional_languages.png|{{docs/Additional_languages_thumb.png}}]] [[docs/Highlight_multiple_lines.png|{{docs/Highlight_multiple_lines_thumb.png}}]] [[docs/Highlight_lines_and_horizontal_scrolling_in_noformat.png|{{docs/Highlight_lines_and_horizontal_scrolling_in_noformat_thumb.png}}]] 

Syntax highlighting is available in standard view issue screen and in preview mode during create, edit or comment of an issue (see screenshots).

[[docs/Edit_issue_with_macro_syntax.png|{{docs/Edit_issue_with_macro_syntax_thumb.png}}]] [[docs/Edit_issue_with_preview.png|{{docs/Edit_issue_with_preview_thumb.png}}]]

It is also available in Greenhopper Rapidboard planning and work mode.

[[docs/Syntax_highlighting_in_Greenhopper_Rapidboard_planning_mode.png|{{docs/Syntax_highlighting_in_Greenhopper_Rapidboard_planning_mode_thumb.png}}]] [[docs/Syntax_highlighting_in_Greenhopper_Rapidboard_Work_mode.png|{{docs/Syntax_highlighting_in_Greenhopper_Rapidboard_Work_mode_thumb.png}}]]


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
*    Tcl {code:tcl} 
*    Objective-C {code:objc} or {code:obj-c} 
*    Scala {code:scala}
*    D {code:d} 
*    Bash {code:bash} resp. Shell {code:sh}
*    Diff {code:diff}

Also highlighting of single or multiple lines is supported by using e.g.

*    {code:java|highlight=11}
*    {noformat:highlight=[4,8-10]} 

Line numbers can be switched off, starting line number or title can be set e.g.

*    {code:css|hide-linenum|title=general.css file from root folder}
*    {code:sql|first-line=10}

The JIRA Syntax Highlighter PlugIn is based on [[http://alexgorbatchev.com/SyntaxHighlighter|SyntaxHighligher by Alex Gorbatchev]]. The plugin contains this JavaScript and CSS code plus some java classes for the integration into JIRA as a JIRA formatting macro plugin.

JIRA Syntax Highlighter PlugIns is released under both MIT license and GNU General Public License (GPL) Version 3.

== Known Issues

Syntax highlighting is not shown in activity streams on the JIRA Dashboard.


== Installation

For installation please add the plugin using the plugin manager or install it manually. Then you need to deactive JIRAs standard macros for code and noformat:   

* Go to "Administration" > "Plugins" resp. "Manage Plugins" or "Manage Add-ons"
* Scroll down to "System Plugins" resp. "System Add-ons" section and click on "Show System Plugins" / "Show System Add-ons". 
* Then scroll down and click on "Wiki Renderer Macro Plugin" to show details of this plugin
* Then click on "Manage plugin modules" resp. "7 of 8 modules activated" to show details about plugin modules. 
* Now deactivate "noformat" and "code" modules (see screenshot).

If you don't deactivate JIRAs standard macros for code and noformat as described above, you will see the error message "Unable to find source-code formatter for language..." when using one of the new programming languages supported by JIRA Syntax Highlighter like Phython, C# etc. resp. the new layout is not used.

[[docs/JIRA_5.0_with_standard_noformat_and_code_macros_disabled.png|{{docs/JIRA_5.0_with_standard_noformat_and_code_macros_disabled_thumb.png}}]] 

If not done already you need to switch on the Wiki Style Renderer in Field Configurations for the corresponding text fields like Description and Comment.

*    [[http://confluence.atlassian.com/display/JIRA/Specifying+Field+Behaviour#SpecifyingFieldBehaviour-ChangingaFieldsRenderer]]
*    [[http://confluence.atlassian.com/display/JIRA/Configuring+Renderers]]


[[https://marketplace.atlassian.com/plugins/jira.plugin.syntaxhighlighter.macro.syntaxplugin|{{images/marketplace_available_dark_180x80.png}}]] 