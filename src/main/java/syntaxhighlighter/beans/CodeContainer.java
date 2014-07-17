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
	private boolean hideLineNum;
	private int firstLine;
	
	public CodeContainer() {
		codeRows = new Vector<CodeRow>();
		firstLine = 1;
		hideLineNum = true;
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

	public boolean isHideLineNum() {
		return hideLineNum;
	}

	public void setHideLineNum(boolean hideLineNum) {
		this.hideLineNum = hideLineNum;
	}

	public int getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
	}

}
