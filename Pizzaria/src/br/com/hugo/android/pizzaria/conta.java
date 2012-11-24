package br.com.hugo.android.pizzaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class conta extends Activity {
	TextView nome;
	TextView data;
	TextView valor;
	Bundle b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conta);
		
		Intent i = getIntent();
		b = i.getExtras();
		
		TextView nome = (TextView) findViewById(R.id.contacliente);
		TextView data =  (TextView) findViewById(R.id.contadata);
		TextView valor = (TextView) findViewById(R.id.contavalor);
		
		if(b != null){
			nome.setText(b.getString("nomecliente"));
			data.setText(b.getString("data"));
			valor.setText(String.valueOf(b.getFloat("valor")));
		}	
		
	}
}
