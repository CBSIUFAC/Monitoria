package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import DAO.InscricaoDAO;
import entity.Inscricao;

@FacesConverter("inscricaoConverter")
public class InscricaoConverter implements Converter { 

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
			try {
				InscricaoDAO inscricaoDAO = new InscricaoDAO();
				Integer id = Integer.parseInt(value);
				Inscricao inscricao = inscricaoDAO.getInscricao(id);

				//Inscricao inscricao = (Inscricao) fc.getExternalContext().getApplicationMap().get("inscricao");
				System.out.println((fc.getExternalContext()));
				System.out.println("FacesContext: "+fc);
				System.out.println("UIComponent "+uic);
				System.out.println("Value "+value);

				System.out.println("inscricao é nulo?"+(inscricao==null?"nulo":"não nulo"));
				return inscricao;

			} catch(Exception e) {
				System.out.println("Problema no inscricaoConverter.!");
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Not a valid theme."));
			}
		}
		else 
			return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
			Inscricao c = new Inscricao();
			c = (Inscricao) object;
			return ""+c.getIdInscricao();
		}
		else
			return null;
	}   
}    
