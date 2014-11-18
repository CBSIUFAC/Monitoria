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
}
