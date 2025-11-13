package Nhom5;

import java.util.Date;

/**
 * Lớp thực phẩm - kế thừa từ SanPham
 * Đặc điểm: có hạn sử dụng
 * Hỗ trợ: tính tiền, hiển thị bảng, ghi file
 */
public class ThucPham extends SanPham {
    private Date hanSuDung;

    // Constructor mặc định
    public ThucPham() {
        super();
    }

    // Constructor đầy đủ
    public ThucPham(String maSP, String tenSP, double gia, int soLuong, Date hanSuDung) {
        super(maSP, tenSP, gia, soLuong);
        this.hanSuDung = hanSuDung;
    }

    // --- Getter & Setter ---
    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    // --- TÍNH TIỀN ---
    @Override
    public double tinhTien() {
        return getGia() * getSoLuong(); // Có thể thêm logic giảm giá nếu hết hạn
    }

    // --- toString() CHO BẢNG ĐẸP (dùng Utils) ---
    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %12s | %5d | HSD: %s",
                getMaSP(),
                getTenSP().length() > 20 ? getTenSP().substring(0, 17) + "..." : getTenSP(),
                Utils.dinhDangTien(getGia()),
                getSoLuong(),
                Utils.dinhDangNgay(hanSuDung)
        );
    }

    // --- toFileString() CHO FILE .txt (có nhãn + Thành Tiền) ---
    public String toFileString() {
        return "Ma San Pham: " + getMaSP() + " | " +
               "Ten San Pham: " + getTenSP() + " | " +
               "Gia: " + getGia() + " | " +
               "So Luong: " + getSoLuong() + " | " +
               "Han su dung: " + (hanSuDung != null ? hanSuDung : "") + " | " +
               "Thanh Tien: " + tinhTien();
    }
}