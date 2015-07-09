package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Relatorio implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRelatorio;
	
	@Column(length=2)
	int mes;
	
	@Column(length=4)
	int ano;
	
	@Column
	int status;
	
	/* RELACIONAMENTOS */
	
	@ManyToOne	
	@JoinColumn(referencedColumnName="idInscricao", name="fkInscricao")
	private Inscricao inscricao;

	/* GETTERS AND SETTERS */
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public int getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	@Override
	public String toString() {
		return "Relatorio [idRelatorio=" + idRelatorio + ", mes=" + mes
				+ ", ano=" + ano + ", inscricao="
				+ inscricao + "]";
	}

}
