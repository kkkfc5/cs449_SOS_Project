package cs449_JUnitTestTEST;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import cs449_JUnitTest.*;

public class main {

	@Test
	public void TESTER1() {
		var t = new MainUnitTest();
		t.FuncOne(1);
	}
	
	@Test
	public void TESTER2() {
		var t = new MainUnitTest();
		t.FuncTwo(0);
	}
	
	@Test
	public void TESTER3_1() {
		var t = new MainUnitTest();
		t.FuncThree(2);
	}
	
	@Test
	public void TESTER3_2() {
		var t = new MainUnitTest();
		t.FuncThree(0);
	}

