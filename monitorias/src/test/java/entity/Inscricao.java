package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Inscricao implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idInscricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataInscricao;
	
	@javax.persistence.Transient
	private Centro centro;
	
	@Column
	private Integer status;
	
	@Transient
	private int vagas;

	/* RELACIONAMENTOS */
	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="matricula",name="fkAluno")
	private Aluno aluno;
	
	@ManyToOne	
	@JoinColumn(referencedColumnName="idDisciplina",name="fkDisciplina")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idEdital",name="fkEdital")
	private Edital edital;

	/* GETTERS AND SETTERS */
	
	
	public int getIdInscricao() {
		return idInscricao;
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public void setIdInscricao(int idInscricao) {
		this.idInscricao = idInscricao;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Inscricao [idInscricao=" + idInscricao + ", dataInscricao="
				+ dataInscricao + ", centro=" + centro + ", status=" + status
				+ ", vagas=" + vagas + ", aluno=" + aluno + ", disciplina="
				+ disciplina + ", edital=" + edital + "]";
	}
	
}