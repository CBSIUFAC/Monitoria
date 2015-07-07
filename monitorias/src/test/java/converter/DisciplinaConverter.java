package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import DAO.DisciplinaDAO;
import entity.Disciplina;

@FacesConverter("disciplinaConverter")
public class DisciplinaConverter implements Converter { 

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
			try {
				DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				Integer id = Integer.parseInt(value);
				Disciplina disciplina = disciplinaDAO.getDisciplina(id);

				//Disciplina disciplina = (Disciplina) fc.getExternalContext().getApplicationMap().get("disciplina");
				System.out.println((fc.getExternalContext()));
				System.out.println("FacesContext: "+fc);
				System.out.println("UIComponent "+uic);
				System.out.println("Value "+value);

				System.out.println("disciplina é nulo?"+(disciplina==null?"nulo":"não nulo"));
				return disciplina;

			} catch(Exception e) {
				System.out.println("Problema no disciplinaConverter.!");
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Not a valid theme."));
			}
		}
		else 
			return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
			Disciplina c = new Disciplina();
			c = (Disciplina) object;
			return ""+c.getIdDisciplina();
		}
		else
			return null;
	}   
}    
