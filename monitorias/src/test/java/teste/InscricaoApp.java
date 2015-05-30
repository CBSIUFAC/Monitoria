package teste;

import java.util.Date;
import java.util.List;

import DAO.InscricaoDAO;
import entity.Aluno;
import entity.Disciplina;
import entity.Edital;
import entity.Inscricao;

public class InscricaoApp {
	
	public static void main (String[] args ){
		
		InscricaoDAO inscricaoDAO = new InscricaoDAO();
		Inscricao inscricao = new Inscricao();
		InscricaoApp inscricaoApp = new InscricaoApp();
		Aluno aluno = new Aluno();
		Edital edital = new Edital();
		Disciplina disciplina = new Disciplina();
		
		//aluno.setCpf(10);
		edital.setIdEdital(1);
		//disciplina.setCodigo("CCET050");
		
		inscricao.setIdInscricao(1);
		inscricao.setDataInscricao(new Date("2014/11/26"));
		inscricao.setAluno(aluno);
		inscricao.setDisciplina(disciplina);
		inscricao.setEdital(edital);
		inscricaoDAO.inserirInscricao(inscricao);
	
		inscricaoApp.imprime();
		
		//aluno.setCpf(10);
		edital.setIdEdital(1);
		//disciplina.setCodigo("CCET050");
		
		inscricao.setDataInscricao(new Date("2015/03/05"));
		inscricao.setAluno(aluno);
		inscricao.setDisciplina(disciplina);
		inscricao.setEdital(edital);
		inscricaoDAO.atualizarInscricao(inscricao);
	
		inscricaoApp.imprime();
		inscricaoDAO.deletarInscricao(inscricao);
		inscricaoApp.imprime();
	}
	
	public void imprime(){
		InscricaoDAO inscricaoDAO = new InscricaoDAO();		
		List<Inscricao> inscricoes = inscricaoDAO.getListaInscricao();
		if (inscricoes.size() == 0)
			System.out.println("Não há inscrição cadastrada no sistema.");
		else{
			System.out.println("Imprimindo Inscrição(ões):");	
			for (Inscricao a : inscricoes) {
				System.out.println(a);
			}
			System.out.println("\n");
		}
	}

}
