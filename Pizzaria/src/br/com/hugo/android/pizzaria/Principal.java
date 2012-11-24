package br.com.hugo.android.pizzaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Principal extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

	}
	
	public void btFuncChamaCliente(View v){
		Intent i = new Intent(getApplicationContext(),ClienteEscolha.class);
		startActivity(i);
	}
	public void btFuncPedido(View v){
	
		Intent in = new Intent(getApplicationContext(),Pedido.class);
		startActivity(in);
	}
	public void btFuncCardapio(View v){
	
		Intent it = new Intent(getApplicationContext(),Cardapio.class);
		startActivity(it);
	}
	public void btFuncFinalizarPedido(View v){
		Intent ip = new Intent(getApplicationContext(),FinalizarPedido.class);
		startActivity(ip);
	}
	public void btFuncChamarProduto(View v){
		Intent id = new Intent(getApplicationContext(),ProdutoEscolha.class);
		startActivity(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		return true;
	}
}
