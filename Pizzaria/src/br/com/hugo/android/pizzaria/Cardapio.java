package br.com.hugo.android.pizzaria;

import java.util.List;

import br.com.hugo.android.pizzaria.dao.ProdutoDAO;
import br.com.hugo.android.pizzaria.entity.Produto;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Cardapio extends ListActivity {

	private ArrayAdapter<Produto> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		adapter = new ArrayAdapter<Produto>(getApplicationContext(), android.R.layout.simple_list_item_1);
		
		setListAdapter(adapter);
		
		loadToDos();
		
	}
	private void loadToDos() {
		adapter.clear();
		List<Produto> allToDos = ProdutoDAO.getProdutoDao(getApplicationContext())
				.selectAll();
		if (allToDos != null) {
			for (Produto item : allToDos) {
				adapter.add(item);
			}
		}
	}
	
	

}
