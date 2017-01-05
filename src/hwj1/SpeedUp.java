package hwj1;

import binaryTree.BTree;
import binaryTree.Node;

public class SpeedUp {

	public double execute(int depth, String treeType){
		double startTimeParallel, stopTimeParallel, startTimeSerial, stopTimeSerial;
		BTree bt = new BTree(depth, treeType);
		Node root = bt.getRoot();
		BTA bta = new BTA();

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
	
	public static void main(String[] args) {
//		execute(15, "complete");
//		System.out.println("\n");
//		execute(30, "random");
//		System.out.println("\n");
//		execute(30, "heavy unbalanced");
		
	}

}
