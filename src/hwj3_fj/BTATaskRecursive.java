package hwj3_fj;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RecursiveTask;

import binaryTreeUtils.Node;
import hwj1_LEF.BTATaskBI;

public class BTATaskRecursive extends RecursiveTask<Integer> {

    public static final int THRESHOLD = 5;

	private BlockingQueue<Node> buffer;
	
	public BTATaskRecursive(BlockingQueue<Node> buf) {
		buffer = buf;
	}
	@Override
	protected Integer compute() {
		while(!buffer.isEmpty()){
			if(buffer.size() < THRESHOLD){
				System.out.println("mario");

				try {
					return new BTATaskBI(buffer, null).call().intValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				BlockingQueue<Node> l = null;
				BlockingQueue<Node> r;
				for(int i = 0; i < (buffer.size() /2); i++)
					try {
						l.put(buffer.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				r = buffer;
				BTATaskRecursive left = new BTATaskRecursive(l);
				left.fork();
				BTATaskRecursive right = new BTATaskRecursive(r);
				return left.join() + right.compute();
			}
		}
		
		return null;
	}

}
