// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class FVarAssignment extends FStatement {

    private VarAssignment VarAssignment;

    public FVarAssignment (VarAssignment VarAssignment) {
        this.VarAssignment=VarAssignment;
        if(VarAssignment!=null) VarAssignment.setParent(this);
    }

    public VarAssignment getVarAssignment() {
        return VarAssignment;
    }

    public void setVarAssignment(VarAssignment VarAssignment) {
        this.VarAssignment=VarAssignment;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarAssignment!=null) VarAssignment.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarAssignment!=null) VarAssignment.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarAssignment!=null) VarAssignment.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FVarAssignment(\n");

        if(VarAssignment!=null)
            buffer.append(VarAssignment.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FVarAssignment]");
        return buffer.toString();
    }
}
