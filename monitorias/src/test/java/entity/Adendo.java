package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Adendo implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAdendo;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column (nullable=false)
	private String srcPDF;

	/* RELACIONAMENTOS */
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idEdital",name="fkEdital")
	private Edital edital;
	
	/* GETTERS AND SETTERS */ 
	
	public int getIdAdendo() {
		return idAdendo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSrcPDF() {
		return srcPDF;
	}

	public void setSrcPDF(String srcPDF) {
		this.srcPDF = srcPDF;
	}
}
