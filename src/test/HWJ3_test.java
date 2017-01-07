package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import binaryTreeUtils.BTree;
import hwj1_LEF.BTA_BI;
import hwj3_fj.BTARecursive;
import speedup.SpeedUpLEF;

public class HWJ3_test {

	BTree btreeC;
	BTree btreeR;
	BTree btreeHU;

	BTA_BI bta;

	@Before
	public void initC() {
		btreeC = new BTree(5, "complete");
		//		btreeR = new BTree(2, "random");
		//		btreeHU = new BTree(5, "heavy unbalanced");
	}
	@Test
	public void testOnerousSumRecursive() {
		BTARecursive btar = new BTARecursive();
		assertEquals(57, btar.computeOnerousSum(btreeC.getRoot()));

	}

	@Test
	public void testSpeedUp() {
		assertTrue(1 < new SpeedUpLEF().execute(15, "complete"));
	}

}
