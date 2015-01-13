package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "Centro.Query", query = "SELECT id, sigla, nome FROM Centro")})

@Entity
@Table(name="ORG_INSTITUICAO", schema="DBSM")
public class Centro implements Serializable {

	//Alterações
	@Id
	@Column(name = "ID_UNIDADE")
	private Integer id;

	@Column(name = "SIGLA_UNIDADE")
	private String sigla;
	
	@Column(name = "NOME_UNIDADE")
	private String nome;
		
	//Fim de alterações
	
	/* RELACIONAMENTOS */
	
	@OneToMany(mappedBy="centro") 
	private List<Disciplina> disciplinas;
	
	@OneToMany(mappedBy="centro") 
	private List<Edital> editais;
	
	/* GETTERS AND SETTERS */
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer centroId) {
		this.id = centroId;
	}
	
	public String getSigla() {
		if(sigla == null) 
			return "- ? -";
		else
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

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Centro [id= "+id+", sigla=" + (sigla==null?" ":sigla) + ", nome=" + nome + "]";
	}	
	
}
