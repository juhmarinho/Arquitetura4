package montador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ManipulaArquivo {

	public static ArrayList<String> leitor(String path) throws IOException {
		TabelaReferencias tabela = new TabelaReferencias();
		ArrayList<String> comando = new ArrayList<String>();
		int i = 0;
        try {

 			FileReader fr = new FileReader(path);
 			BufferedReader br = new BufferedReader(fr);
 			String linha = "";
 			String linha2 = "";

 			while ((linha = br.readLine()) != null) {//le linha por linha
 					
 					i++;
	 				linha2 = linha.trim();
	 				if (linha2.contains(":")) { // verifica se tem label
		 				String[] label = linha2.split(": |:");// vai separar por :
		 				tabela.label.put(linha2, Integer.toString(i - 1)); // grava o que o label fará e a linha que se encontra
		 				//if (label.length > 1){
		 					//String label2 = label[1].trim();
		 				comando.add(label[1]);			
		 			}
		 			//if (!linha2.contains(":")) 
 					else
 						comando.add(linha2);
 					
 					
 				//System.out.println("comando tmp "  + comando.toString());
 					
		 						
 		
 			}// fim while
 			
 			fr.close();
 			br.close();
 			
 			
 		} catch (IOException e) {
 			e.printStackTrace();
 		}	
        
        
        return comando ;
	}

	
	
	public static String LocalizaLabel(String palavra,String path) {
		
		
		TabelaReferencias tabela = new TabelaReferencias();
		int i = 0;
        try {

 			FileReader fr = new FileReader(path);
 			BufferedReader br = new BufferedReader(fr);
			String instrucao = "";

			while ((instrucao = br.readLine()) != null) {
				i++;



			if (instrucao.contains(palavra + ":")) {
				tabela.label.put(palavra, Integer.toString(i-1));
			}
		}
			
			fr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tabela.label.get(palavra);
	}


	public static void escritor(String path, String texto) throws IOException { // apenas para testar no inicio
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		
		buffWrite.append(texto);
		
		buffWrite.close();
	}



}
