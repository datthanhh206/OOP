package Nhom5;

/**
 * Lớp trừu tượng sản phẩm - lớp cha cho DoUong, DoGiaDung
 * Chứa thông tin chung: mã, tên, giá, số lượng
 * Hỗ trợ: tính tiền, hiển thị bảng
 */
public abstract class SanPham {
    protected String maSP;
    protected String tenSP;
    protected double gia;
    protected int soLuong;

    // Constructor mặc định
    public SanPham() {}

    // Constructor đầy đủ
    public SanPham(String maSP, String tenSP, double gia, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    // --- Getter & Setter ---
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    // --- TÍNH TIỀN (giữ abstract để con override nếu cần) ---
    public abstract double tinhTien();

    // --- TÍNH THÀNH TIỀN (giá * số lượng) - DÙNG CHUNG ---
    public double thanhTien() {
        return gia * soLuong;
    }

    // --- toString() ĐẸP HƠN CHO BẢNG ---
    @Override
    public String toString() {
        return String.format("%-6s | %-15s | %10s | %5d",
                maSP,
                tenSP.length() > 15 ? tenSP.substring(0, 12) + "..." : tenSP,
                Utils.dinhDangTien(gia),
                soLuong
        );
    }

    // --- toString() CHO FILE .txt (giữ nguyên định dạng cũ) ---
    public String toFileString() {
        return "Ma San Pham: " + maSP + " | " +
               "Ten San Pham: " + tenSP + " | " +
               "Gia: " + gia + " | " +
               "So Luong: " + soLuong;
    }
}