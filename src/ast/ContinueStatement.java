// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:0


package rs.ac.bg.etf.pp1.ast;

public class ContinueStatement extends Matched {

    private Continue Continue;

    public ContinueStatement (Continue Continue) {
        this.Continue=Continue;
        if(Continue!=null) Continue.setParent(this);
    }

    public Continue getContinue() {
        return Continue;
    }

    public void setContinue(Continue Continue) {
        this.Continue=Continue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Continue!=null) Continue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Continue!=null) Continue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Continue!=null) Continue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ContinueStatement(\n");

        if(Continue!=null)
            buffer.append(Continue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ContinueStatement]");
        return buffer.toString();
    }
}
