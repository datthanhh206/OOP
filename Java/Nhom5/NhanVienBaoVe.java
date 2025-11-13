package Nhom5;

import java.util.Date;

/**
 * Lớp nhân viên bảo vệ - kế thừa từ NhanVien
 * Tính lương: lương cơ bản + 200.000đ mỗi ca làm
 */
public class NhanVienBaoVe extends NhanVien {
    private int soCa; // số ca làm việc

    // Constructor mặc định
    public NhanVienBaoVe() {
        super();
        this.soCa = 0;
    }

    // Constructor CHÍNH - 4 tham số (dùng trong Menu.java)
    public NhanVienBaoVe(String maNV, String tenNV, double luongCoBan, int soCa) {
        super(maNV, tenNV, luongCoBan);
        this.soCa = soCa;
    }

    // --- Getter & Setter ---
    public int getSoCa() {
        return soCa;
    }

    public void setSoCa(int soCa) {
        this.soCa = soCa;
    }

    // --- Tính lương ---
    @Override
    public double tinhLuong() {
        double phuCap = soCa * 200000; // 200k/ca
        return getLuongCoBan() + phuCap;
    }

    // --- toString() để ghi file .txt ---
    @Override
    public String toString() {
        return "Ma Nhan vien: "+getMaNV() + " | " +
               "Ten Nhan vien: "+getTenNV() + " | " +
               "Bao ve" + " | " +     
               "Luong Co ban: "+getLuongCoBan() + " | " +
               "So ca: "+soCa + " | " +
               "Tong luong: "+tinhLuong();
    }
}