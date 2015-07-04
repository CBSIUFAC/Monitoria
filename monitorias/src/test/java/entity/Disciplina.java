package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

//@NamedQueries({
//    @NamedQuery(name = "Disciplina.Query", query = "SELECT id, codigo, nome, cargaHoraria FROM Disciplina")})
@Entity
//@Table(name="disciplinas", schema="DBSM")
public class Disciplina implements Serializable {
	
	@Id
//	@Column(name="ID_DISCIPLINA")
	private int idDisciplina;
	
//	@Column(name="NOME_DISCIPLINA")
	private String codigoDisciplina;
	
//	@Column(name="CH_TOTAL")
	private Integer cargaHoraria;
	
	private String nomeDisciplina;
	
	private int periodo;
	
	@Transient
	private int vagas;
	
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	
	public int getVagas() {
		return vagas;
	}
	
	private int totalInscritos;
	
	private int ano;
	
	private String professor;
	
	/* RELACIONAMENTOS */

	@ManyToOne
//	@JoinColumn(referencedColumnName="ID_UNIDADE",name="ID_UNIDADE") //Por quê que uma teve de ser igual à outra?
	@JoinColumn(referencedColumnName="idCentro", name="fkCentro")
	private Centro centro;
	
	@OneToMany(mappedBy="disciplina", fetch=FetchType.EAGER)
	private List<Inscricao> inscricoes;
	
	@OneToMany(mappedBy="disciplina")
	private Collection<EditalDisciplina> editaisDisciplinas;
	
	
	/* GETTERS AND SETTERS */
	
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
	
	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}
	
	
	public int getId() {
		return idDisciplina;
	}

	public void setId(int id) {
		this.idDisciplina = id;
	}
	
	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina.trim();
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getTotalInscritos() {
		return totalInscritos;
	}

	public void setTotalInscritos(int totalInscritos) {
		this.totalInscritos = totalInscritos;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Collection<EditalDisciplina> getEditaisDisciplinas() {
		return editaisDisciplinas;
	}

	public void setEditaisDisciplinas(
			Collection<EditalDisciplina> editaisDisciplinas) {
		this.editaisDisciplinas = editaisDisciplinas;
	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}


	public Integer getCargaHoraria() {
		if(cargaHoraria == null) 
			return 0;
		else 
			return cargaHoraria;
	}
	
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String toString() {
		return "Disciplina [idDisciplina=" + idDisciplina + ", nomeDisciplina="
				+ nomeDisciplina + ", professor=" + professor + "]";
	}

}