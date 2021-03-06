package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import binaryTreeUtils.BTree;
import hwj4_si.BTA_SI;
import speedup.SpeedUp1LEF;
import speedup.SpeedUp4SI;

public class HWJ4_test {

	BTree btreeC;
	BTree btreeHU;

	BTA_SI bta;

	@Before
	public void initC() {
		btreeC = new BTree(5, "complete");
		btreeHU = new BTree(5, "heavy unbalanced");

	}
	
	@Test
	public void testOnerousSumRecursive() {
		BTA_SI bta = new BTA_SI();
		assertEquals(57, bta.computeOnerousSum(btreeC.getRoot()));
		assertEquals(15, bta.computeOnerousSum(btreeHU.getRoot()));

	}

	@Test
	public void testSpeedUp() throws InterruptedException {
		assertTrue(1 < new SpeedUp4SI().execute(15, "complete"));
	}
}
