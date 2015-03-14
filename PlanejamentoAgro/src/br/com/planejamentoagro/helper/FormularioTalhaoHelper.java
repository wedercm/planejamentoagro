package br.com.planejamentoagro.helper;

import java.util.Calendar;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.model.Talhao;
import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class FormularioTalhaoHelper {
	private EditText etNomeTalhao, etDataPlantio, etProdutoAplicado, etDias;
	private RadioGroup rgAplicacao;
	private RadioButton rbAplicacao22;
	private Context context;
	public FormularioTalhaoHelper(Activity activity)
	{
		this.context = activity;
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
			calendario = FormatacaoDeDatas.string2calendar(FormatacaoDeDatas.subtraiMes(dataPlantio));			
		}
		if (!dias.equals("") && idItemSelecionado == R.id.rbAplicaoEm)
		{
			try{
				diasIniciaAplicacao = Integer.parseInt(dias);
			}catch(NumberFormatException e)
			{
				Toast.makeText(this.context, "Número muito grande.", Toast.LENGTH_SHORT).show();
				diasIniciaAplicacao = -1;
			}
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
		String dataPlantio = FormatacaoDeDatas.adicionaMes(calendario);	
		etDataPlantio.setText(dataPlantio);
	
	}
}
