package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.EnumAsActualParam;
import rs.ac.bg.etf.pp1.CounterVisitor.SingleEnumCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.SingleEnumFunctionCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.ac.bg.etf.pp1.ast.ArrAsignment;
import rs.ac.bg.etf.pp1.ast.FuncCall;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.ProcCall;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.ReturnExpr;
import rs.ac.bg.etf.pp1.ast.ReturnType;

import rs.ac.bg.etf.pp1.ast.VoidRetType;

public class SemanticPass extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;

	int currLevel = 0;

	int constVal = 0;

	EnumRef enumRef = null;

	private Obj enumAsRetType;

	private HashMap<String, Obj> functionsWhichReturnEnums = new HashMap<>();

	// static final Struct enumType = new Struct(6);

	public static final Struct booleanType = new Struct(Struct.Bool);
	{
		Tab.insert(Obj.Type, "bool", booleanType);
	}

	Struct currType = null;
	boolean isVoid = false;

	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ENUMS
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	List<Obj> enums = new ArrayList<>();
	int enumVal = 0;
	Set<Integer> uniqueVal = new HashSet<>();

	Map<String, Collection<Obj>> enumMap = new HashMap<>();

	public void visit(EnumDecl enumDecl) {
		// check for other symbol with the same name
		String name = enumDecl.getName();

		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Simbol " + name + "je vec deklarisan", enumDecl);
		}
		;

		Struct enumType = new Struct(6);

		for (Obj enumConst : enums) {
			enumType.getMembersTable().insertKey(enumConst);
		}

		Tab.insert(Obj.Type, name, enumType);

		enumMap.put(name, enumType.getMembers());

		uniqueVal.clear();
		enums.clear();

	}

	public void visit(SimpleEnumVar enumVar) {
		String name = enumVar.getName();
		for (Obj e : enums) {
			if (name.equals(e.getName())) {
				report_error("Svaka enum konstanta mora imati jedinstveno ime", enumVar);
			}
		}
		Obj enumConst = new Obj(Obj.Con, name, Tab.intType);
		enumConst.setAdr(enumVal);
		enumVal++;
		enums.add(enumConst);
	}

	public void visit(EnumVarWithAssignment enumVar) {
		String name = enumVar.getName();
		for (Obj e : enums) {
			if (name.equals(e.getName())) {
				report_error("Svaka enum konstanta mora imati jedinstveno ime", enumVar);
			}
		}
		Obj enumObj = new Obj(Obj.Con, name, Tab.intType);
		enumVal = enumVar.getN1();
		if (!uniqueVal.add(enumVal)) {
			report_error("Svaka enum konstanta mora imati jedinstvenu vrednost", enumVar);
		} else {
			enumObj.setAdr(enumVal++);
			enums.add(enumObj);
		}
	}

	public void visit(Type type) {
		String name = type.getTypeName();
		Obj obj = Tab.find(name);
		if (obj == Tab.noObj) {
			report_error("Ne postoji tip sa imenom " + name, type);
		} else {
			if (obj.getKind() != Obj.Type) {
				report_error("Simbol " + name + " ne predstavlja tip", type);
			} else {

				SyntaxNode parent = type.getParent();
				if (parent instanceof ReturnType) {
					if (obj.getType().getKind() == 6) {
						enumAsRetType = obj;
					}
				}

				type.struct = obj.getType();
				currType = type.struct;
			}
		}
	}

	private char constValChar;

	public void visit(IntConst intConst) {
		constVal = intConst.getVal();
	}

	public void visit(CharConst charConst) {
		constValChar = charConst.getVal().charValue();
	}

	public void visit(BoolRef bool) {
		bool.struct = booleanType;
	}

	public void visit(ConstId constId) {
		String name = constId.getConstName();
		Scope currScope = Tab.currentScope;
		if (currScope.findSymbol(name) != null) {
			report_error("Simbol sa imenom " + name + " je vec deklarisan", constId);
		} else {

			Rhs rhs = constId.getRhs();
			Obj obj = Tab.insert(Obj.Con, name, currType);

			if (rhs instanceof IntConst && currType == Tab.intType) {
				obj.setAdr(constVal);
				System.out.println("Deklarisana konstanta " + constId.getConstName());
			} else if (rhs instanceof CharConst && currType == Tab.charType) {
				obj.setAdr(constValChar);
				System.out.println("Deklarisana konstanta " + constId.getConstName());
			} else if (rhs instanceof BoolCnst && currType == booleanType) {

			} else {
				report_error("Pogresan tip pri dodeli vrednosti konstanti ", constId);
				return;
			}

		}
	}

	public void visit(VarId varId) {
		// check if varId.name has already been declared in the curr scope
		Scope currScope = Tab.currentScope;
		if (currScope.findSymbol(varId.getVarName()) != null) {
			report_error("Simbol sa imenom " + varId.getVarName() + "je vec deklarisan", varId);
		} else {
			Tab.insert(Obj.Var, varId.getVarName(), currType);
			if (currLevel == 0) {
				nVars++;
			}
			report_info("Deklarisana promenljiva " + varId.getVarName(), varId);
		}
	}

	public void visit(ArrayId arrayId) {
		String name = arrayId.getArrName();
		Scope currScope = Tab.currentScope;
		if (currScope.findSymbol(name) != null) {
			report_error("Simbol sa imenom " + name + "je vec deklarisan", arrayId);
		} else {
			Struct arrayStruct = new Struct(Struct.Array, currType);
			Tab.insert(Obj.Var, name, arrayStruct);
			if (currLevel == 0) {
				nVars++;
			}
			report_info("Deklarisan niz " + name, arrayId);
		}
	}

	public void visit(OperNew oper) {
		if (oper.getExpr().struct != Tab.intType) {
			report_error("Izraz u zagradama nije int", oper);
			return;
		}
		oper.struct = new Struct(Struct.Array, oper.getType().struct);
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ASSIGNMENT
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private boolean isValidEnumUsage(SimpleDesignator d, Expr e, VarAssignment varAssignment) {

		// funkcija sa desne strane koja vraca enum

		SingleEnumFunctionCounter enumFunctionCounter = new SingleEnumFunctionCounter();
		e.traverseTopDown(enumFunctionCounter);
		
		
		//there is only function in the expr - now check if it's enum type
		if (enumFunctionCounter.getCount() == 0) {
			
			Obj func = enumFunctionCounter.getFunc();

			Obj enumAsRet = functionsWhichReturnEnums.get(func.getName());
			if (enumAsRet == null) {
				report_error("Pogresan izraz dodele vrednosti enum promenljivoj - prazna mapa", varAssignment);
				return false;
			}
			if (d.obj.getType().equals(enumAsRet.getType())) {
				report_info("Izraz dodele vrednosti", varAssignment);
				return false;
			} else {
				report_error("Neslaganje tipova pri dodeli enum promenjljivoj", varAssignment);
				return false;
			}

		} else {
			// prosta dodela

			// da li je enumRef jedini faktor u izrazu sa desne strane?

			SingleEnumCounter enumCounter = new SingleEnumCounter();
			e.traverseTopDown(enumCounter);

			if (enumCounter.getCount() > 1) {
				report_error("Pogresan izraz dodele vrednosti enum promenljivoj", varAssignment);
				return false;
			}

			Obj eType = Tab.find(enumRef.getSimpleDesignator().getName());

			if (eType == Tab.noObj) {
				report_error("Pogresan tip nabrajanja ", varAssignment);
				return false;
			}

			if (!(d.obj.getType().equals(eType.getType()))) {
				report_error("Pogresan tip nabrajanja pri dodeli vrednosti ", varAssignment);
				return false;
			}

			boolean found = false;
			for (Obj o : d.obj.getType().getMembers()) {
				if (o.getName().equals(enumRef.getConstName())) {
					found = true;
					break;
				}
			}
			if (!found) {
				report_error("Nepostojeca konstanta nabrajanja", varAssignment);
			}

			enumRef = null;
		}
		return true;
	}

	public void visit(VarAssignment varAssignment) {
		SimpleDesignator d = varAssignment.getSimpleDesignator();
		Expr e = varAssignment.getExpr();
		
		
		
		
		if (d.obj.getKind() != Obj.Var) {
			report_error("Simbol " + d.getName() + " ne predstavlja promenljivu", varAssignment);
			return;
		}

		// enums
		if (d.obj.getType().getKind() == 6) {

			isValidEnumUsage(d, e, varAssignment);
			return;
		}

		if (!(e.struct.compatibleWith(d.obj.getType())))

		{
			report_error("Tipovi sa leve i desne strane operatora dodele nisu kompatibilni", varAssignment);
			return;
		}

		report_info("Izraz dodele vrednosti", varAssignment);
	}

	public void visit(ArrAsignment arrAssign) {
		// znamo da je arrAssign-niz, jer smo to proverili u visit(ArrayDesignator)

		ArrayDesignator d = arrAssign.getArrayDesignator();
		Expr e = arrAssign.getExpr();

		if (!(e.struct.assignableTo(d.obj.getType()))) {
			report_error("Tipovi sa leve i desne strane operatora dodele nisu kompatibilni", arrAssign);
		} else {
			report_info("Izraz dodele vrednosti", arrAssign);
		}
	}

	public void visit(SimpleDesignator simpleDesignator) {
		Obj obj = Tab.find(simpleDesignator.getName());
		if (obj == Tab.noObj) {
			report_error("Simbol " + simpleDesignator.getName() + " nije deklarisan", simpleDesignator);
		}
		simpleDesignator.obj = obj;
	}

	public void visit(ArrayDesignator arrayDesignator) {
		// is array?
		String name = arrayDesignator.getArrName().getName();
		Obj obj = Tab.find(name);
		if (obj == Tab.noObj) {
			report_error("Simbol " + name + "nije definisan", arrayDesignator);
		} else if (obj.getType().getKind() != Struct.Array) {
			report_error("Simbol " + name + " nije niz", arrayDesignator);
		}

		// expr = int ?

		Expr e = arrayDesignator.getExpr();
		if (e.struct != Tab.intType) {
			report_error("Indeks niza mora biti tipa INT", arrayDesignator);
			return;
		}

		report_info("Pristup elementu niza " + name, arrayDesignator);

		arrayDesignator.obj = new Obj(Obj.Elem, name, obj.getType().getElemType());

	}

	public void visit(ArrName arrName) {
		arrName.obj = Tab.find(arrName.getName());
	}

	public void visit(EnumRef enumRef) {

		Obj obj = enumRef.getSimpleDesignator().obj;
		if (obj == Tab.noObj) {
			report_error("Simbol nije definisan ", enumRef);
			return;
		}
		if (obj.getKind() != Obj.Type || obj.getType().getKind() != 6) {
			report_error(obj.getName() + " ne predstavlja nabrajanje", enumRef);
			return;
		}

		String name = enumRef.getConstName();

		Obj found = null;

		for (Obj o : obj.getType().getMembers()) {
			if (name.equals(o.getName())) {
				found = o;
				break;
			}
		}

		if (found == null) {
			report_error(name + "nije konstanta nabrajanja " + obj.getName(), enumRef);
		} else {
			report_info("Koriscenje nabrajanja " + obj.getName(), enumRef);
		}

		enumRef.struct = Tab.intType;

		this.enumRef = enumRef;

	}

	public void visit(IntRef intRef) {
		intRef.struct = Tab.intType;
	}

	/*
	 * public void visit(Var var){ var.struct = var.getDesignator().obj.getType(); }
	 */

	private HashMap<String, Obj> enumVarTurnedToInt = new HashMap<>();

	public void visit(VarRef varRef) {

		if (varRef.getSimpleDesignator().obj.getType().getKind() == 6) {
			varRef.struct = Tab.intType;
			enumVarTurnedToInt.put(varRef.getSimpleDesignator().getName(), varRef.getSimpleDesignator().obj);
		} else
			varRef.struct = varRef.getSimpleDesignator().obj.getType();
	}

	public void visit(ArrRef arrRef) {
		arrRef.struct = arrRef.getArrayDesignator().obj.getType();
	}

	public void visit(VarRefIncDec varRef) {
		Obj obj = varRef.getSimpleDesignator().obj;

		if (obj.getKind() == Obj.Con || (obj.getKind() == Obj.Var && obj.getType().getKind() == 6)) {
			report_error("Nedozvoljen operand ", varRef);
			return;
		}

		Struct type = varRef.getSimpleDesignator().obj.getType();
		if (type == null) {
			return;
		}
		if (type != Tab.intType) {
			report_error("Pogresan tip uz post operator ", varRef);
			return;
		}
		varRef.struct = type;
	}

	public void visit(ArrRefIncDec arrRef) {
		Struct type = arrRef.getArrayDesignator().obj.getType();
		if (type == null) {
			return;
		}
		if (type != Tab.intType) {
			report_error("Pogresan tip uz post operator ", arrRef);
			return;
		}
		arrRef.struct = type;
	}

	public void visit(ArrIncDec arr) {
		Struct type = arr.getArrayDesignator().obj.getType();
		if (type == null) {
			return;
		}
		if (type != Tab.intType) {
			report_error("Pogresan tip uz post operator ", arr);
			return;
		}
		report_info("IncDec izraz ", arr);
	}

	public void visit(VarIncDec var) {
		Obj obj = var.getSimpleDesignator().obj;

		if (obj.getKind() == Obj.Con) {
			report_error("Nedozvoljen operand ", var);
			return;
		}

		Struct type = var.getSimpleDesignator().obj.getType();
		if (type == null) {
			return;
		}
		if (type != Tab.intType) {
			report_error("Pogresan tip uz post operator", var);
			return;
		}
		report_info("IncDec izraz ", var);
	}

	public void visit(FuncCallExpr funcCall) {
		SimpleDesignator d = funcCall.getFuncCall().getStartFuncCall().getSimpleDesignator();
		Obj obj = Tab.find(d.getName());
		if (obj.getKind() != Obj.Meth) {
			report_error("Simbol " + d.getName() + " ne predstavlja funkciju", funcCall);
		} else if (obj.getType() == Tab.noType) {
			report_error("Void metoda ne moze biti deo izraza", funcCall);
		}
		funcCall.struct = obj.getType();
		report_info("Pronadjen poziv funkcije", funcCall);
	}

	public void visit(CharRef charRef) {
		charRef.struct = Tab.charType;
	}

	public void visit(ParenthesisExpr e) {
		e.struct = e.getExpr().struct;
	}

	public void visit(MulopFactor mulopFactor) {
		Struct leftOperand = mulopFactor.getFactorList().struct;
		Struct rightOperand = mulopFactor.getFactor().struct;

		if (leftOperand == Tab.intType && leftOperand.equals(rightOperand)) {
			mulopFactor.struct = leftOperand;
		} else {
			report_error("Nekompatibilni tipovi u izrazu za mnozenje", mulopFactor);
			mulopFactor.struct = Tab.noType;
		}
	}

	public void visit(SimpleFactor simpleFactor) {
		simpleFactor.struct = simpleFactor.getFactor().struct;
	}

	public void visit(Term term) {
		term.struct = term.getFactorList().struct;
	}

	public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getTerm().struct;
	}

	public void visit(NormalExpr e) {
		e.struct = e.getTermList().struct;
	}

	public void visit(MinusExpr e) {
		if (e.getTermList().struct != Tab.intType) {
			report_error("Izraz mora biti int ako je ispred njega minus ", e);
			return;
		}
		e.struct = e.getTermList().struct;
	}

	public void visit(AddExpr addExpr) {
		Struct leftOperand = addExpr.getTermList().struct;
		Struct rightOperand = addExpr.getTerm().struct;

		if (leftOperand == Tab.intType && leftOperand.equals(rightOperand)) {
			addExpr.struct = leftOperand;
		} else {
			report_error("Nekompatibilni tipovi u izrazu za sabiranje", addExpr);
			addExpr.struct = Tab.noType;
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * OTHER STATEMENTS
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public void visit(VarReadDesignator vrd) {
		Obj o = vrd.getSimpleDesignator().obj;
		if (o.getKind() != Obj.Var) {
			report_error("Argument read funkcije mora biti promenjljiva", vrd);
			return;
		}
		Struct type = o.getType();
		if (type != Tab.intType && type != Tab.charType) {
			report_error("Argument read funkcije mora biti int, char ili boolean ", vrd);
			return;
		}
		vrd.obj = o;
				
	}

	public void visit(ArrReadDesignator ard) {
		Obj o = ard.getArrayDesignator().obj;
		if (o.getType() != Tab.intType && o.getType() != Tab.charType) {
			report_error("Argument READ funkcije mora biti int,char ili boolean ", ard);
		}
		ard.obj = o;
	}

	public void visit(PrintStmt print) {
		if (print.getExpr().struct != Tab.intType && print.getExpr().struct != Tab.charType
				&& print.getExpr().struct != booleanType) {
			report_error("Argument print metode mora biti INT, CHAR ili BOOL tipa", print);
		} else {
			report_info("Print metoda", print);
		}
	}

	public void visit(ProcCall procCall) {
		/*
		 * SimpleDesignator d =
		 * procCall.getFuncCall().getStartFuncCall().getSimpleDesignator(); Obj obj =
		 * Tab.find(d.getName()); if(obj.getKind() != Obj.Meth) { report_error("Simbol "
		 * + d.getName() + " ne predstavlja metod", procCall); } //does actual params
		 * match formal params
		 * 
		 */

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Formal - Actual PARAMS
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	// Map<Obj,ArrayList<Struct>> formalParamMap = null;

	public void visit(ReturnType returnType) {

		if (returnType.getType().struct.getKind() == 6) {
			currentMethod = returnType.obj = Tab.insert(Obj.Meth, returnType.getMethName(), Tab.intType);
			functionsWhichReturnEnums.put(returnType.getMethName(), enumAsRetType);
		} else {
			currentMethod = returnType.obj = Tab.insert(Obj.Meth, returnType.getMethName(),
					returnType.getType().struct);
		}

		if ("main".equals(currentMethod.getName())) {
			report_error("Main metoda mora biti void", returnType);
		}

		/*
		 * if(formalParamMap == null) { formalParamMap = new HashMap<>(); }
		 * 
		 * formalParamMap.put(currentMethod,new ArrayList<>());
		 */

		Tab.openScope();
	}

	public void visit(VoidRetType voidRetType) {
		isVoid = true;
		currentMethod = voidRetType.obj = Tab.insert(Obj.Meth, voidRetType.getMethName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(ReturnExpr returnExpr) {
		returnFound = true;
		if (isVoid) {
			report_error("Void metoda vraca neku vrednost", returnExpr);
			return;
		}

		Struct t = returnExpr.getExpr().struct;

		// Return value is enum type
		if (enumAsRetType != null) {
			SingleEnumCounter singleEnum = new SingleEnumCounter();
			returnExpr.getExpr().traverseTopDown(singleEnum);
			if (singleEnum.getCount() > 1) {
				report_error("Izraz u return naredbi nije kompatibilan sa tipom povratne vrednosti funkcije "
						+ currentMethod.getName(), returnExpr);
				return;
			}

			if (enumRef == null) {
				report_error("Izraz u return naredbi nije kompatibilan sa tipom povratne vrednosti funkcije "
						+ currentMethod.getName(), returnExpr);
				return;
			}

			Obj obj = enumRef.getSimpleDesignator().obj;
			if (obj == Tab.noObj) {
				report_error("Simbol nije definisan ", returnExpr);
				return;
			}
			if (obj.getKind() != Obj.Type || obj.getType().getKind() != 6) {
				report_error(obj.getName() + " ne predstavlja nabrajanje", returnExpr);
				return;
			}

			if (enumAsRetType.getName() != enumRef.getSimpleDesignator().obj.getName()) {
				report_error("Izraz u return naredbi nije kompatibilan sa tipom povratne vrednosti funkcije "
						+ currentMethod.getName(), returnExpr);
				return;

			}

			String name = enumRef.getConstName();

			Obj found = null;

			for (Obj o : obj.getType().getMembers()) {
				if (name.equals(o.getName())) {
					found = o;
					break;
				}
			}

			if (found == null) {
				report_error("Pogresna konstanta nabrajanja za tip nabrajanja " + enumAsRetType.getName(), returnExpr);
			}

			enumRef = null;
			enumAsRetType = null;

			return;

		}

		if (!t.compatibleWith(currentMethod.getType())) {
			report_error("Izraz u return naredbi nije kompatibilan sa tipom povratne vrednosti funkcije "
					+ currentMethod.getName(), returnExpr);
		}

	}

	public void visit(ReturnNoExpr returnNoExpr) {
		returnFound = true;
		if (!isVoid) {
			report_error("Ne-VOID metoda mora vratiti neku vrednost", returnNoExpr);
		}
	}

	public void visit(MethodDecl methodDecl) {
		if (!isVoid && !returnFound) {
			report_error("Ne-VOID metoda mora vratiti neku vrednost", methodDecl);
		}
		returnFound = false;
		Tab.chainLocalSymbols(methodDecl.getReturnTypeName().obj);
		Tab.closeScope();
		currentMethod = null;
	}

	int nfpars = 0;

	public void visit(FormalSimpleParamDecl fparamDecl) {

		String name = fparamDecl.getName();

		Type type = fparamDecl.getType();

		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Vec postoji formalan parametar sa imenom" + name, fparamDecl);
		} else {
			Tab.insert(Obj.Var, name, type.struct);
			// formalParamMap.get(currentMethod).add(type.struct);
			nfpars++;
		}
	}

	public void visit(FormalArrParamDecl fparamDecl) {

		String name = fparamDecl.getName();
		Type t = fparamDecl.getType();
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Vec postoji formalan parametar sa imenom " + name, fparamDecl);
			return;
		}
		Struct arrStruct = new Struct(Struct.Array, t.struct);
		Tab.insert(Obj.Var, name, arrStruct);
		nfpars++;

	}

	public void visit(FormParams fparams) {
		if (currentMethod != null) {
			currentMethod.setLevel(nfpars);
			currentMethod.setLocals(Tab.currentScope.getLocals());
			nfpars = 0;
		} else {
			report_error("Current metod greska", fparams);
		}
	}

	// Actual params

	Stack<Obj> funcCalls = new Stack<>();

	Stack<Integer> funcCallsHelper = new Stack<>();

	Map<Integer, ArrayList<Struct>> actualParamMap = new HashMap<>();

	private static int k = 0;

	int actualParamInd = 0;

	public void visit(StartFuncCall sfc) {
		// things for enums
		actualParamInd = 0;
		alternativeTypeStack.push(new HashMap<Integer, Struct>());

		// ----------------------------------------------------

		Obj obj = sfc.getSimpleDesignator().obj;
		if (obj == Tab.noObj) {
			return;
		}
		if (obj.getKind() != Obj.Meth) {
			report_error("Simbol " + obj.getName() + " ne predstavlja naziv funkcije", sfc);
		} else {
			funcCalls.push(obj);
			actualParamMap.put(k, new ArrayList<Struct>());
			funcCallsHelper.push(k);
			k++;
		}
	}

	/*
	 * public void visit(ActualParam actualParam) { if(!funcCalls.empty()) {
	 * actualParamMap.get(funcCalls.peek()).add(actualParam.getExpr().struct); } }
	 */

	HashMap<Integer, Struct> alternativeType = new HashMap<>();
	Stack<HashMap<Integer, Struct>> alternativeTypeStack = new Stack<>();

	public void visit(ActualParamList acl) {
		if (!funcCalls.empty()) {
			actualParamMap.get(funcCallsHelper.peek()).add(acl.getExpr().struct);

			// alternative type for enums
			EnumAsActualParam enumAs = new EnumAsActualParam();
			acl.getExpr().traverseTopDown(enumAs);
			Struct enumType = enumAs.getEnumType();
			if (enumType != null) {
				alternativeTypeStack.peek().put(actualParamInd, enumType);
			}
			actualParamInd++;
		}

	}

	public void visit(Exprs e) {
		if (!funcCalls.empty()) {
			actualParamMap.get(funcCallsHelper.peek()).add(e.getExpr().struct);

			// alternative type for enums
			EnumAsActualParam enumAs = new EnumAsActualParam();
			e.getExpr().traverseTopDown(enumAs);
			Struct enumType = enumAs.getEnumType();
			if (enumType != null) {
				alternativeTypeStack.peek().put(actualParamInd, enumType);
			}
			actualParamInd++;
		}

	}

	private void reverseArray(ArrayList<Struct> a) {
		for (int i = 0; i < a.size() / 2; i++) {
			Struct temp = a.get(i);
			a.set(i, a.get(a.size() - 1 - i));
			a.set(a.size() - 1 - i, temp);
		}
	}

	private int getReversedIndex(int index, int size) {
		return size - 1 - index;
	}

	public void visit(EndFuncCall efc) {

		// provera: da li actualParams odgovaraju formalParams

		actualParamInd = 0;

		if (funcCalls.empty()) {
			return;
		}

		ArrayList<Struct> actualParams = actualParamMap.get(funcCallsHelper.peek());
		reverseArray(actualParams);

		// things for enums.... :(

		HashMap<Integer, Struct> alternativeTypes = alternativeTypeStack.peek();
		HashMap<Integer, Struct> validAlternativeTypes = new HashMap<>();
		
		
		
		for (Integer key : alternativeTypes.keySet()) {
			int reversed = getReversedIndex(key, actualParams.size());
			validAlternativeTypes.put(reversed, alternativeTypes.get(key));
		}

		// -----------------------------------

		ArrayList<Struct> formalParams = new ArrayList<>();
		Collection<Obj> formal = funcCalls.peek().getLocalSymbols();
		for (Obj o : formal) {
			System.out.println(o.getName());
			formalParams.add(o.getType());
		}
		;

		System.out.println("formal: " + formalParams.size());
		System.out.println("actual: " + actualParams.size());

		if (actualParams.size() != funcCalls.peek().getLevel()) {
			report_error("Neodgovarajuc broj argumenata pri pozivu funkcije " + funcCalls.peek().getName(), efc);
			return;
		} else {
			for (int i = 0; i < actualParams.size(); i++) {
				Struct actualStruct = actualParams.get(i);
				if (actualStruct != null) {
					if (!actualStruct.assignableTo(formalParams.get(i))) {

						// try alternative type - for enums
						Struct alternativeType = validAlternativeTypes.get(i);
						if (alternativeType == null || !alternativeType.assignableTo(formalParams.get(i))) {
							report_error("Nije odgovarajuc " + (i + 1) + " argument u pozivu funkcije "
									+ funcCalls.peek().getName(), efc);
						} else {
							continue;
						}
					}
				}
			}
		}

		report_info("Poziv funkcije " + funcCalls.peek().getName(), efc);

		actualParamMap.remove(funcCallsHelper.peek());
		funcCalls.pop();
		funcCallsHelper.pop();
		alternativeTypeStack.pop();
	}

	// DEO KOJI SE ODNOSI NA B

	public void visit(ConditionFactorWithRelop cf) {
		Struct type1 = cf.getExpr1().struct;
		Struct type2 = cf.getExpr().struct;
		
		
		//enum part
		
		EnumAsActualParam enumAs = new EnumAsActualParam();  //name of this class is not so intuitive....it finds enumType
		cf.getExpr1().traverseTopDown(enumAs);
		Struct enumType1 = enumAs.getEnumType();
		Struct enumType2;
		if(enumType1 != null) {			
			enumAs.reset();
			cf.getExpr().traverseTopDown(enumAs);
			enumType2=enumAs.getEnumType();
			if(enumType2 == null) {
				report_error("Sa desne strane enuma mora biti odgovarajuca enum konstanta", cf);				
			}else if(!(enumType2.equals(enumType1))) {
				report_error("Sa desne strane enuma mora biti odgovarajuca enum konstanta", cf);				
			}
			return;
		}
		
		
		
		//--------------------------------
		
		if (!type1.compatibleWith(type2)) {
			report_error("Tipovi operanada koji se porede nisu kompatibilni ", cf);
			return;
		}

		Relop relop = cf.getRelop();

		if ((type1.getKind() == Struct.Array || type2.getKind() == Struct.Array)
				&& !(relop instanceof Eq || relop instanceof Neq)) {
			report_error("Za nizove su dozvoljeni relacioni operator jednakosti i nejednakosti ", cf);
		}
	}

	public void visit(SimpleConditionFactor simpleCondFactor) {
		if (simpleCondFactor.getExpr().struct != booleanType) {
			report_error("Promenljiva koja predstavlja uslov mora biti bool tipa", simpleCondFactor);
		}
	}

	int forCnt = 0;

	public void visit(StartFor sfor) {
		forCnt++;
	}

	public void visit(EndFor efor) {
		forCnt--;
	}

	public void visit(Break br) {
		if (forCnt == 0) {
			report_error("Break naredba se ne nalazi unutar for petlje", br);
		}
	}

	public void visit(Continue cont) {
		if (forCnt == 0) {
			report_error("Continue naredba se ne nalazi unutar for petlje", cont);
		}
	}

	// --------------------------------------------------------------------

	public boolean passed() {
		return !errorDetected;
	}

}
