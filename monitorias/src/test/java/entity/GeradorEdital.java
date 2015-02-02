package entity;

import java.util.Date;

public class GeradorEdital {

	int idEdital;
	String titulo;
	Date dataInscricao;
	Date dataFimInscricao;
	Date dataCriacao;
	Date dataResultado;
	int totalVagas;
	String srcPDF;
	int ano;
	int periodo;
	String nomeCentro;
	
	int idEditalDisciplina;
	int fkDisciplina;
	int fkEdital;
	
	String nomeDisciplina;
	String professor;
	int vagas;
	
	public GeradorEdital(Edital e, EditalDisciplina ed, Disciplina d) {
		
		idEdital = e.getIdEdital();
		titulo = e.getTitulo();
		dataInscricao = e.getDataInscricao();
		dataFimInscricao = e.getDataFimInscricao();
		dataCriacao = e.getDataCriacao();
		dataResultado = e.getDataResultado();
		totalVagas = e.getTotalVagas();
		srcPDF = e.getSrcPDF();
		ano = e.getAno();
		periodo = e.getPeriodo();
		nomeCentro = e.getCentro().getNome();
		
		idEditalDisciplina = ed.getIdEditalDisciplina();
		fkDisciplina = ed.getDisciplina().getIdDisciplina();
		fkEdital = ed.getEdital().getIdEdital();
		
		nomeDisciplina = d.getNomeDisciplina();
		professor = d.getProfessor();
		vagas = d.getVagas();
	}

	
	@Override
	public String toString() {
		return "GeradorEdital [idEdital=" + idEdital + ", titulo=" + titulo
				+ ", dataInscricao=" + dataInscricao + ", dataFimInscricao="
				+ dataFimInscricao + ", dataCriacao=" + dataCriacao
				+ ", dataResultado=" + dataResultado + ", totalVagas="
				+ totalVagas + ", srcPDF=" + srcPDF + ", ano=" + ano
				+ ", periodo=" + periodo + ", nomeCentro=" + nomeCentro
				+ ", idEditalDisciplina=" + idEditalDisciplina
				+ ", fkDisciplina=" + fkDisciplina + ", fkEdital=" + fkEdital
				+ ", nomeDisciplina=" + nomeDisciplina + ", professor="
				+ professor + ", vagas=" + vagas + "]";
	}



	public String getSrcPDF() {
		return srcPDF;
	}

	
	public void setSrcPDF(String srcPDF) {
		this.srcPDF = srcPDF;
	}
	
	public int getIdEdital() {
		return idEdital;
	}


	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Date getDataInscricao() {
		return dataInscricao;
	}


	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}


	public Date getDataFimInscricao() {
		return dataFimInscricao;
	}


	public void setDataFimInscricao(Date dataFimInscricao) {
		this.dataFimInscricao = dataFimInscricao;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public Date getDataResultado() {
		return dataResultado;
	}


	public void setDataResultado(Date dataResultado) {
		this.dataResultado = dataResultado;
	}


	public int getTotalVagas() {
		return totalVagas;
	}


	public void setTotalVagas(int totalVagas) {
		this.totalVagas = totalVagas;
	}


	public int getIdEditalDisciplina() {
		return idEditalDisciplina;
	}


	public void setIdEditalDisciplina(int idEditalDisciplina) {
		this.idEditalDisciplina = idEditalDisciplina;
	}


	public int getFkDisciplina() {
		return fkDisciplina;
	}


	public void setFkDisciplina(int fkDisciplina) {
		this.fkDisciplina = fkDisciplina;
	}


	public int getFkEdital() {
		return fkEdital;
	}


	public void setFkEdital(int fkEdital) {
		this.fkEdital = fkEdital;
	}


	public String getNomeDisciplina() {
		return nomeDisciplina;
	}


	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}


	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
	}


	public int getVagas() {
		return vagas;
	}


	public void setVagas(int vagas) {
		this.vagas = vagas;
	}



	public int getAno() {
		return ano;
	}



	public String getNomeCentro() {
		return nomeCentro;
	}



	public void setNomeCentro(String nomeCentro) {
		this.nomeCentro = nomeCentro;
	}



	public void setAno(int ano) {
		this.ano = ano;
	}



	public int getPeriodo() {
		return periodo;
	}



	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	
	
	
}
