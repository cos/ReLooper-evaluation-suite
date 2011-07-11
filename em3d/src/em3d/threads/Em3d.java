package em3d.threads;
/**
 * Java implementation of the <tt>em3d</tt> Olden benchmark.  This Olden
 * benchmark models the propagation of electromagnetic waves through
 * objects in 3 dimensions. It is a simple computation on an irregular
 * bipartite graph containing nodes representing electric and magnetic
 * field values.
 * <p/>
 * <p><cite>
 * D. Culler, A. Dusseau, S. Goldstein, A. Krishnamurthy, S. Lumetta, T. von
 * Eicken and K. Yelick. "Parallel Programming in Split-C".  Supercomputing
 * 1993, pages 262-273.
 * </cite>
 */
public class Em3d {

  static int nodect = 50;
  static int conn = 10;
  static int numiter = 3;

  /**
   * The main roitine that creates the irregular, linked data structure
   * that represents the electric and magnetic fields and propagates the
   * waves through the graph.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    BiGraph graph = BiGraph.create(nodect, conn);

    for (int i = 0; i < numiter; i++) {
      try {
		graph.compute();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    graph.printGraph();
  }
}

/*
nodect = 50;
conn = 10;
numiter = 3;
=>
E: value -1238.30599380207, from_count 13
E: value -1867.325592512137, from_count 12
E: value -1959.6048376435954, from_count 1
E: value -790.5456933978994, from_count 7
E: value -2447.5465635918076, from_count 1
E: value -1513.916472114712, from_count 9
E: value -2179.457205258014, from_count 16
E: value -1883.7574788273462, from_count 1
E: value -950.5464568853084, from_count 9
E: value -1234.7173606045844, from_count 6
E: value -965.0468167354121, from_count 7
E: value -1229.498022372893, from_count 8
E: value -1698.4827682415778, from_count 1
E: value -1474.407578257317, from_count 11
E: value -2302.3908804021953, from_count 1
E: value -762.719337869357, from_count 6
E: value -1074.4840950654734, from_count 8
E: value -465.6999635301128, from_count 4
E: value -886.7534122602808, from_count 8
E: value -976.5558902349014, from_count 9
E: value -549.8595872386879, from_count 6
E: value -1386.5533936356157, from_count 1
E: value -2247.8927204459887, from_count 1
E: value -1028.1514978801579, from_count 8
E: value -1063.9272093635177, from_count 1
E: value -1225.3403774327394, from_count 6
E: value -2286.7874066405243, from_count 1
E: value -558.4036299866302, from_count 5
E: value -1207.8984048015866, from_count 7
E: value -1677.9639201812618, from_count 9
E: value -1726.782211860124, from_count 11
E: value -1176.6169365957526, from_count 8
E: value -1396.8896292403967, from_count 9
E: value -1120.6872673799612, from_count 7
E: value -1060.2210083896575, from_count 7
E: value -1994.4340118191344, from_count 1
E: value -2031.3983198427902, from_count 1
E: value -2349.599841576875, from_count 12
E: value -982.2169214710752, from_count 8
E: value -893.4281746828298, from_count 7
E: value -994.6176875040289, from_count 7
E: value -1827.769252359721, from_count 10
E: value -1580.8820093331472, from_count 1
E: value -1001.1145996826128, from_count 9
E: value -1060.6247480922966, from_count 1
E: value -735.8279907002916, from_count 8
E: value -523.6055287452907, from_count 6
E: value -740.8262981777118, from_count 6
E: value -1793.160036366079, from_count 11
E: value -1368.4048651193157, from_count 1
H: value 8275.902758666745, from_count 10
H: value 8245.764340797861, from_count 11
H: value 8246.186818154845, from_count 9
H: value 6273.976592918233, from_count 11
H: value 4993.615386455687, from_count 11
H: value 10428.952582517244, from_count 16
H: value 7795.553731779442, from_count 10
H: value 4480.706641848758, from_count 8
H: value 10695.307413379496, from_count 14
H: value 5469.126379469843, from_count 9
H: value 6774.902997044515, from_count 12
H: value 6329.130471019401, from_count 10
H: value 9106.193938063527, from_count 15
H: value 7974.60322517995, from_count 14
H: value 5733.046057665605, from_count 11
H: value 12143.27323830995, from_count 13
H: value 7478.101783645134, from_count 11
H: value 13281.750762012338, from_count 13
H: value 5810.860214392973, from_count 10
H: value 5430.836170500265, from_count 8
H: value 4554.983480416468, from_count 7
H: value 8060.170158646655, from_count 9
H: value 1873.9620477218857, from_count 3
H: value 11989.251905410878, from_count 17
H: value 7941.406845055164, from_count 11
H: value 4792.0543631951705, from_count 8
H: value 7163.132756419202, from_count 11
H: value 6146.758567217945, from_count 8
H: value 8718.131750611117, from_count 10
H: value 8011.871678915925, from_count 12
H: value 9695.966390714639, from_count 14
H: value 4322.809229908746, from_count 8
H: value 5718.648758506927, from_count 8
H: value 3935.13824841943, from_count 6
H: value 15097.681897493763, from_count 19
H: value 2433.494028539707, from_count 4
H: value 7768.132018659497, from_count 9
H: value 6760.675783592273, from_count 12
H: value 3720.9660739195238, from_count 9
H: value 5929.285582420098, from_count 6
H: value 5224.310189280059, from_count 6
H: value 10223.703925150865, from_count 14
H: value 8543.975359077254, from_count 9
H: value 9756.818298444334, from_count 13
H: value 2642.549532269605, from_count 5
H: value 5723.148800807919, from_count 10
H: value 5947.266947344019, from_count 12
H: value 2363.849789965696, from_count 4
H: value 4145.363020774802, from_count 6
H: value 1789.3580672922135, from_count 4
*/
