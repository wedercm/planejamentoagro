package br.com.planejamentoagro.controller;

import java.util.Calendar;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.helper.FormularioTalhaoHelper;
import br.com.planejamentoagro.model.Talhao;
import br.com.planejamentoagro.model.dao.TalhaoDAO;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CadastraTalhao extends Activity {
	private FormularioTalhaoHelper talhaoHelper;
	private Talhao talhao = new Talhao();
	private EditText dataPlantio;
	private int idCliente; 
	private String nomeCliente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_talhao);
		talhaoHelper = new FormularioTalhaoHelper(this);
		dataPlantio = (EditText) findViewById(R.id.etDataPlantio);
		this.idCliente = getIntent().getIntExtra("ID_CLIENTE",-1);
		this.nomeCliente = getIntent().getStringExtra("NOME_CLIENTE");
	}	

	public void salvar()
	{
		
		talhao = talhaoHelper.getTalhao();
		talhao.setIdCliente(this.idCliente);
		talhao.setNomeCliente(this.nomeCliente);
		if ((talhao.getDataPlantio() != null) && (!talhao.getNome().equals("")))
		{
			if (talhao.getDiasIniciaAplicacao() != -1)			{
				TalhaoDAO talhaoDAO = null;
				try{
					talhaoDAO = new TalhaoDAO(CadastraTalhao.this);				
					talhaoDAO.salvar(talhao);
				}catch(Exception e){
					
				}finally{
					if(talhaoDAO != null)
						talhaoDAO.fecharConexao();
				}				
				this.finish();
				Toast.makeText(CadastraTalhao.this, "Talhão cadastrado!", Toast.LENGTH_SHORT).show();
			}else Toast.makeText(CadastraTalhao.this, "Dias para início da aplicação é obrigatório.", Toast.LENGTH_SHORT).show();
		}else Toast.makeText(CadastraTalhao.this, "Nome e Data plantio são obrigatórios.", Toast.LENGTH_SHORT).show();
	}
	public void onClickCalendario(View v)
	{		
		Calendar calendario = Calendar.getInstance();
		int mYear = calendario.get(Calendar.YEAR);
		int mMonth = calendario.get(Calendar.MONTH);
		int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
		DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
			{
				dataPlantio.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);				
			}
			
		}, mYear, mMonth, mDay);
		dpd.setTitle("Selecione a data de plantio.");
		dpd.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_talhao, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_save) {
			salvar();
			return true;
		}
		if (id == android.R.id.home)
		{
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
