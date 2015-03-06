package br.com.planejamentoagro.controller;

import java.util.Calendar;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.model.Informacoes;
import br.com.planejamentoagro.model.dao.InformacoesTecnicasDAO;
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

public class CadastraInformacoes extends Activity {
	private EditText etInformacoes, etDataVisita;
	private int idTalhao;
	private Informacoes info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_informacoes);
		etInformacoes = (EditText) findViewById(R.id.etInformacoes);
		etDataVisita = (EditText) findViewById(R.id.etDataVisita);
		this.idTalhao = getIntent().getIntExtra(TalhaoDAO.COLUNA_ID,-1);

	}
	private void salvar()
	{
		String dataVisita, informacoes;
		informacoes = etInformacoes.getText().toString();
		dataVisita = etDataVisita.getText().toString();
		if(!informacoes.equals("") && !dataVisita.equals(""))
		{
			info = new Informacoes(0, this.idTalhao, dataVisita, informacoes);
			InformacoesTecnicasDAO infoDAO = new InformacoesTecnicasDAO(getApplicationContext());
			infoDAO.salvar(info);
			infoDAO.fecharConexao();
			finish();
			Toast.makeText(CadastraInformacoes.this, "Informações cadastradas.", Toast.LENGTH_SHORT).show();
		}else Toast.makeText(CadastraInformacoes.this, "Todos campos são obrigatórios.", Toast.LENGTH_SHORT).show();
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
				etDataVisita.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);				
			}
			
		}, mYear, mMonth, mDay);
		dpd.setTitle("Selecione a data de visita.");
		dpd.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_informacoes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_save){
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
