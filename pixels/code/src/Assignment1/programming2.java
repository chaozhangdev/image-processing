package Assignment1;

import java.io.*;

public class programming2 {
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("D:/rose.raw"); // file path to read
			out = new FileOutputStream("D:/rose+.raw"); // file path to write
			int i = 0, j = 0;
			int[][] image = new int[256][256];
			int r,t;
			int order=3,sum=0; // variable order is the numbers of the lowest order bits
			int[] b = new int[8];
			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++)
					image[i][j] = in.read();		
			int[][] image1 = new int[256][256];
			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++) {
					t = image[i][j];					
					for (int k = 0; k < 8; k++) {  // here is to change decimalism data into binary array b[]
						r = t % 2;
						b[k] = r;
						t /= 2;						
					}
					for (int k = 0; k < order; k++) b[k]=0;	// here is to set 0 according to requirements
					for (int k = 0; k < 8; k++) sum+=(Math.pow(2,k))*b[k]; // put processed binary data array into output array image1
					image1[i][j]=sum;				
					sum=0;
				}
			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++)
					out.write(image1[i][j]);		
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
