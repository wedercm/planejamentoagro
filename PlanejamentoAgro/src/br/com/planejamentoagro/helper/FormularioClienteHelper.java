package br.com.planejamentoagro.helper;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.model.Cliente;
import android.app.Activity;
import android.widget.EditText;

public class FormularioClienteHelper {
	private EditText edNome;
	private EditText edNomeFazenda;
	private EditText edMunicipio;	
	private Cliente cliente;
	
	public FormularioClienteHelper(Activity activity)
	{
		this.edNome = (EditText) activity.findViewById(R.id.editTextNomeCliente);
		this.edNomeFazenda = (EditText) activity.findViewById(R.id.editTextNomeFazenda);
		this.edMunicipio = (EditText) activity.findViewById(R.id.editTextTelefone);
	}
	public Cliente getCliente()
	{
		cliente = new Cliente(this.edNome.getText().toString(), this.edNomeFazenda.getText().toString(), this.edMunicipio.getText().toString());
		return this.cliente;
		
	}
	public void setCliente(Cliente cliente)
	{
		this.edNome.setText(cliente.getNome());
		this.edNomeFazenda.setText(cliente.getNomeFazenda());
		this.edMunicipio.setText(cliente.getMunicipio());
		this.cliente = cliente;
	}

}
