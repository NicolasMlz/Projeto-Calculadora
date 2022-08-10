package br.com.cod3r.calculadora.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame{
	
	//Construtor da tela
	public Calculadora() {
		
		//Configurar layout com os widgets
		configurarLayout();
		
		//Configuracoes iniciais da janela da calculadora
		setSize(232, 322);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//Metodos
	private void configurarLayout() {
		
		//Definir em qual ponto cardinal o container ficara
		setLayout(new BorderLayout());
		
			//Adicionar display em cima
			Display display = new Display();
			display.setPreferredSize(new Dimension(232, 60));
			add(display, BorderLayout.NORTH);
			
			//Adicionar teclado embaixo do display
			Teclado teclado = new Teclado();
			add(teclado, BorderLayout.CENTER);
		
	}

	//Main
	 public static void main(String[] args) {
		new Calculadora();
	}
}
