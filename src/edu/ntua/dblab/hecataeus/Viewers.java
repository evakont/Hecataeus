/**
 * 
 */
package edu.ntua.dblab.hecataeus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.ntua.dblab.hecataeus.graph.evolution.NodeCategory;
import edu.ntua.dblab.hecataeus.graph.evolution.NodeType;
import edu.ntua.dblab.hecataeus.graph.visual.VisualEdge;
import edu.ntua.dblab.hecataeus.graph.visual.VisualEdgeColor;
import edu.ntua.dblab.hecataeus.graph.visual.VisualEdgeLabel;
import edu.ntua.dblab.hecataeus.graph.visual.VisualEdgeToolTips;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNode;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNodeColor;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNodeFont;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNodeIcon;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNodeLabel;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNodeShape;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNodeToolTips;
import edu.ntua.dblab.hecataeus.graph.visual.VisualNodeVisible;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.picking.LayoutLensShapePickSupport;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * @author eva
 *
 */
public class Viewers {
	public static int cnt = 0;
	protected Transformer<VisualNode, Point2D> locationTransformer;
	protected VisualizationViewer<VisualNode, VisualEdge> vv;
	protected HecataeusViewer viewer;
	
	protected void setTr(){
		final Dimension prefferedSize = Toolkit.getDefaultToolkit().getScreenSize();
		locationTransformer = new Transformer<VisualNode, Point2D>() {
			@Override
			public Point2D transform(VisualNode node) {
				NodeType type = (node.getType());
				Point2D p2d;
				if(type.getCategory() == NodeCategory.SCHEMA){
					p2d = new Point2D.Double(prefferedSize.getWidth()-200, 100+cnt);
					cnt++;
					return p2d;
				}
				else if (type.getCategory()== NodeCategory.MODULE){
					p2d = new Point2D.Double(prefferedSize.getWidth()-400, 100+cnt);
					cnt++;
					return p2d;
				}
				else if (type.getCategory()== NodeCategory.CONTAINER){
					p2d = new Point2D.Double(prefferedSize.getWidth()-600, 100+cnt);
					cnt++;
					return p2d;
				}
				else if (type.getCategory()== NodeCategory.INOUTSCHEMA){
					p2d = new Point2D.Double(prefferedSize.getWidth()-800, 100+cnt);
					cnt++;
					return p2d;
				}
				else
					p2d = new Point2D.Double(prefferedSize.getWidth()-300, 100+cnt);
					return p2d;
			}
		};
	}
	
	
	protected VisualizationViewer<VisualNode, VisualEdge> SetViewers(Layout<VisualNode, VisualEdge> layout, HecataeusViewer viewer) {
		
		
		
		
		
		
		
		
		this.viewer = viewer;
		vv = new VisualizationViewer<VisualNode, VisualEdge>(layout);
		Dimension prefferedSize = Toolkit.getDefaultToolkit().getScreenSize();
		vv = new VisualizationViewer<VisualNode, VisualEdge>(layout);
		vv.setSize(new Dimension((int)prefferedSize.getWidth()-800,(int)prefferedSize.getHeight()-300));
		vv.setBackground(Color.white);
		vv.setPickSupport(new LayoutLensShapePickSupport<VisualNode, VisualEdge>(vv));
		
		vv.setVertexToolTipTransformer(new VisualNodeToolTips());
		vv.setEdgeToolTipTransformer(new VisualEdgeToolTips());
		// the renderer of the vv
		RenderContext<VisualNode, VisualEdge>  pr = vv.getRenderContext();
		// the labels of the Vertices
		pr.setVertexLabelTransformer(new VisualNodeLabel());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.AUTO); 

		vv.getRenderContext().setEdgeStrokeTransformer(new ConstantTransformer(new BasicStroke(0.1f)));
		// the fonts of the vertices
		pr.setVertexFontTransformer(new VisualNodeFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8)));
		//the shape of the edges
		pr.setEdgeShapeTransformer(new EdgeShape.QuadCurve<VisualNode, VisualEdge>());
		// the labels of the Edges
		pr.setEdgeLabelTransformer(new VisualEdgeLabel());
		// call the setVertexPaintFunction to paint the nodes
		pr.setVertexFillPaintTransformer(new VisualNodeColor(vv.getPickedVertexState()));
		// call the setEdgePaintFunction to paint the edges
		pr.setEdgeFillPaintTransformer( new VisualEdgeColor(vv.getPickedEdgeState()));
		// call the setVertexShapeFunction to set the shape of the nodes
		pr.setVertexShapeTransformer(new VisualNodeShape());
		// call the setNodeVisible to set the shape of the nodes according to
		pr.setVertexIncludePredicate(new VisualNodeVisible());
		pr.setVertexIconTransformer(new VisualNodeIcon());
		
//		double amount = 1.0;
//		ScalingControl scaler = new CrossoverScalingControl();
//		scaler.scale(vv, amount > 0 ? 1.1f : 1 / 1.1f, vv.getCenter());
		vv.getRenderContext().getMultiLayerTransformer().addChangeListener(vv);
		HecataeusModalGraphMouse gm = new HecataeusModalGraphMouse();
		gm.HecataeusViewerPM(viewer);
		vv.setGraphMouse(gm);
		vv.addGraphMouseListener(gm);
		
		return vv;

	}
	

}
