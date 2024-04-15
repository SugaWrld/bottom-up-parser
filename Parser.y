%{
import java.io.*;
%}

%right  ASSIGN
%left   OR
%left   AND
%right  NOT
%left   EQ      NE
%left   LE      LT      GE      GT
%left   ADD     SUB
%left   MUL     DIV     MOD

%token <obj>    EQ   NE   LE   LT   GE   GT
%token <obj>    ADD  SUB  MUL  DIV  MOD
%token <obj>    OR   AND  NOT

%token <obj>    IDENT     NUM_LIT   BOOL_LIT

%token <obj> BOOL  NUM  TYPEOF
%token <obj> FUNC  IF  THEN  ELSE  WHILE  PRINT  RETURN
%token <obj> BEGIN  END  LPAREN  RPAREN  LBRACKET  RBRACKET
%token <obj> ASSIGN  VAR  SEMI  COMMA  NEW  DOT  SIZE

%type <obj> program   decl_list  decl
%type <obj> func_decl  local_decls  local_decl  type_spec  prim_type
%type <obj> params  param_list  param  args  arg_list
%type <obj> stmt_list  stmt  assign_stmt  print_stmt  return_stmt  if_stmt  while_stmt  compound_stmt     
%type <obj> expr

%%


program         : decl_list                                     { parserImpl.Debug("program -> decl_list"); $$ = parserImpl.program____decllist($1); }
                ;

decl_list       : decl_list decl                                { parserImpl.Debug("decl_list -> decl_list decl"); $$ = parserImpl.decllist____decllist_decl($1,$2); }
                |                                               { parserImpl.Debug("decl_list -> eps"); $$ = parserImpl.decllist____eps(); }
                ;

decl            : func_decl                                     { parserImpl.Debug("decl -> func_decl"); $$ = parserImpl.decl____funcdecl($1); }
                ;

func_decl       : FUNC IDENT TYPEOF type_spec LPAREN params RPAREN BEGIN local_decls { parserImpl.Debug("func_decl -> func ID::type_spec(params) begin local_decls"); $<obj>$ = parserImpl.fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_10X_stmtlist_END($1,$2,$3,$4,$5,$6,$7,$8,$9); } stmt_list END { parserImpl.Debug("stmt_list end"); $$ = parserImpl.fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_X10_stmtlist_END($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12); }
                ;

params          : param_list                                    { parserImpl.Debug("params -> param_list"); $$ = parserImpl.params____paramlist($1); }
                |                                               { parserImpl.Debug("params -> eps"); $$ = parserImpl.params____eps(); }
                ;

param_list      : param_list COMMA param                        { parserImpl.Debug("param_list -> param_list COMMA param"); $$ = parserImpl.paramlist____paramlist_COMMA_param($1,$2,$3); }
                | param                                         { parserImpl.Debug("param_list -> param"); $$ = parserImpl.paramlist____param($1); }
                ;

param           : IDENT TYPEOF type_spec                        { parserImpl.Debug("param -> IDENT :: type_spec"); $$ = parserImpl.param____IDENT_TYPEOF_typespec($1,$2,$3); }
                ;

type_spec       : prim_type                                     { parserImpl.Debug("type_spec -> prim_type"); $$ = parserImpl.typespec____primtype($1); }
                | prim_type LBRACKET RBRACKET                   { parserImpl.Debug("type_spec -> prim_type [ ]"); $$ = parserImpl.typespec____primtype_LBRACKET_RBRACKET($1,$2,$3);}
                ;

prim_type       : NUM                                           { parserImpl.Debug("prim_type -> num"); $$ = parserImpl.primtype____NUM($1); }
                | BOOL                                          { parserImpl.Debug("prim_type -> bool"); $$ = parserImpl.primtype____BOOL($1); }
                ;

local_decls     : local_decls  local_decl                       { parserImpl.Debug("local_decls -> local_decls local_decl"); $$ = parserImpl.localdecls____localdecls_localdecl($1,$2); }
                |                                               { parserImpl.Debug("local_decls -> eps"); $$ = parserImpl.localdecls____eps(); }
                ;

local_decl      : VAR  IDENT  TYPEOF  type_spec  SEMI           { parserImpl.Debug("local_decl -> var IDENT :: type_spec ;"); $$ = parserImpl.localdecl____VAR_IDENT_TYPEOF_typespec_SEMI($1,$2,$3,$4,$5); }
                ;

stmt_list       : stmt_list stmt                                { parserImpl.Debug("stmt_list -> stmt_list stmt"); $$ = parserImpl.stmtlist____stmtlist_stmt($1,$2); }
                |                                               { parserImpl.Debug("stmt_list -> eps"); $$ = parserImpl.stmtlist____eps(); }
                ;

stmt            : assign_stmt                                   { parserImpl.Debug("stmt -> assign_stmt"); $$ = parserImpl.stmt____assignstmt($1); }
                | print_stmt                                    { parserImpl.Debug("stmt -> print_stmt"); $$ = parserImpl.stmt____printstmt($1); }
                | return_stmt                                   { parserImpl.Debug("stmt -> return_stmt"); $$ = parserImpl.stmt____returnstmt($1); }
                | if_stmt                                       { parserImpl.Debug("stmt -> if_stmt"); $$ = parserImpl.stmt____ifstmt($1); }
                | while_stmt                                    { parserImpl.Debug("stmt -> while_stmt"); $$ = parserImpl.stmt____whilestmt($1); }
                | compound_stmt                                 { parserImpl.Debug("stmt -> compound_stmt"); $$ = parserImpl.stmt____compoundstmt($1); }
                ;

assign_stmt     : IDENT ASSIGN expr SEMI                        { parserImpl.Debug("assign_stmt -> IDENT := expr ;"); $$ = parserImpl.assignstmt____IDENT_ASSIGN_expr_SEMI($1,$2,$3,$4); }
                | IDENT LBRACKET expr RBRACKET ASSIGN expr SEMI { parserImpl.Debug("assign_stmt -> IDENT [ expr ] := expr ;"); $$ = parserImpl.assignstmt____IDENT_LBRACKET_expr_RBRACKET_ASSIGN_expr_SEMI($1,$2,$3,$4,$5,$6,$7); }
                ;

print_stmt      : PRINT expr SEMI                               { parserImpl.Debug("print_stmt -> print expr ;"); $$ = parserImpl.printstmt____PRINT_expr_SEMI($1,$2,$3); }
                ;

return_stmt     : RETURN expr SEMI                              { parserImpl.Debug("return_stmt -> return expr ;"); $$ = parserImpl.returnstmt____RETURN_expr_SEMI($1,$2,$3); }
                ;

if_stmt         : IF expr THEN stmt_list ELSE stmt_list END     { parserImpl.Debug("if_stmt -> if expr then stmt_list else stmt_list end"); $$ = parserImpl.ifstmt____IF_expr_THEN_stmtlist_ELSE_stmtlist_END($1,$2,$3,$4,$5,$6,$7);}
                ;

while_stmt      : WHILE expr BEGIN stmt_list END                { parserImpl.Debug("while_stmt -> while expr begin stmt_list end"); $$ = parserImpl.whilestmt____WHILE_expr_BEGIN_stmtlist_END($1,$2,$3,$4); }
                ;

compound_stmt   : BEGIN local_decls stmt_list END               { parserImpl.Debug("compound_stmt -> begin local_decls stmt_list end"); $$ = parserImpl.compoundstmt____BEGIN_localdecls_stmtlist_END($1,$2,$3,$4);}
                ;

args            : arg_list                                      { parserImpl.Debug("args -> arg_list"); $$ = parserImpl.args____arglist($1); }
                |                                               { parserImpl.Debug("args -> eps"); $$ = parserImpl.args____eps(); }
                ;

arg_list        : arg_list COMMA expr                           { parserImpl.Debug("arg_list -> arg_list COMMA expr"); $$ = parserImpl.arglist____arglist_COMMA_expr($1,$2,$3); }
                | expr                                          { parserImpl.Debug("arg_list -> expr"); $$ = parserImpl.arglist____expr($1); }
                ;

expr            : expr ADD expr                                 { parserImpl.Debug("expr -> expr ADD expr"); $$ = parserImpl.expr____expr_ADD_expr($1,$2,$3); }
                | expr SUB expr                                 { parserImpl.Debug("expr -> expr SUB expr"); $$ = parserImpl.expr____expr_SUB_expr($1,$2,$3); }
                | expr MUL expr                                 { parserImpl.Debug("expr -> expr MUL expr"); $$ = parserImpl.expr____expr_MUL_expr($1,$2,$3); }
                | expr DIV expr                                 { parserImpl.Debug("expr -> expr DIV expr"); $$ = parserImpl.expr____expr_DIV_expr($1,$2,$3); }
                | expr MOD expr                                 { parserImpl.Debug("expr -> expr MOD expr"); $$ = parserImpl.expr____expr_MOD_expr($1,$2,$3); }
                | expr EQ  expr                                 { parserImpl.Debug("expr -> expr EQ  expr"); $$ = parserImpl.expr____expr_EQ_expr($1,$2,$3); }
                | expr NE  expr                                 { parserImpl.Debug("expr -> expr NE  expr"); $$ = parserImpl.expr____expr_NE_expr($1,$2,$3); }
                | expr LE  expr                                 { parserImpl.Debug("expr -> expr LE  expr"); $$ = parserImpl.expr____expr_LE_expr($1,$2,$3); }
                | expr LT  expr                                 { parserImpl.Debug("expr -> expr LT  expr"); $$ = parserImpl.expr____expr_LT_expr($1,$2,$3); }
                | expr GE  expr                                 { parserImpl.Debug("expr -> expr GE  expr"); $$ = parserImpl.expr____expr_GE_expr($1,$2,$3); }
                | expr GT  expr                                 { parserImpl.Debug("expr -> expr GT  expr"); $$ = parserImpl.expr____expr_GT_expr($1,$2,$3); }
                | expr AND expr                                 { parserImpl.Debug("expr -> expr AND expr"); $$ = parserImpl.expr____expr_AND_expr($1,$2,$3); }
                | expr OR  expr                                 { parserImpl.Debug("expr -> expr OR  expr"); $$ = parserImpl.expr____expr_OR_expr($1,$2,$3); }
                | NOT expr                                      { parserImpl.Debug("expr -> NOT expr"); $$ = parserImpl.expr____NOT_expr($1,$2); }
                | LPAREN expr RPAREN                            { parserImpl.Debug("expr -> LPAREN expr RPAREN"); $$ = parserImpl.expr____LPAREN_expr_RPAREN($1,$2,$3); }
                | IDENT                                         { parserImpl.Debug("expr -> IDENT"); $$ = parserImpl.expr____IDENT($1); }
                | NUM_LIT                                       { parserImpl.Debug("expr -> NUM_LIT"); $$ = parserImpl.expr____NUMLIT($1); }
                | BOOL_LIT                                      { parserImpl.Debug("expr -> BOOL_LIT"); $$ = parserImpl.expr____BOOLLIT($1); }
                | IDENT LPAREN args RPAREN                      { parserImpl.Debug("expr -> IDENT LPAREN args RPAREN"); $$ = parserImpl.expr____IDENT_LPAREN_args_RPAREN($1,$2,$3,$4); }
                | NEW prim_type LBRACKET expr RBRACKET          { parserImpl.Debug("expr -> NEW prim_type [ expr ]"); $$ = parserImpl.expr____NEW_primtype_LBRACKET_expr_RBRACKET($1,$2,$3,$4,$5); }
                | IDENT LBRACKET expr RBRACKET                  { parserImpl.Debug("expr -> IDENT [ expr ]"); $$ = parserImpl.expr____IDENT_LBRACKET_expr_RBRACKET($1,$2,$3,$4); }
                | IDENT DOT SIZE                                { parserImpl.Debug("expr -> IDENT . SIZE"); $$ = parserImpl.expr____IDENT_DOT_SIZE($1,$2,$3); }
                ;

%%
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
