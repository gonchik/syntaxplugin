package syntaxhighlighter.brush.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.RegExpRule;

/**
 * Puppet brush.
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class BrushPuppet extends Brush {

  public BrushPuppet() {
    super();

	// Contributed by Eric Sorenson https://bitbucket.org/hski/syntaxplugin-public/pull-request/7/add-support-for-puppet-dsl-syntax/diff#chg-src/main/resources/scripts/shBrushPuppet.js
	// Based on Puppet 4 language spec - https://github.com/puppetlabs/puppet-specifications/tree/master/language
	// and Vim syntax for puppet - https://github.com/puppetlabs/puppet/blob/master/ext/vim/syntax/puppet.vim
    
    String keywords = "and case class default define else elsif if in inherits node or unless " +
    		"type file function private attr";
    
    String builtins = "false true undef present absent purged latest installed running stopped mounted unmounted " +
			"role configured file directory link";

    List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
    _regExpRuleList.add(new RegExpRule(RegExpRule.singleLinePerlComments, "comments")); // one line comments
    _regExpRuleList.add(new RegExpRule(RegExpRule.multiLineCComments, "comments")); // multiline comments
    _regExpRuleList.add(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // strings
    _regExpRuleList.add(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
    _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword"));
    _regExpRuleList.add(new RegExpRule(getKeywords(builtins), Pattern.MULTILINE, "functions"));
    _regExpRuleList.add(new RegExpRule("\\$[\\{\\}\\w_:]+", Pattern.MULTILINE, "variable"));

    setRegExpRuleList(_regExpRuleList);

    setCommonFileExtensionList(Arrays.asList("puppet"));
  }
}
