package Nhom5;

/**
 * Lớp trừu tượng NhanVien - kế thừa từ Nguoi
 * Chứa thông tin chung: mã NV, tên NV, lương cơ bản
 * Các lớp con: NhanVienBanHang, NhanVienBaoVe, QuanLy
 */
public abstract class NhanVien extends Nguoi {
    protected String maNV;
    protected String tenNV;
    protected double luongCoBan;

    // Constructor mặc định
    public NhanVien() {
        super();
    }

    // Constructor đầy đủ
    public NhanVien(String maNV, String tenNV, double luongCoBan) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.luongCoBan = luongCoBan;
    }

    // --- Getter & Setter ---
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    // --- Tính lương (mỗi loại nhân viên khác nhau) ---
    public abstract double tinhLuong();

    // --- toString() để ghi file .txt ---
    @Override
    public String toString() {
        return "Ma Nhan vien: " +maNV + " | " +
               "Ten Nhan vien: " +tenNV + " | " +
               "Luong Co ban: "+luongCoBan + " | " +
               "Tong luong: "+tinhLuong(); // Ghi luôn tổng lương
    }
}