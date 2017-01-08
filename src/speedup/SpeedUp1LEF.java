package speedup;

import binaryTreeUtils.BTree;
import binaryTreeUtils.Node;
import hwj1_LEF.BTA_LEF;

public class SpeedUp1LEF implements SpeedUp {

	@Override
	public double execute(int depth, String treeType) throws InterruptedException{
		System.out.println("WARM UP");
		Thread.sleep(1000);

		double startTimeParallel, stopTimeParallel, startTimeSerial, stopTimeSerial;
		BTree bt = new BTree(depth, treeType);
		Node root = bt.getRoot();
		BTA_LEF bta = new BTA_LEF();

		startTimeParallel = System.nanoTime();
		bta.computeOnerousSum(root);
		stopTimeParallel = System.nanoTime();

		System.out.println("Parallel Execution Time: " + ((stopTimeParallel - startTimeParallel) / Math.pow(10, 9)));

		startTimeSerial = System.nanoTime();
		bta.computeOnerousSumMonoThread(root);
		stopTimeSerial = System.nanoTime();

		System.out.println("Serial Execution Time:   " + ((stopTimeSerial - startTimeSerial) / Math.pow(10, 9)));

		System.out.println("Speed Up: " + ((stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel)));
		
		return (stopTimeSerial - startTimeSerial) / (stopTimeParallel - startTimeParallel);
	}


}
