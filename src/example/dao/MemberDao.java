package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.dto.Member;

public class MemberDao extends Dao {
	private List<Member> members;

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
}
