package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Table(name = "mnt_alunos", schema = "DBSM")
@Entity
public class Aluno implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAluno;
	
	
//	@Column(name="MATRICULA")
	private String matricula;
		
//	@Column(name="CPF")
	private String cpf;

//	@Column(name="NOME_PESSOA")
	private String nome;
	
//	@Column(name="RG")
	private String rg;
	
//	@Column(name="DT_NASCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	/* RELACIONAMENTOS */
	
	@OneToOne(mappedBy="aluno")
	private Usuario usuario;
	
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
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Aluno [cpf=" + cpf + ", nome=" + nome + ", rg=" + rg
				+ ", matricula=" + matricula + ", dataNascimento="
				+ dataNascimento + "]";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
}