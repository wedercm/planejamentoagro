package br.com.planejamentoagro.background;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BackgroundReceiverBoot extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		geraAlarme(context);
	}
	public void geraAlarme(Context context)
	{
		boolean alarmeAtivo = (PendingIntent.getBroadcast(context, 0, new Intent("GERA_NOTIFICACAO"), PendingIntent.FLAG_NO_CREATE) == null);
		if(alarmeAtivo)
		{
			Intent intent = new Intent("GERA_NOTIFICACAO");
			PendingIntent pIntent  = PendingIntent.getBroadcast(context, 0, intent, 0);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 5, 0,0);
			AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 86400000, pIntent);
		} 
	}
}
