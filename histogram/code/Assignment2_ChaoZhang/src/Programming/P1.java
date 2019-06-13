package Programming;

import java.io.*;

public class P1 {

	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("D:/ct.raw");
			out = new FileOutputStream("D:/ct+.raw");
			int i, j;
			int[][] image_in = new int[256][256];

			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++)
					image_in[i][j] = in.read(); // Read the input image into
												// image_in[ ][ ]

			int[][] image_out = new int[256][256];
			int[] h = new int[256];
			int[] H = new int[256];

			for (i = 0; i < 256; i++) // initialization of h[]
				h[i] = 0;

			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++)
					h[image_in[i][j]]++; // Compute the histogram of the input
											// image and store it in h[ ]

			H[0] = h[0];
			for (i = 1; i < 256; i++) // Compute the cumulative histogram and
										// store it in H[]
				H[i] = H[i - 1] + h[i];

			double s = 0.00389; // get the scaling factor S
			// 0.00389 is k-1/m*n which is 255(8 bits grayscale)/256*256

			for (i = 0; i < 256; i++) // Normalize H[] with the scaling factor S
				H[i] *= s;

			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++) // get the image_out[] from the H[]
					image_out[i][j] = H[image_in[i][j]];

			for (i = 0; i < 256; i++)
				for (j = 0; j < 256; j++)
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
