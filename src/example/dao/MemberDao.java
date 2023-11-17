package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.container.Container;
import example.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;
	
	public MemberDao() {
		this.members = new ArrayList<>();
	}
	
	public void doJoin(Member member) {
		this.members.add(member);
	}

	// 해당 member 객체 가져오기
	public Member getMemberByLoginId(String loginId) {
		for (Member member : this.members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public boolean isLoginIdDupChk(String loginId) {
		Member member = getMemberByLoginId(loginId);
		if (member != null) {
			return true;
		}
		return false;
	}

	// members 사이즈 확인용
	public int getMembersSize() {
		return this.members.size();
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public String getWriterName(int memberId) {
		for (Member member : Container.memberDao.getMembers()) {
			if (memberId == member.id) {
				return member.name;
			}
		}
		return null;
	}
}
