package br.com.hugo.android.pizzaria;

import java.nio.channels.FileLock;

import br.com.hugo.android.pizzaria.dao.ClienteDAO;
import br.com.hugo.android.pizzaria.dao.Itens_pedidoDAO;
import br.com.hugo.android.pizzaria.dao.PedidoDAO;
import br.com.hugo.android.pizzaria.dao.ProdutoDAO;
import br.com.hugo.android.pizzaria.entity.Cliente;
import br.com.hugo.android.pizzaria.entity.Itens_pedido;
import br.com.hugo.android.pizzaria.entity.Pedido;
import br.com.hugo.android.pizzaria.entity.Produto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ContinuarPedido extends Activity {
	Bundle b;
	private int idpedido;
	private br.com.hugo.android.pizzaria.entity.Pedido p;
	private Itens_pedido item;
	private Float valor;
	private Float conta;
	private Cliente c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.terminar_pedido);
		b = getIntent().getExtras();
		idpedido = b.getInt("idpedido");
		Log.d("teste finalizar pedido", "idpedido = " + idpedido);
	}
	public void Finalizar(View v){
		p = new Pedido();
		item = new Itens_pedido();
		c = new Cliente();
		item = Itens_pedidoDAO.getItens_pedidoDAO(getApplicationContext()).select(idpedido);
		p = PedidoDAO.getPedidoDao(getApplicationContext()).select(idpedido);
		c =  ClienteDAO.getClienteDao(getApplicationContext()).select(p.getCliente());
		Log.d("Continuar Pedido", "Produto " +item.getProduto());
		valor = ProdutoDAO.getProdutoDao(getApplicationContext()).selectPrecoProduto(item.getProduto());
		Log.d("Continuar Pedido", "Quantidade " + item.getQuantidade());
		Log.d("Continuar Pedido", "preco " +valor);
		
		conta = item.getQuantidade() * valor; 
		
		Bundle b = new Bundle();
		Intent i =  new Intent(getApplicationContext(),conta.class);
		b.putString("nomecliente", c.getNome());
		b.putString("data", p.getData());
		b.putFloat("valor", valor);
		i.putExtras(b);
		startActivity(i);
	}
}
