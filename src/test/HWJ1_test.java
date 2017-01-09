package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import binaryTreeUtils.*;
import hwj1_LEF.BTA_LEF;
import speedup.SpeedUp1LEF;

public class HWJ1_test {

	BTree btreeC;
	BTree btreeR;
	BTree btreeHU;

	BTA_LEF bta;

	@Before
	public void initC() {
		btreeC = new BTree(5, "complete");
		btreeR = new BTree(2, "random");
		btreeHU = new BTree(5, "heavy unbalanced");
	}


	@Before
	public void initOnerousSum() {
		bta = new BTA_LEF();
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
		BTA_LEF bta = new BTA_LEF();
		assertEquals(57, bta.computeOnerousSum(btreeC.getRoot()));
	}
	
	@Test
	public void testOnerousSumMonoThread() {
		BTA_LEF bta = new BTA_LEF();
		assertEquals(57, bta.computeOnerousSumMonoThread(btreeC.getRoot()));
	}


	
	@Test
	public void testSpeedUp() throws InterruptedException {
		assertTrue(1 < new SpeedUp1LEF().execute(15, "complete"));
	}


}
