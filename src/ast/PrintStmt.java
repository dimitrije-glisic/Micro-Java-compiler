// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class PrintStmt extends Matched {

    private Expr Expr;
    private SecondArg SecondArg;

    public PrintStmt (Expr Expr, SecondArg SecondArg) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SecondArg=SecondArg;
        if(SecondArg!=null) SecondArg.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SecondArg getSecondArg() {
        return SecondArg;
    }

    public void setSecondArg(SecondArg SecondArg) {
        this.SecondArg=SecondArg;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(SecondArg!=null) SecondArg.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SecondArg!=null) SecondArg.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SecondArg!=null) SecondArg.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SecondArg!=null)
            buffer.append(SecondArg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStmt]");
        return buffer.toString();
    }
}
