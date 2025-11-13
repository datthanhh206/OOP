package Nhom5;

import java.util.Date;

/**
 * Lớp thành viên VIP - kế thừa từ KhachHang
 * Tính năng: tích điểm khi mua hàng (100.000đ = 1 điểm)
 */
public class ThanhVien extends KhachHang {
    private int diemTichLuy;

    // Constructor mặc định
    public ThanhVien() {
        super();
    }

    // === CONSTRUCTOR ĐÃ SỬA ===
    // Phải nhận ĐẦY ĐỦ thông tin của KhachHang, và THÊM thông tin của ThanhVien
    public ThanhVien(String hoTen, Date ngaySinh, String gioiTinh, 
                     String diaChi, String dienThoai, String maKH,
                     int diemTichLuy) { // Thêm 'diemTichLuy'
        
        // 1. Gọi constructor lớp cha (KhachHang) để lưu 6 thông tin
        super(hoTen, ngaySinh, gioiTinh, diaChi, dienThoai, maKH);
        
        // 2. Lưu thông tin của riêng lớp này
        this.diemTichLuy = diemTichLuy;
    }

    // --- Getter & Setter ---
    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    // --- Cộng điểm khi mua hàng ---
    public void congDiem(double soTienMua) {
        int diemCongThem = (int) (soTienMua / 100000); // 100k = 1 điểm
        if (diemCongThem > 0) {
            this.diemTichLuy += diemCongThem;
            System.out.println("Đã cộng thêm " + diemCongThem + " điểm. Tổng điểm mới: " + this.diemTichLuy);
        }
    }

    // === toString() ĐÃ SỬA (Rất quan trọng) ===
    @Override
    public String toString() {
        // 1. Lấy tất cả 6 thông tin từ lớp cha (KhachHang)
        String thongTinCha = super.toString();
        
        // 2. Nối thêm thông tin của riêng lớp này (ThanhVien)
        // Kết quả sẽ là: [6 thông tin cha] | Diem tich luy: [điểm]
        return thongTinCha + " | " + "Diem tich luy: " + this.diemTichLuy;
    }
}