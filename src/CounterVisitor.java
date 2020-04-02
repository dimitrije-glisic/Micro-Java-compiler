package rs.ac.bg.etf.pp1;

import java.util.HashMap;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CounterVisitor extends VisitorAdaptor {
	
	protected int count;
	
	public int getCount() {
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor{
		
		public void visit(FormalSimpleParamDecl	fpd) {
			count++;
		}
		
		public void visit(FormalArrParamDecl far) {
			count++;
		}
		
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VarId var) {
			count++;
		}
		
		/*
		public void visit(VarIdInit var) {
			count++;
		}
		*/
		public void visit(ArrayId arr) {
			count++;
		}
		
		/*
		public void visit(ArrayIdInit arr) {
			count++;
		}
		*/
		
	}
	
	public static class SingleEnumCounter extends CounterVisitor{
		
		public void visit(SimpleFactor sf) {
			count++;
		}
		
	}
	
	
	public static class SingleEnumFunctionCounter extends CounterVisitor{
		int sfc = 0;	
		Obj func = null;
		boolean dec = false;
		
		
		public Obj getFunc() {
			return func;
		}
		
		
		public void visit(StartFuncCall startFunc) {
			System.out.println("\n\n***startfunc****\n\n");

			if(sfc == 0) {
				this.func = startFunc.getSimpleDesignator().obj;
			}
			sfc++;
		}
		
		
		public void visit(EndFuncCall endFunc) {
			dec = true;
		}
		
		public void visit(EndFactor ef) {
			System.out.println("\n\n***simplef****\n\n");
			
			if(sfc == 0)
				count++;
			if(dec) {
				sfc--;
				dec = false;
			}
			
		}		
		
	}
	
	public static class EnumAsActualParam extends CounterVisitor{
		
		
		Struct enumType  = null;
		
		public void reset() {
			enumType = null;
		}
		
		public Struct getEnumType() {
			return enumType;
		}								
		
		public void visit(VarRef var) {
			if(var.getSimpleDesignator().obj.getType().getKind() == 6) {
				enumType = var.getSimpleDesignator().obj.getType();
			}
		}
		
		public void visit(EnumRef enumRef) {
			enumType = enumRef.getSimpleDesignator().obj.getType();
		}
		
		
	}
	
}
