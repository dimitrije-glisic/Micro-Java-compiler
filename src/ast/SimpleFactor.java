// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:2


package rs.ac.bg.etf.pp1.ast;

public class SimpleFactor extends FactorList {

    private Factor Factor;
    private EndFactor EndFactor;

    public SimpleFactor (Factor Factor, EndFactor EndFactor) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.EndFactor=EndFactor;
        if(EndFactor!=null) EndFactor.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public EndFactor getEndFactor() {
        return EndFactor;
    }

    public void setEndFactor(EndFactor EndFactor) {
        this.EndFactor=EndFactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(EndFactor!=null) EndFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(EndFactor!=null) EndFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(EndFactor!=null) EndFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleFactor(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndFactor!=null)
            buffer.append(EndFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleFactor]");
        return buffer.toString();
    }
}
