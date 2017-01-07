package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import binaryTreeUtils.*;
import hwj1_LEF.BTA_BI;
import speedup.SpeedUpLEF;

public class HWJ1_test {

	BTree btreeC;
	BTree btreeR;
	BTree btreeHU;

	BTA_BI bta;

	@Before
	public void initC() {
		btreeC = new BTree(5, "complete");
		btreeR = new BTree(2, "random");
		btreeHU = new BTree(5, "heavy unbalanced");
	}

	//	@Before
	//	public void initU() {
	//	}
	//
	//	@Before
	//	public void initHU() {
	//	}

	@Before
	public void initOnerousSum() {
		bta = new BTA_BI();
	}

	@Test
	public void testC() {
		assertEquals(1, btreeC.getRoot().getSx().getDx().getSx().getDx().getValue());
	}

	@Test
	public void testR() {
		if(btreeR.getRoot().getSx() != null)
			assertEquals(1, btreeR.getRoot().getSx().getValue());
		else
			assertEquals(1, btreeR.getRoot().getDx().getValue());
	}

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
		BTA_BI bta = new BTA_BI();
		assertEquals(57, bta.computeOnerousSum(btreeC.getRoot()));

	}

	@Test
	public void testSpeedUp() {
		assertTrue(1 < new SpeedUpLEF().execute(11, "complete"));
	}


}
