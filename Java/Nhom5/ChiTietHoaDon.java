package Nhom5;

/**
 * Lớp chi tiết hóa đơn: Mỗi dòng trong hóa đơn
 * Chứa: 1 sản phẩm + số lượng
 */
public class ChiTietHoaDon {
    private SanPham sanPham;  
    private int soLuong;

    // Constructor
    public ChiTietHoaDon(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }

    // Tính tiền cho dòng này
    public double tinhTien() {
        return sanPham.getGia() * soLuong;
    }

    // Getter
    public SanPham getSanPham() { return sanPham; }
    public int getSoLuong() { return soLuong; }

    // Setter (nếu cần sửa số lượng)
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    // toString() để ghi ra file .txt
    @Override
    public String toString() {
        return "Ma San Pham: " +sanPham.getMaSP() + " | " +
               "Ten San Pham: " +sanPham.getTenSP() + " | " +
               "Gia: " +sanPham.getGia() + " | " +
               "So Luong: " +soLuong + " | " +
               "Thanh tien: " +tinhTien();
    }
}