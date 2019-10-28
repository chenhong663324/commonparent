package edu.hstc.roast.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	//private String datePattern = "yyyy-MM-dd HH:mm:ss";
	private String datePattern = "yyyy-MM-dd";

	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf=new SimpleDateFormat(datePattern);
		Date date=null;
		try {
			date=sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
