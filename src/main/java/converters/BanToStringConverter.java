package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Ban;

@Component
@Transactional
public class BanToStringConverter implements Converter<Ban, String> {
	@Override
	public String convert(Ban arg0) {
	return String.valueOf(arg0.getId());
	}
	}


