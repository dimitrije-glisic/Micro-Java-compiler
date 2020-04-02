// generated with ast extension for cup
// version 0.8
// 13/0/2020 9:43:59


package rs.ac.bg.etf.pp1.ast;

public class For extends ForStatement {

    private FStatement FStatement;
    private LeftSemi LeftSemi;
    private ForCondition ForCondition;
    private RightSemi RightSemi;
    private FStatement FStatement1;
    private StartFor StartFor;
    private Statement Statement;
    private EndFor EndFor;

    public For (FStatement FStatement, LeftSemi LeftSemi, ForCondition ForCondition, RightSemi RightSemi, FStatement FStatement1, StartFor StartFor, Statement Statement, EndFor EndFor) {
        this.FStatement=FStatement;
        if(FStatement!=null) FStatement.setParent(this);
        this.LeftSemi=LeftSemi;
        if(LeftSemi!=null) LeftSemi.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.RightSemi=RightSemi;
        if(RightSemi!=null) RightSemi.setParent(this);
        this.FStatement1=FStatement1;
        if(FStatement1!=null) FStatement1.setParent(this);
        this.StartFor=StartFor;
        if(StartFor!=null) StartFor.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.EndFor=EndFor;
        if(EndFor!=null) EndFor.setParent(this);
    }

    public FStatement getFStatement() {
        return FStatement;
    }

    public void setFStatement(FStatement FStatement) {
        this.FStatement=FStatement;
    }

    public LeftSemi getLeftSemi() {
        return LeftSemi;
    }

    public void setLeftSemi(LeftSemi LeftSemi) {
        this.LeftSemi=LeftSemi;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public RightSemi getRightSemi() {
        return RightSemi;
    }

    public void setRightSemi(RightSemi RightSemi) {
        this.RightSemi=RightSemi;
    }

    public FStatement getFStatement1() {
        return FStatement1;
    }

    public void setFStatement1(FStatement FStatement1) {
        this.FStatement1=FStatement1;
    }

    public StartFor getStartFor() {
        return StartFor;
    }

    public void setStartFor(StartFor StartFor) {
        this.StartFor=StartFor;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public EndFor getEndFor() {
        return EndFor;
    }

    public void setEndFor(EndFor EndFor) {
        this.EndFor=EndFor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FStatement!=null) FStatement.accept(visitor);
        if(LeftSemi!=null) LeftSemi.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(RightSemi!=null) RightSemi.accept(visitor);
        if(FStatement1!=null) FStatement1.accept(visitor);
        if(StartFor!=null) StartFor.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(EndFor!=null) EndFor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FStatement!=null) FStatement.traverseTopDown(visitor);
        if(LeftSemi!=null) LeftSemi.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(RightSemi!=null) RightSemi.traverseTopDown(visitor);
        if(FStatement1!=null) FStatement1.traverseTopDown(visitor);
        if(StartFor!=null) StartFor.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(EndFor!=null) EndFor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FStatement!=null) FStatement.traverseBottomUp(visitor);
        if(LeftSemi!=null) LeftSemi.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(RightSemi!=null) RightSemi.traverseBottomUp(visitor);
        if(FStatement1!=null) FStatement1.traverseBottomUp(visitor);
        if(StartFor!=null) StartFor.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(EndFor!=null) EndFor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("For(\n");

        if(FStatement!=null)
            buffer.append(FStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LeftSemi!=null)
            buffer.append(LeftSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RightSemi!=null)
            buffer.append(RightSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FStatement1!=null)
            buffer.append(FStatement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StartFor!=null)
            buffer.append(StartFor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndFor!=null)
            buffer.append(EndFor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [For]");
        return buffer.toString();
    }
}
