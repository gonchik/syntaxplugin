package syntaxhighlighter.beans;

public class Code {
	
	private String style;
	private String text;
	
	public Code(String style, String text){
		this.style = style;
		this.text = text;
	}

	public String getStyle() {
		return style;
	}

	public String getText() {
		return text;
	}
	
}
