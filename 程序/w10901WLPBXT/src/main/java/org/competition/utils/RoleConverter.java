package org.competition.utils;
import org.competition.model.Role;import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class RoleConverter implements Converter<String, Role>{
	@Override
	public Role convert(String value) {
		Role role =null;
		role = new Role();
		role.setId(Integer.parseInt(value));
		return role;
	}
}
