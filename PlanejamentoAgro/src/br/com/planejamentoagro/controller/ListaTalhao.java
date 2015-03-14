package br.com.planejamentoagro.controller;

import java.io.File;
import java.util.List;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.adpter.AdapterTalhaoListView;
import br.com.planejamentoagro.helper.DiretoriosHelper;
import br.com.planejamentoagro.model.Talhao;
import br.com.planejamentoagro.model.dao.TalhaoDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListaTalhao extends Activity {
	private List<Talhao> arrayTalhao;
	private ListView lvListaTalhao;
	private AdapterTalhaoListView talhaoAdapter;
	private Talhao talhaoSelecionado = null;
	private int idCliente;
	private String nomeCliente;
	private TalhaoDAO talhaoDAO;
	private ImageView imAddTalhao;
	private TextView tvAddTalhao;
	private ProgressBar progressBar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_talhao);
		lvListaTalhao = (ListView) findViewById(R.id.list);
		imAddTalhao = (ImageView) findViewById(R.id.imAddTalhao);
		tvAddTalhao = (TextView) findViewById(R.id.tvAddTalhao);
		progressBar = (ProgressBar) findViewById(R.id.progressBarListaTalhao);
		registerForContextMenu(lvListaTalhao);
		this.idCliente = getIntent().getIntExtra("ID_CLIENTE",-1);
		this.nomeCliente = getIntent().getStringExtra("NOME_CLIENTE");
		this.setTitle("Talhãos do "+ nomeCliente);
		ClickLongo(lvListaTalhao);
		ClickCurto(lvListaTalhao);
	}
	protected void onResume() {
		super.onResume();
		AtualizaListaAsyncTask task = new AtualizaListaAsyncTask();
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_talhao, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.idCadastraTalhao)
		{
			iniciarActivityCadastraTalhao();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu_contexto_talhao, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menuDeletarTalhao:
			deletarTalhao();
			break;
		case R.id.menuInformacaoAplicacao:
			informacoesAplicacao();
			break;
		case R.id.menuEditarTalhao:
			editarTalhao();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);		
	}
	private void ClickCurto(ListView lv)
	{
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int idTalhao = (int) talhaoAdapter.getItemId(position);
				String nomeTalhao = talhaoAdapter.getItem(position).getNome();
				Intent i = new Intent(ListaTalhao.this,InformacoesTecnicas.class);
				i.putExtra("ID_TALHAO", idTalhao);
				i.putExtra("NOME_TALHAO", nomeTalhao);
				i.putExtra("NOME_CLIENTE", nomeCliente);
				startActivity(i);
			}					
		});
	}
	private void ClickLongo(ListView lv)
	{
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View v, int position,long id)
		    {
		    	talhaoSelecionado = (Talhao) talhaoAdapter.getItem(position);
		    	return false;
		    }
		});
	}
	private void informacoesAplicacao()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(talhaoSelecionado.getInformacoesAplicacoes());
		builder.setNeutralButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Informações Cliente");
		dialog.show();
	}
	private void deletarTalhao()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String message = new StringBuilder("Deseja deletar o ").append(talhaoSelecionado.getNome()).append("?")
				.append("WARNING: Todas informações dos talhões e fotos serão deletadas.").toString();
		builder.setMessage(message);
		builder.setPositiveButton("Sim", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				talhaoDAO = new TalhaoDAO(ListaTalhao.this);
				String nomeTalhao = talhaoSelecionado.getNome();
				talhaoDAO.deletar(talhaoSelecionado);
				talhaoAdapter.remove(talhaoSelecionado);
				if(talhaoAdapter.getCount() <= 0)
				{
					tvAddTalhao.setVisibility(View.VISIBLE);
					imAddTalhao.setVisibility(View.VISIBLE);
				}

				File diretorioImagens = new File(InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente+"/"+nomeTalhao);
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
		Intent i = new Intent(ListaTalhao.this,EditaTalhao.class);
		i.putExtra("TALHAO_SELECIONADO", talhaoSelecionado);
		i.putExtra("NOME_CLIENTE", this.nomeCliente);

		startActivity(i);
	}
	public void adicionarTalhao(View v)
	{
		iniciarActivityCadastraTalhao();
	}
	private void iniciarActivityCadastraTalhao()
	{
		Intent i = new Intent(ListaTalhao.this,CadastraTalhao.class);
		i.putExtra("ID_CLIENTE", this.idCliente);
		i.putExtra("NOME_CLIENTE", this.nomeCliente);
		startActivityForResult(i, this.idCliente);
	}
	private class AtualizaListaAsyncTask extends AsyncTask<Void, Void, List<Talhao>>
	{
		@Override
		protected void onProgressUpdate(Void... values){
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected List<Talhao> doInBackground(Void... params){
			publishProgress();
			talhaoDAO = new TalhaoDAO(ListaTalhao.this);
			arrayTalhao = talhaoDAO.listaPorFK(idCliente,TalhaoDAO.COLUNA_DATA_PLANTIO);
	        talhaoDAO.fecharConexao();
			return arrayTalhao;
		}
		@Override
		protected void onPostExecute(List<Talhao> result) {
			progressBar.setVisibility(View.INVISIBLE);
			if(result.size() > 0){
	        	tvAddTalhao.setVisibility(View.INVISIBLE);
	        	imAddTalhao.setVisibility(View.INVISIBLE);
				if(talhaoAdapter == null){
					talhaoAdapter = new AdapterTalhaoListView(ListaTalhao.this, result);
					lvListaTalhao.setAdapter(talhaoAdapter);
				}else{
					talhaoAdapter.clear();
					talhaoAdapter.addAll(result);
					lvListaTalhao.setAdapter(talhaoAdapter);
				}
			}else
			{
				tvAddTalhao.setVisibility(View.VISIBLE);
				imAddTalhao.setVisibility(View.VISIBLE);
			}
		}		
	}

}
