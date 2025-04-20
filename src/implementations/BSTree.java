package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>
{
	
	BSTreeNode<E> root;
	
	public BSTree() 
	{
		root = null;
	}
	
	public BSTree(E rootValue) 
	{
		root = new BSTreeNode<>(rootValue);
	}

	@Override
	public BSTreeNode<E> getRoot() throws NullPointerException 
	{
		if (root == null) 
		{
			throw new NullPointerException();
		}
		return root;
	}

	@Override
	public int getHeight() 
	{
		return getHeightHelper(root);
	}
	
	private int getHeightHelper(BSTreeNode<E> node) 
	{
		if (node == null) 
		{
			return 0;
		}
		
		int smallerHeight = getHeightHelper(node.smaller);
		int largerHeight = getHeightHelper(node.larger);
		
		return 1 + Math.max(smallerHeight, largerHeight);
	}

	@Override
	public int size() 
	{
		return sizeHelper(root);
	}
	
	private int sizeHelper(BSTreeNode<E> node) 
	{
		if (node == null) 
		{
			return 0;
		}
		
		return 1 + sizeHelper(node.smaller)+ sizeHelper(node.larger); 
	}

	@Override
	public boolean isEmpty() 
	{
		if (root == null) 
		{
			return true;
		}
		
		else 
		{
			return false;
		}
	}

	@Override
	public void clear() 
	{
		root = null;
	}

	@Override
	public boolean contains(E entry) throws NullPointerException 
	{
		if (entry == null) 
		{
			throw new NullPointerException();
		}
		
		return containsHelper(root, entry);
	}
	
	private boolean containsHelper(BSTreeNode<E> node, E entry) 
	{
		if (node == null) 
		{
			return false;
		}
		
		int cmp = entry.compareTo(node.data);
		
		if (cmp == 0) 
		{
			return true;
		}
		
		else if (cmp < 0) 
		{
			return containsHelper(node.smaller, entry);
		}
		
		else
		{
			return containsHelper(node.larger, entry);
		}
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException 
	{
		if (entry == null) 
		{
			throw new NullPointerException();
		}
		
		return searchHelper(root, entry);
	}
	
	private BSTreeNode<E> searchHelper(BSTreeNode<E> node, E entry) 
	{
		if (node == null) 
		{
			return null;
		}
		
		int cmp = entry.compareTo(node.data);
		
		if (cmp == 0) 
		{
			return node;
		}
		
		else if (cmp < 0) 
		{
			return searchHelper(node.smaller, entry);
		}
		
		else
		{
			return searchHelper(node.larger, entry);
		}
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException 
	{
		if (newEntry == null) 
		{
			throw new NullPointerException("Entry cannot be null");
		}
		
		if (root == null) 
		{
			root = new BSTreeNode<E>(newEntry);
		}
		
		
		return addHelper(root, newEntry);
	}
	
	private boolean addHelper(BSTreeNode<E> node, E newEntry) 
	{
		int cmp = newEntry.compareTo(node.data);
		
		if (cmp < 0) 
		{
			if (node.smaller == null) 
			{
				node.smaller = new BSTreeNode<E>(newEntry);
				return true;
			}
			return addHelper(node.smaller, newEntry);
		}
		
		else if (cmp > 0) 
		{
			if (node.larger == null) 
			{
				node.larger = new BSTreeNode<E>(newEntry);
				return true;
			}
			return addHelper(node.larger, newEntry);
		}
		
		else 
		{
			return false;
		}
	}
		
		

	@Override
	public BSTreeNode<E> removeMin() 
	{
		BSTreeNode<E> returnNode;
		
		if (root == null) 
		{
			return null;
		}
		
		if (root.smaller == null) 
		{
			returnNode = root;
			root = root.larger;
			return returnNode;
		}
		
		return removeMinHelper(root);
	}
	
	private BSTreeNode<E> removeMinHelper(BSTreeNode<E> node) 
	{
		BSTreeNode<E> returnNode;
		BSTreeNode<E> currentNode = node.smaller;
		
		if (currentNode.smaller == null) 
		{
			returnNode = currentNode;
			node.smaller = currentNode.larger;
			return returnNode;
		}
		
		return removeMinHelper(currentNode);
	}

	@Override
	public BSTreeNode<E> removeMax() 
	{
		BSTreeNode<E> returnNode;
		
		if (root == null) 
		{
			return null;
		}
		
		if (root.larger == null) 
		{
			returnNode = root;
			root = root.smaller;
			return returnNode;
		}
		
		return removeMaxHelper(root);
	}
	
	private BSTreeNode<E> removeMaxHelper(BSTreeNode<E> node) 
	{
		BSTreeNode<E> returnNode;
		BSTreeNode<E> currentNode = node.larger;
		
		if (currentNode.larger == null) 
		{
			returnNode = currentNode;
			node.larger = currentNode.smaller;
			return returnNode;
		}
		
		return removeMaxHelper(currentNode);
	}

	@Override
	public utilities.Iterator<E> inorderIterator() 
	{
		ArrayList<E> elements = new ArrayList<>();
		inorderHelper(root, elements);
		
		return new utilities.Iterator<E>() 
		{
			private int index = 0;
			
			public boolean hasNext() 
			{
				return index < elements.size();
			}
			
			public E next() throws NoSuchElementException
			{
				if (!hasNext()) 
				{
					throw new NoSuchElementException();
				}
				return elements.get(index++);
			}
		};
	}
	
	private void inorderHelper(BSTreeNode<E> node, ArrayList<E> result) 
	{
		if (node != null) 
		{
			inorderHelper(node.smaller, result);
			result.add(node.data);
			inorderHelper(node.larger, result);
		}
	} 

	@Override
	public utilities.Iterator<E> preorderIterator() 
	{
		ArrayList<E> elements = new ArrayList<>();
		preorderHelper(root, elements);
		
		return new utilities.Iterator<E>() 
		{
			private int index = 0;
			
			public boolean hasNext() 
			{
				return index < elements.size();
			}
			
			public E next() throws NoSuchElementException
			{
				if (!hasNext()) 
				{
					throw new NoSuchElementException();
				}
				return elements.get(index++);
			}
		};
	}
	
	private void preorderHelper(BSTreeNode<E> node, ArrayList<E> result) 
	{
		if (node != null) 
		{
			result.add(node.data);
			preorderHelper(node.smaller, result);
			preorderHelper(node.larger, result);
		}
	} 

	@Override
	public utilities.Iterator<E> postorderIterator() 
	{
		ArrayList<E> elements = new ArrayList<>();
		postorderHelper(root, elements);
		
		return new utilities.Iterator<E>() 
		{
			private int index = 0;
			
			public boolean hasNext() 
			{
				return index < elements.size();
			}
			
			public E next() throws NoSuchElementException
			{
				if (!hasNext()) 
				{
					throw new NoSuchElementException();
				}
				return elements.get(index++);
			}
		};
	}
	
	private void postorderHelper(BSTreeNode<E> node, ArrayList<E> result) 
	{
		if (node != null) 
		{
			postorderHelper(node.smaller, result);
			postorderHelper(node.larger, result);
			result.add(node.data);
		}
	} 

}
