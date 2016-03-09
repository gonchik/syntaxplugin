package syntaxhighlighter.beans;

import java.util.List;
import java.util.Vector;

/** 
 * Bean class representing a code block 
 * 
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class CodeContainer {

	private List<CodeRow> codeRows;
	private CodeRow lastCodeRow;
	private boolean showLineNums;
	private int firstLine;
	private boolean wrapLines;
	
	public CodeContainer() {
		codeRows = new Vector<CodeRow>();
		firstLine = 1;
		showLineNums = false;
		newCodeRow();
	}

	public void newCodeRow(){
		lastCodeRow = new CodeRow();
		codeRows.add(lastCodeRow);
	}
	
	public void addCode(String style, String text){
		Code newCode = new Code(style, text);
		lastCodeRow.addCode(newCode);
	}
	
	public List<CodeRow> getCodeRows(){
		return codeRows;
	}

	public boolean isShowLineNums() {
		return showLineNums;
	}

	public void setShowLineNums(boolean showLineNums) {
		this.showLineNums = showLineNums;
	}

	public int getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
	}

	public void setWrapLines(boolean wrapLines) {
		this.wrapLines = wrapLines;
	}

	public boolean isWrapLines() {
		return wrapLines;
	}


}
