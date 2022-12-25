/**
 * 
 * class that constructs pixel image which represents values of each pixel in the image
 *
 */
public class Pixel {
	private int[] value;
	
	/**
	 * 
	 * @param grayvalue true if image is grayscale and false if image is color
	 * constructor for pixels that will construct different pixel based on if its a grayscale pixel or color pixel
	 */
	public Pixel(int grayvalue){
		value = new int[1];
		value[0] = grayvalue;
		
	}
	public Pixel(int red, int green, int blue){
		value = new int[3];
		value[0] = red;
		value[1] = green;
		value[2] = blue;
		
	}
	/**
	 * 
	 * @return value of pixel
	 */
	public int[] getValue(){
		return value;
	}
	/**
	 * overrides equals method
	 */
	@Override
	public boolean equals(Object other){
		if (this==other) {
			return true;
		}
	     if (this.getClass() != other.getClass()) {
	    	 return false;
	     }
	     Pixel pixel = (Pixel) other ;
	     return this.value.equals(pixel.getValue());
		
	}
	@Override
	/**
	 * overrides tostring with rgb seperated with letters 
	 */
	public String toString(){
		if(value.length == 1) {
			return Integer.toString(value[0]);
		}
		else {
			return "R" + Integer.toString(value[0]) +"#G" + Integer.toString(value[1]) + "#B" + Integer.toString(value[2]);
		}
	}
	/**
	 * overides clone method
	 */
	@Override
	public Pixel clone() { 
		Pixel pixel;
		if(value.length == 1) {
			pixel = new Pixel(value[0]);
		}
		else {
			pixel = new Pixel(value[0], value[1], value[2]);
		}
		return pixel;
	}
}

