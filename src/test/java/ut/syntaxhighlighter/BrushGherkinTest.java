package ut.syntaxhighlighter;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import syntaxhighlighter.SyntaxHighlighterParserUtil;
import syntaxhighlighter.beans.CodeContainer;
import syntaxhighlighter.beans.CodeRow;
import syntaxhighlighter.brush.custom.BrushGherkin;

public class BrushGherkinTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		String aGherkinString = "Feature: Feature\n" 
				+ "  Scenario Outline: A scenario    # A comment\n" 
				+ "    @tag @tagz\n"
				+ "    # Another comment\n" 
				+ "    Given I have 4 cukes in my \"big\" and 'Given' Given belly\n" 
				+ "      \"\"\"\n"
				+ "      A doc string with Given\n" 
				+ "      \"\"\"\n" 
				+ "    And a table:\n" 
				+ "      | with | some |\n" 
				+ "      | rows |      |\n";
	    			    
	    CodeContainer container = SyntaxHighlighterParserUtil.brush(aGherkinString, new BrushGherkin());
	    
	    List<CodeRow> codeRows = container.getCodeRows();
	    CodeRow codeRow;		
		
	    assertEquals(11, codeRows.size());
	    
	    codeRow = codeRows.get(1);
	    assertEquals(3, codeRow.getCode().size());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_KEYWORD, codeRow.getCode().get(0).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(1).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_COMMENTS, codeRow.getCode().get(2).getStyle());

	    codeRow = codeRows.get(4);
	    assertEquals(6, codeRow.getCode().size());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_KEYWORD, codeRow.getCode().get(0).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(1).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(2).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(3).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(4).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(5).getStyle());
	    
	    codeRow = codeRows.get(5);
	    assertEquals(2, codeRow.getCode().size());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(0).getStyle());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(1).getStyle());
	    codeRow = codeRows.get(6);
	    assertEquals(1, codeRow.getCode().size());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(0).getStyle());
	    codeRow = codeRows.get(7);
	    assertEquals(1, codeRow.getCode().size());
	    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(0).getStyle());

	}

}
