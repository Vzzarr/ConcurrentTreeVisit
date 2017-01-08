package speedup;

import binaryTreeUtils.BTree;
import binaryTreeUtils.Node;
import hwj3_fj.BTA_FJ;

public class SpeedUp3FJ implements SpeedUp {

	@Override
	public double execute(int depth, String treeType) throws InterruptedException{
		System.out.println("WARM UP");
		Thread.sleep(1000);

		double startTimeParallel, stopTimeParallel, startTimeSerial, stopTimeSerial;
		BTree bt = new BTree(depth, treeType);
		Node root = bt.getRoot();
		BTA_FJ btar = new BTA_FJ();

		startTimeParallel = System.nanoTime();
		btar.computeOnerousSum(root);
		stopTimeParallel = System.nanoTime();

		System.out.println("Parallel Execution Time: " + ((stopTimeParallel - startTimeParallel) / Math.pow(10, 9)));

		startTimeSerial = System.nanoTime();
		btar.computeOnerousSumMonoThread(root);
		stopTimeSerial = System.nanoTime();

		System.out.println("Serial Execution Time:   " + ((stopTimeSerial - startTimeSerial) / Math.pow(10, 9)));

		System.out.println("Speed Up: " + ((stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel)));
		
		return (stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel);
	}

}
