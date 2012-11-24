package br.com.hugo.android.pizzaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClienteEscolha extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cliente_escolha);
	}
	public void btFuncCadastraCliente(View v) {		
		Intent i = new Intent(getApplicationContext(), CadastraCliente.class);
		startActivity(i);
	}
	public void btFuncBuscarCliente(View v){
		Intent in = new Intent(getApplicationContext(),BuscarCliente.class);
		startActivity(in);
	}

}
