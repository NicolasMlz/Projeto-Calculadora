package br.com.cod3r.calculadora.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{
	
	public Botao(String texto, Color cor) {
		
		//Texto do botao
		setText(texto);
		//Configuracoes do texto
		setFont(new Font("courier", Font.PLAIN, 23));
		
		
		//Cor do botao
		setFocusPainted(false);
		setOpaque(true);
		setBackground(cor);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
}
