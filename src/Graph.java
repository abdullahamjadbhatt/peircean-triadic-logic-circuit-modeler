import java.util.ArrayList;

/**
 *This class creates a Directed Acyclic Graph to simulate the design.
 * @author Abdullah Amjad
 * @version 4.0
 */

public class Graph
{
    public Graph()
    {
        this.root = new Component();
        this.nodes = new ArrayList();
        this.orderedPairs = new ArrayList();
        this.construct = false;
    }
    
    private Component find(int index)
    {
        for(int i = 0; i < this.nodes.size(); i++)
        {
            if(this.nodes.get(i).index == index)
            {
                return this.nodes.get(i);
            }
        }
        return null;
    }
    
    public boolean insert(Component source, Component sink)
    {
        Component _source = this.find(source.index);
        Component _sink = this.find(sink.index);
        
        if(_source != null)
        {
            source = _source;
        }
        else
        {
            this.root.children.add(source);
            source.parents.add(this.root);
            this.nodes.add(source);
        }
        
        if(_sink != null)
        {
            sink = _sink;
        }
        else
        {
            this.nodes.add(sink);
        }
        
        source.children.add(sink);
        sink.parents.add(source);
        
        ArrayList<Component> stack = new ArrayList();
        boolean cycle = source.isCycle(stack);
        
        if(cycle)
        {
            source.children.remove(sink);
            sink.parents.remove(source);
        }
        else
        {
            if(this.root.children.contains(sink))
            {
                this.root.children.remove(sink);
                sink.parents.remove(this.root);
            }
        }
        
        for(int i = 0; i < this.nodes.size(); i++)
        {
            this.nodes.get(i).visited = false;
        }
        
        return !cycle;
    }
    
    public void remove(Component desc) 
    {
        int index = this.nodes.indexOf(desc);
        if(index >= 0)
        {
            int i = 0;
            while(!this.nodes.isEmpty() && i < this.nodes.get(index).children.size())
            {
                this.nodes.get(index).children.get(i).parents.remove(this.nodes.get(index));                
                if(!this.isChildPopular(this.nodes.get(index).children.get(i)))
                {
                    this.nodes.remove(this.nodes.get(index).children.get(i));
                    this.nodes.get(index).children.remove(i);
                    index = this.nodes.indexOf(desc);
                    continue;
                }
                i++;
            }

            i = 0;
            while(!this.nodes.isEmpty() && i < this.nodes.get(index).parents.size())
            {
                this.nodes.get(index).parents.get(i).children.remove(this.nodes.get(index));                
                if(!this.isParentPopular(this.nodes.get(index).parents.get(i)))
                {
                    this.nodes.remove(this.nodes.get(index).parents.get(i));
                    this.nodes.get(index).parents.remove(i);
                    index = this.nodes.indexOf(desc);
                    continue;
                }
                i++;
            }       
            this.nodes.remove(index);
        }
    }    
    
    private boolean isChildPopular(Component peer) 
    {
        if(peer != this.root)
        {
            if(peer.children.isEmpty())
            {
                if(peer.parents.isEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isParentPopular(Component peer) 
    {
        if(peer != this.root)
        {
            if(peer.children.isEmpty())
            {
                if(peer.parents.isEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void evaluate() 
    {
        for(int i = 0; i < this.root.children.size(); i++)
        {
            if(this.root.children.get(i).type.contentEquals("Input"))
            {
                this.root.children.get(i).evaluate();
            }
        }
        
        if(this.construct)
        {
            ArrayList<String> header = new ArrayList();
            ArrayList<String> orderedPair = new ArrayList();
            
            for(int i = 0; i < this.nodes.size(); i++)
            {
                if(this.nodes.get(i).type.contentEquals("Input"))
                {
                    header.add(this.nodes.get(i).label.getText());
                    orderedPair.add(this.nodes.get(i).logicalOutput);
                }
            }
            
            for(int i = 0; i < this.nodes.size(); i++)
            {
                if(this.nodes.get(i).type.contentEquals("Output"))
                {
                    header.add(this.nodes.get(i).label.getText());
                    orderedPair.add(this.nodes.get(i).logicalInputs.get(0));
                }
            }
            
            if(this.orderedPairs.isEmpty())
            {
                this.orderedPairs.add(header);
            }
            
            this.orderedPairs.remove(orderedPair);
            this.orderedPairs.add(orderedPair);
        }
        
        for(int i = 0; i < this.nodes.size(); i++)
        {
            this.nodes.get(i).logicalInputs.clear();
            this.nodes.get(i).logicalOutput = null;
        }
    }    
    
    public ArrayList<String> generateExpressions() 
    {
        ArrayList<String> expressions = new ArrayList();
        for(int i = 0; i < this.nodes.size(); i++)
        {
            if(this.nodes.get(i).type.contentEquals("Output"))
            {
                expressions.add(this.nodes.get(i).generateExpression());
            }
        }
        return expressions;
    }    
    
    private final Component root;
    protected ArrayList<Component> nodes;
    protected ArrayList<ArrayList<String>> orderedPairs;
    protected boolean construct;
}
