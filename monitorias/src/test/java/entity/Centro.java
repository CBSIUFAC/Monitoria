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

	public Integer getIdCentro() {
		return idCentro;
	}


	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
	}


	public String getSiglaCentro() {
		return siglaCentro;
	}


	public void setSiglaCentro(String siglaCentro) {
		this.siglaCentro = siglaCentro;
	}


	public String getNomeCentro() {
		return nomeCentro;
	}


	public void setNomeCentro(String nomeCentro) {
		this.nomeCentro = nomeCentro;
	}


	public List<Edital> getEditais() {
		return editais;
	}


	public void setEditais(List<Edital> editais) {
		this.editais = editais;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCentro == null) ? 0 : idCentro.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Centro other = (Centro) obj;
		if (idCentro == null) {
			if (other.idCentro != null)
				return false;
		} else if (!idCentro.equals(other.idCentro))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Centro [idCentro=" + idCentro + "]";
	}

	
}
