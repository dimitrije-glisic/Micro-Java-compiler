// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:57


package rs.ac.bg.etf.pp1.ast;

public class Declarations extends DeclarationList {

    private DeclarationList DeclarationList;
    private DeclarationPart DeclarationPart;

    public Declarations (DeclarationList DeclarationList, DeclarationPart DeclarationPart) {
        this.DeclarationList=DeclarationList;
        if(DeclarationList!=null) DeclarationList.setParent(this);
        this.DeclarationPart=DeclarationPart;
        if(DeclarationPart!=null) DeclarationPart.setParent(this);
    }

    public DeclarationList getDeclarationList() {
        return DeclarationList;
    }

    public void setDeclarationList(DeclarationList DeclarationList) {
        this.DeclarationList=DeclarationList;
    }

    public DeclarationPart getDeclarationPart() {
        return DeclarationPart;
    }

    public void setDeclarationPart(DeclarationPart DeclarationPart) {
        this.DeclarationPart=DeclarationPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclarationList!=null) DeclarationList.accept(visitor);
        if(DeclarationPart!=null) DeclarationPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclarationList!=null) DeclarationList.traverseTopDown(visitor);
        if(DeclarationPart!=null) DeclarationPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclarationList!=null) DeclarationList.traverseBottomUp(visitor);
        if(DeclarationPart!=null) DeclarationPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Declarations(\n");

        if(DeclarationList!=null)
            buffer.append(DeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DeclarationPart!=null)
            buffer.append(DeclarationPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Declarations]");
        return buffer.toString();
    }
}
