package br.com.kath.controller.car;

import java.util.List;

import br.com.kath.model.ProdutoModel;

public class NameValidate {

	public int nameValidate(List<ProdutoModel> carProducts, String name) {
		for (int i = 0; i < carProducts.size(); i ++) {
			if (carProducts.get(i).getProductName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
}
