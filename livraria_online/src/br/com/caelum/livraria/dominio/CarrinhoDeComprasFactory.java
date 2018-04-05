package br.com.caelum.livraria.dominio;

import static java.time.LocalDate.now;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.javamoney.moneta.Money;

//12 - Extrair classe: Essa classe será o resultado da refatoração.
public class CarrinhoDeComprasFactory implements Iterable<CarrinhoDeCompras> {

	private final Set<CarrinhoDeCompras> carrinhos;
	
	public CarrinhoDeComprasFactory() {
		this.carrinhos = new HashSet<>();
	}
	
	@Override
	public Iterator<CarrinhoDeCompras> iterator() {
		return carrinhos.iterator();
	}
 
	public CarrinhoDeCompras obterCarrinho(final Cliente idCliente, Livro livro, Money valorFrete) {
		CarrinhoDeCompras ret = new CarrinhoDeCompras(idCliente, livro, valorFrete, now());
		if(carrinhos.contains(ret)) {
			ret = carrinhos.stream()
					.filter(carrinho -> carrinho.doCliente(idCliente))
					.findFirst().orElse(null);
			//14 - Ocultar delegação. Nesse ponto, quando o código for bagunçado, será usado um
			// getLivros().adicionar(livro)
			if(ret != null)ret.adicionar(livro);
		}
		else carrinhos.add(ret);
		return ret;
	}

}