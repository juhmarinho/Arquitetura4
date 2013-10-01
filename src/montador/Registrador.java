package montador;

public class Registrador {

	private String nome;
	private int numero, resetValor;
	private volatile int valor;

	public Registrador(String n, int num, int val) {
		nome = n;
		numero = num;
		valor = val;
		resetValor = val;
	}

	
	public String getNome() {
		return nome;
	}

	public int getResetValor() {
		return resetValor;
	}

	public int getNumero() {
		return numero;
	}

	public int getValor() {
		return valor;
	}

	public int setValor(int val) {
		valor = val;

		return valor;
	}
	
	public static int getValor(int num) {

		return regFile[num].getValor();

	}
	public static String getNome(int num) {

		return regFile[num].getNome();

	}

	//public static final int GLOBAL_POINTER_REGISTER = 28;
	//public static final int STACK_POINTER_REGISTER = 29;

	public static Registrador[] regFile = { 
			new Registrador("$zero", 0, 0),
			new Registrador("$at", 1, 0), 
			new Registrador("$v0", 2, 0),
			new Registrador("$v1", 3, 0), 
			new Registrador("$a0", 4, 0),
			new Registrador("$a1", 5, 0),
			new Registrador("$a2", 6, 0),
			new Registrador("$a3", 7, 0), 
			new Registrador("$t0", 8, 0),
			new Registrador("$t1", 9, 0), 
			new Registrador("$t2", 10, 0),
			new Registrador("$t3", 11, 0), 
			new Registrador("$t4", 12, 0),
			new Registrador("$t5", 13, 0), 
			new Registrador("$t6", 14, 0),
			new Registrador("$t7", 15, 0),
			new Registrador("$s0", 16, 0),
			new Registrador("$s1", 17, 0), 
			new Registrador("$s2", 18, 0),
			new Registrador("$s3", 19, 0),
			new Registrador("$s4", 20, 0),
			new Registrador("$s5", 21, 0),
			new Registrador("$s6", 22, 0),
			new Registrador("$s7", 23, 0),
			new Registrador("$t8", 24, 0),
			new Registrador("$t9", 25, 0),
			new Registrador("$k0", 26, 0),
			new Registrador("$k1", 27, 0),
			new Registrador("$gp", 28, 0),
			new Registrador("$sp", 29, 64),
			new Registrador("$fp", 30, 0), 
			new Registrador("$ra", 31, 0),
			new Registrador("$pc", 32, 0) 
			};  //4194304

	

	public static int atualizarReg(int num, int val) {
		int old = 0;

		for (int i = 0; i < regFile.length; i++) {
			if (regFile[i].getNumero() == num) {
				old = regFile[i].setValor(val);
				break;
			}
		}

		return old;
	}

	public static void atualizarReg(String reg, int val) {
		// if(reg.equals("$zero")){
		// System.out.println("You can not change the value of the zero register.");
		// }
		// else{
		for (int i = 0; i < regFile.length; i++) {
			if (regFile[i].getNome().equals(reg)) {
				atualizarReg(i, val);
				break;
			}
		}
		// }
	}

	

	public static int getNumero(String n) {
		int j = -1;
		for (int i = 0; i < regFile.length; i++) {
			if (regFile[i].getNome().equals(n)) {
				j = regFile[i].getNumero();
				break;
			}
		}
		return j;
	}



	public static void Limpar() {
		for (int i = 0; i < regFile.length; i++) {
			Registrador.atualizarReg(i, regFile[i].getResetValor());
		}

	}

	
	public static void ImprimirRegistradores() {
		for (int i = 0; i < regFile.length; i++) {
			System.out.println("registrador" + Registrador.getNome(i) + " valor " + Registrador.getValor(i));
		}

	}
	
	
	
	
}

	
	
