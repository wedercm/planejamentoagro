package br.com.planejamentoagro.model.dao;

import br.com.planejamentoagro.model.Informacoes;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class InformacoesTecnicasDAO extends ModeloDAO<Informacoes>{

	
	public static final String NOME_TABELA = "InformacoesTecnicas";	
	public static final String COLUNA_ID = "_id";
	public static final String COLUNA_ID_TALHAO = "idTalhao";
	public static final String COLUNA_INFORMACOES = "informacoes";
	public static final String COLUNA_DATA_VISITA = "dataVisita";
	public static final String SQL_CRIA_TABELA_INFORMACOES = new StringBuilder()
			.append("CREATE TABLE ").append(NOME_TABELA).append("( ")
			.append(COLUNA_ID).append(" INTEGER PRIMARY KEY autoincrement, ")
			.append(COLUNA_ID_TALHAO).append(" TEXT NOT NULL,")
			.append(COLUNA_INFORMACOES).append(" TEXT NOT NULL,")
			.append(COLUNA_DATA_VISITA).append(" TEXT NOT  NULL,")
			.append("FOREIGN KEY (").append(COLUNA_ID_TALHAO).append(") references ")
			.append(TalhaoDAO.NOME_TABELA).append("(").append(TalhaoDAO.COLUNA_ID).append(") ON DELETE CASCADE)")
			.toString();	
	
//	public static final String SQL_CRIA_TABELA_INFORMACOES = "CREATE TABLE "+ NOME_TABELA + "( "
//			+COLUNA_ID+" INTEGER PRIMARY KEY autoincrement, "
//			+COLUNA_ID_TALHAO+" INTEGER NOT NULL, "
//			+COLUNA_INFORMACOES+" TEXT NOT NULL,"
//			+COLUNA_DATA_VISITA+" TEXT NOT  NULL,"
//			+ "FOREIGN KEY ("+COLUNA_ID_TALHAO+") references "+TalhaoDAO.NOME_TABELA+"("+TalhaoDAO.COLUNA_ID+") ON DELETE CASCADE)";

	
	public static final String SQL_DELETA_TABELA_INFORMACOES = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	public InformacoesTecnicasDAO(Context context) {
		super(context);
	}
	@Override
	public String getNomeColunaPrimaryKey() {
		return COLUNA_ID;
	}

	@Override
	public String getNomeTabela() {
		return NOME_TABELA;
	}
	@Override
	public String getNomeColunaForenKey() {
		return COLUNA_ID_TALHAO;
	}

	@Override
	public ContentValues geraContentValeusEntidade(Informacoes info) {
		ContentValues values = new ContentValues();
		values.put(COLUNA_INFORMACOES, info.getInformacoes());
		values.put(COLUNA_ID_TALHAO, info.getIdTalhao());
		values.put(COLUNA_DATA_VISITA, info.getDataVisita().toString());
		return values;
	}

	@Override
	public Informacoes geraEntidadePorContentValues(Cursor cursor) {
		Informacoes info = new Informacoes(cursor.getInt(0), cursor.getInt(cursor.getColumnIndex(COLUNA_ID_TALHAO)), 
				cursor.getString(cursor.getColumnIndex(COLUNA_DATA_VISITA)), cursor.getString(cursor.getColumnIndex(COLUNA_INFORMACOES)));
		return info;
	}	
}
