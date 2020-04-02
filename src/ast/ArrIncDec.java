// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class ArrIncDec implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ArrayDesignator ArrayDesignator;
    private IncDec IncDec;

    public ArrIncDec (ArrayDesignator ArrayDesignator, IncDec IncDec) {
        this.ArrayDesignator=ArrayDesignator;
        if(ArrayDesignator!=null) ArrayDesignator.setParent(this);
        this.IncDec=IncDec;
        if(IncDec!=null) IncDec.setParent(this);
    }

    public ArrayDesignator getArrayDesignator() {
        return ArrayDesignator;
    }

    public void setArrayDesignator(ArrayDesignator ArrayDesignator) {
        this.ArrayDesignator=ArrayDesignator;
    }

    public IncDec getIncDec() {
        return IncDec;
    }

    public void setIncDec(IncDec IncDec) {
        this.IncDec=IncDec;
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
        if(ArrayDesignator!=null) ArrayDesignator.accept(visitor);
        if(IncDec!=null) IncDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayDesignator!=null) ArrayDesignator.traverseTopDown(visitor);
        if(IncDec!=null) IncDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayDesignator!=null) ArrayDesignator.traverseBottomUp(visitor);
        if(IncDec!=null) IncDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrIncDec(\n");

        if(ArrayDesignator!=null)
            buffer.append(ArrayDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IncDec!=null)
            buffer.append(IncDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrIncDec]");
        return buffer.toString();
    }
}
