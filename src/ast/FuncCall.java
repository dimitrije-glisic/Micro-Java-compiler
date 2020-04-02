// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:44:2


package rs.ac.bg.etf.pp1.ast;

public class FuncCall implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private StartFuncCall StartFuncCall;
    private ActualPars ActualPars;
    private EndFuncCall EndFuncCall;

    public FuncCall (StartFuncCall StartFuncCall, ActualPars ActualPars, EndFuncCall EndFuncCall) {
        this.StartFuncCall=StartFuncCall;
        if(StartFuncCall!=null) StartFuncCall.setParent(this);
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
        this.EndFuncCall=EndFuncCall;
        if(EndFuncCall!=null) EndFuncCall.setParent(this);
    }

    public StartFuncCall getStartFuncCall() {
        return StartFuncCall;
    }

    public void setStartFuncCall(StartFuncCall StartFuncCall) {
        this.StartFuncCall=StartFuncCall;
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
    }

    public EndFuncCall getEndFuncCall() {
        return EndFuncCall;
    }

    public void setEndFuncCall(EndFuncCall EndFuncCall) {
        this.EndFuncCall=EndFuncCall;
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
        if(StartFuncCall!=null) StartFuncCall.accept(visitor);
        if(ActualPars!=null) ActualPars.accept(visitor);
        if(EndFuncCall!=null) EndFuncCall.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StartFuncCall!=null) StartFuncCall.traverseTopDown(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
        if(EndFuncCall!=null) EndFuncCall.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StartFuncCall!=null) StartFuncCall.traverseBottomUp(visitor);
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        if(EndFuncCall!=null) EndFuncCall.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FuncCall(\n");

        if(StartFuncCall!=null)
            buffer.append(StartFuncCall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndFuncCall!=null)
            buffer.append(EndFuncCall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FuncCall]");
        return buffer.toString();
    }
}
