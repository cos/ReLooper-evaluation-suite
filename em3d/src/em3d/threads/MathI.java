package em3d.threads;
/*This class defines our semi standard integer math package*/

public class MathI {
  private static java.util.Random ri = new java.util.Random(0);

  public static int randInt() {
    return MathI.ri.nextInt();
  }

  public static int floor(double d) {
    return (int) Math.floor(d);
  }

  public static int abs(int x) {
    if (x >= 0) {
      return x;
    } else {
      return (-1) * x;
    }
  }
}


