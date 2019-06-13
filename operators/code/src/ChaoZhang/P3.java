package ChaoZhang;

import java.io.*;

public class P3 {
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("C:/Users/CHAO/Desktop/lines.raw"); // file path to read
			out = new FileOutputStream("C:/Users/CHAO/Desktop/lines+.raw"); // file path to write

			int i, j, u, v;
			int x, y;   
			int[][] image_in = new int[256][256];    
			int xCtr, yCtr, nAng, nRad, cRad;  
			double dAng, dRad;
			int[][] houghArray;

			xCtr = 128;   //center x is 128
			yCtr = 128;   //center y is 128
			nAng = 256;    //total steps of angle are 256
			dAng = Math.PI / nAng;   //the change of angle
			nRad = 256;    //total steps of radial are 256
			cRad = 100 / 2;   //the center of the radial
			double rMax = Math.sqrt(xCtr * xCtr + yCtr * yCtr);  	
			dRad = (2.0 * rMax) / nRad;
			houghArray = new int[nAng][nRad];  // hough accumulator 

			for (i = 0; i < 256; i++) // here is to read the binary data into
										// array image[][]
				for (j = 0; j < 256; j++)
					image_in[i][j] = in.read();

			for (u = 0; u < 256; u++)
				for (v = 0; v < 256; v++)
					if (image_in[u][v] < 255) {
						x = u - xCtr;
						y = v - yCtr;
						
						for (int k = 0; k < nAng; k++) {
							double theta = dAng * k;
							int r = cRad + (int) Math.rint((x * Math.cos(theta) + y * Math.sin(theta)) / dRad);  //get r which is  x ¡¤ cos(¦È) + y ¡¤ sin(¦È) 
							if (r >= 0 && r < nRad)
								houghArray[k][r]++;  //accumulate the value
						}
					}

			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++)
				{
					if (houghArray[i][j]<50) houghArray[i][j]=0;   //set the threshold value to 50 
					out.write(255-houghArray[i][j]);  //invert the image for better analyzing
				}
					

		} finally

		{
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
