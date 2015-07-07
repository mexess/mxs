package com.bionic.edu;

import java.sql.Time;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.bionic.edu.TimeConverter")
public class TimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {
		Time time = Time.valueOf(value);
		return time;
		} catch (Exception e) {
			FacesMessage msg =
					new FacesMessage("Time Conversion error.",
							"Invalid Time format.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ConverterException(msg);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
