package org.competition.dao;
import java.util.List;
import org.competition.model.Member;
import org.competition.model.Search;
public interface MemberDao {
public void save(Member member);
public void delete(Member member);
public void update(Member member);
public Member getById(int id);
public List<Member> query(Search search);
public Member getByUserId(int id);
}

