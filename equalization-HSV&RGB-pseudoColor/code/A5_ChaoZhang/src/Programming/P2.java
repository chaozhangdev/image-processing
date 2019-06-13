package Programming;

import java.io.*;

public class P2 {

	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("C:/Users/CHAO/Desktop/summer_deck2.raw");
			out = new FileOutputStream("C:/Users/CHAO/Desktop/summer_deck2++.raw");
			int i, j, k;
			int[][][] image_in = new int[3][400][300];

			for (k = 0; k < 3; k++)
				for (i = 0; i < 400; i++)
					for (j = 0; j < 300; j++)
						image_in[k][i][j] = in.read(); // Read the input image into
														// image_in[][][]

			int[][][] image_out = new int[3][400][300]; // 3 is three channels for red, green and blue
			double[][][] hsv = new double[3][400][300];

			// RGB to HSV
			int r, g, b;

			double rr, gg, bb;
			double h = 0, s, v;
			double min, max, delta;

			for (i = 0; i < 400; i++)
				for (j = 0; j < 300; j++)

				{
					// do the calculation according to the mathematical formula

					r = image_in[0][i][j];
					g = image_in[1][i][j];
					b = image_in[2][i][j];

					rr = r / 255.0;
					gg = g / 255.0;
					bb = b / 255.0;

					min = Math.min(Math.min(rr, gg), bb);
					max = Math.max(Math.max(rr, gg), bb);

					delta = max - min;

					// get V
					v = max;

					// get S
					if (max != 0)
						s = delta / max;
					else {
						s = 0;
						h = -1;
					}

					// get H
					if (rr == max)
						h = (gg - bb) / delta;
					if (gg == max)
						h = 2 + (bb - rr) / delta;
					if (bb == max)
						h = 4 + (rr - gg) / delta;

					h *= 60;

					if (h < 0)
						h += 360;

					hsv[0][i][j] = h;
					hsv[1][i][j] = s;
					hsv[2][i][j] = v;

				}

			// histogram equalization of values (v)

			int[] hh = new int[256];
			int[] H = new int[256];
			int vvv;

			// initialization of h[]
			for (i = 0; i < 256; i++)
				hh[i] = 0;

			// Compute the histogram of values and store it in h[]
			for (i = 0; i < 400; i++)
				for (j = 0; j < 300; j++) {
					vvv = (int) (hsv[2][i][j] * 255.0);
					hh[vvv] += 1;
				}

			// Compute the cumulative histogram and store it in H[]
			H[0] = hh[0];
			for (i = 1; i < 256; i++)
				H[i] = H[i - 1] + hh[i];

			// get the scaling factor S 0.002 is k-1/m*n which is 255/400*300

			double ss = 0.002;

			// Normalize H[] with the scaling factor S
			for (i = 0; i < 256; i++)
				H[i] *= ss;

			// get the processed values array from the H[]
			for (i = 0; i < 400; i++)
				for (j = 0; j < 300; j++)

				{
					vvv = (int) (hsv[2][i][j] * 255.0);
					hsv[2][i][j] = H[vvv] / 255.0;
				}

			// HSV to RGB

			rr = 0;
			gg = 0;
			bb = 0;

			for (i = 0; i < 400; i++)
				for (j = 0; j < 300; j++)

				{

					h = hsv[0][i][j];
					s = hsv[1][i][j];
					v = hsv[2][i][j];

					double c, x, m;

					// do the calculation according to the mathematical formula

					c = (v * s);
					x = c * (1 - Math.abs((h / 60) % 2 - 1));
					m = v - c;

					if (h >= 0 && h < 60) {
						rr = c;
						gg = x;
						bb = 0;
					}
					if (h >= 60 && h < 120) {
						rr = x;
						gg = c;
						bb = 0;
					}
					if (h >= 120 && h < 180) {
						rr = 0;
						gg = c;
						bb = x;
					}
					if (h >= 180 && h < 240) {
						rr = 0;
						gg = x;
						bb = c;
					}
					if (h >= 240 && h < 300) {
						rr = x;
						gg = 0;
						bb = c;
					}
					if (h >= 300 && h < 360) {
						rr = c;
						gg = 0;
						bb = x;
					}

					r = (int) ((rr + m) * 255.0);
					g = (int) ((gg + m) * 255.0);
					b = (int) ((bb + m) * 255.0);

					image_out[0][i][j] = r;
					image_out[1][i][j] = g;
					image_out[2][i][j] = b;

				}

			// Write the result image_out[][]

			for (k = 0; k < 3; k++)
				for (i = 0; i < 400; i++)
					for (j = 0; j < 300; j++)
						out.write(image_out[k][i][j]);

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
