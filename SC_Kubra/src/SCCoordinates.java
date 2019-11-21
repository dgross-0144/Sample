/**
 * Created: 05/01/2018
 * Author: D.Gross
 * Purpose: Conversion utility between Lats/Longs and PowerOn's X,Y
 * 
 * Comments:
 * These functions take angles parameter in degrees and lengths in meters 
 * Use constructor to shift origin to match Oracle's configuration
 * 
 * Changes:
 * 06/21/2018 - Switch LatitudetoY() to use a modified(???) Elliptical calc per Kubra's request.
 */
public class SCCoordinates {
	private static final double RADIUS_MAJOR = 6378137.0; 
	private static double RADIUS_MINOR = 6356752.3142;
	private double YOrigin = 0; 
	private double XOrigin = 0;
	
	  public SCCoordinates() {
	  }

	  // Adjust latitude, longitude of origin away from central meridian and equator (in meters)
	  public SCCoordinates(double XOrg, double YOrg) {
	    YOrigin = YOrg; 
	    XOrigin = XOrg; 
	  }

	  public double YtoLatitude(double Y) {
		  double adjustedY = Y + YOrigin;
		  return Math.toDegrees(Math.atan(Math.exp(adjustedY / RADIUS_MAJOR)) * 2 - Math.PI/2);
	  }
	  
	  public double XtoLongitude(double X) {
		  double adjustedX = X + XOrigin;
		  return Math.toDegrees(adjustedX / RADIUS_MAJOR);
	  }
	  
	  ////Original version
	  //public double LatitudetoY(double Latitude) {
	  //  double dCalc = Math.log(Math.tan(Math.PI / 4 + Math.toRadians(Latitude) / 2));
	  //  double Y = dCalc * RADIUS_MAJOR;
	  //  return Y - YOrigin;
	  //}  

	  //Uses a modified version of the elliptical approximation to match Kubra's Geo issue
	  public double LatitudetoY(double Latitude) {
		  double earthNormalized = 1.0 - Math.pow(RADIUS_MINOR / RADIUS_MAJOR, 2);
		  double EarthProj = Math.sqrt(earthNormalized) * 
		          Math.sin( Math.toRadians(Latitude));
		  EarthProj = Math.pow(((1.0 - EarthProj) / (1.0 + EarthProj)), 
		          0.5 * Math.sqrt(earthNormalized));
		  double EarthProjNormalized = 
		          Math.tan(0.5 * ((Math.PI * 0.5) - Math.toRadians(Latitude))) / EarthProj;
		  double Y =  (-1) * RADIUS_MAJOR * Math.log(EarthProjNormalized);
		  return Y - YOrigin;
	  }  
	  
	  public double LongitudetoX(double Longitude) {
		  double X = Math.toRadians(Longitude) * RADIUS_MAJOR;
		  return X - XOrigin;
	  }
	  

}
