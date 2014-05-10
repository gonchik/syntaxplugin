;(function()
{
	// CommonJS
	SyntaxHighlighter = SyntaxHighlighter || (typeof require !== 'undefined'? require('shCore').SyntaxHighlighter : null);

	function Brush()
	{
		// Based on highlighting from https://github.com/cucumber/gherkin-syntax-highlighters/blob/gh-pages/highlight.js
		// Syntax explained at http://docs.behat.org/guides/1.gherkin.html

		this.regexList = [
			{ regex: SyntaxHighlighter.regexLib.singleLinePerlComments,	css: 'comments' },		// #comments 
			{ regex: /(['\"]{3})([^\1])*?\1/gm, 						css: 'string' },		// multi-line strings like in python with """
			{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },		// strings
			{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },		// strings
			{ regex: /@.*$/gmi,											css: 'color1' },		// @tags
			{ regex: /^\s*(But |And |Then |When |Given |Scenarios|Examples|Scenario Template|Scenario Outline|Scenario|Background|Feature)/gmi,	css: 'keyword'  } // english
		];
	};

	Brush.prototype	= new SyntaxHighlighter.Highlighter();
	Brush.aliases	= ['gherkin'];

	SyntaxHighlighter.brushes.Gherkin = Brush;

	// CommonJS
	typeof(exports) != 'undefined' ? exports.Brush = Brush : null;
})();
