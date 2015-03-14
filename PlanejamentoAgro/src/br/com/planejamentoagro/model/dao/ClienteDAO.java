package br.com.planejamentoagro.model.dao;

import br.com.planejamentoagro.model.Cliente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ClienteDAO extends ModeloDAO<Cliente>{

	
	public static final String NOME_TABELA = "Cliente";
	
	public static final String COLUNA_ID = "_id";
	public static final String COLUNA_NOME = "nomeCliente";
	public static final String COLUNA_MUNICIPIO = "municipio";
	public static final String COLUNA_NOME_FAZENDA = "nomeFazenda";
	public static final String SQL_CRIA_TABELA_CLIENTE = new StringBuilder()
					.append("CREATE TABLE ").append(NOME_TABELA).append("( ")
					.append(COLUNA_ID).append(" INTEGER PRIMARY KEY autoincrement,")
					.append(COLUNA_NOME).append(" TEXT UNIQUE,").append(COLUNA_MUNICIPIO).append(" TEXT,")
					.append(COLUNA_NOME_FAZENDA).append(" TEXT)").toString();
			
//	public static final String SQL_CRIA_TABELA_CLIENTE = "CREATE TABLE "+ NOME_TABELA + "( "
//			+COLUNA_ID+" INTEGER PRIMARY KEY autoincrement, "
//			+COLUNA_NOME+" TEXT UNIQUE,"+COLUNA_MUNICIPIO+" TEXT, "+COLUNA_NOME_FAZENDA+" TEXT)";
	
	public static final String SQL_DELETA_TABELA_CLIENTE = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	public ClienteDAO(Context context) {
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
	public ContentValues geraContentValeusEntidade(Cliente cliente) {
		ContentValues values = new ContentValues();
		values.put(COLUNA_NOME, cliente.getNome().toString());
		values.put(COLUNA_NOME_FAZENDA, cliente.getNomeFazenda().toString());
		values.put(COLUNA_MUNICIPIO, cliente.getMunicipio().toString());
		return values;
	}

	@Override
	public Cliente geraEntidadePorContentValues(Cursor cursor) {
		Cliente cliente = new Cliente();
		cliente.setId(cursor.getInt(0));
		cliente.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
		cliente.setnomeFazenda(cursor.getString(cursor.getColumnIndex(COLUNA_NOME_FAZENDA)));
		cliente.setmunicipio(cursor.getString(cursor.getColumnIndex(COLUNA_MUNICIPIO)));
		return cliente;
	}
	

	@Override
	public String getNomeColunaForenKey() {
		return null;
	}

	
}
