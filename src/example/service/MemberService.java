package example.service;

import example.container.Container;
import example.dao.MemberDao;
import example.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	
	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}

	public int getLastId() {
		return memberDao.getLastId();
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public void doJoin(Member member) {
		memberDao.doJoin(member);
	}

	public int getMembersSize() {
		return memberDao.getMembersSize();
	}

}
