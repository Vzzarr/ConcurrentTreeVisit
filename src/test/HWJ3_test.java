package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import binaryTreeUtils.BTree;
import hwj1_LEF.BTA_LEF;
import hwj3_fj.BTA_FJ;
import speedup.SpeedUp1LEF;

public class HWJ3_test {

	BTree btreeC;
	BTree btreeR;
	BTree btreeHU;

	BTA_LEF bta;

	@Before
	public void initC() {
		btreeC = new BTree(5, "complete");
	}
	@Test
	public void testOnerousSumRecursive() {
		BTA_FJ btar = new BTA_FJ();
		assertEquals(57, btar.computeOnerousSum(btreeC.getRoot()));

	}

	@Test
	public void testSpeedUp() throws InterruptedException {
		assertTrue(1 < new SpeedUp1LEF().execute(15, "complete"));
	}

}
