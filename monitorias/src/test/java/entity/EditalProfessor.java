package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EditalProfessor implements Serializable {

	@Id
	@GeneratedValue
	private int idEditalProfessor;
	
	/* RELACIONAMENTOS */
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idEdital", name="fkEdital")
	private Edital edital;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="cpf", name="fkProfessor")
	private Professor professor;
	
	/* GETTERS AND SETTERS */
	
}
