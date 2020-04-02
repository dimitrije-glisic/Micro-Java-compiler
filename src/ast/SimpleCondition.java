// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:1


package rs.ac.bg.etf.pp1.ast;

public class SimpleCondition extends ConditionFactorList {

    private ConditionFactor ConditionFactor;

    public SimpleCondition (ConditionFactor ConditionFactor) {
        this.ConditionFactor=ConditionFactor;
        if(ConditionFactor!=null) ConditionFactor.setParent(this);
    }

    public ConditionFactor getConditionFactor() {
        return ConditionFactor;
    }

    public void setConditionFactor(ConditionFactor ConditionFactor) {
        this.ConditionFactor=ConditionFactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionFactor!=null) ConditionFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionFactor!=null) ConditionFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionFactor!=null) ConditionFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleCondition(\n");

        if(ConditionFactor!=null)
            buffer.append(ConditionFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleCondition]");
        return buffer.toString();
    }
}
