package speedup;

import binaryTreeUtils.BTree;
import binaryTreeUtils.Node;
import hwj1_LEF.BTA_BI;

public class SpeedUpLEF extends SpeedUpAbstract {

	@Override
	public double execute(int depth, String treeType){
		double startTimeParallel, stopTimeParallel, startTimeSerial, stopTimeSerial;
		BTree bt = new BTree(depth, treeType);
		Node root = bt.getRoot();
		BTA_BI bta = new BTA_BI();

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
