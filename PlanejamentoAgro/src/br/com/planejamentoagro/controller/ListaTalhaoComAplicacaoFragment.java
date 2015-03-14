package br.com.planejamentoagro.controller;

import java.io.File;
import java.util.List;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.adpter.AdapterTalhaoComAplicacaoListView;
import br.com.planejamentoagro.helper.DiretoriosHelper;
import br.com.planejamentoagro.model.Talhao;
import br.com.planejamentoagro.model.dao.TalhaoDAO;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ListaTalhaoComAplicacaoFragment extends Fragment{
	private List<Talhao> arrayTalhao;
	private ListView lvListaTalhao;
	private AdapterTalhaoComAplicacaoListView talhaoAdapter;
	private Talhao talhaoSelecionado = null;
	private String nomeCliente;
	private TalhaoDAO talhaoDAO;
	private TextView tvAddTalhao;
	public ListaTalhaoComAplicacaoFragment() {}
	public static ListaTalhaoComAplicacaoFragment newInstance()
	{
		return new ListaTalhaoComAplicacaoFragment();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onResume() {
		super.onResume();
		AtualizaListaAsyncTask task = new AtualizaListaAsyncTask();
		task.execute();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_lista_talhao, container,false);
		lvListaTalhao = (ListView) rootView.findViewById(R.id.listViewListaTalhao);
		tvAddTalhao = (TextView) rootView.findViewById(R.id.tvAddTalhao);
//		registerForContextMenu(lvListaTalhao);
		this.nomeCliente = getActivity().getIntent().getStringExtra("NOME_CLIENTE");
		ClickCurto(lvListaTalhao);
		ClickLongo(lvListaTalhao);
		return rootView;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.lista_talhao, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.menu_contexto_talhao, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menuDeletar:
			deletarTalhao();
			break;
		case R.id.menuInformacaoAplicacao:
			informacoesAplicacao();
			break;
		case R.id.menuEditar:
			editarTalhao();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);		
	}
	private void ClickCurto(ListView lv)
	{
//		lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
//		{
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				int idTalhao = (int) talhaoAdapter.getItemId(position);
//				String nomeTalhao = talhaoAdapter.getItem(position).getNome();
//				Intent i = new Intent(getActivity(),InformacoesTecnicas.class);
//				i.putExtra("ID_TALHAO", idTalhao);
//				i.putExtra("NOME_TALHAO", nomeTalhao);
//				startActivity(i);
//			}					
//		});
	}
	private void ClickLongo(ListView lv)
	{
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View v, int position,long id)
		    {
//		    	talhaoSelecionado = (Talhao) talhaoAdapter.getItem(position);
		    	return false;
		    }
		});
	}
	private void informacoesAplicacao()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(talhaoSelecionado.getInformacoesAplicacoes());
		builder.setNeutralButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Informações Cliente");
		dialog.show();
	}
	private void deletarTalhao()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Deseja deletar o talhão "+ talhaoSelecionado.getNome()+ "?"
				+ "WARNING: Todas informações dos talhões e fotos serão deletadas.");
		builder.setPositiveButton("Sim", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				talhaoDAO = new TalhaoDAO(getActivity());
				String nomeTalhao = talhaoSelecionado.getNome();
				talhaoDAO.deletar(talhaoSelecionado);
				talhaoAdapter.remove(talhaoSelecionado);
				File diretorioImagens = new File(InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente+"/"+nomeCliente+"-"+nomeTalhao);
				
				if(diretorioImagens.exists())
				{
					DiretoriosHelper.deleteDirectory(diretorioImagens);
				}
				talhaoSelecionado = null;				
			}
			
		});
		builder.setNegativeButton("Não",null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Confirmar operação");
		dialog.show();
	}
	private void editarTalhao(){
		Intent i = new Intent(getActivity(),EditaTalhao.class);
		i.putExtra("TALHAO_SELECIONADO", talhaoSelecionado);
		i.putExtra("NOME_CLIENTE", this.nomeCliente);
		startActivity(i);
	}
	class AtualizaListaAsyncTask extends AsyncTask<Void, Void, List<Talhao>>
	{

		@Override
		protected List<Talhao> doInBackground(Void... params) {
			talhaoDAO = new TalhaoDAO(getActivity());
	        arrayTalhao = talhaoDAO.listarTalhoesComAplicacao(15);
	        talhaoDAO.fecharConexao();
			return arrayTalhao;
		}
		@Override
		protected void onPostExecute(List<Talhao> result) {
			if(result.size() > 0){
	        	tvAddTalhao.setVisibility(View.INVISIBLE);
				if(talhaoAdapter == null){
					talhaoAdapter = new AdapterTalhaoComAplicacaoListView(getActivity(), result);
					lvListaTalhao.setAdapter(talhaoAdapter);
				}else{
					talhaoAdapter.clear();
					talhaoAdapter.addAll(result);
					lvListaTalhao.setAdapter(talhaoAdapter);
				}
			}else
			{
				tvAddTalhao.setText("Não existem talhões com aplicação.");
				tvAddTalhao.setVisibility(View.VISIBLE);
			}
		}		
	}
}
