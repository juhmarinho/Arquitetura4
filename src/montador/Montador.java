package montador;


import java.io.IOException;
import java.util.ArrayList;

public class Montador {

	public static ArrayList<String> comando = new ArrayList<String>();
	public static ArrayList<String> comandoBinario = new ArrayList<String>();


	public static ArrayList<String> montar(String arquivo) throws IOException {

		try {

			comando = ManipulaArquivo.leitor(arquivo);


			System.out.println("comando lido " + comando.toString());
			String[] linha;
			String aux;


			for (int j = 0; j < comando.size(); j++) {

				aux =	comando.get(j);
				aux = aux.replaceAll("( )*+,( )*+",",");

				linha = aux.split(" |,|\\(|\\)");

				comandoBinario.add(j, (converteComando(linha, arquivo)));

			}

			System.out.println("comando convertido" + comandoBinario.toString());


		} catch (IOException e) {
			e.printStackTrace();
		}	

		return comandoBinario ;
	}


	public static String converteComando(String[] linha, String arq){

		TabelaReferencias tabela = new TabelaReferencias();

		String[] r = null;
		String op = null;
		String rd = null;
		String rt = null;
		String rs = null;
		String shamt = null;
		String imed = null;
		String func = null;
		String inst = null;

		if ((linha[0].equals("add")) || (linha[0].equals("sub"))
				|| (linha[0].equals("and")) || (linha[0].equals("addu"))
				|| (linha[0].equals("nor")) || (linha[0].equals("or"))
				|| (linha[0].equals("slt")) || (linha[0].equals("sltu"))
				|| (linha[0].equals("xor")) || (linha[0].equals("mult"))) {

			r = tabela.tipo_r.get(linha[0]).split(" "); // pega a linha zero do comando e splita

			op = r[0];
			func = r[1];
			rd = tabela.registradores.get(linha[1]);
			rs = tabela.registradores.get(linha[2]);
			rt = tabela.registradores.get(linha[3]);
			shamt = "00000";

			inst = (op + rs + rt + rd + shamt + func);
		}else if(linha[0].equals("halt")){

			op = tabela.tipo_h.get(linha[0]);
			imed = Conversores.converte_binario("0", 26);

			inst = (op+imed);

		}else if (linha[0].equals("sll") || (linha[0].equals("srl"))) {

			r = tabela.tipo_r.get(linha[0]).split(" ");
			op = r[0];
			func = r[1];
			rd = tabela.registradores.get(linha[1]);
			rt = tabela.registradores.get(linha[2]);
			rs = "00000";
			shamt = Conversores.converte_binario(linha[3], 5);

			inst = (op + rs + rt + rd + shamt + func);

		}else if (linha[0].equals("jr")) {

			r = tabela.tipo_r.get(linha[0]).split(" ");
			op = r[0];
			func = r[1];
			rd = "00000";
			rs = tabela.registradores.get(linha[1]);
			rt = "00000";
			shamt = "00000";

			inst = (op + rs + rt + rd + shamt + func);

		}else if ((linha[0].equals("addi")) || (linha[0].equals("addiu"))
				|| (linha[0].equals("andi")) || (linha[0].equals("slti"))
				|| (linha[0].equals("ori"))) {

			op = tabela.tipo_i.get(linha[0]);

			rs = tabela.registradores.get(linha[1]);
			rt = tabela.registradores.get(linha[2]);
			imed = Conversores.converte_binario(linha[3], 16);

			inst = (op + rs + rt + imed);


		}else if ((linha[0].equals("lw")) || (linha[0].equals("sw"))
				|| (linha[0].equals("lb")) || (linha[0].equals("sb"))) {
			op = tabela.tipo_i.get(linha[0]);
			rt = tabela.registradores.get(linha[1]);
			imed = Conversores.converte_binario(linha[2], 16);
			rs = tabela.registradores.get(linha[3]);

			inst = (op + rs + rt + imed);

		}else if ((linha[0].equals("bne")) || (linha[0].equals("beq"))) {
			op = tabela.tipo_i.get(linha[0]);
			rs = tabela.registradores.get(linha[1]);
			rt = tabela.registradores.get(linha[2]);
			imed = Conversores.converte_binario(ManipulaArquivo.LocalizaLabel(linha[3], arq), 16);

			inst = (op + rs + rt + imed);

		} else if ((linha[0].equals("j")) || (linha[0].equals("jal"))) {
			op = tabela.tipo_j.get(linha[0]);
			imed = Conversores.converte_binario(ManipulaArquivo.LocalizaLabel(linha[1], arq), 26);

			inst = (op + imed);

		} else
			inst = linha[0];

		return inst;

	}
}





