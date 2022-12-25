import java.util.Scanner;
import java.io.File;

/**
 * 
 * Creates image object with fields as array of tiles, width height, and grayscale
 *
 */
public class Image{
	private Tile[] tileArray;
	private int width = 0;
	private int height = 0;
	private boolean grayscale;
	/**
	 * 
	 * @param height
	 * @param width
	 * @param grayscale
	 * constructor for Image
	 */
	public Image(int height, int width, boolean grayscale) {
		tileArray = new Tile[(height*width)/16];
		this.height = height;
		this.width = width;
		this.grayscale = grayscale;
	}
	/**
	 * 
	 * @param filename given filename of inputImage construct image object by reading file
	 * also uses try catch to catch any possible file exceptions
	 */
	public Image(String filename){
		try {
			String userWord;
			File file = new File(filename);
			Scanner scnr = new Scanner(file);
			userWord = scnr.next();
			width = Integer.parseInt(scnr.next());
			height = Integer.parseInt(scnr.next());
			tileArray = new Tile[(height*width)/16];
			scnr.next();
			for(int i =0; i <tileArray.length; i++) {
				Tile temp = new Tile();
				tileArray[i] = temp;
			}
			if(userWord.equals("P2")) {
				grayscale = true;
			}
			else{
				grayscale = false;
				
			}
			if(grayscale == true) {
				for(int y = 0; y<height; y++) {
					for (int x =0; x<width; x++) {
						Pixel tempPixel = new Pixel(Integer.parseInt(scnr.next()));
						setPixel(y, x, tempPixel);
					}
				}
			}
			else {
				for(int y = 0; y<height; y++) {
					for (int x =0; x<width; x++) {
						Pixel tempPixel = new Pixel(Integer.parseInt(scnr.next()),Integer.parseInt(scnr.next()),Integer.parseInt(scnr.next()));
						setPixel(y, x, tempPixel);
					}
				}
			}
			scnr.close();
		} 
		
		catch (Exception e){
			System.out.println("Error in image constructor");
		}
	}
	/**
	 * overrides clone method
	 */
	@Override
	public Image clone() { 
		Image image = new Image(height, width, grayscale);
		return image;
	}
	/**
	 * 
	 * @return getter for width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * getter for height
	 * @return returns height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * accesses pixel from image class 
	 * @param y y coordinate to find pixel
	 * @param x x coordinate to find pixel
	 * @return returns pixel 
	 */
	public Pixel getPixel(int y, int x) {
		int tile = (y/4) * (getWidth()/4) + (x/4);
		int pixely = (y%4);
		int pixelx = (x%4);
		return tileArray[tile].getPixel(pixely, pixelx);
	}
	/**
	 * setter for pixel 
	 * @param y
	 * @param x
	 * @param p
	 */
	public void setPixel(int y, int x, Pixel p) {
		int tile = (y/4) * (getWidth()/4) + (x/4);
		int pixely = (y%4);
		int pixelx = (x%4);
		tileArray[tile].setPixel(pixely, pixelx, p);
	}
	/**
	 * 
	 * @param factor decides how to scale image
	 * @return returns image after being scaled
	 */
	public Image scale(int factor) {
		Image imageChange = new Image(height, width, grayscale);
		if(factor > 0) {
			imageChange = new Image(height*factor, width*factor, grayscale);
			for(int i =0; i <imageChange.tileArray.length; i++) {
				Tile temp = new Tile();
				imageChange.tileArray[i] = temp;
			}
			for(int x = 0; x<width; x++) {
				for(int y = 0; y<height; y++) {
					for(int x1 = x* factor; x1<x*factor + factor; x1++) {
						for(int y1 = y * factor; y1<y*factor + factor; y1++) {
							imageChange.setPixel(y1, x1, getPixel(y,x));
						}
					}
				}
			}
		}
		else if(factor<0) {
			factor *= -1;
			imageChange = new Image(height/factor, width/factor, grayscale);
			for(int i =0; i <imageChange.tileArray.length; i++) {
				Tile temp = new Tile();
				imageChange.tileArray[i] = temp;
			}
			for(int x = 0; x<width; x+=factor) {
				for(int y = 0; y<height; y+=factor) {
					imageChange.setPixel(y/factor, x/factor, getPixel(y,x));
				}
			}	
		}
		return imageChange;
	}
	/**
	 * 
	 * @param topY y coordinate for origin of image
	 * @param topX x coordinate for origin of image
	 * @param height height of image
	 * @param width width of image
	 * @return returns image after crop method
	 */
	public Image crop(int topY, int topX, int height, int width) {
		Image imageChange = new Image(height, width, grayscale);
		for(int i =0; i <imageChange.tileArray.length; i++) {
			Tile temp = new Tile();
			imageChange.tileArray[i] = temp;
		}
		for(int y = topY; y<topY+height; y++) {
			for(int x = topX; x<topX+width; x++) {
				imageChange.setPixel(y-topY, x-topX, getPixel(y,x));
			}
		}
		return imageChange;
	}
	/**
	 * 
	 * @param direction either horizontal or vertical decides which way to flip
	 * @return returns image after flip
	 */
	public Image flip(String direction) {
		Image imageChange = new Image(height, width, grayscale);
		for(int i =0; i <imageChange.tileArray.length; i++) {
			Tile temp = new Tile();
			imageChange.tileArray[i] = temp;
		}
		if(direction.equals("horizontal")) {
			for(int y = 0; y<getHeight(); y++) {
				for(int x = getWidth()-1; x>=0; x--) {
					imageChange.setPixel(y, x, getPixel(y, getWidth()-1-x));
				}
			}
		}
		else {
			for(int y = getHeight()-1; y>=0; y--) { 
				for(int x = 0; x<getWidth(); x++) {
					imageChange.setPixel(y, x, getPixel(getHeight()-1-y, x));
				}
			}
		}
		return imageChange;
	}
	/**
	 * 
	 * @param clockwise true if being turned clockwise false if being turned counterclockwise
	 * @return returns image after being rotated
	 */
	public Image rotate(boolean clockwise) {
		Image imageChange = new Image(width, height, grayscale);
		for(int i =0; i <imageChange.tileArray.length; i++) {
			Tile temp = new Tile();
			imageChange.tileArray[i] = temp;
		}
		if (clockwise == true) {
			for(int x = 0; x<getWidth(); x++) {
				for(int y = getHeight()-1; y>=0; y--) {
					imageChange.setPixel(x, getHeight()-1-y, getPixel(y,x));
				}
			}
		}
		else {
			for(int x = getWidth()-1; x>=0; x--) {
				for(int y = 0; y<getHeight(); y++) {
					imageChange.setPixel(getWidth()-1-x, y, getPixel(y,x));
				}
			}
		}
		return imageChange;
	}
	/**
	 * override for equals method
	 */
	@Override
	public boolean equals(Object other){
		if (this==other) {
			return true;
		}
	     if (this.getClass() != other.getClass()) {
	    	 return false;
	     }
	     Image image = (Image) other ;
	     return this.tileArray.equals(image.tileArray);
		
	}
}
