package Nhom5;

import java.util.Date;

/**
 * Lớp trừu tượng Nguoi - lớp cha cho KhachHang, NhanVien
 * Chứa thông tin cơ bản: họ tên, ngày sinh, giới tính, địa chỉ, điện thoại
 */
public abstract class Nguoi {
    protected String hoTen;
    protected Date ngaySinh;
    protected String gioiTinh;
    protected String diaChi;
    protected String dienThoai;

    // Constructor mặc định
    public Nguoi() {}

    // Constructor đầy đủ
    public Nguoi(String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String dienThoai) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }

    // --- Getter & Setter ---
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {  // SỬA: nhận Date, không phải String
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    // --- toString() để ghi file .txt ---
    @Override
    public String toString() {
        return "Ho ten: " +hoTen + " | " +
               "Ngay Sinh: " +(ngaySinh != null ? new java.sql.Date(ngaySinh.getTime()) : "") + " | " +
               "Gioi Tinh: " +gioiTinh + " | " +
               "Dia chi: " +diaChi + " | " +
               "Dien thoai: " +dienThoai;
    }
}