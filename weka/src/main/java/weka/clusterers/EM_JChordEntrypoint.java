package weka.clusterers;

public class EM_JChordEntrypoint {
	public static void main(String[] argv) {
		EM em = new EM();
		try {
			em.workaroundForPrivateEMInit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
