// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:58


package rs.ac.bg.etf.pp1.ast;

public class FormalParamErr extends FormalParamDecl {

    public FormalParamErr () {
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
        buffer.append("FormalParamErr(\n");

        buffer.append(tab);
        buffer.append(") [FormalParamErr]");
        return buffer.toString();
    }
}
