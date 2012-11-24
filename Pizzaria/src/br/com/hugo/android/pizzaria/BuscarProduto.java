package br.com.hugo.android.pizzaria;

import br.com.hugo.android.pizzaria.dao.ProdutoDAO;
import br.com.hugo.android.pizzaria.entity.Produto;
import br.com.hugo.android.pizzaria.relatorio.ProdutoAdapter;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BuscarProduto extends ListActivity implements OnCreateContextMenuListener{
	private ProdutoAdapter ad;
	public static final int CONTEXTMENUITEMEDITAR = 101;
	public static final int CONTEXTMENUITEMDELETAR = 102;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ad = new ProdutoAdapter(getApplicationContext(), R.layout.buscar_produto, 
				ProdutoDAO.getProdutoDao(getApplicationContext()).selectAll());
		
		setListAdapter(ad);
		registerForContextMenu(getListView());
		
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if(v.getId() == getListView().getId() ){
			menu.add(0, CONTEXTMENUITEMEDITAR, 1, R.string.menueditar);
			menu.add(0, CONTEXTMENUITEMDELETAR, 2, R.string.menudeletar);
		}
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		super.onContextItemSelected(item);
		
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		
		Produto p = ad.getItem(info.position);
		
		if(item.getItemId() == CONTEXTMENUITEMEDITAR){
			Intent in = new Intent(getApplicationContext(),CadastrarProduto.class);
			in.putExtra("idproduto", p.getId());
			Log.d("teste do buscar", "id do produto: " + p.getId());
			startActivity(in);
		}
		if(item.getItemId() == CONTEXTMENUITEMDELETAR){
			ProdutoDAO.getProdutoDao(getApplicationContext()).delete(p.getId());
			Toast.makeText(getApplicationContext(), "Deletado com Sucesso", Toast.LENGTH_SHORT).show();
		}
		return true;
	}
}
