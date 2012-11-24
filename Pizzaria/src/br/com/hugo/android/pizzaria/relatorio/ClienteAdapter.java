package br.com.hugo.android.pizzaria.relatorio;

import java.util.List;

import br.com.hugo.android.pizzaria.entity.Cliente;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClienteAdapter extends ArrayAdapter<Cliente> {
	
	Context context;
	int textViewResourceId;
	List<Cliente> alltodos;
	
	public ClienteAdapter(Context context, int textViewResourceId,
			List<Cliente> alltodos) {
		super(context, textViewResourceId, alltodos);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.alltodos = alltodos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 //super.getView(position, convertView, parent);
		
		ClienteAuxiliar cliente =  null;
		
		if(convertView == null){
			LayoutInflater layout = LayoutInflater.from(context);
			convertView = layout.inflate(textViewResourceId, parent, false);
			cliente = new ClienteAuxiliar();
			
			cliente.nome = (TextView) convertView
					.findViewById(br.com.hugo.android.pizzaria.R.id.clientenome);
			
			cliente.email = (TextView) convertView
					.findViewById(br.com.hugo.android.pizzaria.R.id.clienteemail);
			
			convertView.setTag(cliente);
		}else{
			cliente =(ClienteAuxiliar) convertView.getTag();
		}
		Cliente c = alltodos.get(position);
		
		cliente.nome.setText(c.getNome());
		cliente.email.setText(c.getEmail());
		return convertView;
	}
	@Override
	public int getCount() {
		return alltodos.size();
	}
	@Override
	public Cliente getItem(int position) {
		return alltodos.get(position);
	}
	public static class ClienteAuxiliar{
		TextView nome;
		TextView email;
	}

	
}
