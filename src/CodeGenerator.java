package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;

	private ArrayList<Obj> postInc = new ArrayList<>();
	private ArrayList<Obj> postDec = new ArrayList<>();

	
	
	private ArrayList<ArrRefIncDec> postIncArr = new ArrayList<>();
	private ArrayList<ArrRefIncDec> postDecArr = new ArrayList<>();

	
	private void emptyPostInc() {
		for (Obj o : postInc) {
			
			
			
			Code.load(o);

			Obj con = new Obj(Obj.Con, "%", Tab.intType);
			con.setAdr(1);

			Code.load(con);

			Code.put(Code.add);

			Code.store(o);

		}
		postInc.clear();

		for (Obj o : postDec) {

			Code.load(o);

			Obj con = new Obj(Obj.Con, "%", Tab.intType);
			con.setAdr(-1);

			Code.load(con);

			Code.put(Code.add);

			Code.store(o);
		}
		
		for(ArrRefIncDec a : postIncArr) {
			a.getArrayDesignator().traverseTopDown(new CodeGenerator());
			
			Code.put(Code.dup2);
			
			Code.load(a.getArrayDesignator().obj);
			Obj con = new Obj(Obj.Con, "%", Tab.intType);
			con.setAdr(1);

			Code.load(con);

			Code.put(Code.add);

			Code.store(a.getArrayDesignator().obj);			
		}
		
		for(ArrRefIncDec a : postDecArr) {
			a.getArrayDesignator().traverseTopDown(new CodeGenerator());
			
			Code.put(Code.dup2);
			
			Code.load(a.getArrayDesignator().obj);
			Obj con = new Obj(Obj.Con, "%", Tab.intType);
			con.setAdr(-1);

			Code.load(con);

			Code.put(Code.add);

			Code.store(a.getArrayDesignator().obj);			

		}
		
		
	}

	// ----------------------------

	public int getMainPc() {
		return mainPc;
	}

	public void visit(PrintStmt printStmt) {
		if (printStmt.getExpr().struct == Tab.intType || printStmt.getExpr().struct == SemanticPass.booleanType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}

	public void visit(VarAssignment varAssignment) {
		Obj var = varAssignment.getSimpleDesignator().obj;

		Code.store(var);

		emptyPostInc();

	}

	public void visit(ArrAsignment arrAssignment) {
		Obj var = arrAssignment.getArrayDesignator().obj;

		Code.store(var);
		
		emptyPostInc();

	}

	public void visit(FuncCall funcCall) {
		Obj functionObj = funcCall.getStartFuncCall().getSimpleDesignator().obj;
		
		if(functionObj.getName().equals("ord"))
			return;

		emptyPostInc();
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);

	}

	public void visit(ProcCall procCall) {
		if (procCall.getFuncCall().getStartFuncCall().getSimpleDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}

	}

	
	public void visit(CharRef charRef) {
		Obj con = Tab.insert(Obj.Con, "#", Tab.charType);
		con.setLevel(0);
		con.setAdr(charRef.getC1());
		
		Code.load(con);
	}
	
	public void visit(IntRef intRef) {
		Obj con = Tab.insert(Obj.Con, "$", intRef.struct);
		con.setLevel(0);
		con.setAdr(intRef.getN1());

		Code.load(con);
	}
	
	public void visit(BoolRef boolRef) {
		Obj con = Tab.insert(Obj.Con, "%", boolRef.struct);
		con.setLevel(0);
		
		if(boolRef.getBoolConst() instanceof True)
			con.setAdr(1);
		else
			con.setAdr(0);
			
		Code.load(con);
	}
	

	public void visit(EnumRef enumRef) {
		Obj enumObj = enumRef.getSimpleDesignator().obj;
		Obj con = null;
		for (Obj o : enumObj.getType().getMembers()) {
			if (enumRef.getConstName().equals(o.getName())) {
				con = o;
				break;
			}
		}		
		
		Code.load(con);
	}

	public void visit(VarRef var) {
		Obj v = var.getSimpleDesignator().obj;

		Code.load(v);

	}

	public void visit(VarIncDec var) {
		Obj v = var.getSimpleDesignator().obj;

		Code.load(v);

		Obj con = new Obj(Obj.Con, "%", Tab.intType);

		if (var.getIncDec() instanceof Inc) {
			con.setAdr(1);
		} else {
			con.setAdr(-1);
		}
		Code.load(con);

		Code.put(Code.add);

		Code.store(v);
	}

	public void visit(ArrIncDec arr) {

		Code.put(Code.dup2);

		Obj elem = arr.getArrayDesignator().obj;

		Code.load(elem);

		Obj con = new Obj(Obj.Con, "%", Tab.intType);

		if (arr.getIncDec() instanceof Inc)
			con.setAdr(1);
		else
			con.setAdr(-1);

		Code.load(con);

		Code.put(Code.add);

		Code.store(elem);
	}

	public void visit(VarRefIncDec var) {
		Obj v = var.getSimpleDesignator().obj;

		Code.load(v);

		if (var.getIncDec() instanceof Inc)
			postInc.add(v);
		else
			postDec.add(v);

	}

	public void visit(ArrRefIncDec arr) {
		Obj a = arr.getArrayDesignator().obj;

		Code.load(a);

		if (arr.getIncDec() instanceof Inc)
			postIncArr.add(arr);
		else
			postDecArr.add(arr);
	}

	public void visit(ArrRef arr) {
		Obj a = arr.getArrayDesignator().obj;

		Code.load(a);
	}

	public void visit(ArrName arrName) {
		Obj a = arrName.obj;

		Code.load(a);
	}

	public void visit(ReturnType ret) {
		ret.obj.setAdr(Code.pc);

		SyntaxNode methodNode = ret.getParent();

		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());

	}

	public void visit(VoidRetType ret) {
		if ("main".equals(ret.getMethName())) {
			mainPc = Code.pc;
		}
		ret.obj.setAdr(Code.pc);

		SyntaxNode methodNode = ret.getParent();

		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());

	}

	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnExpr ret) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnNoExpr ret) {
		Code.put(Code.exit);
		Code.put(Code.return_);		
	}
	
	
	public void visit(AddExpr addExpr) {
		if (addExpr.getAddop() instanceof Add) {
			Code.put(Code.add);
		} else {
			Code.put(Code.sub);
		}
	}

	public void visit(MulopFactor mulFactor) {
		if (mulFactor.getMulop() instanceof Mul) {
			Code.put(Code.mul);
		} else if (mulFactor.getMulop() instanceof Div) {
			Code.put(Code.div);
		}else {
			Code.put(Code.rem);
		}
	}

	/*
	 * public void visit(ArrayIdInit arr) {
	 * 
	 * Code.put(Code.newarray);
	 * 
	 * Struct type = Tab.find(arr.getType()).getType(); if(type == Tab.intType) {
	 * Code.put(1); }else { Code.put(2); }
	 * 
	 * }
	 */

	public void visit(OperNew operNew) {

		Code.put(Code.newarray);

		if (operNew.struct.getElemType() == Tab.intType) {
			Code.put(1);
		} else {
			Code.put(0);
		}

	}
	
	
	
	
	
	public void visit(VarReadDesignator vrd) {
		
		if(vrd.getSimpleDesignator().obj.getType() == Tab.intType) {
			Code.put(Code.read);
		}else {
			Code.put(Code.bread);
		}
		
		Code.store(vrd.obj);		
	}

	
	
	public void visit(ArrReadDesignator ard) {
		
		//Adresa niza postavljena na eStack pri obilasku ArrName; kao i indeks - implicitno
		
		if(ard.getArrayDesignator().obj.getType() == Tab.intType) {
			Code.put(Code.read);
		}else {
			Code.put(Code.bread);
		}
		
		Code.store(ard.obj);
		
		
	}
	
	public void visit(MinusExpr minusExpr) {
		Code.put(Code.neg);
	}
	
	
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	//---------------------------DRUGI DEO -----------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	
	
	
	Stack<ArrayList<Integer>> patchAdr =  new Stack<>();
	Stack<Integer> jmpAdr =  new Stack<>();
	
	
	
	ArrayList<Integer> patchArray = new ArrayList<>();
	
	public void visit(ConditionFactorWithRelop cf) {
		Relop relop = cf.getRelop();
		int code = 0;
		if(relop instanceof Eq) {
			code = Code.eq;
		}else if(relop instanceof Neq) {
			code = Code.ne;
		}else if(relop instanceof Gt) {
			code = Code.gt;
		}else if(relop instanceof Ge) {
			code = Code.ge;
		}else if(relop instanceof Lt) {
			code = Code.lt;
		}else {
			code = Code.le;
		}
		Code.putFalseJump(code, 0);
		patchArray.add(Code.pc - 2);
	}
	
	
	public void visit(SimpleConditionFactor simpleCondFact) {
		Obj con = new Obj(Obj.Con, "$", Tab.intType);
		con.setLevel(0);
		con.setAdr(1);
		Code.load(con);
		Code.putFalseJump(Code.ge, 0);
		patchArray.add(Code.pc -2);
	}
	
	
	ArrayList<Integer> jmpBegin = new ArrayList<>();
	
	
	public void visit(Or or) {
		Code.putJump(0);
		jmpBegin.add(Code.pc - 2);
		for(Integer adr : patchArray) {
			Code.fixup(adr);
		}
		patchArray.clear();
	}
	
	public void visit(Condition cond) {
		
		SyntaxNode parent = cond.getParent();
		if(!(parent instanceof ForCond)) {
			for(Integer jmpAdr : jmpBegin) {
				Code.fixup(jmpAdr);
			}
			jmpBegin.clear();
		}
		
		ArrayList<Integer> temp = new ArrayList<>();
		temp.addAll(patchArray);
		patchAdr.push(temp);
		patchArray.clear();
		
		
	}
	
	
	public void visit(Else e) {
		Code.putJump(0);
		jmpAdr.push(Code.pc - 2);
		ArrayList<Integer> adr = patchAdr.pop();
		for(Integer a : adr) {
			Code.fixup(a);
		}
		
	}
	
	
	public void visit(AfterElseExpr afterElse) {
		Code.fixup(jmpAdr.pop());
	}
	
	
	public void visit(UnmatchedIf unmatchedIf) {
		ArrayList<Integer> adr = patchAdr.pop();
		for(Integer a : adr) {
			Code.fixup(a);
		}
	}
	
	//-------------------------------------------------
	
	private int condAdr;	
	private Stack<Integer> forArg3 = new Stack<>();
	
	//koristim ga zbog break naredbi, pomaze mi pri selekciji odgovarajuceg patch niza
	
	
	private Stack<ArrayList<Integer>> breakPatching = new Stack<>();
	
	
	public void visit(LeftSemi leftSemi) {
		condAdr = Code.pc;
		
		emptyPostInc();
		
	}
	
	
	public void visit(RightSemi rightSemi) {
		Code.putJump(0);
		jmpBegin.add(Code.pc - 2);
		forArg3.push(Code.pc);				
	}
	
	
	public void visit(StartFor startFor) {
		emptyPostInc();
		Code.putJump(condAdr);
		
		for(Integer jmpAdr : jmpBegin) {
			Code.fixup(jmpAdr);
		}
		jmpBegin.clear();
	
		breakPatching.push(new ArrayList<Integer>());
	}
	
	
	public void visit(EndFor endFor) {
		
		Code.putJump(forArg3.pop());
		
		ArrayList<Integer> adr = patchAdr.pop();
		for(Integer a : adr) {
			Code.fixup(a);
		}
		
		
		adr = breakPatching.pop();
		for(Integer a : adr) {
			Code.fixup(a);
		}
		
	}
	
	
	public void visit(Break br) {
		Code.putJump(0);
	
		breakPatching.peek().add(Code.pc - 2);
	}
	
	
	public void visit(Continue cont) {
		Code.putJump(forArg3.peek());
	}
	
	
	
	
}
