package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Member;
import example.util.Util;

public class MemberController extends Controller {
	private int memberId;
	private List<Member> members;

	public MemberController(Scanner sc) {
		this.memberId = 0;
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	@Override
	public void doAction(String methodName, String cmd) {
		
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	// 회원가입
	private void doJoin() {

		if (isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}

		String loginId = null;
		while (true) {
			System.out.printf("아이디: ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력정보입니다.");
				continue;
			}

			if (isLoginIdDupChk(loginId)) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}

			System.out.printf("%s은(는) 사용가능한 아이디입니다.\n", loginId);
			break;
		}

		String loginPw = null;
		while (true) {
			System.out.printf("비밀번호: ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력정보입니다.");
				continue;
			}

			System.out.printf("비밀번호 확인: ");
			String loginPwChk = sc.nextLine().trim();

			if (!loginPw.equals(loginPwChk)) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		String userName = null;
		while (true) {
			System.out.printf("이름: ");
			userName = sc.nextLine().trim();

			if (userName.length() == 0) {
				System.out.println("이름은 필수 입력정보입니다.");
				continue;
			}
			break;
		}

		Member member = new Member(++memberId, Util.getDateStr(), loginId, loginPw, userName);
		members.add(member);

		System.out.println("회원가입 되었습니다.");
	}

	// 로그인
	private void doLogin() {

		if (isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}

		String loginId = null;
		while (true) {
			System.out.printf("아이디: ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			break;
		}

		String loginPw = null;
		while (true) {
			System.out.printf("비밀번호: ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			break;
		}

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println(loginId + "은(는) 존재하지 않는 아이디입니다.");
			return;
		}

		if (!member.loginPw.equals(loginPw)) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}

		loginedMember = member;

		System.out.println(member.name + "님 환영합니다.");
	}

	// 로그아웃
	private void doLogout() {

		if (!isLogined()) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	// 해당 member 객체 가져오기
	private Member getMemberByLoginId(String loginId) {
		for (Member member : this.members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean isLoginIdDupChk(String loginId) {
		Member member = getMemberByLoginId(loginId);

		if (member != null) {
			return true;
		}

		return false;
	}

	// 테스트용 유저 생성 메서드
	@Override
	public void makeTestData() {
		for (int i = 0; i < 3; i++) {
			this.members.add(
					new Member(++memberId, Util.getDateStr(), "test" + (i + 1), "test" + (i + 1), "user" + (i + 1)));
		}

		System.out.println("테스트용 유저 생성 .(" + this.members.size() + "개)");
	}
}
