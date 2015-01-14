package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import entity.EditalProfessor;
import entity.Relatorio;

@NamedQueries({
    	@NamedQuery(name = "Professor.Query", query = "SELECT nome, rg, cpf FROM Professor")})

@Table(name = "MNT_PROFESSORES", schema = "DBSM")
@Entity
public class Professor implements Serializable {

	@Id
	@Column(name="CPF")
	private String cpf;
	
	@Column(name="NOME_PESSOA")
	private String nome;
	
	@Column(name="RG")
	private String rg;
	
	//RELACIONAMENTOS 

//	@OneToMany(mappedBy="professor")
//	private List<Relatorio> relatorios;


	@OneToMany(mappedBy="professor")
	private Collection<EditalProfessor> editaisProfessores;

	 //GETTERS AND SETTERS
	
	public Collection<EditalProfessor> getEditaisProfessores() {
		return editaisProfessores;
	}

	public void setEditaisProfessores(Collection<EditalProfessor> editaisProfessores) {
		this.editaisProfessores = editaisProfessores;
	}

//	public List<Relatorio> getRelatorios() {
//		return relatorios;
//	}
//	
//	public void setRelatorios(List<Relatorio> relatorios) {
//		this.relatorios = relatorios;
//	}

	
	//Getters and Setters
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Professor [cpf=" + cpf + ", nome=" + nome + ", rg= " + rg+ " ]";
	}
		
}