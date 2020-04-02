// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class FVarIncDec extends FStatement {

    private VarIncDec VarIncDec;

    public FVarIncDec (VarIncDec VarIncDec) {
        this.VarIncDec=VarIncDec;
        if(VarIncDec!=null) VarIncDec.setParent(this);
    }

    public VarIncDec getVarIncDec() {
        return VarIncDec;
    }

    public void setVarIncDec(VarIncDec VarIncDec) {
        this.VarIncDec=VarIncDec;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarIncDec!=null) VarIncDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarIncDec!=null) VarIncDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarIncDec!=null) VarIncDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FVarIncDec(\n");

        if(VarIncDec!=null)
            buffer.append(VarIncDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FVarIncDec]");
        return buffer.toString();
    }
}
