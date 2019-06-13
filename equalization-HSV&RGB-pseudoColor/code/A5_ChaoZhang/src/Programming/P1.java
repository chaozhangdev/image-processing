package Programming;

import java.io.*;

public class P1 {

	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("C:/Users/CHAO/Desktop/summer_deck2.raw");
			out = new FileOutputStream("C:/Users/CHAO/Desktop/summer_deck2+.raw");
			int i, j, k;
			int[][][] image_in = new int[3][400][300];

			for (k = 0; k < 3; k++)
				for (i = 0; i < 400; i++)
					for (j = 0; j < 300; j++)
						image_in[k][i][j] = in.read(); // Read the input image into
														// image_in[][][]

			int[][][] image_out = new int[3][400][300]; // 3 is three channels for red, green and blue
			int[] h = new int[256];    // 
			int[] H = new int[256];

			for (k = 0; k < 3; k++) {

				for (i = 0; i < 256; i++) // initialization of h[]
					h[i] = 0;

				for (i = 0; i < 400; i++)
					for (j = 0; j < 300; j++)
						h[image_in[k][i][j]]++; // Compute the histogram of the input
												// image and store it in h[]

				H[0] = h[0];
				for (i = 1; i < 256; i++) // Compute the cumulative histogram and
											// store it in H[]
					H[i] = H[i - 1] + h[i];

				double s = 0.002125; // get the scaling factor S
				// 0.00213 is k-1/m*n which is 255(8 bits grayscale per channel)/400*300

				for (i = 0; i < 256; i++) // Normalize H[] with the scaling factor S
					H[i] *= s;

				for (i = 0; i < 400; i++)
					for (j = 0; j < 300; j++) // get the image_out[] from the H[]
						image_out[k][i][j] = H[image_in[k][i][j]];
			}

			for (k = 0; k < 3; k++)
				for (i = 0; i < 400; i++)
					for (j = 0; j < 300; j++)
						out.write(image_out[k][i][j]); // Write the result image
														// image_out[][]

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
