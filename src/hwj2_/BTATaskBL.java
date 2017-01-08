package hwj2_;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import binaryTreeUtils.Node;

public class BTATaskBL implements Callable<Integer> {

	private BlockingQueue<Node> buffer;
	CyclicBarrier barrierDeque;
	private BlockingQueue<ArrayDeque<Node>> listDeque;

	public BTATaskBL(BlockingQueue<Node> buf, CyclicBarrier barrierDeq, BlockingQueue<ArrayDeque<Node>> listDeq) {
		buffer = buf;
		barrierDeque = barrierDeq;
		listDeque = listDeq;
	}

	@Override
	public Integer call() throws Exception {
		int val = 0;
		Node head, head_sx, head_dx;
		head = null;
		/*viene rimosso e inserito il singolo deq per evitare che più thread prendano lo stesso deq*/
		ArrayDeque<Node> deq = listDeque.take();	
		barrierDeque.await();
		listDeque.put(deq);

		/*il primo thread che riesce a superare questa condizione, inzializza la propria deque; 
		gli altri faranno work steaing*/
		if(!buffer.isEmpty()){
			head = buffer.poll(1, TimeUnit.SECONDS);
			//	Thread.sleep(1000);
			if(head != null){
				val += head.getValue();
				System.out.println(head.getValue());

				head_sx = head.getSx();
				head_dx = head.getDx();

				if(head_sx != null)
					deq.push(head_sx);
				if(head_dx != null)
					deq.push(head_dx);
			}
		}

		/*-----------------------------------*/
		boolean condition = true;
		while(condition){
			condition = false;
			if(!deq.isEmpty()){
				head = deq.pop();
				head_sx = head.getSx();
				head_dx = head.getDx();
				val += head.getValue();
				System.out.println(head.getValue());

				if(head_sx != null)
					deq.push(head_sx);
				if(head_dx != null)
					deq.push(head_dx);
			}
			else {
				for (ArrayDeque<Node> d : listDeque)
					if(d != deq && !d.isEmpty()){
						Node n = d.pollFirst();
						if(n != null)
							deq.push(n);
						break;
					}
			}
			/*valuta se tutti i deque siano vuoti, in caso affermativo si esce dal while*/
			for (ArrayDeque<Node> d : listDeque) 
				condition = condition || (!d.isEmpty());
		}
		return val;
	}
	
	private void takeFirst(){
		
	}
}
