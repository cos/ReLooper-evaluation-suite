package em3d.parallelArray;
import java.util.*;

import extra166y.Ops.Procedure;
import extra166y.ParallelArray;

/**
 * A class that represents the irregular bipartite graph used in
 * EM3D.  The graph contains two linked structures that represent the
 * E nodes and the N nodes in the application.
 */
class BiGraph {
  protected static int bla;
/**
   * Nodes that represent the electrical field.
   */
  ParallelArray<ENode> eNodes;
  /**
   * Nodes that representhe the magnetic field.
   */
  ParallelArray<ENode> hNodes;

  /**
   * Construct the bipartite graph.
   *
   * @param e the nodes representing the electric fields
   * @param hTable the nodes representing the magnetic fields
   */
  public BiGraph(ParallelArray<ENode> e, ParallelArray<ENode> hTable) {
    eNodes = e;
    hNodes = hTable;
  }

  /**
   * @param numNodes  the number of nodes to create
   * @param numDegree the out-degree of each node
   * @return the bi graph that we've created.
   */
  static BiGraph create(int numNodes, int numDegree) {
    // making nodes (we create a table)
    final ParallelArray<ENode> hTable = ENode.fillTable(numNodes, numDegree);
    final ParallelArray<ENode> eTable = ENode.fillTable(numNodes, numDegree);

    hTable.applySeq(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			ParallelArray<ENode> eTable2 = eTable;
			ENode[] bla = eTable2.getArray();
			b.makeUniqueNeighbors(bla);	
		}
	});

    eTable.applySeq(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			b.makeUniqueNeighbors(hTable.getArray());	
		}
	});
    
    hTable.applySeq(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			b.makeFromNodes();
		}
	});

    eTable.applySeq(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			b.makeFromNodes();
		}
	});
    
    hTable.applySeq(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			b.updateFromNodes();
		}
	});

    eTable.applySeq(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			b.updateFromNodes();
		}
	});

    BiGraph g = new BiGraph(eTable, hTable);
    return g;
  }

  /**
   * Update the field values of e-nodes based on the values of
   * neighboring h-nodes and vice-versa.
   */
  void compute() {
	  
    eNodes.apply(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			b.computeNewValue();
		}
	});

    hNodes.apply(new Procedure<ENode>() {
		@Override
		public void op(ENode b) {
			b.computeNewValue();
		}
	});
  }

  public void printGraph() {
    ENode n;
    int i;
    for (i = 0; i < this.eNodes.getArray().length; ++i) {
      System.out.print("E: ");
      ((ENode) this.eNodes.getArray()[i]).printNode();
      System.out.print("\n");
    }

    for (i = 0; i < this.hNodes.getArray().length; ++i) {
      System.out.print("H: ");
      ((ENode) this.hNodes.getArray()[i]).printNode();
      System.out.print("\n");
		}
	}
}



