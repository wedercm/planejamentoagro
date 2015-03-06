package br.com.planejamentoagro.controller;

import java.util.Calendar;
import br.com.planejamentoagro.R;
import br.com.planejamentoagro.helper.FormatacaoDeDatas;
import br.com.planejamentoagro.helper.DiretoriosHelper;
import br.com.planejamentoagro.helper.FormularioEditaTalhaoHelper;
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

public class EditaTalhao extends Activity {
	private FormularioEditaTalhaoHelper talhaoHelper;
	private Talhao talhao = new Talhao();
	private EditText tvDataPlantio;
	private int idTalhao, idCliente;
	private String nomeTalhao, nomeCliente;
	private Calendar calendario;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_edita_talhao);
		talhaoHelper = new FormularioEditaTalhaoHelper(this);
		tvDataPlantio = (EditText) findViewById(R.id.etDataPlantio);
		talhao = (Talhao) getIntent().getSerializableExtra("TALHAO_SELECIONADO");
		nomeCliente= getIntent().getStringExtra("NOME_CLIENTE");
		

		talhaoHelper.setTalhao(talhao);
		talhaoHelper.setDatasTalhao(talhao);
		if(talhao != null)
		{
			idTalhao = talhao.getId();
			idCliente = talhao.getIdCliente();
			nomeTalhao = talhao.getNome();
		}

	}
	public void onClickSalvarTalhao(View v)
	{
		
		talhao = talhaoHelper.getTalhao();
		talhao.setIdCliente(idCliente);
		talhao.setId(idTalhao);
		if ((talhao.getDataPlantio() != null) && (!talhao.getNome().equals("")))
		{
			if (talhao.getDiasIniciaAplicacao() != -1)
			{
				TalhaoDAO talhaoDAO = new TalhaoDAO(EditaTalhao.this);
				talhaoDAO.editar(talhao);
				talhaoDAO.fecharConexao();
				DiretoriosHelper.renameFile(InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente+"/"+nomeTalhao,InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente+"/"+talhao.getNome());
				this.finish();
				Toast.makeText(EditaTalhao.this, "Talhão cadastrado: ", Toast.LENGTH_SHORT).show();
			}else Toast.makeText(EditaTalhao.this, "Dias para início da aplicação é obrigatório.", Toast.LENGTH_SHORT).show();
		}else Toast.makeText(EditaTalhao.this, "Nome e Data plantio são obrigatórios.", Toast.LENGTH_SHORT).show();
	}
	public void onClickCalendario(View v)
	{		
		int id = v.getId();
		if(id == R.id.button0)
		{
			calendario = talhao.getDataPlantio();
			int mYear = calendario.get(Calendar.YEAR);
			int mMonth = calendario.get(Calendar.MONTH);
			int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
	
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
				{
					tvDataPlantio.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
					talhao.setDataPlantio(FormatacaoDeDatas.string2calendar(dayOfMonth+"/"+monthOfYear+"/"+year));
					talhaoHelper.setDatasTalhao(talhao);
				}
				
			}, mYear, mMonth, mDay);
			dpd.setTitle("Selecione a data de plantio.");
			dpd.show();
		}
		if(id == R.id.btnAplicacao1)
		{
			calendario = FormatacaoDeDatas.string2calendar(talhao.getDataAplicacao1());

			int mYear = calendario.get(Calendar.YEAR);
			int mMonth = calendario.get(Calendar.MONTH);
			int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() 
			{
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
				{
					
					String data=dayOfMonth+"/"+(monthOfYear)+"/"+year;					
					talhao.setDataAplicacao1(data);					
					talhaoHelper.setDatasTalhao(talhao);
					
				}				
			},mYear, mMonth, mDay);
			dpd.setTitle("Selecione a data de plantio.");
			dpd.show();
		}
		if(id == R.id.btnAplicacao2)
		{
			calendario = FormatacaoDeDatas.string2calendar(talhao.getDataAplicacao2());
			int mYear = calendario.get(Calendar.YEAR);
			int mMonth = calendario.get(Calendar.MONTH);
			int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() 
			{
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
				{
					
					String data=dayOfMonth+"/"+(monthOfYear)+"/"+year;					
					talhao.setDataAplicacao2(data);					
					talhaoHelper.setDatasTalhao(talhao);
					
				}				
			},mYear, mMonth, mDay);
			dpd.setTitle("Selecione a data de plantio.");
			dpd.show();
		}
		if(id == R.id.btnAplicacao3)
		{
			calendario = FormatacaoDeDatas.string2calendar(talhao.getDataAplicacao3());
			int mYear = calendario.get(Calendar.YEAR);
			int mMonth = calendario.get(Calendar.MONTH);
			int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() 
			{
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
				{
					
					String data=dayOfMonth+"/"+(monthOfYear)+"/"+year;					
					talhao.setDataAplicacao3(data);					
					talhaoHelper.setDatasTalhao(talhao);
					
				}				
			},mYear, mMonth, mDay);
			dpd.setTitle("Selecione a data de plantio.");
			dpd.show();
		}
		if(id == R.id.btnAplicacao4)
		{
			calendario = FormatacaoDeDatas.string2calendar(talhao.getDataAplicacao4());
			int mYear = calendario.get(Calendar.YEAR);
			int mMonth = calendario.get(Calendar.MONTH);
			int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() 
			{
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
				{
					
					String data=dayOfMonth+"/"+(monthOfYear)+"/"+year;					
					talhao.setDataAplicacao4(data);					
					talhaoHelper.setDatasTalhao(talhao);
					
				}				
			},mYear, mMonth, mDay);
			dpd.setTitle("Selecione a data de plantio.");
			dpd.show();
		}
		if(id == R.id.btnAplicacao5)
		{
			calendario = FormatacaoDeDatas.string2calendar(talhao.getDataAplicacao5());
			int mYear = calendario.get(Calendar.YEAR);
			int mMonth = calendario.get(Calendar.MONTH);
			int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() 
			{
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
				{
					
					String data=dayOfMonth+"/"+(monthOfYear)+"/"+year;					
					talhao.setDataAplicacao5(data);					
					talhaoHelper.setDatasTalhao(talhao);
					
				}				
			},mYear, mMonth, mDay);
			dpd.setTitle("Selecione a data de plantio.");
			dpd.show();
		}
		if(id == R.id.btnAplicacao6)
		{
			calendario = FormatacaoDeDatas.string2calendar(talhao.getDataAplicacao6());
			int mYear = calendario.get(Calendar.YEAR);
			int mMonth = calendario.get(Calendar.MONTH);
			int mDay = calendario.get(Calendar.DAY_OF_MONTH);		
			DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() 
			{
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
				{
					
					String data=dayOfMonth+"/"+(monthOfYear)+"/"+year;					
					talhao.setDataAplicacao6(data);					
					talhaoHelper.setDatasTalhao(talhao);
					
				}				
			},mYear, mMonth, mDay);
			dpd.setTitle("Selecione a data de plantio.");
			dpd.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edita_talhao, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_save)
		{
			salvar();
			return true;			
		}
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private void salvar()
	{
		talhao = talhaoHelper.getTalhao();
		if ((talhao.getDataPlantio() != null) && (!talhao.getNome().equals("")))
		{
			if (talhao.getDiasIniciaAplicacao() != -1)
			{
				TalhaoDAO talhaoDAO = new TalhaoDAO(EditaTalhao.this);
				talhaoDAO.editar(talhao);
				talhaoDAO.fecharConexao();
				DiretoriosHelper.renameFile(InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente+"/"+nomeTalhao,InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente+"/"+talhao.getNome());
				this.finish();
				Toast.makeText(EditaTalhao.this, "Talhão cadastrado: ", Toast.LENGTH_SHORT).show();
			}else Toast.makeText(EditaTalhao.this, "Dias para início da aplicação é obrigatório.", Toast.LENGTH_SHORT).show();
		}else Toast.makeText(EditaTalhao.this, "Nome e Data plantio são obrigatórios.", Toast.LENGTH_SHORT).show();
	}
}
