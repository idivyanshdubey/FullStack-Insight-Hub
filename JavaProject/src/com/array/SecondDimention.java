package com.array;

import java.util.Arrays;

public class SecondDimention {
	public static void main(String[] args) {
		int[][] matrix = {
			    {1, 2, 3},
			    {4, 5, 6},
			    {7, 8, 9}
			};
			for (int i = 0; i < matrix.length; i++) { // Outer loop for rows
			    for (int j = 0; j < matrix[i].length; j++) { // Inner loop for columns
			        System.out.println(matrix[i][j]+ " ");
			    }
			    System.out.println(" ");
			}
			//System.out.println(Arrays.deepToString(matrix));
	}

}
