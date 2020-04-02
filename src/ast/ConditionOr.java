// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:1


package rs.ac.bg.etf.pp1.ast;

public class ConditionOr extends ConditionList {

    private ConditionList ConditionList;
    private Or Or;
    private ConditionTerm ConditionTerm;

    public ConditionOr (ConditionList ConditionList, Or Or, ConditionTerm ConditionTerm) {
        this.ConditionList=ConditionList;
        if(ConditionList!=null) ConditionList.setParent(this);
        this.Or=Or;
        if(Or!=null) Or.setParent(this);
        this.ConditionTerm=ConditionTerm;
        if(ConditionTerm!=null) ConditionTerm.setParent(this);
    }

    public ConditionList getConditionList() {
        return ConditionList;
    }

    public void setConditionList(ConditionList ConditionList) {
        this.ConditionList=ConditionList;
    }

    public Or getOr() {
        return Or;
    }

    public void setOr(Or Or) {
        this.Or=Or;
    }

    public ConditionTerm getConditionTerm() {
        return ConditionTerm;
    }

    public void setConditionTerm(ConditionTerm ConditionTerm) {
        this.ConditionTerm=ConditionTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionList!=null) ConditionList.accept(visitor);
        if(Or!=null) Or.accept(visitor);
        if(ConditionTerm!=null) ConditionTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionList!=null) ConditionList.traverseTopDown(visitor);
        if(Or!=null) Or.traverseTopDown(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionList!=null) ConditionList.traverseBottomUp(visitor);
        if(Or!=null) Or.traverseBottomUp(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionOr(\n");

        if(ConditionList!=null)
            buffer.append(ConditionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Or!=null)
            buffer.append(Or.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionTerm!=null)
            buffer.append(ConditionTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionOr]");
        return buffer.toString();
    }
}
