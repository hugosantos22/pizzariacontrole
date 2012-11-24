package br.com.hugo.android.pizzaria.dao;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.hugo.android.pizzaria.entity.Itens_pedido;

public class Itens_pedidoDAO extends Dao<Itens_pedido> {
	
	public static String TABELA = "itens_pedido";
	public static Itens_pedidoDAO item;
	public static Context ctx;

	protected Itens_pedidoDAO(Context context) {
		super(context);
		ctx = context;
	}
	public static final class Column{
		public static String ID = "_id";
		public static String PEDIDO = "pedido";
		public static String PRODUTO = "produto";
		public static String QUANTIDADE = "quantidade";
	}
	public static Itens_pedidoDAO getItens_pedidoDAO(Context context){
		ctx = context;
		if(item == null ){
			item = new Itens_pedidoDAO(ctx);
		}
		return item;
	}

	@Override
	public List<Itens_pedido> selectAll() {
		
		return null;
	}

	@Override
	public Itens_pedido select(int i) {
		
		SQLiteDatabase db = getDB();
		Cursor c = null;
		try {
			String columns[] = new String[]{Column.ID,Column.PEDIDO,Column.PRODUTO,Column.QUANTIDADE};
			c = db.query(TABELA, columns, Column.PEDIDO + " = ?", new String[]{String.valueOf(i)}, null, null, null, null);
			Itens_pedido item =  new Itens_pedido();
			if(c.moveToFirst()){
				item.setId(c.getInt(c.getColumnIndex(Column.ID)));
				item.setPedido(c.getInt(c.getColumnIndex(Column.PEDIDO)));
				item.setProduto(c.getInt(c.getColumnIndex(Column.PRODUTO)));
				item.setQuantidade(c.getInt(c.getColumnIndex(Column.QUANTIDADE)));
				
				return item;
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
	public void insert(Itens_pedido o) {
		
		SQLiteDatabase db = getDB();
		
		try {
			ContentValues values = new ContentValues();
			
			values.put(Column.PEDIDO, o.getPedido());
			values.put(Column.PRODUTO, o.getProduto());
			values.put(Column.QUANTIDADE, o.getQuantidade());
			
			db.insert(TABELA, null, values);
			
		} catch (Exception e) {
			Log.e("Itens_pedidoDAO",
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
			Log.e("Itens_pedidoDAO", TABELA + ": falha ao excluir registro " + i, e);
		} finally {
			db.close();
		}
		
	}

	@Override
	public void update(Itens_pedido o) {
		SQLiteDatabase db = getDB();
		
		try {
			ContentValues values = new ContentValues();
			
			values.put(Column.PEDIDO, o.getPedido());
			values.put(Column.PRODUTO, o.getProduto());
			values.put(Column.QUANTIDADE, o.getQuantidade());
			
			db.update(TABELA, values, "_id = ?", new String[]{String.valueOf(o.getId())});
			
		} catch (Exception e) {
			Log.e("Itens_pedidoDAO", TABELA + ": falha ao atualizar registro "
					+ o.getId(), e);
		} finally {
			db.close();
		}
		
	}

}
