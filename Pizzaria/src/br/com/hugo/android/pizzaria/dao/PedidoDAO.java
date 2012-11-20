package br.com.hugo.android.pizzaria.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.hugo.android.pizzaria.entity.Pedido;

public class PedidoDAO extends Dao<Pedido> {
	
	public static String TABELA = "pedidos";
	public static PedidoDAO pedido;
	public static Context cotx;

	protected PedidoDAO(Context context) {
		super(context);
		cotx = context;
	}
	public static final class Column{
		public static String ID = "_id";
		public static String DATA = "data";
		public static String MESA = "mesa";
		public static String CLIENTE = "cliente"; 
	}
	
	public static PedidoDAO getPedidoDao(Context context){
		cotx = context;
		if(pedido == null){
			pedido = new PedidoDAO(cotx);
		}
		return pedido;
		
	}

	@Override
	public List<Pedido> selectAll() {
		
		return null;
	}

	@Override
	public Pedido select(int i) {
		
		return null;
	}
	
	public int selectUltimoID(){
			
		int id = 0;
		SQLiteDatabase db = getDB();
		Cursor c = null;
		try {
			String sql ="select Max(_id) as _id from " + TABELA;
			
			c = db.rawQuery(sql, null);
			
			if(c.moveToFirst()){
				id = c.getInt(0);
				db.close();
				c.close();
			}
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					"Falha na leitura dos dados.", e);
		}
		
		return id;
	}

	@Override
	public void insert(Pedido o) {
		SQLiteDatabase db = getDB();
		
		try {
			ContentValues values = new ContentValues();
			
			values.put(Column.CLIENTE, o.getCliente());
			values.put(Column.DATA, 
				new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
			values.put(Column.MESA, o.getMesa());
			
			db.insert(TABELA, null, values);
			
		} catch (Exception e) {
			Log.e("PedidoDAO",
					TABELA + ": falha ao inserir registros ", e);
			} finally {
				db.close();
			}
		
	}

	@Override
	public void delete(int i) {
		
		
	}

	@Override
	public void update(Pedido o) {
		
		
	}

}
