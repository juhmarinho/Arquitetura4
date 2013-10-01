package processamento;


import java.io.IOException;
//import java.util.ArrayList;

public class Memoria {
	//static ArrayList<String> memoriaArray = new ArrayList<String>();
	private static String[] memoria = new String[1024];

	public Memoria() throws IOException {

	}
	

	public static String[] getMemoria() {
		return memoria;
	}

	public static void setMemoria(String[] memoria) {
		Memoria.memoria = memoria;
	}

	public static void IniciarMemoria() {
		// TODO Auto-generated method stub
		for (int i = 0; i < getMemoria().length; i++) {
			getMemoria()[i] = null;
		}
		
		/*for(int i = 0;i<128;i++){
			getMemoria()[i] = "0";
			
		}*/
			
		
	}
	
	
	public static void ImprimirMemoria() throws IOException {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < getMemoria().length; i++) {
	  if (getMemoria()[i] == null) break; 
			System.out.println("memoria posicao " + i + " : " + getMemoria()[i] );
		}
		
			
		
	}
	
	public static int loadWord(int endereco) {
		int retorno = 0;

		retorno = Integer.parseInt(memoria[endereco]);
		System.out.println(retorno);
		return retorno;
	}

	public static void setWord(int endereco, int valor) {

		memoria[endereco] = Integer.toString(valor);
		
	}


}
