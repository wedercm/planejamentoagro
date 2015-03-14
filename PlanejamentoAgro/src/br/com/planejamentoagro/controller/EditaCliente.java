package br.com.planejamentoagro.controller;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.helper.DiretoriosHelper;
import br.com.planejamentoagro.helper.FormularioClienteHelper;
import br.com.planejamentoagro.model.Cliente;
import br.com.planejamentoagro.model.dao.ClienteDAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class EditaCliente extends Activity{
	private FormularioClienteHelper helperCliente;
	private int idCliente;
	private String nomeCliente;
	private ClienteDAO clienteDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_cliente);
		helperCliente = new FormularioClienteHelper(this);
		Cliente cliente = (Cliente) getIntent().getSerializableExtra("CLIENTE_SELECIONADO");
		
		if (cliente != null)
		{
			helperCliente.setCliente(cliente);
			idCliente = cliente.getId();
			nomeCliente = cliente.getNome();
		}
		clienteDAO = new ClienteDAO(EditaCliente.this);

	}
	public void salvarCliente()
	{
		
		Cliente cliente = helperCliente.getCliente();
		if(!cliente.getNome().equals(""))
		{
			cliente.setId(idCliente);
			clienteDAO.editar(cliente);
			clienteDAO.fecharConexao();
			DiretoriosHelper.renameFile(InformacoesTecnicas.CAMINHO_IMAGENS+nomeCliente,InformacoesTecnicas.CAMINHO_IMAGENS+cliente.getNome());
			finish();
		}else Toast.makeText(getApplicationContext(), "Nome cliente é obrigatório.", Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastra_cliente, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_save) {
			salvarCliente();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
