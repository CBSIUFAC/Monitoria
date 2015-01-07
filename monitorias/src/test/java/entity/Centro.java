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

	//Alterações
	@Id
	@Column(name = "ID_UNIDADE")
	private Integer centroId;

	@Column(name = "SIGLA_UNIDADE")
	private String centroSigla;
	
	@Column(name = "NOME_UNIDADE")
	private String centroNome;
		
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
	

	public Integer getCentroId() {
		return centroId;
	}

	public void setCentroId(Integer centroId) {
		this.centroId = centroId;
	}
	
	public String getSigla() {
		return centroSigla;
	}
	
	public void setSigla(String sigla) {
		this.centroSigla = sigla;
	}
	
	public String getNome() {
		return centroNome;
	}
	
	public void setNome(String nome) {
		this.centroNome = nome;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Centro [id= "+centroId+", sigla=" + (centroSigla==null?" ":centroSigla) + ", nome=" + centroNome + "]";
	}
	
	
}
