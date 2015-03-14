package br.com.planejamentoagro.controller;
import java.io.File;
import java.util.List;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.adpter.AdapterClienteListView;
import br.com.planejamentoagro.helper.DiretoriosHelper;
import br.com.planejamentoagro.helper.GeraInformacoesEntidades;
import br.com.planejamentoagro.model.Cliente;
import br.com.planejamentoagro.model.dao.ClienteDAO;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ListaClientesFragment extends Fragment{
	public static final String DELETOU_CLIENTE = "DELETOU_CLIENTE";
	private SharedPreferences mPreference; 
	private AdapterClienteListView adaptadorClientesListView =null;
	private List<Cliente> listaDeClientes= null;
	private ClienteDAO clienteDAO = null;
	private ListView listViewClientes = null;
	private Cliente clienteSelecionadoToqueLista = null;
	private TextView textViewCadastrarCliente;
	private ImageView imageViewCadastrarCliente;
	private ProgressBar progressBar;
	public ListaClientesFragment() {
		// TODO Auto-generated constructor stub
	}
	public static ListaClientesFragment newInstance()
	{
		return new ListaClientesFragment();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setHasOptionsMenu(true);
		mPreference = getActivity().getSharedPreferences(DELETOU_CLIENTE, Context.MODE_PRIVATE);
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
		View rootView = inflater.inflate(R.layout.fragment_lista_cliente, container,false);
		listViewClientes = (ListView) rootView.findViewById(R.id.listViewListaClientes);
		textViewCadastrarCliente = (TextView) rootView.findViewById(R.id.tvAddCliente);
		imageViewCadastrarCliente = (ImageView) rootView.findViewById(R.id.imAddCliente);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBarListaCliente);
		mPreference.edit().putBoolean(DELETOU_CLIENTE, false);
		registerForContextMenu(listViewClientes);
		toqueCurtoItemLista(listViewClientes);
		toqueLongoItemLista(listViewClientes);
		return rootView;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.lista_cliente, menu);		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			setInformacoesSobreAppDialog();
			return true;
		}
		if (id == R.id.cadastrarCliente)
		{
			
			Intent i = new Intent(getActivity(),CadastraCliente.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {		
		super.onCreateContextMenu(menu, v, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.menu_contexto, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuDeletarCliente:
			deletarCliente();
			break;
		case R.id.menuSobreCliente:
			setInformacoesClienteDialog();
			break;
		case R.id.menuEditarCliente:
			editarCliente();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);		
	}
	private void toqueCurtoItemLista(ListView lv)
	{
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getActivity(),ListaTalhao.class);
		    	Cliente cliente = (Cliente) adaptadorClientesListView.getItem(position);
				i.putExtra("ID_CLIENTE", cliente.getId());
				i.putExtra("NOME_CLIENTE", cliente.getNome());
				startActivity(i);
			}					
		});
	}
	private void toqueLongoItemLista(ListView lv)
	{
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View v, int position,long id)
		    {
		    	clienteSelecionadoToqueLista = (Cliente) adaptadorClientesListView.getItem(position);
		    	return false;
		    }
		});
	}
	private void setInformacoesClienteDialog()
	{
		GeraInformacoesEntidades.geraAlertaDialog(getActivity(),clienteSelecionadoToqueLista.getInformacoes(), 
				new StringBuilder().append("Sobre ")
				.append(clienteSelecionadoToqueLista.getNome()).toString(),"OK");
	}	
	private void setInformacoesSobreAppDialog()
	{
		GeraInformacoesEntidades.
		geraAlertaDialog(getActivity(),"Desenvolvido por: Weder Mendes\nemail: wederc@gmail.com", 
				"Informações Desenvolvedor","OK");
	}
	
	private void deletarCliente()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		String mensagemAlertDialog = new StringBuilder().append("Deseja deletar o cliente ")
				.append(clienteSelecionadoToqueLista.getNome())
				.append("?")
				.append("\nWARNING: Todas informações do cliente, seus talhões e suas repectivas fotos serão deletadas").toString();
		builder.setMessage(mensagemAlertDialog);
		builder.setPositiveButton("Sim", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				clienteDAO = new ClienteDAO(getActivity());
				String nomeCliente = clienteSelecionadoToqueLista.getNome();
				clienteDAO.deletar(clienteSelecionadoToqueLista);
				adaptadorClientesListView.remove(clienteSelecionadoToqueLista);
				if(adaptadorClientesListView.getCount() <= 0)
				{
					imageViewCadastrarCliente.setVisibility(View.VISIBLE);
		        	textViewCadastrarCliente.setVisibility(View.VISIBLE);
				}
				mPreference.edit().putBoolean(DELETOU_CLIENTE, true).commit();
				clienteDAO.fecharConexao();
				File diretorioImagens = new File(InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente);
				if(diretorioImagens.exists())
				{
					DiretoriosHelper.deleteDirectory(diretorioImagens);
				}
				clienteSelecionadoToqueLista = null;		
			}
			
		});
		builder.setNegativeButton("Não",null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Confirmar operação");
		dialog.show();
	}
	private void editarCliente()
	{
		Intent i = new Intent(getActivity(),EditaCliente.class);
		i.putExtra("CLIENTE_SELECIONADO", clienteSelecionadoToqueLista);
		startActivity(i);
	}
	private class AtualizaListaAsyncTask extends AsyncTask<Void, Void, List<Cliente>>
	{
		@Override
		protected void onProgressUpdate(Void... values) {
			progressBar.setVisibility(View.VISIBLE);
		}
		@Override
		protected List<Cliente> doInBackground(Void... params) {
			clienteDAO = new ClienteDAO(getActivity());
	        listaDeClientes = clienteDAO.listarTodos(ClienteDAO.COLUNA_NOME);
	        clienteDAO.fecharConexao();
	        publishProgress();
	        return listaDeClientes;
		}
		@Override
		protected void onPostExecute(List<Cliente> result) {
			progressBar.setVisibility(View.INVISIBLE);
			if(result.size() > 0){
				imageViewCadastrarCliente.setVisibility(View.INVISIBLE);
				textViewCadastrarCliente.setVisibility(View.INVISIBLE);
				if(adaptadorClientesListView == null){
					adaptadorClientesListView = new AdapterClienteListView(getActivity(), result);
					listViewClientes.setAdapter(adaptadorClientesListView);
				}
				else{
					adaptadorClientesListView.clear();
					adaptadorClientesListView.addAll(result);
					listViewClientes.setAdapter(adaptadorClientesListView);
				}
			}else{
				imageViewCadastrarCliente.setVisibility(View.VISIBLE);
	        	textViewCadastrarCliente.setVisibility(View.VISIBLE);
			}
		}		
	}
}
