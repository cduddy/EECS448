import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testDeterminant() {
		double[][] matrix = new double[2][2];
		matrix[0][0] = 3;
		matrix[0][1] = 4;
		matrix[1][0] = -5;
		matrix[1][1] = 2;
		
		assertEquals(26.0, Matrix.determinant(matrix, 2), .001);
	}

}
