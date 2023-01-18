package Q4;

import java.io.FileOutputStream;
import java.io.IOException;

public class byteToFile {
    public static void main(String[] args) {
        int [] a = new int[]{104, 116, 116, 112, 115, 58, 47, 47, 119, 119, 119, 46,
                121, 111, 117, 116, 117, 98, 101, 46, 99, 111, 109, 47, 119, 97, 116,
                99, 104, 63, 118, 61, 100, 81, 119, 52, 119, 57, 87, 103, 88, 99, 81};
        byte[] b = new byte[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = (byte)a[i];
        }
        convertByteArrayToFile(b, "file.txt");
    }


    public static void convertByteArrayToFile(byte[] bytes, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
