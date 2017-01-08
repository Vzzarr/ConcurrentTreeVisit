package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import binaryTreeUtils.BTree;
import hwj2_.BTA_BL;

public class HWJ2_test {

	BTree btreeC;

	BTA_BL btal;

	@Before
	public void initC() {
		btreeC = new BTree(5, "complete");
	}


	@Before
	public void initOnerousSum() {
		btal = new BTA_BL(500);
	}


	@Test
	public void testOnerousSum() {
		assertEquals(57, btal.computeOnerousSum(btreeC.getRoot()));

	}

//	@Test
//	public void testSpeedUp() {
//		assertTrue(1 < new SpeedUpLEF().execute(11, "complete"));
//	}


}
