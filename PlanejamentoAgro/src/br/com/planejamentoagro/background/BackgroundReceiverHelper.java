package br.com.planejamentoagro.background;
import java.util.Calendar;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.controller.MainActivity;
import br.com.planejamentoagro.helper.FormatacaoDeDatas;
import br.com.planejamentoagro.model.dao.TalhaoDAO;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class BackgroundReceiverHelper extends BroadcastReceiver {
	public static final int ID_NOFICICACAO = 87;
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		if(verificaData(context))
		{
			geraNotificacao(context);
		}
	}
	private static boolean verificaData(Context context)
	{
		TalhaoDAO talhaoDao = new TalhaoDAO(context);
		Calendar c = Calendar.getInstance();
		String data = FormatacaoDeDatas.calendar2string(c);
		boolean result = talhaoDao.verificarData(data);
		talhaoDao.fecharConexao();
		return result;
	}
	private void geraNotificacao(Context context)
	{
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		PendingIntent p = PendingIntent.getActivity(context, 0, new Intent(context,MainActivity.class), 0);
		
		Notification.Builder builder = new Notification.Builder(context);
		builder.setTicker("Aplicações hoje.");
		builder.setContentTitle("Aplicação");
		builder.setContentText("Existe aplicacações para hoje.");
		int SDK_INT = android.os.Build.VERSION.SDK_INT;
		if(SDK_INT >= 21)
			builder.setSmallIcon(R.drawable.ic_notification);
		else
			builder.setSmallIcon(R.drawable.ic_notification2);
		
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		builder.setContentIntent(p);
		
		Notification n = builder.build();
		n.vibrate = new long[]{150,300,150,600};
		nm.notify(ID_NOFICICACAO,n);
		
		try {
			Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone toque = RingtoneManager.getRingtone(context, som);
			toque.play();
		} catch (Exception e) {
			
		}
	}
}
