package br.com.planejamentoagro.helper;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public final class FormatacaoDeDatas {
	
	FormatacaoDeDatas()
	{		
	}

	public static int diasRestantes(Date d1, Date d2)
	{
		DateTime dt1 = new DateTime(d1);
		DateTime dt2 = new DateTime(d2);
		return Days.daysBetween(dt1, dt2).getDays()+1;		
	}
	public static String date2string(Date d)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH);
		int ano = c.get(Calendar.YEAR);
		String data = new StringBuilder().append(dia).append("/")
				.append(mes).append("/")
				.append(ano).toString();
		return data;
	}
	public static String calendar2string(Calendar c)
	{
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH);
		int ano = c.get(Calendar.YEAR);
		String data = new StringBuilder().append(dia).append("/")
				.append(mes).append("/")
				.append(ano).toString();
		return data;
	}
	public static Date string2date(String a)	
	{
		String dia=null,mes = null,ano=null;
		if (a.length() == 8)
		{
			dia = a.substring(0, 1);
			mes = a.substring(2, 3);
			ano = a.substring(4, 8);
		}else if(a.length() == 10)
		{
			dia = a.substring(0, 2);
			mes = a.substring(3,5);
			ano = a.substring(6,10);
		}else if(a.charAt(1) == '/')
		{
			dia = a.substring(0, 1);
			mes = a.substring(2, 4);
			ano = a.substring(5, 9);
		}else{
			dia = a.substring(0, 2);
			mes = a.substring(3, 4);
			ano = a.substring(5, 9);			
		}
		Calendar c = Calendar.getInstance();
		try {
			c.set(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia),0,0,0);
		} catch (Exception e){
		}		
		Date d = new Date();
		d = c.getTime();
		return d;
	}
	public static int string2month(String a)	
	{
		String mes = null;
		int month=0;
		if (a.length() == 8)
		{
			mes = a.substring(2, 3);
		}else if(a.length() == 10)
		{
			mes = a.substring(3,5);
		}else if(a.charAt(1) == '/')
		{
			mes = a.substring(2, 4);
		}else{
			mes = a.substring(3, 4);			
		}
		
		try{
			month = Integer.parseInt(mes);
		}catch(NumberFormatException e)
		{
			
		}
		return month;
	}
	public static Calendar string2calendar(String a)	
	{
		String dia=null,mes = null,ano=null;
		if (a.length() == 8)
		{
			dia = a.substring(0, 1);
			mes = a.substring(2, 3);
			ano = a.substring(4, 8);
		}else if(a.length() == 10)
		{
			dia = a.substring(0, 2);
			mes = a.substring(3,5);
			ano = a.substring(6,10);
		}else if(a.charAt(1) == '/')
		{
			dia = a.substring(0, 1);
			mes = a.substring(2, 4);
			ano = a.substring(5, 9);
		}else{
			dia = a.substring(0, 2);
			mes = a.substring(3, 4);
			ano = a.substring(5, 9);			
		}
		Calendar c = Calendar.getInstance();
		try {
			c.set(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia),0,0,0);
		} catch (NumberFormatException e) 
		{
			
		}
		return c;
	}
	public static String subtraiMes(String data)
	{
		String dia=null,mes = null,ano=null;
		if (data.length() == 8)
		{
			dia = data.substring(0, 1);
			mes = data.substring(2, 3);
			ano = data.substring(4, 8);
		}else if(data.length() == 10)
		{
			dia = data.substring(0, 2);
			mes = data.substring(3,5);
			ano = data.substring(6,10);
		}else if(data.charAt(1) == '/')
		{
			dia = data.substring(0, 1);
			mes = data.substring(2, 4);
			ano = data.substring(5, 9);
		}else{
			dia = data.substring(0, 2);
			mes = data.substring(3, 4);
			ano = data.substring(5, 9);			
		}
		int mesModificado=0;
		String dataRetorno;
		try{
			mesModificado = Integer.parseInt(mes)-1;
			dataRetorno = new StringBuilder().append(dia).append("/")
					.append(mesModificado).append("/")
					.append(ano).toString();
		}catch(NumberFormatException e){
			dataRetorno = new StringBuilder().append(dia).append("/")
					.append(mes).append("/")
					.append(ano).toString();
		}
		
		return dataRetorno;
	}
	public static String adicionaMes(String data)
	{
		String dia=null,mes = null,ano=null;
		if (data.length() == 8)
		{
			dia = data.substring(0, 1);
			mes = data.substring(2, 3);
			ano = data.substring(4, 8);
		}else if(data.length() == 10)
		{
			dia = data.substring(0, 2);
			mes = data.substring(3,5);
			ano = data.substring(6,10);
		}else if(data.charAt(1) == '/')
		{
			dia = data.substring(0, 1);
			mes = data.substring(2, 4);
			ano = data.substring(5, 9);
		}else{
			dia = data.substring(0, 2);
			mes = data.substring(3, 4);
			ano = data.substring(5, 9);			
		}
		int mesModificado=0;
		String dataRetorno;
		try{
			mesModificado = Integer.parseInt(mes)+1;
			dataRetorno = new StringBuilder().append(dia).append("/")
					.append(mesModificado).append("/")
					.append(ano).toString();
		}catch(NumberFormatException e){
			dataRetorno = new StringBuilder().append(dia).append("/")
					.append(mes).append("/")
					.append(ano).toString();
		}		
		return dataRetorno;
	}
	public static String adicionaMes(Calendar calendario)
	{
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		int mes = calendario.get(Calendar.MONTH);
		int ano = calendario.get(Calendar.YEAR);
		int mesModificado= mes + 1;;
		String  dataRetorno = new StringBuilder().append(dia).append("/")
					.append(mesModificado).append("/")
					.append(ano).toString();
		return dataRetorno;
	}
}


