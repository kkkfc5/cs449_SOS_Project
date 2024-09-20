package cs449_JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class MainUnitTest {

	@Test
	public void FuncOne(int a) {
		System.out.println("A: " + a);
		assertEquals(1, a);
	}

	@Test
	public void FuncTwo(int b) {
		System.out.println("B: " + b);
		assertEquals(2, b);
	}
	
	@Test
	public int FuncThree(int c) {
		System.out.println("C: " + c);
		assertTrue(c * 2 == 4);
		return c * 2;
	}
	

}
