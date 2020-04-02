// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:2


package rs.ac.bg.etf.pp1.ast;

public class EnumRef extends Factor {

    private SimpleDesignator SimpleDesignator;
    private String constName;

    public EnumRef (SimpleDesignator SimpleDesignator, String constName) {
        this.SimpleDesignator=SimpleDesignator;
        if(SimpleDesignator!=null) SimpleDesignator.setParent(this);
        this.constName=constName;
    }

    public SimpleDesignator getSimpleDesignator() {
        return SimpleDesignator;
    }

    public void setSimpleDesignator(SimpleDesignator SimpleDesignator) {
        this.SimpleDesignator=SimpleDesignator;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SimpleDesignator!=null) SimpleDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SimpleDesignator!=null) SimpleDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SimpleDesignator!=null) SimpleDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumRef(\n");

        if(SimpleDesignator!=null)
            buffer.append(SimpleDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumRef]");
        return buffer.toString();
    }
}
