package edu.ntua.dblab.hecataeus.graph.evolution.messages;

import java.util.PriorityQueue;

import edu.ntua.dblab.hecataeus.graph.evolution.EdgeType;
import edu.ntua.dblab.hecataeus.graph.evolution.EvolutionEdge;
import edu.ntua.dblab.hecataeus.graph.evolution.EvolutionGraph;
import edu.ntua.dblab.hecataeus.graph.evolution.EvolutionNode;
import edu.ntua.dblab.hecataeus.graph.evolution.MetriseisRewrite;

public class ModuleMaestroRewrite<V extends EvolutionNode<E>,E extends EvolutionEdge> extends ModuleMaestro<V, E>
{
	
	public ModuleMaestroRewrite(PriorityQueue<Message<V,E>> globalQueue)
	{
		super(globalQueue);
	}

	public String doRewrite(String tempParam, EvolutionGraph<V,E> graph, StopWatch stw, MetriseisRewrite mr)
	{
		while(myQueue.peek()!=null)
		{
			Message<V,E> currentMessage=(Message<V,E>) myQueue.poll();
			MaestroAbstract<V, E> maestro = MaestroFactory.create(currentMessage,forGlobal);
			tempParam=maestro.doRewrite(currentMessage, tempParam, graph, stw, mr);
		}
		return(tempParam);
	}
	
	@SuppressWarnings("unchecked")
	public void moveToNewInputsIfExist(EvolutionGraph<V,E> graph, V node)
	{
		for (int ii = 0; ii < node.getOutEdges().size(); ii++)
		{
			if(node.getOutEdges().get(ii).getType()==EdgeType.EDGE_TYPE_INPUT)
			{
				V pprov=null;
				V inNode=(V) node.getOutEdges().get(ii).getToNode();
				for(int i=0;i<inNode.getOutEdges().size();i++)
				{
					if(inNode.getOutEdges().get(i).getType()==EdgeType.EDGE_TYPE_FROM)
					{
						V prov=(V) inNode.getOutEdges().get(i).getToNode();
						for(int j=0;j<prov.getInEdges().size();j++)
						{
							if(prov.getInEdges().get(j).getType()==EdgeType.EDGE_TYPE_OUTPUT)
							{
								pprov=(V) prov.getInEdges().get(j).getFromNode();
							}
						}
					}
				}
				V newpprov = graph.findVertexById(pprov.ID + 0.4);
				if(newpprov==null)
				{
					return;
				}
				else
				{	// All children of inNode need to move to their edges to newpprov.
					V newprovOut=null;
					for(int i=0;i<newpprov.getOutEdges().size();i++)
					{
						if(newpprov.getOutEdges().get(i).getType()==EdgeType.EDGE_TYPE_OUTPUT)
						{
							newprovOut=(V) newpprov.getOutEdges().get(i).getToNode();
						}
					}
					for(int i=0;i<inNode.getOutEdges().size();i++)
					{
						if(inNode.getOutEdges().get(i).getType()==EdgeType.EDGE_TYPE_INPUT)
						{
							V attr=(V) inNode.getOutEdges().get(i).getToNode();
							for(int j=0;j<attr.getOutEdges().size();j++)
							{
								if(attr.getOutEdges().get(j).getType()==EdgeType.EDGE_TYPE_MAPPING)
								{	// move it to new providers output
									for(int k=0;k<newprovOut.getOutEdges().size();k++)
									{
										if(newprovOut.getOutEdges().get(k).getToNode().getName().equals(attr.getOutEdges().get(j).getToNode().getName()))
										{
											attr.getOutEdges().get(j).setToNode(newprovOut.getOutEdges().get(k).getToNode());
										}
									}
								}
							}
						}
						if(inNode.getOutEdges().get(i).getType()==EdgeType.EDGE_TYPE_FROM)
						{
							inNode.getOutEdges().get(i).setToNode(newprovOut);
						}
					}
					for(int i=0;i<node.getOutEdges().size();i++)
					{
						if(node.getOutEdges().get(i).getType()==EdgeType.EDGE_TYPE_USES&&
								node.getOutEdges().get(i).getToNode()==graph.findVertexById(pprov.ID))
						{
							if(node.getOutEdges().get(i).getToNode()==pprov)
							{
								node.getOutEdges().get(i).setToNode(newpprov);
							}
						}
					}
				}
			}
		}
	}
}
