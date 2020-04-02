// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:58


package rs.ac.bg.etf.pp1.ast;

public class ConstId extends ConstPart {

    private String constName;
    private Rhs rhs;

    public ConstId (String constName, Rhs rhs) {
        this.constName=constName;
        this.rhs=rhs;
        if(rhs!=null) rhs.setParent(this);
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Rhs getRhs() {
        return rhs;
    }

    public void setRhs(Rhs rhs) {
        this.rhs=rhs;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(rhs!=null) rhs.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(rhs!=null) rhs.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(rhs!=null) rhs.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstId(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(rhs!=null)
            buffer.append(rhs.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstId]");
        return buffer.toString();
    }
}
