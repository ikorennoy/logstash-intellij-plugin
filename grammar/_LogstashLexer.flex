package com.github.redfoos.logstash;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.redfoos.logstash.psi.LogstashTypes.*;

%%

%{
  public _LogstashLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _LogstashLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R

WHITE_SPACE=[ \t\r\n]+
DOUBLE_QUOTED_STRING=(\"([^\\\"]|\\.)*\")
SINGLE_QUOTED_STRING=('([^\\']|\\.)*')
BAREWORD=[A-Za-z_][A-Za-z0-9_-]*
REGEXP=(\/([^\\/]|\\.)*\/)
NUMBER=(-?[0-9]+(\.[0-9]*)?)
COMMENT=([ \t\n\x0B\f\r]?#[^\r\n]*\r?\n?)+

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return WHITE_SPACE; }

  "input"                     { return INPUT_PLUGIN_TYPE; }
  "filter"                    { return FILTER_PLUGIN_TYPE; }
  "output"                    { return OUTPUT_PLUGIN_TYPE; }
  "if"                        { return IF_OPERATOR; }
  "else"                      { return ELSE_OPERATOR; }
  "in"                        { return IN_OPERATOR; }
  "not"                       { return NOT_OPERATOR; }
  "("                         { return LPARENTH; }
  ")"                         { return RPARENTH; }
  "["                         { return LBRACKET; }
  "]"                         { return RBRACKET; }
  "{"                         { return LBRACE; }
  "}"                         { return RBRACE; }
  "=>"                        { return RIGHT_ARROW; }
  ","                         { return COMMA; }
  "=="                        { return EQUALS; }
  "!="                        { return NOT_EQUALS; }
  "<="                        { return LESS_THAN_OR_EQUAL; }
  ">="                        { return GREATER_THAN_OR_EQUAL; }
  "<"                         { return LESS_THAN; }
  ">"                         { return GREATER_THAN; }
  "=~"                        { return REGEXP_EQUAL; }
  "!~"                        { return REGEXP_NOT_EQUAL; }
  "and"                       { return AND_OPERATOR; }
  "or"                        { return OR_OPERATOR; }
  "xor"                       { return XOR_OPERATOR; }
  "nand"                      { return NAND_OPERATOR; }
  "!"                         { return EXCLAMATION_MARK; }

  {DOUBLE_QUOTED_STRING}      { return DOUBLE_QUOTED_STRING; }
  {SINGLE_QUOTED_STRING}      { return SINGLE_QUOTED_STRING; }
  {BAREWORD}                  { return BAREWORD; }
  {REGEXP}                    { return REGEXP; }
  {NUMBER}                    { return NUMBER; }
  {COMMENT}                   { return COMMENT; }

}

[^] { return BAD_CHARACTER; }
