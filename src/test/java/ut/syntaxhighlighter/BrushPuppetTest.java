package ut.syntaxhighlighter;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import syntaxhighlighter.SyntaxHighlighterParserUtil;
import syntaxhighlighter.beans.CodeContainer;
import syntaxhighlighter.beans.CodeRow;
import syntaxhighlighter.brush.custom.BrushPuppet;

public class BrushPuppetTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
	    String aPuppetString = 
		"  class foo::bar {                 # 'class' is a keyword\n" +
	    "    # single line comment\n" +
	    "    /*  This one tests a multi-line\n" +
	    "       C-style winged comment      */\n" +
	    "    file { \"/etc/passwd\": # 'file' should be a keyword, label is double-quoted string\n" +
	    "      ensure  => present, # 'present' is in 'builtins'\n" +
	    "      owner   => 'root',  # a single-quoted string\n" +
	    "      mode    => $mode,   \n" +
	    "      group   => ${foo::params::group},  # should also trigger 'variable'\n" +
	    "      content => \"This content includes ${foo::params::interpolation}\"\n" +
	    "    }\n" +
	    "  }\n";
	    			    
	    CodeContainer container = SyntaxHighlighterParserUtil.brush(aPuppetString, new BrushPuppet());
	    
	    List<CodeRow> codeRows = container.getCodeRows();
	    CodeRow codeRow;		
		
	    assertEquals(12, codeRows.size());
	    
	    codeRow = codeRows.get(4);
	    assertEquals(6, codeRow.getCode().size());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(0).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_KEYWORD, codeRow.getCode().get(1).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(2).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(3).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(4).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_COMMENTS, codeRow.getCode().get(5).getStyle());

	}

}
