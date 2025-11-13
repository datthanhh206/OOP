package Nhom5;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lớp khách hàng - kế thừa từ Nguoi
 * Đặc điểm: có mã khách hàng (maKH)
 * Lưu ý: SĐT đã có trong lớp cha Nguoi (dienThoai) → không cần thêm
 */
public class KhachHang extends Nguoi {
    private String maKH;

    // Constructor mặc định
    public KhachHang() {
        super();
    }

    // Constructor đầy đủ
    public KhachHang(String hoTen, Date ngaySinh, String gioiTinh, 
                     String diaChi, String dienThoai, String maKH) {
        super(hoTen, ngaySinh, gioiTinh, diaChi, dienThoai);
        this.maKH = maKH;
    }

    // --- Getter & Setter ---
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    // --- toString() để ghi file .txt ---
    @Override
    public String toString() {
        // Tạo một formatter để đổi Date thành String "dd/MM/yyyy"
        String ngaySinhFormatted = new SimpleDateFormat("dd/MM/yyyy").format(getNgaySinh());
        
        return "Ma Khach hang: " +maKH + " | " +
               "Ho ten: " +getHoTen() + " | " +
               "Ngay sinh: " + ngaySinhFormatted + " | " + // <--- ĐÃ SỬA
               "Gioi tinh : " +getGioiTinh() + " | " +
               "Dia chi: "+getDiaChi() + " | " +
               "Dien thoai: "+getDienThoai();  
    }
}