package binaryTree;

public class BTree {

	private Node root;
	
	/**
	 * generates a Binary Tree according to @param type {@value}
	 * @param depth = specifies the depth of the Binary Tree
	 * @param type = 
	 * "complete" --> (complete Binary Tree) || 
	 * "random" --> (random unbalanded Binary Tree) || 
	 * "heavy unbalanced" --> (Binary Tree with only left children or only right children)
	 */
	public BTree(int depth, String type){
		if(type == "complete")
			root = generateCompleteBTree(depth);
		if(type == "random")
			root = generateRandomBTree(depth);
		if(type == "heavy unbalanced")
			root = generateHeavyUnbalancedBTree(depth, Math.random());
	}

	public Node generateCompleteBTree(int depth){
		if (depth <= 0) return null;
	    Node currentNode = new Node() {
			
			@Override
			public int getValue() {
				FakeProcessor fake = new FakeProcessor(1000);
				return fake.onerousFunction(depth);
			}
			
			@Override
			public Node getSx() {
				return generateCompleteBTree(depth-1);
			}
			
			@Override
			public Node getDx() {
				return generateCompleteBTree(depth-1);
			}
		};
	    return currentNode;
	}
	
	private Node generateRandomBTree(int depth) {
		if(depth <= 0) return null;
		double r = Math.random();
		Node currentNode = new Node() {
			
			@Override
			public int getValue() {
				FakeProcessor fake = new FakeProcessor(1000);
				return fake.onerousFunction(depth);
			}
			
			@Override
			public Node getSx() {
				if(r < 0.7)
					return generateRandomBTree(depth - 1);
				return null;
			}
			
			@Override
			public Node getDx() {
				if(r < 0.4 || r >= 0.7)
					return generateRandomBTree(depth - 1);
				return null;
			}
		};
		return currentNode;
	}
	
	private Node generateHeavyUnbalancedBTree(int depth, double r) {
		if(depth <= 0) return null;
		Node currentNode = new Node() {
			
			@Override
			public int getValue() {
				FakeProcessor fake = new FakeProcessor(1000);
				return fake.onerousFunction(depth);
			}
			
			@Override
			public Node getSx() {
				if(r > 0.5)
					return generateHeavyUnbalancedBTree(depth - 1, r);
				return null;
			}
			
			@Override
			public Node getDx() {
				if(r <= 0.5)
					return generateHeavyUnbalancedBTree(depth - 1, r);
				return null;
			}
		};
		return currentNode;
	}

	
	
	
	public Node getRoot(){
		return this.root;
	}

	
}
