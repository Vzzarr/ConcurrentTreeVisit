package speedup;

import binaryTreeUtils.BTree;
import binaryTreeUtils.Node;
import hwj3_fj.BTA_FJ;
import hwj4_si.BTA_SI;

public class SpeedUp4SI implements SpeedUp {

	@Override
	public double execute(int depth, String treeType) throws InterruptedException{
		System.out.println("WARM UP");
		Thread.sleep(1000);

		double startTimeParallel, stopTimeParallel, startTimeSerial, stopTimeSerial;
		BTree bt = new BTree(depth, treeType);
		Node root = bt.getRoot();
		BTA_SI btasi = new BTA_SI();

		startTimeParallel = System.nanoTime();
		btasi.computeOnerousSum(root);
		stopTimeParallel = System.nanoTime();

		System.out.println("Parallel Execution Time: " + ((stopTimeParallel - startTimeParallel) / Math.pow(10, 9)));

		startTimeSerial = System.nanoTime();
		btasi.computeOnerousSumMonoThread(root);
		stopTimeSerial = System.nanoTime();

		System.out.println("Serial Execution Time:   " + ((stopTimeSerial - startTimeSerial) / Math.pow(10, 9)));

		System.out.println("Speed Up: " + ((stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel)));
		
		return (stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel);
	}

}
