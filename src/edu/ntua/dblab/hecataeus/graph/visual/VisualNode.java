/**
 * @author George Papastefanatos, National Technical University of Athens
 * @author Fotini Anagnostou, National Technical University of Athens
 */
package edu.ntua.dblab.hecataeus.graph.visual;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import edu.ntua.dblab.hecataeus.HecataeusModalGraphMouse;
import edu.ntua.dblab.hecataeus.graph.evolution.EvolutionNode;
import edu.ntua.dblab.hecataeus.graph.evolution.NodeType;

/**
 * The class implements a visual node of the graph
 * It holds the visual properties of the node
 * 
 * @author  gpapas
 */
public class VisualNode extends EvolutionNode<VisualEdge>{

	private double angle;
	private Point2D location;
	private Point2D lastChildLocation;
	private Boolean isVisible  = true;
	private double nodeSize; // used only for node type cluster
	private Color nodeColor;
	private String label; //used only for node type cluster
	private String fileName;
	public int size;
	
	

	
	
	public VisualNode(){
		super();
		this.location = new Point2D.Double();
		this.lastChildLocation = new Point2D.Double();
		this.angle = 0.0;
		this.nodeColor = new Color(255,255,255);
		this.nodeSize = 0.0;
		this.label = "";
		this.fileName = "";
	}
	
	public void setLocation(Point2D loc){
		this.location = loc;
	}
	
	public Point2D getLocation(){
		return this.location;
	}
	
	public void setNodeColor(Color c){
		this.nodeColor = c;
	}
	
	public Color getNodeColor(VisualNode v){
		return this.nodeColor;
	}
	
	public void setNodeAngle(double a){
		this.angle = a;
	}
	public double getNodeAngle(){
		return this.angle;
	}
	
	public void setNodeSize(double size){
		this.nodeSize = size;
	}
	
	public double getNodeSize(){
		return this.nodeSize;
	}
	
	public void setNodeLabel(String label){
		this.label = label;
	}
	
	public String getNodeLabel(){
		return this.label;
	}
	
	public void setFileName(String name){
		this.fileName = name;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public VisualNode(String name, NodeType type) {
		super(name, type);
		this.location = new Point2D.Double();
		this.lastChildLocation = new Point2D.Double();
		this.angle = 0.0;
		this.nodeColor = new Color(255,255,255);
		this.nodeSize = 0.0;
		this.label = "";
		this.fileName = "";
	}
	
	
	public void setVisible(boolean Value) {
		this.isVisible = Value;
	}
	
	public Boolean getVisible() {

		return this.isVisible ;
	}
	
	/**
	 * @return  true if node has Policies, false otherwise
	 */
	public Boolean getHasPolicies(){
		return (this.getPolicies().size()>0? true:false);
	}
	
	/**
	 * @return true if node has Events, false otherwise
	 */
	public Boolean getHasEvents(){
		return (this.getEvents().size()>0? true:false);
	}
	
		
	public VisualNode clone() {
		VisualNode node = new VisualNode();
		node.setName(this.getName());
		node.setType(this.getType());
//		Point2D newLoc = new Point2D.Double(this.location.getX(),this.location.getY());
//		node.setLocation(newLoc);
//		newLoc = new Point2D.Double(this.lastChildLocation.getX(),this.lastChildLocation.getY());
//		node.setLastChildLocation(newLoc.getX(),newLoc.getY());
		node.setSQLDefinition(this.getSQLDefinition());
		node.setEvents(this.getEvents());
		node.setPolicies(this.getPolicies());
		node.setVisible(this.getVisible());
		node.setStatus(this.getStatus(),true);
		node._inEdges=this.getInEdges();
		node._outEdges= this.getOutEdges();
		node.setNodeAngle(this.angle);
		node.setNodeColor(this.nodeColor);
		node.setNodeSize(this.nodeSize);
		node.setLocation(this.getLocation());
		node.setFileName(this.fileName);
		return node;
	}

//	public void addMouseListener(HecataeusModalGraphMouse myListener, MouseEvent e) {
//		// TODO Auto-generated method stub
//		if (e.isPopupTrigger())
//			myListener.graphReleased(this, e);
//	}
	

}
