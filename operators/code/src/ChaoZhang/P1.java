package ChaoZhang;

import java.io.*;

public class P1 {
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("C:/Users/CHAO/Desktop/building.raw"); // file path to read
			out = new FileOutputStream("C:/Users/CHAO/Desktop/building+.raw"); // file path to write
			int i = 0, j = 0;
			int[][] image_in = new int[420][560];
			int[][] image_out_x = new int[420][560]; // compute the horizontal edge image
			int[][] image_out_y = new int[420][560]; // compute the vertical edge image
			int[][] image_out_g = new int[420][560]; // compute the gradient image
			int[][] image_out_g_t = new int[420][560]; // compute the thresholded gradient image

			for (i = 0; i < 420; i++) // here is to read the binary data into
										// array image[][]
				for (j = 0; j < 560; j++)
					image_in[i][j] = in.read();

			int x, y; // x is horizontal edge, y is vertical edge
			double g; // g is gradient

			for (i = 1; i < 419; i++)
				for (j = 1; j < 559; j++) {
					x = image_in[i + 1][j + 1] - image_in[i - 1][j + 1] + 2 * image_in[i + 1][j]
							- 2 * image_in[i - 1][j] + image_in[i + 1][j - 1] - image_in[i - 1][j - 1];
					// compute x according the Hpx in Sobel Operators

					y = image_in[i - 1][j - 1] - image_in[i - 1][j + 1] + 2 * image_in[i][j - 1]
							- 2 * image_in[i][j + 1] + image_in[i + 1][j - 1] - image_in[i + 1][j + 1];
					// compute y according the Hpy in Sobel Operators

					x = Math.abs(x);
					image_out_x[i][j] = x; // put calculated x into the output array

					y = Math.abs(y);
					image_out_y[i][j] = y; // put calculated y into the output array

					g = Math.sqrt(x * x + y * y);
					g = Math.abs(g);
					image_out_g[i][j] = (int) g; // put calculated g into the output array

					if (g > 128)
						image_out_g_t[i][j] = 0; // calculated g with a threshold of Te = 128 and put it into the output
													// array
					else
						image_out_g_t[i][j] = 255;
				}

			/*
			 * for (i = 0; i < 420; i++) for (j = 0; j < 560; j++) // write out the
			 * processed array into the image out.write(image_out_x[i][j]); // this one is
			 * for the horizontal edge image
			 * 
			 * /* for (i = 0; i < 420; i++) //write out the processed array into the image
			 * for (j = 0; j < 560; j++) // this one is for the vertical edge image
			 * out.write(image_out_y[i][j]);
			 * 
			 * for (i = 0; i < 420; i++) //write out the processed array into the image for
			 * (j = 0; j < 560; j++) // this one is for the gradient image
			 * out.write(image_out_g[i][j]);
			 */
			for (i = 0; i < 420; i++) // write out the processed array into the image for
				for (j = 0; j < 560; j++) // this one is for the thresholded gradient image
					out.write(image_out_g_t[i][j]);

			// because it only contains one output stream, so we should write out the image
			// (horizontal edge image, vertical edge image, gradient image, thresholded
			// gradient image) one by one

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
