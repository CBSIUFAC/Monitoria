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
public class centroConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	CentroDAO centroDAO = new CentroDAO();
            	Integer id = Integer.parseInt(value);
            	Centro centro = centroDAO.getCentro(id);
            	
                //Centro centro = (Centro) fc.getExternalContext().getApplicationMap().get("centro");
                System.out.println((fc.getExternalContext()));
                System.out.println("FacesContext: "+fc);
                System.out.println("UIComponent "+uic);
                System.out.println("Value "+value);

                
               return centro;
               
            } catch(Exception e) {
            	System.out.println("Deu pau!");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
        	Centro c = new Centro();
        	c = (Centro) object;
        	return ""+c.getIdCentro();
           // return String.valueOf(((Centro) object).getIdCentro());
        }
        else {
            return null;
        }
    }   
}     