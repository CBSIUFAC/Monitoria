package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "Disciplina.Query", query = "SELECT id, codigo, nome, cargaHoraria FROM Disciplina")})
@Entity
@Table(name="disciplinas", schema="DBSM")
public class Disciplina implements Serializable {
	
	@Id
	@Column(name="ID_DISCIPLINA")
	private int id;
	
	@Column(name="COD_DISCIPLINA")
	private String codigo;
	
	@Column(name="NOME_DISCIPLINA")
	private String nome;
	
	@Column(name="CH_TOTAL")
	private Integer cargaHoraria;
	
	/* RELACIONAMENTOS */

//	public Centro getCentro() {
//		return centro;
//	}
//
//	public void setCentro(Centro centro) {
//		this.centro = centro;
//	}
	
	//@ManyToOne
	//@JoinColumn(referencedColumnName="sigla_unidade", name="sigla")
	//private Centro centro;
	
	@OneToMany(mappedBy="disciplina")
	private List<Inscricao> inscricoes;
	
	@OneToMany(mappedBy="disciplina")
	private List<Relatorio> relatorios;
	
	@OneToMany(mappedBy="disciplina")
	private Collection<EditalDisciplina> editaisDisciplinas;
	
	/* GETTERS AND SETTERS */
	
	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}
	
	public List<Relatorio> getRelatorios() {
		return relatorios;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return "Disciplina [codigo=" + codigo + ", nome=" + nome
				+ ", cargaHoraria=" + cargaHoraria+"]";
				//+ ", centro=" + centro + "]";
	}
}
