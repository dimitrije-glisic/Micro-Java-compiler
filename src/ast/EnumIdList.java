// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:58


package rs.ac.bg.etf.pp1.ast;

public class EnumIdList extends EnumList {

    private EnumList EnumList;
    private EnumVar EnumVar;

    public EnumIdList (EnumList EnumList, EnumVar EnumVar) {
        this.EnumList=EnumList;
        if(EnumList!=null) EnumList.setParent(this);
        this.EnumVar=EnumVar;
        if(EnumVar!=null) EnumVar.setParent(this);
    }

    public EnumList getEnumList() {
        return EnumList;
    }

    public void setEnumList(EnumList EnumList) {
        this.EnumList=EnumList;
    }

    public EnumVar getEnumVar() {
        return EnumVar;
    }

    public void setEnumVar(EnumVar EnumVar) {
        this.EnumVar=EnumVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumList!=null) EnumList.accept(visitor);
        if(EnumVar!=null) EnumVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumList!=null) EnumList.traverseTopDown(visitor);
        if(EnumVar!=null) EnumVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumList!=null) EnumList.traverseBottomUp(visitor);
        if(EnumVar!=null) EnumVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumIdList(\n");

        if(EnumList!=null)
            buffer.append(EnumList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumVar!=null)
            buffer.append(EnumVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumIdList]");
        return buffer.toString();
    }
}
