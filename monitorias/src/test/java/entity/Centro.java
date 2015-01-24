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

//@NamedQueries({
//    @NamedQuery(name = "Centro.Query", query = "SELECT id, sigla, nome FROM Centro")})

@Entity
//@Table(name="ORG_INSTITUICAO", schema="DBSM")
public class Centro implements Serializable {

	//Alterações
	@Id
//	@Column(name = "ID_UNIDADE")
	private Integer idCentro;

//	@Column(name = "SIGLA_UNIDADE")
	private String siglaCentro;
	
//	@Column(name = "NOME_UNIDADE")
	private String nomeCentro;
		
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
		return idCentro;
	}

	public void setId(Integer centroId) {
		this.idCentro = centroId;
	}
	
	public String getSigla() {
		if(siglaCentro == null) 
			return "- ? -";
		else
			return siglaCentro;
	}
	
	public void setSigla(String sigla) {
		this.siglaCentro = sigla;
	}
	
	public String getNome() {
		return nomeCentro;
	}
	
	public void setNome(String nome) {
		this.nomeCentro = nome;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Centro [id= "+idCentro+", sigla=" + (siglaCentro==null?" ":siglaCentro) + ", nome=" + nomeCentro + "]";
	}	
	
}
