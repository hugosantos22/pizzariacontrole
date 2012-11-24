package br.com.hugo.android.pizzaria;

import br.com.hugo.android.pizzaria.dao.ClienteDAO;
import br.com.hugo.android.pizzaria.entity.Cliente;
import br.com.hugo.android.pizzaria.relatorio.ClienteAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Toast;

public class BuscarCliente extends ListActivity implements OnCreateContextMenuListener {
	private ClienteAdapter ad;
	public static final int CONTEXTMENUITEMEDITAR = 101;
	public static final int CONTEXTMENUITEMDELETAR = 102;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ad =  new ClienteAdapter(getApplicationContext(), R.layout.buscar_cliente, 
				ClienteDAO.getClienteDao(getApplicationContext()).selectAll());
		
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
		Cliente c = ad.getItem(info.position);
		if(item.getItemId() == CONTEXTMENUITEMEDITAR){
			Intent in = new Intent(getApplicationContext(),CadastraCliente.class);
			in.putExtra("idcliente", c.getId());
			Log.d("teste do buscar", "id do cliente: " + c.getId());
			startActivity(in);
		}
		else if(item.getItemId() == CONTEXTMENUITEMDELETAR){
			ClienteDAO.getClienteDao(getApplicationContext()).delete(c.getId());
			Toast.makeText(getApplicationContext(), "Deletado com Sucesso", Toast.LENGTH_SHORT).show();
		}
		return true;
		
	}
}
