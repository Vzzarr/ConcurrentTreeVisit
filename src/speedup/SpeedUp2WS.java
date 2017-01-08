package speedup;

import binaryTreeUtils.BTree;
import binaryTreeUtils.Node;
import hwj2_WS.BTA_WS;

public class SpeedUp2WS implements SpeedUp {

	@Override
	public double execute(int depth, String treeType) throws InterruptedException {
		System.out.println("WARM UP");
		Thread.sleep(1000);

		double startTimeParallel, stopTimeParallel, startTimeSerial, stopTimeSerial;
		int numElements;
		BTree bt = new BTree(depth, treeType);
		if(treeType == "complete")
			numElements = (int) Math.log(Math.pow(2, depth + 1) - 1);
		else numElements = depth;
		Node root = bt.getRoot();
		BTA_WS btaws = new BTA_WS(numElements);

		startTimeParallel = System.nanoTime();
		btaws.computeOnerousSum(root);
		stopTimeParallel = System.nanoTime();

		System.out.println("Parallel Execution Time: " + ((stopTimeParallel - startTimeParallel) / Math.pow(10, 9)));

		startTimeSerial = System.nanoTime();
		btaws.computeOnerousSumMonoThread(root);
		stopTimeSerial = System.nanoTime();

		System.out.println("Serial Execution Time:   " + ((stopTimeSerial - startTimeSerial) / Math.pow(10, 9)));

		System.out.println("Speed Up: " + ((stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel)));
		
		return (stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel);
	}

}
