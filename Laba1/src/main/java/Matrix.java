import java.io.Serializable;

public class Matrix implements Serializable {
    private int rows;
    private int colums;
    private double[][] matrix;

    public Matrix(int rows, int colums) {
        this.rows = rows;
        this.colums = colums;
        this.matrix = new double[rows][colums];
    }

    public Matrix(double[][] matrix) {
        this.rows = matrix.length;
        this.colums = matrix[0].length;
        this.matrix = new double[this.rows][this.colums];

        for(int i = 0; i < this.rows; ++i) {
            for(int j = 0; j < this.colums; ++j) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

    }

    public int getRows() {
        return this.rows;
    }

    public int getColums() {
        return this.colums;
    }

    public void setElement(int rows, int colums, double element) {
        this.matrix[rows][colums] = element;
    }

    public double getElement(int rows, int colums) {
        return this.matrix[rows][colums];
    }
}
