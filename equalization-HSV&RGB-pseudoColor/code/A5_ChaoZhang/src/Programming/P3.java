package Programming;

import java.io.*;

public class P3 {

	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("C:/Users/CHAO/Desktop/tempusa.raw");
			out = new FileOutputStream("C:/Users/CHAO/Desktop/tempusa+.raw");
			int i, j, k;
			int[][] image_in = new int[640][420];

			for (i = 0; i < 640; i++)
				for (j = 0; j < 420; j++)
					image_in[i][j] = in.read(); // Read the input image into image_in[][]

			int[][][] image_out = new int[3][640][420]; // 3 is three channels for red, green and blue

			int[][] table = new int[256][3]; // generate a pseudo color look-up table with 256 entries

			for (i = 0; i < 256; i++) // the initialization of the pseudo color look-up table
				for (j = 0; j < 3; j++)
					table[i][j] = 0;

			table[0][0] = 255; // set red
			table[85][1] = 255; // set green
			table[170][2] = 255; // set blue

			// the pseudo color look-up table is a loop from red to green to blue and back
			// to blue
			// for the grayscale is from 0 to 255, I set the step is 3 in this pseudo color
			// table
			// from [255][0][0] , [252][3][0] , [249][6][0] ... to [0][255][0] and from
			// [0][255][0] , [0][249][3] , [0][246][6] to [0][0][255] and then back to
			// [255][0][0]

			// generate the color spectrum from red to green which is from [255][0][0] to
			// [0][255][0]

			for (i = 1; i <= 84; i++) {
				table[i][0] = table[i - 1][0] - 3;
				table[i][1] = table[i - 1][1] + 3;
			}

			// generate the color spectrum from green to blue which is from [0][255][0] to
			// [0][0][255]

			for (i = 86; i <= 169; i++) {
				table[i][1] = table[i - 1][1] - 3;
				table[i][2] = table[i - 1][2] + 3;
			}

			// generate the color spectrum from blue back to red which is from [0][0][255]
			// to [255][0][0]

			for (i = 171; i <= 255; i++) {
				table[i][0] = table[i - 1][0] + 3;
				table[i][2] = table[i - 1][2] - 3;
			}

			// switch the grayscale image to the pseudo color image
			for (k = 0; k < 3; k++)
				for (i = 0; i < 640; i++)
					for (j = 0; j < 420; j++)
						image_out[k][i][j] = table[image_in[i][j]][k];

			// Write the result image image_out[][]
			for (k = 0; k < 3; k++)
				for (i = 0; i < 640; i++)
					for (j = 0; j < 420; j++)
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
