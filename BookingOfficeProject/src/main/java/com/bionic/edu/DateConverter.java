package com.bionic.edu;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.bionic.edu.DateConverter")
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


		Date date = format.parse(arg2);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
		} catch (Exception e) {
			FacesMessage msg =
					new FacesMessage("Time Conversion error.",
							"Invalid Time format.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ConverterException(msg);
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(arg2);

		return result;
	}

}
