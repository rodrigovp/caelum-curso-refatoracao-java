package br.com.caelum.livraria.dominio;

public class ObjetosParaTestes {
	
	public static final Telefone UM_TELEFONE = new Telefone("11", "55555555");
	
	public static final String CEP = "03297060";
	public static final String ID_CLIENTE = "idCliente";
	public static final Cliente UM_CLIENTE = new Cliente("idCliente", CEP, UM_TELEFONE);
	public static final Cliente OUTRO_CLIENTE = new Cliente("outroIdCliente", CEP, UM_TELEFONE);
	public static final Autor umAutor = new Autor("Rodrigo vieira");
}
