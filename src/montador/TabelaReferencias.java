package montador;
	import java.util.HashMap;



public class TabelaReferencias {
		
	     public HashMap<String, String> label = new HashMap<String, String>();
		 public HashMap<String, String> registradores = new HashMap<String, String>();
		 public HashMap<String, String> tipo_r = new HashMap<String, String>();
		 public HashMap<String, String> tipo_i = new HashMap<String, String>();
		 public HashMap<String, String> tipo_j = new HashMap<String, String>();
		 public HashMap<String, String> tipo_h = new HashMap<String, String>();
		 
		
		public TabelaReferencias() {
			
			tipo_h.put("halt", "111111");
			
			tipo_j.put("j", "000010");
			tipo_j.put("jal", "000011");
			
			tipo_i.put("addi", "001000");//*
			tipo_i.put("andi", "001100");//*	
			tipo_i.put("beq", "000100");
			tipo_i.put("bne", "000101");
			tipo_i.put("lb", "100000");//*
			tipo_i.put("lw", "100011");//*
			tipo_i.put("ori", "001101");//*
			tipo_i.put("sb", "101000");//*
			tipo_i.put("slti", "001010");//*
			tipo_i.put("sw", "101011");//*
			
			tipo_r.put("add", "000000 100000"); 
			tipo_r.put("mult", "000000 011000");
			tipo_r.put("and", "000000 100100");
			tipo_r.put("jr", "000000 001000");
			tipo_r.put("or", "000000 100101");
			tipo_r.put("sll", "000000 000000");
			tipo_r.put("slt", "000000 101010");
			tipo_r.put("srl", "000000 000010");
			tipo_r.put("sub", "000000 100010");
			tipo_r.put("addu", "000000 100001");
		
			
			registradores.put("$zero" , "00000");
			registradores.put("$at"  , "00001");
			registradores.put("$v0"   , "00010");
			registradores.put("$v1"  , "00011");
			registradores.put("$a0"  , "00100");
			registradores.put("$a1"  , "00101");
			registradores.put("$a2"  , "00110");
			registradores.put("$a3"  , "00111");
			registradores.put("$t0"  , "01000");
			registradores.put("$t1"  , "01001");
			registradores.put("$t2"  , "01010");
			registradores.put("$t3"  , "01011");
			registradores.put("$t4"  , "01100");
			registradores.put("$t5"  , "01101");
			registradores.put("$t6"  , "01110");
			registradores.put("$t7"  , "01111");
			registradores.put("$s0"  , "10000");
			registradores.put("$s1"  , "10001");
			registradores.put("$s2"  , "10010");
			registradores.put("$s3"  , "10011");
			registradores.put("$s4"  , "10100");
			registradores.put("$s5"  , "10101");
			registradores.put("$s6"  , "10110");
			registradores.put("$s7"  , "10111");
			registradores.put("$t8"  , "11000");
			registradores.put("$t9"  , "11001");
			registradores.put("$k0"  , "11010");
			registradores.put("$k1"  , "11011");
			registradores.put("$gp"  , "11100");
			registradores.put("$sp"  , "11101");
			registradores.put("$fp"  , "11110");
			registradores.put("$ra"  , "11111");
			

		
		}
		
	}


