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
public class Relatorio implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRelatorio;
	
	@Column(nullable=false)
	private String srcPdfAtividade;
	
	@Column(nullable=false)
	private String srcPdfRelatorio;
	
	@Temporal(TemporalType.DATE)
	private Date dataRelatorio;

	/* RELACIONAMENTOS */
	
	@ManyToOne
	@JoinColumn(referencedColumnName="matricula",name="fkAluno")
	private Aluno aluno;
	
	@ManyToOne	
	@JoinColumn(referencedColumnName="idDisciplina", name="fkDisciplina")
	private Disciplina disciplina;

	/* GETTERS AND SETTERS */
	
	public int getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public String getSrcPdfAtividade() {
		return srcPdfAtividade;
	}

	public void setSrcPdfAtividade(String srcPdfAtividade) {
		this.srcPdfAtividade = srcPdfAtividade;
	}

	public String getSrcPdfRelatorio() {
		return srcPdfRelatorio;
	}

	public void setSrcPdfRelatorio(String srcPdfRelatorio) {
		this.srcPdfRelatorio = srcPdfRelatorio;
	}

	public Date getDataRelatorio() {
		return dataRelatorio;
	}

	public void setDataRelatorio(Date dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
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

//	public Professor getProfessor() {
//		return professor;
//	}
//
//	public void setProfessor(Professor professor) {
//		this.professor = professor;
//	}

	@Override
	public String toString() {
		return "Relatorio [idRelatorio=" + idRelatorio + ", srcPdfAtividade="
				+ srcPdfAtividade + ", srcPdfRelatorio=" + srcPdfRelatorio
				+ ", dataRelatorio=" + dataRelatorio + ", aluno=" + aluno
				+ ", disciplina=" + disciplina	+ "]";
	}
	
}
