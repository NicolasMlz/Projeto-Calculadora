package br.com.cod3r.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	/* Esse modelo pressupoe que há apenas
	 * uma única memória para qualquer instância
	 * de calculadora criada...
	 * 
	 * Assim, o construtor deve ser privado e usado
	 * em uma instância ESTÁTICA do tipo da classe 
	 */
	
	//Atributos
	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA, SINAL;
	}
	private static final Memoria instancia = new Memoria();
	private final List<DisplayObserver> observadores = new ArrayList<>();
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
	private TipoComando operacao = null;
	
	//Construtor privado
	private Memoria() {
		
	}
	
	//Metodos
	/*Registar observador*/
	public void adicionarObservador(DisplayObserver o) {
		observadores.add(o);
	}
	
	/*Recebe o valor digitado e armazena com o anterior e processa
	 * as informacoes*/
	public void processarDigito(String texto) {
		
		//Definir qual tecla foi detectada
		TipoComando tipoComando = detectarTipoComando(texto);
		
		//O que fazer
		if(tipoComando == null) { return; }
		
		//Resetar a calculadora 
		else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			operacao = null;
		} 
		//Trocar sinal
		else if(tipoComando == TipoComando.SINAL && textoAtual.contains("-")){
			textoAtual = textoAtual.substring(1);
			operacao = null;
		} else if(tipoComando == TipoComando.SINAL && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
			operacao = null;
		} 
		//Armazenar apenas numeros e virgulas no display
		else if(tipoComando == TipoComando.NUMERO 
				|| tipoComando == TipoComando.VIRGULA) {
			
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} 
		//Realizar as operacoes envolvendo os numeros
		else {
			
			//Caso comum a todas as operacoes
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			operacao = tipoComando;	
		}
		
		//Notificar
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}
	
	private String obterResultadoOperacao() {
			
		//Nao fazer nada caso seja o primeiro caso da calculadora	
		if(operacao == null || operacao == TipoComando.IGUAL ||
				operacao == TipoComando.SINAL) {
			return textoAtual;
		} 
		
		//Atributos referente aos cálculos
		double numeroBuffer = 
				Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = 
				Double.parseDouble(textoAtual.replace(",", "."));
		double resultado = 0;
		
		//Calcular de fato
		if(operacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if(operacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		} else if(operacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if(operacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		} 
		
		/*Transformar resultado novamente em String parar ser
		* mostrada no display
		*/
		String resultadoString = 
				Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");
		
		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando(String texto) {
		
		if(textoAtual.isEmpty() && texto == "0") { return null; } 
		else if("AC".equals(texto)) { return TipoComando.ZERAR; }
        else if("/".equals(texto)) { return TipoComando.DIV; }
        else if("*".equals(texto)) { return TipoComando.MULT; }
        else if("+".equals(texto)) { return TipoComando.SOMA; }
        else if("-".equals(texto)) { return TipoComando.SUB; }
        else if("=".equals(texto)) { return TipoComando.IGUAL; }
        else if("±".equals(texto)) { return TipoComando.SINAL; }
        else if(!textoAtual.contains(",") && ",".equals(texto)) { return TipoComando.VIRGULA; }
        else {
        	Integer.parseInt(texto);
        	return TipoComando.NUMERO;
        }	
	}

	//Getters
	public static Memoria getInstancia() {
		return instancia;
	}
	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
}
