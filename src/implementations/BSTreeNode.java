package implementations;

public class BSTreeNode<E>
{
	E data;
	BSTreeNode<E> larger;
	BSTreeNode<E> smaller;
	
	public BSTreeNode(E data) 
	{
		this.data = data;
	}
	
	public E getElement() 
	{
        return data;
    }

    public void setElement(E data) 
    {
        this.data = data;
    }

    public BSTreeNode<E> getSmaller() 
    {
        return smaller;
    }

    public void setSmaller(BSTreeNode<E> smaller) 
    {
        this.smaller = smaller;
    }

    public BSTreeNode<E> getLarger() 
    {
        return larger;
    }

    public void setLarger(BSTreeNode<E> larger) 
    {
        this.larger = larger;
    }
    
    
	
	

}
