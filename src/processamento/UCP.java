package processamento;

import java.io.IOException;
import java.util.ArrayList;

import montador.Conversores;
import montador.Montador;
import montador.Registrador;
import montador.TabelaReferencias;

public class UCP {
	
	static ArrayList<String> inst= new ArrayList<String>();
	static String s = null;
	static String op = null;//
	static int rs = 0;//
	static int rt = 0;//
	static int rd = 0;//
	static int imed = 0;//
	static int end = 0;//
	static int end_j = 0;//
	static int shamt = 0;//
	static int endereco;//
	static String funct = null;//
	static String instr_r = null;
	static String instr_h = null;
	static String instr_r_op = null;
	static String instr_i = null;
	static String instr_j = null;
	static int stepc = 0;

	public UCP() throws IOException {

	}


	public static void Assembly(String arquivo) throws IOException {
	
		int i = 0;
		//int j = 0;
		
		inst = Montador.montar(arquivo);
		
		System.out.println("instrução para leitura" + inst);
		//LerInstr();
		Memoria.IniciarMemoria();
		//preenchendo a memoria
		for (i = 0; i < inst.size(); i++) {
			//j = i + 128;  // ?????????????????????????
			Memoria.getMemoria()[i] = inst.get(i);
			
		}
		Memoria.ImprimirMemoria();
	

}
	// Motor do simulador
	public static void Executar(int ini) throws IOException {

		ULA ula = new ULA();
		
		TabelaReferencias tabela  = new TabelaReferencias();
		
		int pc = ini;

		while (Memoria.getMemoria()[pc] != null) {

			
			instr_r = Memoria.getMemoria()[pc].substring(26, 32);
			instr_r_op = Memoria.getMemoria()[pc].substring(0, 6);
			instr_i = Memoria.getMemoria()[pc].substring(0, 6);
			instr_j = Memoria.getMemoria()[pc].substring(0, 6);
			instr_h = Memoria.getMemoria()[pc].substring(0, 6);
			Registrador.atualizarReg("$pc", ((pc - ini) * 4));

			

			// executa cada funcao
			if (instr_r.equals(tabela.tipo_r.get("add").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("add").substring(0, 6))) {

				ula.add(Memoria.getMemoria()[pc]);
				pc++;
				continue;
			}
			
			if (instr_r.equals(tabela.tipo_r.get("and").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("and").substring(0, 6))) {

				ula.and(Memoria.getMemoria()[pc]);
				pc++;
				continue;
			}
			

			if (instr_r.equals(tabela.tipo_r.get("or").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("or").substring(0, 6))) {

				ula.or(Memoria.getMemoria()[pc]);
				pc++;
				continue;
			}
			//funcao mult
			if (instr_r.equals(tabela.tipo_r.get("mult").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("mult").substring(0, 6))) {

				ula.mult(Memoria.getMemoria()[pc]);
				pc++;
				continue;
			}
			
			// Função j
			if (instr_j.equals(tabela.tipo_j.get("j"))) {

				try {
					// Desmembrando a Instrução
					op = Memoria.getMemoria()[pc].substring(0, 6);
					end = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 32));
					System.out.println("jumper op : " + op + "  pc  :" + pc + "  end : " + end);
					// Executando operação
					pc = ini + end;
				} catch (Exception e) {
					System.out.println("Erro no Jump da linha " + pc);
					e.printStackTrace();
					break;
				}
				continue;
			}

			// funcao addi
			if (instr_i.equals(tabela.tipo_i.get("addi"))) {

				ula.addi(Memoria.getMemoria()[pc]);
				pc++;
				continue;
			}
			// Função lw
			if (instr_i.equals(tabela.tipo_i.get("lw"))) {

				try {
					// Desmembrando a instrução
					op = Memoria.getMemoria()[pc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(11, 16));
					endereco = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(16, 32));

						Registrador.atualizarReg(rt,Memoria.loadWord(Registrador.getValor(rs)+ (endereco)));
					pc++;
					continue;
				} catch (Exception e) {
					System.out.println("Erro no lw");
					e.printStackTrace();
					break;
				}
			}
			// Função sw
			if (instr_i.equals(tabela.tipo_i.get("sw"))) {

				// Desmembrando a instrução
				try {
					op = Memoria.getMemoria()[pc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(11, 16));
					endereco = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(16, 32));
		Memoria.setWord(Registrador.getValor(rs) + endereco,Registrador.getValor(rt));
						pc++;
					continue;
				} catch (Exception e) {
					System.out.println("Erro no lw");
					e.printStackTrace();
					break;
				}
			}
			// Função sub
			if (instr_r.equals(tabela.tipo_r.get("sub").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("sub").substring(0, 6))) {

				ula.sub(Memoria.getMemoria()[pc]);
				pc++;
				continue;
			}
			// Função jr
			if (instr_r.equals(tabela.tipo_r.get("jr").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("jr").substring(0, 6))) {
				try {

					op = Memoria.getMemoria()[pc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 11));

					pc = ini + Registrador.getValor(rs);
					continue;
				} catch (Exception e) {
					System.out.println("Erro no jr");
					e.printStackTrace();
					break;
				}
			}
			// Função sll
			if (instr_r.equals(tabela.tipo_r.get("sll").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("sll").substring(0, 6))) {
				
				ula.sll(Memoria.getMemoria()[pc]);
				pc++;
				continue;
				
			}
			// Função srl
			if (instr_r.equals(tabela.tipo_r.get("srl").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("srl").substring(0, 6))) {
				
				ula.srl(Memoria.getMemoria()[pc]);
					pc++;
					continue;
				
			
			}
			// Função beq
			if (instr_i.equals(tabela.tipo_i.get("beq"))) {
				try {
					op = Memoria.getMemoria()[pc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(11, 16));
					end = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(16, 32));
					if (Registrador.getValor(rs) == Registrador.getValor(rt)) {
						pc = ini + end;
					} else
						pc++;
					continue;
				} catch (Exception e) {
					System.out.println("Erro no beq");
					e.printStackTrace();
					break;
				}
			}
			// Função bne
			if (instr_i.equals(tabela.tipo_i.get("bne"))) {
				try {
					System.out.println("entroubne2");
					op = Memoria.getMemoria()[pc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(11, 16));
					end = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(16, 32));

					if (Registrador.getValor(rs) != Registrador.getValor(rt)) {
						pc = ini + end;
					} else
						pc++;
					continue;
				} catch (Exception e) {
					System.out.println("Erro no bne");
					e.printStackTrace();
					break;
				}
			}
			// Função jal
			else if (instr_j.equals(tabela.tipo_j.get("jal"))) {
				try {
					op = Memoria.getMemoria()[pc].substring(0, 6);
					end = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 32));

					Registrador.atualizarReg(31, (pc + 1) - ini);
					pc = ini + end;
					continue;
				} catch (Exception e) {
					System.out.println("Erro no jal");
					e.printStackTrace();
					break;
				}
			}
			// Função slt
			if (instr_r.equals(tabela.tipo_r.get("slt").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("slt").substring(0, 6))) {
				try {
					op = Memoria.getMemoria()[pc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(11, 16));
					rd = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(16, 21));
					shamt = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(21, 26));
					funct = Memoria.getMemoria()[pc].substring(26, 32);

					if (Registrador.getValor(rs) < Registrador.getValor(rt))
						Registrador.atualizarReg(rd, 1);
					else
						Registrador.atualizarReg(rd, 0);
					pc++;
					continue;
				} catch (Exception e) {
					System.out.println("Erro no slt");
					e.printStackTrace();
					break;
				}
			}
			// Função slti
			if (instr_i.equals(tabela.tipo_i.get("slti"))) {
				try {
					op = Memoria.getMemoria()[pc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(11, 16));
					imed = Conversores.conversorDecimal(Memoria.getMemoria()[pc].substring(16, 32));

					if (Registrador.getValor(rt) < imed)
						Registrador.atualizarReg(rs, 1);
					else
						Registrador.atualizarReg(rs, 0);
					pc++;
					continue;
				} catch (Exception e) {
					System.out.println("Erro no slti");
					e.printStackTrace();
					break;
				}
			}
			
			if(instr_h.equals(tabela.tipo_h.get("halt"))){
				
				break;
			}

		}

	}



	public static String ExecutarStep(int ini) throws IOException {

		ULA ula = new ULA();
		TabelaReferencias tabela = new TabelaReferencias();
		

		if (Memoria.getMemoria()[stepc] != null) {

			instr_r = Memoria.getMemoria()[stepc].substring(26, 32);
			instr_r_op = Memoria.getMemoria()[stepc].substring(0, 6);
			instr_i = Memoria.getMemoria()[stepc].substring(0, 6);
			instr_j = Memoria.getMemoria()[stepc].substring(0, 6);
			instr_h = Memoria.getMemoria()[stepc].substring(0, 6);
			Registrador.atualizarReg("$pc", ((stepc - ini) * 4));

			
			if (instr_r.equals(tabela.tipo_r.get("add").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("add").substring(0, 6))) {

				ula.add(Memoria.getMemoria()[stepc]);
				stepc++;
			}
			//funcao mult
			if (instr_r.equals(tabela.tipo_r.get("mult").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("mult").substring(0, 6))) {

				ula.mult(Memoria.getMemoria()[stepc]);
				stepc++;
				
			}
			// Funcao j
			if (instr_j.equals(tabela.tipo_j.get("j"))) {

				try {
					// Desmembrando a Instrução
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					end = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 32));

					// Executando operação
					stepc = ini + end;
				} catch (Exception e) {
					System.out.println("Erro no Jump da linha " + stepc);
					e.printStackTrace();
				}
			}

			// funcao addi
			if (instr_i.equals(tabela.tipo_i.get("addi"))) {

				ula.addi(Memoria.getMemoria()[stepc]);
				stepc++;
			}
			// Função lw
			if (instr_i.equals(tabela.tipo_i.get("lw"))) {

				try {
					// Desmembrando a instrução
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(11, 16));
					endereco = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(16,32));

					// Executando a operação
//					if (endereco == 0)
						Registrador.atualizarReg(rt,Memoria.loadWord(Registrador.getValor(rs)+ endereco));
	//				else
		//				Registrador.atualizarReg(
			//					rt,
				//				Memoria.loadWord(Registrador.getValor(rs)
					//					+ (endereco)));
					stepc++;
				} catch (Exception e) {
					System.out.println("Erro no lw");
					e.printStackTrace();
				}
			}
			// Função sw
			if (instr_i.equals(tabela.tipo_i.get("sw"))) {

				// Desmembrando a instrução
				try {
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(12, 16));
					endereco = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(16,32));

					// Executando a operação
					//if (endereco == 0)
						Memoria.setWord(Registrador.getValor(rs) + endereco,Registrador.getValor(rt));
				//	else
					//	Memoria.setWord(Registrador.getValor(rs) + (endereco),
						//		Registrador.getValor(rt));
					stepc++;
				} catch (Exception e) {
					System.out.println("Erro no sw");
					e.printStackTrace();
				}
			}
			// Função sub
			if (instr_r.equals(tabela.tipo_r.get("sub").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("sub").substring(0, 6))) {

				ula.sub(Memoria.getMemoria()[stepc]);
				stepc++;
			}
			// Função jr
			if (instr_r.equals(tabela.tipo_r.get("jr").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("jr").substring(0, 6))) {
				try {

					op = Memoria.getMemoria()[stepc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 11));

					stepc = ini + Registrador.getValor(rs);
				} catch (Exception e) {
					System.out.println("Erro no jr");
					e.printStackTrace();
				}
			}
			// Função sll
			if (instr_r.equals(tabela.tipo_r.get("sll").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("sll").substring(0, 6))) {

				ula.sll(Memoria.getMemoria()[stepc]);
				stepc++;
			}
			if (instr_r.equals(tabela.tipo_r.get("srl").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("srl").substring(0, 6))) {

				ula.srl(Memoria.getMemoria()[stepc]);
				stepc++;
			}
			// Função beq
			if (instr_i.equals(tabela.tipo_i.get("beq"))) {
				try {
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(11, 16));
					end = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(16, 32));
					if (Registrador.getValor(rs) == Registrador.getValor(rt)) {
						stepc = ini + end;
					} else
						stepc++;
				} catch (Exception e) {
					System.out.println("Erro no beq");
					e.printStackTrace();
				}
			}
			// Função bne
			if (instr_i.equals(tabela.tipo_i.get("bne"))) {
				try {
					//System.out.println("entroubne");
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(11, 16));
					end = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(16, 32));

					if (Registrador.getValor(rs) != Registrador.getValor(rt)) {
						stepc = ini + end;
					} else
						stepc++;
				} catch (Exception e) {
					System.out.println("Erro no bne");
					e.printStackTrace();
				}
			}
			// Função jal
			else if (instr_j.equals(tabela.tipo_j.get("jal"))) {
				try {
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					end = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 32));

					Registrador.atualizarReg(31, (stepc + 4) - ini);
					stepc = ini + end;
				} catch (Exception e) {
					System.out.println("Erro no jal");
					e.printStackTrace();
				}
			}
			// Função slt
			if (instr_r.equals(tabela.tipo_r.get("slt").substring(7, 13))
					&& instr_r_op.equals(tabela.tipo_r.get("slt").substring(0, 6))) {
				try {
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(11, 16));
					rd = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(16, 21));
					shamt = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(21, 26));
					funct = Memoria.getMemoria()[stepc].substring(26, 32);

					if (Registrador.getValor(rs) < Registrador.getValor(rt))
						Registrador.atualizarReg(rd, 1);
					else
						Registrador.atualizarReg(rd, 0);
					stepc++;
				} catch (Exception e) {
					System.out.println("Erro no slt");
					e.printStackTrace();
				}
			}
			// Função slti
			if (instr_i.equals(tabela.tipo_i.get("slti"))) {
				try {
					op = Memoria.getMemoria()[stepc].substring(0, 6);
					rs = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(6, 11));
					rt = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(11, 16));
					imed = Conversores.conversorDecimal(Memoria.getMemoria()[stepc].substring(16, 32));

					if (Registrador.getValor(rt) < imed)
						Registrador.atualizarReg(rs, 1);
					else
						Registrador.atualizarReg(rs, 0);
					stepc++;
				} catch (Exception e) {
					System.out.println("Erro no slti");
					e.printStackTrace();
				}
			}
			
			if(instr_h.equals(tabela.tipo_h.get("halt"))){
				
				try{
					return "0";
				//System.out.println("final step");
					
				} catch (Exception e) {
					System.out.println("Erro no slti");
					e.printStackTrace();
				}
			}
			
		}
		return null;

	}

}
