package br.com.planejamentoagro.adpter;
import java.util.Collection;
import java.util.List;
import br.com.planejamentoagro.model.Cliente;
import br.com.planejamentoagro.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterClienteListView extends BaseAdapter{
	private LayoutInflater mInflater;
    private List<Cliente> itens;
	public AdapterClienteListView(Context contex, List<Cliente> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(contex);
	}
	@Override
	public int getCount() {
		return itens.size();
	}
	@Override
	public Cliente getItem(int position) {
		return itens.get(position);
	}
	@Override
	public long getItemId(int position) {		
		return itens.get(position).getId();
	}@Override
	public View getView(int position, View view, ViewGroup parent) {
	
		ViewHolder vHolder;
		
		if(view == null)
		{
			view = mInflater.inflate(R.layout.item_listview_cliente, parent,false);
			vHolder = new ViewHolder();			
			vHolder.nome = (TextView) view.findViewById(R.id.itemListNomeCliente);
			vHolder.fazenda = (TextView) view.findViewById(R.id.itemListNomeFazenda);
			vHolder.municipio = (TextView) view.findViewById(R.id.itemListNomeMunicipio);			
			view.setTag(vHolder);			
		}else{
			vHolder = (ViewHolder) view.getTag();
		}
		Cliente cliente = itens.get(position);
		if(cliente != null)
		{
			vHolder.nome.setText(cliente.getNome());
			vHolder.fazenda.setText(cliente.getNomeFazenda());
			vHolder.municipio.setText(cliente.getMunicipio());
		}
		return view;
	}
	static class ViewHolder{
		TextView nome;
		TextView fazenda;
		TextView municipio;
	}
	public void addAll(Collection<? extends Cliente> collection){
        itens.addAll(collection);        
        notifyDataSetChanged();
    }
	public void remove(Cliente cliente)
	{
		itens.remove(cliente);
		notifyDataSetChanged();
	}
	 public void clear() {
	     itens.clear();
	     notifyDataSetChanged();
	}
}
