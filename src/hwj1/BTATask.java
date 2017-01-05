package hwj1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import binaryTree.Node;

public class BTATask implements Callable<Integer>{

	BlockingQueue<Node> buffer;
	CyclicBarrier barrier;
	
	public BTATask(BlockingQueue<Node> buf, CyclicBarrier bar) {
		buffer = buf;
		barrier = bar;
	}
	
	@Override
	public Integer call() throws Exception {
		int val = 0;
		while(!buffer.isEmpty()){
			Node head = buffer.take();
//			Thread.sleep(1000);
			Node head_sx = head.getSx();
			Node head_dx = head.getDx();
			if(head_sx != null)
				buffer.put(head_sx);
			if(head_dx != null)
				buffer.put(head_dx);
			val = val + head.getValue();
		}
		barrier.await();
//		System.out.println(val);
		return val;
	}

}
