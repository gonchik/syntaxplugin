package syntaxhighlighter.brush.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.RegExpRule;

/**
 * Gherkin brush.
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class BrushGherkin extends Brush {

  public BrushGherkin() {
    super();

	// Based on highlighting from https://github.com/cucumber/gherkin-syntax-highlighters/blob/gh-pages/highlight.js
	// Syntax explained at http://docs.behat.org/guides/1.gherkin.html
    
    List<RegExpRule> _regExpRuleList = new ArrayList<>();
    _regExpRuleList.add(new RegExpRule(RegExpRule.singleLinePerlComments, "comments")); // single line comments with #
    _regExpRuleList.add(new RegExpRule("(['\\\"]{3})([^(['\\\"]{3})])*?(['\\\"]{3})", Pattern.MULTILINE, "string"));
    _regExpRuleList.add(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // strings
    _regExpRuleList.add(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
    _regExpRuleList.add(new RegExpRule("@.*$", Pattern.MULTILINE, "color1")); // @tags
    _regExpRuleList.add(new RegExpRule("^\\s*(But |And |Then |When |Given |Scenarios|Examples|Scenario Template|Scenario Outline|Scenario|Background|Feature)", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "keyword")); // english

    setRegExpRuleList(_regExpRuleList);

    setCommonFileExtensionList(Arrays.asList("gherkin"));
  }
}
