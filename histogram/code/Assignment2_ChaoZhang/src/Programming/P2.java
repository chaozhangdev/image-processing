package Programming;

import java.io.*;

public class P2 {

	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("D:/testpattern.raw");
			out = new FileOutputStream("D:/testpattern+.raw");
			int i, j;
			int[][] image_in = new int[464][528];
			int[][] image_out = new int[464][528];

			for (i = 0; i < 464; i++)
				for (j = 0; j < 528; j++)
					image_in[i][j] = in.read(); // Read the input image into
												// image_in[ ][ ]

			// h1 is the first filter matrix, h2 is the second filter matrix,
			// which are the two 3*3 filters
			double[][] h1 = { { 0.111, 0.111, 0.111 }, { 0.111, 0.111, 0.111 }, { 0.111, 0.111, 0.111 } };
			double[][] h2 = { { 0.075, 0.125, 0.075 }, { 0.125, 0.200, 0.125 }, { 0.075, 0.125, 0.075 } };

			int k1, k2;
			double sum;
			for (i = 1; i < 464; i++) // i and j from 1 to 498 is to ignore some
										// pixels in four corners because these
										// pixels can not be proceed by filter as boundaries
				for (j = 1; j < 528; j++) {
					sum = 0; // initialization of sum for every pixel
					for (k1 = -1; k1 <= 1; k1++)
						for (k2 = -1; k2 <= 1; k2++)
							sum += image_in[i + k1][j + k1] * h2[k1 + 1][k2 + 1];
					// use filter matrix h1 or h2 to filter the image
					// respectively
					image_out[i][j] = (int) sum;
				}

			for (i = 0; i < 500; i++)
				for (j = 0; j < 500; j++)
					out.write(image_out[i][j]); // Write the result image
												// image_out[ ][ ]

		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}
}
