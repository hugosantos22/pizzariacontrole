package br.com.hugo.android.pizzaria;

import java.util.ArrayList;
import java.util.List;

import br.com.hugo.android.pizzaria.dao.Itens_pedidoDAO;
import br.com.hugo.android.pizzaria.dao.ProdutoDAO;
import br.com.hugo.android.pizzaria.entity.Itens_pedido;
import br.com.hugo.android.pizzaria.entity.Produto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class Itens_Pedido extends Activity {
	
	private List produto; 
	private Spinner produtos;
	private Itens_pedido item; 
	Bundle b;
	private EditText quant;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_produtos_pedidos);
		
		Intent i = getIntent();
		b = i.getExtras();
		Log.d("teste continuar pedido", "idpedido = " + b.getInt("idpedido"));
		Spinner produtos= (Spinner) findViewById(R.id.produto);
		
		produto = new ArrayList<Produto>();
		produto = ProdutoDAO.getProdutoDao(getApplicationContext()).selectAll();
		
		ArrayAdapter<Produto> aditem = new ArrayAdapter<Produto>(getApplicationContext(),
				android.R.layout.simple_spinner_item,produto);
		produtos.setAdapter(aditem);
	}
	public void salvarItem(View v){
		Spinner produtos= (Spinner) findViewById(R.id.produto);
		EditText quant = (EditText) findViewById(R.id.quantidade);
		Produto p = (Produto) produtos.getSelectedItem();
		int q = Integer.parseInt(quant.getText().toString());
		if(item == null){
			item =  new Itens_pedido();
		}
		item.setPedido(b.getInt("idpedido"));
		item.setProduto(p.getId());
		item.setQuantidade(q);
		if(item.getId() == 0){
			Itens_pedidoDAO.getItens_pedidoDAO(getApplicationContext()).insert(item);
			messagemAviso("Confirmação", "Cadastro efetuado com sucesso");
			
		}else{
			Itens_pedidoDAO.getItens_pedidoDAO(getApplicationContext()).update(item);
			messagemAviso("Confirmação", "Cadastro Atualizado com sucesso");
		}
	}
	public void messagemAviso(String titulo, String msg) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				Itens_Pedido.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("Sim", null);
		mensagem.show();
	}

}
