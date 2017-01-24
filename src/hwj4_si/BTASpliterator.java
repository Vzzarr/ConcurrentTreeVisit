package hwj4_si;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

import binaryTreeUtils.Node;

public class BTASpliterator implements Spliterator<Node>{

	private final LinkedList<Node> buffer;
	private Node node;

	public BTASpliterator(LinkedList<Node> buffer, Node node) {
		this.buffer = buffer;
		this.node = node;
	}

	/**
	 * estrae il primo nodo, ne esegue l'action 
	 * e lo mette come nodo corrente, permettendo 
	 * di iterare così sulla lista
	 */
	@Override
	public boolean tryAdvance(Consumer<? super Node> action) {
		if(this.node != null)
			try {
				this.node = this.buffer.removeFirst();
				action.accept(this.node);
				return true;
			} catch (NoSuchElementException e) { this.node = null; }
		return false;
	}

	/**
	 * permette di iterare, splittando la lista in due quando un nodo presenta due figli;
	 * in particolare il task corrente continua ad analizzare il figlio sinistro,
	 * mentre il nuovo task figlio visiterà il figlio destro
	 */
	@Override
	public Spliterator<Node> trySplit() {
		if(this.node != null){
			if(this.node.getSx() != null && this.node.getDx() != null){
				Node dx = this.node.getDx();
				LinkedList<Node> list = new LinkedList<>();
				list.add(dx);
				this.buffer.add(this.node.getSx());
				this.node = this.node.getSx();
				return new BTASpliterator(list, dx);
			} 
			else if(this.node.getSx() != null){
				this.buffer.add(this.node.getSx());
				this.node = this.node.getSx();
				return trySplit();
			}
			else if(this.node.getDx() != null){
				this.buffer.add(this.node.getDx());
				this.node = this.node.getDx();
				return trySplit();
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		return Long.MAX_VALUE;
	}

	@Override
	public int characteristics() {
		// TODO Auto-generated method stub
		return 0;
	}
}
