package br.com.planejamentoagro.helper;

import android.app.AlertDialog;
import android.content.Context;

public final class GeraInformacoesEntidades{
	
	public static void geraAlertaDialog(Context contextoAtividade,String mensagemAlertDialog, String tituloAlertDialog, String mensagemNetralButton)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(contextoAtividade);
		builder.setMessage(mensagemAlertDialog);
		builder.setNeutralButton(mensagemNetralButton, null);
		AlertDialog dialog = builder.create();
		dialog.setTitle(tituloAlertDialog);
		dialog.show();
	}
}
