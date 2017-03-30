import java.io.*;
import java.util.Scanner;

public class Matrix 
{
	static double[][] m_arr;
	public static void inverseMatrix(double[][] x, int n, double determinant,BufferedWriter bw)
	{
		try {
			bw.write("Minv = \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		printMatrix(x,n,determinant,bw);
	}
	public static void printMatrix(double[][] x, int n, double determinant,BufferedWriter bw)
	{
		try {
				if(determinant ==0)
				{
					for(int i = 0; i < n ; i++)
						{
							for(int j = 0 ; j < n ; j++)
							{
									bw.write((int)x[i][j] + "	");						
							}
							bw.write("\n");
						}
				}
				else
				{
					for(int i = 0; i < n ; i++)
					{
						for(int j = 0 ; j < n ; j++)
						{
								bw.write((int)x[i][j]/determinant + "	");						
						}
						bw.write("\n");
					}
				}
			} 
		catch (IOException e) 
			{
				e.printStackTrace();
			}
	}
	public static double determinant(double[][] x, int n)
	{
		double det = 0.0;
		if (n == 1)
		{
			det = x[1][1];
		}
		else if(n == 2)
		{
			det = x[0][0] * x[1][1] - x[0][1] * x[1][0];
		}
		else
		{
			for(int j1 = 0 ; j1 < n; j1++)
			{
				double[][] m = new double[n-1][];
				for(int k = 0 ; k < (n-1) ; k++)
				{
					m[k] = new double[n-1];
				}
				for(int i = 1 ; i < n ; i++)
				{
					int j2 = 0;
					for(int j = 0 ; j < n; j++)
					{
					  if(j == j1)
					  
						  continue;
					  
					  m[i-1][j2] = x[i][j];
					  j2++;
					}
				}
				 det += Math.pow(-1.0,1.0+j1+1.0)* x[0][j1] * determinant(m,n-1);
			}
		}
		
		
		return det;
	}

	
	public static void main(String[] args)
	{
		
		String fileName = "input.txt";
		//String line = null;
		try{
			boolean cond = true;
			
			//creating the file reader
			Scanner fileReader = new Scanner(new File(fileName));
			BufferedWriter bufferedWriter = null;
			FileWriter fileWriter = null;
			fileWriter = new FileWriter("output.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			
			
			while(cond)
			{
				//reading in the size
				String n_size = fileReader.next();
				int m_size = Integer.parseInt(n_size);
				if(m_size == 0)
				{
					cond = false;
					System.out.println("Done!");
				}
				else
				{
					double[][] matrix = new double[m_size][m_size];//creates matrix
					for(int i = 0; i < m_size ; i++)
					{
						for(int j = 0 ; j < m_size ; j++)
						{
							matrix[i][j] = Integer.parseInt(fileReader.next());//changes strings to ints
						}
					}
					bufferedWriter.write("M = ");
					printMatrix(matrix,m_size,0,bufferedWriter);//prints matrix
					double det = determinant(matrix, m_size);//calculates determinant
					bufferedWriter.write("det(M) = " + det + "\n");
					bufferedWriter.write("\n");
					inverseMatrix(matrix,m_size,det,bufferedWriter);//inverts matrix
					bufferedWriter.write("\n");
				}
				
			}
			fileReader.close();
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Unable to find file '" + fileName + "'");
		}
		catch(IOException ex)
		{
			System.out.println("Error with file '" + fileName + "'");
		}
		
		
	}
}