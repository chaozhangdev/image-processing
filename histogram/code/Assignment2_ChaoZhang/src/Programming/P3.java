package Programming;

import java.io.*;

public class P3 {

	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("D:/circuit.raw");
			out = new FileOutputStream("D:/circuit+.raw");
			int i, j;
			int[][] image_in = new int[440][455];
			int[][] image_out = new int[440][455];

			for (i = 0; i < 440; i++)
				for (j = 0; j < 455; j++)
					image_in[i][j] = in.read(); // Read the input image into
												// image_in[][]

			int k1, k2, counter;
			int[] mid = new int[9];

			for (i = 1; i < 439; i++) // i from 1 to 438 and j from 1 to 453 is
										// to ignore some pixels in four corners
										// because these pixels can not be
										// proceed by filter as boundaries
				for (j = 1; j < 454; j++) {

					counter = 0;
					for (k1 = -1; k1 <= 1; k1++)
						for (k2 = -1; k2 <= 1; k2++)
							mid[counter++] = image_in[i + k1][j + k2];
					// put the filter's 9 pixels in mid[] to sort

					image_out[i][j] = sort(mid); // class sort is to sort the
													// mid[] and return the
													// median number of these 9
													// pixels in the filter
				}

			for (i = 0; i < 440; i++)
				for (j = 0; j < 455; j++)
					out.write(image_out[i][j]); // Write the result image
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

	public static int sort(int[] mid) { // bubble sort
		int sign = 1, t, kk;
		while (sign != 0) {
			sign = 0;
			for (kk = 0; kk < 8; kk++)
				if (mid[kk] > mid[kk + 1]) {
					t = mid[kk + 1];
					mid[kk + 1] = mid[kk];
					mid[kk] = t;
					sign = 1;
				}
		}

		return mid[4]; // return the median number
	}
}
