package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Aluno implements Serializable {
	
	@Id
	@Column(length=11, nullable=false)
	private int cpf;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private int rg;
	
	@Column(length=11,nullable=false)
	private int matricula;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	/* RELACIONAMENTOS */
	
	@OneToMany(mappedBy="aluno")
	private List<Inscricao> inscricoes;
	
	@OneToMany(mappedBy="aluno")
	private List<Relatorio> relatorios;
	
	/* GETTERS AND SETTERS */
	
	public List<Inscricao> getInscricoes() {
		return inscricoes;
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
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}	
}
