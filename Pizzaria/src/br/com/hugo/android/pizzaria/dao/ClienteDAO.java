package br.com.hugo.android.pizzaria.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.hugo.android.pizzaria.entity.Cliente;

public class ClienteDAO extends Dao<Cliente> {
	
	public static String TABELA = "clientes";
	
	public static Context cotx;
	
	public static ClienteDAO cliente;
	
	public static final class Column{
		public static String ID = "_id";
		public static String NOME = "nome";
		public static String EMAIL = "email";
		public static String RUA = "rua";
		public static String BAIRRO = "bairro";
		public static String NUMERO = "numero";
	}

	protected ClienteDAO(Context context) {
		super(context);
		cotx = context;
	}
	public static ClienteDAO getClienteDao(Context ctx){
		cotx = ctx;
		if(cliente == null){
			cliente = new ClienteDAO(cotx);
		}
		return cliente;
		
	}

	@Override
	public List<Cliente> selectAll() {
		
		SQLiteDatabase db = getDB();
		
		Cursor c = null;
		
		try {
			String columns[] = new String[] {Column.ID,Column.NOME,Column.EMAIL,Column.RUA,Column.BAIRRO,Column.NUMERO};
			c = db.query(TABELA, columns, null, null, null, null, Column.NOME);
			
			List<Cliente> alltodos = new ArrayList<Cliente>();
			if(c.moveToFirst()){
				do {
					Cliente cliente = new Cliente();
					cliente.setId(c.getColumnIndex(Column.ID));
					cliente.setNome(c.getString(c.getColumnIndex(Column.NOME)));
					cliente.setEmail(c.getString(c.getColumnIndex(Column.EMAIL)));
					cliente.setRua(c.getString(c.getColumnIndex(Column.RUA)));
					cliente.setBairro(c.getString(c.getColumnIndex(Column.BAIRRO)));
					cliente.setNumero(c.getString(c.getColumnIndex(Column.NUMERO)));
					
					alltodos.add(cliente);
									
				} while (c.moveToNext());
			}
			return alltodos;
			
		} catch (Exception e) {	
			Log.e(this.getClass().getName(),
					"Falha na leitura dos dados.", e);
		}finally{
			if(c != null){
				c.close();
			}
			db.close();
		}
		return null;
	}

	@Override
	public Cliente select(int i) {
		
		SQLiteDatabase db = getDB();
		
		Cursor c = null;
		
		try {
			String columns[] = new String[] {Column.ID,Column.NOME,Column.EMAIL,Column.RUA,Column.BAIRRO,Column.NUMERO};
			c = db.query(TABELA, columns, Column.ID + "=",new String[]{String.valueOf(i)}, null, null, null, null);
			
			Cliente cliente = new Cliente();
			
			if(c.moveToFirst()){
				
				cliente.setId(c.getColumnIndex(Column.ID));
				cliente.setNome(c.getString(c.getColumnIndex(Column.NOME)));
				cliente.setEmail(c.getString(c.getColumnIndex(Column.EMAIL)));
				cliente.setRua(c.getString(c.getColumnIndex(Column.RUA)));
				cliente.setBairro(c.getString(c.getColumnIndex(Column.BAIRRO)));
				cliente.setNumero(c.getString(c.getColumnIndex(Column.NUMERO)));
				
				return cliente;
			}
			
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					"Falha na leitura dos dados.", e);
		}finally{
			if(c != null){
				c.close();
				
			}
			db.close();
		}
		
		return null;
	}

	@Override
	public void insert(Cliente o) {
		
		SQLiteDatabase db = getDB();
		
		try {
			ContentValues values = new ContentValues();
			
			values.put(Column.NOME, o.getNome());
			values.put(Column.EMAIL, o.getEmail());
			values.put(Column.RUA, o.getRua());
			values.put(Column.BAIRRO, o.getBairro());
			values.put(Column.NUMERO, o.getNumero());
			
			db.insert(TABELA, null, values);
			
		} catch (Exception e) {
				Log.e("ClienteDAO",
					TABELA + ": falha ao inserir registros ", e);
			} finally {
				db.close();
			}
		
	}

	@Override
	public void delete(int i) {
		
		SQLiteDatabase db = getDB();
		
		try {
			db.delete(TABELA, "_id = ?", new String[]{String.valueOf(i)});
		} catch (Exception e) {
			Log.e("ClienteDAO", TABELA + ": falha ao excluir registro " + i, e);
		} finally {
			db.close();
		}
	}

	@Override
	public void update(Cliente o) {
		
		SQLiteDatabase db = getDB();
		
		try {
			ContentValues values = new ContentValues();
			
			values.put(Column.NOME, o.getNome());
			values.put(Column.EMAIL, o.getEmail());
			values.put(Column.RUA, o.getRua());
			values.put(Column.BAIRRO, o.getBairro());
			values.put(Column.NUMERO, o.getNumero());
			
			db.update(TABELA, values, "_id = ?", new String[]{String.valueOf(o.getId())});
			
		} catch (Exception e) {
			Log.e("ClienteDAO", TABELA + ": falha ao atualizar registro "
					+ o.getId(), e);
		} finally {
			db.close();
		}
		
	}

}
