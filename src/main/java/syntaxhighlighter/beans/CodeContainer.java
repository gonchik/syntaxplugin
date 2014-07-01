package syntaxhighlighter.beans;

import java.util.List;
import java.util.Vector;

public class CodeContainer {

	private List<CodeRow> codeRows;
	private CodeRow lastCodeRow;
	
	public CodeContainer() {
		codeRows = new Vector<CodeRow>();
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

}
