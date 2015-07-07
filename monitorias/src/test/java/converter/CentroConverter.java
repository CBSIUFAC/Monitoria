package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import DAO.CentroDAO;
import entity.Centro;

@FacesConverter("centroConverter")
public class CentroConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
			try {
				CentroDAO centroDAO = new CentroDAO();
				Integer id = Integer.parseInt(value);
				Centro centro = centroDAO.getCentro(id);
				return centro;

			} catch(Exception e) {
				System.err.println("Problema no centroConverter.!");
				return null;
			}
		}
		else
			return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
			Centro c = new Centro();
			c = (Centro) object;
			return ""+c.getIdCentro();
		}
		else {
			return null;
		}
	}   
}     