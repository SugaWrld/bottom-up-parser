public class Token
{
    public String lexeme;
    public int lineno;
    public int colno;
    public Token(String lexeme, int lineno, int colno)
    {
        this.lexeme = lexeme;
        this.lineno = lineno + 1;
        this.colno = colno + 1;
    }
}
