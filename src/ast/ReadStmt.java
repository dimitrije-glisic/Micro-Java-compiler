// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:0


package rs.ac.bg.etf.pp1.ast;

public class ReadStmt extends Matched {

    private ReadDesignator ReadDesignator;

    public ReadStmt (ReadDesignator ReadDesignator) {
        this.ReadDesignator=ReadDesignator;
        if(ReadDesignator!=null) ReadDesignator.setParent(this);
    }

    public ReadDesignator getReadDesignator() {
        return ReadDesignator;
    }

    public void setReadDesignator(ReadDesignator ReadDesignator) {
        this.ReadDesignator=ReadDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReadDesignator!=null) ReadDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReadDesignator!=null) ReadDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReadDesignator!=null) ReadDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReadStmt(\n");

        if(ReadDesignator!=null)
            buffer.append(ReadDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReadStmt]");
        return buffer.toString();
    }
}
