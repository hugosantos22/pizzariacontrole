package br.com.hugo.android.pizzaria;

import br.com.hugo.android.pizzaria.dao.PedidoDAO;
import br.com.hugo.android.pizzaria.relatorio.ToDoAdapter;
import android.app.Activity;
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
import android.widget.ArrayAdapter;

public class FinalizarPedido extends ListActivity implements
		OnCreateContextMenuListener {

	private ToDoAdapter adp;
	public static final int CONTEXTMENUITEMCONTINUAR = 101;
	public static final int CONTEXTMENUITEMFINALIZAR = 102;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adp = new ToDoAdapter(getApplicationContext(),
				R.layout.finalizar_pedido, PedidoDAO.getPedidoDao(
						getApplicationContext()).selectAll());
		setListAdapter(adp);
		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId() == getListView().getId()) {
			menu.add(0, CONTEXTMENUITEMCONTINUAR, 1, R.string.menucontinuar);
			menu.add(0, CONTEXTMENUITEMFINALIZAR, 2, R.string.menufinalizar);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		super.onContextItemSelected(item);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		br.com.hugo.android.pizzaria.entity.Pedido pedido = adp
				.getItem(info.position);
		
		Log.d("teste", "pedido " + pedido.getId());

		if (item.getItemId() == CONTEXTMENUITEMCONTINUAR) {
			Intent in = new Intent(getApplicationContext(), Itens_Pedido.class);
			in.putExtra("idpedido", pedido.getId());
			startActivity(in);
		} else if (item.getItemId() == CONTEXTMENUITEMFINALIZAR) {
			Intent i = new Intent(getApplicationContext(),
					ContinuarPedido.class);
			i.putExtra("idpedido", pedido.getId());
			startActivity(i);
		}
		return true;
	}
}
