package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORG_INSTITUICAO", schema="DBSM")
public class Centro implements Serializable {

	@Id
	@Column(name = "sigla_unidade")
	private String sigla;
	
	@Column(name = "nome_unidade")
	private String nome;
		
	/* RELACIONAMENTOS */
	
//	@OneToMany(mappedBy="centro") 
//	private List<Disciplina> disciplinas;
	
	@OneToMany(mappedBy="centro") 
	private List<Edital> editais;
	
	/* GETTERS AND SETTERS */
	
//	public List<Disciplina> getDisciplinas() {
//		return disciplinas;
//	}
	
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

//	public void setDisciplinas(List<Disciplina> disciplinas) {
//		this.disciplinas = disciplinas;
//	}

	@Override
	public String toString() {
		return "Centro [sigla=" + sigla + ", nome=" + nome + "]";
	}
	
	
}
