package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import DAO.EditalDAO;
import entity.Edital;

@FacesConverter("editalConverter")
public class EditalConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
			try {
				EditalDAO editalDAO = new EditalDAO();
				Integer id = Integer.parseInt(value);
				Edital edital = editalDAO.getEdital(id);
				return edital;

			} catch(Exception e) {
				System.err.println("Problema no editalConverter.!");
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Not a valid theme."));
			}
		}
		else {
			System.out.println("Nulo(1)");
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
			Edital e = new Edital();
			e = (Edital) object;
			return ""+e.getIdEdital();
		}
		else { 
			System.out.println("Nulo(2)");
			return null;
		}
	}   
}     