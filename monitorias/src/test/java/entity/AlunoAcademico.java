package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACAD_ALUNOS", schema="DBSM")
public class AlunoAcademico implements Serializable {

	@Id
	@Column
	private int id;
	
	@Column
	private String nome;
	
	@Column
	private char sexo;
	
	@Column
	private Date dt_nascimento;
	
	
	/* GETTERS AND SETTERS */
	
	
	public int getId() {
		return id;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "AlunoAcademicoDAO [id=" + id + ", nome=" + nome + ", sexo=" + sexo
				+ ", dt_nascimento=" + dt_nascimento + "]";
	}
	
}
