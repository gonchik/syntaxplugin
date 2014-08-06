package ut.jira.plugin.syntaxhighlighter.macro;

import java.util.List;

import jira.plugin.syntaxhighlighter.macro.SyntaxHighlighterMacro;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SyntaxHighlighterMacroUnitTest
{
    @Test
    public void testExpandRangesWithOnlyRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro(null, null);
    	List<Integer> ret = macro.expandRanges("[1-3]");
    	
		assertEquals(3, ret.size());
		assertEquals(1, ret.get(0).intValue());
		assertEquals(2, ret.get(1).intValue());
		assertEquals(3, ret.get(2).intValue());
    }
    
    @Test
    public void testExpandRangesWithoutRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro(null, null);
    	List<Integer> ret = macro.expandRanges("[1,2]");
    	
        assertEquals(2, ret.size());
        assertEquals(1, ret.get(0).intValue());
        assertEquals(2, ret.get(1).intValue());
    }
    
    @Test
    public void testExpandRangesWithMultipleRanges()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro(null, null);
    	List<Integer> ret = macro.expandRanges("[1-3,5-7]");
    	
        assertEquals(6, ret.size());
        assertEquals(1, ret.get(0).intValue());
        assertEquals(2, ret.get(1).intValue());
        assertEquals(3, ret.get(2).intValue());
        assertEquals(5, ret.get(3).intValue());
        assertEquals(6, ret.get(4).intValue());
        assertEquals(7, ret.get(5).intValue());
    }
    
    @Test
    public void testExpandRangesWithSequencesAndRanges()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro(null, null);
    	List<Integer> ret = macro.expandRanges("[1,2-3,4,5-7]");
    	
        assertEquals(7, ret.size());
        assertEquals(1, ret.get(0).intValue());
        assertEquals(2, ret.get(1).intValue());
        assertEquals(3, ret.get(2).intValue());
        assertEquals(4, ret.get(3).intValue());
        assertEquals(5, ret.get(4).intValue());
        assertEquals(6, ret.get(5).intValue());
        assertEquals(7, ret.get(6).intValue());
    }
    
    @Test
    public void testRangeToSequenceWithCorrectRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro(null, null);
    	List<Integer> ret;
    	
    	ret = macro.rangeToSequence("1-2");
        assertEquals(2, ret.size());
        assertEquals(1, ret.get(0).intValue());
        assertEquals(2, ret.get(1).intValue());

    	ret = macro.rangeToSequence("1-3");
        assertEquals(3, ret.size());
        assertEquals(1, ret.get(0).intValue());
        assertEquals(2, ret.get(1).intValue());
        assertEquals(3, ret.get(2).intValue());

    	ret = macro.rangeToSequence("8-11");
        assertEquals(4, ret.size());
        assertEquals(8, ret.get(0).intValue());
        assertEquals(9, ret.get(1).intValue());
        assertEquals(10, ret.get(2).intValue());
        assertEquals(11, ret.get(3).intValue());
    }
    
    @Test
    public void testRangeToSequenceWithEmptyRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro(null, null);
    	List<Integer> ret = macro.expandRanges("");
    	
        assertTrue(ret.isEmpty());
    }
    
    @Test
    public void testRangeToSequenceWithIllegalRange()
    {
    	SyntaxHighlighterMacro macro = new SyntaxHighlighterMacro(null, null);
    	List<Integer> ret;
    	
    	//start==end
    	ret = macro.rangeToSequence("1-1");
    	assertEquals(1, ret.size());
    	assertEquals(1, ret.get(0).intValue());
    	
    	//end>start
    	ret = macro.expandRanges("2-1");
    	assertTrue(ret.isEmpty());

    	//illegal chars
    	ret = macro.expandRanges("2-y");
    	assertTrue(ret.isEmpty());
    }
    
}