//Simple example of using the Storm Center coordinate conversion class
public class Sample {

	public static void main(String[] args) {
		SCCoordinates cordConverter = new SCCoordinates();
		System.out.println(cordConverter.XtoLongitude(-8237085.721248278));
		System.out.println(cordConverter.YtoLatitude(5033834.9049384855));
		System.out.println(cordConverter.LongitudetoX(-73.995));
		System.out.println(cordConverter.LatitudetoY(41.145556)); //Note - modified version per Kubra
	}

}
