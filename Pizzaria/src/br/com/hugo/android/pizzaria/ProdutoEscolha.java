package br.com.hugo.android.pizzaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProdutoEscolha extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.roduto_escolha);
	}
	public void btFuncCAadCardapio(View v){
		Intent itn = new Intent(getApplicationContext(),CadastrarProduto.class);
		startActivity(itn);
	}
	public void BuscarProduto(View v) {
		Intent i = new Intent(getApplicationContext(), BuscarProduto.class);
		startActivity(i);
	}
}
