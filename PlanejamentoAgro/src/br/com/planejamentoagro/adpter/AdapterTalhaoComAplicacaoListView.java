package br.com.planejamentoagro.adpter;

import java.util.Collection;
import java.util.List;

import br.com.planejamentoagro.model.Talhao;
import br.com.planejamentoagro.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterTalhaoComAplicacaoListView extends BaseAdapter{
	private LayoutInflater mInflater;
    private List<Talhao> itens;
	public AdapterTalhaoComAplicacaoListView(Context contex, List<Talhao> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(contex);
	}

	static void ViewHolder()
	{
		
	}
	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Talhao getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return itens.get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder vHolder;
		
		if(view == null)
		{
			view = mInflater.inflate(R.layout.item_listview_talhao_aplicacao, parent,false);
			vHolder = new ViewHolder();			
			vHolder.textViewNomeCliente = (TextView) view.findViewById(R.id.textViewNomeCliente);
			vHolder.textViewNomeTalhao = (TextView) view.findViewById(R.id.textViewNomeTalhao);
			vHolder.textViewDiasParaProximaAplicacao = (TextView) view.findViewById(R.id.itemListDiasParaAplicacao);			
			view.setTag(vHolder);			
		}else{
			vHolder = (ViewHolder) view.getTag();
		}
		Talhao talhao = itens.get(position);
		String dias = "dias.";
		if(talhao != null)
		{
			vHolder.textViewNomeTalhao.setText(talhao.getNome());
			vHolder.textViewNomeCliente.setText(talhao.getNomeCliente());
			int diasParaProximaAplicacao = talhao.getDiasParaProximaAplicacao(); 
			if(diasParaProximaAplicacao <= 1)
			{
				dias = "dia.";
			}
			if (diasParaProximaAplicacao < 1000)
			{
				if(diasParaProximaAplicacao <= 5)
				{
					vHolder.textViewDiasParaProximaAplicacao.setTextColor(Color.RED);
				}else vHolder.textViewDiasParaProximaAplicacao.setTextColor(Color.WHITE);
				String textTextViewProximaAplicacao = new StringBuilder().append(diasParaProximaAplicacao)
						.append(" ").append(dias).toString();
				vHolder.textViewDiasParaProximaAplicacao.setText(textTextViewProximaAplicacao);
			}else vHolder.textViewDiasParaProximaAplicacao.setText("Todas aplicações realizadas.");
		}
		return view;
	}
	static class ViewHolder{
		TextView textViewNomeTalhao,textViewNomeCliente,textViewDiasParaProximaAplicacao;
	}
	public void remove(Talhao talhao)
	{
		itens.remove(talhao);
		notifyDataSetChanged();
	}
	 public void clear() {
	     itens.clear();
	     notifyDataSetChanged();
	 }
	public void addAll(Collection<? extends Talhao> collection){
        itens.addAll(collection);        
        notifyDataSetChanged();		
	}
}