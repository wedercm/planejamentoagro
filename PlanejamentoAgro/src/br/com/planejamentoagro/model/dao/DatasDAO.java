package br.com.planejamentoagro.model.dao;

import java.util.Calendar;

import br.com.planejamentoagro.model.Talhao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatasDAO extends ModeloDAO<Talhao>{
	
	public static final String COLUNA_ID = "_id";
	public static final String COLUNA_ID_TALHAO = "idTalhao";
	public static final String COLUNA_DATA_PLANTIO = "dataPlantio";
	public static final String COLUNA_DATA_1 = "data1";
	public static final String COLUNA_DATA_2 = "data2";
	public static final String COLUNA_DATA_3 = "data3";
	public static final String COLUNA_DATA_4 = "data4";
	public static final String COLUNA_DATA_5 = "data5";
	public static final String COLUNA_DATA_6 = "data6";
	public static final String NOME_TABELA = "Datas";
	
	public static final String SQL_CRIA_TABELA_TALHAO = "CREATE TABLE "+ NOME_TABELA + "("
			+ COLUNA_ID +" INTEGER PRIMARY KEY autoincrement, "
			+ COLUNA_ID_TALHAO +" INTEGER NOT NULL UNIQUE,"
			+ COLUNA_DATA_PLANTIO+" TEXT NOT NULL,"
			+ COLUNA_DATA_1 +" TEX NOT NULL,"
			+ COLUNA_DATA_2 +" TEX NOT NULL,"
			+ COLUNA_DATA_3 +" TEX NOT NULL,"
			+ COLUNA_DATA_4 +" TEX NOT NULL,"
			+ COLUNA_DATA_5 +" TEX NOT NULL,"
			+ COLUNA_DATA_6 +" TEX NOT NULL,"
			+ "FOREIGN KEY ("+COLUNA_ID_TALHAO+") references "+TalhaoDAO.NOME_TABELA+"("+TalhaoDAO.COLUNA_ID+") ON DELETE CASCADE)";
	
	public static final String SQL_DELETA_TABELA_DATA = "DROP TABLE IF EXISTS " + NOME_TABELA;
	protected SQLiteDatabase dataBase = null;

	public DatasDAO(Context context) {
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
	public ContentValues geraContentValeusEntidade(Talhao talhao) {
		ContentValues values = new ContentValues();
//		values.put(COLUNA_NOME, talhao.getNome());
//		values.put(COLUNA_ID_CLIENTE, talhao.getIdCliente());
//		String dataPlantio = talhao.getDataPlantio().get(Calendar.DAY_OF_MONTH)+"/"+talhao.getDataPlantio().get(Calendar.MONTH)+"/"+talhao.getDataPlantio().get(Calendar.YEAR);
//		values.put(COLUNA_DATA_PLANTIO, dataPlantio);
//		values.put(COLUNA_DIA_INICIA_APLICACAO, talhao.getDiasIniciaAplicacao());
//		values.put(COLUNA_ID_ITEM_SELECIONADO, talhao.getIdItemSelecionado());
//		values.put(COLUNA_PRODUTO_APLICADO, talhao.getProdutoAplicado());
		return values;
	}
	@Override
	public Talhao geraEntidadePorContentValues(Cursor cursor) {
		Talhao talhao = new Talhao();
//		talhao.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
//		talhao.setIdCliente(cursor.getInt(cursor.getColumnIndex(COLUNA_ID_CLIENTE)));
//		talhao.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
//		talhao.setProdutoAplicado(cursor.getString(cursor.getColumnIndex(COLUNA_PRODUTO_APLICADO)));
//		Calendar c = string2calendar(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_PLANTIO)));
//		talhao.setDataPlantio(c);
//		talhao.setDiasIniciaAplicacao(cursor.getInt(cursor.getColumnIndex(COLUNA_DIA_INICIA_APLICACAO)));
//		talhao.setIdItemSelecionado(cursor.getInt(cursor.getColumnIndex(COLUNA_ID_ITEM_SELECIONADO)));
		return talhao;
	}
	@Override
	public String getNomeColunaForenKey() {
		return COLUNA_ID_TALHAO;
	}
	public Calendar string2calendar(String a)	
	{
		String dia=null,mes = null,ano=null;
		if (a.length() == 8)
		{
			dia = a.substring(0, 1);
			mes = a.substring(2, 3);
			ano = a.substring(4, 8);
		}else if(a.length() == 10)
		{
			dia = a.substring(0, 2);
			mes = a.substring(3,5);
			ano = a.substring(6,10);
		}else if(a.charAt(1) == '/')
		{
			dia = a.substring(0, 1);
			mes = a.substring(2, 4);
			ano = a.substring(5, 9);
		}else{
			dia = a.substring(0, 2);
			mes = a.substring(3, 4);
			ano = a.substring(5, 9);			
		}
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia),0,0,0);
		return c;
	}
	
}
