// File: FileHandler.java
package Nhom5;

import java.io.*;
import java.util.ArrayList;

/**
 * Lớp xử lý đọc/ghi file .txt cho toàn bộ hệ thống
 * Hỗ trợ: ghi danh sách đối tượng ra file, đọc về ArrayList
 */
public class FileHandler {

    /**
     * GHI DANH SÁCH RA FILE .TXT
     * @param filePath đường dẫn file (vd: "sanpham.txt")
     * @param list danh sách đối tượng (có toString())
     * @param <T> kiểu generic
     */
    public <T> void ghiFile(String filePath, ArrayList<T> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (T item : list) {
                pw.println(item.toString()); // Ghi từng dòng
            }
            System.out.println("Ghi file thanh cong: " + filePath);
        } catch (IOException e) {
            System.out.println("Loi ghi file " + filePath + ": " + e.getMessage());
        }
    }

    /**
     * ĐỌC FILE .TXT VỀ DANH SÁCH (chỉ dùng để hiển thị hoặc debug)
     * @param filePath đường dẫn file
     */
    public void xemFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("\n--- NOI DUNG FILE: " + filePath + " ---");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--- KET THUC ---\n");
        } catch (FileNotFoundException e) {
            System.out.println("File khong ton tai: " + filePath);
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    /**
     * KIỂM TRA FILE CÓ TỒN TẠI KHÔNG
     * @param filePath đường dẫn
     * @return true nếu tồn tại
     */
    public boolean tonTai(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * ĐỌC FILE .TXT VỀ DANH SÁCH CÁC DÒNG
     * @param filePath đường dẫn file
     * @return ArrayList<String> chứa nội dung từng dòng trong file
     */
    public ArrayList<String> docFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File khong ton tai: " + filePath);
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
        return lines;
    }
}
