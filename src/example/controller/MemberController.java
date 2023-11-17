package example.controller;

import java.util.Scanner;

import example.dao.MemberDao;
import example.dto.Member;
import example.util.Util;

public class MemberController extends Controller {
	private MemberDao memberDao;
	private Scanner sc;
	
	public MemberController(Scanner sc) {
		this.memberDao = new MemberDao();
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

		String loginId = null;
		while (true) {
			System.out.printf("아이디: ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력정보입니다.");
				continue;
			}

			if (this.memberDao.isLoginIdDupChk(loginId)) {
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

		Member member = new Member(this.memberDao.getLastId(), Util.getDateStr(), loginId, loginPw, userName);
		this.memberDao.doJoin(member);

		System.out.println("회원가입 되었습니다.");
	}

	// 로그인
	private void doLogin() {

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

		Member member = this.memberDao.getMemberByLoginId(loginId);

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

		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}
	

	// 테스트용 유저 생성 메서드
	@Override
	public void makeTestData() {
		for (int i = 0; i < 3; i++) {
			this.memberDao.doJoin(
					new Member(i + 1, Util.getDateStr(), "test" + (i + 1), "test" + (i + 1), "user" + (i + 1)));
		}

		System.out.println("테스트용 회원데이터가 생성되었습니다.(" + this.memberDao.getMembersSize() + "개)");
	}
}
