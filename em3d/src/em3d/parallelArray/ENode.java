package em3d.parallelArray;
import java.util.*;

import extra166y.Ops.Generator;
import extra166y.ParallelArray;

/**
 * This class implements nodes (both E- and H-nodes) of the EM graph. Sets
 * up random neighbors and propagates field values among neighbors.
 */
class ENode {
  /**
   * The value of the node.
   */
  double value;
  /**
   * Array of nodes to which we send our value.
   */
  ENode[] toNodes;
  /**
   * Array of nodes from which we receive values.
   */
  ENode[] fromNodes;
  /**
   * Coefficients on the fromNodes edges
   */
  double[] coeffs;
  /**
   * The number of fromNodes edges
   */
  int fromCount;
  /**
   * Used to create the fromEdges - keeps track of the number of edges that have
   * been added
   */
  int fromLength;

  /**
   * Constructor for a node with given `degree'.   The value of the
   * node is initialized to a random value.
   */
  public ENode(int degree) {
    value = MathFP.randDouble();
    // create empty array for holding toNodes
    toNodes = new ENode[degree];
    fromNodes = null;
    coeffs = null;
    fromCount = 0;
    fromLength = 0;
  }

  /**
   * Create the linked list of E or H nodes.  We create a table which is used
   * later to create links among the nodes.
   *
   * @param size   the no. of nodes to create
   * @param degree the out degree of each node
   * @return a table containing all the nodes.
   */
  static ParallelArray<ENode> fillTable(int size, final int degree) {
    ENode[] source = new ENode[size];
	ParallelArray<ENode> table = ParallelArray.createUsingHandoff(source, ParallelArray.defaultExecutor());
    
    table.replaceWithGeneratedValueSeq(new Generator<ENode>() {
		@Override
		public ENode op() {
			return new ENode(degree);
		}
	});

    return table;
  }

  /**
   * Create unique `degree' neighbors from the nodes given in nodeTable.
   * We do this by selecting a random node from the give nodeTable to
   * be neighbor. If this neighbor has been previously selected, then
   * a different random neighbor is chosen.
   *
   * @param nodeTable the list of nodes to choose from.
   */
  void makeUniqueNeighbors(ENode[] nodeTable) {
    for (int filled = 0; filled < toNodes.length; filled++) {
      int k;
      ENode otherNode = null;

      do {
        // generate a random number in the correct range
        int index = MathI.randInt();
        if (index < 0) {
          index = -index;
        }

        index = index % nodeTable.length;

        // find a node with the random index in the given table
        otherNode = (ENode) nodeTable[index];

        for (k = 0; k < filled; k++) {
          if (otherNode == toNodes[filled]) {
            break;
          }
        }
      } while (k < filled);

      // other node is definitely unique among "filled" toNodes
      toNodes[filled] = otherNode;

      // update fromCount for the other node
      otherNode.fromCount = otherNode.fromCount + 1;
    }
  }

  /**
   * Allocate the right number of FromNodes for this node. This
   * step can only happen once we know the right number of from nodes
   * to allocate. Can be done after unique neighbors are created and known.
   * <p/>
   * It also initializes random coefficients on the edges.
   */
  void makeFromNodes() {
    fromNodes = new ENode[fromCount]; // nodes fill be filled in later
    coeffs = new double[fromCount];
  }

  /**
   * Fill in the fromNode field in "other" nodes which are pointed to
   * by this node.
   */
  void updateFromNodes() {
    int i;
    for (i = 0; i < this.toNodes.length; ++i) {
      ENode otherNode = this.toNodes[i];
      int count = otherNode.fromLength;
      otherNode.fromLength = otherNode.fromLength + 1;
      otherNode.fromNodes[count] = this;
      otherNode.coeffs[count] = MathFP.randDouble();
    }
  }

  /**
   * Get the new value of the current node based on its neighboring
   * from_nodes and coefficients.
   */
  void computeNewValue() {
    for (int i = 0; i < fromCount; i++) {
      value -= coeffs[i] * fromNodes[i].value;
    }
  }

  public void printNode() {
    System.out.print("value " + value + ", from_count " + fromCount);
	}
}


