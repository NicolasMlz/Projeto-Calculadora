package br.com.cod3r.calculadora.visao;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.cod3r.calculadora.modelo.Memoria;
import br.com.cod3r.calculadora.modelo.DisplayObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements DisplayObserver {
	
	//Atributos
	private JLabel label;
	
	//Construtor
	public Display() {
		
		//Adicionar observer para ser notificado
		Memoria.getInstancia().adicionarObservador(this);
		
		//Propriedades do painel
		setBackground(new Color(46, 49, 50));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 17));
		
		//Propriedades da fonte
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 35));
		add(label);
	}
	
	//Metodos
	@Override
	public void valorAlterado(String valorAlterado) {
		label.setText(valorAlterado);
	}
	
}
