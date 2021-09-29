import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class MatrixService {

    public static Matrix sumMatrix(Matrix matrix1, Matrix matrix2) {

        Matrix matrix = new Matrix(matrix1.getRows(), matrix2.getColums());

        for (int i = 0; i < matrix.getRows(); i++)
            for (int j = 0; j < matrix.getColums(); j++) {
                matrix.setElement(i,j,matrix1.getElement(i,j) * matrix2.getElement(i,j));
            }

        return matrix;
    }
    public static void writeInFile(Matrix matrix, String filename) throws IOException {
        File file = new File(filename);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException var10) {
            throw new IOException("create new fille error: " + var10.getMessage());
        }

        PrintWriter out = new PrintWriter(file.getAbsolutePath());

        try {
            for (int i = 0; i < matrix.getRows(); ++i) {
                for (int j = 0; j < matrix.getColums(); ++j) {
                    out.printf("%.2f ", matrix.getElement(i, j));
                }

                out.println();
            }
        } finally {
            out.close();
        }

    }

    public static int CountRows(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream is = new FileInputStream(file);

        int aChar;
        try {
            int count = 1;

            for (aChar = 0; aChar != -1; aChar = is.read()) {
                count += aChar == 10 ? 1 : 0;
            }

            aChar = count;
        } catch (Throwable var6) {
            try {
                is.close();
            } catch (Throwable var5) {
                var6.addSuppressed(var5);
            }

            throw var6;
        }

        is.close();
        return aChar;
    }

    public static int CountColumn(String filename) throws IOException {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            double count;
            for (count = 0.0D; sc.hasNext(); ++count) {
                sc.next();
            }

            count /= (double) CountRows(filename);
            if (count != (double) ((int) count)) {
                throw new IOException("Неправильно задана матрица");
            } else {
                return (int) count;
            }
        } catch (IOException var5) {
            throw new IOException("create new fille error: " + var5.getMessage());
        }
    }

    public static Matrix readFile(String filename) throws IOException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        int r = CountRows(filename);
        int c = CountColumn(filename);
        Matrix matrix = new Matrix(r, c);

        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                matrix.setElement(i, j, sc.nextDouble());
            }
        }

            return matrix;
        }
    }




