package Nhom5;

/**
 * Lớp đồ gia dụng - kế thừa từ SanPham
 * Đặc điểm: có thời gian bảo hành (tháng)
 * Hỗ trợ: tính tiền, hiển thị bảng, ghi file
 */
public class DoGiaDung extends SanPham {
    private int baoHanh; // tháng

    // Constructor mặc định
    public DoGiaDung() {
        super();
    }

    // Constructor đầy đủ
    public DoGiaDung(String maSP, String tenSP, double gia, int soLuong, int baoHanh) {
        super(maSP, tenSP, gia, soLuong);
        this.baoHanh = baoHanh;
    }

    // --- Getter & Setter ---
    public int getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    // --- TÍNH TIỀN ---
    @Override
    public double tinhTien() {
        return getGia() * getSoLuong(); // Có thể thêm khuyến mãi sau
    }

    // --- toString() CHO BẢNG ĐẸP (dùng Utils) ---
    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %12s | %5d | BH: %d tháng",
                getMaSP(),
                getTenSP().length() > 20 ? getTenSP().substring(0, 17) + "..." : getTenSP(),
                Utils.dinhDangTien(getGia()),
                getSoLuong(),
                baoHanh
        );
    }

    // --- toFileString() CHO FILE .txt (có nhãn + Thành Tiền) ---
    public String toFileString() {
        return "Ma San Pham: " + getMaSP() + " | " +
               "Ten San Pham: " + getTenSP() + " | " +
               "Gia: " + getGia() + " | " +
               "So Luong: " + getSoLuong() + " | " +
               "Bao hanh: " + baoHanh + " | " +
               "Thanh Tien: " + tinhTien();
    }
}