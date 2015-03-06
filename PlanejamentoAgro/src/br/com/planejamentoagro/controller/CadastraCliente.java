package br.com.planejamentoagro.controller;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.helper.FormularioClienteHelper;
import br.com.planejamentoagro.inteface.InterfaceAtividades;
import br.com.planejamentoagro.model.Cliente;
import br.com.planejamentoagro.model.dao.ClienteDAO;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class CadastraCliente extends Activity implements InterfaceAtividades{
	private FormularioClienteHelper helperCliente;
	private ClienteDAO clienteDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_cliente);
		helperCliente = new FormularioClienteHelper(this);
		clienteDAO = new ClienteDAO(CadastraCliente.this);

	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	// OnClick do button.
	public void salvarCliente(View v)
	{
		Cliente cliente = helperCliente.getCliente();
		if(!cliente.getNome().equals(""))
		{
			clienteDAO.salvar(cliente);
			clienteDAO.fecharConexao();
			finish();
		}else Toast.makeText(getApplicationContext(), "Nome cliente é obrigatório.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_cliente, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_save){
			Cliente cliente = helperCliente.getCliente();
			if(!cliente.getNome().equals(""))
			{
				clienteDAO.salvar(cliente);
				clienteDAO.fecharConexao();
				finish();
			}else Toast.makeText(getApplicationContext(), "Nome cliente é obrigatório.", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public Context getContext() {
		return getApplicationContext();
	}
	
}
