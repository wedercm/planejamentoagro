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

public class AdapterTalhaoListView extends BaseAdapter{
	private LayoutInflater mInflater;
    private List<Talhao> itens;
	public AdapterTalhaoListView(Context contex, List<Talhao> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(contex);
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
			view = mInflater.inflate(R.layout.item_listview_talhao, parent,false);
			vHolder = new ViewHolder();
			vHolder.nome = (TextView) view.findViewById(R.id.itemListNomeTalhao);
			vHolder.diasParaProximaAplicacao = (TextView) view.findViewById(R.id.itemListDiasParaAplicacao);			
			view.setTag(vHolder);			
		}else{
			vHolder = (ViewHolder) view.getTag();
		}
		Talhao talhao = itens.get(position);
		String dias = "dias.";
		if(talhao != null)
		{
			vHolder.nome.setText(talhao.getNome());
			int diasParaProximaAplicacao = talhao.getDiasParaProximaAplicacao();
			if(diasParaProximaAplicacao <= 1)
			{
				dias = "dia.";
			}
			if (diasParaProximaAplicacao < 1000)
			{
				if(diasParaProximaAplicacao <= 5)
				{
					vHolder.diasParaProximaAplicacao.setTextColor(Color.RED);
				}else vHolder.diasParaProximaAplicacao.setTextColor(Color.WHITE);
			String textTextViewProximaAplicacao = new StringBuilder().append(diasParaProximaAplicacao)
					.append(" ").append(dias).toString();
				vHolder.diasParaProximaAplicacao.setText(textTextViewProximaAplicacao);
			}else vHolder.diasParaProximaAplicacao.setText("Todas aplicações realizadas.");
		}
		return view;
	}
	static class ViewHolder{
		TextView nome;
		TextView diasParaProximaAplicacao;
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
