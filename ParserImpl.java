import java.util.*;

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

    private void putGlobal(String functionName, ParseTreeInfo.FuncDeclInfo funcDeclInfo) {
        Env tempEnv = this.env;
        while(tempEnv.prev != null)
            tempEnv = tempEnv.prev;

        Env tempEnv2 = new Env(tempEnv);
        tempEnv2.prev = null;
        tempEnv.prev = tempEnv2;
        tempEnv2.Put(functionName, funcDeclInfo);
    }

    private void popEnv() {
        this.env = this.env.prev;
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
        if(!hasMain)
            throw new Exception("The program must have one main function that returns num value and has no parameters.");

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

    ParseTree.FuncDecl fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_10X_stmtlist_END(
            Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7, Object s8, Object s9
    ) throws Exception {
        Token functionName = (Token) s2;
        ParseTree.TypeSpec returnType = (ParseTree.TypeSpec) s4;
        ArrayList<ParseTree.Param> params = (ArrayList<ParseTree.Param>) s6;
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s9;

        ParseTree.FuncDecl funcdecl = new ParseTree.FuncDecl(functionName.lexeme, returnType, params, null, null);
        funcdecl.info.functionName = functionName;
        funcdecl.info.returnType = returnType;
        funcdecl.info.params = params;

        putGlobal(functionName.lexeme, funcdecl.info);
        nextEnv();
        params.forEach(param -> env.Put(param.ident, param.typespec.typename));

        return null;
    }

    ParseTree.FuncDecl fundecl____FUNC_IDENT_TYPEOF_typespec_LPAREN_params_RPAREN_BEGIN_localdecls_X10_stmtlist_END(
            Object s1, Object s2, Object s3, Object s4, Object s5, Object s6,
            Object s7, Object s8, Object s9, Object s10, Object s11, Object s12
    ) throws Exception {
        Token id = (Token) s2;
        ParseTree.TypeSpec rettype = (ParseTree.TypeSpec) s4;
        ArrayList<ParseTree.Param> params = (ArrayList<ParseTree.Param>) s6;
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s9;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>) s11;
        ParseTree.FuncDecl funcdecl = new ParseTree.FuncDecl(id.lexeme, rettype, params, localdecls, stmtlist);

        popEnv();

        // check if the return statements are compatible
        ParseTreeInfo.StmtStmtInfo retType = new ParseTreeInfo.StmtStmtInfo();
        for(ParseTree.Stmt stmt: stmtlist) {
            if(stmt.info.retType != null) {
                retType = stmt.info;
                break;
            }
        }
        if(!rettype.typename.equals(retType.retType)) {
            throw new Exception(
                "[Error at " + retType.lineno + ":" + retType.colno + "] Function " + id.lexeme + "() should return " + rettype.typename + " value, instead of " + retType.retType + " value."
            );
        }

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

    List<ParseTree.LocalDecl> localdecls____localdecls_localdecl(Object s1, Object s2) throws Exception {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s1;
        ParseTree.LocalDecl localdecl = (ParseTree.LocalDecl) s2;
        localdecls.add(localdecl);

        // put local declarations into the environment
        if(env.table.get(localdecl.ident) != null)
            throw new Exception("[Error at " + localdecl.info.lineno + ":" + localdecl.info.colno + "] Identifier " + localdecl.ident + " is already defined.");
        env.table.put(localdecl.ident, localdecl.typespec.typename);

        return localdecls;
    }

    List<ParseTree.LocalDecl> localdecls____eps() throws Exception {
        return new ArrayList<>();
    }

    ParseTree.LocalDecl localdecl____VAR_IDENT_TYPEOF_typespec_SEMI(Object s1, Object s2, Object s3, Object s4, Object s5) {
        Token id = (Token) s2;
        ParseTree.TypeSpec typespec = (ParseTree.TypeSpec) s4;
        ParseTree.LocalDecl localdecl = new ParseTree.LocalDecl(id.lexeme, typespec);

        localdecl.info.lineno = id.lineno;
        localdecl.info.colno = id.colno;
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

        // check if expr is an identifier and if its defined
        if(expr instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr).ident) == null)
            throw new Exception("[Error at " + expr.info.lineno + ":" + expr.info.colno + "] Variable " + ((ParseTree.ExprIdent) expr).ident + " is not defined.");

        // check if ident's type matches with expr's type
        Object idType = env.Get(id.lexeme);
        Object exprType = determineType(expr);
        if(!idType.equals(exprType))
            throw new Exception("[Error at " + id.lineno + ":" + id.colno + "] Variable " + id.lexeme + " should have " + idType + " value, instead of " + exprType + " value.");

        return stmt;
    }

    ParseTree.AssignStmtForArray assignstmt____IDENT_LBRACKET_expr_RBRACKET_ASSIGN_expr_SEMI(
            Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7
    ) throws Exception {
        Token id = (Token) s1;
        ParseTree.Expr expr1 = (ParseTree.Expr) s3;
        ParseTree.Expr expr2 = (ParseTree.Expr) s6;
        ParseTree.AssignStmtForArray stmt = new ParseTree.AssignStmtForArray(id.lexeme, expr1, expr2);

        // check if its defined
        if(env.Get(id.lexeme) == null)
            throw new Exception("[Error at " + id.lineno + ":" + id.colno + "] Array " + id.lexeme + " is not defined.");

        // check if index value is a num or an ident
        if(expr1 instanceof ParseTree.ExprIdent && !env.Get(((ParseTree.ExprIdent)expr1).ident).equals("num"))
            throw new Exception("[Error at " + expr1.info.lineno + ":" + expr1.info.colno + "] Array index must be num value.");
        else if(!(expr1 instanceof ParseTree.ExprIdent) && !determineType(expr1).equals("num"))
            throw new Exception("[Error at " + expr1.info.lineno + ":" + expr1.info.colno + "] Array index must be num value.");

        // check if element value is the same type as the array
        String idType = (String) env.Get(id.lexeme);
        String exprType = determineType(expr2);
        if(!idType.contains(exprType))
            throw new Exception(
                    "[Error at " + id.lineno + ":" + id.colno + "] Element of array " + id.lexeme + " should have "
                    + idType.substring(0, idType.length() - 2) + " value, instead of "
                    + exprType + " value.");

        return stmt;
    }

    ParseTree.PrintStmt printstmt____PRINT_expr_SEMI(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        return new ParseTree.PrintStmt(expr);
    }

    ParseTree.ReturnStmt returnstmt____RETURN_expr_SEMI(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        ParseTree.ReturnStmt returnStmt = new ParseTree.ReturnStmt(expr);

        returnStmt.info.retType = determineType(expr);
        returnStmt.info.lineno = expr.info.lineno;
        returnStmt.info.colno = expr.info.colno;

        return returnStmt;
    }

    ParseTree.IfStmt ifstmt____IF_expr_THEN_stmtlist_ELSE_stmtlist_END(
        Object s1, Object s2, Object s3, Object s4, Object s5, Object s6, Object s7)
    throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        ArrayList<ParseTree.Stmt> stmtlist1 = (ArrayList<ParseTree.Stmt>) s4;
        ArrayList<ParseTree.Stmt> stmtlist2 = (ArrayList<ParseTree.Stmt>) s6;

        // check if expr is a bool
        if(!determineType(expr).equals("bool"))
            throw new Exception("[Error at " + expr.info.lineno + ":" + expr.info.colno + "] Condition of if or while statement should be bool value.");

        return new ParseTree.IfStmt(expr, stmtlist1, stmtlist2);
    }

    ParseTree.WhileStmt whilestmt____WHILE_expr_BEGIN_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>) s4;

        // check if expr is a bool
        if(!determineType(expr).equals("bool"))
            throw new Exception("[Error at " + expr.info.lineno + ":" + expr.info.colno + "] Condition of if or while statement should be bool value.");

        return new ParseTree.WhileStmt(expr, stmtlist);
    }

    ParseTree.CompoundStmt compundstmt____BEGIN_localdecls_10X(Object s1, Object s2) {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s2;

        nextEnv();

        return new ParseTree.CompoundStmt(localdecls, null);
    }

    ParseTree.CompoundStmt compoundstmt____BEGIN_localdecls_stmtlist_END(Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception {
        ArrayList<ParseTree.LocalDecl> localdecls = (ArrayList<ParseTree.LocalDecl>) s2;
        ArrayList<ParseTree.Stmt> stmtlist = (ArrayList<ParseTree.Stmt>) s4;

        popEnv();
        popEnv();

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

    // TODO: fail_04d
    ParseTree.ExprAdd expr____expr_ADD_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token add = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + add.lineno + ":" + add.colno + "] Binary operation + cannot be used with "
                + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprAdd(expr1, expr2);
    }

    ParseTree.ExprSub expr____expr_SUB_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token sub = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + sub.lineno + ":" + sub.colno + "] Binary operation - cannot be used "
                    + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprSub(expr1, expr2);
    }

    ParseTree.ExprMul expr____expr_MUL_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token mul = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + mul.lineno + ":" + mul.colno + "] Binary operation * cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprMul(expr1, expr2);
    }

    ParseTree.ExprDiv expr____expr_DIV_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token div = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + div.lineno + ":" + div.colno + "] Binary operation / cannot be used " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprDiv(expr1, expr2);
    }

    ParseTree.ExprMod expr____expr_MOD_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token mod = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + mod.lineno + ":" + mod.colno + "] Binary operation % cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprMod(expr1, expr2);
    }

    ParseTree.ExprEq expr____expr_EQ_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token eq = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2) && !areBothBool(expr1, expr2))
            throw new Exception("[Error at " + eq.lineno + ":" + eq.colno + "] Binary operation = cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprEq(expr1, expr2);
    }

    ParseTree.ExprNe expr____expr_NE_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token ne = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + ne.lineno + ":" + ne.colno + "] Binary operation <> cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprNe(expr1, expr2);
    }

    ParseTree.ExprLe expr____expr_LE_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token le = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + le.lineno + ":" + le.colno + "] Binary operation <= cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprLe(expr1, expr2);
    }

    ParseTree.ExprLt expr____expr_LT_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token lt = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + lt.lineno + ":" + lt.colno + "] Binary operation < cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprLt(expr1, expr2);
    }

    ParseTree.ExprGe expr____expr_GE_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token ge = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + ge.lineno + ":" + ge.colno + "] Binary operation >= cannot be used with "
                + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprGe(expr1, expr2);
    }

    ParseTree.ExprGt expr____expr_GT_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token gt = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothNum(expr1, expr2))
            throw new Exception("[Error at " + gt.lineno + ":" + gt.colno + "] Binary operation > cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprGt(expr1, expr2);
    }

    ParseTree.ExprAnd expr____expr_AND_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token and = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothBool(expr1, expr2))
            throw new Exception("[Error at " + and.lineno + ":" + and.colno + "] Binary operation and cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprAnd(expr1, expr2);
    }

    ParseTree.ExprOr expr____expr_OR_expr(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr1 = (ParseTree.Expr) s1;
        Token or = (Token) s2;
        ParseTree.Expr expr2 = (ParseTree.Expr) s3;

        if(!areBothBool(expr1, expr2))
            throw new Exception("[Error at " + or.lineno + ":" + or.colno + "] Binary operation or cannot be used with " + determineType(expr1) + " and " +  determineType(expr2) + " values.");

        return new ParseTree.ExprOr(expr1, expr2);
    }

    ParseTree.ExprNot expr____NOT_expr(Object s1, Object s2) throws Exception {
        Token not = (Token) s1;
        ParseTree.Expr expr = (ParseTree.Expr) s2;

        if(!isBool(expr))
            throw new Exception("[Error at " + not.lineno + ":" + not.colno + "] Unary operation not cannot be used with num value.");

        return new ParseTree.ExprNot(expr);
    }

    ParseTree.ExprParen expr____LPAREN_expr_RPAREN(Object s1, Object s2, Object s3) throws Exception {
        ParseTree.Expr expr = (ParseTree.Expr) s2;
        ParseTree.ExprParen exprParen = new ParseTree.ExprParen(expr);

        String type = determineType(expr);
        if(type.equals("num")) exprParen.info.type = "num";
        else if(type.equals("bool")) exprParen.info.type = "bool";

        return exprParen;
    }

    ParseTree.ExprIdent expr____IDENT(Object s1) throws Exception {
        Token id = (Token) s1;
        ParseTree.ExprIdent expr = new ParseTree.ExprIdent(id.lexeme);

        expr.info.lineno = id.lineno;
        expr.info.colno = id.colno;
        expr.reladdr = env.address;
        return expr;
    }

    ParseTree.ExprNumLit expr____NUMLIT(Object s1) throws Exception {
        Token token = (Token) s1;
        double value = token.lexeme.contains(".") ? Double.parseDouble(token.lexeme) : Integer.parseInt(token.lexeme);
        ParseTree.ExprNumLit exprNumLit = new ParseTree.ExprNumLit(value);

        exprNumLit.info.lineno = token.lineno;
        exprNumLit.info.colno = token.colno;

        return exprNumLit;
    }

    ParseTree.ExprBoolLit expr____BOOLLIT(Object s1) throws Exception {
        Token token = (Token) s1;
        boolean value = Boolean.parseBoolean(token.lexeme);
        ParseTree.ExprBoolLit exprBoolLit = new ParseTree.ExprBoolLit(value);

        exprBoolLit.info.lineno = token.lineno;
        exprBoolLit.info.colno = token.colno;

        return exprBoolLit;
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

        if(!(determineType(expr).equals("num")))
            throw new Exception("[Error at " + expr.info.lineno + ":" + expr.info.colno + "] Array index must be num value.");

        return new ParseTree.ExprArrayElem(id.lexeme, expr);
    }

    ParseTree.ExprArraySize expr____IDENT_DOT_SIZE(Object s1, Object s2, Object s3) throws Exception {
        Token id = (Token) s1;
        return new ParseTree.ExprArraySize(id.lexeme);
    }

    private boolean isBool(ParseTree.Expr expr) throws Exception {

        // expr = bool
        if(expr instanceof ParseTree.ExprBoolLit) return true;

        // expr = ident
        else if(expr instanceof ParseTree.ExprIdent
                && env.Get(((ParseTree.ExprIdent) expr).ident).equals("bool")
        ) return true;

        // expr = paren
        else if(expr instanceof ParseTree.ExprParen && expr.info.type.equals("bool")) return true;

        else return false;
    }

    private boolean areBothNum(ParseTree.Expr expr1, ParseTree.Expr expr2) throws Exception {
        // expr1 = num, expr2 = num
        if(expr1 instanceof ParseTree.ExprNumLit && expr2 instanceof ParseTree.ExprNumLit)
            return true;

        // expr1 = num, expr2 = ident
        else if(expr1 instanceof ParseTree.ExprNumLit
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("num"))
        ) return true;

        // expr1 = ident, expr2 = num
        else if(expr2 instanceof ParseTree.ExprNumLit
            && (expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("num"))
        ) return true;

        // expr1 = ident, expr2 = ident
        else if(expr1 instanceof ParseTree.ExprIdent && expr2 instanceof ParseTree.ExprIdent)
            return env.Get(((ParseTree.ExprIdent)expr1).ident).equals(env.Get(((ParseTree.ExprIdent)expr2).ident));

        // if expr1 is paren, expr2 is num
        else if(expr1 instanceof ParseTree.ExprParen
            && expr1.info.type.equals("num")
            && expr2 instanceof ParseTree.ExprNumLit
        ) return true;

        // if expr1 is paren, expr2 is ident
        else if(expr1 instanceof ParseTree.ExprParen && expr1.info.type.equals("num")
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("num"))
        ) return true;

        // if expr1 is num, expr2 is paren
        else if(expr1 instanceof ParseTree.ExprNumLit
            && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("num")
        ) return true;

        // if expr1 is ident, expr2 is paren
        else if(expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("num")
            && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("num")
        ) return true;

        // expr1 = paren, expr2 = undetermined
        else if(expr1 instanceof ParseTree.ExprParen && expr1.info.type.equals("num") && determineType(expr2).equals("num"))
            return true;

        // expr1 = undetermined, expr2 = paren
        else if(determineType(expr1).equals("num") && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("num"))
            return true;

        // if both are parens
        else if(expr1 instanceof ParseTree.ExprParen && expr1.info.type.equals("num")
            && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("num")
        ) return true;

        // expr1 = undetermined, expr2 = num
        else if(determineType(expr1).equals("num") && expr2 instanceof ParseTree.ExprNumLit)
            return true;

        // expr1 = num, expr2 = undetermined
        else if(expr1 instanceof ParseTree.ExprNumLit && determineType(expr2).equals("num"))
            return true;

        // expr1 = undetermined, expr2 = ident
        else if(determineType(expr1).equals("num")
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("num"))
        ) return true;

        // expr1 = ident, expr2 = undetermined
        else if(expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("num")
            && determineType(expr2).equals("num")
        ) return true;

        // expr1 = undetermined, expr2 = undetermined
        else if(determineType(expr1).equals("num") && determineType(expr2).equals("num"))
            return true;

        // if either are one of the num classes
        else if(determineType(expr1).equals("num") && determineType(expr2).equals("num"))
            return true;

        // throw error
        else return false;
    }

    private boolean areBothBool(ParseTree.Expr expr1, ParseTree.Expr expr2) throws Exception {

        // expr1 = bool, expr2 = bool
        if(expr1 instanceof ParseTree.ExprBoolLit && expr2 instanceof ParseTree.ExprBoolLit)
            return true;

        // expr1 = bool, expr2 = ident
        else if(expr1 instanceof ParseTree.ExprBoolLit
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("bool"))
        ) return true;

        // expr1 = ident, expr2 = bool
        else if(expr2 instanceof ParseTree.ExprBoolLit
            && (expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("bool"))
        ) return true;

        // expr1 = ident, expr2 = ident
        else if(expr1 instanceof ParseTree.ExprIdent && expr2 instanceof ParseTree.ExprIdent)
            return env.Get(((ParseTree.ExprIdent)expr1).ident).equals(env.Get(((ParseTree.ExprIdent)expr2).ident));

        // if expr1 is paren, expr2 is bool
        else if(expr1 instanceof ParseTree.ExprParen
            && expr1.info.type.equals("bool")
            && expr2 instanceof ParseTree.ExprBoolLit
        ) return true;

        // if expr1 is paren, expr2 is ident
        else if(expr1 instanceof ParseTree.ExprParen && expr1.info.type.equals("bool")
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("bool"))
        ) return true;

        // if expr1 is bool, expr2 is paren
        else if(expr1 instanceof ParseTree.ExprBoolLit
            && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("bool")
        ) return true;

        // if expr1 is ident, expr2 is paren
        else if(expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("bool")
            && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("bool")
        ) return true;

        // expr1 = paren, expr2 = undetermined
        else if(expr1 instanceof ParseTree.ExprParen && expr1.info.type.equals("bool") && determineType(expr2).equals("bool"))
            return true;

        // expr1 = undetermined, expr2 = paren
        else if(determineType(expr1).equals("bool") && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("bool"))
            return true;

        // if both are parens
        else if(expr1 instanceof ParseTree.ExprParen && expr1.info.type.equals("bool")
            && expr2 instanceof ParseTree.ExprParen && expr2.info.type.equals("bool")
        ) return true;

        // expr1 = undetermined, expr2 = bool
        else if(determineType(expr1).equals("bool") && expr2 instanceof ParseTree.ExprBoolLit)
            return true;

        // expr1 = bool, expr2 = undetermined
        else if(expr1 instanceof ParseTree.ExprBoolLit && determineType(expr2).equals("bool"))
            return true;

        // expr1 = undetermined, expr2 = ident
        else if(determineType(expr1).equals("bool")
            && (expr2 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr2).ident).equals("bool"))
        ) return true;

        // expr1 = ident, expr2 = undetermined
        else if(expr1 instanceof ParseTree.ExprIdent && env.Get(((ParseTree.ExprIdent) expr1).ident).equals("bool")
            && determineType(expr2).equals("bool")
        ) return true;

        // expr1 = undetermined, expr2 = undetermined
        else if(determineType(expr1).equals("bool") && determineType(expr2).equals("bool"))
            return true;

        // if either are one of the bool classes
        else if(determineType(expr1).equals("bool") && determineType(expr2).equals("bool"))
            return true;

        // throw error
        else return false;
    }

    private String determineType(ParseTree.Expr expr) throws Exception {
        List<Class<?>> numClasses = Arrays.asList(
            ParseTree.ExprNumLit.class, ParseTree.ExprAdd.class, ParseTree.ExprSub.class,
            ParseTree.ExprMul.class, ParseTree.ExprDiv.class, ParseTree.ExprMod.class
        );
        boolean num = numClasses.stream().anyMatch(clazz -> clazz.isInstance(expr));
        if(num) return "num";

        List<Class<?>> boolClasses = Arrays.asList(
            ParseTree.ExprBoolLit.class, ParseTree.ExprAnd.class, ParseTree.ExprOr.class,
            ParseTree.ExprNot.class, ParseTree.ExprEq.class, ParseTree.ExprNe.class,
            ParseTree.ExprLe.class, ParseTree.ExprLt.class, ParseTree.ExprGe.class,
            ParseTree.ExprGt.class
        );
        boolean bool = boolClasses.stream().anyMatch(clazz -> clazz.isInstance(expr));
        if(bool) return "bool";

        // expr = array, type = num
        if(expr instanceof ParseTree.ExprNewArray && ((ParseTree.ExprNewArray) expr).elemtype.typename.equals("num"))
            return "num[]";

        // expr = array, type = bool
        if(expr instanceof ParseTree.ExprNewArray && ((ParseTree.ExprNewArray) expr).elemtype.typename.equals("bool"))
            return "bool[]";

        if(expr instanceof ParseTree.ExprArrayElem) {
            String id = ((ParseTree.ExprArrayElem) expr).ident;
            String type = (String) env.Get(id);
            return type.substring(0, type.length() - 2);
        }

        // expr = ident
        if(expr instanceof ParseTree.ExprIdent) {
            String id = ((ParseTree.ExprIdent) expr).ident;
            return (String) env.Get(id);
        }

        return "null";
    }

}
