//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 ".\Parser.y"
import java.io.*;
//#line 19 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ASSIGN=257;
public final static short OR=258;
public final static short AND=259;
public final static short NOT=260;
public final static short EQ=261;
public final static short NE=262;
public final static short LE=263;
public final static short LT=264;
public final static short GE=265;
public final static short GT=266;
public final static short ADD=267;
public final static short SUB=268;
public final static short MUL=269;
public final static short DIV=270;
public final static short MOD=271;
public final static short IDENT=272;
public final static short NUM_LIT=273;
public final static short BOOL_LIT=274;
public final static short BOOL=275;
public final static short NUM=276;
public final static short TYPEOF=277;
public final static short FUNC=278;
public final static short IF=279;
public final static short THEN=280;
public final static short ELSE=281;
public final static short WHILE=282;
public final static short PRINT=283;
public final static short RETURN=284;
public final static short BEGIN=285;
public final static short END=286;
public final static short LPAREN=287;
public final static short RPAREN=288;
public final static short LBRACKET=289;
public final static short RBRACKET=290;
public final static short VAR=291;
public final static short SEMI=292;
public final static short COMMA=293;
public final static short NEW=294;
public final static short DOT=295;
public final static short SIZE=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,   22,    3,    8,    8,    9,    9,
   10,    6,    6,    7,    7,    4,    4,    5,   13,   13,
   14,   14,   14,   14,   14,   14,   15,   15,   16,   17,
   18,   19,   20,   11,   11,   12,   12,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,   21,
};
final static short yylen[] = {                            2,
    1,    2,    0,    1,    0,   12,    1,    0,    3,    1,
    3,    1,    3,    1,    1,    2,    0,    5,    2,    0,
    1,    1,    1,    1,    1,    1,    4,    7,    3,    3,
    7,    5,    4,    1,    0,    3,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    3,    1,    1,    1,    4,    5,    4,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    2,    4,    0,    0,   15,   14,    0,
    0,    0,    0,    0,    0,    0,   10,   13,    0,    0,
    0,   11,   17,    9,    0,    0,   16,   20,    0,    0,
    0,    0,    0,    0,    0,    0,   17,    6,   19,   21,
   22,   23,   24,   25,   26,    0,    0,    0,    0,    0,
   54,   55,    0,    0,    0,    0,    0,    0,    0,   18,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   20,   20,   29,   30,    0,   27,    0,    0,    0,
    0,    0,   59,   52,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   40,   41,   42,    0,    0,
   33,    0,   56,    0,   58,    0,   20,   32,    0,    0,
   57,    0,   28,   31,
};
final static short yydgoto[] = {                          1,
    2,    4,    5,   25,   27,   10,   11,   15,   16,   17,
   89,   90,   30,   39,   40,   41,   42,   43,   44,   45,
   55,   28,
};
final static short yysindex[] = {                         0,
    0, -253, -246,    0,    0, -245, -267,    0,    0, -254,
 -233, -213, -230, -212, -211, -210,    0,    0, -267, -204,
 -213,    0,    0,    0, -209, -186,    0,    0, -189, -262,
 -267, -241,   89,   89,   89,   89,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -198,   89,   89,   89, -284,
    0,    0,   89, -267,  208,  183,   31,   45, -209,    0,
   63,  123,  302,   89,   89, -200,  167, -188,   89,   89,
   89,   89,   89,   89,   89,   89,   89,   89,   89,   89,
   89,    0,    0,    0,    0,   88,    0, -145, -166, -170,
  263,  137,    0,    0,   89,  291,  302,  193,  193,   83,
   83,   83,   83, -218, -218,    0,    0,    0,  264,  210,
    0,   89,    0,   89,    0,  153,    0,    0,   77,  263,
    0,  218,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,  124,    0,    0,    0,    0,    0,    0,    0,    0,
 -238, -163,    0,    0,    0, -161,    0,    0,    0,    0,
    0,    0,    0,    0,  226,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -195,
    0,    0,    0,    0,    0,    0,    0,    0,  234,    0,
    0,    0, -201, -160,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -158,
 -281,    0,    0,    0,    0,   -5,  -11,  -47,  -22, -112,
 -103,  -67,  -58, -159, -148,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -259,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   98,    0,  -13,   82,    0,    0,  117,
    0,    0,  -55,    0,    0,    0,    0,    0,    0,    0,
  -34,    0,
};
final static int YYTABLESIZE=573;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         56,
   57,   58,   64,   86,   65,   22,   37,    8,    9,   32,
   66,   37,   61,   62,   63,   47,   33,   46,   67,   34,
   35,   36,   37,   38,    3,    6,  109,  110,   36,   91,
   92,    7,   12,   36,   96,   97,   98,   99,  100,  101,
  102,  103,  104,  105,  106,  107,  108,   48,   12,   12,
   79,   80,   81,   12,   12,   13,   51,   51,   14,   18,
  116,  122,   53,   53,   19,   53,   53,   53,   53,   53,
   53,   53,   53,   53,   53,   53,   20,  119,   51,  120,
   23,   26,   21,   51,   53,   29,   51,   31,   51,   53,
   51,   51,   53,   60,   53,   93,   53,   53,   38,   38,
   95,   38,   38,   38,   38,   38,   38,   38,   38,   39,
   39,  112,   39,   39,   39,   39,   39,   39,   39,   39,
   38,  113,  114,    1,    8,   38,    7,   35,   38,   34,
   38,   39,   38,   38,   59,   68,   39,   24,    0,   39,
    0,   39,    0,   39,   39,   45,   45,    0,   45,   45,
   45,   45,   45,   45,   46,   46,    0,   46,   46,   46,
   46,   46,   46,    0,    0,    0,    0,   45,    0,    0,
    0,    0,   45,    0,    0,   45,   46,   45,    0,   45,
   45,   46,    0,    0,   46,    0,   46,    0,   46,   46,
   47,   47,    0,   47,   47,   47,   47,   47,   47,   48,
   48,    0,   48,   48,   48,   48,   48,   48,    0,    0,
   43,   43,   47,   43,   43,    0,    0,   47,    0,    0,
   47,   48,   47,    0,   47,   47,   48,    0,    0,   48,
    0,   48,   43,   48,   48,   44,   44,   43,   44,   44,
   43,    0,   43,    0,   43,   43,   49,   49,    0,    0,
    0,    0,   50,    0,    0,    0,    0,   44,    0,    0,
    0,    0,   44,    0,    0,   44,    0,   44,   49,   44,
   44,    0,    0,   49,   50,    0,   49,    0,   49,   50,
   49,   49,   50,    0,   50,    0,   50,   50,   69,   70,
    0,   71,   72,   73,   74,   75,   76,   77,   78,   79,
   80,   81,   69,   70,    0,   71,   72,   73,   74,   75,
   76,   77,   78,   79,   80,   81,    0,    0,    0,    0,
   69,   70,   84,   71,   72,   73,   74,   75,   76,   77,
   78,   79,   80,   81,   69,   70,   85,   71,   72,   73,
   74,   75,   76,   77,   78,   79,   80,   81,   49,   77,
   78,   79,   80,   81,   87,    0,    0,    0,    0,   32,
   50,   51,   52,    0,    0,    0,   33,    0,  123,   34,
   35,   36,   37,  111,    0,   53,    0,    0,    0,    0,
   69,   70,   54,   71,   72,   73,   74,   75,   76,   77,
   78,   79,   80,   81,   69,   70,    0,   71,   72,   73,
   74,   75,   76,   77,   78,   79,   80,   81,    0,    0,
   69,   70,   88,   71,   72,   73,   74,   75,   76,   77,
   78,   79,   80,   81,   69,   70,  115,   71,   72,   73,
   74,   75,   76,   77,   78,   79,   80,   81,    0,    0,
   69,   70,  121,   71,   72,   73,   74,   75,   76,   77,
   78,   79,   80,   81,   94,   73,   74,   75,   76,   77,
   78,   79,   80,   81,    0,   69,   70,   83,   71,   72,
   73,   74,   75,   76,   77,   78,   79,   80,   81,    0,
    0,   32,    0,    0,    0,    0,    0,   82,   33,   32,
    0,   34,   35,   36,   37,  118,   33,    5,    0,   34,
   35,   36,   37,  124,    5,   20,    0,    5,    5,    5,
    5,    5,   20,    0,    0,   20,   20,   20,   20,   20,
   69,   70,    0,   71,   72,   73,   74,   75,   76,   77,
   78,   79,   80,   81,    0,   32,    0,    0,    0,    0,
    0,    0,   33,    0,  117,   34,   35,   36,   37,   70,
    0,   71,   72,   73,   74,   75,   76,   77,   78,   79,
   80,   81,   71,   72,   73,   74,   75,   76,   77,   78,
   79,   80,   81,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         34,
   35,   36,  287,   59,  289,   19,  288,  275,  276,  272,
  295,  293,   47,   48,   49,  257,  279,   31,   53,  282,
  283,  284,  285,  286,  278,  272,   82,   83,  288,   64,
   65,  277,  287,  293,   69,   70,   71,   72,   73,   74,
   75,   76,   77,   78,   79,   80,   81,  289,  287,  288,
  269,  270,  271,  292,  293,  289,  258,  259,  272,  290,
   95,  117,  258,  259,  277,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,  271,  288,  112,  280,  114,
  285,  291,  293,  285,  280,  272,  288,  277,  290,  285,
  292,  293,  288,  292,  290,  296,  292,  293,  258,  259,
  289,  261,  262,  263,  264,  265,  266,  267,  268,  258,
  259,  257,  261,  262,  263,  264,  265,  266,  267,  268,
  280,  288,  293,    0,  288,  285,  288,  288,  288,  288,
  290,  280,  292,  293,   37,   54,  285,   21,   -1,  288,
   -1,  290,   -1,  292,  293,  258,  259,   -1,  261,  262,
  263,  264,  265,  266,  258,  259,   -1,  261,  262,  263,
  264,  265,  266,   -1,   -1,   -1,   -1,  280,   -1,   -1,
   -1,   -1,  285,   -1,   -1,  288,  280,  290,   -1,  292,
  293,  285,   -1,   -1,  288,   -1,  290,   -1,  292,  293,
  258,  259,   -1,  261,  262,  263,  264,  265,  266,  258,
  259,   -1,  261,  262,  263,  264,  265,  266,   -1,   -1,
  258,  259,  280,  261,  262,   -1,   -1,  285,   -1,   -1,
  288,  280,  290,   -1,  292,  293,  285,   -1,   -1,  288,
   -1,  290,  280,  292,  293,  258,  259,  285,  261,  262,
  288,   -1,  290,   -1,  292,  293,  258,  259,   -1,   -1,
   -1,   -1,  258,   -1,   -1,   -1,   -1,  280,   -1,   -1,
   -1,   -1,  285,   -1,   -1,  288,   -1,  290,  280,  292,
  293,   -1,   -1,  285,  280,   -1,  288,   -1,  290,  285,
  292,  293,  288,   -1,  290,   -1,  292,  293,  258,  259,
   -1,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  271,  258,  259,   -1,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,  271,   -1,   -1,   -1,   -1,
  258,  259,  292,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,  258,  259,  292,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  270,  271,  260,  267,
  268,  269,  270,  271,  292,   -1,   -1,   -1,   -1,  272,
  272,  273,  274,   -1,   -1,   -1,  279,   -1,  292,  282,
  283,  284,  285,  286,   -1,  287,   -1,   -1,   -1,   -1,
  258,  259,  294,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,  258,  259,   -1,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  270,  271,   -1,   -1,
  258,  259,  290,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,  258,  259,  290,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  270,  271,   -1,   -1,
  258,  259,  290,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,  288,  263,  264,  265,  266,  267,
  268,  269,  270,  271,   -1,  258,  259,  285,  261,  262,
  263,  264,  265,  266,  267,  268,  269,  270,  271,   -1,
   -1,  272,   -1,   -1,   -1,   -1,   -1,  280,  279,  272,
   -1,  282,  283,  284,  285,  286,  279,  272,   -1,  282,
  283,  284,  285,  286,  279,  272,   -1,  282,  283,  284,
  285,  286,  279,   -1,   -1,  282,  283,  284,  285,  286,
  258,  259,   -1,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,   -1,  272,   -1,   -1,   -1,   -1,
   -1,   -1,  279,   -1,  281,  282,  283,  284,  285,  259,
   -1,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  271,  261,  262,  263,  264,  265,  266,  267,  268,
  269,  270,  271,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=296;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"ASSIGN","OR","AND","NOT","EQ","NE","LE","LT","GE","GT","ADD",
"SUB","MUL","DIV","MOD","IDENT","NUM_LIT","BOOL_LIT","BOOL","NUM","TYPEOF",
"FUNC","IF","THEN","ELSE","WHILE","PRINT","RETURN","BEGIN","END","LPAREN",
"RPAREN","LBRACKET","RBRACKET","VAR","SEMI","COMMA","NEW","DOT","SIZE",
};
final static String yyrule[] = {
"$accept : program",
"program : decl_list",
"decl_list : decl_list decl",
"decl_list :",
"decl : func_decl",
"$$1 :",
"func_decl : FUNC IDENT TYPEOF type_spec LPAREN params RPAREN BEGIN local_decls $$1 stmt_list END",
"params : param_list",
"params :",
"param_list : param_list COMMA param",
"param_list : param",
"param : IDENT TYPEOF type_spec",
"type_spec : prim_type",
"type_spec : prim_type LBRACKET RBRACKET",
"prim_type : NUM",
"prim_type : BOOL",
"local_decls : local_decls local_decl",
"local_decls :",
"local_decl : VAR IDENT TYPEOF type_spec SEMI",
"stmt_list : stmt_list stmt",
"stmt_list :",
"stmt : assign_stmt",
"stmt : print_stmt",
"stmt : return_stmt",
"stmt : if_stmt",
"stmt : while_stmt",
"stmt : compound_stmt",
"assign_stmt : IDENT ASSIGN expr SEMI",
"assign_stmt : IDENT LBRACKET expr RBRACKET ASSIGN expr SEMI",
"print_stmt : PRINT expr SEMI",
"return_stmt : RETURN expr SEMI",
"if_stmt : IF expr THEN stmt_list ELSE stmt_list END",
"while_stmt : WHILE expr BEGIN stmt_list END",
"compound_stmt : BEGIN local_decls stmt_list END",
"args : arg_list",
"args :",
"arg_list : arg_list COMMA expr",
"arg_list : expr",
"expr : expr ADD expr",
"expr : expr SUB expr",
"expr : expr MUL expr",
"expr : expr DIV expr",
"expr : expr MOD expr",
"expr : expr EQ expr",
"expr : expr NE expr",
"expr : expr LE expr",
"expr : expr LT expr",
"expr : expr GE expr",
"expr : expr GT expr",
"expr : expr AND expr",
"expr : expr OR expr",
"expr : NOT expr",
"expr : LPAREN expr RPAREN",
"expr : IDENT",
"expr : NUM_LIT",
"expr : BOOL_LIT",
"expr : IDENT LPAREN args RPAREN",
"expr : NEW prim_type LBRACKET expr RBRACKET",
"expr : IDENT LBRACKET expr RBRACKET",
"expr : IDENT DOT SIZE",
};

//#line 137 ".\Parser.y"
    private Lexer lexer;
    private Token last_token;
    private ParserImpl parserImpl;
    public ParseTree.Program parsetree_program;

    private int yylex () {
        int yyl_return = -1;
        try {
            yylval = new ParserVal(0);
            yyl_return = lexer.yylex();
            last_token = (Token)yylval.obj;
        }
        catch (IOException e) {
            System.out.println("IO error :"+e);
        }
        return yyl_return;
    }

    public void yyerror (String error) {
        //System.out.println ("Error message for " + lexer.lineno+":"+lexer.column +" by Parser.yyerror(): " + error);
        int last_token_lineno = 0;
        int last_token_column = 0;
        System.out.println ("Error message by Parser.yyerror() at near " + last_token_lineno+":"+last_token_column + ": " + error);
    }

    public Parser(Reader r, boolean yydebug) {
        this.lexer   = new Lexer(r, this);
        this.yydebug = yydebug;
        this.parserImpl = new ParserImpl();
        this.parsetree_program = parserImpl.parsetree_program;
    }
//#line 448 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
throws Exception
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 34 ".\Parser.y"
{ parserImpl.Debug("program -> decl_list"); yyval.obj = parserImpl.program____decllist(val_peek(0).obj); }
break;
case 2:
//#line 37 ".\Parser.y"
{ parserImpl.Debug("decl_list -> decl_list decl"); yyval.obj = parserImpl.decllist____decllist_decl(val_peek(1).obj,val_peek(0).obj); }
break;
case 3:
//#line 38 ".\Parser.y"
{ parserImpl.Debug("decl_list -> eps"); yyval.obj = parserImpl.decllist____eps(); }
break;
case 4:
//#line 41 ".\Parser.y"
{ parserImpl.Debug("decl -> func_decl"); yyval.obj = parserImpl.decl____funcdecl(val_peek(0).obj); }
break;
case 5:
//#line 44 ".\Parser.y"
{ parserImpl.Debug("func_decl -> func ID::type_spec(params) begin local_decls"); yyval.obj = parserImpl.fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_10X_stmtlist_END(val_peek(8).obj,val_peek(7).obj,val_peek(6).obj,val_peek(5).obj,val_peek(4).obj,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 6:
//#line 44 ".\Parser.y"
{ parserImpl.Debug("stmt_list end"); yyval.obj = parserImpl.fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_X10_stmtlist_END(val_peek(11).obj,val_peek(10).obj,val_peek(9).obj,val_peek(8).obj,val_peek(7).obj,val_peek(6).obj,val_peek(5).obj,val_peek(4).obj,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 7:
//#line 47 ".\Parser.y"
{ parserImpl.Debug("params -> param_list"); yyval.obj = parserImpl.params____paramlist(val_peek(0).obj); }
break;
case 8:
//#line 48 ".\Parser.y"
{ parserImpl.Debug("params -> eps"); yyval.obj = parserImpl.params____eps(); }
break;
case 9:
//#line 51 ".\Parser.y"
{ parserImpl.Debug("param_list -> param_list COMMA param"); yyval.obj = parserImpl.paramlist____paramlist_COMMA_param(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 10:
//#line 52 ".\Parser.y"
{ parserImpl.Debug("param_list -> param"); yyval.obj = parserImpl.paramlist____param(val_peek(0).obj); }
break;
case 11:
//#line 55 ".\Parser.y"
{ parserImpl.Debug("param -> IDENT :: type_spec"); yyval.obj = parserImpl.param____IDENT_TYPEOF_typespec(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 12:
//#line 58 ".\Parser.y"
{ parserImpl.Debug("type_spec -> prim_type"); yyval.obj = parserImpl.typespec____primtype(val_peek(0).obj); }
break;
case 13:
//#line 59 ".\Parser.y"
{ parserImpl.Debug("type_spec -> prim_type [ ]"); yyval.obj = parserImpl.typespec____primtype_LBRACKET_RBRACKET(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj);}
break;
case 14:
//#line 62 ".\Parser.y"
{ parserImpl.Debug("prim_type -> num"); yyval.obj = parserImpl.primtype____NUM(val_peek(0).obj); }
break;
case 15:
//#line 63 ".\Parser.y"
{ parserImpl.Debug("prim_type -> bool"); yyval.obj = parserImpl.primtype____BOOL(val_peek(0).obj); }
break;
case 16:
//#line 66 ".\Parser.y"
{ parserImpl.Debug("local_decls -> local_decls local_decl"); yyval.obj = parserImpl.localdecls____localdecls_localdecl(val_peek(1).obj,val_peek(0).obj); }
break;
case 17:
//#line 67 ".\Parser.y"
{ parserImpl.Debug("local_decls -> eps"); yyval.obj = parserImpl.localdecls____eps(); }
break;
case 18:
//#line 70 ".\Parser.y"
{ parserImpl.Debug("local_decl -> var IDENT :: type_spec ;"); yyval.obj = parserImpl.localdecl____VAR_IDENT_TYPEOF_typespec_SEMI(val_peek(4).obj,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 19:
//#line 73 ".\Parser.y"
{ parserImpl.Debug("stmt_list -> stmt_list stmt"); yyval.obj = parserImpl.stmtlist____stmtlist_stmt(val_peek(1).obj,val_peek(0).obj); }
break;
case 20:
//#line 74 ".\Parser.y"
{ parserImpl.Debug("stmt_list -> eps"); yyval.obj = parserImpl.stmtlist____eps(); }
break;
case 21:
//#line 77 ".\Parser.y"
{ parserImpl.Debug("stmt -> assign_stmt"); yyval.obj = parserImpl.stmt____assignstmt(val_peek(0).obj); }
break;
case 22:
//#line 78 ".\Parser.y"
{ parserImpl.Debug("stmt -> print_stmt"); yyval.obj = parserImpl.stmt____printstmt(val_peek(0).obj); }
break;
case 23:
//#line 79 ".\Parser.y"
{ parserImpl.Debug("stmt -> return_stmt"); yyval.obj = parserImpl.stmt____returnstmt(val_peek(0).obj); }
break;
case 24:
//#line 80 ".\Parser.y"
{ parserImpl.Debug("stmt -> if_stmt"); yyval.obj = parserImpl.stmt____ifstmt(val_peek(0).obj); }
break;
case 25:
//#line 81 ".\Parser.y"
{ parserImpl.Debug("stmt -> while_stmt"); yyval.obj = parserImpl.stmt____whilestmt(val_peek(0).obj); }
break;
case 26:
//#line 82 ".\Parser.y"
{ parserImpl.Debug("stmt -> compound_stmt"); yyval.obj = parserImpl.stmt____compoundstmt(val_peek(0).obj); }
break;
case 27:
//#line 85 ".\Parser.y"
{ parserImpl.Debug("assign_stmt -> IDENT := expr ;"); yyval.obj = parserImpl.assignstmt____IDENT_ASSIGN_expr_SEMI(val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 28:
//#line 86 ".\Parser.y"
{ parserImpl.Debug("assign_stmt -> IDENT [ expr ] := expr ;"); yyval.obj = parserImpl.assignstmt____IDENT_LBRACKET_expr_RBRACKET_ASSIGN_expr_SEMI(val_peek(6).obj,val_peek(5).obj,val_peek(4).obj,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 29:
//#line 89 ".\Parser.y"
{ parserImpl.Debug("print_stmt -> print expr ;"); yyval.obj = parserImpl.printstmt____PRINT_expr_SEMI(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 30:
//#line 92 ".\Parser.y"
{ parserImpl.Debug("return_stmt -> return expr ;"); yyval.obj = parserImpl.returnstmt____RETURN_expr_SEMI(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 31:
//#line 95 ".\Parser.y"
{ parserImpl.Debug("if_stmt -> if expr then stmt_list else stmt_list end"); yyval.obj = parserImpl.ifstmt____IF_expr_THEN_stmtlist_ELSE_stmtlist_END(val_peek(6).obj,val_peek(5).obj,val_peek(4).obj,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj);}
break;
case 32:
//#line 98 ".\Parser.y"
{ parserImpl.Debug("while_stmt -> while expr begin stmt_list end"); yyval.obj = parserImpl.whilestmt____WHILE_expr_BEGIN_stmtlist_END(val_peek(4).obj,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj); }
break;
case 33:
//#line 101 ".\Parser.y"
{ parserImpl.Debug("compound_stmt -> begin local_decls stmt_list end"); yyval.obj = parserImpl.compoundstmt____BEGIN_localdecls_stmtlist_END(val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj);}
break;
case 34:
//#line 104 ".\Parser.y"
{ parserImpl.Debug("args -> arg_list"); yyval.obj = parserImpl.args____arglist(val_peek(0).obj); }
break;
case 35:
//#line 105 ".\Parser.y"
{ parserImpl.Debug("args -> eps"); yyval.obj = parserImpl.args____eps(); }
break;
case 36:
//#line 108 ".\Parser.y"
{ parserImpl.Debug("arg_list -> arg_list COMMA expr"); yyval.obj = parserImpl.arglist____arglist_COMMA_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 37:
//#line 109 ".\Parser.y"
{ parserImpl.Debug("arg_list -> expr"); yyval.obj = parserImpl.arglist____expr(val_peek(0).obj); }
break;
case 38:
//#line 112 ".\Parser.y"
{ parserImpl.Debug("expr -> expr ADD expr"); yyval.obj = parserImpl.expr____expr_ADD_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 39:
//#line 113 ".\Parser.y"
{ parserImpl.Debug("expr -> expr SUB expr"); yyval.obj = parserImpl.expr____expr_SUB_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 40:
//#line 114 ".\Parser.y"
{ parserImpl.Debug("expr -> expr MUL expr"); yyval.obj = parserImpl.expr____expr_MUL_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 41:
//#line 115 ".\Parser.y"
{ parserImpl.Debug("expr -> expr DIV expr"); yyval.obj = parserImpl.expr____expr_DIV_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 42:
//#line 116 ".\Parser.y"
{ parserImpl.Debug("expr -> expr MOD expr"); yyval.obj = parserImpl.expr____expr_MOD_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 43:
//#line 117 ".\Parser.y"
{ parserImpl.Debug("expr -> expr EQ  expr"); yyval.obj = parserImpl.expr____expr_EQ_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 44:
//#line 118 ".\Parser.y"
{ parserImpl.Debug("expr -> expr NE  expr"); yyval.obj = parserImpl.expr____expr_NE_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 45:
//#line 119 ".\Parser.y"
{ parserImpl.Debug("expr -> expr LE  expr"); yyval.obj = parserImpl.expr____expr_LE_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 46:
//#line 120 ".\Parser.y"
{ parserImpl.Debug("expr -> expr LT  expr"); yyval.obj = parserImpl.expr____expr_LT_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 47:
//#line 121 ".\Parser.y"
{ parserImpl.Debug("expr -> expr GE  expr"); yyval.obj = parserImpl.expr____expr_GE_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 48:
//#line 122 ".\Parser.y"
{ parserImpl.Debug("expr -> expr GT  expr"); yyval.obj = parserImpl.expr____expr_GT_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 49:
//#line 123 ".\Parser.y"
{ parserImpl.Debug("expr -> expr AND expr"); yyval.obj = parserImpl.expr____expr_AND_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 50:
//#line 124 ".\Parser.y"
{ parserImpl.Debug("expr -> expr OR  expr"); yyval.obj = parserImpl.expr____expr_OR_expr(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 51:
//#line 125 ".\Parser.y"
{ parserImpl.Debug("expr -> NOT expr"); yyval.obj = parserImpl.expr____NOT_expr(val_peek(1).obj,val_peek(0).obj); }
break;
case 52:
//#line 126 ".\Parser.y"
{ parserImpl.Debug("expr -> LPAREN expr RPAREN"); yyval.obj = parserImpl.expr____LPAREN_expr_RPAREN(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 53:
//#line 127 ".\Parser.y"
{ parserImpl.Debug("expr -> IDENT"); yyval.obj = parserImpl.expr____IDENT(val_peek(0).obj); }
break;
case 54:
//#line 128 ".\Parser.y"
{ parserImpl.Debug("expr -> NUM_LIT"); yyval.obj = parserImpl.expr____NUMLIT(val_peek(0).obj); }
break;
case 55:
//#line 129 ".\Parser.y"
{ parserImpl.Debug("expr -> BOOL_LIT"); yyval.obj = parserImpl.expr____BOOLLIT(val_peek(0).obj); }
break;
case 56:
//#line 130 ".\Parser.y"
{ parserImpl.Debug("expr -> IDENT LPAREN args RPAREN"); yyval.obj = parserImpl.expr____IDENT_LPAREN_args_RPAREN(val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 57:
//#line 131 ".\Parser.y"
{ parserImpl.Debug("expr -> NEW prim_type [ expr ]"); yyval.obj = parserImpl.expr____NEW_primtype_LBRACKET_expr_RBRACKET(val_peek(4).obj,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 58:
//#line 132 ".\Parser.y"
{ parserImpl.Debug("expr -> IDENT [ expr ]"); yyval.obj = parserImpl.expr____IDENT_LBRACKET_expr_RBRACKET(val_peek(3).obj,val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
case 59:
//#line 133 ".\Parser.y"
{ parserImpl.Debug("expr -> IDENT . SIZE"); yyval.obj = parserImpl.expr____IDENT_DOT_SIZE(val_peek(2).obj,val_peek(1).obj,val_peek(0).obj); }
break;
//#line 833 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
