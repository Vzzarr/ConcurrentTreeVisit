package hwj4_si;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import binaryTreeUtils.Node;

public class BTA_Spliterator implements Spliterator<Node>{

	private final List<Node> array;
	private int origin;
	private int fence;
	
	public BTA_Spliterator(List<Node> array, int origin, int fence) {
		this.array = array;
		this.origin = origin;
		this.fence = fence;
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super Node> action) {
		if(origin < fence){
			action.accept((Node) array.get(origin));
			origin++;
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<Node> trySplit() {
		int orig = origin;
		int mid = (orig + fence) / 2;
		if(orig < mid){
			origin = mid;
			return new BTA_Spliterator(array, origin, mid);
		}else	//too small to split
			return null;
	}

	@Override
	public long estimateSize() {
		return (long)(fence - origin);
	}

	@Override
	public int characteristics() {
		// TODO Auto-generated method stub
		return 0;
	}
}
