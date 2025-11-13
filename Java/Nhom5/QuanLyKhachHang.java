package Nhom5;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale; // Rất quan trọng để parse Date

/**
 * Lớp quản lý khách hàng - implements lQuanLy
 * Hỗ trợ: Thêm, Sửa, Xóa, Tìm kiếm, Thống kê, Lưu file .txt
 */
public class QuanLyKhachHang implements lQuanLy {
    private ArrayList<KhachHang> dsKH = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    private final String FILE_PATH = "khachhang.txt";

    public QuanLyKhachHang() {
        docFile();
    }

    @Override
    public void Them(Object obj) {
        if (obj instanceof KhachHang kh) {
            dsKH.add(kh);
            ghiFile();
            System.out.println("Thêm khách hàng thành công: " + kh.getMaKH());
        }
    }

    @Override
    public void Sua(String ma) {
        KhachHang kh = (KhachHang) TimKiem(ma);
        if (kh != null) {
            System.out.println("Khách hàng hiện tại: " + kh);
            // TODO: Nhập thông tin mới
            ghiFile();
        } else {
            System.out.println("Không tìm thấy khách hàng mã: " + ma);
        }
    }

    @Override
    public boolean Xoa(String ma) {
        boolean removed = dsKH.removeIf(kh -> kh.getMaKH().equalsIgnoreCase(ma.trim()));
        if (removed) {
            ghiFile();
            System.out.println("Xóa khách hàng thành công: " + ma);
        } else {
            System.out.println("Không tìm thấy mã: " + ma);
        }
        return removed;
    }

    @Override
    public Object TimKiem(String tuKhoa) {
        if (tuKhoa == null || tuKhoa.trim().isEmpty()) return null;
        final String search = tuKhoa.trim().toLowerCase();
        return dsKH.stream()
                .filter(kh -> kh.getMaKH().equalsIgnoreCase(search) ||
                             kh.getHoTen().toLowerCase().contains(search))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Object ThongKe(String dieuKien) {
        return switch (dieuKien.toLowerCase()) {
            case "tongso" -> dsKH.size();
            case "thanhvien" -> dsKH.stream().filter(kh -> kh instanceof ThanhVien).count();
            default -> 0;
        };
    }

    // BÊN TRONG FILE QuanLyKhachHang.java

    public void docFile() {
        // 1. Gọi FileHandler để lấy danh sách CÁC DÒNG CHỮ
        ArrayList<String> lines = fileHandler.docFile(FILE_PATH);
        
        // 2. Xóa danh sách cũ đi để nạp danh sách mới từ file
        dsKH.clear(); 
        
        // 3. Định dạng ngày tháng 
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");

        // 4. Lặp qua từng dòng chữ và parse
        for (String line : lines) {
            if (line.trim().isEmpty()) continue; // Bỏ qua dòng trống

            try {
                // Tách các phần tử theo dấu " | "
                String[] parts = line.split(" \\| "); 
                
                // Lấy giá trị bằng cách tách theo dấu ": "
                String maKH = parts[0].split(": ")[1];
                String hoTen = parts[1].split(": ")[1];
                String ngaySinhStr = parts[2].split(": ")[1];
                String gioiTinh = parts[3].split(": ")[1];
                String diaChi = parts[4].split(": ")[1];
                String dienThoai = parts[5].split(": ")[1];
                
                // Chuyển chuỗi ngày sinh về đối tượng Date
                Date ngaySinh = parser.parse(ngaySinhStr);

                // 5. KIỂM TRA QUAN TRỌNG: Đây là ThanhVien hay KhachHang?
                if (line.contains("Diem tich luy")) {
                    // 6a. Là THÀNH VIÊN (có 7 phần tử)
                    // Lấy phần tử cuối cùng
                    int diemTichLuy = Integer.parseInt(parts[6].split(": ")[1]);
                    
                    // Tạo đối tượng ThanhVien (dùng constructor đã sửa)
                    dsKH.add(new ThanhVien(hoTen, ngaySinh, gioiTinh, diaChi, dienThoai, maKH, diemTichLuy));
                    
                } else {
                    // 6b. Là KHÁCH HÀNG thường (chỉ có 6 phần tử)
                    dsKH.add(new KhachHang(hoTen, ngaySinh, gioiTinh, diaChi, dienThoai, maKH));
                }

            } catch (Exception e) {
                // Báo lỗi nếu 1 dòng bị hỏng, nhưng vẫn tiếp tục đọc các dòng khác
                System.out.println("Loi parse dong: [" + line + "] - " + e.getMessage());
            }
        }
        
        System.out.println("Da doc file khachhang.txt thanh cong. Tong so: " + dsKH.size() + " khach.");
    }

    public void ghiFile() {
        fileHandler.ghiFile(FILE_PATH, dsKH);
    }

    // --- HIỂN THỊ DƯỚI DẠNG BẢNG ĐẸP ---
    public void hienThi() {
        if (dsKH.isEmpty()) {
            System.out.println("Chưa có khách hàng nào.");
            return;
        }

        System.out.println("\n--- DANH SÁCH KHÁCH HÀNG ---");
        Utils.inTieuDe("Mã KH", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Điện thoại", "Loại");

        for (KhachHang kh : dsKH) {
            String loai = kh instanceof ThanhVien ? "Thành viên" : "Thường";
            Utils.inDong(
                kh.getMaKH(),
                kh.getHoTen(),
                Utils.dinhDangNgay(kh.getNgaySinh()),
                kh.getGioiTinh(),
                kh.getDiaChi(),
                kh.getDienThoai(),
                loai
            );
        }
        Utils.inKetThuc(7);
        System.out.println();
    }

    public void xuatFile() {
        fileHandler.xemFile(FILE_PATH);
    }

    public ArrayList<KhachHang> getDsKH() {
        return dsKH;
    }
}