// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:1


package rs.ac.bg.etf.pp1.ast;

public class ConditionTerm implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConditionFactorList ConditionFactorList;

    public ConditionTerm (ConditionFactorList ConditionFactorList) {
        this.ConditionFactorList=ConditionFactorList;
        if(ConditionFactorList!=null) ConditionFactorList.setParent(this);
    }

    public ConditionFactorList getConditionFactorList() {
        return ConditionFactorList;
    }

    public void setConditionFactorList(ConditionFactorList ConditionFactorList) {
        this.ConditionFactorList=ConditionFactorList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionFactorList!=null) ConditionFactorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionFactorList!=null) ConditionFactorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionFactorList!=null) ConditionFactorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionTerm(\n");

        if(ConditionFactorList!=null)
            buffer.append(ConditionFactorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionTerm]");
        return buffer.toString();
    }
}
