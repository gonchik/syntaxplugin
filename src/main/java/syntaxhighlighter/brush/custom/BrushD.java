package syntaxhighlighter.brush.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.RegExpRule;

/**
 * D brush.
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class BrushD extends Brush {

  public BrushD() {
    super();

    // Contributed by lindt https://github.com/alexgorbatchev/SyntaxHighlighter/pull/180
	// This is just for the very basic grammar

    String datatypes = "bool byte char creal dchar double float idouble ifloat int ireal " +
            "long real short ubyte ucent uint ulong ushort wchar wstring void " +
            "size_t sizediff_t";
    
    String keywords = "abstract alias align asm assert auto break case cast cdouble cent " +
            "cfloat const continue debug default delegate delete deprecated " +
            "export extern final finally function goto immutable import inout " +
            "invariant is lazy macro module new nothrow override package pragma " +
            "private protected public pure ref return shared short static super " +
            "synchronized template this throw typedef typeid typeof volatile " +
            "__FILE__ __LINE__ __gshared __traits __vector __parameters body " +
            "catch class do else enum for foreach foreach_reverse if in interface " +
            "mixin out scope struct switch try union unittest version while with";
    
    String functions = "assert";

    List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
    _regExpRuleList.add(new RegExpRule(RegExpRule.singleLineCComments, "comments")); // one line comments
    _regExpRuleList.add(new RegExpRule(RegExpRule.multiLineCComments, "comments")); // multiline comments
    _regExpRuleList.add(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // strings
    _regExpRuleList.add(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
    _regExpRuleList.add(new RegExpRule("^ *#.*", Pattern.MULTILINE, "preprocessor"));
    _regExpRuleList.add(new RegExpRule(getKeywords(datatypes), Pattern.MULTILINE, "keyword"));
    _regExpRuleList.add(new RegExpRule(getKeywords(functions), Pattern.MULTILINE, "functions"));
    _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword"));
    
    setRegExpRuleList(_regExpRuleList);

    setCommonFileExtensionList(Arrays.asList("d", "di"));
  }
}
