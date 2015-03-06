package br.com.planejamentoagro.controller;

import java.util.Calendar;
import br.com.planejamentoagro.R;
import br.com.planejamentoagro.adpter.TabPagerAdapter;
import br.com.planejamentoagro.view.SlidingTabLayout;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity{
	
	private SlidingTabLayout mSlidingTabLayout;
	private ViewPager mViewPager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mViewPager = (ViewPager) findViewById(R.id.view_pager);
		mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),getApplicationContext()));			
		mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
		mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);
		mSlidingTabLayout.setBackgroundResource(R.drawable.shape_tabs);;
		mSlidingTabLayout.setSelectedIndicatorColors(Color.GREEN);
		mSlidingTabLayout.setDistributeEvenly(true);
		mSlidingTabLayout.setViewPager(mViewPager);
		
	}
	public void casdastrarCliente(View v)
	{
		Intent i = new Intent(getApplicationContext(),CadastraCliente.class);
		startActivity(i);
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}	
}
