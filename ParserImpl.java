import java.util.*;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class ParserImpl
{
    public static Boolean _debug = true;
    void Debug(String message)
    {
        if(_debug)
            System.out.println(message);
    }

    // This is for chained symbol table.
    // This includes the global scope only at this moment.
    Env env = new Env(null);
    // this stores the root of parse tree, which will be used to print parse tree and run the parse tree
    ParseTree.Program parsetree_program = null;

    Object program____decllist(Object s1) throws Exception
    {
        // 1. check if decllist has main function having no parameters and returns int type
        // 2. assign the root, whose type is ParseTree.Program, to parsetree_program
        ArrayList<ParseTree.FuncDecl> decllist = (ArrayList<ParseTree.FuncDecl>)s1;
        parsetree_program = new ParseTree.Program(decllist);
        return parsetree_program;
    }

    Object decllist____decllist_decl(Object s1, Object s2) throws Exception
    {
        ArrayList<ParseTree.FuncDecl> decllist = (ArrayList<ParseTree.FuncDecl>)s1;
        ParseTree.FuncDecl                decl = (ParseTree.FuncDecl           )s2;
        decllist.add(decl);
        return decllist;
    }

    Object decllist____eps() throws Exception
    {
        return new ArrayList<ParseTree.FuncDecl>();
    }

    Object decl____funcdecl(Object s1) throws Exception
    {
        return s1;
    }

    Object fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_10X_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9) throws Exception
    {
        // 1. add function_type_info object (name, return type, params) into the global scope of env
        // 2. create a new symbol table on top of env
        // 3. add parameters into top-local scope of env
        // 4. etc.
        return null;
    }

    Object fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_X10_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9, Object s10, Object s11, Object s12) throws Exception
    {
        // 1. check if this function has at least one return type
        // 2. etc.
        // 3. create and return funcdecl node
        Token                            id         = (Token                           )s2;
        ParseTree.TypeSpec               rettype    = (ParseTree.TypeSpec              )s4;
        ArrayList<ParseTree.Param>       params     = (ArrayList<ParseTree.Param>      )s6;
        ArrayList<ParseTree.LocalDecl>   localdecls = (ArrayList<ParseTree.LocalDecl>  )s9;
        ArrayList<ParseTree.Stmt>        stmtlist   = (ArrayList<ParseTree.Stmt>       )s11;
        Token                            end        = (Token                           )s12;
        ParseTree.FuncDecl funcdecl = new ParseTree.FuncDecl(id.lexeme, rettype, params, localdecls, stmtlist);
        return funcdecl;
    }

    Object params____paramlist(Object s1) throws Exception
    {
        return s1;
    }

    Object params____eps() throws Exception
    {
        return new ArrayList<ParseTree.Param>();
    }

    Object paramlist____paramlist_COMMA_param(Object s1, Object s2, Object s3) throws Exception
    {
        ArrayList<ParseTree.Param> paramlist = (ArrayList<ParseTree.Param>)s1;
        ParseTree.Param            param     = (ParseTree.Param           )s3;
        paramlist.add(param);
        return paramlist;
    }

    Object paramlist____param(Object s1) throws Exception
    {
        ArrayList<ParseTree.Param> paramlist = new ArrayList<ParseTree.Param>();
        paramlist.add((ParseTree.Param)s1);
        return paramlist;
    }

    Object param____IDENT_TYPEOF_typespec(Object s1, Object s2, Object s3) throws Exception
    {
        Token              id       = (Token             )s1;
        ParseTree.TypeSpec typespec = (ParseTree.TypeSpec)s3;
        return new ParseTree.Param(id.lexeme, typespec);
    }

    Object typespec____primtype(Object s1)
    {
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec)s1;
        return primtype;
    }

    Object typespec____primtype_LBRACKET_RBRACKET(Object s1, Object s2, Object s3)
    {
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec)s1;
        return new ParseTree.TypeSpec(primtype + "[]");
    }

    Object primtype____NUM(Object s1) throws Exception
    {
        ParseTree.TypeSpec typespec = new ParseTree.TypeSpec("int");
        return typespec;
    }

    Object primtype____BOOL(Object s1) throws Exception
    {
        ParseTree.TypeSpec typespec = new ParseTree.TypeSpec("bool");
        return typespec;
    }

    Object localdecls____localdecls_localdecl(Object s1, Object s2)
    {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>)s1;
        ParseTree.LocalDecl            localdecl  = (ParseTree.LocalDecl           )s2;
        localdecls.add(localdecl);
        return localdecls;
    }

    Object localdecls____eps() throws Exception
    {
        return new ArrayList<ParseTree.LocalDecl>();
    }

    Object localdecl____VAR_IDENT_TYPEOF_typespec_SEMI(Object s1, Object s2, Object s3, Object s4, Object s5)
    {
        Token              id       = (Token             )s2;
        ParseTree.TypeSpec typespec = (ParseTree.TypeSpec)s4;
        ParseTree.LocalDecl localdecl = new ParseTree.LocalDecl(id.lexeme, typespec);
        localdecl.reladdr = 1;
        return localdecl;
    }

    Object stmtlist____stmtlist_stmt(Object s1, Object s2) throws Exception
    {
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>)s1;
        ParseTree.Stmt            stmt     = (ParseTree.Stmt           )s2;
        stmtlist.add(stmt);
        return stmtlist;
    }

    Object stmtlist____eps() throws Exception
    {
        return new ArrayList<ParseTree.Stmt>();
    }

    Object stmt____assignstmt  (Object s1) throws Exception
    {
        assert(s1 instanceof ParseTree.AssignStmt);
        return s1;
    }

    Object stmt____printstmt   (Object s1) throws Exception
    {
        assert(s1 instanceof ParseTree.PrintStmt);
        return s1;
    }

    Object stmt____returnstmt  (Object s1) throws Exception
    {
        assert(s1 instanceof ParseTree.ReturnStmt);
        return s1;
    }

    Object stmt____ifstmt      (Object s1) throws Exception
    {
        assert(s1 instanceof ParseTree.IfStmt);
        return s1;
    }

    Object stmt____whilestmt   (Object s1) throws Exception
    {
        assert(s1 instanceof ParseTree.WhileStmt);
        return s1;
    }

    Object stmt____compoundstmt(Object s1) throws Exception
    {
        assert(s1 instanceof ParseTree.CompoundStmt);
        return s1;
    }

    Object assignstmt____IDENT_ASSIGN_expr_SEMI(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        // 1. check if ident.value_type matches with expr.value_type
        // 2. etc.
        // e. create and return node
        Token          id     = (Token         )s1;
        Token          assign = (Token         )s2;
        ParseTree.Expr expr   = (ParseTree.Expr)s3;
        Object id_type = env.Get(id.lexeme);
        {
            // check if expr.type matches with id_type
            if(id_type.equals("num")
                && (expr instanceof ParseTree.ExprNumLit)
                )
                {} // ok
            else if(id_type.equals("num")
                && (expr instanceof ParseTree.ExprFuncCall)
                && (env.Get(((ParseTree.ExprFuncCall)expr).ident).equals("num()"))
                )
            {} // ok
            else
            {
                throw new Exception("semantic error");
            }
        }
        ParseTree.AssignStmt stmt = new ParseTree.AssignStmt(id.lexeme, expr);
        stmt.ident_reladdr = 1;
        return stmt;
    }

    Object assignstmt____IDENT_LBRACKET_expr_RBRACKET_ASSIGN_expr_SEMI(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7) throws Exception
    {
        // 1. check if ident.value_type matches with expr1.value_type
        // 2. check if expr2.value_type is int
        // 3. etc.
        // 4. create and return node
        Token          id     = (Token         )s1;
        Token          lbrack = (Token         )s2;
        ParseTree.Expr expr1  = (ParseTree.Expr)s3;
        Token          rbrack = (Token         )s4;
        Token          assign = (Token         )s5;
        ParseTree.Expr expr2  = (ParseTree.Expr)s6;
        Object id_type = env.Get(id.lexeme);
        {
            // check if expr1.type matches with id_type
            if(id_type.equals("num[]")
                && (expr1 instanceof ParseTree.ExprFuncCall)
                && (env.Get(((ParseTree.ExprFuncCall)expr1).ident).equals("num()"))
                )
            {} // ok
            else
            {
                throw new Exception("semantic error");
            }
            // check if expr2.type matches with num
            if(id_type.equals("num[]")
                && (expr2 instanceof ParseTree.ExprNumLit)
                )
            {} // ok
            else
            {
                throw new Exception("semantic error");
            }
        }
        ParseTree.AssignStmt stmt = new ParseTree.AssignStmt(id.lexeme, expr2);
        stmt.ident_reladdr = 1;
        return stmt;
    }

    Object printstmt____PRINT_expr_SEMI(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr.value_type is int
        // 2. etc.
        // 3. create and return node
        Token          print = (Token         )s1;
        ParseTree.Expr expr  = (ParseTree.Expr)s2;
        return new ParseTree.PrintStmt(expr);
    }

    Object returnstmt____RETURN_expr_SEMI(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr.value_type matches with the current function return type
        // 2. etc.
        // 3. create and return node
        ParseTree.Expr expr = (ParseTree.Expr)s2;
        return new ParseTree.ReturnStmt(expr);
    }

    Object ifstmt____IF_expr_THEN_stmtlist_ELSE_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7) throws Exception
    {
        // 1. check if expr.value_type is bool
        // 2. etc.
        // 3. create and return node
        ParseTree.Expr expr = (ParseTree.Expr)s2;
        ArrayList<ParseTree.Stmt> stmtlist1 = (ArrayList<ParseTree.Stmt>)s4;
        ArrayList<ParseTree.Stmt> stmtlist2 = (ArrayList<ParseTree.Stmt>)s6;
        return new ParseTree.IfStmt(expr, stmtlist1, stmtlist2);
    }

    Object whilestmt____WHILE_expr_BEGIN_stmtlist_END(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        // 1. check if expr.value_type is bool
        // 2. etc.
        // 3. create and return node
        ParseTree.Expr expr = (ParseTree.Expr)s2;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>)s4;
        return new ParseTree.WhileStmt(expr, stmtlist);
    }

    Object compoundstmt____BEGIN_localdecls_stmtlist_END(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        // 1. create and return node
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>)s2;
        ArrayList<ParseTree.Stmt>      stmtlist   = (ArrayList<ParseTree.Stmt>     )s3;
        return new ParseTree.CompoundStmt(localdecls, stmtlist);
    }

    Object args____arglist(Object s1) throws Exception
    {
        return s1;
    }

    Object args____eps() throws Exception
    {
        return new ArrayList<ParseTree.Expr>();
    }

    Object arglist____arglist_COMMA_expr(Object s1, Object s2, Object s3) throws Exception
    {
        ArrayList<ParseTree.Expr> arglist = (ArrayList<ParseTree.Expr>)s1;
        ParseTree.Expr            expr    = (ParseTree.Expr           )s3;
        arglist.add(expr);
        return arglist;
    }

    Object arglist____expr(Object s1) throws Exception
    {
        ArrayList<ParseTree.Expr> arglist = new ArrayList<ParseTree.Expr>();
        arglist.add((ParseTree.Expr)s1);
        return arglist;
    }

    Object expr____expr_ADD_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprAdd(expr1,expr2);
    }

    Object expr____expr_SUB_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprSub(expr1,expr2);
    }

    Object expr____expr_MUL_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprMul(expr1,expr2);
    }

    Object expr____expr_DIV_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprDiv(expr1,expr2);
    }

    Object expr____expr_MOD_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprMod(expr1,expr2);
    }

    Object expr____expr_EQ_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprEq(expr1,expr2);
    }

    Object expr____expr_NE_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprNe(expr1,expr2);
    }

    Object expr____expr_LE_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprLe(expr1,expr2);
    }

    Object expr____expr_LT_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprLt(expr1,expr2);
    }

    Object expr____expr_GE_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprGe(expr1,expr2);
    }

    Object expr____expr_GT_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprGt(expr1,expr2);
    }

    Object expr____expr_AND_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprAnd(expr1,expr2);
    }

    Object expr____expr_OR_expr(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr2 = (ParseTree.Expr)s3;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprOr(expr1,expr2);
    }

    Object expr____NOT_expr(Object s1, Object s2) throws Exception
    {
    // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        Token          oper  = (Token         )s2;
        ParseTree.Expr expr1 = (ParseTree.Expr)s1;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprNot(expr1);
    }

    Object expr____LPAREN_expr_RPAREN(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. create and return node whose value_type is the same to the expr.value_type
        Token          lparen = (Token         )s1;
        ParseTree.Expr expr   = (ParseTree.Expr)s2;
        Token          rparen = (Token         )s3;
        return new ParseTree.ExprParen(expr);
    }

    Object expr____IDENT(Object s1) throws Exception
    {
        // 1. check if id.lexeme can be found in chained symbol tables
        // 2. check if it is variable type
        // 3. etc.
        // 4. create and return node that has the value_type of the id.lexeme
        Token id = (Token)s1;
        ParseTree.ExprIdent expr = new ParseTree.ExprIdent(id.lexeme);
        expr.reladdr = 1;
        return expr;
    }

    Object expr____NUMLIT(Object s1) throws Exception
    {
        // 1. create and return node that has int type
        Token token = (Token)s1;
        double value = Integer.parseInt(token.lexeme);
        return new ParseTree.ExprNumLit(value);
    }

    Object expr____BOOLLIT(Object s1) throws Exception
    {
        // 1. create and return node that has int type
        Token token = (Token)s1;
        double value = Integer.parseInt(token.lexeme);
        return new ParseTree.ExprNumLit(value);
    }

    Object expr____IDENT_LPAREN_args_RPAREN(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        // 1. check if id.lexeme can be found in chained symbol tables
        // 2. check if it is function type
        // 3. check if the number and types of env(id.lexeme).params match with those of args
        // 4. etc.
        // 5. create and return node that has the value_type of env(id.lexeme).return_type
        Token                    id   = (Token                   )s1;
        ArrayList<ParseTree.Arg> args = (ArrayList<ParseTree.Arg>)s3;
        Object func_attr = env.Get(id.lexeme);
        {
            // check if argument types match with function param types
            if(env.Get(id.lexeme).equals("num()")
                && (args.size() == 0)
                )
            {} // ok
            else
            {
                throw new Exception("semantic error");
            }
        }
        return new ParseTree.ExprFuncCall(id.lexeme, args);
    }

    Object expr____NEW_primtype_LBRACKET_expr_RBRACKET(Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception
    {
        // 1. check if expr.value_type is int
        // 2. etc.
        // 3. create and return node that has the value_type of primtype.lexeme + "[]"
        Token          newtok = (Token         )s1;
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec)s2;
        Token          lbrack  = (Token         )s3;
        ParseTree.Expr  expr    = (ParseTree.Expr)s4;
        Token          rbrack  = (Token         )s5;
        // check if expr.type matches with num
        return new ParseTree.ExprNewArray(primtype, expr);
    }

    Object expr____IDENT_LBRACKET_expr_RBRACKET(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        // 1. check if ident.value_type is primtype[] type
        // 2. check if expr.value_type is int
        // 3. etc.
        // 4. create and return node that has the value_type of primtype.lexeme
        Token          id     = (Token         )s1;
        Token          lbrack = (Token         )s2;
        ParseTree.Expr expr   = (ParseTree.Expr)s3;
        Token          rbrack = (Token         )s4;
        Object id_type = env.Get(id.lexeme);
        {
            // check if expr.type matches with num
            if(id_type.equals("num[]")
                && (expr instanceof ParseTree.ExprFuncCall)
                && (env.Get(((ParseTree.ExprFuncCall)expr).ident).equals("num()"))
                )
            {} // ok
            else
            {
                throw new Exception("semantic error");
            }
        }
        return new ParseTree.ExprArrayElem(id.lexeme, expr);
    }

    Object expr____IDENT_DOT_SIZE(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if ident.value_type is primtype[] type
        // 2. create and return node that has int type
        Token id   = (Token)s1;
        Token dot  = (Token)s2;
        Token size = (Token)s3;
        Object id_type = env.Get(id.lexeme);
        {
            // check if id_type matches with num[]
            if(id_type.equals("num[]")
                )
            {} // ok
            else
            {
                throw new Exception("semantic error");
            }
        }
        return new ParseTree.ExprArraySize(id.lexeme);
    }

}
