package em3d.threads;
import java.util.*;

/**
 * A class that represents the irregular bipartite graph used in
 * EM3D.  The graph contains two linked structures that represent the
 * E nodes and the N nodes in the application.
 */
class BiGraph {
  /**
   * Nodes that represent the electrical field.
   */
  ENode[] eNodes;
  /**
   * Nodes that representhe the magnetic field.
   */
  ENode[] hNodes;

  /**
   * Construct the bipartite graph.
   *
   * @param e the nodes representing the electric fields
   * @param h the nodes representing the magnetic fields
   */
  public BiGraph(ENode[] e, ENode[] h) {
    eNodes = e;
    hNodes = h;
  }

  /**
   * @param numNodes  the number of nodes to create
   * @param numDegree the out-degree of each node
   * @return the bi graph that we've created.
   */
  static BiGraph create(int numNodes, int numDegree) {
    // making nodes (we create a table)
    ENode[] hTable = ENode.fillTable(numNodes, numDegree);
    ENode[] eTable = ENode.fillTable(numNodes, numDegree);

	for (int i = hTable.length - 1; i >= 0; --i) {
      hTable[i].makeUniqueNeighbors(eTable);
    }

	for (int i = eTable.length - 1; i >= 0; --i) {
      ((ENode) eTable[i]).makeUniqueNeighbors(hTable);
    }
    
    // Create the fromNodes and coeff field
    for (int i = hTable.length - 1; i >= 0; --i) {
      ((ENode) hTable[i]).makeFromNodes();
    }

   for (int i = eTable.length - 1; i >= 0; --i) {
      ((ENode) eTable[i]).makeFromNodes();
    }

    // Update the fromNodes
    for (int i = hTable.length - 1; i >= 0; --i) {
      ((ENode) hTable[i]).updateFromNodes();
    }

   for (int i = eTable.length - 1; i >= 0; --i) {
      ((ENode) eTable[i]).updateFromNodes();
    }

    BiGraph g = new BiGraph(eTable, hTable);
    return g;
  }

  /**
   * Update the field values of e-nodes based on the values of
   * neighboring h-nodes and vice-versa.
 * @throws InterruptedException 
   */
  void compute() throws InterruptedException {
    int i;
    Thread t[] = new Thread[this.eNodes.length];
    
    for (i = 0; i < this.eNodes.length; ++i) {
    	final int j = i;
    	t[i] = new Thread(new Runnable() {
			@Override
			public void run() {
				eNodes[j].computeNewValue();
			}
		});
    	t[i].start();
    }
    
    for (i = 0; i < this.eNodes.length; ++i) {
    	t[i].join();
    }
    
    for (i = 0; i < this.hNodes.length; ++i) {
    	final int j = i;
    	t[i] = new Thread(new Runnable() {
			@Override
			public void run() {
				hNodes[j].computeNewValue();
			}
		});
    	t[i].start();
    }
    
    for (i = 0; i < this.eNodes.length; ++i) {
    	t[i].join();
    }
  }

  public void printGraph() {
    ENode n;
    int i;
    for (i = 0; i < this.eNodes.length; ++i) {
      System.out.print("E: ");
      ((ENode) this.eNodes[i]).printNode();
      System.out.print("\n");
    }

    for (i = 0; i < this.hNodes.length; ++i) {
      System.out.print("H: ");
      ((ENode) this.hNodes[i]).printNode();
      System.out.print("\n");
		}
	}
}



