package teste;

import java.util.Date;
import java.util.List;

import DAO.InscricaoDAO;
import DAO.RelatorioDAO;
import entity.Aluno;
import entity.Inscricao;
import entity.Relatorio;

public class RelatorioApp {
	
public static void main (String[] args ){
		
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		InscricaoDAO inscricaoDAO = new InscricaoDAO();
		Relatorio relatorio = new Relatorio();
		RelatorioApp relatorioApp = new RelatorioApp();
		Aluno aluno  = new Aluno();
		Inscricao inscricao = inscricaoDAO.getInscricao(27);
		
		relatorio.setAno(2015);
		relatorio.setInscricao(inscricao);
		relatorio.setMes(03);
		relatorio.setStatus(0);
		relatorioDAO.inserirRelatorio(relatorio);
	
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
