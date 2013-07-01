package ut.jira.plugin.syntaxhighlighter.macro;

import jira.plugin.syntaxhighlighter.macro.SyntaxHighlighterMacro;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SyntaxHighlighterMacroUnitTest
{
    @Test
    public void testExpandRangesWithOnlyRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro();
        assertEquals("", "[1,2,3]", macro.expandRanges("[1-3]"));
    }
    
    @Test
    public void testExpandRangesWithoutRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro();
        assertEquals("", "[1,2]", macro.expandRanges("[1,2]"));
    }
    
    @Test
    public void testExpandRangesWithMultipleRanges()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro();
        assertEquals("", "[1,2,3,5,6,7]", macro.expandRanges("[1-3,5-7]"));
    }
    
    @Test
    public void testExpandRangesWithSequencesAndRanges()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro();
        assertEquals("", "[1,2,3,4,5,6,7]", macro.expandRanges("[1,2-3,4,5-7]"));
    }
    
    @Test
    public void testRangeToSequenceWithCorrectRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro();
    	assertEquals("", "1,2", macro.rangeToSequence("1-2"));
    	assertEquals("", "1,2,3", macro.rangeToSequence("1-3"));
    	assertEquals("", "8,9,10,11,12,13", macro.rangeToSequence("8-13"));
    }
    
    @Test
    public void testRangeToSequenceWithEmptyRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro();
    	assertEquals("", "", macro.rangeToSequence(""));
    }
    
    @Test
    public void testRangeToSequenceWithIllegalRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro();
    	//start==end
    	assertEquals("", "1-1", macro.rangeToSequence("1-1"));
    	//end>start
    	assertEquals("", "2-1", macro.rangeToSequence("2-1"));
    	//illegal chars
    	assertEquals("", "1-y", macro.rangeToSequence("1-y"));
    }
    
}