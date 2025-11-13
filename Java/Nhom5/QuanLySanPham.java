package Nhom5;

import java.io.*;
import java.util.ArrayList;

/**
 * Lớp quản lý sản phẩm - implements lQuanLy
 * Hỗ trợ: Thêm, Sửa, Xóa, Tìm kiếm (mã + tên), Thống kê, Lưu file .txt (có nhãn)
 */
public class QuanLySanPham implements lQuanLy {
    private ArrayList<SanPham> dsSP = new ArrayList<>();
    private final String FILE_PATH = "sanpham.txt";

    public QuanLySanPham() {
        docFile();
    }

    @Override
    public void Them(Object obj) {
        if (obj instanceof SanPham sp) {
            dsSP.add(sp);
            ghiFile();
            System.out.println("Thêm sản phẩm thành công: " + sp.getMaSP());
        }
    }

    @Override
    public void Sua(String ma) {
        SanPham sp = (SanPham) TimKiem(ma);
        if (sp != null) {
            System.out.println("Sản phẩm hiện tại: " + sp);
            ghiFile();
        } else {
            System.out.println("Không tìm thấy sản phẩm mã: " + ma);
        }
    }

    @Override
    public boolean Xoa(String ma) {
        boolean removed = dsSP.removeIf(sp -> sp.getMaSP().equalsIgnoreCase(ma.trim()));
        if (removed) ghiFile();
        return removed;
    }

    // ĐÃ SỬA LỖI: final or effectively final
    @Override
    public Object TimKiem(String tuKhoa) {
        if (tuKhoa == null || tuKhoa.trim().isEmpty()) return null;
        final String searchKey = tuKhoa.trim().toLowerCase();

        return dsSP.stream()
                .filter(sp -> sp.getMaSP().equalsIgnoreCase(searchKey) ||
                              sp.getTenSP().toLowerCase().contains(searchKey))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Object ThongKe(String dieuKien) {
        return switch (dieuKien.toLowerCase()) {
            case "tongso" -> dsSP.size();
            case "hethang" -> dsSP.stream().filter(sp -> sp.getSoLuong() == 0).count();
            case "giatri" -> dsSP.stream().mapToDouble(sp -> sp.getGia() * sp.getSoLuong()).sum();
            default -> "Không hỗ trợ: " + dieuKien;
        };
    }

    // --- HÀM HỖ TRỢ ---
    private String extractValue(String field) {
        if (field == null || !field.contains(":")) return field.trim();
        return field.split(":", 2)[1].trim();
    }

    // --- ĐỌC FILE .txt (CÓ NHÃN) ---
    public void docFile() {
        dsSP.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("File sanpham.txt không tồn tại. Tạo mới khi thêm SP.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty() || !line.contains("|")) continue;

                String[] parts = line.split("\\|", -1);
                if (parts.length < 4) {
                    System.out.println("Lỗi dòng " + lineNum + ": Thiếu dữ liệu -> " + line);
                    continue;
                }

                try {
                    String ma = extractValue(parts[0]);
                    String ten = extractValue(parts[1]);
                    double gia = Double.parseDouble(extractValue(parts[2]));
                    int sl = Integer.parseInt(extractValue(parts[3]));

                    if (line.contains("Dung tich") && parts.length >= 7) {
                        java.sql.Date hsd = java.sql.Date.valueOf(extractValue(parts[4]));
                        double dt = Double.parseDouble(extractValue(parts[5]));
                        dsSP.add(new DoUong(ma, ten, gia, sl, hsd, dt));
                    } else if (line.contains("Bảo hành") && parts.length >= 6) {
                        int bh = Integer.parseInt(extractValue(parts[4]));
                        dsSP.add(new DoGiaDung(ma, ten, gia, sl, bh));
                    }
                } catch (Exception e) {
                    System.out.println("Lỗi dòng " + lineNum + ": " + e.getMessage() + " -> " + line);
                }
            }
            System.out.println("Đọc file sản phẩm thành công: " + dsSP.size() + " sản phẩm.");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file sanpham.txt: " + e.getMessage());
        }
    }

    // ĐÃ TỐI ƯU: DÙNG toFileString() → KHÔNG LẶP CODE
    public void ghiFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (SanPham sp : dsSP) {
                if (sp instanceof DoUong du) {
                    pw.println(du.toFileString());
                } else if (sp instanceof DoGiaDung dg) {
                    pw.println(dg.toFileString());
                }
            }
            System.out.println("Ghi file sanpham.txt thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // --- HIỂN THỊ DƯỚI DẠNG BẢNG ĐẸP ---
    public void hienThi() {
        if (dsSP.isEmpty()) {
            System.out.println("Chưa có sản phẩm nào.");
            return;
        }

        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        Utils.inTieuDe("Mã SP", "Tên SP", "Giá", "SL", "Loại", "Chi tiết", "Thành tiền");

        for (SanPham sp : dsSP) {
            String loai = sp instanceof DoUong ? "Đồ uống" : "Gia dụng";
            String chiTiet = sp instanceof DoUong ?
                    "HSD: " + Utils.dinhDangNgay(((DoUong) sp).getHanSuDung()) +
                    ", DT: " + ((DoUong) sp).getDungTich() + "L" :
                    "BH: " + ((DoGiaDung) sp).getBaoHanh() + " tháng";
            String thanhTien = Utils.dinhDangTien(sp.thanhTien());

            Utils.inDong(
                sp.getMaSP(),
                sp.getTenSP(),
                Utils.dinhDangTien(sp.getGia()),
                String.valueOf(sp.getSoLuong()),  // ĐÃ SỬA
                loai,
                chiTiet,
                thanhTien
            );
        }
        Utils.inKetThuc(7);
        System.out.println();
    }

    // --- XUẤT FILE ---
    public void xuatFile() {
        System.out.println("\n--- NỘI DUNG sanpham.txt ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.lines().forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println("File sanpham.txt chưa tồn tại.");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        System.out.println("--- KẾT THÚC ---\n");
    }

    public ArrayList<SanPham> getDsSP() {
        return dsSP;
    }
}