package hwj4_si;

import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

import binaryTreeUtils.Node;

public class BTASpliterator implements Spliterator<Node>{

	private final LinkedList<Node> buffer;
	private int origin;
	private int fence;
	
	public BTASpliterator(LinkedList<Node> buffer, int origin, int fence) {
		this.buffer = buffer;
		this.origin = origin;
		this.fence = fence;
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super Node> action) {
		if(this.origin < this.fence){
			Node node = this.buffer.get(this.origin);
			action.accept(node);
			if(node.getSx() != null){
				this.buffer.addLast(node.getSx());
				fence++;
			}
			if(node.getDx() != null){
				this.buffer.addLast(node.getDx());
				fence++;
			}
			origin++;
			return true;
		}
		return false;
		/*if(origin < fence){
			action.accept(extractNode());
			origin++;
			return true;
		}
		return false;*/
	}

	@Override
	public Spliterator<Node> trySplit() {
		int orig = this.origin;
		System.out.println("mario");
		int mid = (orig + this.fence) / 2;
		if(orig < mid){
			fence = mid - 1;
			return new BTASpliterator(buffer, mid, fence);
			
		}else	//too small to split
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
	
//	private Node extractNode() {
//		Node node = buffer.remove(0);
//		Node head_sx = node.getSx();
//		Node head_dx = node.getDx();
//
//		if(head_sx != null)
//			buffer.add(head_sx);
//		if(head_dx != null)
//			buffer.add(head_dx);
//		this.value += node.getValue();
//	}
	
}
