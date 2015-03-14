package br.com.planejamentoagro.model.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.planejamentoagro.helper.DbHelper;
import br.com.planejamentoagro.inteface.EntidadePersistivel;

public abstract class ModeloDAO <T extends EntidadePersistivel> {
	
	protected SQLiteDatabase dataBase = null;
	public ModeloDAO(Context context)
	{
		DbHelper dbHelper = DbHelper.getInstance(context);
		dataBase = dbHelper.getWritableDatabase();
	}
	public ModeloDAO(Context context,SQLiteDatabase dataBase)
	{
		DbHelper dbHelper = DbHelper.getInstance(context);
		dataBase = dbHelper.getWritableDatabase();
	}
	public abstract String getNomeColunaPrimaryKey();
	public abstract String getNomeColunaForenKey();
	public abstract String getNomeTabela();
	
	public abstract ContentValues geraContentValeusEntidade(T entidade);
	public abstract T geraEntidadePorContentValues(Cursor cursor);
	
	public void salvar(T entidade)
	{
		ContentValues values = geraContentValeusEntidade(entidade);
		dataBase.insert(getNomeTabela(), null, values);
	}
	public void deletar(T entidade)
	{
		String[] whereArgs = {String.valueOf(entidade.getId())};
		String whereClause = new StringBuilder().append(getNomeColunaPrimaryKey()).append(" = ?").toString();
		dataBase.delete(getNomeTabela(), whereClause, whereArgs);		
	}
	public void deletarTodos()
	{
		String sql = new StringBuilder().append("DELETE FROM ").append(getNomeTabela()).toString();
		dataBase.execSQL(sql);
	}
	public void editar(T entidade)
	{
		ContentValues values = geraContentValeusEntidade(entidade);
		String[] whereArgs = {String.valueOf(entidade.getId())};
		String whereClause = new StringBuilder().append(getNomeColunaPrimaryKey()).append(" = ?").toString();
		dataBase.update(getNomeTabela(), values, whereClause , whereArgs);
	}
	public List<T> listarTodos(String ordenar)
	{
		String sql;
		StringBuilder strBuilder = new StringBuilder().append("SELECT * FROM ").append(getNomeTabela());
		if (ordenar != null)
			strBuilder.append(" ORDER BY ").append(ordenar).toString();
		sql = strBuilder.toString();
		return recuperaPorSQL(sql);
	}
	public List<T> listarTalhoesComAplicacao(int dias)
	{		
		return recuperaPorSQL(getSqlListarTalhoesComAplicacao(dias));
	}
	public List<T> listarPorID(int id, String ordenar)
	{
		String sql;
		StringBuilder strBuilder = new StringBuilder().append("SELECT * FROM ").append(getNomeTabela())
				.append(" WHERE ").append(getNomeColunaPrimaryKey()).append(" = ").append(id);
		
		if (ordenar != null)
			strBuilder = strBuilder.append(" ORDER BY ").append(ordenar);
		
		sql = strBuilder.toString();
		return recuperaPorSQL(sql);
		
	}
	public List<T> listaPorFK(int fk,String ordenar)
	{
		String sql;
		StringBuilder strBuilder = new StringBuilder().append("SELECT * FROM ").append(getNomeTabela())
				.append(" WHERE ").append(getNomeColunaForenKey()).append(" = ").append(fk);
		if (ordenar != null)
			strBuilder = strBuilder.append(" ORDER BY ").append(ordenar);

		sql = strBuilder.toString();
		return recuperaPorSQL(sql);
	}
	public boolean verificarData(String data)
	{
		String sql = new StringBuilder().append("SELECT * FROM ").append(getNomeTabela()).append(" WHERE ")
				.append(TalhaoDAO.COLUNA_DATA_APLICACAO_1).append("= '").append(data).append("'")
				.append(" OR ").append(TalhaoDAO.COLUNA_DATA_APLICACAO_2).append("= '").append(data).append("'")
				.append(" OR ").append(TalhaoDAO.COLUNA_DATA_APLICACAO_3).append("= '").append(data).append("'")
				.append(" OR ").append(TalhaoDAO.COLUNA_DATA_APLICACAO_4).append("= '").append(data).append("'")
				.append(" OR ").append(TalhaoDAO.COLUNA_DATA_APLICACAO_5).append("= '").append(data).append("'")
				.append(" OR ").append(TalhaoDAO.COLUNA_DATA_APLICACAO_6).append("= '").append(data).append("'")
				.toString();
		
//		String sql =  "SELECT * FROM "+getNomeTabela()+" WHERE "+TalhaoDAO.COLUNA_DATA_APLICACAO_1+ "= '"+data+"'"
//				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_2+ "= '"+data+"'"		
//				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_3+ "= '"+data+"'"
//				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_4+ "= '"+data+"'"
//				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_5+ "= '"+data+"'"
//				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_6+ "= '"+data+"'";
		Cursor cursor = dataBase.rawQuery(sql, null);		
		return (cursor.getCount() > 0);		
	}
	
	public List<T> recuperaPorSQL(String sql)
	{
		Cursor cursor = dataBase.rawQuery(sql, null);
		List<T> result = new ArrayList<T>();

		try {			
			while(cursor.moveToNext())
			{
	            T entidade = geraEntidadePorContentValues(cursor);
	            result.add(entidade);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
        return result;
	}
	
	public void fecharConexao()
	{
		if(dataBase != null && dataBase.isOpen())
			dataBase.close();
	}
	private String getSqlListarTalhoesComAplicacao(int day)
	{
		String sql = "Select * from Talhao WHERE ";
		Calendar c = Calendar.getInstance();
		for(int i=0;i<=day;++i)
		{
			
			String data = new StringBuffer().append(c.get(Calendar.DAY_OF_MONTH))
					.append("/")
					.append(c.get(Calendar.MONTH))
					.append("/")
					.append(c.get(Calendar.YEAR)).toString();
			if(i < day)
			{
				sql = new StringBuilder().append(sql).append("dataAplicacao1 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao2 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao3 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao4 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao5 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao6 like ").append("'").append(data).append("'")
						.append(" OR ").toString();
			}else{
				sql = new StringBuilder().append(sql).append("dataAplicacao1 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao2 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao3 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao4 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao5 like ").append("'").append(data).append("'")
						.append(" OR dataAplicacao6 like ").append("'").append(data).append("'").toString();
			}
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		sql = new StringBuilder(sql).append(" ORDER BY ").append(TalhaoDAO.COLUNA_DATA_PLANTIO).toString();
		return sql;
	}
}
