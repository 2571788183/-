package org.competition.utils;
import org.competition.model.Member;import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class MemberConverter implements Converter<String, Member>{
	@Override
	public Member convert(String value) {
		Member member =null;
		member = new Member();
		member.setId(Integer.parseInt(value));
		return member;
	}
}
