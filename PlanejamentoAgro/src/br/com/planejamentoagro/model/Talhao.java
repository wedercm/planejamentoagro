package br.com.planejamentoagro.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import br.com.planejamentoagro.helper.FormatacaoDeDatas;
import br.com.planejamentoagro.inteface.EntidadePersistivel;

public class Talhao implements Serializable, EntidadePersistivel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome, nomeCliente;
	private int id;
	private String produtoAplicado;
	private Calendar dataPlantio;	
	private Date data1,data2,data3,data4,data5,data6;
	private Date dataAplicacao1,dataAplicacao2,dataAplicacao3,dataAplicacao4,dataAplicacao5,dataAplicacao6;
	private int diasIniciaAplicacao;
	private int idCliente;
	private int proximaAplicacao;
	private Date dataProximaAplicacao;
	public Talhao() {
		this.nome = this.nomeCliente = "";
		this.produtoAplicado = "";
		this.dataPlantio = null;
		this.diasIniciaAplicacao = 0;
		this.idCliente = -1;
	}
	public Talhao(String nome, Calendar dataPlantio, String produtoAplicado)
	{
		this.dataPlantio = dataPlantio;
		this.nome = nome;
		this.produtoAplicado = produtoAplicado;
		this.diasIniciaAplicacao = 22;
		ajustaDatas(0);
	}
	public Talhao(String nome, Calendar dataPlantio, String produtoAplicado, int intervaloAplicao)
	{
		this.nome = nome;
		this.dataPlantio = dataPlantio;
		this.diasIniciaAplicacao = intervaloAplicao;
		this.produtoAplicado = produtoAplicado;
		ajustaDatas(0);
	}
	@Override
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdCliente()
	{
		return this.idCliente;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeCliente() {
		return this.nomeCliente;
	}
	public void setNomeCliente(String nome) {
		this.nomeCliente = nome;
	}
	public String getProdutoAplicado() {
		return produtoAplicado;
	}
	public void setProdutoAplicado(String produtoAplicado) {
		this.produtoAplicado = produtoAplicado;
	}
	public String getData1() {
		
		return FormatacaoDeDatas.date2string(data1);
	}
	public String getData2() {
		return FormatacaoDeDatas.date2string(data2);
	}
	public String getData3() {
		return FormatacaoDeDatas.date2string(data3);
	}
	public String getData4() {
		return FormatacaoDeDatas.date2string(data4);
	}
	public String getData5() {
		return FormatacaoDeDatas.date2string(data5);
	}
	public String getData6() {
		return FormatacaoDeDatas.date2string(data6);
	}
	public String getDataAplicacao1() {		
		return FormatacaoDeDatas.date2string(dataAplicacao1);
	}
	public String getDataAplicacao2() {
		return FormatacaoDeDatas.date2string(dataAplicacao2);
	}
	public String getDataAplicacao3() {
		return FormatacaoDeDatas.date2string(dataAplicacao3);
	}
	public String getDataAplicacao4() {
		return FormatacaoDeDatas.date2string(dataAplicacao4);
	}
	public String getDataAplicacao5() {
		return FormatacaoDeDatas.date2string(dataAplicacao5);
	}
	public String getDataAplicacao6() {
		return FormatacaoDeDatas.date2string(dataAplicacao6);
	}
	public void setDataAplicacao1(String data) {		
		this.dataAplicacao1 = FormatacaoDeDatas.string2date(data);
		ajustaDatas(1);
	}
	public void setDataAplicacao2(String data) {
		this.dataAplicacao2 = FormatacaoDeDatas.string2date(data);
		ajustaDatas(2);
	}
	public void setDataAplicacao3(String data) {
		this.dataAplicacao3 = FormatacaoDeDatas.string2date(data);
		ajustaDatas(3);
	}
	public void setDataAplicacao4(String data) {
		this.dataAplicacao4 = FormatacaoDeDatas.string2date(data);
		ajustaDatas(4);
	}
	public void setDataAplicacao5(String data) {
		this.dataAplicacao5 = FormatacaoDeDatas.string2date(data);
		ajustaDatas(5);
	}
	public void setDataAplicacao6(String data){
		this.dataAplicacao6 = FormatacaoDeDatas.string2date(data);
	}
	public void setData1(String data) {		
		this.data1 = FormatacaoDeDatas.string2date(data);
	}
	public void setData2(String data) {
		this.data2 = FormatacaoDeDatas.string2date(data);
	}
	public void setData3(String data) {
		this.data3 = FormatacaoDeDatas.string2date(data);
	}
	public void setData4(String data) {
		this.data4 = FormatacaoDeDatas.string2date(data);
	}
	public void setData5(String data) {
		this.data5 = FormatacaoDeDatas.string2date(data);
	}
	public void setData6(String data){
		this.data6 = FormatacaoDeDatas.string2date(data);
	}
	
	public void setDataPlantio(Calendar dataPlantio) {
		this.dataPlantio = dataPlantio;
		ajustaDatas(0);
	}
	public Calendar getDataPlantio()
	{
		return this.dataPlantio;
	}
	public int getDiasIniciaAplicacao()
	{
		return this.diasIniciaAplicacao;
	}
	public void setDiasIniciaAplicacao(int diasIniciaAplicacao) {
		this.diasIniciaAplicacao = diasIniciaAplicacao;
	}
	private void ajustaDatas(int id)
	{
		Date data0;
		Calendar calendarioAux = Calendar.getInstance();
		switch (id) {
		case 0:
			data0 = dataPlantio.getTime();	    
		    dataPlantio.add(Calendar.DAY_OF_MONTH,diasIniciaAplicacao);
		    this.data1 =dataAplicacao1= dataPlantio.getTime();
			dataPlantio.add(Calendar.DAY_OF_MONTH,15);
			this.data2 =dataAplicacao2= dataPlantio.getTime();
			dataPlantio.add(Calendar.DAY_OF_MONTH,15);
			this.data3 =dataAplicacao3= dataPlantio.getTime();
			dataPlantio.add(Calendar.DAY_OF_MONTH,15);
			this.data4 =dataAplicacao4= dataPlantio.getTime();
			dataPlantio.add(Calendar.DAY_OF_MONTH,15);
			this.data5 =dataAplicacao5= dataPlantio.getTime();
			dataPlantio.add(Calendar.DAY_OF_MONTH,15);
			this.data6 =dataAplicacao6= dataPlantio.getTime();
			dataPlantio.add(Calendar.DAY_OF_MONTH,15);
			dataPlantio.setTime(data0);
			break;
		case 1:
			calendarioAux.setTime(dataAplicacao1);
		    calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data2 = this.dataAplicacao2 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data3 = this.dataAplicacao3 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data4 = this.dataAplicacao4 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data5 = this.dataAplicacao5 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data6 = this.dataAplicacao6 = calendarioAux.getTime();
			break;
		case 2:
			calendarioAux.setTime(dataAplicacao2);
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data3 = this.dataAplicacao3 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data4 = this.dataAplicacao4 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data5 = this.dataAplicacao5 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data6 = this.dataAplicacao6 = calendarioAux.getTime();
			break;
		case 3:
			calendarioAux.setTime(dataAplicacao3);
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data4 = this.dataAplicacao4 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data5 = this.dataAplicacao5 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data6 = this.dataAplicacao6 = calendarioAux.getTime();
			break;
		case 4:
			calendarioAux.setTime(dataAplicacao4);
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data5 = this.dataAplicacao5 = calendarioAux.getTime();
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data6 = this.dataAplicacao6 = calendarioAux.getTime();
			break;
		case 5:
			calendarioAux.setTime(dataAplicacao5);
			calendarioAux.add(Calendar.DAY_OF_MONTH,15);
			this.data6 = this.dataAplicacao6 = calendarioAux.getTime();
			break;
		default:
			break;
		}	   
	}
	public int getProximaAplicacao()
	{
		getDataProximaAplicacao();
		return proximaAplicacao;
	}
	private Date getDataProximaAplicacao()
	{
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);		
		Date d = c.getTime();
		if(d.before(this.dataAplicacao1) || !(d.after(dataAplicacao1) || d.before(dataAplicacao1)))
		{
			dataProximaAplicacao = dataAplicacao1;
			proximaAplicacao = 1;
		}else if((d.after(dataAplicacao1) && d.before(dataAplicacao2)) || !(d.after(dataAplicacao2) || d.before(dataAplicacao2)))
		{
			dataProximaAplicacao = dataAplicacao2;
			proximaAplicacao = 2;
		}else if((d.after(dataAplicacao2) && d.before(dataAplicacao3)) || !(d.after(dataAplicacao3) || d.before(dataAplicacao3)))
		{
			dataProximaAplicacao = dataAplicacao3;
			proximaAplicacao = 3;
		}else if((d.after(dataAplicacao3) && d.before(dataAplicacao4)) || !(d.after(dataAplicacao4) || d.before(dataAplicacao4)))
		{
			dataProximaAplicacao = dataAplicacao4;
			proximaAplicacao = 4;
		}else if((d.after(dataAplicacao4) && d.before(dataAplicacao5)) || !(d.after(dataAplicacao5) || d.before(dataAplicacao5)))			
		{
			dataProximaAplicacao = dataAplicacao5;
			proximaAplicacao = 5;
		}else if((d.after(dataAplicacao5) && d.before(dataAplicacao6)) || !(d.after(dataAplicacao6) || d.before(dataAplicacao6)))
		{
			dataProximaAplicacao = dataAplicacao6;
			proximaAplicacao = 6;
		}else{
			proximaAplicacao = -1;
			dataProximaAplicacao = null;
		}
		
		return dataProximaAplicacao;
	}
	public int getDiasParaProximaAplicacao()
	{
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date dataHoje = c.getTime();
		if(getDataProximaAplicacao() != null)
			return FormatacaoDeDatas.diasRestantes(dataHoje,getDataProximaAplicacao());
		else 
			return 1000;
	}
	public String getInformacoesAplicacoes()
	{
		
		int diaPlantio = this.dataPlantio.get(Calendar.DAY_OF_MONTH);
		int mesPlantio = (this.dataPlantio.get(Calendar.MONTH))+1;
		int anoPlantio = this.dataPlantio.get(Calendar.YEAR);
		Calendar c = Calendar.getInstance();		
		c.setTime(dataAplicacao1);
		int diaAplicacao1 = c.get(Calendar.DAY_OF_MONTH);
		int mesAplicacao1 = (c.get(Calendar.MONTH))+1;
		int anoAplicacao1 = c.get(Calendar.YEAR);
		c.setTime(dataAplicacao2);
		int diaAplicacao2 = c.get(Calendar.DAY_OF_MONTH);
		int mesAplicacao2 = (c.get(Calendar.MONTH))+1;
		int anoAplicacao2 = c.get(Calendar.YEAR);
		c.setTime(dataAplicacao3);
		int diaAplicacao3 = c.get(Calendar.DAY_OF_MONTH);
		int mesAplicacao3 = (c.get(Calendar.MONTH))+1;
		int anoAplicacao3 = c.get(Calendar.YEAR);
		c.setTime(dataAplicacao4);
		int diaAplicacao4 = c.get(Calendar.DAY_OF_MONTH);
		int mesAplicacao4 = (c.get(Calendar.MONTH))+1;
		int anoAplicacao4 = c.get(Calendar.YEAR);
		c.setTime(dataAplicacao5);
		int diaAplicacao5 = c.get(Calendar.DAY_OF_MONTH);
		int mesAplicacao5 = (c.get(Calendar.MONTH))+1;
		int anoAplicacao5 = c.get(Calendar.YEAR);
		c.setTime(dataAplicacao6);
		int diaAplicacao6 = c.get(Calendar.DAY_OF_MONTH);
		int mesAplicacao6 = (c.get(Calendar.MONTH))+1;
		int anoAplicacao6 = c.get(Calendar.YEAR);
		
		
		return 	"\nData Plantio: "+diaPlantio+"/"+mesPlantio+"/"+anoPlantio+				
				"\n1ª Aplicação: "+diaAplicacao1+"/"+mesAplicacao1+"/"+anoAplicacao1+
				"\n2ª Aplicação: "+diaAplicacao2+"/"+mesAplicacao2+"/"+anoAplicacao2+
				"\n3ª Aplicação: "+diaAplicacao3+"/"+mesAplicacao3+"/"+anoAplicacao3+
				"\n4ª Aplicação: "+diaAplicacao4+"/"+mesAplicacao4+"/"+anoAplicacao4+
				"\n5ª Aplicação: "+diaAplicacao5+"/"+mesAplicacao5+"/"+anoAplicacao5+
				"\n6ª Aplicação: "+diaAplicacao6+"/"+mesAplicacao6+"/"+anoAplicacao6; 
	}
	@Override
	public String toString()
	{
		return this.nome+":"+dataPlantio.get(Calendar.DAY_OF_MONTH)+"/"+dataPlantio.get(Calendar.MONTH)+"/"+dataPlantio.get(Calendar.YEAR);
	}
	
	
}
