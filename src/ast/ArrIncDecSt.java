// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class ArrIncDecSt extends Matched {

    private ArrIncDec ArrIncDec;

    public ArrIncDecSt (ArrIncDec ArrIncDec) {
        this.ArrIncDec=ArrIncDec;
        if(ArrIncDec!=null) ArrIncDec.setParent(this);
    }

    public ArrIncDec getArrIncDec() {
        return ArrIncDec;
    }

    public void setArrIncDec(ArrIncDec ArrIncDec) {
        this.ArrIncDec=ArrIncDec;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrIncDec!=null) ArrIncDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrIncDec!=null) ArrIncDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrIncDec!=null) ArrIncDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrIncDecSt(\n");

        if(ArrIncDec!=null)
            buffer.append(ArrIncDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrIncDecSt]");
        return buffer.toString();
    }
}
