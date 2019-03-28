package syntaxhighlighter.brush.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.RegExpRule;

/**
 * TCL brush.
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class BrushTcl extends Brush {

  public BrushTcl() {
    super();

    // According to http://www.tcl.tk/man/tcl/TclCmd/contents.htm
    String commands = "after append apply array bgerror binary break catch cd chan clock close concat "
    		+ "continue coroutine dde dict else elseif encoding eof error eval exec exit expr fblocked fconfigure fcopy file "
    		+ "fileevent filename flush for foreach format gets glob global history http if incr info interp join lappend "
    		+ "lassign lindex linsert list llength lmap load lrange lrepeat lreplace lreverse lsearch "
    		+ "lset lsort mathfunc mathop memory msgcat my namespace next nextto open package pid platform proc puts pwd re_syntax read "
    		+ "refchan regexp registry regsub rename return safe scan seek self set socket source split string subst "
    		+ "switch tailcall tcltest tell throw time tm trace transchan try unknown unload unset update uplevel upvar variable vwait while yield yieldto zlib";
    
    // See http://www.tcl.tk/man/tcl/TclCmd/tclvars.htm
    String variables = "argc argv argv0 auto_path env errorCode errorInfo tcl_interactive tcl_library "
    		+ "tcl_nonwordchars tcl_patchLevel tcl_pkgPath tcl_platform tcl_precision tcl_rcFileName "
    		+ "tcl_traceCompile tcl_traceExec tcl_wordchars tcl_version";
    
    // See http://www.tcl.tk/man/tcl/TclCmd/library.htm
    String procedures = "auto_execok auto_import auto_load auto_mkindex auto_qualify auto_reset "
    		+ "tcl_findLibrary parray tcl_endOfWord tcl_startOfNextWord tcl_startOfPreviousWord "
    		+ "tcl_wordBreakAfter tcl_wordBreakBefore";
        
    List<RegExpRule> _regExpRuleList = new ArrayList<>();
    _regExpRuleList.add(new RegExpRule(";\\s*#.*$", Pattern.MULTILINE, "comments")); // inline comments with ; # 
    _regExpRuleList.add(new RegExpRule("^\\s*#.*$", Pattern.MULTILINE, "comments")); // single line comments with #
    _regExpRuleList.add(new RegExpRule(RegExpRule.doubleQuotedString, "string")); 
    _regExpRuleList.add(new RegExpRule("\\$[A-Za-z]\\w*", Pattern.MULTILINE, "variable")); 

    // See http://www.tcl.tk/man/tcl/TclCmd/array.htm 
    _regExpRuleList.add(new RegExpRule("array (anymore |donesearch |exists |get |names |nextelement |set |size |startsearch |statistics |unset )", Pattern.MULTILINE, "keyword")); 

    // See http://www.tcl.tk/man/tcl/TclCmd/dict.htm
    _regExpRuleList.add(new RegExpRule("dict (append |create |exists |filter |for |get |incr |info |keys |lappend |merge |remove |replace |set |size |unset |update |values |with )", Pattern.MULTILINE, "keyword")); 

    // See http://www.tcl.tk/man/tcl/TclCmd/string.htm 
    _regExpRuleList.add(new RegExpRule("string (bytelength |compare |equal |first |index |is |last |length |map |match |range |repeat |replace |reverse |tolower |totitle |toupper|trim|trimleft|trimright|wordend|wordstart)", Pattern.MULTILINE, "keyword")); 
    
    _regExpRuleList.add(new RegExpRule(getKeywords(commands), Pattern.MULTILINE, "keyword"));
    _regExpRuleList.add(new RegExpRule(getKeywords(variables), Pattern.MULTILINE, "variable"));
    _regExpRuleList.add(new RegExpRule(getKeywords(procedures), Pattern.MULTILINE, "functions"));

    setRegExpRuleList(_regExpRuleList);
    setCommonFileExtensionList(Arrays.asList("tcl"));

  }
}
