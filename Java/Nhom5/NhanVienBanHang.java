package Nhom5;

import java.util.Date;

/**
 * Lớp nhân viên bán hàng - kế thừa từ NhanVien
 * Tính lương: lương cơ bản + 1% hoa hồng mỗi sản phẩm bán được
 */
public class NhanVienBanHang extends NhanVien {
    private int soSanPhamBan; // số sản phẩm đã bán

    // Constructor mặc định
    public NhanVienBanHang() {
        super();
        this.soSanPhamBan = 0;
    }

    // Constructor CHÍNH - 4 tham số (dùng trong Menu.java)
    public NhanVienBanHang(String maNV, String tenNV, double luongCoBan, int soSanPhamBan) {
        super(maNV, tenNV, luongCoBan);
        this.soSanPhamBan = soSanPhamBan;
    }

    // --- Getter & Setter ---
    public int getSoSanPhamBan() {
        return soSanPhamBan;
    }

    public void setSoSanPhamBan(int soSanPhamBan) {
        this.soSanPhamBan = soSanPhamBan;
    }

    // --- Tính lương ---
    @Override
    public double tinhLuong() {
        double hoaHong = soSanPhamBan * (getLuongCoBan() * 0.01); // 1% mỗi sản phẩm
        return getLuongCoBan() + hoaHong;
    }

    // --- toString() để ghi file .txt ---
    @Override
    public String toString() {
        return "Ma Nhan vien: "+getMaNV() + " | " +
               "Ten Nhan vien: "+getTenNV() + " | " +
               "Ban hang" + " | " +  
               "Luong Co ban: "+ getLuongCoBan() + " | " +
               "So SP ban: "+soSanPhamBan + " | " +
               "Tong luong: "+tinhLuong();
    }
}