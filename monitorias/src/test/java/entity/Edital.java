package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Edital implements Serializable {
	
	@Id
	private int idEdital;
	
	@Column(nullable=false)
	private String titulo;
	
	@Temporal(TemporalType.DATE)
	private Date dataInscricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataResultado;
	
	@Column(nullable=false)
	private int totalVagas;
	
	@Column(nullable=false)
	private String srcPDF;
	
	/* RELACIONAMENTOS */
	
	@OneToMany(mappedBy="edital")
	private List<Inscricao> inscricoes;
	
	@OneToMany(mappedBy="edital")
	private List<Adendo> adendos;

	@OneToMany(mappedBy="edital")
	private Collection<EditalDisciplina> editaisDisciplinas;
	
	@OneToMany(mappedBy="edital")
	private Collection<EditalProfessor> editaisProfessores;
	
	/* GETTERS AND SETTERS */
	
	public List<Adendo> getAdendos() {
		return adendos;
	}
	
	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}

	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Date getDataResultado() {
		return dataResultado;
	}

	public void setDataResultado(Date dataResultado) {
		this.dataResultado = dataResultado;
	}

	public int getTotalVagas() {
		return totalVagas;
	}

	public void setTotalVagas(int totalVagas) {
		this.totalVagas = totalVagas;
	}

	public String getSrcPDF() {
		return srcPDF;
	}

	public void setSrcPDF(String srcPDF) {
		this.srcPDF = srcPDF;
	}

	public Collection<EditalDisciplina> getEditaisDisciplinas() {
		return editaisDisciplinas;
	}

	public void setEditaisDisciplinas(
			Collection<EditalDisciplina> editaisDisciplinas) {
		this.editaisDisciplinas = editaisDisciplinas;
	}

	public Collection<EditalProfessor> getEditaisProfessores() {
		return editaisProfessores;
	}

	public void setEditaisProfessores(Collection<EditalProfessor> editaisProfessores) {
		this.editaisProfessores = editaisProfessores;
	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public void setAdendos(List<Adendo> adendos) {
		this.adendos = adendos;
	}

	@Override
	public String toString() {
		return "Edital [idEdital=" + idEdital + ", titulo=" + titulo
				+ ", dataInscricao=" + dataInscricao + ", dataResultado="
				+ dataResultado + ", totalVagas=" + totalVagas + ", srcPDF="
				+ srcPDF + "]";
	}
	
	
}
