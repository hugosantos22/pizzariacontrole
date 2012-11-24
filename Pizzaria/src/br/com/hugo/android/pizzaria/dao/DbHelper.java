package br.com.hugo.android.pizzaria.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Assistente de cria��o do banco de dados inicial do sistema.
 * Opcionalmente criam-se uma popula��o de dados inicial.
 * 
 * @author Luis Guisso
 * @version 1.0 02 de junho de 2012
 */
public class DbHelper extends SQLiteOpenHelper {

	/**
	 * Construtor da classe
	 * 
	 * @param context Contexto da aplica��o
	 * @param name Nome do banco de dados a ser criado
	 * @param factory Interface que permite retornar novos cursores ap�s consultas
	 * @param version Vers�o do banco de dados a ser criado
	 */
	public DbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	 * M�todo invocado pelo Android a fim de executar
	 * a cria��o de um novo banco de dados
	 * 
	 * @param db Nome do banco de dados fornecido pelo Android 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			
			String tblcliente = "CREATE TABLE "+ ClienteDAO.TABELA 
					+ "(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+"nome TEXT NOT NULL,"
					+"email TEXT NOT NULL,"
					+"rua TEXT NOT NULL,"
					+"bairro TEXT NOT NULL,"
					+"numero TEXT NOT NULL);";
			
			String tblproduto = "CREATE TABLE "+ ProdutoDAO.TABELA 
					+ "(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+"nome TEXT NOT NULL,"
					+"descricao TEXT NOT NULL,"
					+"preco FLOAT NOT NULL);";
			
			String tblpedido = "CREATE TABLE "+ PedidoDAO.TABELA 
					+ "(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+"cliente INTEGER NOT NULL,"
					+"data TEXT NOT NULL,"
					+"mesa INTEGER NOT NULL);";
			
			String tblitens_pedido = "CREATE TABLE "+ Itens_pedidoDAO.TABELA 
					+ "(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+"pedido INTEGER NOT NULL,"
					+"produto INTEGER NOT NULL,"
					+"quantidade INTEGER NOT NULL);";

			
			db.execSQL(tblcliente);
			db.execSQL(tblproduto);
			db.execSQL(tblpedido);
			db.execSQL(tblitens_pedido);

			
		} catch (Exception e) {
			Log.e("DbHelper", "Erro na cria��o da tabela", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

	/**
	 * M�todo invocado pelo Android a fim de executar
	 * a atualiza��o de vers�es do banco de dados
	 * 
	 * @param db Banco de dados a ser atualizado
	 * @param oldVersion N�mero da vers�o atual do banco de dados
	 * @param newVersion N�mero da vers�o para a qual o banco de dados
	 * ser� atualizado
	 */

}
