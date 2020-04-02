// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:1


package rs.ac.bg.etf.pp1.ast;

public class NoSecArg extends SecondArg {

    public NoSecArg () {
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
        buffer.append("NoSecArg(\n");

        buffer.append(tab);
        buffer.append(") [NoSecArg]");
        return buffer.toString();
    }
}
