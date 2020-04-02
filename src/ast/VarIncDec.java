// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class VarIncDec implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private SimpleDesignator SimpleDesignator;
    private IncDec IncDec;

    public VarIncDec (SimpleDesignator SimpleDesignator, IncDec IncDec) {
        this.SimpleDesignator=SimpleDesignator;
        if(SimpleDesignator!=null) SimpleDesignator.setParent(this);
        this.IncDec=IncDec;
        if(IncDec!=null) IncDec.setParent(this);
    }

    public SimpleDesignator getSimpleDesignator() {
        return SimpleDesignator;
    }

    public void setSimpleDesignator(SimpleDesignator SimpleDesignator) {
        this.SimpleDesignator=SimpleDesignator;
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
        if(SimpleDesignator!=null) SimpleDesignator.accept(visitor);
        if(IncDec!=null) IncDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SimpleDesignator!=null) SimpleDesignator.traverseTopDown(visitor);
        if(IncDec!=null) IncDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SimpleDesignator!=null) SimpleDesignator.traverseBottomUp(visitor);
        if(IncDec!=null) IncDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarIncDec(\n");

        if(SimpleDesignator!=null)
            buffer.append(SimpleDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IncDec!=null)
            buffer.append(IncDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarIncDec]");
        return buffer.toString();
    }
}
