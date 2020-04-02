// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:58


package rs.ac.bg.etf.pp1.ast;

public class ArrayId extends VarPart {

    private String arrName;

    public ArrayId (String arrName) {
        this.arrName=arrName;
    }

    public String getArrName() {
        return arrName;
    }

    public void setArrName(String arrName) {
        this.arrName=arrName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayId(\n");

        buffer.append(" "+tab+arrName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayId]");
        return buffer.toString();
    }
}
