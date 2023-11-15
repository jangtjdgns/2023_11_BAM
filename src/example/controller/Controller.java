package example.controller;

import java.util.Scanner;

public abstract class Controller {
	public Scanner sc;

	// 추상클래스
	public abstract void doAction(String methodName, String cmd);
	
	public abstract void makeTestData();
}
