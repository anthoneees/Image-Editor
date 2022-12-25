import java.io.PrintWriter;
import java.io.File;
/**
 * 
 * Class ImagingApp that is the access point for the program
 * takes in inputImage of file and outImage of file as fields 
 */
public class ImagingApp {
	private static Image inputImage;
	private static Image outputImage;
	
	/**
	 * 
	 * @param args arguments that will be taken from terminal
	 * main will take arguments from terminal and decide what to do with them
	 */
	public static void main(String[] args) {
		/*
		 * try catch for any ioexceptions, as well as if statement to check that there are correct amount of arguments
		 */
		try {
			if (args.length < 4 || args.length > 7) {
				return;
			}
			else {
				/**
				 * for every different method call, create a new image object from input file, set output file to the image after edit was made
				 * save image to the new image object with the file name of the output file
				 */
				inputImage = new Image(args[0]);
				if(args[2].equals("scale")) {
					outputImage = inputImage.scale(Integer.parseInt(args[3]));
					saveImage(outputImage, args[1]);
				}
				else if(args[2].equals("crop")) {
					outputImage = inputImage.crop(Integer.parseInt(args[3]) , Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
					saveImage(outputImage, args[1]);
				}
				else if(args[2].equals("flip")) {
					outputImage = inputImage.flip(args[3]);
					saveImage(outputImage, args[1]);
				}
	
				else if(args[2].equals("rotate")) {
					if (args[3].equals("clockwise")) {
						outputImage = inputImage.rotate(true);
					}
					else if (args[3].equals("counterclockwise")) {
						outputImage = inputImage.rotate(false);
					}
					else {
						return;
					}
					saveImage(outputImage, args[1]);
				}
				else {
					return;
				}
	
			}
		
		}
		catch(Exception e) {
			return;
		}
	}
	/*
	 * @param img object which will hold image after edits
	 * @param filename which will give us the file name to save img object to
	 * @return returns true if saveImage was successful and false if exception was thrown
	 */
	public static boolean saveImage(Image img, String filename){
		/*
		 * try catch to handle any file errors
		 * this method checks if the file already exists, if it does not create a new file
		 * it the prints to the file the fields that the img object holds including grayscale, width, height, and the pixel values
		 */
		try {
			File file = new File(filename);
			boolean exists = file.exists();
			if(exists == false) {
				file.createNewFile();
			}
			PrintWriter writer = new PrintWriter(file);
			if(img.getPixel(0, 0).getValue().length == 1) {
				writer.print("P2\n" + img.getWidth() + " " + img.getHeight() + "\n255\n");
				for(int y = 0; y< img.getHeight(); y++) {
					for(int x = 0; x<img.getWidth(); x++) {
						writer.print(img.getPixel(y, x).getValue()[0] + " ");
					}
					writer.print("\n");
				}
			}
			else {
				writer.print("P3\n" + img.getWidth() + " " + img.getHeight() + "\n255\n");
				for(int y = 0; y< img.getHeight(); y++) {
					for(int x = 0; x<img.getWidth(); x++) {
						Pixel pixel = img.getPixel(y,  x);
						writer.print(pixel.getValue()[0] + " " + pixel.getValue()[1] + " " + pixel.getValue()[2]);
						writer.print("\n");
					}
				}
			}
			writer.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	/*
	 * does the same thing as other saveImage except takes in compressedImage as object
	 */
	public static boolean saveImage(CompressedImage img, String filename){
		try {
			File file = new File(filename);
			boolean exists = file.exists();
			if(exists == false) {
				file.createNewFile();
			}
			PrintWriter writer = new PrintWriter(file);
			if(img.getPixel(0, 0).getValue().length == 1) {
				writer.print("P2\n" + img.getWidth() + " " + img.getHeight() + "\n255\n");
				for(int y = 0; y< img.getHeight(); y++) {
					for(int x = 0; x<img.getWidth(); x++) {
						writer.print(img.getPixel(y, x).getValue()[0] + " ");
					}
					writer.print("\n");
				}
			}
			else {
				writer.print("P3\n" + img.getWidth() + " " + img.getHeight() + "\n255\n");
				for(int y = 0; y< img.getHeight(); y++) {
					for(int x = 0; x<img.getWidth(); x++) {
						Pixel pixel = img.getPixel(y,  x);
						writer.print(pixel.getValue()[0] + " " + pixel.getValue()[1] + " " + pixel.getValue()[2]);
						writer.print("\n");
					}
				}
			}
			writer.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
