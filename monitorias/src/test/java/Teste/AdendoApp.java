package Teste;

import java.util.Date;
import java.util.List;

import DAO.AdendoDAO;
import entity.Adendo;
import entity.Edital;

public class AdendoApp {
public static void main (String[] args ){
		
		AdendoDAO adendoDAO = new AdendoDAO();
		Adendo adendo = new Adendo();
		AdendoApp adendoApp = new AdendoApp();
		Edital edital = new Edital();
		edital.setIdEdital(1);
		
		adendo.setIdAdendo(3);
		adendo.setSrcPDF("http3://endereco.do.pdf.do.adendo/");
		adendo.setTitulo("Titulo3 de Teste de um Adendo");
		adendo.setEdital(edital);
		adendoDAO.inserirAdendo(adendo);
	
		adendoApp.imprime();
		
		adendo.setSrcPDF("http4://endereco.do.pdf.do.adendo/");
		adendo.setTitulo("Titulo4 de Teste de um Adendo");
		adendo.setEdital(edital);
		adendoDAO.atualizarAdendo(adendo);
	
		adendoApp.imprime();
		
		adendoDAO.deletarAdendo(adendo);
		
		adendoApp.imprime();
		
	}
	
	public void imprime(){
		AdendoDAO adendoDAO = new AdendoDAO();		
		List<Adendo> adendos = adendoDAO.getListaAdendo();
		if (adendos.size() == 0)
			System.out.println("Não há adendos cadastrados no sistema.");
		else{
			System.out.println("Exibindo adendo(s): ");
			for (Adendo a : adendos) {
				System.out.println(a);
			}
		}
		System.out.println("\n");
	}

}
