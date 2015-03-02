package application.converter;

import org.springframework.core.convert.converter.Converter;

import application.domain.Content;

public class StringToContent implements Converter<String, Content> {

	@Override
	public Content convert(String source) {
		Content content = new Content();
		content.setContent(source);
		return content;
	}
}
