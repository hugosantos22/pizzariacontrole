package br.com.hugo.android.pizzaria;

import java.util.ArrayList;
import java.util.List;

import br.com.hugo.android.pizzaria.dao.ClienteDAO;
import br.com.hugo.android.pizzaria.dao.PedidoDAO;
import br.com.hugo.android.pizzaria.entity.Cliente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Pedido extends Activity {

	Spinner mesa;
	Spinner cliente;
	EditText data;
	private Cliente cli;
	private List clientes;
	private br.com.hugo.android.pizzaria.entity.Pedido pedido;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.cadastrar_pedido);
		Spinner mesa = (Spinner) findViewById(R.id.mesa);
		Spinner cliente =  (Spinner) findViewById(R.id.cliente);
		
		cli = new Cliente();
		clientes = new ArrayList<Cliente>();
		clientes = ClienteDAO.getClienteDao(getApplicationContext()).selectAll();
		
		ArrayAdapter<Cliente> adcliente = new ArrayAdapter<Cliente>(getApplicationContext(),
				android.R.layout.simple_spinner_item,clientes);
		
		cliente.setAdapter(adcliente);
		
		ArrayAdapter<CharSequence> admesa = ArrayAdapter.createFromResource(this, 
				R.array.spiner_mesa, android.R.layout.simple_spinner_item);
		admesa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mesa.setAdapter(admesa);
	}
	public void Salvar_pedido(View v){
		
		Spinner mesa = (Spinner) findViewById(R.id.mesa);
		Spinner cliente =  (Spinner) findViewById(R.id.cliente);
		
	 
		Cliente cl = (Cliente) cliente.getSelectedItem();
		Log.d("SpinnerTest", "id do cliente: " + cl.getId());
		
		int m = Integer.parseInt(mesa.getSelectedItem().toString());
		if(pedido == null){
			pedido = new br.com.hugo.android.pizzaria.entity.Pedido();
		}
		pedido.setCliente(cl.getId());
		pedido.setMesa(m);
		if(pedido.getId() == 0){
			PedidoDAO.getPedidoDao(getApplicationContext()).insert(pedido);
			int id_pedido = PedidoDAO.getPedidoDao(getApplicationContext()).selectUltimoID();
			Bundle b = new Bundle();
			b.putInt("idpedido", id_pedido);
			
			Intent i = new Intent(getApplicationContext(),Itens_Pedido.class);
			i.putExtras(b);
			startActivity(i);
			
		}else{
			PedidoDAO.getPedidoDao(getApplicationContext()).update(pedido);
			
		}
		
	}
	public void Voltar_pedido(View v){
		Intent in = new Intent(getApplicationContext(),Principal.class);
		startActivity(in);
		finish();
	}
	public void messagemAviso(String titulo, String msg) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				Pedido.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("Sim", null);
		mensagem.show();
	}

}
