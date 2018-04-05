package br.com.caelum.livraria.service;

import static br.com.caelum.livraria.dominio.ClienteTest.umCliente;
import static br.com.caelum.livraria.dominio.ISBNTest.outroIsbnValido;
import static br.com.caelum.livraria.dominio.ISBNTest.umIsbnValido;
import static br.com.caelum.livraria.dominio.Livraria.reais;
import static br.com.caelum.livraria.dominio.LivroTest.outroLivro;
import static br.com.caelum.livraria.dominio.LivroTest.umLivro;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.livraria.dominio.CalculadoraFrete;
import br.com.caelum.livraria.dominio.CarrinhoDeCompras;
import br.com.caelum.livraria.dominio.CarrinhoDeComprasFactory;
import br.com.caelum.livraria.dominio.TodosLivros;

public class SelecaoDeLivroTest {
	
	private TodosLivros todosLivros;
	private SelecaoDeLivro servico;
	private CalculadoraFrete calculadoraFrete; 
	private CarrinhoDeComprasFactory todosCarrinhosDeCompras;
	
	@Before
	public void setUp() {
		this.todosLivros = mock(TodosLivros.class);
		when(todosLivros.por(umIsbnValido)).thenReturn(umLivro);
		
		this.todosCarrinhosDeCompras = new CarrinhoDeComprasFactory();
		this.calculadoraFrete = mock(CalculadoraFrete.class);
		when(calculadoraFrete.baseadoEm(anyString())).thenReturn(Money.of(5, reais));
		this.servico = new SelecaoDeLivro(todosLivros, calculadoraFrete, todosCarrinhosDeCompras);
	}
	
	@Test
	public void criarUmCarrinhoDeComprasComUmLivro() {
		CarrinhoDeCompras carrinho = servico.adicionarLivroNoCarrinhoDoCliente(umIsbnValido, umCliente);
		
		assertThat(carrinho.doCliente(umCliente), is(true));
		assertThat(carrinho, contains(umLivro));
	}
	
	@Test
	public void adicionarLivroEmCarrinhoDeComprasExistente() {
		when(todosLivros.por(outroIsbnValido)).thenReturn(outroLivro);
		
		servico.adicionarLivroNoCarrinhoDoCliente(umIsbnValido, umCliente);
		CarrinhoDeCompras carrinho = servico.adicionarLivroNoCarrinhoDoCliente(outroIsbnValido, umCliente);
		
		assertThat(carrinho.doCliente(umCliente), is(true));
		assertThat(carrinho, hasItems(umLivro, outroLivro));
	}
}