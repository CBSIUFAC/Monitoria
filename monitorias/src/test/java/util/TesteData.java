package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteData {
	
	public static void main( String[] args) {
	
		Date data = new Date();  
		SimpleDateFormat f = new SimpleDateFormat("yyyy");  
		int a = Integer.parseInt(f.format(data));  
		System.out.println(a);
		
	}

}
