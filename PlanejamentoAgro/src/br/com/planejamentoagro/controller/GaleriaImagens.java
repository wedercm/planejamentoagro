//package br.com.planejamentoagro.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import br.com.planejamentoagro.R;
//import br.com.planejamentoagro.adpter.GaleriaImagensAdapter;
//import br.com.planejamentoagro.model.dao.CaminhoImagenDAO;
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.GridView;
//
//public class GaleriaImagens extends Activity {
//	private List<String> list;
//	private GaleriaImagensAdapter galeriaAdapter;
//	private GridView gridView;
//	private CaminhoImagenDAO caminhoImagensDAO;
//	private int idTalhao;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_galeria_imagens);
//		idTalhao = getIntent().getIntExtra("ID_TALHAO", -1);
//		
//	}
//	
//	@Override
//	protected void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		carregarImages();
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.galeria_imagens, menu);
//		return true;
//	}
//	public void carregarImages()
//	{
//		list = new ArrayList<String>();
//		caminhoImagensDAO = new CaminhoImagenDAO(GaleriaImagens.this);
//		list = caminhoImagensDAO.listarCaminhoImagem(idTalhao);
//		this.gridView = (GridView) findViewById(R.id.gridView);
//		this.galeriaAdapter = new GaleriaImagensAdapter(getApplicationContext(), list);
//		gridView.setAdapter(galeriaAdapter);
//	}
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//}
