package br.com.hugo.android.pizzaria;

import br.com.hugo.android.pizzaria.dao.ProdutoDAO;
import br.com.hugo.android.pizzaria.entity.Produto;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastrarProduto extends Activity {

	EditText nome;
	EditText desc;
	EditText preco;
	
 	private Produto produto;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_produto);
		
	}
	public void SalvarProduto(View v){
		
		EditText nome = (EditText) findViewById(R.id.nome_produto);
		EditText desc = (EditText) findViewById(R.id.descricao);
		EditText preco = (EditText) findViewById(R.id.preco);
		
		String n = nome.getText().toString();
		String d = desc.getText().toString();
		float p = Float.parseFloat(preco.getText().toString());
		
		if(produto == null){
			produto = new Produto();
		}
		
		produto.setNome(n);
		produto.setDescricao(d);
		produto.setPreco(p);
		
		if(produto.getId() == 0){
			
			ProdutoDAO.getProdutoDao(getApplicationContext()).insert(produto);
			messagemAviso("Confirmação", "Cadastro efetuado com sucesso");
			
		}else{
			ProdutoDAO.getProdutoDao(getApplicationContext()).update(produto);
			messagemAviso("Atualização", "Cadastro Atualizado com sucesso");
		}
	}
	public void VoltarProduto(View v){
		
		Intent in = new Intent(getApplicationContext(),Principal.class);
		startActivity(in);
		
	}
	public void LimparProduto(View v){
		
		nome.setText("");
		desc.setText("");
		preco.setText("");
	}
	public void messagemAviso(String titulo, String msg) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				CadastrarProduto.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("Sim", null);
		mensagem.show();
	}
}
