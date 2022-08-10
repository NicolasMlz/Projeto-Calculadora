package br.com.cod3r.calculadora.modelo;

@FunctionalInterface
public interface DisplayObserver {
	
	void valorAlterado(String valorAlterado);
}
