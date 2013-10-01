package montador;

public class Conversores {
	
	
public static String converte_binario(String s, int tam) {
		
    	int valor = Integer.parseInt(s); // pega o texto e passa pra inteiro
		String bin = Integer.toBinaryString(valor); // pega o valor e passa pra binario
			
			if(bin.length()<tam){
			
				String zeros = "";

				for (int i = 0; i < tam - bin.length(); i++)

					zeros += "0";

				bin = zeros + bin;
				
			}else{
				
				bin = bin.substring(16, 32);
	
			}
			return bin;
	}


public static int conversorDecimal(String bin) {
	
	int decimal = Integer.parseInt(bin, 2); // converte pra decimal

	return decimal;
		
}


public static int getBinarioNegativo(String bin){

	String cWord = " ";
	String nWord = " ";
	String compl = "1";
	char sinal;
	char zero = '0';
	int i;
	int decimal;
	String bit;
	
	//verifica se o primeiro bit  0, se for,  positivo, converte normal.
	
	sinal = bin.charAt(0);
	
	
	if(sinal==zero){
		
		
		//decimal = Integer.parseInt(bin, 2);

		return conversorDecimal(bin);
		
	}else{
	
	//Complementando o bin�rio negativo
	for (i = 16; i > 0; i--) {
	
		bit =bin.substring(i - 1, i);
		if (bit.equals("1")) 
		{
				cWord = "0" + cWord;
		}
	
		if (bit.equals("0")) 
		{
				cWord = "1" + cWord;
		}
	}
	
		String s[] = cWord.split(" ");
		cWord = s[0];
	
	
	//Somando mais 1 para retornar ao binario positivo
		for (i = 16; i > 0; i--) {
	
			bit = cWord.substring(i - 1, i);
	
			if (bit.equals(compl)) 
			{
				nWord = "0" + nWord;
			}
	
			else if (compl.equals("1")) 
			{
				compl = "-1";
				nWord = "1" + nWord;
	
			} else
				nWord = bit + nWord;
			}
		s = nWord.split(" ");
		nWord = s[0];
	
		decimal = (-1*(Integer.parseInt(nWord,2)));
		System.out.println(" valor convertido" + nWord + " em decimal " + decimal);
		return decimal;
	
	}
	
}



public static String intToHexString(int d)
{
   String leadingZero = new String("0");
   String leadingX = new String("0x");
   String t = Integer.toHexString(d); // converte para hexadecimal
   while (t.length() < 8)
      t = leadingZero.concat(t);
      
   t = leadingX.concat(t);   
   return t;
}



}
