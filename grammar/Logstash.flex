package com.github.redfoos.logstash;

import com.github.redfoos.logstash.psi.LogstashTypes;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.redfoos.logstash.psi.LogstashTypes.*;

%%

%class LogstashLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

NEWLINE=(\R( \t)*)
WHITE_SPACE=[ \t\x0B\f]+
END_OF_LINE_COMMENT=#.*
IDENTIFIER=(@ | [:jletter:]) ([:jletter:] | [:jletterdigit:] | -)*
NUMBER=-?[0-9]+(.[0-9]+)?

STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
LOGSTASH_BLOCK_NAME=("input" | "filter" | "output")
REGEXP=\/.*\/

%state WS_MATTERS
%state LOOK_FOR_CTRANS

%%

<YYINITIAL>    {END_OF_LINE_COMMENT}    { yybegin(YYINITIAL); return COMMENT; }


"("                                     { yybegin(YYINITIAL); return LPARENTH; }
")"                                     { yybegin(YYINITIAL); return RPARENTH; }
"{"                                     { yybegin(YYINITIAL); return LBRACE; }
"}"                                     { yybegin(YYINITIAL); return RBRACE; }
"["                                     { yybegin(YYINITIAL); return LBRACKET; }
"]"                                     { yybegin(YYINITIAL); return RBRACKET; }
","                                     { yybegin(YYINITIAL); return COMMA; }
"=>"                                    { yybegin(YYINITIAL); return RIGHTARROW; }
if                                      { yybegin(YYINITIAL); return IF_TOK; }
else                                    { yybegin(YYINITIAL); return ELSE_TOK; }
and                                     { yybegin(YYINITIAL); return AND; }
in                                      { yybegin(YYINITIAL); return IN; }
not                                     { yybegin(YYINITIAL); return NOT; }
or                                      { yybegin(YYINITIAL); return OR; }
xor                                     { yybegin(YYINITIAL); return XOR; }
nand                                    { yybegin(YYINITIAL); return NAND; }
"=="                                    { yybegin(YYINITIAL); return EQUAL; }
"!="                                    { yybegin(YYINITIAL); return NEQUAL; }
"<="                                    { yybegin(YYINITIAL); return LESS_OR_EQUAL; }
">="                                    { yybegin(YYINITIAL); return MORE_OR_EQUAL; }
"<"                                     { yybegin(YYINITIAL); return LESS; }
">"                                     { yybegin(YYINITIAL); return MORE; }
"=~"                                    { yybegin(YYINITIAL); return REGEXPEQUAL; }
"!~"                                    { yybegin(YYINITIAL); return REGEXPNEQUAL; }
"!"                                     { yybegin(YYINITIAL); return LOGICAL_NOT; }
{REGEXP}                                { yybegin(YYINITIAL); return REGEXP; }
{STRING}                                { yybegin(YYINITIAL); return STRING; }
{LOGSTASH_BLOCK_NAME}                   { yybegin(YYINITIAL); return PLUGIN_BLOCK; }
{IDENTIFIER}                            { yybegin(YYINITIAL); return IDENTIFIER; }
{NUMBER}                                { yybegin(YYINITIAL); return NUMBER; }
({NEWLINE}|{WHITE_SPACE})+              { yybegin(YYINITIAL); return WHITE_SPACE; }
[^]                                     { yybegin(YYINITIAL); return BAD_CHARACTER; }
