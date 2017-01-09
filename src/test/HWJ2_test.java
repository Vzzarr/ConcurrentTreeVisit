package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import binaryTreeUtils.BTree;
import hwj2_WS.BTA_WS;
import speedup.SpeedUp2WS;

public class HWJ2_test {

	BTree btreeC;

	BTA_WS btal;

	@Before
	public void initC() {
		btreeC = new BTree(5, "complete");
	}


	@Before
	public void initOnerousSum() {
		btal = new BTA_WS(500);
	}


	@Test
	public void testOnerousSum() {
		assertEquals(57, btal.computeOnerousSum(btreeC.getRoot()));

	}
}
