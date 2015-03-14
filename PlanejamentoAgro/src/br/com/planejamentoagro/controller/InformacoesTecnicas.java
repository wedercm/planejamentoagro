package br.com.planejamentoagro.controller;

import java.io.File;
import java.util.List;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.adpter.AdapterInformacoesListView;
import br.com.planejamentoagro.helper.DiretoriosHelper;
import br.com.planejamentoagro.model.Informacoes;
import br.com.planejamentoagro.model.dao.InformacoesTecnicasDAO;
import br.com.planejamentoagro.model.dao.TalhaoDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.Toast;

public class InformacoesTecnicas extends Activity {
	public static final String CAMINHO_IMAGENS = Environment.getExternalStorageDirectory()+"/PlanejamentoAgro/";
	public static final String EXTRA_ID_INFO = "EXTRA_ID_INFO";
	public static final String EXTRA_INFO = "EXTRA_INFO";
	public static final String EXTRA_DATA_INFO = "EXTRA_DATA_INFO";
	private List<Informacoes> arrayInformacoes;
	private AdapterInformacoesListView informacoesAdapter;
	private ListView lvListaInformacoes;
	private Informacoes infoSelecionada=null;
	private ImageView ivAddInfo;
	private TextView tvAddInfo;
	private ProgressBar progressBar;
	String localImagemInformacao = null;
	private int idTalhao;
	private InformacoesTecnicasDAO infoDAO;
	private String nomeTalhao, nomeCliente;
	public int REQUEST_CAMERA = 123;
    private File[] allFiles ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacoes_tecnicas);
		
		idTalhao = getIntent().getIntExtra("ID_TALHAO", -1);
		nomeTalhao = getIntent().getStringExtra("NOME_TALHAO");
		nomeCliente = getIntent().getStringExtra("NOME_CLIENTE");
		lvListaInformacoes = (ListView) findViewById(R.id.list);
		ivAddInfo = (ImageView) findViewById(R.id.imAddInformacoes);
		tvAddInfo = (TextView) findViewById(R.id.tvAddInformacoes);	
		progressBar = (ProgressBar) findViewById(R.id.progressBarInformacoesTecnicas);
		ClickLongo(lvListaInformacoes);
		ClickCurto(lvListaInformacoes);
		registerForContextMenu(lvListaInformacoes);
	}
	
	//Button
	public void adicionarInformacoes(View v)
	{
		Intent i = new Intent(InformacoesTecnicas.this,CadastraInformacoes.class);
		i.putExtra(TalhaoDAO.COLUNA_ID, idTalhao);
		startActivity(i);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		AtualizaListaAsyncTask task = new AtualizaListaAsyncTask();
		task.execute();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK)
			Toast.makeText(getApplicationContext(), "Imagem salva com sucesso.", Toast.LENGTH_SHORT).show();
		else Toast.makeText(getApplicationContext(), "Imagem não foi salva.", Toast.LENGTH_SHORT).show(); 
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.informacoes_tecnicas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		if(id == R.id.action_addInformacoes)
		{
			Intent i = new Intent(InformacoesTecnicas.this,CadastraInformacoes.class);
			i.putExtra(TalhaoDAO.COLUNA_ID, idTalhao);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

		public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu_contexto_informacoes, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menuDeletar:
			deletarInformacoes();
			break;
		case R.id.menuTirarFotos:
			tirarFoto();
			break;
		case R.id.menuEditar:
			editarInformacoes();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);		
	}
	private void editarInformacoes(){
		Intent i = new Intent(InformacoesTecnicas.this,CadastraInformacoes.class);
		i.putExtra(TalhaoDAO.COLUNA_ID, infoSelecionada.getIdTalhao());
		i.putExtra(EXTRA_ID_INFO, infoSelecionada.getId());
		i.putExtra(EXTRA_INFO, infoSelecionada.getInformacoes());
		i.putExtra(EXTRA_DATA_INFO, infoSelecionada.getDataVisita());
		startActivity(i);
	}
	private void tirarFoto()
	{
		String caminhoPastaImagensInformacao = new StringBuilder().append(CAMINHO_IMAGENS)
				.append(nomeCliente)
				.append("/")
				.append(nomeTalhao)
				.append("/")
				.append(infoSelecionada.getId()).toString();
		File diretorioImagensInfomacao = new File(caminhoPastaImagensInformacao);
//		File rootDirectory = new File(CAMINHO_IMAGENS+".nomedia");
//		if(!rootDirectory.exists())
//		{
//			rootDirectory.mkdirs();
//		}
		if(!diretorioImagensInfomacao.exists())
		{
			diretorioImagensInfomacao.mkdirs();			
		}
		this.localImagemInformacao = new StringBuilder().append(diretorioImagensInfomacao)
				.append("/")
				.append(System.currentTimeMillis())
				.append(".jpg").toString();		
		File foto = new File(localImagemInformacao);
		Uri uriFoto = Uri.fromFile(foto);		
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		i.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
		startActivityForResult(i, REQUEST_CAMERA);
	}
	public class SingleMediaScanner implements MediaScannerConnectionClient {

        private MediaScannerConnection mMs;
        private File mFile;

        public SingleMediaScanner(Context context, File f) {
            mFile = f;
            mMs = new MediaScannerConnection(context, this);
            mMs.connect();
        }

        public void onMediaScannerConnected() {
            mMs.scanFile(mFile.getAbsolutePath(), null);            
        }
        @Override
        public void onScanCompleted(String path, Uri uri) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
            mMs.disconnect();
        }

    }
	private void ClickCurto(ListView lv)
	{
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				infoSelecionada = (Informacoes) informacoesAdapter.getItem(position);
				File diretorioImagens = new File(CAMINHO_IMAGENS+nomeCliente+"/"+nomeTalhao+"/"+infoSelecionada.getId());
				  if(diretorioImagens.exists())
				  {
					  allFiles = diretorioImagens.listFiles();
					  if(allFiles.length > 0){
						  new SingleMediaScanner(InformacoesTecnicas.this, allFiles[0]);
					  }
					  else Toast.makeText(getApplicationContext(), "Não existe imagens para esssa visita.", Toast.LENGTH_SHORT).show();
				  }else Toast.makeText(getApplicationContext(), "Não existe imagens para esssa visita.", Toast.LENGTH_SHORT).show();
				
			}					
		});
	}
	private void ClickLongo(ListView lv)
	{
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View v, int position,long id)
		    {
		    	infoSelecionada = (Informacoes) informacoesAdapter.getItem(position);
		    	return false;
		    }
		});
	}
	private void deletarInformacoes()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Deseja deletar essas informações de visita ?"
				+ "\n"
				+ "WARNING: Todas as fotos também serão deletadas.");
		builder.setPositiveButton("Sim", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				infoDAO= new InformacoesTecnicasDAO(getApplicationContext());
				infoDAO.deletar(infoSelecionada);
				informacoesAdapter.remove(infoSelecionada);
				if(informacoesAdapter.getCount() <= 0)
				{
					tvAddInfo.setVisibility(View.VISIBLE);
					ivAddInfo.setVisibility(View.VISIBLE);
				}
				File diretorioImagens = new File(InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente+"/"+nomeTalhao+"/"+infoSelecionada.getId());
				
				if(diretorioImagens.exists())
				{
					DiretoriosHelper.deleteDirectory(diretorioImagens);
				}
				infoSelecionada = null;
			}
			
		});
		builder.setNegativeButton("Não",null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Confirmar operação");
		dialog.show();
	}
	private class AtualizaListaAsyncTask extends AsyncTask<Void, Void, List<Informacoes>>
	{
		@Override
		protected void onProgressUpdate(Void... values){
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected List<Informacoes	> doInBackground(Void... params){
			publishProgress();
			infoDAO = new InformacoesTecnicasDAO(InformacoesTecnicas.this);
			arrayInformacoes = infoDAO.listaPorFK(idTalhao,InformacoesTecnicasDAO.COLUNA_DATA_VISITA);
	        infoDAO.fecharConexao();
			return arrayInformacoes;
		}
		@Override
		protected void onPostExecute(List<Informacoes> result) {
			progressBar.setVisibility(View.INVISIBLE);
			if(result.size() > 0){
				ivAddInfo.setVisibility(View.INVISIBLE);
				tvAddInfo.setVisibility(View.INVISIBLE);
				if(informacoesAdapter == null){
					informacoesAdapter = new AdapterInformacoesListView(InformacoesTecnicas.this, result);
					lvListaInformacoes.setAdapter(informacoesAdapter);
				}else{
					informacoesAdapter.clear();
					informacoesAdapter.addAll(result);
					lvListaInformacoes.setAdapter(informacoesAdapter);
				}
			}else
			{
				ivAddInfo.setVisibility(View.VISIBLE);
				tvAddInfo.setVisibility(View.VISIBLE);
			}
		}		
	}
}
