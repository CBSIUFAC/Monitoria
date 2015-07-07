package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EditalDisciplina implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEditalDisciplina;
	
	@Column
	private int vagas;
	
	/* RELACIONAMENTOS */
	
	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="idEdital", name="fkEdital")
	private Edital edital;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(referencedColumnName="idDisciplina", name="fkDisciplina")
	private Disciplina disciplina;
	/* GETTERS AND SETTERS */
	
	public int getIdEditalDisciplina() {
		return idEditalDisciplina;
	}

	public void setIdEditalDisciplina(int idEditalDisciplina) {
		this.idEditalDisciplina = idEditalDisciplina;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@Override
	public String toString() {
		return "EditalDisciplina [idEditalDisciplina=" + idEditalDisciplina
				+ ", edital=" + edital + ", disciplina=" + disciplina + "]";
	}
	
}
