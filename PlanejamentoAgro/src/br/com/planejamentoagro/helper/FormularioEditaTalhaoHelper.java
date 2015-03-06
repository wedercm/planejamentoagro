package br.com.planejamentoagro.helper;

import java.util.Calendar;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.model.Talhao;
import android.app.Activity;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;


public class FormularioEditaTalhaoHelper {
	private EditText etNomeTalhao, etDataPlantio, etProdutoAplicado;
	private TextView tvDataAplicacao1,tvDataAplicacao2,tvDataAplicacao3,tvDataAplicacao4,tvDataAplicacao5,tvDataAplicacao6;
	private TextView tvData1,tvData2,tvData3,tvData4,tvData5,tvData6;
	private Talhao talhao = new Talhao();
	public FormularioEditaTalhaoHelper(Activity activity)
	{
		this.etNomeTalhao = (EditText) activity.findViewById(R.id.etNomeTalhao);
		this.etDataPlantio = (EditText) activity.findViewById(R.id.etDataPlantio);
		this.etProdutoAplicado = (EditText) activity.findViewById(R.id.etProdutoAplicado);
		this.tvData1 = (TextView)  activity.findViewById(R.id.tvDataPrevista1);
		this.tvData2 = (TextView)  activity.findViewById(R.id.tvDataPrevista2);
		this.tvData3 = (TextView)  activity.findViewById(R.id.tvDataPrevista3);
		this.tvData4 = (TextView)  activity.findViewById(R.id.tvDataPrevista4);
		this.tvData5 = (TextView)  activity.findViewById(R.id.tvDataPrevista5);
		this.tvData6 = (TextView)  activity.findViewById(R.id.tvDataPrevista6);
		this.tvDataAplicacao1 = (TextView)  activity.findViewById(R.id.tvDataAplicacao1);
		this.tvDataAplicacao2 = (TextView)  activity.findViewById(R.id.tvDataAplicacao2);
		this.tvDataAplicacao3 = (TextView)  activity.findViewById(R.id.tvDataAplicacao3);
		this.tvDataAplicacao4 = (TextView)  activity.findViewById(R.id.tvDataAplicacao4);
		this.tvDataAplicacao5 = (TextView)  activity.findViewById(R.id.tvDataAplicacao5);
		this.tvDataAplicacao6 = (TextView)  activity.findViewById(R.id.tvDataAplicacao6);		
		this.etDataPlantio.setEnabled(false);
		
		
	}
	public Talhao getTalhao()
	{
		String nomeTalhao = etNomeTalhao.getText().toString();
		String produtoAplicado = etProdutoAplicado.getText().toString();
		String dataPlantio = etDataPlantio.getText().toString();
		Calendar calendario;		
		if(dataPlantio.equals("")){
			calendario = null;	
		}else{
			dataPlantio = FormatacaoDeDatas.subtraiMes(dataPlantio);
			calendario = FormatacaoDeDatas.string2calendar(dataPlantio);
			this.talhao.setData1(FormatacaoDeDatas.subtraiMes(tvData1.getText().toString()));
			this.talhao.setData2(FormatacaoDeDatas.subtraiMes(tvData2.getText().toString()));
			this.talhao.setData3(FormatacaoDeDatas.subtraiMes(tvData3.getText().toString()));
			this.talhao.setData4(FormatacaoDeDatas.subtraiMes(tvData4.getText().toString()));
			this.talhao.setData5(FormatacaoDeDatas.subtraiMes(tvData5.getText().toString()));
			this.talhao.setData6(FormatacaoDeDatas.subtraiMes(tvData6.getText().toString()));
			
			this.talhao.setDataPlantio(calendario);
			this.talhao.setDataAplicacao1(FormatacaoDeDatas.subtraiMes(tvDataAplicacao1.getText().toString()));
			this.talhao.setDataAplicacao2(FormatacaoDeDatas.subtraiMes(tvDataAplicacao2.getText().toString()));
			this.talhao.setDataAplicacao3(FormatacaoDeDatas.subtraiMes(tvDataAplicacao3.getText().toString()));
			this.talhao.setDataAplicacao4(FormatacaoDeDatas.subtraiMes(tvDataAplicacao4.getText().toString()));
			this.talhao.setDataAplicacao5(FormatacaoDeDatas.subtraiMes(tvDataAplicacao5.getText().toString()));
			this.talhao.setDataAplicacao6(FormatacaoDeDatas.subtraiMes(tvDataAplicacao6.getText().toString()));			
			this.talhao.setNome(nomeTalhao);
			this.talhao.setProdutoAplicado(produtoAplicado);
		}		
		return this.talhao;		
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
		this.talhao = talhao;
	}
	public void setDatasTalhao(Talhao talhao)
	{
		switch (talhao.getProximaAplicacao()) {
		
		case 2:
			tvData1.setTextColor(Color.RED);
			tvDataAplicacao1.setTextColor(Color.RED);
			break;
		case 3:
			tvData1.setTextColor(Color.RED);
			tvDataAplicacao1.setTextColor(Color.RED);
			tvData2.setTextColor(Color.RED);
			tvDataAplicacao2.setTextColor(Color.RED);
			break;
		case 4:
			tvData1.setTextColor(Color.RED);
			tvDataAplicacao1.setTextColor(Color.RED);
			tvData2.setTextColor(Color.RED);
			tvDataAplicacao2.setTextColor(Color.RED);
			tvData3.setTextColor(Color.RED);
			tvDataAplicacao3.setTextColor(Color.RED);
			break;
		case 5:
			tvData1.setTextColor(Color.RED);
			tvDataAplicacao1.setTextColor(Color.RED);
			tvData2.setTextColor(Color.RED);
			tvDataAplicacao2.setTextColor(Color.RED);
			tvData3.setTextColor(Color.RED);
			tvDataAplicacao3.setTextColor(Color.RED);
			tvData4.setTextColor(Color.RED);
			tvDataAplicacao4.setTextColor(Color.RED);
			break;
		case 6:
			tvData1.setTextColor(Color.RED);
			tvDataAplicacao1.setTextColor(Color.RED);
			tvData2.setTextColor(Color.RED);
			tvDataAplicacao2.setTextColor(Color.RED);
			tvData3.setTextColor(Color.RED);
			tvDataAplicacao3.setTextColor(Color.RED);
			tvData4.setTextColor(Color.RED);
			tvDataAplicacao4.setTextColor(Color.RED);
			tvData5.setTextColor(Color.RED);
			tvDataAplicacao5.setTextColor(Color.RED);
			break;
		case -1:
			tvData1.setTextColor(Color.RED);
			tvDataAplicacao1.setTextColor(Color.RED);
			tvData2.setTextColor(Color.RED);
			tvDataAplicacao2.setTextColor(Color.RED);
			tvData3.setTextColor(Color.RED);
			tvDataAplicacao3.setTextColor(Color.RED);
			tvData4.setTextColor(Color.RED);
			tvDataAplicacao4.setTextColor(Color.RED);
			tvData5.setTextColor(Color.RED);
			tvDataAplicacao5.setTextColor(Color.RED);
			tvData6.setTextColor(Color.RED);
			tvDataAplicacao6.setTextColor(Color.RED);
			break;
		default:
			break;
		}
		tvData1.setText(FormatacaoDeDatas.adicionaMes(talhao.getData1()));
		tvData2.setText(FormatacaoDeDatas.adicionaMes(talhao.getData2()));
		tvData3.setText(FormatacaoDeDatas.adicionaMes(talhao.getData3()));
		tvData4.setText(FormatacaoDeDatas.adicionaMes(talhao.getData4()));
		tvData5.setText(FormatacaoDeDatas.adicionaMes(talhao.getData5()));
		tvData6.setText(FormatacaoDeDatas.adicionaMes(talhao.getData6()));
		
		tvDataAplicacao1.setText(FormatacaoDeDatas.adicionaMes(talhao.getDataAplicacao1()));
		tvDataAplicacao2.setText(FormatacaoDeDatas.adicionaMes(talhao.getDataAplicacao2()));
		tvDataAplicacao3.setText(FormatacaoDeDatas.adicionaMes(talhao.getDataAplicacao3()));
		tvDataAplicacao4.setText(FormatacaoDeDatas.adicionaMes(talhao.getDataAplicacao4()));
		tvDataAplicacao5.setText(FormatacaoDeDatas.adicionaMes(talhao.getDataAplicacao5()));
		tvDataAplicacao6.setText(FormatacaoDeDatas.adicionaMes(talhao.getDataAplicacao6()));
	}
	

}
