package br.com.hugo.android.pizzaria;

import br.com.hugo.android.pizzaria.dao.ClienteDAO;
import br.com.hugo.android.pizzaria.entity.Cliente;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastraCliente extends Activity {
	
	EditText nome;
	EditText email;
	EditText rua;
	EditText bairro;
	EditText numero;
	
	private int idcliente;
	private Cliente cliente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_cliente);
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			idcliente = bundle.getInt("idcliente");
			cliente = new Cliente();
			cliente =  ClienteDAO.getClienteDao(getApplicationContext()).select(idcliente);
			
			if(cliente != null){
				
				EditText nome = (EditText) findViewById(R.id.nome);
				EditText email = (EditText) findViewById(R.id.email);
				EditText rua = (EditText) findViewById(R.id.rua);
				EditText bairro = (EditText) findViewById(R.id.bairro);
				EditText numero = (EditText) findViewById(R.id.numero);
				
				nome.setText(cliente.getNome());
				email.setText(cliente.getEmail());
				rua.setText(cliente.getRua());
				bairro.setText(cliente.getBairro());
				numero.setText(cliente.getNumero());
			}
		}

	}
	public void Salvar(View v){
		
		EditText nome = (EditText) findViewById(R.id.nome);
		EditText email = (EditText) findViewById(R.id.email);
		EditText rua = (EditText) findViewById(R.id.rua);
		EditText bairro = (EditText) findViewById(R.id.bairro);
		EditText numero = (EditText) findViewById(R.id.numero);
		
		String n = nome.getText().toString();
		String e = email.getText().toString();
		String r = rua.getText().toString();
		String b = bairro.getText().toString();
		String nu = numero.getText().toString();
		
		if(cliente == null){
			cliente = new Cliente();
		}
		
		cliente.setNome(n);
		cliente.setEmail(e);
		cliente.setRua(r);
		cliente.setBairro(b);
		cliente.setNumero(nu);
		if(cliente.getId() == 0){
			ClienteDAO.getClienteDao(getApplicationContext()).insert(cliente);
			messagemAviso("Confirmação", "Cadastro efetuado com sucesso");
		}else{
			ClienteDAO.getClienteDao(getApplicationContext()).update(cliente);
			messagemAviso("Atualização", "Cadastro Atualizado com sucesso");
		}
	}
	public void Voltar(View v){
		
		Intent i = new Intent(getApplicationContext(),ClienteEscolha.class);
		startActivity(i);
	}
	public void Limpar(View v){
		nome.setText(null);
		email.setText(null);
		rua.setText(null);
		bairro.setText(null);
		numero.setText(null);	
	}
	public void messagemAviso(String titulo, String msg) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				CadastraCliente.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("Sim", null);
		mensagem.show();
	}

}
