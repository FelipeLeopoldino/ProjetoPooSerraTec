package classes;

import java.util.ArrayList;

public class Teste {

	public static void main(String[] args) {

		ArrayList<Produto> produto = new ArrayList<Produto>();

		produto.add(new Produto(1, "Caneta", 1.99, 3.99, "Papelaria"));
		produto.add(new Produto(2, "Caderno", 10.99, 20.99, "Papelaria"));
		produto.add(new Produto(3, "Mochila", 19.99, 39.99, "Papelaria"));

		for (Produto produto2 : produto) {
			System.out.println(produto2);
		}



	}
}
