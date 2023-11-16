package example.controller;

import java.util.Scanner;

import example.dto.Member;

public abstract class Controller {
	public Scanner sc;
	
	public static Member loginedMember;
	
	public boolean isLogined() {
		return loginedMember != null;
	}

	public abstract void doAction(String methodName, String cmd);
	
	public abstract void makeTestData();
	
}
