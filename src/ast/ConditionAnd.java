// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:1


package rs.ac.bg.etf.pp1.ast;

public class ConditionAnd extends ConditionFactorList {

    private ConditionFactorList ConditionFactorList;
    private ConditionFactor ConditionFactor;

    public ConditionAnd (ConditionFactorList ConditionFactorList, ConditionFactor ConditionFactor) {
        this.ConditionFactorList=ConditionFactorList;
        if(ConditionFactorList!=null) ConditionFactorList.setParent(this);
        this.ConditionFactor=ConditionFactor;
        if(ConditionFactor!=null) ConditionFactor.setParent(this);
    }

    public ConditionFactorList getConditionFactorList() {
        return ConditionFactorList;
    }

    public void setConditionFactorList(ConditionFactorList ConditionFactorList) {
        this.ConditionFactorList=ConditionFactorList;
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
        if(ConditionFactorList!=null) ConditionFactorList.accept(visitor);
        if(ConditionFactor!=null) ConditionFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionFactorList!=null) ConditionFactorList.traverseTopDown(visitor);
        if(ConditionFactor!=null) ConditionFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionFactorList!=null) ConditionFactorList.traverseBottomUp(visitor);
        if(ConditionFactor!=null) ConditionFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionAnd(\n");

        if(ConditionFactorList!=null)
            buffer.append(ConditionFactorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionFactor!=null)
            buffer.append(ConditionFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionAnd]");
        return buffer.toString();
    }
}
