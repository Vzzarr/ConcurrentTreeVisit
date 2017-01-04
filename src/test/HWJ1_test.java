package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import binaryTree.*;

public class HWJ1_test {

	BTree btreeC;
	BTree btreeU;
	BTree btreeHU;
	
	@Before
	public void initC() {
		btreeC = new BTree(4, "complete");
	}
	
	@Before
	public void initU() {
		btreeU = new BTree(5, "unbalanced");
	}

	@Before
	public void initHU() {
		btreeHU = new BTree(5, "heavy unbalanced");
	}
	
//	@Before
//	public void initOnerousSum() {
//		BTA bta = new BTA();
//	}
	
//	@Test
//	public void testC() {
//		assertEquals(1, btreeC.getRoot().getSx().getDx().getValue());
//
//	}

	@Test
	public void testHU() {
		int i;
		if(btreeHU.getRoot().getSx() == null)
			i = btreeHU.getRoot().getDx().getDx().getDx().getDx().getValue();
		else
			i = btreeHU.getRoot().getSx().getSx().getSx().getSx().getValue();
		assertEquals(1, i);

	}
	
	@Test
	public void testOnerousSum() {
		BTA bta = new BTA();
		//System.out.println(btreeC.getRoot().getValue());
		assertEquals(1, bta.computeOnerousSum(btreeC.getRoot()));

	}
	
//	@After
//	public void clean() {
//		System.out.println("Cleaning after test divide");
//	}


}