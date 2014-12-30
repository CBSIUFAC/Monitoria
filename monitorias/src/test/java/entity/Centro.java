package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Centro implements Serializable {

	@Id
	@Column(nullable=false)
	private String sigla;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String nomeDiretor;
	
	/* RELACIONAMENTOS */
	
	@OneToMany(mappedBy="centro") 
	private List<Disciplina> disciplinas;
	
	@OneToMany(mappedBy="centro") 
	private List<Edital> editais;
	
	/* GETTERS AND SETTERS */
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNomeDiretor() {
		return nomeDiretor;
	}
	
	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Centro [sigla=" + sigla + ", nome=" + nome + ", nomeDiretor="
				+ nomeDiretor + "]";
	}
	
	
}
