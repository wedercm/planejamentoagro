package br.com.planejamentoagro.adpter;

import java.util.Collection;
import java.util.List;
import br.com.planejamentoagro.model.Informacoes;
import br.com.planejamentoagro.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterInformacoesListView extends BaseAdapter{
	private LayoutInflater mInflater;
    private List<Informacoes> itens;
	public AdapterInformacoesListView(Context contex, List<Informacoes> result) {
		this.itens = result;
		mInflater = LayoutInflater.from(contex);
	}
	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Informacoes getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) 
	{		
		return itens.get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder vHolder;
		
		if(view == null)
		{
			view = mInflater.inflate(R.layout.item_listview_informacoes, parent,false);
			vHolder = new ViewHolder();			
			vHolder.informacoes = (TextView) view.findViewById(R.id.tvInformacoes);
			vHolder.dataVisita = (TextView) view.findViewById(R.id.tvDataVisita);			
			view.setTag(vHolder);			
		}else{
			vHolder = (ViewHolder) view.getTag();
		}
		Informacoes info = itens.get(position);
		if(info != null)
		{
			vHolder.informacoes.setText(info.getInformacoes());
			vHolder.dataVisita.setText(info.getDataVisita());
		}
		return view;
	}
	static class ViewHolder{
		TextView informacoes;
		TextView dataVisita;
	}
	public void addAll(Collection<? extends Informacoes> collection){
        itens.addAll(collection);        
        notifyDataSetChanged();
    }
	public void remove(Informacoes informacoes)
	{
		itens.remove(informacoes);
		notifyDataSetChanged();
	}
	 public void clear() {
	     itens.clear();
	     notifyDataSetChanged();
	}
}
