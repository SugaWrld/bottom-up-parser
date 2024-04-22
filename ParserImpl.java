import java.util.*;

// TODO: handle not type check properly
// TODO: fix relative address

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class ParserImpl {
    public static Boolean _debug = true;

    void Debug(String message) {
        if (_debug) System.out.println(message);
    }

    Env env = new Env(null);
    ParseTree.Program parsetree_program = null;

    private void nextEnv() {
        Env tempEnv = new Env(this.env);
        this.env = tempEnv;
    }

    ParseTree.Program program____decllist(Object s1) throws Exception {
        ArrayList<ParseTree.FuncDecl> decllist = (ArrayList<ParseTree.FuncDecl>) s1;
        parsetree_program = new ParseTree.Program(decllist);

        // check if there is at least one main function with no parameters and returns num
        boolean hasMain = decllist.stream()
                .anyMatch(decl ->
                        decl.ident.equals("main")
                        && decl.params.isEmpty()
                        && decl.rettype.typename.equals("num")
                );
        if(!hasMain) throw new Exception("semantic error: improper main function");

        return parsetree_program;
    }

    List<ParseTree.FuncDecl> decllist____decllist_decl(Object s1, Object s2) throws Exception {
        ArrayList<ParseTree.FuncDecl> decllist = (ArrayList<ParseTree.FuncDecl>) s1;
        ParseTree.FuncDecl decl = (ParseTree.FuncDecl) s2;
        decllist.add(decl);
        return decllist;
    }

    List<ParseTree.FuncDecl> decllist____eps() throws Exception {
        return new ArrayList<>();
    }

    ParseTree.FuncDecl decl____funcdecl(Object s1) throws Exception {
        ParseTree.FuncDecl funcdecl = (ParseTree.FuncDecl) s1;
        return funcdecl;
    }

    ParseTree.FuncDecl fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_10X_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9) throws Exception {
//        env.Put();
        nextEnv();
        return null;
    }

    ParseTree.FuncDecl fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_X10_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9, Object s10, Object s11, Object s12) throws Exception {
        Token id = (Token) s2;
        ParseTree.TypeSpec rettype = (ParseTree.TypeSpec) s4;
        ArrayList<ParseTree.Param> params = (ArrayList<ParseTree.Param>) s6;
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s9;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>) s11;
        ParseTree.FuncDecl funcdecl = new ParseTree.FuncDecl(id.lexeme, rettype, params, localdecls, stmtlist);
        return funcdecl;
    }

    List<ParseTree.Param> params____paramlist(Object s1) throws Exception {
        return (List<ParseTree.Param>) s1;
    }

    List<ParseTree.Param> params____eps() throws Exception {
        return new ArrayList<>();
    }

    ArrayList<ParseTree.Param> paramlist____paramlist_COMMA_param(Object s1, Object s2, Object s3) throws Exception {
        ArrayList<ParseTree.Param> paramlist = (ArrayList<ParseTree.Param>) s1;
        ParseTree.Param param = (ParseTree.Param) s3;
        paramlist.add(param);
        return paramlist;
    }

    List<ParseTree.Param> paramlist____param(Object s1) throws Exception {
        ArrayList<ParseTree.Param> paramlist = new ArrayList<>();
        ParseTree.Param param = (ParseTree.Param) s1;
        paramlist.add(param);
        return paramlist;
    }

    ParseTree.Param param____IDENT_TYPEOF_typespec(Object s1, Object s2, Object s3) throws Exception {
        Token id = (Token) s1;
        ParseTree.TypeSpec typespec = (ParseTree.TypeSpec) s3;
        return new ParseTree.Param(id.lexeme, typespec);
    }

    ParseTree.TypeSpec typespec____primtype(Object s1) {
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec) s1;
        return primtype;
    }

    ParseTree.TypeSpec typespec____primtype_LBRACKET_RBRACKET(Object s1, Object s2, Object s3) {
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec) s1;
        Token lbrack = (Token) s2;
        Token rbrack = (Token) s3;
        return new ParseTree.TypeSpec(primtype.typename + lbrack.lexeme + rbrack.lexeme);
    }

    ParseTree.TypeSpec primtype____NUM(Object s1) throws Exception {
        Token num = (Token) s1;
        ParseTree.TypeSpec typespec = new ParseTree.TypeSpec(num.lexeme);
        return typespec;
    }

    ParseTree.TypeSpec primtype____BOOL(Object s1) throws Exception {
        Token bool = (Token) s1;
        ParseTree.TypeSpec typespec = new ParseTree.TypeSpec(bool.lexeme);
        return typespec;
    }

    List<ParseTree.LocalDecl> localdecls____localdecls_localdecl(Object s1, Object s2) {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s1;
        ParseTree.LocalDecl localdecl = (ParseTree.LocalDecl) s2;
        localdecls.add(localdecl);

        // put local declarations into the environment
        localdecls.forEach(decl -> env.Put(decl.ident, decl.typespec.typename));

        return localdecls;
    }

    List<ParseTree.LocalDecl> localdecls____eps() throws Exception {
        return new ArrayList<>();
    }

    ParseTree.LocalDecl localdecl____VAR_IDENT_TYPEOF_typespec_SEMI(Object s1, Object s2, Object s3, Object s4, Object s5) {
        Token id = (Token) s2;
        ParseTree.TypeSpec typespec = (ParseTree.TypeSpec) s4;
        ParseTree.LocalDecl localdecl = new ParseTree.LocalDecl(id.lexeme, typespec);

        localdecl.reladdr = env.address;

        return localdecl;
    }

    List<ParseTree.Stmt> stmtlist____stmtlist_stmt(Object s1, Object s2) throws Exception {
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>) s1;
        ParseTree.Stmt stmt = (ParseTree.Stmt) s2;
        stmtlist.add(stmt);
        return stmtlist;
    }

    List<ParseTree.Stmt> stmtlist____eps() throws Exception {
        return new ArrayList<>();
    }

    ParseTree.Stmt stmt____assignstmt(Object s1) throws Exception {
        try {
            ParseTree.AssignStmt stmt = (ParseTree.AssignStmt) s1;
            return stmt;
        } catch(ClassCastException e) {
            ParseTree.AssignStmtForArray stmt = (ParseTree.AssignStmtForArray) s1;
            return stmt;
        }
    }

    ParseTree.Stmt stmt____printstmt(Object s1) throws Exception {
        ParseTree.PrintStmt stmt = (ParseTree.PrintStmt) s1;
        return stmt;
    }

    ParseTree.Stmt stmt____returnstmt(Object s1) throws Exception {
        ParseTree.ReturnStmt stmt = (ParseTree.ReturnStmt) s1;
        return stmt;
    }

    ParseTree.Stmt stmt____ifstmt(Object s1) throws Exception {
        ParseTree.IfStmt stmt = (ParseTree.IfStmt) s1;
        return stmt;
    }

    ParseTree.Stmt stmt____whilestmt(Object s1) throws Exception {
        ParseTree.WhileStmt stmt = (ParseTree.WhileStmt) s1;
        return stmt;
    }

    ParseTree.Stmt stmt____compoundstmt(Object s1) throws Exception {
        ParseTree.CompoundStmt stmt = (ParseTree.CompoundStmt) s1;
        return stmt;
    }

    ParseTree.AssignStmt assignstmt____IDENT_ASSIGN_expr_SEMI(Object s1, Object s2, Object s3, Object s4) throws Exception {
        Token id = (Token) s1;
        ParseTree.Expr expr = (ParseTree.Expr) s3;
        ParseTree.AssignStmt stmt = new ParseTree.AssignStmt(id.lexeme, expr);
        stmt.ident_reladdr = env.address;

        // check if id's and expr's types are compatible
        Object id_type = env.Get(id.lexeme);
        exprCheckType(expr, (ParseTree.Expr) id_type);

        return stmt;
    }

    ParseTree.AssignStmtForArray assignstmt____IDENT_LBRACKET_expr_RBRACKET_ASSIGN_expr_SEMI(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7) throws Exception {
        Token id = (Token) s1;
        ParseTree.Expr expr1 = (ParseTree.Expr) s3;
        ParseTree.Expr expr2 = (ParseTree.Expr) s6;
        ParseTree.AssignStmtForArray stmt = new ParseTree.AssignStmtForArray(id.lexeme, expr1, expr2);
        return stmt;
    }

    ParseTree.PrintStmt printstmt____PRINT_expr_SEMI(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        return new ParseTree.PrintStmt(expr);
    }

    ParseTree.ReturnStmt returnstmt____RETURN_expr_SEMI(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        return new ParseTree.ReturnStmt(expr);
    }

    ParseTree.IfStmt ifstmt____IF_expr_THEN_stmtlist_ELSE_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        ArrayList<ParseTree.Stmt> stmtlist1 = (ArrayList<ParseTree.Stmt>) s4;
        ArrayList<ParseTree.Stmt> stmtlist2 = (ArrayList<ParseTree.Stmt>) s6;
        return new ParseTree.IfStmt(expr, stmtlist1, stmtlist2);
    }

    ParseTree.WhileStmt whilestmt____WHILE_expr_BEGIN_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>) s4;
        return new ParseTree.WhileStmt(expr, stmtlist);
    }

    ParseTree.CompoundStmt compoundstmt____BEGIN_localdecls_stmtlist_END(Object s1, Object s2, Object s3, Object s4) throws Exception {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s2;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>) s3;
        return new ParseTree.CompoundStmt(localdecls, stmtlist);
    }

    List<ParseTree.Arg> args____arglist(Object s1) throws Exception {
        List<ParseTree.Arg> arglist = (List<ParseTree.Arg>) s1;
        return arglist;
    }

    List<ParseTree.Arg> args____eps() throws Exception {
        return new ArrayList<>();
    }

    List<ParseTree.Arg> arglist____arglist_COMMA_expr(Object s1, Object s2, Object s3) throws Exception {
        ArrayList<ParseTree.Arg> arglist = (ArrayList<ParseTree.Arg>) s1;
        ParseTree.Expr expr = (ParseTree.Expr) s3;
        arglist.add(new ParseTree.Arg(expr));
        return arglist;
    }

    List<ParseTree.Arg> arglist____expr(Object s1) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s1;
        ArrayList<ParseTree.Arg> arglist = new ArrayList<>();
        arglist.add(new ParseTree.Arg(expr));
        return arglist;
    }

    ParseTree.ExprAdd expr____expr_ADD_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprAdd(expr1, expr2);
    }

    ParseTree.ExprSub expr____expr_SUB_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprSub(expr1, expr2);
    }

    ParseTree.ExprMul expr____expr_MUL_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprMul(expr1, expr2);
    }

    ParseTree.ExprDiv expr____expr_DIV_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprDiv(expr1, expr2);
    }

    ParseTree.ExprMod expr____expr_MOD_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprMod(expr1, expr2);
    }

    ParseTree.ExprEq expr____expr_EQ_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprEq(expr1, expr2);
    }

    ParseTree.ExprNe expr____expr_NE_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprNe(expr1, expr2);
    }

    ParseTree.ExprLe expr____expr_LE_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprLe(expr1, expr2);
    }

    ParseTree.ExprLt expr____expr_LT_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprLt(expr1, expr2);
    }

    ParseTree.ExprGe expr____expr_GE_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprGe(expr1, expr2);
    }

    ParseTree.ExprGt expr____expr_GT_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprGt(expr1, expr2);
    }

    ParseTree.ExprAnd expr____expr_AND_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprAnd(expr1, expr2);
    }

    ParseTree.ExprOr expr____expr_OR_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;
        exprCheckType(expr1, expr2);
        return new ParseTree.ExprOr(expr1, expr2);
    }

    ParseTree.ExprNot expr____NOT_expr(Object s1, Object s2) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        return new ParseTree.ExprNot(expr);
    }

    ParseTree.ExprParen expr____LPAREN_expr_RPAREN(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        return new ParseTree.ExprParen(expr);
    }

    ParseTree.ExprIdent expr____IDENT(Object s1) throws Exception {
        Token id = (Token) s1;
        ParseTree.ExprIdent expr = new ParseTree.ExprIdent(id.lexeme);
        expr.reladdr = env.address;
        return expr;
    }

    ParseTree.ExprNumLit expr____NUMLIT(Object s1) throws Exception {
        Token token = (Token) s1;
        double value = token.lexeme.contains(".") ? Double.parseDouble(token.lexeme) : Integer.parseInt(token.lexeme);
        return new ParseTree.ExprNumLit(value);
    }

    ParseTree.ExprBoolLit expr____BOOLLIT(Object s1) throws Exception {
        Token token = (Token) s1;
        boolean value = Boolean.parseBoolean(token.lexeme);
        return new ParseTree.ExprBoolLit(value);
    }

    ParseTree.ExprFuncCall expr____IDENT_LPAREN_args_RPAREN(Object s1, Object s2, Object s3, Object s4) throws Exception {
        Token id = (Token) s1;
        ArrayList<ParseTree.Arg> args = (ArrayList<ParseTree.Arg>) s3;
        return new ParseTree.ExprFuncCall(id.lexeme, args);
    }

    ParseTree.ExprNewArray expr____NEW_primtype_LBRACKET_expr_RBRACKET(Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception {
        ParseTree.TypeSpec primtype = (ParseTree.TypeSpec) s2;
        ParseTree.Expr expr = (ParseTree.Expr) s4;
        return new ParseTree.ExprNewArray(primtype, expr);
    }

    ParseTree.ExprArrayElem expr____IDENT_LBRACKET_expr_RBRACKET(Object s1, Object s2, Object s3, Object s4) throws Exception {
        Token id = (Token) s1;
        ParseTree.Expr expr = (ParseTree.Expr) s3;
        return new ParseTree.ExprArrayElem(id.lexeme, expr);
    }

    ParseTree.ExprArraySize expr____IDENT_DOT_SIZE(Object s1, Object s2, Object s3) throws Exception {
        Token id = (Token) s1;
        return new ParseTree.ExprArraySize(id.lexeme);
    }

    private void exprCheckType(ParseTree.Expr expr1, ParseTree.Expr expr2) throws Exception {
        List<Class<?>> classesToCheck = Arrays.asList(
                ParseTree.ExprAdd.class, ParseTree.ExprSub.class, ParseTree.ExprMul.class,
                ParseTree.ExprDiv.class, ParseTree.ExprMod.class, ParseTree.ExprParen.class
        );

        // expr1 = num, expr2 = num
        if((expr1 instanceof ParseTree.ExprNumLit && expr2 instanceof ParseTree.ExprNumLit)) {}

        // expr1 = bool, expr2 = bool
        else if(expr1 instanceof ParseTree.ExprBoolLit && expr2 instanceof ParseTree.ExprBoolLit) {}

        // expr1 = num, expr2 = ident
        else if(expr1 instanceof ParseTree.ExprNumLit
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("num"))
        ) {}

        // expr1 = ident, expr2 = num
        else if(expr2 instanceof ParseTree.ExprNumLit
            && (expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("num"))
        ) {}

        // expr1 = bool, expr2 = ident
        else if(expr1 instanceof ParseTree.ExprBoolLit
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("bool"))
        ) {}

        // expr1 = ident, expr2 = bool
        else if(expr2 instanceof ParseTree.ExprBoolLit
            && (expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("bool"))
        ) {}

        // expr1 = ident, expr2 = ident
        else if((expr1 instanceof ParseTree.ExprIdent && expr2 instanceof ParseTree.ExprIdent)
            && (env.Get(((ParseTree.ExprIdent)expr1).ident).equals(env.Get(((ParseTree.ExprIdent)expr2).ident)))
        ) {}

        // expr1 or expr2 instance of classesToCheck
        else if(classesToCheck.stream().anyMatch(clazz -> clazz.isInstance(expr1) || clazz.isInstance(expr2))) {}

        // throw error
        else throw new Exception("semantic error: incompatible types");
    }

}
