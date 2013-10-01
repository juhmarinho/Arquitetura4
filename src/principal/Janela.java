package principal;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
//import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
//import java.awt.ScrollPane;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;


//import java.awt.SystemColor;
//import javax.swing.UIManager;



import montador.Conversores;
import montador.ManipulaArquivo;
import montador.Montador;
import montador.Registrador;

import processamento.Memoria;
import processamento.UCP;




import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Janela extends JFrame {

	private JPanel Principal;
	public static JTable table;
	//private JTable table_1;
	//private JTable table_2;
	//private static JScrollPane scrollPane = new JScrollPane();
	//private static JScrollPane scrollPane_1 = new JScrollPane();
	//private static JScrollPane scrollPane_2 = new JScrollPane();
	int count =0;
	
	private JTextPane textPaneEdit;
	private JTextPane textPaneMsg;
	private JButton Executar ;
	public JButton Passoapasso;
	private JScrollPane scrollPane_3;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JScrollPane scrollPane_5;
	private JScrollPane scrollPane_4;
	private int i = 0 ;
	private String j ="1" ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela frame = new Janela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criando a Janela
	 */
	public Janela() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("azul-botoes-icones_21-759.jpg"));
		setTitle("JJR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 757);
		Principal = new JPanel();
		Principal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Principal);
		
		
		//Bot�o Reset
		JButton Reset = new JButton("Reset");
		Reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Limpar();
				textPaneMsg.setText("Resetado");
			}
		});
		Reset.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		Reset.setBackground(Color.WHITE);
		Reset.setIcon(new ImageIcon("Reset.jpg"));
		
		
		//Bot�o Assembly
		JButton Assembly = new JButton("Assembly");
		Assembly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					ManipulaArquivo.escritor("textoGerado.txt", textPaneEdit.getText());
					UCP.Assembly("textoGerado.txt");
					
					/*for (int i = 0; i < Montador.comandoBinario.size(); i++) {
						textPaneMsg.setText(Montador.comandoBinario.get(i));
							}
					
					*/
					
					textPaneMsg.setText(textPaneMsg.getText() + "\nArquivo Compilado com sucesso !");
					
					
					Executar.setEnabled(true);
					Passoapasso.setEnabled(true);
					atualizarTabelaTexto(table_5);
					atualizarTabelaMemoria(table_4);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Assembly.setBackground(Color.WHITE);
		Assembly.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		Assembly.setIcon(new ImageIcon("Assembly.jpg"));
				
		//Botao Novo
		JButton Novo = new JButton("Novo");
		Novo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Limpar();
				textPaneEdit.setEnabled(true);
				textPaneMsg.setText("Programa JJR ~ ");
			}
		});
		Novo.setBackground(Color.WHITE);
		Novo.setForeground(Color.BLACK);
		Novo.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		Novo.setIcon(new ImageIcon("Novo.jpg"));
		
		
		
		
		//Bot�o Salvar
		JButton Salvar = new JButton("Salvar");
		Salvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser saveEmp = new JFileChooser();//new dialog
				

	        	if (saveEmp.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) 
	        		{
	        			return; 
	        		}


        		
        		try {  
        			
                    PrintWriter out = new PrintWriter(saveEmp.getSelectedFile() + ".txt" );  
                    
                 
                    String[] frase = textPaneEdit.getText().split("\n");
                    for(int i=0;i<frase.length;i++){
                    	if(!frase[i].equals(""))
                    		out.print(frase[i]+"\n"); 
                    }
                    out.flush();  
                      
                    out.close();  
                  
                } catch ( IOException exc ) {             
                }  
        	
				
        		textPaneMsg.setText(textPaneMsg.getText() + "\nArquivo Salvo com sucesso !");
				
				
				
				
				
			}
		});
		Salvar.setBackground(Color.WHITE);
		Salvar.setIcon(new ImageIcon("Salvar.jpg"));
		Salvar.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		
		
		//Bot�o Abrir
		JButton Abrir = new JButton("Abrir");
		Abrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				JFileChooser loadEmp = new JFileChooser();//new dialog


	        	if (loadEmp.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) 
	        		{
	        			return; 
	        		}

					
	        	try {	
	        		//File arq1 = loadEmp.getSelectedFile().getAbsoluteFile();
	        		
	        		
	        		//caminho = arq1.getParent(); 
                    //nomeArq = arq1.getName();
	        			
					FileReader fr = new FileReader(loadEmp.getSelectedFile());
	        		BufferedReader br = new BufferedReader(fr);
	        		String linha;
	        		StringBuffer sb = new StringBuffer();
	        		

	        		while((linha = br.readLine()) != null) 
	        		{
	        			sb.append(linha).append("\n");
	        		}

	        		fr.close();
	        		textPaneEdit.setText(sb.toString());
	        		textPaneEdit.setEnabled(true);
	        		
	        		
	        		textPaneMsg.setText("Arquivo aberto com Sucesso");
	        		
	        	} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex2) {
					ex2.printStackTrace();
				}
				
				
	        	
				
			
			}
		});
		Abrir.setBackground(Color.WHITE);
		Abrir.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		Abrir.setIcon(new ImageIcon("Abrir.jpg"));
	
		
		//Botao Executar
		Executar = new JButton("Executar");
		Executar.setEnabled(false);
		Executar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					UCP.Executar(0);
					atualizarTabelaRegistradores(table_3);
					//atualizarTabelaMemoria(table_4);
					textPaneMsg.setText(textPaneMsg.getText() + "\nArquivo Executado com Sucesso");
					Executar.setEnabled(false);
					Passoapasso.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Executar.setBackground(Color.WHITE);
		Executar.setIcon(new ImageIcon("Executar.jpg"));
		Executar.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		
		
		//Botao Executar Passo a Passo
		Passoapasso = new JButton("Passo a Passo");
		Passoapasso.setEnabled(false);
		Passoapasso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					j = UCP.ExecutarStep(0);
					atualizarTabelaRegistradores(table_3);
					atualizarTabelaMemoria(table_4);
					if (j == "0"){
						textPaneMsg.setText(textPaneMsg.getText() +" todo programa foi executado");
						Passoapasso.setEnabled(false);
						Executar.setEnabled(false);
					}else{						
						textPaneMsg.setText(textPaneMsg.getText() +"\npasso " + (i+1) + " executado com sucesso !");
						i++;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		Passoapasso.setBackground(Color.WHITE);
		Passoapasso.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		Passoapasso.setIcon(new ImageIcon("Passoapasso.jpg"));
		
		
		
		
		//Botao Fechar
		JButton Fechar = new JButton("Fechar");
		Fechar.setBackground(Color.WHITE);
		Fechar.setIcon(new ImageIcon("Fechar.jpg"));
		Fechar.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		Fechar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		
		
		
		JTabbedPane Central = new JTabbedPane(JTabbedPane.TOP);
		Central.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		
		JTabbedPane Mensagens = new JTabbedPane(JTabbedPane.TOP);
		Mensagens.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		
      

		JTabbedPane Registradores = new JTabbedPane(JTabbedPane.TOP);
		Registradores.setFont(new Font("EngraversGothic BT", Font.PLAIN, 16));
		
		
		JToolBar Registrador = new JToolBar();
		Registrador.setFont(new Font("EngraversGothic BT", Font.PLAIN, 12));
		Registradores.addTab("Registradores", null, Registrador, null);
		
		scrollPane_3 = new JScrollPane();
		Registrador.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{"$zero", "0", null},
				{"$at", "1", null},
				{"$v0", "2", null},
				{"$v1", "3", null},
				{"$a0", "4", null},
				{"$a1", "5", null},
				{"$a2", "6", null},
				{"$a3", "7", null},
				{"$t0", "8", null},
				{"$t1", "9", null},
				{"$t2", "10", null},
				{"$t3", "11", null},
				{"$t4", "12", null},
				{"$t5", "13", null},
				{"$t6", "14", null},
				{"$t7", "15", null},
				{"$s0", "16", null},
				{"$s1", "17", null},
				{"$s2", "18", null},
				{"$s3", "19", null},
				{"$s4", "20", null},
				{"$s5", "21", null},
				{"$s6", "22", null},
				{"$s7", "23", null},
				{"$t8", "24", null},
				{"$t9", "25", null},
				{"$k0", "26", null},
				{"$k1", "27", null},
				{"$gp", "28", null},
				{"$sp", "20", null},
				{"$fp", "30", null},
				{"$ra", "31", null},
				{"pc", "32", null},
			},
			new String[] {
				"Nome", "Numero", "Valor"
			}
		));
		scrollPane_3.setViewportView(table_3);
		atualizarTabelaRegistradores(table_3);
		table_3.setEnabled(false);
		
		JToolBar Msg = new JToolBar();
		Mensagens.addTab("JJR Mensagens ", null, Msg, null);
		
		textPaneMsg = new JTextPane();
		Msg.add(textPaneMsg);
		
		JToolBar Editar = new JToolBar();
		Central.addTab("Editar", null, Editar, null);
		
		textPaneEdit = new JTextPane();
		Editar.add(textPaneEdit);
		textPaneEdit.setEnabled(false);
		
		JToolBar Memoria = new JToolBar();
		Central.addTab("Memoria", null, Memoria, null);
		
		scrollPane_4 = new JScrollPane();
		Memoria.add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Endereco", "Valor(+0)", "Valor(+1)", "Valor(+2)", "Valor(+3)", "Valor(+4)", "Valor(+5)", "Valor(+6)", "Valor(+7)"
			}
		));
		scrollPane_4.setViewportView(table_4);
		
		JToolBar Texto = new JToolBar();
		Texto.setFont(new Font("EngraversGothic BT", Font.PLAIN, 12));
		Central.addTab("Texto", null, Texto, null);
		
		scrollPane_5 = new JScrollPane();
		Texto.add(scrollPane_5);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Endereco", "Codigo"
			}
		));
		table_5.getColumnModel().getColumn(1).setPreferredWidth(237);
		table_5.getColumnModel().getColumn(1).setMinWidth(44);
		scrollPane_5.setViewportView(table_5);
		GroupLayout gl_Principal = new GroupLayout(Principal);
		gl_Principal.setHorizontalGroup(
			gl_Principal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Principal.createSequentialGroup()
					.addGroup(gl_Principal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Principal.createSequentialGroup()
							.addComponent(Reset)
							.addGap(6)
							.addComponent(Assembly)
							.addGap(6)
							.addComponent(Novo)
							.addGap(6)
							.addComponent(Salvar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(Abrir, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(Executar))
						.addGroup(gl_Principal.createSequentialGroup()
							.addGroup(gl_Principal.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_Principal.createSequentialGroup()
									.addGap(10)
									.addComponent(Mensagens, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE))
								.addComponent(Central, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_Principal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Principal.createSequentialGroup()
							.addComponent(Passoapasso)
							.addGap(10)
							.addComponent(Fechar))
						.addComponent(Registradores, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
					.addGap(55))
		);
		gl_Principal.setVerticalGroup(
			gl_Principal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Principal.createSequentialGroup()
					.addGroup(gl_Principal.createParallelGroup(Alignment.LEADING)
						.addComponent(Reset)
						.addComponent(Assembly)
						.addGroup(gl_Principal.createSequentialGroup()
							.addGap(1)
							.addComponent(Novo))
						.addComponent(Salvar)
						.addGroup(gl_Principal.createSequentialGroup()
							.addGap(1)
							.addComponent(Abrir))
						.addComponent(Executar)
						.addComponent(Passoapasso)
						.addComponent(Fechar))
					.addGroup(gl_Principal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Principal.createSequentialGroup()
							.addGap(11)
							.addComponent(Central, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Mensagens, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Principal.createSequentialGroup()
							.addGap(28)
							.addComponent(Registradores, GroupLayout.PREFERRED_SIZE, 587, GroupLayout.PREFERRED_SIZE)))
					.addGap(66))
		);
		Principal.setLayout(gl_Principal);
	}
	
	
	private void atualizarTabelaTexto(JTable tabela) {
		// TODO Auto-generated method stub
		
		String[] col = new String[] {"Endereco","Codigo"};
		String[][] table3 = new String[Montador.comando.size()][2];
		
		for(int i=0;i<Montador.comando.size();i++){
			table3[i][0] = Conversores.intToHexString(i*4);
			table3[i][1] = Montador.comando.get(i);
			
		}
		
		DefaultTableModel dtm = new DefaultTableModel(table3, col);  
	 
	    tabela.setModel(dtm); 
	    tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
	    tabela.getColumnModel().getColumn(1).setPreferredWidth(400);
	   
	    scrollPane_5.setViewportView(tabela);
		

	}
	
	
	public void atualizarTabelaRegistradores(JTable tabela){
				
		String[] col = new String[] {"Numero","Nome","Valor"};
		
		String[][] table1 = new String[33][3];
		
		
		
	    for (int i=0; i< Registrador.regFile.length; i++)
	    {
	    	table1[i][0] = Registrador.regFile[i].getNome();
	    	table1[i][1] = Integer.toString(Registrador.regFile[i].getNumero());
	    	table1[i][2] = Conversores.intToHexString(Registrador.regFile[i].getValor());
	    		
	    }
	    //table1[33][1]="";
	    DefaultTableModel dtm = new DefaultTableModel(table1, col);  
	    
	    
	     tabela.setModel(dtm); 
	     tabela.setCellSelectionEnabled(false);   
	     tabela.setRowSelectionAllowed(true); 
	     
	     tabela.getColumnModel().getColumn(1).setPreferredWidth(30);
	    
	     scrollPane_3.setViewportView(tabela);

	}

	private void atualizarTabelaMemoria(JTable tabela) {
		// TODO Auto-generated method stub
		
		
		String[] col = new String[] {"Endereco","Valor(+0)", "Valor(+1)", "Valor(+2)", "Valor(+3)", "Valor(+4)", "Valor(+5)", "Valor(+6)", "Valor(+7)"};
		String[][] table2 = new String[16][9];
		
		for (int i=0; i< 16; i++){
			table2[i][0] = Conversores.intToHexString(i*8);
		}
		
		for (int k=0; i< Memoria.getMemoria().length; i++){
			if(Memoria.getMemoria()[i]== null)
				Memoria.getMemoria()[i]="00000000";
		}
		
		//System.err.println(Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[0],2)));
			
	for(int j=1;j<9;j++){
				table2[0][j] = Memoria.getMemoria()[(j-1)+0];//Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[j-1],2));
				//System.err.println(Integer.parseInt(Memoria.getMemoria()[j-1],2));
				table2[1][j] = Memoria.getMemoria()[(j-1)+8];
				table2[2][j] =  Memoria.getMemoria()[(j-1)+16];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+16]));
				table2[3][j] = Memoria.getMemoria()[(j-1)+24]; //"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+24]));
				table2[4][j] =  Memoria.getMemoria()[(j-1)+32];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+32]));
				table2[5][j] =  Memoria.getMemoria()[(j-1)+40];//"0x"+  Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+40]));
				table2[6][j] =  Memoria.getMemoria()[(j-1)+48];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+48]));
				table2[7][j] =  Memoria.getMemoria()[(j-1)+56];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+56]));
				table2[8][j] = Memoria.getMemoria()[(j-1)+64]; //"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+64]));
				table2[9][j] =  Memoria.getMemoria()[(j-1)+72];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+72]));
				table2[10][j] =  Memoria.getMemoria()[(j-1)+80];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+80]));
				table2[11][j] =  Memoria.getMemoria()[(j-1)+ 88];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+88]));
				table2[12][j] =  Memoria.getMemoria()[(j-1)+ 96];//"0x" + Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+96]));
				table2[13][j] =  Memoria.getMemoria()[(j-1)+104];//"0x"+ Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+104]));
				table2[14][j] =  Memoria.getMemoria()[(j-1)+112];//"0x"+ Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+112]));
				table2[15][j] = Memoria.getMemoria()[(j-1)+120];//"0x"+ Integer.toHexString(Integer.parseInt(Memoria.getMemoria()[(j-1)+120]));
				
			}
			
		
		DefaultTableModel dtm = new DefaultTableModel(table2, col);  
	    
	    
	    tabela.setModel(dtm); 
	    scrollPane_4.setViewportView(tabela);
		
	}
	
	
	private void Limpar(){
		
		Registrador.Limpar();
		atualizarTabelaRegistradores(table_3);
		Memoria.IniciarMemoria();
		Montador.comando.clear();
		textPaneEdit.setText("");
		atualizarTabelaTexto(table_5);
		
	}
}
