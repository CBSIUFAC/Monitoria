package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor implements Serializable {

	@Id
	@Column(length=11, nullable=false)
	private int cpf;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private int rg;
	
	/* RELACIONAMENTOS */

	@OneToMany(mappedBy="professor")
	private List<Relatorio> relatorios;
	
	@OneToMany(mappedBy="professor")
	private List<Disciplina> disciplinas;
	
	@OneToMany(mappedBy="professor")
	private Collection<EditalProfessor> editaisProfessores;
	
	/* GETTERS AND SETTERS */
	
	
	public List <Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public Collection<EditalProfessor> getEditaisProfessores() {
		return editaisProfessores;
	}

	public void setEditaisProfessores(Collection<EditalProfessor> editaisProfessores) {
		this.editaisProfessores = editaisProfessores;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getRg() {
		return rg;
	}
	
	public void setRg(int rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Professor [cpf=" + cpf + ", nome=" + nome + ", rg=" + rg + "]";
	}
		
}