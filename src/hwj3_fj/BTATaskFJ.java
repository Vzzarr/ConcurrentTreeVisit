package hwj3_fj;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveTask;

import binaryTreeUtils.Node;
import hwj1_LEF.BTATaskLEF;

public class BTATaskFJ extends RecursiveTask<Integer> {

    public static final int THRESHOLD = 5;

	private BlockingQueue<Node> buffer;
	
	public BTATaskFJ(BlockingQueue<Node> buf) {
		buffer = buf;
	}
	@Override
	protected Integer compute() {
		while(!buffer.isEmpty()){
			if(buffer.size() < THRESHOLD){
				try {
					return new BTATaskLEF(buffer, null).call().intValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				BlockingQueue<Node> l = new LinkedBlockingQueue<Node>();
				BlockingQueue<Node> r;
				for(int i = 0; i < (buffer.size() / 2); i++)
					try {
						l.put(buffer.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				r = buffer;
				BTATaskFJ left = new BTATaskFJ(l);
				left.fork();
				BTATaskFJ right = new BTATaskFJ(r);
				return left.join() + right.compute();
			}
		}
		
		return null;
	}

}
