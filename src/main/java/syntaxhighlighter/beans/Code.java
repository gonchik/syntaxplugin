package syntaxhighlighter.beans;

/** 
 * Bean class representing a sniplet of code with a certain style e.g. plain, keyword, coments
 * 
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
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
