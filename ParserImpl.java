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

    ParseTree.Program program____decllist(Object s1) throws Exception
    {
        // 1. check if decllist has main function having no parameters and returns int type
        // 2. assign the root, whose type is ParseTree.Program, to parsetree_program
        ArrayList<ParseTree.FuncDecl> decllist = (ArrayList<ParseTree.FuncDecl>)s1;
        parsetree_program = new ParseTree.Program(decllist);
        return parsetree_program;
    }

    List<ParseTree.FuncDecl> decllist____decllist_decl(Object s1, Object s2) throws Exception
    {
        ArrayList<ParseTree.FuncDecl> decllist = (ArrayList<ParseTree.FuncDecl>)s1;
        ParseTree.FuncDecl                decl = (ParseTree.FuncDecl           )s2;
        decllist.add(decl);
        return decllist;
    }

    List<ParseTree.FuncDecl> decllist____eps() throws Exception
    {
        return new ArrayList<>();
    }

    ParseTree.FuncDecl decl____funcdecl(Object s1) throws Exception
    {
        return (ParseTree.FuncDecl) s1;
    }

    // TODO
    ParseTree.FuncDecl fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_10X_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9) throws Exception
    {
        // 1. add function_type_info object (name, return type, params) into the global scope of env
        // 2. create a new symbol table on top of env
        // 3. add parameters into top-local scope of env
        // 4. etc.

//        Token                            id         = (Token                           )s2;
//        ParseTree.TypeSpec               rettype    = (ParseTree.TypeSpec              )s4;
//        ArrayList<ParseTree.Param>       params     = (ArrayList<ParseTree.Param>      )s6;
//        ArrayList<ParseTree.LocalDecl>   localdecls = (ArrayList<ParseTree.LocalDecl>  )s8;
//        ArrayList<ParseTree.Stmt>        stmtlist   = (ArrayList<ParseTree.Stmt>       )s9;
//        Token                            end        = (Token                           )s10;
//        // 1. add function_type_info object (name, return type, params) into the global scope of env
//        env.Add(id.lexeme, new FunctionTypeInfo(id.lexeme, rettype, params));
//        // 2. create a new symbol table on top of env
//        env = new Env(env);
//        // 3. add parameters into top-local scope of env
//        for(ParseTree.Param param : params)
//        {
//            env.Add(param.ident, param.typespec);
//        }
//        // 4. etc.
//        ParseTree.FuncDecl funcdecl = new ParseTree.FuncDecl(id.lexeme, rettype, params, localdecls, stmtlist);
//        // 5. remove the top-local scope of env
//        env = env.GetParent();
//        return funcdecl;

        return null;
    }

    // TODO
    ParseTree.FuncDecl fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_X10_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9, Object s10, Object s11, Object s12) throws Exception
    {
        // 1. check if this function has at least one return type
        // 2. etc.
        // 3. create and return funcdecl node
        Token                            id         = (Token                           )s2;
        ParseTree.TypeSpec               rettype    = (ParseTree.TypeSpec              )s4;
        ArrayList<ParseTree.Param>       params     = (ArrayList<ParseTree.Param>      )s6;
        ArrayList<ParseTree.LocalDecl>   localdecls = (ArrayList<ParseTree.LocalDecl>  )s9;
        ArrayList<ParseTree.Stmt>        stmtlist   = (ArrayList<ParseTree.Stmt>       )s11;
        ParseTree.FuncDecl funcdecl = new ParseTree.FuncDecl(id.lexeme, rettype, params, localdecls, stmtlist);
        return funcdecl;
    }

    List<ParseTree.Param> params____paramlist(Object s1) throws Exception
    {
        return (List<ParseTree.Param>) s1;
    }

    List<ParseTree.Param> params____eps() throws Exception
    {
        return new ArrayList<>();
    }

    ArrayList<ParseTree.Param> paramlist____paramlist_COMMA_param(Object s1, Object s2, Object s3) throws Exception
    {
        ArrayList<ParseTree.Param> paramlist = (ArrayList<ParseTree.Param>)s1;
        ParseTree.Param            param     = (ParseTree.Param           )s3;
        paramlist.add(param);
        return paramlist;
    }

    List<ParseTree.Param> paramlist____param(Object s1) throws Exception
    {
       ArrayList<ParseTree.Param> paramlist = new ArrayList<>();
       paramlist.add((ParseTree.Param)s1);
       return paramlist;
    }

    ParseTree.Param param____IDENT_TYPEOF_typespec(Object s1, Object s2, Object s3) throws Exception
    {
        Token              id       = (Token             )s1;
        ParseTree.TypeSpec typespec = (ParseTree.TypeSpec)s3;
        return new ParseTree.Param(id.lexeme, typespec);
    }

    ParseTree.TypeSpec typespec____primtype(Object s1)
    {
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec)s1;
        return primtype;
    }

    ParseTree.TypeSpec typespec____primtype_LBRACKET_RBRACKET(Object s1, Object s2, Object s3)
    {
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec)s1;
        Token              lbrack   = (Token             )s2;
        Token              rbrack   = (Token             )s3;
        return new ParseTree.TypeSpec(primtype.typename + lbrack.lexeme + rbrack.lexeme);
    }

    ParseTree.TypeSpec primtype____NUM(Object s1) throws Exception
    {
        Token num = (Token)s1;
        ParseTree.TypeSpec typespec = new ParseTree.TypeSpec(num.lexeme);
        return typespec;
    }

    ParseTree.TypeSpec primtype____BOOL(Object s1) throws Exception
    {
        Token bool = (Token)s1;
        ParseTree.TypeSpec typespec = new ParseTree.TypeSpec(bool.lexeme);
        return typespec;
    }

    List<ParseTree.LocalDecl> localdecls____localdecls_localdecl(Object s1, Object s2)
    {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>)s1;
        ParseTree.LocalDecl            localdecl  = (ParseTree.LocalDecl           )s2;
        localdecls.add(localdecl);
        return localdecls;
    }

    List<ParseTree.LocalDecl> localdecls____eps() throws Exception
    {
        return new ArrayList<>();
    }

    ParseTree.LocalDecl localdecl____VAR_IDENT_TYPEOF_typespec_SEMI(Object s1, Object s2, Object s3, Object s4, Object s5)
    {
        Token              id       = (Token             )s2;
        ParseTree.TypeSpec typespec = (ParseTree.TypeSpec)s4;
        ParseTree.LocalDecl localdecl = new ParseTree.LocalDecl(id.lexeme, typespec);
        // env.Put(id.lexeme, typespec); // Add to symbol table
        // localdecl.reladdr = 1;
        return localdecl;
    }

    List<ParseTree.Stmt> stmtlist____stmtlist_stmt(Object s1, Object s2) throws Exception
    {
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>)s1;
        ParseTree.Stmt            stmt     = (ParseTree.Stmt           )s2;
        stmtlist.add(stmt);
        return stmtlist;
    }

    List<ParseTree.Stmt> stmtlist____eps() throws Exception
    {
        return new ArrayList<>();
    }

    ParseTree.Stmt stmt____assignstmt(Object s1) throws Exception
    {
        // assert(s1 instanceof ParseTree.AssignStmt);
        return (ParseTree.Stmt) s1;
    }

    ParseTree.Stmt stmt____printstmt(Object s1) throws Exception
    {
        // assert(s1 instanceof ParseTree.PrintStmt);
        return (ParseTree.Stmt) s1;
    }

    ParseTree.Stmt stmt____returnstmt(Object s1) throws Exception
    {
        // assert(s1 instanceof ParseTree.ReturnStmt);
        return (ParseTree.Stmt) s1;
    }

    ParseTree.Stmt stmt____ifstmt(Object s1) throws Exception
    {
        // assert(s1 instanceof ParseTree.IfStmt);
        return (ParseTree.Stmt) s1;
    }

   ParseTree.Stmt stmt____whilestmt(Object s1) throws Exception
    {
        // assert(s1 instanceof ParseTree.WhileStmt);
        return (ParseTree.Stmt) s1;
    }

    ParseTree.Stmt stmt____compoundstmt(Object s1) throws Exception
    {
        // assert(s1 instanceof ParseTree.CompoundStmt);
        return (ParseTree.Stmt) s1;
    }

    // this is different
    ParseTree.AssignStmt assignstmt____IDENT_ASSIGN_expr_SEMI(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        // 1. check if ident.value_type matches with expr.value_type
        // 2. etc.
        // e. create and return node
        Token          id     = (Token         )s1;
        Token          assign = (Token         )s2;
        ParseTree.Expr expr   = (ParseTree.Expr)s3;
        Object id_type = env.Get(id.lexeme);
        // {
        //     // check if expr.type matches with id_type
        //     if(id_type.equals("num")
        //             && (expr instanceof ParseTree.ExprNumLit)
        //     )
        //     {
        //         Debug("ok");
        //     } // ok
        //     else if(id_type.equals("num")
        //             && (expr instanceof ParseTree.ExprFuncCall)
        //             && (env.Get(((ParseTree.ExprFuncCall)expr).ident).equals("num()"))
        //     )
        //     {} // ok
        //     else
        //     {
        //         throw new Exception("semantic error");
        //     }
        // }
        ParseTree.AssignStmt stmt = new ParseTree.AssignStmt(id.lexeme, expr);
        stmt.ident_reladdr = 1;
        return stmt;
    }

    ParseTree.AssignStmtForArray assignstmt____IDENT_LBRACKET_expr_RBRACKET_ASSIGN_expr_SEMI(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7) throws Exception
    {
        // 1. check if ident.value_type matches with expr1.value_type
        // 2. check if expr2.value_type is int
        // 3. etc.
        // 4. create and return node
        Token          id     = (Token         )s1;
        ParseTree.Expr expr1  = (ParseTree.Expr)s3;
        ParseTree.Expr expr2  = (ParseTree.Expr)s6;
        Object id_type = env.Get(id.lexeme);
        // {
        //     // check if expr1.type matches with id_type
        //     if(
        //         id_type.equals("num[]")
        //         && (expr1 instanceof ParseTree.ExprFuncCall)
        //         && (env.Get(((ParseTree.ExprFuncCall)expr1).ident).equals("num()"))
        //     ) {} // ok
        //     // check if expr2.type matches with num
        //     else if(id_type.equals("num[]") && (expr2 instanceof ParseTree.ExprNumLit)) {} // ok
        //     else
        //     {
        //         throw new Exception("semantic error");
        //     }
        // }
        ParseTree.AssignStmtForArray stmt = new ParseTree.AssignStmtForArray(id.lexeme, expr1, expr2);
        stmt.ident_reladdr = 1;
        return stmt;
    }

    ParseTree.PrintStmt printstmt____PRINT_expr_SEMI(Object s1, Object s2, Object s3) throws Exception
    {
        Token          print = (Token         )s1;
        ParseTree.Expr expr  = (ParseTree.Expr)s2;
        Token          semi  = (Token         )s3;
        return new ParseTree.PrintStmt(expr);
    }

    ParseTree.ReturnStmt returnstmt____RETURN_expr_SEMI(Object s1, Object s2, Object s3) throws Exception
    {
        Token          ret   = (Token         )s1;
        ParseTree.Expr expr = (ParseTree.Expr)s2;
        Token          semi  = (Token         )s3;
        return new ParseTree.ReturnStmt(expr);
    }

    ParseTree.IfStmt ifstmt____IF_expr_THEN_stmtlist_ELSE_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7) throws Exception
    {
        Token          iftok = (Token         )s1;
        ParseTree.Expr expr = (ParseTree.Expr)s2;
        Token          then  = (Token         )s3;
        ArrayList<ParseTree.Stmt> stmtlist1 = (ArrayList<ParseTree.Stmt>)s4;
        Token          else_ = (Token         )s5;
        ArrayList<ParseTree.Stmt> stmtlist2 = (ArrayList<ParseTree.Stmt>)s6;
        Token          end   = (Token         )s7;
        return new ParseTree.IfStmt(expr, stmtlist1, stmtlist2);
    }

    ParseTree.WhileStmt whilestmt____WHILE_expr_BEGIN_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception
    {
        ParseTree.Expr expr = (ParseTree.Expr)s2;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>)s4;
        return new ParseTree.WhileStmt(expr, stmtlist);
    }

    ParseTree.CompoundStmt compoundstmt____BEGIN_localdecls_stmtlist_END(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>)s2;
        ArrayList<ParseTree.Stmt>      stmtlist   = (ArrayList<ParseTree.Stmt>     )s3;
        return new ParseTree.CompoundStmt(localdecls, stmtlist);
    }

    List<ParseTree.Arg> args____arglist(Object s1) throws Exception
    {
        return (List<ParseTree.Arg>) s1;
    }

    List<ParseTree.Arg> args____eps() throws Exception
    {
        return new ArrayList<>();
    }

    List<ParseTree.Arg> arglist____arglist_COMMA_expr(Object s1, Object s2, Object s3) throws Exception
    {
        ArrayList<ParseTree.Arg> arglist = (ArrayList<ParseTree.Arg>)s1;
        ParseTree.Expr            expr    = (ParseTree.Expr           )s3;
        arglist.add(new ParseTree.Arg(expr));
        return arglist;
    }

    List<ParseTree.Arg> arglist____expr(Object s1) throws Exception
    {
        ParseTree.Expr            expr    = (ParseTree.Expr           )s1;
        ArrayList<ParseTree.Arg> arglist = new ArrayList<>();
        arglist.add(new ParseTree.Arg(expr));
        return arglist;
    }

    ParseTree.ExprAdd expr____expr_ADD_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprSub expr____expr_SUB_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprMul expr____expr_MUL_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprDiv expr____expr_DIV_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprMod expr____expr_MOD_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprEq expr____expr_EQ_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprNe expr____expr_NE_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprLe expr____expr_LE_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprLt expr____expr_LT_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprGe expr____expr_GE_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprGt expr____expr_GT_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprAnd expr____expr_AND_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprOr expr____expr_OR_expr(Object s1, Object s2, Object s3) throws Exception
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

    ParseTree.ExprNot expr____NOT_expr(Object s1, Object s2) throws Exception
    {
    // 1. check if expr1.value_type matches with the expr2.value_type
        // 2. etc.
        // 3. create and return node that has value_type
        Token          oper  = (Token         )s1;
        ParseTree.Expr expr1 = (ParseTree.Expr)s2;
        // check if expr1.type matches with expr2.type
        return new ParseTree.ExprNot(expr1);
    }

    ParseTree.ExprParen expr____LPAREN_expr_RPAREN(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. create and return node whose value_type is the same to the expr.value_type
        Token          lparen = (Token         )s1;
        ParseTree.Expr expr   = (ParseTree.Expr)s2;
        Token          rparen = (Token         )s3;
        return new ParseTree.ExprParen(expr);
    }

    ParseTree.ExprIdent expr____IDENT(Object s1) throws Exception
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

    ParseTree.ExprNumLit expr____NUMLIT(Object s1) throws Exception
    {
        // 1. create and return node that has int type
        Token token = (Token)s1;
        double value = token.lexeme.contains(".") ? Double.parseDouble(token.lexeme) : Integer.parseInt(token.lexeme);
//        double value = Integer.parseInt(token.lexeme);
        return new ParseTree.ExprNumLit(value);
    }

    ParseTree.ExprBoolLit expr____BOOLLIT(Object s1) throws Exception
    {
        // 1. create and return node that has int type
        Token token = (Token)s1;
        boolean value = Boolean.parseBoolean(token.lexeme);
        return new ParseTree.ExprBoolLit(value);
    }

    ParseTree.ExprFuncCall expr____IDENT_LPAREN_args_RPAREN(Object s1, Object s2, Object s3, Object s4) throws Exception
    {
        // 1. check if id.lexeme can be found in chained symbol tables
        // 2. check if it is function type
        // 3. check if the number and types of env(id.lexeme).params match with those of args
        // 4. etc.
        // 5. create and return node that has the value_type of env(id.lexeme).return_type
        Token                    id   = (Token                   )s1;
        ArrayList<ParseTree.Arg> args = (ArrayList<ParseTree.Arg>)s3;
        Object func_attr = env.Get(id.lexeme);
        // {
        //     // check if argument types match with function param types
        //     if(env.Get(id.lexeme).equals("num()")
        //         && (args.size() == 0)
        //         )
        //     {} // ok
        //     else
        //     {
        //         throw new Exception("semantic error");
        //     }
        // }
        return new ParseTree.ExprFuncCall(id.lexeme, args);
    }

    ParseTree.ExprNewArray expr____NEW_primtype_LBRACKET_expr_RBRACKET(Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception
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

    ParseTree.ExprArrayElem expr____IDENT_LBRACKET_expr_RBRACKET(Object s1, Object s2, Object s3, Object s4) throws Exception
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
        // {
        //     // check if expr.type matches with num
        //     if(id_type.equals("num[]")
        //         && (expr instanceof ParseTree.ExprFuncCall)
        //         && (env.Get(((ParseTree.ExprFuncCall)expr).ident).equals("num()"))
        //         )
        //     {} // ok
        //     else
        //     {
        //         throw new Exception("semantic error");
        //     }
        // }
        return new ParseTree.ExprArrayElem(id.lexeme, expr);
    }

    ParseTree.ExprArraySize expr____IDENT_DOT_SIZE(Object s1, Object s2, Object s3) throws Exception
    {
        // 1. check if ident.value_type is primtype[] type
        // 2. create and return node that has int type
        Token id   = (Token)s1;
        Token dot  = (Token)s2;
        Token size = (Token)s3;
        Object id_type = env.Get(id.lexeme);
        // {
        //     // check if id_type matches with num[]
        //     if(id_type.equals("num[]")
        //         )
        //     {} // ok
        //     else
        //     {
        //         throw new Exception("semantic error");
        //     }
        // }
        return new ParseTree.ExprArraySize(id.lexeme);
    }

}
