package Assignment1;

import java.io.*;

public class programming1 {
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("D:/rose.raw"); // file path to read
			out = new FileOutputStream("D:/rose+.raw"); // file path to write
			int i = 0, j = 0;
			int[][] image = new int[256][256];

			for (i = 0; i < 256; i++) // here is to read the binary data into
										// array image[][]
				for (j = 0; j < 256; j++)
					image[i][j] = in.read();

			int[][] image1 = new int[32][32]; // here is to change the pixels
												// 256x256 into different
												// requirements
			for (i = 0; i < 32; i++) // the method is to ignore ever x pixels by
										// rows and columns for different
										// requirements
				for (j = 0; j < 32; j++)
					image1[i][j] = image[i * 8][j * 8];

			for (i = 0; i < 32; i++) // here is to output the image array into
										// .raw file
				for (j = 0; j < 32; j++)
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
