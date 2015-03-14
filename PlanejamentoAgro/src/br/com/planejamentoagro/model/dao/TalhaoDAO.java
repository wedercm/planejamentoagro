package br.com.planejamentoagro.model.dao;

import java.util.Calendar;

import br.com.planejamentoagro.helper.FormatacaoDeDatas;
import br.com.planejamentoagro.model.Talhao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class TalhaoDAO extends ModeloDAO<Talhao>{
	
	public static final String COLUNA_ID = "_id";
	public static final String COLUNA_ID_CLIENTE = "idCLiente";
	public static final String COLUNA_NOME = "nomeTalhao";
	public static final String COLUNA_NOME_CLIENTE = "nomeCliente";
	public static final String COLUNA_PRODUTO_APLICADO = "produtoAplicado";
	public static final String COLUNA_DATA_PLANTIO = "dataPlantio";
	public static final String COLUNA_DIA_INICIA_APLICACAO = "diasIniciaAplicacao";
	public static final String COLUNA_DATA_1 = "data1";
	public static final String COLUNA_DATA_2 = "data2";
	public static final String COLUNA_DATA_3 = "data3";
	public static final String COLUNA_DATA_4 = "data4";
	public static final String COLUNA_DATA_5 = "data5";
	public static final String COLUNA_DATA_6 = "data6";
	public static final String COLUNA_DATA_APLICACAO_1 = "dataAplicacao1";
	public static final String COLUNA_DATA_APLICACAO_2 = "dataAplicacao2";
	public static final String COLUNA_DATA_APLICACAO_3 = "dataAplicacao3";
	public static final String COLUNA_DATA_APLICACAO_4 = "dataAplicacao4";
	public static final String COLUNA_DATA_APLICACAO_5 = "dataAplicacao5";
	public static final String COLUNA_DATA_APLICACAO_6 = "dataAplicacao6";
	public static final String NOME_TABELA = "Talhao";
	
	public static final String SQL_CRIA_TABELA_TALHAO = "CREATE TABLE "+ NOME_TABELA + "("
			+ COLUNA_ID +" INTEGER PRIMARY KEY autoincrement, "
			+ COLUNA_ID_CLIENTE +" INTEGER NOT NULL,"
			+ COLUNA_NOME +" TEXT NOT NULL, "+COLUNA_PRODUTO_APLICADO+" TEXT, "+COLUNA_DATA_PLANTIO+" TEXT NOT NULL,"
			+ COLUNA_NOME_CLIENTE +" TEXT NOT NULL,"			
			+ COLUNA_DIA_INICIA_APLICACAO+" INTEGER,"
			+ COLUNA_DATA_1 +" TEXT NOT NULL,"
			+ COLUNA_DATA_2 +" TEXT NOT NULL,"
			+ COLUNA_DATA_3 +" TEXT NOT NULL,"
			+ COLUNA_DATA_4 +" TEXT NOT NULL,"
			+ COLUNA_DATA_5 +" TEXT NOT NULL,"
			+ COLUNA_DATA_6 +" TEXT NOT NULL,"
			+ COLUNA_DATA_APLICACAO_1 +" TEXT NOT NULL,"
			+ COLUNA_DATA_APLICACAO_2 +" TEXT NOT NULL,"
			+ COLUNA_DATA_APLICACAO_3 +" TEXT NOT NULL,"
			+ COLUNA_DATA_APLICACAO_4 +" TEXT NOT NULL,"
			+ COLUNA_DATA_APLICACAO_5 +" TEXT NOT NULL,"
			+ COLUNA_DATA_APLICACAO_6 +" TEXT NOT NULL,"
			+ "FOREIGN KEY ("+COLUNA_ID_CLIENTE+") references "+ClienteDAO.NOME_TABELA+"("+ClienteDAO.COLUNA_ID+") ON DELETE CASCADE)";
	
	public static final String SQL_DELETA_TABELA_TALHAO = "DROP TABLE IF EXISTS " + NOME_TABELA;

	public TalhaoDAO(Context context) {
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
		values.put(COLUNA_NOME, talhao.getNome());
		values.put(COLUNA_NOME_CLIENTE, talhao.getNomeCliente());
		values.put(COLUNA_ID_CLIENTE, talhao.getIdCliente());
		String dataPlantio = talhao.getDataPlantio().get(Calendar.DAY_OF_MONTH)+"/"+talhao.getDataPlantio().get(Calendar.MONTH)+"/"+talhao.getDataPlantio().get(Calendar.YEAR);
		values.put(COLUNA_DATA_PLANTIO, dataPlantio);
		values.put(COLUNA_DIA_INICIA_APLICACAO, talhao.getDiasIniciaAplicacao());
		values.put(COLUNA_PRODUTO_APLICADO, talhao.getProdutoAplicado());
		values.put(COLUNA_DATA_1, talhao.getData1());
		values.put(COLUNA_DATA_2, talhao.getData2());
		values.put(COLUNA_DATA_3, talhao.getData3());
		values.put(COLUNA_DATA_4, talhao.getData4());
		values.put(COLUNA_DATA_5, talhao.getData5());
		values.put(COLUNA_DATA_6, talhao.getData6());
		values.put(COLUNA_DATA_APLICACAO_1, talhao.getDataAplicacao1());
		values.put(COLUNA_DATA_APLICACAO_2, talhao.getDataAplicacao2());
		values.put(COLUNA_DATA_APLICACAO_3, talhao.getDataAplicacao3());
		values.put(COLUNA_DATA_APLICACAO_4, talhao.getDataAplicacao4());
		values.put(COLUNA_DATA_APLICACAO_5, talhao.getDataAplicacao5());
		values.put(COLUNA_DATA_APLICACAO_6, talhao.getDataAplicacao6());
		return values;
	}
	@Override
	public Talhao geraEntidadePorContentValues(Cursor cursor) {
		Talhao talhao = new Talhao();
		talhao.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
		talhao.setIdCliente(cursor.getInt(cursor.getColumnIndex(COLUNA_ID_CLIENTE)));
		talhao.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
		talhao.setNomeCliente(cursor.getString(cursor.getColumnIndex(COLUNA_NOME_CLIENTE)));
		talhao.setProdutoAplicado(cursor.getString(cursor.getColumnIndex(COLUNA_PRODUTO_APLICADO)));
		Calendar c = FormatacaoDeDatas.string2calendar(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_PLANTIO)));
		talhao.setDataPlantio(c);
		talhao.setDiasIniciaAplicacao(cursor.getInt(cursor.getColumnIndex(COLUNA_DIA_INICIA_APLICACAO)));
		talhao.setData1(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_1)));
		talhao.setData2(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_2)));
		talhao.setData3(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_3)));
		talhao.setData4(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_4)));
		talhao.setData5(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_5)));
		talhao.setData6(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_6)));
		talhao.setDataAplicacao1(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_APLICACAO_1)));
		talhao.setDataAplicacao2(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_APLICACAO_2)));
		talhao.setDataAplicacao3(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_APLICACAO_3)));
		talhao.setDataAplicacao4(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_APLICACAO_4)));
		talhao.setDataAplicacao5(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_APLICACAO_5)));
		talhao.setDataAplicacao6(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_APLICACAO_6)));

		return talhao;
	}
	@Override
	public String getNomeColunaForenKey() {
		return COLUNA_ID_CLIENTE;
	}
	
}
