package syntaxhighlighter.beans;

import java.util.List;
import java.util.Vector;

/** 
 * Bean class representing a code row in a code block
 * 
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class CodeRow {

	private List<Code> codeParts;
	private boolean highlighted;

	public CodeRow(){
		highlighted = false;
		codeParts = new Vector<Code>();
	}
	
	public void addCode(Code code){
		codeParts.add(code);
	}
	
	public List<Code> getCode(){
		return codeParts;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
}
