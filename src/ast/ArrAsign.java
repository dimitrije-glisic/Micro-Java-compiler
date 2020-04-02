// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class ArrAsign extends Matched {

    private ArrAsignment ArrAsignment;

    public ArrAsign (ArrAsignment ArrAsignment) {
        this.ArrAsignment=ArrAsignment;
        if(ArrAsignment!=null) ArrAsignment.setParent(this);
    }

    public ArrAsignment getArrAsignment() {
        return ArrAsignment;
    }

    public void setArrAsignment(ArrAsignment ArrAsignment) {
        this.ArrAsignment=ArrAsignment;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrAsignment!=null) ArrAsignment.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrAsignment!=null) ArrAsignment.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrAsignment!=null) ArrAsignment.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrAsign(\n");

        if(ArrAsignment!=null)
            buffer.append(ArrAsignment.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrAsign]");
        return buffer.toString();
    }
}
