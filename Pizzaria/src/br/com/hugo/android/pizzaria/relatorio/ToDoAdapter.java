package br.com.hugo.android.pizzaria.relatorio;

import java.util.List;

import br.com.hugo.android.pizzaria.dao.ClienteDAO;
import br.com.hugo.android.pizzaria.entity.Cliente;
import br.com.hugo.android.pizzaria.entity.Pedido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ToDoAdapter extends ArrayAdapter<Pedido> {

	Context context;
	int textViewResourceId;
	List<Pedido> allToDos;

	public ToDoAdapter(Context context, int textViewResourceId,
			List<Pedido> allToDos) {
		super(context, textViewResourceId, allToDos);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.allToDos = allToDos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// super.getView(position, convertView, parent);

		ToDoAuxiliar auxiliar = null;

		if (convertView == null) {
			LayoutInflater layout = LayoutInflater.from(context);
			convertView = layout.inflate(textViewResourceId, parent, false);

			auxiliar = new ToDoAuxiliar();

			
			auxiliar.cliente = (TextView) convertView
					.findViewById(br.com.hugo.android.pizzaria.R.id.clientepedido);
			auxiliar.mesa = (TextView) convertView
					.findViewById(br.com.hugo.android.pizzaria.R.id.mesapedido);
			auxiliar.data = (TextView) convertView
			.findViewById(br.com.hugo.android.pizzaria.R.id.datapedido);

			convertView.setTag(auxiliar);

		} else {
			auxiliar = (ToDoAuxiliar) convertView.getTag();
		}

		Pedido p = allToDos.get(position);
		Cliente c = ClienteDAO.getClienteDao(getContext()).select(p.getCliente());
		auxiliar.cliente.setText(c.getNome());
		auxiliar.mesa.setText(String.valueOf(p.getMesa()));
		auxiliar.data.setText(p.getData());
		return convertView;
	}

	private static class ToDoAuxiliar {
		TextView cliente;
		TextView mesa;
		TextView data;
	}

	@Override
	public int getCount() {
		return allToDos.size();
	}

	@Override
	public Pedido getItem(int position) {
		return allToDos.get(position);
	}
}
