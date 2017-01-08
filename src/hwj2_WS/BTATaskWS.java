package hwj2_WS;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import binaryTreeUtils.Node;

public class BTATaskWS implements Callable<Integer> {

	private BlockingQueue<Node> buffer;
	CyclicBarrier barrierDeque;
	private BlockingQueue<ArrayDeque<Node>> listDeque;
	
	private Node head, head_sx, head_dx;
	private ArrayDeque<Node> deq;


	public BTATaskWS(BlockingQueue<Node> buf, CyclicBarrier barrierDeq, BlockingQueue<ArrayDeque<Node>> listDeq) {
		buffer = buf;
		barrierDeque = barrierDeq;
		listDeque = listDeq;
	}

	@Override
	public Integer call() throws Exception {
		head = null;
		
		/*viene rimosso e inserito il singolo deq per evitare che più thread prendano lo stesso deq*/
		deq = listDeque.take();	
		barrierDeque.await();
		listDeque.put(deq);

		/*il primo thread che riesce a superare questa condizione, inzializza la propria deque; 
		gli altri faranno work steaing*/		
		int val = takeFirst();

		boolean existsDequeNotEmpty = true;
		while(existsDequeNotEmpty){
			if(!deq.isEmpty()){
				head = deq.pop();
				head_sx = head.getSx();
				head_dx = head.getDx();
				val += head.getValue();
//				System.out.println(head.getValue());

				if(head_sx != null)
					deq.push(head_sx);
				if(head_dx != null)
					deq.push(head_dx);
				existsDequeNotEmpty = true;
			}
			else	//valuta se tutti i deque siano vuoti, in caso affermativo si esce dal while
				existsDequeNotEmpty = workStealing();
		}
		return val;
	}
	
	/**
	 * il buffer conterrà il solo valore root dell'albero; il primo thread che riesce ad aggiudicarselo
	 * lo inserirà nel proprio deque, gli altri non inseriscono nulla e più avanti saranno costretti
	 * a fare Work Stealing. Il buffer iniziale non avrà più altre funzionalità
	 * @return
	 * @throws InterruptedException
	 */
	private int takeFirst() throws InterruptedException{
		int v = 0;
		if(!buffer.isEmpty()){
			head = buffer.poll();
			if(head != null){
				v = head.getValue();
//				System.out.println(head.getValue());

				head_sx = head.getSx();
				head_dx = head.getDx();

				if(head_sx != null)
					deq.push(head_sx);
				if(head_dx != null)
					deq.push(head_dx);
			}
		}
		return v;
	}
	
	/**
	 * richiamata quando la propria deque è vuota, si prova a "rubare" dalle altre deque
	 * @return true se è riuscita a rubare da altre deque, false altrimenti
	 */
	private boolean workStealing(){
		for(ArrayDeque<Node> d : listDeque)
			if(d != deq && !d.isEmpty()){
				Node n = d.pollFirst();
				if(n != null)
					deq.push(n);
				return true;
			}
		return false;
	}
}
