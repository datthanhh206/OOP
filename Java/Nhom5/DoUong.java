package Nhom5;

import java.util.Date;

/**
 * Lớp đồ uống - kế thừa từ ThucPham
 * Đặc điểm: có dung tích (lít)
 * Hỗ trợ: tính tiền, hiển thị bảng, ghi file
 */
public class DoUong extends ThucPham {
    private double dungTich;

    // Constructor mặc định
    public DoUong() {
        super();
    }

    // Constructor đầy đủ
    public DoUong(String maSP, String tenSP, double gia, int soLuong, Date hanSD, double dungTich) {
        super(maSP, tenSP, gia, soLuong, hanSD);
        this.dungTich = dungTich;
    }

    // --- Getter & Setter ---
    public double getDungTich() {
        return dungTich;
    }

    public void setDungTich(double dungTich) {
        this.dungTich = dungTich;
    }

    // --- TÍNH TIỀN (DÙChung từ SanPham) ---
    @Override
    public double tinhTien() {
        return getGia() * getSoLuong(); // hoặc: return thanhTien();
    }

    // --- toString() CHO BẢNG ĐẸP (dùng Utils) ---
    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %12s | %5d | %-12s | %s",
                getMaSP(),
                getTenSP().length() > 20 ? getTenSP().substring(0, 17) + "..." : getTenSP(),
                Utils.dinhDangTien(getGia()),
                getSoLuong(),
                Utils.dinhDangNgay(getHanSuDung()),
                dungTich + "L"
        );
    }

    // --- toFileString() CHO FILE .txt (có nhãn + Thành Tiền) ---
    public String toFileString() {
        return "Ma San Pham: " + getMaSP() + " | " +
               "Ten San Pham: " + getTenSP() + " | " +
               "Gia: " + getGia() + " | " +
               "So Luong: " + getSoLuong() + " | " +
               "Han su dung: " + (getHanSuDung() != null ? getHanSuDung() : "") + " | " +
               "Dung tich: " + dungTich + " | " +
               "Thanh Tien: " + tinhTien();
    }
}