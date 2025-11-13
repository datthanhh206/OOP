package Nhom5;

import java.util.Date;

/**
 * Lớp quản lý - kế thừa từ NhanVien
 * Tính lương: lương cơ bản × hệ số
 */
public class QuanLy extends NhanVien {
    private double heSo; // hệ số lương (vd: 2.5)

    // Constructor mặc định
    public QuanLy() {
        super();
    }

    // Constructor đầy đủ
    public QuanLy(String maNV, String tenNV, double luongCoBan, double heSo) {
        super(maNV, tenNV, luongCoBan); // Gọi constructor của NhanVien
        this.heSo = heSo;
    }

    // --- Getter & Setter ---
    public double getHeSo() {
        return heSo;
    }

    public void setHeSo(double heSo) {
        this.heSo = heSo;
    }

    // --- Tính lương ---
    @Override
    public double tinhLuong() {
        return getLuongCoBan() * heSo;
    }

    // --- toString() để ghi file .txt ---
    @Override
    public String toString() {
        return "Ma Nhan vien: "+getMaNV() + " | " +
               "Ten Nhan vien: "+getTenNV() + " | " +
               "Luong Co ban: "+getLuongCoBan() + " | " +
               "He so: "+heSo + " | " +
               "Tong luong: "+tinhLuong();
    }
}