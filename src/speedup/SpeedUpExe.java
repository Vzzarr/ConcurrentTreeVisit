package speedup;

public class SpeedUpExe {

	public static void main(String[] args) throws InterruptedException {
		SpeedUp1LEF su1 = new SpeedUp1LEF();
		SpeedUp2WS su2 = new SpeedUp2WS();
		SpeedUp3FJ su3 = new SpeedUp3FJ();
		SpeedUp4SI su4 = new SpeedUp4SI();
		
		System.out.println("SpeedUp HWJ1");
		su1.execute(15, "complete");
		System.out.println("\n\n\nSpeedUp HWJ2");
		su2.execute(15, "complete");
		System.out.println("\n\n\nSpeedUp HWJ3");
		su3.execute(15, "complete");
		System.out.println("\n\n\nSpeedUp HWJ4");
		su4.execute(15, "complete");

	}

}
