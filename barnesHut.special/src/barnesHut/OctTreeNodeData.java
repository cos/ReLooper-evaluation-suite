/*
Lonestar Benchmark Suite for irregular applications that exhibit 
amorphous data-parallelism.

Center for Grid and Distributed Computing
The University of Texas at Austin

Copyright (C) 2007, 2008, 2009 The University of Texas at Austin

Licensed under the Eclipse Public License, Version 1.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.eclipse.org/legal/epl-v10.html

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

File: OctTreeNodeData.java 
*/

package barnesHut;

class OctTreeNodeData { // the internal nodes are cells that summarize their children's properties
  double mass;
  double posx;
  double posy;
  double posz;

  OctTreeNodeData(double px, double py, double pz) {
    mass = 0.0;
    posx = px;
    posy = py;
    posz = pz;
  }

  @Override
  public String toString() {
    String result = "mass = " + mass;
    result += "pos = (" + posx + "," + posy + "," + posz + ")";
    return result;
  }

  public double posx() {
    return posx;
  }

  public double posy() {
    return posy;
  }

  public double posz() {
    return posz;
  }
}
