package Nhom5;

import java.util.ArrayList;

/**
 * Lớp quản lý hóa đơn - implements lQuanLy
 * Hỗ trợ: Thêm, Sửa, Xóa, Tìm, Thống kê, Lưu file .txt
 */
public class QuanLyHoaDon implements lQuanLy {
    private ArrayList<HoaDon> dsHD = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    private final String FILE_PATH = "hoadon.txt";

    public QuanLyHoaDon() {
        docFile();
    }

    @Override
    public void Them(Object obj) {
        if (obj instanceof HoaDon hd) {
            dsHD.add(hd);
            ghiFile();
            System.out.println("Thêm hóa đơn thành công: " + hd.getMaHD());
        }
    }

    @Override
    public void Sua(String ma) {
        HoaDon hd = (HoaDon) TimKiem(ma);
        if (hd != null) {
            System.out.println("Hóa đơn hiện tại: " + hd);
            // TODO: Cho phép sửa chi tiết (nếu cần)
            ghiFile();
        } else {
            System.out.println("Không tìm thấy hóa đơn: " + ma);
        }
    }

    // ĐÃ SỬA: thêm return boolean
    @Override
    public boolean Xoa(String ma) {
        boolean removed = dsHD.removeIf(hd -> hd.getMaHD().equalsIgnoreCase(ma.trim()));
        if (removed) ghiFile();
        return removed;
    }

    @Override
    public Object TimKiem(String tuKhoa) {
        if (tuKhoa == null || tuKhoa.trim().isEmpty()) return null;
        final String key = tuKhoa.trim();
        return dsHD.stream()
                .filter(hd -> hd.getMaHD().equalsIgnoreCase(key))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Object ThongKe(String dieuKien) {
        return switch (dieuKien.toLowerCase()) {
            case "tongso" -> dsHD.size();
            case "doanhthu" -> dsHD.stream().mapToDouble(HoaDon::tinhTongTien).sum();
            default -> 0.0;
        };
    }

    // --- Đọc file .txt ---
    public void docFile() {
        try {
            ArrayList<?> data = fileHandler.docFile(FILE_PATH);
            if (data != null && !data.isEmpty() && data.get(0) instanceof HoaDon) {
                dsHD = (ArrayList<HoaDon>) data;
                System.out.println("Đọc file hóa đơn thành công: " + dsHD.size() + " hóa đơn.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file hóa đơn: " + e.getMessage());
        }
    }

    // --- Ghi file .txt ---
    public void ghiFile() {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(FILE_PATH))) {
            for (HoaDon hd : dsHD) {
                // Gọi đúng hàm toFileString() mà bạn đã viết trong HoaDon.java
                pw.println(hd.toFileString());
            }
        } catch (java.io.IOException e) {
            System.out.println("Loi ghi file hoadon.txt: " + e.getMessage());
        }
    }

    // --- HIỂN THỊ DƯỚI DẠNG BẢNG ĐẸP ---
    public void hienThi() {
        if (dsHD.isEmpty()) {
            System.out.println("Chưa có hóa đơn nào.");
            return;
        }

        System.out.println("\n--- DANH SÁCH HÓA ĐƠN ---");
        Utils.inTieuDe("Mã HD", "Ngày lập", "Nhân viên", "Khách hàng", "Số SP", "Tổng tiền");

        for (HoaDon hd : dsHD) {
            String tenNV = hd.getNhanVien().getTenNV();
            if (tenNV.length() > 12) tenNV = tenNV.substring(0, 9) + "...";

            String tenKH = hd.getKhachHang().getHoTen();
            if (tenKH.length() > 12) tenKH = tenKH.substring(0, 9) + "...";

            Utils.inDong(
                hd.getMaHD(),
                Utils.dinhDangNgay(hd.getNgayLap()),
                hd.getNhanVien().getMaNV() + "-" + tenNV,
                hd.getKhachHang().getMaKH() + "-" + tenKH,
                String.valueOf(hd.getDsChiTiet().size()),
                Utils.dinhDangTien(hd.tinhTongTien())
            );
        }
        Utils.inKetThuc(6);
        System.out.println();
    }

    // --- XUẤT FILE ĐỂ XEM ---
    public void xuatFile() {
        fileHandler.xemFile(FILE_PATH);
    }

    // --- Getter ---
    public ArrayList<HoaDon> getDsHD() {
        return dsHD;
    }
}