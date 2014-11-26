package Teste;

import java.util.Date;
import java.util.List;

import DAO.EditalDAO;
import entity.Edital;

public class EditalApp {

public static void main(String[] args) {
		
		EditalDAO editalDAO = new EditalDAO();
		Edital edital = new Edital();
		EditalApp editalApp = new EditalApp();
		
		
		edital.setIdEdital(3);
		edital.setDataInscricao( new Date ("26/11/2014"));
		edital.setDataResultado( new Date ("26/03/2015"));
		edital.setSrcPDF("http://imagens.lucas.com.br/");
		edital.setTitulo("Edital de monitoria do CCET");
		edital.setTotalVagas(6);
		
		editalDAO.inserirEdital(edital);
		editalApp.imprime();
		
		edital.setDataInscricao( new Date ("2014/11/26"));
		edital.setDataResultado( new Date ("2015/03/26"));
		edital.setSrcPDF("http://url.esta.errada/");
		edital.setTitulo("Edital de monitoria do CENTRO DE CIÊNCIAS EXATAS E TECNOLÓGICAS");
		edital.setTotalVagas(12);
		
		editalDAO.atualizarEdital(edital);
	
		editalApp.imprime();

		editalDAO.deletarEdital(edital);
		edital.setIdEdital(2);
		editalDAO.deletarEdital(edital);
		
		editalApp.imprime();
		
	}
	
	public void imprime(){
		EditalDAO editalDAO = new EditalDAO();		
		List<Edital> editais = editalDAO.getListaEdital();
		if (editais.size() == 0)
			System.out.println("Não há edital cadastrado no banco de dados.");
		else{
			System.out.println("Imprimindo Edital:");	
			for (Edital a : editais) {
				System.out.println(a);
			}
		
			System.out.println("\n");
		}
	}

}
