package br.com.planejamentoagro.model.dao;

import java.util.ArrayList;
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
		String[] args = {String.valueOf(entidade.getId())};
		dataBase.delete(getNomeTabela(), getNomeColunaPrimaryKey()+ " = ?", args);		
	}
	public void deletarTodos()
	{
		dataBase.execSQL("DELETE FROM"+ getNomeTabela());
	}
	public void editar(T entidade)
	{
		ContentValues values = geraContentValeusEntidade(entidade);
		String[] args = {String.valueOf(entidade.getId())};
		dataBase.update(getNomeTabela(), values, getNomeColunaPrimaryKey()+" = ?", args);
	}
	public List<T> listarTodos(String ordenar)
	{
		String sql;
		if (ordenar != null)
			sql = "SELECT * FROM "+getNomeTabela()+" ORDER BY "+ordenar;
		else sql = "SELECT * FROM "+getNomeTabela();
		return recuperaPorSQL(sql);
	}
	public List<T> listarPorDatas(String data)
	{
		String sql =  "SELECT * FROM "+getNomeTabela()+" WHERE "+TalhaoDAO.COLUNA_DATA_APLICACAO_1+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_2+ "= '"+data+"'"		
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_3+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_4+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_5+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_6+ "= '"+data+"' ORDER BY "+TalhaoDAO.COLUNA_NOME;
		return recuperaPorSQL(sql);
	}
	public List<T> listarPorID(int id, String ordenar)
	{
		String sql;
		if (ordenar != null)
		sql = "SELECT * FROM "+getNomeTabela()+" where "+getNomeColunaPrimaryKey()+" = "+id+ " ORDER BY "+ordenar;
		else sql = "SELECT * FROM "+getNomeTabela()+" where "+getNomeColunaPrimaryKey()+" = "+id;

		return recuperaPorSQL(sql);
		
	}
	public List<T> listaPorFK(int fk,String ordenar)
	{
		String sql;
		if (ordenar != null)
			sql = "SELECT * FROM "+getNomeTabela()+" where "+getNomeColunaForenKey()+" = "+fk+" ORDER BY "+ordenar;
		else sql = "SELECT * FROM "+getNomeTabela()+" where "+getNomeColunaForenKey()+" = "+fk;

		return recuperaPorSQL(sql);
	}
	public boolean verificarData(String data)
	{
		String sql =  "SELECT * FROM "+getNomeTabela()+" WHERE "+TalhaoDAO.COLUNA_DATA_APLICACAO_1+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_2+ "= '"+data+"'"		
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_3+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_4+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_5+ "= '"+data+"'"
				+ " OR "+TalhaoDAO.COLUNA_DATA_APLICACAO_6+ "= '"+data+"'";
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
}
