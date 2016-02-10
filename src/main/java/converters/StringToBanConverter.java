package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.BanService;
import domain.Ban;

@Component
@Transactional
public class StringToBanConverter implements Converter<String,Ban>{
	@Autowired
	private BanService commentService;
	
	@Override
	public Ban convert(String arg0) {
	return commentService.findOne(Integer.valueOf(arg0));
	}
	}


