// Copyright (c) 2011 Chan Wai Shing
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
package syntaxhighlighter.parser;

/**
 * Matched result, it will be generated when parsing the content.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class MatchResult {

  /**
   * The position in the document for this matched result.
   */
  private int offset;
  /**
   * The length of the matched result.
   */
  private int length;
  /**
   * The style key for this matched result, see {@link syntaxhighlighter.theme}.
   */
  private String styleKey;

  /**
   * Constructor.
   * 
   * @param offset the position in the document for this matched result
   * @param length the length of the matched result.
   * @param styleKey the style key for this matched result, cannot be null, see 
   * {@link syntaxhighlighter.theme}
   */
  protected MatchResult(int offset, int length, String styleKey) {
    if (styleKey == null) {
      throw new NullPointerException("argument 'styleKey' cannot be null");
    }
    this.offset = offset;
    this.length = length;
    this.styleKey = styleKey;
  }

  /**
   * The position in the document for this matched result.
   * @return the offset in the document
   */
  public int getOffset() {
    return offset;
  }

  /**
   * The position in the document for this matched result.
   * @param offset the offset in the document
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }

  /**
   * The length of the matched result.
   * @return the length
   */
  public int getLength() {
    return length;
  }

  /**
   * The length of the matched result.
   * @param length the length
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * The style key for this matched result, see {@link syntaxhighlighter.theme}.
   * @return the style key
   */
  public String getStyleKey() {
    return styleKey;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[");
    sb.append(offset);
    sb.append(", ");
    sb.append(length);
    sb.append(", ");
    sb.append(styleKey);
    sb.append("]");

    return sb.toString();
  }
}