package br.com.hugo.android.pizzaria.relatorio;

import java.util.List;

import br.com.hugo.android.pizzaria.entity.Produto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProdutoAdapter extends ArrayAdapter<Produto> {
	
	Context context;
	int textViewResourceId;
	List<Produto> alltodos;

	public ProdutoAdapter(Context context, int textViewResourceId,List<Produto> alltodos) {
		super(context, textViewResourceId,alltodos);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.alltodos = alltodos;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//return super.getView(position, convertView, parent);
		
		ProdutoAuxiliar auxiliar = null;
		if(convertView == null){
			LayoutInflater layout = LayoutInflater.from(context);
			convertView = layout.inflate(textViewResourceId, parent, false);
			auxiliar = new ProdutoAuxiliar();
			
			auxiliar.produto = (TextView) convertView
			.findViewById(br.com.hugo.android.pizzaria.R.id.produtonome);
			
			auxiliar.desc = (TextView) convertView
			.findViewById(br.com.hugo.android.pizzaria.R.id.produtodesc);
			
			auxiliar.preco = (TextView) convertView
			.findViewById(br.com.hugo.android.pizzaria.R.id.produtopreco);
			
			convertView.setTag(auxiliar);
		}else{
			auxiliar = (ProdutoAuxiliar) convertView.getTag();
		}
		Produto p = alltodos.get(position);
		auxiliar.produto.setText(p.getNome());
		auxiliar.desc.setText(p.getDescricao());
		auxiliar.preco.setText(String.valueOf(p.getPreco()));
		return convertView;
	}
	@Override
	public int getCount() {
		return alltodos.size();
	}
	@Override
	public Produto getItem(int position) {
		return alltodos.get(position);
	}
	public static class ProdutoAuxiliar{
		TextView produto;
		TextView desc;
		TextView preco;
	}

}
