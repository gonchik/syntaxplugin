package ut.syntaxhighlighter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import syntaxhighlighter.SyntaxHighlighterParserUtil;
import syntaxhighlighter.beans.CodeContainer;
import syntaxhighlighter.beans.CodeRow;
import syntaxhighlighter.brush.BrushJava;

/**
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class SyntaxHighlighterParserUtilTest {

  public SyntaxHighlighterParserUtilTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void test() {
    
    String aJavaString = 
    		"/* Some multiline comment\n" + 
    		" * which span over \n" +
    		" */\n" + 
    		"   Some multi line code without any\n" +
    		"   matching word or text\n" +
    		"String someString = \"An example comment: /* example */\";\n" +
			"// The comment around this code has been commented out.\n" +
			"// /*\n" +
			"some_code(\"an example string\", true);\n" +
			"// */\";\n" +
			"some text at \n" +
			"the end";
    
    CodeContainer container = SyntaxHighlighterParserUtil.brush(aJavaString, new BrushJava());
    
    List<CodeRow> codeRows = container.getCodeRows();
    CodeRow codeRow;

    assertEquals(12, codeRows.size());
    
    codeRow = codeRows.get(0);
    assertEquals(1, codeRow.getCode().size());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_COMMENTS, codeRow.getCode().get(0).getStyle());
    
    codeRow = codeRows.get(5);
    assertEquals(3, codeRow.getCode().size());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(0).getStyle());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(1).getStyle());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(2).getStyle());

    codeRow = codeRows.get(8);
    assertEquals(5, codeRow.getCode().size());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(0).getStyle());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_STRING, codeRow.getCode().get(1).getStyle());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(2).getStyle());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_KEYWORD, codeRow.getCode().get(3).getStyle());
    assertEquals(SyntaxHighlighterParserUtil.STYLE_PLAIN, codeRow.getCode().get(4).getStyle());

    codeRow = codeRows.get(9);
    assertEquals(1, codeRow.getCode().size());

  }
}
