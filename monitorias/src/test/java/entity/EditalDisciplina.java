package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EditalDisciplina implements Serializable {
	
	@Id
	@GeneratedValue
	private int idEditalDisciplina;
	
	/* RELACIONAMENTOS */
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idEdital", name="fkEdital")
	private Edital edital;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="cod_disciplina", name="codigo")
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
