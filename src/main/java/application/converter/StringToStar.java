package application.converter;

import org.springframework.core.convert.converter.Converter;

import application.domain.Star;


public class StringToStar implements Converter <String,Star>{

	@Override
	public Star convert(String source) {
		//to do
		return null;
	}


}
