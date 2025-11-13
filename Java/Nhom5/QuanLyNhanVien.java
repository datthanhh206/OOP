package Nhom5;

import java.io.*;
import java.util.*;

public class QuanLyNhanVien {
    private List<NhanVien> ds = new ArrayList<>();
    private static final String FILE_NAME = "nhanvien.txt";

    // ĐỌC FILE
    public void docFile() {
        ds.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Bỏ qua dòng trống
                try {
                    // Tách các phần tử theo dấu " | "
                    String[] parts = line.split(" \\| ");                     
                    // Lấy giá trị bằng cách tách theo dấu ": "
                    String ma = parts[0].split(": ")[1];
                    String ten = parts[1].split(": ")[1];
                    String loaiNV = parts[2].trim(); // "Ban hang" hoặc "Bao ve"
                    double luong = Double.parseDouble(parts[3].split(": ")[1]);
                    // KIỂM TRA LOẠI NHÂN VIÊN
                    if (loaiNV.equals("Ban hang")) {
                        // LÀ NHÂN VIÊN BÁN HÀNG
                        int soSP = Integer.parseInt(parts[4].split(": ")[1]);
                        ds.add(new NhanVienBanHang(ma, ten, luong, soSP));                        
                    } else if (loaiNV.equals("Bao ve")) {
                        // LÀ NHÂN VIÊN BẢO VỆ
                        int soCa = Integer.parseInt(parts[4].split(": ")[1]);
                        ds.add(new NhanVienBaoVe(ma, ten, luong, soCa));
                    }                   
                } catch (Exception e) {
                    // Báo lỗi nếu 1 dòng bị hỏng, nhưng vẫn tiếp tục đọc các dòng khác
                    System.out.println("Loi parse dong nhanvien: [" + line + "] - " + e.getMessage());
                }
            }
            
            System.out.println("Da doc file nhanvien.txt thanh cong. Tong so: " + ds.size() + " nhan vien.");

        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file " + FILE_NAME + ". Tao file moi khi ghi.");
        } catch (Exception e) {
            System.out.println("Loi doc file " + FILE_NAME + ": " + e.getMessage());
        }
    }

    // GHI FILE
    public void ghiFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (NhanVien nv : ds) {
                pw.println(nv.toString());
            }
        } catch (Exception e) {
            System.out.println("Loi ghi file nhanvien!");
            e.printStackTrace();
        }
    }

    // THÊM
    public void Them(NhanVien nv) {
        ds.add(nv);
    }

    // TÌM KIẾM – TRẢ VỀ NhanVien (KHÔNG PHẢI Object)
    public NhanVien TimKiem(String ma) {
        for (NhanVien nv : ds) {
            if (nv.getMaNV().equalsIgnoreCase(ma.trim())) {
                return nv;
            }
        }
        return null;
    }

    // TÌM KIẾM THEO TỪ KHÓA (tên hoặc mã)
    public NhanVien TimKiemTuKhoa(String tuKhoa) {
        for (NhanVien nv : ds) {
            if (nv.getMaNV().toLowerCase().contains(tuKhoa.toLowerCase()) ||
                nv.getTenNV().toLowerCase().contains(tuKhoa.toLowerCase())) {
                return nv;
            }
        }
        return null;
    }

    // XÓA – TRẢ VỀ boolean
    public boolean Xoa(String ma) {
        NhanVien nv = TimKiem(ma);
        if (nv != null) {
            ds.remove(nv);
            return true;
        }
        return false;
    }

    // HIỂN THỊ
    public void hienThi() {
        if (ds.isEmpty()) {
            System.out.println("Khong co nhan vien nao!");
            return;
        }
        System.out.println("\n--- DANH SACH NHAN VIEN ---");
        for (NhanVien nv : ds) {
            System.out.println(nv);
        }
    }

    // XUẤT FILE .TXT (giống ghiFile)
    public void xuatFile() {
        ghiFile();
        System.out.println("Da xuat nhan vien ra " + FILE_NAME);
    }
}