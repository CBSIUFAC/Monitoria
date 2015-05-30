package teste;

import java.util.Date;
import java.util.List;

import DAO.RelatorioDAO;
import entity.Aluno;
import entity.Disciplina;
import entity.Professor;
import entity.Relatorio;

public class RelatorioApp {
	
public static void main (String[] args ){
		
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		Relatorio relatorio = new Relatorio();
		RelatorioApp relatorioApp = new RelatorioApp();
		Aluno aluno  = new Aluno();
		Disciplina disciplina = new Disciplina();
		Professor professor = new Professor();
		
		//aluno.setCpf(10);
		//disciplina.setCodigo("CCET050");
		//professor.setCpf(0);
		
		relatorio.setIdRelatorio(1);
		relatorio.setDataRelatorio(new Date(new String("1090/02/20")));
		relatorio.setSrcPdfAtividade("http://pdf.atividades/"); //E o pdf das faltas e tal?
		relatorio.setSrcPdfRelatorio("http://pdf.relatorio/"); //E o pdf das faltas e tal?
		relatorio.setAluno(aluno);
		relatorio.setDisciplina(disciplina);
		//relatorio.setProfessor(professor);
		relatorioDAO.inserirRelatorio(relatorio);
	
		relatorioApp.imprime();
		
		//aluno.setCpf(10);
		//disciplina.setCodigo("CCET050");
		//professor.setCpf(10);
		
		relatorio.setIdRelatorio(1);
		relatorio.setDataRelatorio(new Date(new String("2014/11/26")));
		relatorio.setSrcPdfAtividade("http2://pdf2.atividades/"); //E o pdf das faltas e tal?
		relatorio.setSrcPdfRelatorio("http2://pdf2.relatorio/"); //E o pdf das faltas e tal?
		relatorio.setAluno(aluno);
		relatorio.setDisciplina(disciplina);
		//relatorio.setProfessor(professor);
		relatorioDAO.atualizarRelatorio(relatorio);
	
		relatorioApp.imprime();
		relatorioDAO.deletarRelatorio(relatorio);	
		relatorioApp.imprime();		
	}
	
	public void imprime(){
		RelatorioDAO relatorioDAO = new RelatorioDAO();		
		List<Relatorio> relatorios = relatorioDAO.getListaRelatorio();
		if (relatorios.size() == 0)
			System.out.println("Não há relatorio cadastrado no sistema.");
		else{
			System.out.println("Imprimindo Relatorio(s):");	
			for (Relatorio a : relatorios) {
				System.out.println(a);
			}	
			System.out.println("\n");
		}
	}


}
