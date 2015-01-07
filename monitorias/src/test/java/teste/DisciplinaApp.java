package teste;

import java.util.List;

import DAO.DisciplinaDAO;
import entity.Disciplina;

public class DisciplinaApp {
public static void main (String[] args ){
		
		DisciplinaApp disciplinaApp = new DisciplinaApp();
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
//		Disciplina d = disciplinaDAO.getDisciplina(1191);
		

		List<Disciplina> lista = disciplinaDAO.getListaDisciplina();
	//	System.out.println(lista.get(0));
		disciplinaApp.imprime();
		
	}
	
	public void imprime(){

		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();		
		List<Disciplina> disciplinas = disciplinaDAO.getListaDisciplina();
		
		if (disciplinas == null)
			System.out.println("disciplinas nulo");
		else
		if (disciplinas.size() == 0)
			System.out.println("Não há disciplina cadastrada no sistema.");
		else{
			System.out.println("Imprimindo Disciplina(s):");
			int a = 1;
			for (Disciplina d : disciplinas) {
				if ( a == 10)
					break;
				System.out.println(d);
				a++;
			}
			System.out.println("\n");
		}
	}
}
