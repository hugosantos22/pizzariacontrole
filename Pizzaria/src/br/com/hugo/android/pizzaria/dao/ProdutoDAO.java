package br.com.hugo.android.pizzaria.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.hugo.android.pizzaria.entity.Produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProdutoDAO extends Dao<Produto> {
	
	public static String TABELA = "produtos";
	public static Context coxt;
	public static ProdutoDAO produto;

	protected ProdutoDAO(Context context) {
		super(context);
		coxt = context;
		
	}
	public static final class Column{
		public static String ID = "_id";
		public static String NOME = "nome";
		public static String DESCRICAO = "descricao";
		public static String PRECO = "preco"; 
	}
	
	public static ProdutoDAO getProdutoDao(Context ctx){
		coxt = ctx;
		if(produto == null){
			produto = new ProdutoDAO(coxt);
		}
		return produto;
		
	}

	@Override
	public List<Produto> selectAll() {
		
		SQLiteDatabase db = getDB();
		
		Cursor c = null;
		
		try {
			String columns[] = new String[]{Column.ID,Column.NOME,Column.DESCRICAO,Column.PRECO};
			c = db.query(TABELA, columns, null, null, null, null, null);
			
			List<Produto> alltodos = new ArrayList<Produto>();
			try {
				if(c.moveToFirst()){
					do {
						Produto produto = new Produto();
						
						produto.setId(c.getColumnIndex(Column.ID));
						produto.setNome(c.getString(c.getColumnIndex(Column.NOME)));
						produto.setDescricao(c.getString(c.getColumnIndex(Column.DESCRICAO)));
						produto.setPreco(c.getFloat(c.getColumnIndex(Column.PRECO)));
						
						alltodos.add(produto);
						
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
			
		} catch (Exception e) {
		}
		
		return null;
	}

	@Override
	public Produto select(int i) {
		
		SQLiteDatabase db = getDB();
		
		Cursor c = null;
		
		try {
			String columns[] = new String[]{Column.ID,Column.NOME,Column.DESCRICAO,Column.PRECO};
			c = db.query(TABELA, columns, Column.ID + "=",new String[]{String.valueOf(i)}, null, null, null, null);
			
			Produto produto = new Produto();
			if(c.moveToFirst()){
				
				produto.setId(c.getColumnIndex(Column.ID));
				produto.setNome(c.getString(c.getColumnIndex(Column.NOME)));
				produto.setDescricao(c.getString(c.getColumnIndex(Column.DESCRICAO)));
				produto.setPreco(c.getFloat(c.getColumnIndex(Column.PRECO)));
				
				return produto;
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
	public void insert(Produto o) {
		
		SQLiteDatabase db = getDB();
		
		try {
			ContentValues values = new ContentValues();
			
			values.put(Column.NOME, o.getNome());
			values.put(Column.DESCRICAO, o.getDescricao());
			values.put(Column.PRECO, o.getPreco());
			
			db.insert(TABELA, null, values);
			
		} catch (Exception e) {
			Log.e("ProdutoDAO",
					TABELA + ": falha ao inserir registros ", e);
			} finally {
				db.close();
			}
		
	}

	@Override
	public void delete(int i) {
		SQLiteDatabase db = getDB();
		
		try {
			db.delete(TABELA, "_id = ?",new String[]{String.valueOf(i)});
			
		} catch (Exception e) {
			Log.e("ProdutoDAO", TABELA + ": falha ao excluir registro " + i, e);
		} finally {
			db.close();
		}
		
	}

	@Override
	public void update(Produto o) {
		
		SQLiteDatabase db = getDB();
		
		try {
			ContentValues values = new ContentValues();
			
			values.put(Column.NOME, o.getNome());
			values.put(Column.DESCRICAO, o.getDescricao());
			values.put(Column.PRECO, o.getPreco());
			
			db.update(TABELA, values, "_id = ?", new String[]{String.valueOf(o.getId())});
			
		} catch (Exception e) {
			Log.e("ProdutoDAO", TABELA + ": falha ao atualizar registro "
					+ o.getId(), e);
		} finally {
			db.close();
		}
		
	}

}
