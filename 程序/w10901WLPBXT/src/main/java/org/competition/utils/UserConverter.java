package org.competition.utils;
import org.competition.model.User;import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class UserConverter implements Converter<String, User>{
	@Override
	public User convert(String value) {
		User user =null;
		user = new User();
		user.setId(Integer.parseInt(value));
		return user;
	}
}
