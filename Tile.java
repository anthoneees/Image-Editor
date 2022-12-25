/**
 * 
 * creates tile object which is an array filled with 16 pixel objects
 *
 */
public class Tile {
	private Pixel[] block;
	
	/**
	 * constructor for tile that is just an array of 16 pixels
	 */
	public Tile() {
		block = new Pixel[16];
	}
	
	/**
	 * getPixel essentially turns the 1d array into a 2d array
	 * @param y the y value in the array
	 * @param x the x value in the array
	 * @return returns the pixel at the given point
	 */
	public Pixel getPixel(int y, int x) {
		return block[(y)*4+(x)];
	}
	
	/**
	 * 
	 * @param y the y value in the array
	 * @param x the x value in the array
	 * @param p pixel to set at the position given
	 */
	public void setPixel(int y, int x, Pixel p) {
		block[(y)*4+(x)] = p;
	}
	/**
	 * Overrides equals method
	 */
	@Override
	public boolean equals(Object other){
		if (this==other) {
			return true;
		}
	     if (this.getClass() != other.getClass()) {
	    	 return false;
	     }
	     Tile tile = (Tile) other ;
	     return this.block.equals(tile.block);
		
	}
	/**
	 * overrides to String by seperating all pixels in tile seperated by comma
	 */
	@Override
	public String toString() {
		String temp = "";
		for(int i = 0 ; i<15; i++) {
			temp += block[i].toString() + ", ";
		}
		temp += block[15].toString();
		return temp;
	}
	/**
	 * overrides clone 
	 */
	@Override
	public Tile clone() { 
		Tile tile = new Tile();
		return tile;
	}
}
