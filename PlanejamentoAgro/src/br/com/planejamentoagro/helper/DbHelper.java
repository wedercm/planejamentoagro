package br.com.planejamentoagro.helper;

import br.com.planejamentoagro.model.dao.ClienteDAO;
import br.com.planejamentoagro.model.dao.InformacoesTecnicasDAO;
import br.com.planejamentoagro.model.dao.TalhaoDAO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
	private static final String DATABASE = "planejamentoagro.db";
	private static final int VERSAO = 2;
	private static DbHelper instance;

	public DbHelper(Context context) {
		super(context, DATABASE, null, VERSAO);
	}
	public static DbHelper getInstance(Context context){
		if(instance == null)
		{
			instance = new DbHelper(context);
		}
		return instance;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ClienteDAO.SQL_CRIA_TABELA_CLIENTE);
		db.execSQL(TalhaoDAO.SQL_CRIA_TABELA_TALHAO);
		db.execSQL(InformacoesTecnicasDAO.SQL_CRIA_TABELA_INFORMACOES);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(ClienteDAO.SQL_DELETA_TABELA_CLIENTE);
		db.execSQL(TalhaoDAO.SQL_DELETA_TABELA_TALHAO);
		db.execSQL(InformacoesTecnicasDAO.SQL_DELETA_TABELA_INFORMACOES);
		onCreate(db);
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}
	

}
