package Nhom5;

import java.util.ArrayList;
import java.util.Date;

/**
 * Lớp hóa đơn - đại diện cho một lần mua hàng
 * Chứa: mã HD, ngày lập, nhân viên, khách hàng, danh sách chi tiết
 */
public class HoaDon {
    private String maHD;
    private Date ngayLap;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private ArrayList<ChiTietHoaDon> dsChiTiet;

    // Constructor
    public HoaDon(String maHD, Date ngayLap, NhanVien nhanVien, KhachHang khachHang) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.dsChiTiet = new ArrayList<>();
    }

    // Thêm chi tiết
    public void themChiTiet(ChiTietHoaDon chiTiet) {
        dsChiTiet.add(chiTiet);
    }

    // Tính tổng tiền
    public double tinhTongTien() {
        return dsChiTiet.stream()
                        .mapToDouble(ChiTietHoaDon::tinhTien)
                        .sum();
    }

    // --- Getter ---
    public String getMaHD() { return maHD; }
    public Date getNgayLap() { return ngayLap; }
    public NhanVien getNhanVien() { return nhanVien; }
    public KhachHang getKhachHang() { return khachHang; }
    public ArrayList<ChiTietHoaDon> getDsChiTiet() { return dsChiTiet; }

    // --- toString() để HIỂN THỊ (in ra console) ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HOA DON: ").append(maHD)
          .append(" | NGAY: ").append(Utils.dinhDangNgay(ngayLap))
          .append(" | NV: ").append(nhanVien.getMaNV()).append(" - ").append(nhanVien.getTenNV())
          .append(" | KH: ").append(khachHang.getMaKH()).append(" - ").append(khachHang.getHoTen())
          .append("\nCHI TIET:\n");

        for (ChiTietHoaDon ct : dsChiTiet) {
            SanPham sp = ct.getSanPham();
            sb.append("  - ").append(sp.getTenSP())
              .append(" x").append(ct.getSoLuong())
              .append(" = ").append(Utils.dinhDangTien(ct.tinhTien()))
              .append("\n");
        }
        sb.append("TONG: ").append(Utils.dinhDangTien(tinhTongTien()));
        return sb.toString();
    }

    // --- toFileString() ĐỂ GHI FILE .txt (1 dòng = 1 hóa đơn) ---
    public String toFileString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Ma HD: ").append(maHD)
      .append(" | Ngay lap: ").append(Utils.dinhDangNgay(ngayLap))
      .append(" | Nhan vien: ").append(nhanVien.getMaNV())
      .append(" | Khach hang: ").append(khachHang.getMaKH())
      .append(" | Chi tiet: ");

    for (int i = 0; i < dsChiTiet.size(); i++) {
        ChiTietHoaDon ct = dsChiTiet.get(i);
        // Lấy sản phẩm ra để dễ gọi
        SanPham sp = ct.getSanPham(); 
        sb.append(sp.getMaSP()) // Mã SP
          .append(" (").append(sp.getTenSP()).append(")") // Tên SP
          .append(" x ").append(ct.getSoLuong()); // Số lượng
        // =======================

        if (i < dsChiTiet.size() - 1) sb.append(", ");
    }

    sb.append(" | Tong tien: ").append(tinhTongTien());
    return sb.toString();
}
}