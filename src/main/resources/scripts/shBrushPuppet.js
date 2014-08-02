;(function()
{
	// CommonJS
	SyntaxHighlighter = SyntaxHighlighter || (typeof require !== 'undefined'? require('shCore').SyntaxHighlighter : null);

	function Brush()
	{
		// Contributed by Eric Sorenson.

		// Based on Puppet 4 language spec - https://github.com/puppetlabs/puppet-specifications/tree/master/language
		// and Vim syntax for puppet - https://github.com/puppetlabs/puppet/blob/master/ext/vim/syntax/puppet.vim
	
		var keywords =	'and case class default define else elsif if in inherits node or unless' +
						'type function private attr';
		var builtins =	'false true undef present absent purged latest installed running stopped mounted unmounted' +
						'role configured file directory link';

		this.regexList = [
			{ regex: SyntaxHighlighter.regexLib.singleLinePerlComments,	css: 'comments' },		// one line comments
			{ regex: SyntaxHighlighter.regexLib.multiLineCComments,		css: 'comments' },		// multiline comments
			{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },		// double quoted strings
			{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },		// single quoted strings
			{ regex: new RegExp(this.getKeywords(keywords), 'gm'),		css: 'keyword' },		// keywords
			{ regex: new RegExp(this.getKeywords(builtins), 'gm'),		css: 'color1' },		// builtins
			{ regex: /$[\{\}\w:]+/g,									css: 'variable bold'}   // variables, also with ${var} interpolation
			];

		this.forHtmlScript(SyntaxHighlighter.regexLib.aspScriptTags);
	};

	Brush.prototype	= new SyntaxHighlighter.Highlighter();
	Brush.aliases	= ['puppet', 'pp'];

	SyntaxHighlighter.brushes.Puppet = Brush;

	// CommonJS
	typeof(exports) != 'undefined' ? exports.Brush = Brush : null;
})();
