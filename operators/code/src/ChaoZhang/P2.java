package ChaoZhang;

import java.io.*;

public class P2 {
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("C:/Users/CHAO/Desktop/moon.raw"); // file path to read
			out = new FileOutputStream("C:/Users/CHAO/Desktop/moon+.raw"); // file path to write
			int i = 0, j = 0;
			int[][] image_in = new int[528][464];
			int[][] image_out = new int[528][464];

			for (i = 0; i < 528; i++) // here is to read the binary data into
										// array image[][]
				for (j = 0; j < 464; j++)
					image_in[i][j] = in.read();

			int w = 1; // w is the coefficient and can be changed to different value for the test
			int s;
			int[][] mask = new int[][] { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } }; // mask is the Laplacian sharpening
																					// filter

			for (i = 1; i < 527; i++)
				for (j = 1; j < 463; j++) {
					s = 0; // s is the sum of the filter
					for (int a = -1; a < 2; a++)
						for (int b = -1; b < 2; b++) {
							s += image_in[i + a][j + b] * mask[1 + a][1 + b]; // filter the pixel with filter
						}

					s = image_in[i][j] - w * s; // calculate the new value according to the function in the book in
												// image sharping with laplacian sharping filter

					image_out[i][j] = Math.abs(s); // s should be the absolute value before be writen into output array
				}

			for (i = 0; i < 528; i++)
				for (j = 0; j < 464; j++)
					out.write(image_out[i][j]);

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

