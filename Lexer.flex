/*
 * name: haris ahmad
 * email: hka5236@psu.edu
 * class: cmpsc 470-001
 */

%%

%class Lexer
%byaccj
%line
%column

%{

  public Parser parser;

  public Lexer(java.io.Reader r, Parser parser) {
    this(r);
    this.parser = parser;
  }

  public int getLineNumber() {
    return this.yyline;
  }

  public int getColumnNumber() {
    return this.yycolumn;
  }

%}

num          = [0-9]+("."[0-9]+)?
identifier   = [a-zA-Z][a-zA-Z0-9_]*
newline      = \n
whitespace   = [ \t\r]+
linecomment  = "//".*
blockcomment = "/*"[^]*"*/"
boolean = "true" | "false"

%%

// keywords
"func"                              { parser.yylval = new ParserVal(new Token(yytext())); return Parser.FUNC        ; }
"return"                            { parser.yylval = new ParserVal(new Token(yytext())); return Parser.RETURN      ; }
"var"                               { parser.yylval = new ParserVal(new Token(yytext())); return Parser.VAR         ; }
"if"                                { parser.yylval = new ParserVal(new Token(yytext())); return Parser.IF          ; }
"then"                              { parser.yylval = new ParserVal(new Token(yytext())); return Parser.THEN        ; }
"else"                              { parser.yylval = new ParserVal(new Token(yytext())); return Parser.ELSE        ; }
"begin"                             { parser.yylval = new ParserVal(new Token(yytext())); return Parser.BEGIN       ; }
"end"                               { parser.yylval = new ParserVal(new Token(yytext())); return Parser.END         ; }
"while"                             { parser.yylval = new ParserVal(new Token(yytext())); return Parser.WHILE       ; }
"new"                               { parser.yylval = new ParserVal(new Token(yytext())); return Parser.NEW         ; }
"num"                               { parser.yylval = new ParserVal(new Token(yytext())); return Parser.NUM         ; }
"bool"                              { parser.yylval = new ParserVal(new Token(yytext())); return Parser.BOOL        ; }
"print"                             { parser.yylval = new ParserVal(new Token(yytext())); return Parser.PRINT       ; }
"size"                              { parser.yylval = new ParserVal(new Token(yytext())); return Parser.SIZE        ; }
"and"                               { parser.yylval = new ParserVal(new Token(yytext())); return Parser.AND         ; }
"or"                                { parser.yylval = new ParserVal(new Token(yytext())); return Parser.OR          ; }
"not"                               { parser.yylval = new ParserVal(new Token(yytext())); return Parser.NOT         ; }

// symbols
"("                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.LPAREN      ; }
")"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.RPAREN      ; }
"["                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.LBRACKET    ; }
"]"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.RBRACKET    ; }
"."                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.DOT         ; }
":="                                { parser.yylval = new ParserVal(new Token(yytext())); return Parser.ASSIGN      ; }
"::"                                { parser.yylval = new ParserVal(new Token(yytext())); return Parser.TYPEOF      ; }
"+"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.ADD         ; }
"-"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.SUB         ; }
"*"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.MUL         ; }
"/"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.DIV         ; }
"%"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.MOD         ; }
"<"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.LT          ; }
">"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.GT          ; }
"<="                                { parser.yylval = new ParserVal(new Token(yytext())); return Parser.LE          ; }
">="                                { parser.yylval = new ParserVal(new Token(yytext())); return Parser.GE          ; }
"="                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.EQ          ; }
"<>"                                { parser.yylval = new ParserVal(new Token(yytext())); return Parser.NE          ; }
";"                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.SEMI        ; }
","                                 { parser.yylval = new ParserVal(new Token(yytext())); return Parser.COMMA       ; }

// literals
{boolean}                           { parser.yylval = new ParserVal(new Token(yytext())); return Parser.BOOL_LIT    ; }
{identifier}                        { parser.yylval = new ParserVal(new Token(yytext())); return Parser.IDENT       ; }
{num}                               { parser.yylval = new ParserVal(new Token(yytext())); return Parser.NUM_LIT     ; }
{linecomment}                       { System.out.print("")                                                          ; }
{newline}                           { System.out.print("");                                                         ; }
{whitespace}                        { System.out.print("")                                                          ; }
{blockcomment}                      { System.out.print("");                                                         ; }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '" + yytext() + "' at " + (yyline + 1) + ":" + (yycolumn+1) + ".\n"); return -1; }