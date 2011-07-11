package em3d.threads;
/*This class defines our semi standard floating point math package*/

public class MathFP {
  private static java.util.Random ri = new java.util.Random(0);

  public static double randDouble() {
    return MathFP.ri.nextDouble();
  }

  public static double sqrt(double d) {
    return Math.sqrt(d);
  }

  public static double pow(double x, double y) {
    return Math.pow(x, y);
  }

  public static double log(double x) {
    return Math.log(x);
  }

  public static double tan(double x) {
    return Math.tan(x);
  }

  public static double abs(double x) {
    if (x >= 0) {
      return x;
    } else {
      return (-1) * x;
    }
  }
}


