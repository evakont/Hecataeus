/**
 * @author George Papastefanatos, National Technical University of Athens
 * @author Fotini Anagnostou, National Technical University of Athens
 */
package edu.ntua.dblab.hecataeus.graph.evolution;

/**
 * @author  George Papastefanatos
 */
public class EvolutionEdge{
     
	private String _Name = null;
	private EdgeType _Type;
	private int _EdgeKey = 0;
	private EvolutionNode<? extends EvolutionEdge> _FromNode = null;
    private EvolutionNode<? extends EvolutionEdge> _ToNode = null;
    private EvolutionPolicies _policies = null;
	private StatusType _status = StatusType.NO_STATUS;
      
    public EvolutionEdge() {
    	// just create the edge and set afterwards its properties
    }
        
    public EvolutionEdge(String name, EdgeType type,  EvolutionNode<? extends EvolutionEdge> fromNode,  EvolutionNode<? extends EvolutionEdge>  toNode) {
        this._Name = name;
        this._Type = type;
        this._FromNode =   fromNode;
        this._ToNode =   toNode;
        this._policies = new EvolutionPolicies();
    }
    
    /* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#getName()
	 */
	public String getName() {
		return this._Name;
	}

	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#setName(java.lang.String)
	 */
	public void setName(String Value) {
		this._Name = Value;
	}

	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#getType()
	 */
	public EdgeType getType() {
		return this._Type;
	}

	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#setType(edu.ntua.dblab.hecataeus.graph.evolution.EdgeType)
	 */
	public void setType(EdgeType Value) {
		this._Type = Value;
	}

	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#getKey()
	 */
	public int getKey() {
		return this._EdgeKey;
	}

	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#setKey(java.lang.String)
	 */
	public void setKey(int Value) {
		this._EdgeKey = Value;
	}
	
	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#getStatus()
	 */
	public StatusType getStatus() {
		return this._status ;
	}
	
	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#setStatus(edu.ntua.dblab.hecataeus.graph.evolution.StatusType)
	 */
	public void setStatus(StatusType status) {
		this._status = status ;
	}
	
    
    /* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#getFromNode()
	 */
    public  EvolutionNode<? extends EvolutionEdge> getFromNode() {
        return this._FromNode;
    }
    
    /* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#setFromNode(edu.ntua.dblab.hecataeus.graph.evolution.EvolutionNode)
	 */
    public void setFromNode(EvolutionNode<? extends EvolutionEdge> Value) {
     	this._FromNode = Value;
        
        
    }
    
    /* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#getToNode()
	 */
    public EvolutionNode<? extends EvolutionEdge> getToNode() {
        return this._ToNode;
    }
    
    /* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#setToNode(edu.ntua.dblab.hecataeus.graph.evolution.EvolutionNode)
	 */
    public void setToNode(EvolutionNode<? extends EvolutionEdge> Value) {
        this._ToNode = Value;
    }
    
   	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#getPolicies()
	 */
   	public EvolutionPolicies getPolicies() {
		return this._policies;
	}
	
   	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#setPolicies(edu.ntua.dblab.hecataeus.graph.evolution.EvolutionPolicies)
	 */
   	public void setPolicies(EvolutionPolicies policies) {
			this._policies = policies;
   	}

   	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#addPolicy(edu.ntua.dblab.hecataeus.graph.evolution.EventType, edu.ntua.dblab.hecataeus.graph.evolution.EvolutionNode, edu.ntua.dblab.hecataeus.graph.evolution.PolicyType)
	 */
	public void addPolicy(EventType eventType, EvolutionNode<? extends EvolutionEdge> eventNode, PolicyType policyType) {
		EvolutionPolicies policies = this._policies;
		EvolutionPolicy policy = policies.get(eventType, eventNode);
		if(policy!=null)
				policies.remove(policy);
		policies.add(new EvolutionPolicy(eventType,eventNode,policyType));
	}

	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#addPolicy(edu.ntua.dblab.hecataeus.graph.evolution.EvolutionPolicy)
	 */
	public void addPolicy(EvolutionPolicy p) {
		EvolutionPolicies policies = this._policies;
		EvolutionPolicy policy = policies.get(p.getSourceEvent().getEventType(), p.getSourceEvent().getEventNode());
		if(policy!=null)
				policies.remove(policy);
		policies.add(p);
	}
	
	/* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#removePolicy(edu.ntua.dblab.hecataeus.graph.evolution.EvolutionPolicy)
	 */
	public void removePolicy(EvolutionPolicy policy) {
		this._policies.remove(policy);
	}

	
    /* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#isPartOf()
	 */
    public boolean isPartOf() {
    	if ((this.getType()==EdgeType.EDGE_TYPE_SCHEMA) 
    			||(this.getType()==EdgeType.EDGE_TYPE_WHERE)
    			||((this.getType()==EdgeType.EDGE_TYPE_OPERATOR)
    					&&(this.getToNode().getType()!=NodeType.NODE_TYPE_ATTRIBUTE)
    					&&(this.getToNode().getType()!=NodeType.NODE_TYPE_QUERY))
    					||((this.getType()==EdgeType.EDGE_TYPE_GROUP_BY)
    	    					&&(this.getToNode().getType()!=NodeType.NODE_TYPE_ATTRIBUTE))
    	    					||((this.getType()==EdgeType.EDGE_TYPE_MAPPING)
    	    							&&(this.getToNode().getType()!=NodeType.NODE_TYPE_ATTRIBUTE))
    	){
    		return true;
    	}        
    	return false;
    }
    
    
    /* (non-Javadoc)
	 * @see edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge#isProvider()
	 */
    public boolean isProvider(){
    	return (!this.isPartOf());
    }
    
    public String toString() {
		return this._Name;
	}
	
}