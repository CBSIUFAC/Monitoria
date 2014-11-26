package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Disciplina implements Serializable {
	
	@Id
	@Column(nullable=false)
	private String codigo;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private int cargaHoraria;
	
	/* RELACIONAMENTOS */
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="cpf", name="fkProfessor")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="sigla", name="fkCentro")
	private Centro centro;
	
	@OneToMany(mappedBy="disciplina")
	private List<Inscricao> inscricoes;
	
	@OneToMany(mappedBy="disciplina")
	private List<Relatorio> relatorios;
	
	@OneToMany(mappedBy="disciplina")
	private Collection<EditalDisciplina> editaisDisciplinas;
	
	/* GETTERS AND SETTERS */
	
	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}
	
	public List<Relatorio> getRelatorios() {
		return relatorios;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return "Disciplina [codigo=" + codigo + ", nome=" + nome
				+ ", cargaHoraria=" + cargaHoraria + ", professor=" + professor
				+ ", centro=" + centro + "]";
	}
	
}
