
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        try {
            String matr1 = "M1";
            String matr2 = "M2";
            String matr3 = "M3";
            File file1 = new File(matr1);
            File file2 = new File(matr2);
            File file3 = new File(matr3);
            Matrix matrix1 = MatrixService.readFile(file1.getPath() + ".txt");
            Matrix matrix2 = MatrixService.readFile(file2.getPath() + ".txt");

            if (matrix1.getRows() != matrix2.getRows() || matrix1.getColums() != matrix2.getColums()) {
                System.out.println("Размеры матриц не совпадают");
                return;
            }

            Socket socket = new Socket("localhost", 3345);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Matrix[] matrixs = {matrix1, matrix2};
            out.writeObject(matrixs);

            MatrixService.writeInFile((Matrix) in.readObject(), file3.getPath() + ".txt");

            out.close();
            in.close();
            socket.close();

        } catch (NoSuchElementException e) {
            System.out.println("Некорректные входные данные");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
