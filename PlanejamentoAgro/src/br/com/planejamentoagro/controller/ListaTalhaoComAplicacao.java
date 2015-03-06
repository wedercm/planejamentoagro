package br.com.planejamentoagro.controller;

import br.com.planejamentoagro.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ListaTalhaoComAplicacao extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_talhao_com_aplicacao);
		if(savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.containerListaTalhaoAplicacao, new ListaTalhaoComAplicacaoFragment()).commit();
		}
	}
}
