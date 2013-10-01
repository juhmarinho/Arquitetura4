package processamento;

import montador.Conversores;
import montador.Registrador;


public class ULA {
	
	private String op = null;
	private int rs = 0;
	private int rt = 0;
	private int rd = 0;  //tipo r
	private int imed = 0; //tipo i,j,h
	private int end = 0;
	private int end_j = 0;
	private int shamt = 0;
	private String funct = null;
	private int endereco;

	public ULA() {
		// TODO Auto-generated constructor stub
	}	
	
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public int getRs() {
		return rs;
	}

	public void setRs(int rs) {
		this.rs = rs;
	}

	public int getRt() {
		return rt;
	}

	public void setRt(int rt) {
		this.rt = rt;
	}

	public int getRd() {
		return rd;
	}

	public void setRd(int rd) {
		this.rd = rd;
	}

	public int getImed() {
		return imed;
	}

	public void setImed(int imed) {
		this.imed = imed;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getEnd_j() {
		return end_j;
	}

	public void setEnd_j(int end_j) {
		this.end_j = end_j;
	}

	public int getShamt() {
		return shamt;
	}

	public void setShamt(int shamt) {
		this.shamt = shamt;
	}

	public String getFunct() {
		return funct;
	}

	public void setFunct(String funct) {
		this.funct = funct;
	}

	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	// ----------------------INSTRUÇÕES DO TIPO_R-------------------------//

	public void add(String instr) {
			try {
			// Desmembrando a instrucao
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			rd = Conversores.conversorDecimal(instr.substring(16, 21));
			shamt = Conversores.conversorDecimal(instr.substring(21, 26));
			funct = instr.substring(26, 32);

			// Efetuando operacao
			int add1 = Registrador.getValor(rs);
			int add2 = Registrador.getValor(rt);
			int soma = add1 + add2;

			// Atualizando registrador
			Registrador.atualizarReg(rd, soma);
			
		} catch (Exception e) {
			System.out.println("Erro na adicao");
			e.printStackTrace();
		}

	}
	
	public void sub(String instr) {

		try {
			// Desmembrando instrução
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			rd = Conversores.conversorDecimal(instr.substring(16, 21));
			shamt = Conversores.conversorDecimal(instr.substring(21, 26));
			funct = instr.substring(26, 32);

			// Efetuando operação
			int sub1 = Registrador.getValor(rs);
			int sub2 = Registrador.getValor(rt);
			int sub = sub1 - sub2;

			// Atualizando registrador com o novo valor
			Registrador.atualizarReg(rd, sub);

		} catch (Exception e) {
			System.out.println("Erro na subtração");
			e.printStackTrace();
		}
	}

	public void mult(String instr) {
		// TODO Auto-generated method stub
		try{
			
			
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			rd = Conversores.conversorDecimal(instr.substring(16, 21));
			shamt = Conversores.conversorDecimal(instr.substring(21, 26));
			funct = instr.substring(26, 32);
			
			int mult1 = Registrador.getValor(rs);
			int mult2 = Registrador.getValor(rt);
			int mult = mult1 * mult2;

			// Atualizando registrador com o novo valor
			Registrador.atualizarReg(rd, mult);

		} catch (Exception e) {
			System.out.println("Erro na multiplica��o");
			e.printStackTrace();
		}
	}
	
	public void sll(String instr){
		
		try {
			
			
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			rd = Conversores.conversorDecimal(instr.substring(16, 21));
			shamt = Conversores.conversorDecimal(instr.substring(21, 26));
			funct = instr.substring(26, 32);

			Registrador.atualizarReg(rd,
					Registrador.getValor(rt) << shamt);

			
			
		} catch (Exception e) {
			System.out.println("Erro no sll");
			e.printStackTrace();
			
		}
		
		
	}
	
	public void srl(String instr){
		
		try {
			
			
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			rd = Conversores.conversorDecimal(instr.substring(16, 21));
			shamt = Conversores.conversorDecimal(instr.substring(21, 26));
			funct = instr.substring(26, 32);

			Registrador.atualizarReg(rd,
					Registrador.getValor(rt) >>> shamt);

			
			
		} catch (Exception e) {
			System.out.println("Erro no srl");
			e.printStackTrace();
			
		}
		
		
	}
	
	public void and(String instr){
		
		try {
			
			
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			rd = Conversores.conversorDecimal(instr.substring(16, 21));
			shamt = Conversores.conversorDecimal(instr.substring(21, 26));
			funct = instr.substring(26, 32);
			
			int and1 = Registrador.getValor(rs);
			int and2 = Registrador.getValor(rt);
			int and = and1 & and2;
			System.out.println("and" + and);
			Registrador.atualizarReg(rd,and);

			
			
		} catch (Exception e) {
			System.out.println("Erro no srl");
			e.printStackTrace();
			
		}
		
	}
	
	public void or(String instr){
		
		try {
			
			
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			rd = Conversores.conversorDecimal(instr.substring(16, 21));
			shamt = Conversores.conversorDecimal(instr.substring(21, 26));
			funct = instr.substring(26, 32);
			
			int or1 = Registrador.getValor(rs);
			int or2 = Registrador.getValor(rt);
			int or = or1 | or2;
		
			Registrador.atualizarReg(rd,or);

			
			
		} catch (Exception e) {
			System.out.println("Erro no srl");
			e.printStackTrace();
			
		}
		
	}
		
 	public void addi(String instr) {
 		
 		try {
			// Desmembrando a instrucao
			op = instr.substring(0, 6);
			rs = Conversores.conversorDecimal(instr.substring(6, 11));
			rt = Conversores.conversorDecimal(instr.substring(11, 16));
			imed = Conversores.getBinarioNegativo(instr.substring(16, 32));
			
			// Efetuando operacao
			int add1 = Registrador.getValor(rt);
			int soma = add1 + imed;
			// Atualizando registrador com o novo valor
			Registrador.atualizarReg(rs, soma);
			
			
			
		} catch (Exception e) {
			System.out.println("Erro no addi");
			e.printStackTrace();
		}

 	}





}
