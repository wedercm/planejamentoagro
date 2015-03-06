package br.com.planejamentoagro.helper;

import java.util.Calendar;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.model.Talhao;
import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class FormularioTalhaoHelper {
	private EditText etNomeTalhao, etDataPlantio, etProdutoAplicado, etDias;
	private RadioGroup rgAplicacao;
	private RadioButton rbAplicacao22;
	public FormularioTalhaoHelper(Activity activity)
	{
		this.etNomeTalhao = (EditText) activity.findViewById(R.id.etNomeTalhao);
		this.etDataPlantio = (EditText) activity.findViewById(R.id.etDataPlantio);
		this.etProdutoAplicado = (EditText) activity.findViewById(R.id.etProdutoAplicado);
		this.etDias = (EditText) activity.findViewById(R.id.etDias);
		this.rgAplicacao = (RadioGroup) activity.findViewById(R.id.rgAplicacao);
		this.rbAplicacao22 = (RadioButton) activity.findViewById(R.id.rbAplicacao22);
		rbAplicacao22.setChecked(true);
		etDias.setEnabled(false);
		this.etDataPlantio.setEnabled(false);
		rgAplicacao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() 
	    {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	        	if (checkedId == R.id.rbAplicacao22)
	        	{
	        		etDias.setEnabled(false);
	        	}else etDias.setEnabled(true); 
	        }
	    });
	}
	public Talhao getTalhao()
	{
		Talhao talhao = new Talhao();
		int idItemSelecionado = rgAplicacao.getCheckedRadioButtonId();
		String nomeTalhao = etNomeTalhao.getText().toString();
		String produtoAplicado = etProdutoAplicado.getText().toString();
		String dataPlantio = etDataPlantio.getText().toString();
		int diasIniciaAplicacao=0;

		String dias = etDias.getText().toString();
		Calendar calendario;
		if(dataPlantio.equals("")){
			calendario = null;	
		}else{
			calendario = string2calendar(dataPlantio);			
			
		}
		if (!dias.equals("") && idItemSelecionado == R.id.rbAplicaoEm)
		{
			diasIniciaAplicacao = Integer.parseInt(dias);		
		}else diasIniciaAplicacao = -1;
		
		if (idItemSelecionado == R.id.rbAplicacao22 && calendario != null)
		{			
			talhao = new Talhao(nomeTalhao, calendario, produtoAplicado);
		}else if(calendario != null){
			talhao = new Talhao(nomeTalhao, calendario, produtoAplicado,diasIniciaAplicacao);
		}
		
		return talhao;		
	}
	public void setTalhao(Talhao talhao)
	{
		etNomeTalhao.setText(talhao.getNome());
		etProdutoAplicado.setText(talhao.getProdutoAplicado());
		Calendar calendario = talhao.getDataPlantio();
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		int mes = calendario.get(Calendar.MONTH);
		int ano = calendario.get(Calendar.YEAR);	
		etDataPlantio.setText(dia+"/"+(mes+1)+"/"+ano);
	
	}
	public int string2month(String a)	
	{
		String mes = null;
		if (a.length() == 8)
		{
			mes = a.substring(2, 3);
		}else if(a.length() == 10)
		{
			mes = a.substring(3,5);
		}else if(a.charAt(1) == '/')
		{
			mes = a.substring(2, 4);
		}else{
			mes = a.substring(3, 4);			
		}
		
		return Integer.parseInt(mes);
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
		c.set(Integer.parseInt(ano),Integer.parseInt(mes)-1,Integer.parseInt(dia),0,0,0);
		return c;
	}

}
