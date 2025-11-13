// File: Menu.java
package Nhom5;

import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Menu {
    private static QuanLySanPham qlSP = new QuanLySanPham();
    private static QuanLyHoaDon qlHD = new QuanLyHoaDon();
    private static QuanLyNhanVien qlNV = new QuanLyNhanVien();
    private static QuanLyKhachHang qlKH = new QuanLyKhachHang();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        qlSP.docFile();
        qlHD.docFile();
        qlNV.docFile();
        qlKH.docFile();
        hienThiMenu();
    }

    public static void hienThiMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n" + "=".repeat(60));
            System.out.println("     QUAN LY SIEU THI MINI - NHOM 5");
            System.out.println("=".repeat(60));
            System.out.println("1. Quan ly San pham");
            System.out.println("2. Quan ly Khach hang");
            System.out.println("3. Quan ly Nhan vien");
            System.out.println("4. Quan ly Hoa don");
            System.out.println("5. Xuat du lieu ra file .txt");
            System.out.println("0. Thoat");
            System.out.println("=".repeat(60));
            System.out.print("Chon chuc nang: ");

            int chon = nhapSo();
            switch (chon) {
                case 1: menuSanPham(); break;
                case 4: menuHoaDon(); break;
                case 3: menuNhanVien(); break;
                case 2: menuKhachHang(); break;
                case 5: xuatFile(); break;
                case 0: luuVaThoat(); return;
                default: System.out.println("Lua chon khong hop le!"); pause(); break;
            }
        }
    }

    // ================== SAN PHAM ==================
    private static void menuSanPham() {
        while (true) {
            clearScreen();
            System.out.println("\n-- QUAN LY SAN PHAM --");
            System.out.println("1. Them san pham"); System.out.println("2. Sua san pham");
            System.out.println("3. Xoa san pham"); System.out.println("4. Tim kiem");
            System.out.println("5. Hien thi danh sach"); System.out.println("6. Thong ke");
            System.out.println("0. Quay lai"); System.out.print("Chon: ");
            int c = nhapSo(); if (c == 0) break;
            switch (c) {
                case 1: themSanPham(); break;
                case 2: suaSanPham(); break;
                case 3: xoaSanPham(); break;
                case 4: timSanPham(); break;
                case 5: qlSP.hienThi(); pause(); break;
                case 6: thongKeSanPham(); pause(); break;
                default: System.out.println("Khong hop le!"); pause(); break;
            }
        }
    }

    private static void themSanPham() {
        System.out.println("Loai: 1. Do uong | 2. Do gia dung");
        int loai = nhapSo();
        System.out.print("Ma SP: "); String ma = docChuoi();
        System.out.print("Ten SP: "); String ten = docChuoi();
        System.out.print("Gia: "); double gia = nhapDouble();
        System.out.print("So luong: "); int sl = nhapSo();

        if (loai == 1) {
            java.sql.Date hsd = nhapNgay("Han su dung (yyyy-MM-dd): ");
            if (hsd == null) { System.out.println("Han su dung bat buoc!"); pause(); return; }
            System.out.print("Dung tich (lit): "); double dt = nhapDouble();
            qlSP.Them(new DoUong(ma, ten, gia, sl, hsd, dt));
        } else {
            System.out.print("Bao hanh (thang): "); int bh = nhapSo();
            qlSP.Them(new DoGiaDung(ma, ten, gia, sl, bh));
        }
        System.out.println("Them thanh cong!");
        qlSP.ghiFile();
        pause();
    }

    private static void suaSanPham() {
        System.out.print("Nhap ma SP can sua: ");
        String ma = docChuoi();
        SanPham sp = (SanPham) qlSP.TimKiem(ma);
        if (sp == null) {
            System.out.println("Khong tim thay san pham!"); pause(); return;
        }
        System.out.println("San pham hien tai: " + sp);

        System.out.print("Ten moi (Enter de giu): ");
        String tenMoi = docChuoi();
        if (!tenMoi.isEmpty()) sp.setTenSP(tenMoi);

        System.out.print("Gia moi (Enter de giu): ");
        String giaStr = docChuoi();
        if (!giaStr.isEmpty()) {
            try { sp.setGia(Double.parseDouble(giaStr)); }
            catch (NumberFormatException e) { System.out.println("Gia khong hop le! Giu nguyen."); }
        }

        System.out.print("So luong moi (Enter de giu): ");
        String slStr = docChuoi();
        if (!slStr.isEmpty()) {
            try { sp.setSoLuong(Integer.parseInt(slStr)); }
            catch (NumberFormatException e) { System.out.println("So luong khong hop le! Giu nguyen."); }
        }

        if (sp instanceof DoUong du) {
            java.sql.Date hsd = nhapNgay("Han su dung moi (yyyy-MM-dd, Enter de giu): ");
            if (hsd != null) du.setHanSuDung(hsd);
            System.out.print("Dung tich moi (lit, Enter de giu): ");
            String dt = docChuoi();
            if (!dt.isEmpty()) {
                try { du.setDungTich(Double.parseDouble(dt)); }
                catch (NumberFormatException e) { System.out.println("Dung tich khong hop le! Giu nguyen."); }
            }
        } else if (sp instanceof DoGiaDung dg) {
            System.out.print("Bao hanh moi (thang, Enter de giu): ");
            String bh = docChuoi();
            if (!bh.isEmpty()) {
                try { dg.setBaoHanh(Integer.parseInt(bh)); }
                catch (NumberFormatException e) { System.out.println("Bao hanh khong hop le! Giu nguyen."); }
            }
        }

        System.out.println("Sua thanh cong!");
        qlSP.ghiFile();
        pause();
    }

    private static void xoaSanPham() {
        System.out.print("Nhap ma SP can xoa: ");
        String ma = docChuoi();
        if (qlSP.Xoa(ma)) {
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay san pham!");
        }
        qlSP.ghiFile();
        pause();
    }

    private static void timSanPham() {
        System.out.print("Nhap tu khoa: ");
        String tk = docChuoi();
        Object kq = qlSP.TimKiem(tk);
        System.out.println(kq != null ? kq : "Khong tim thay!");
        pause();
    }

    private static void thongKeSanPham() {
        System.out.println("Tong so san pham: " + qlSP.ThongKe("tongso"));
        System.out.println("San pham het hang: " + qlSP.ThongKe("hethang"));
        System.out.println("Tong gia tri kho: " + Utils.dinhDangTien((Double) qlSP.ThongKe("giatri")));
        pause();
    }

    // ================== KHACH HANG ==================
private static void menuKhachHang() {
    while (true) {
        clearScreen();
        System.out.println("\n-- QUAN LY KHACH HANG --");
        System.out.println("1. Them");
        System.out.println("2. Sua");
        System.out.println("3. Xoa");
        System.out.println("4. Tim kiem");
        System.out.println("5. Hien thi");
        System.out.println("0. Quay lai");
        System.out.print("Chon: "); int c = nhapSo();
        
        if (c == 0) break;
        switch (c) {
            case 1: themKhachHang(); break;
            case 2: suaKhachHang(); break;
            case 3: xoaKhachHang(); break;
            case 4: timKhachHang(); break;
            case 5: qlKH.hienThi(); pause(); break;
            default: System.out.println("Khong hop le!"); pause(); break;
        }
    }
}

/**
 * Hàm THÊM khách hàng (Thường hoặc Thành viên)
 */
private static void themKhachHang() {
    System.out.println("\n-- THEM KHACH HANG MOI --");
    System.out.print("Chon loai: (1: Thuong, 2: Thanh vien): ");
    int loaiKH = nhapSo(); 

    System.out.print("Ma KH: "); String ma = docChuoi();
    System.out.print("Ho ten: "); String ten = docChuoi();
    System.out.print("SDT: "); String sdt = docChuoi();
    System.out.print("Gioi Tinh: "); String gioitinh = docChuoi();
    System.out.print("Dia chi: "); String diachi = docChuoi();
    System.out.print("Ngay sinh (dd/MM/yyyy): ");
    String ngaySinhStr = docChuoi();
    
    try {
        Date ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinhStr);

        if (loaiKH == 2) {
            // LÀ THÀNH VIÊN
            System.out.print("Nhap diem tich luy ban dau: ");
            int diem = nhapSo();
            qlKH.Them(new ThanhVien(ten, ngaySinh, gioitinh, diachi, sdt, ma, diem));
        } else {
            // LÀ KHÁCH THƯỜNG (mặc định)
            qlKH.Them(new KhachHang(ten, ngaySinh, gioitinh, diachi, sdt, ma));
        }
        
        System.out.println("Them thanh cong!");
        // (Hàm Them() bên trong qlKH nên tự động gọi ghiFile())
        
    } catch (ParseException e) {
        System.out.println("Loi: Nhap sai dinh dang ngay sinh (phai la dd/MM/yyyy).");
    }
    pause();
}

/**
 * Hàm SỬA thông tin khách hàng
 */
private static void suaKhachHang() {
    System.out.println("\n-- SUA KHACH HANG --");
    System.out.print("Nhap Ma KH can sua: ");
    String maSua = docChuoi();
    
    KhachHang khSua = (KhachHang) qlKH.TimKiem(maSua); 
    
    if (khSua == null) {
        System.out.println("Khong tim thay ma khach hang: " + maSua);
    } else {
        System.out.println("Da tim thay: " + khSua.getHoTen());
        System.out.println("Nhap thong tin moi (Enter de giu nguyen):");
        
        System.out.print("Ho ten moi (" + khSua.getHoTen() + "): ");
        String tenMoi = docChuoi();
        if (!tenMoi.trim().isEmpty()) khSua.setHoTen(tenMoi);
        
        System.out.print("SDT moi (" + khSua.getDienThoai() + "): ");
        String sdtMoi = docChuoi();
        if (!sdtMoi.trim().isEmpty()) khSua.setDienThoai(sdtMoi);
        
        System.out.print("Dia chi moi (" + khSua.getDiaChi() + "): ");
        String diaChiMoi = docChuoi();
        if (!diaChiMoi.trim().isEmpty()) khSua.setDiaChi(diaChiMoi);
        
        System.out.print("Gioi tinh moi (" + khSua.getGioiTinh() + "): ");
        String gioiTinhMoi = docChuoi();
        if (!gioiTinhMoi.trim().isEmpty()) khSua.setGioiTinh(gioiTinhMoi);
        
        // Sửa điểm của Thành viên
        if (khSua instanceof ThanhVien) {
            ThanhVien tvSua = (ThanhVien) khSua;
            System.out.print("Diem tich luy moi (" + tvSua.getDiemTichLuy() + ") (-1 de giu nguyen): ");
            int diemMoi = nhapSo(); // Dùng nhapSo() sẽ an toàn hơn parse
            if (diemMoi != -1) {
                tvSua.setDiemTichLuy(diemMoi);
            }
        }
        
        qlKH.ghiFile(); // Lưu lại tất cả thay đổi
        System.out.println("Cap nhat thanh cong!");
    }
    pause();
}

/**
 * Hàm XÓA khách hàng
 */
private static void xoaKhachHang() {
    System.out.println("\n-- XOA KHACH HANG --");
    System.out.print("Nhap Ma KH can xoa: ");
    String maXoa = docChuoi();
    
    // Hàm Xoa() bên qlKH nên tự gọi ghiFile() và in thông báo
    if (qlKH.Xoa(maXoa)) { 
        System.out.println("Xoa thanh cong!");
    } else {
        System.out.println("Khong tim thay ma de xoa!");
    }
    pause();
}

/**
 * Hàm TÌM KIẾM khách hàng
 */
private static void timKhachHang() {
    System.out.println("\n-- TIM KIEM KHACH HANG --");
    System.out.print("Tim (ma hoac ten KH): ");
    String tuKhoa = docChuoi();
    
    KhachHang kh = (KhachHang) qlKH.TimKiem(tuKhoa);
    
    // In kết quả
    System.out.println(kh != null ? kh.toString() : "Khong tim thay!");
    pause();
}

    // ================== NHAN VIEN ==================
    private static void menuNhanVien() {
        while (true) {
            clearScreen();
            System.out.println("\n-- QUAN LY NHAN VIEN --");
            System.out.println("1. Them"); System.out.println("2. Sua"); System.out.println("3. Xoa");
            System.out.println("4. Tim"); System.out.println("5. Hien thi"); System.out.println("0. Quay lai");
            System.out.print("Chon: "); int c = nhapSo(); if (c == 0) break;
            switch (c) {
                case 1: themNhanVien(); break;
                case 2: suaNhanVien(); break;
                case 3: xoaNhanVien(); break;
                case 4: timNhanVien(); pause(); break;
                case 5: qlNV.hienThi(); pause(); break;
                default: System.out.println("Khong hop le!"); pause(); break;
            }
        }
    }

    private static void themNhanVien() {
        System.out.println("Loai: 1. Ban hang | 2. Bao ve");
        int loai = nhapSo();
        System.out.print("Ma NV: "); String ma = docChuoi();
        System.out.print("Ho ten: "); String ten = docChuoi();
        System.out.print("Luong CB: "); double luong = nhapDouble();

        if (loai == 1) {
            System.out.print("So SP ban: "); int soSP = nhapSo();
            qlNV.Them(new NhanVienBanHang(ma, ten, luong, soSP));
        } else {
            System.out.print("So ca lam: "); int soCa = nhapSo();
            qlNV.Them(new NhanVienBaoVe(ma, ten, luong, soCa));
        }
        System.out.println("Them thanh cong!");
        qlNV.ghiFile();
        pause();
    }

    private static void suaNhanVien() {
        System.out.print("Nhap ma NV can sua: ");
        String ma = docChuoi();
        NhanVien nv = (NhanVien) qlNV.TimKiem(ma);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
            pause(); return;
        }

        System.out.println("Nhan vien hien tai: " + nv);
        System.out.println("Nhap thong tin moi (Enter de giu nguyen):");

        System.out.print("Ho ten moi: ");
        String tenMoi = docChuoi();
        if (!tenMoi.isEmpty()) nv.setTenNV(tenMoi);

        System.out.print("Luong CB moi: ");
        String luongStr = docChuoi();
        if (!luongStr.isEmpty()) {
            try { nv.setLuongCoBan(Double.parseDouble(luongStr)); }
            catch (NumberFormatException e) { System.out.println("Luong khong hop le! Giu nguyen."); }
        }

        if (nv instanceof NhanVienBanHang bh) {
            System.out.print("So san pham ban moi: ");
            String soSP = docChuoi();
            if (!soSP.isEmpty()) {
                try { bh.setSoSanPhamBan(Integer.parseInt(soSP)); }
                catch (NumberFormatException e) { System.out.println("So SP khong hop le! Giu nguyen."); }
            }
        } else if (nv instanceof NhanVienBaoVe bv) {
            System.out.print("So ca lam moi: ");
            String soCa = docChuoi();
            if (!soCa.isEmpty()) {
                try { bv.setSoCa(Integer.parseInt(soCa)); }
                catch (NumberFormatException e) { System.out.println("So ca khong hop le! Giu nguyen."); }
            }
        }

        System.out.println("Sua nhan vien thanh cong!");
        qlNV.ghiFile();
        pause();
    }

    private static void xoaNhanVien() {
        System.out.print("Ma NV can xoa: ");
        String ma = docChuoi();
        if (qlNV.Xoa(ma)) {
            System.out.println("Xoa nhan vien " + ma + " thanh cong!");
        } else {
            System.out.println("Khong tim thay nhan vien co ma: " + ma);
        }
        qlNV.ghiFile();
        pause();
    }

    private static void timNhanVien() {
        System.out.print("Tim (ma hoac ten): ");
        String tuKhoa = docChuoi();
        NhanVien nv = (NhanVien) qlNV.TimKiem(tuKhoa);
        System.out.println(nv != null ? nv : "Khong tim thay!");
        pause();
    }

    // ================== HOA DON ==================
    private static void menuHoaDon() {
        while (true) {
            clearScreen();
            System.out.println("\n-- QUAN LY HOA DON --");
            System.out.println("1. Tao hoa don moi");
            System.out.println("2. Xem hoa don");
            System.out.println("3. Xem danh sach hoa don");
            System.out.println("4. Thong ke doanh thu");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int c = nhapSo();
            if (c == 0) break;
            switch (c) {
                case 1: taoHoaDon(); break;
                case 2: xemHoaDon(); pause(); break;
                case 3: xemDanhSachHoaDon(); pause(); break;
                case 4: thongKeDoanhThu(); pause(); break;
                default: System.out.println("Khong hop le!"); pause(); break;
            }
        }
    }

    private static void taoHoaDon() {
        System.out.print("Ma HD: "); String maHD = docChuoi();
        System.out.print("Ma NV: "); String maNV = docChuoi();
        NhanVien nv = (NhanVien) qlNV.TimKiem(maNV);
        if (nv == null) { System.out.println("NV khong ton tai!"); pause(); return; }

        System.out.print("Ma KH: "); String maKH = docChuoi();
        KhachHang kh = (KhachHang) qlKH.TimKiem(maKH);
        if (kh == null) { System.out.println("KH khong ton tai!"); pause(); return; }

        HoaDon hd = new HoaDon(maHD, new Date(), nv, kh);

        while (true) {
            System.out.print("Ma SP (0 de ket thuc): "); String maSP = docChuoi();
            if (maSP.equals("0")) break;
            SanPham sp = (SanPham) qlSP.TimKiem(maSP);
            if (sp == null || sp.getSoLuong() == 0) {
                System.out.println("SP khong ton tai hoac het hang!");
                pause();
                continue;
            }
            System.out.print("So luong: "); int sl = nhapSo();
            if (sl > sp.getSoLuong()) {
                System.out.println("Khong du hang!");
                pause();
                continue;
            }
            sp.setSoLuong(sp.getSoLuong() - sl);
            hd.themChiTiet(new ChiTietHoaDon(sp, sl));
        }

        qlHD.Them(hd);
        System.out.println("Tao hoa don thanh cong! Tong tien: " + Utils.dinhDangTien(hd.tinhTongTien()));
        qlHD.ghiFile();
        pause();
    }

    private static void xemHoaDon() {
        System.out.print("Nhap ma HD: ");
        String ma = docChuoi();
        HoaDon hd = (HoaDon) qlHD.TimKiem(ma);
        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
            pause();
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                 HOA DON BAN HANG");
        System.out.println("=".repeat(70));
        System.out.printf("Ma HD: %-15s | Ngay: %s\n", hd.getMaHD(), Utils.dinhDangNgay(hd.getNgayLap()));
        System.out.printf("Nhan vien: %-20s | Khach hang: %s\n",
                hd.getNhanVien().getMaNV() + " - " + hd.getNhanVien().getTenNV(),
                hd.getKhachHang().getMaKH() + " - " + hd.getKhachHang().getHoTen());
        System.out.println("=".repeat(70));

        Utils.inTieuDe("STT", "Ma SP", "Ten SP", "SL", "Don gia", "Thanh tien");

        int stt = 1;
        double tongTien = 0;
        for (ChiTietHoaDon ct : hd.getDsChiTiet()) {
            SanPham sp = ct.getSanPham();
            double thanhTien = sp.getGia() * ct.getSoLuong();
            tongTien += thanhTien;

            Utils.inDong(
                String.valueOf(stt++),
                sp.getMaSP(),
                sp.getTenSP(),
                String.valueOf(ct.getSoLuong()),
                Utils.dinhDangTien(sp.getGia()),
                Utils.dinhDangTien(thanhTien)
            );
        }
        Utils.inKetThuc(6);

        System.out.println("TONG TIEN: " + Utils.dinhDangTien(tongTien));
        if (hd.getKhachHang() instanceof ThanhVien tv) {
            int diem = Utils.tinhDiemTichLuy(tongTien);
            System.out.println("DIEM TICH LUY: +" + diem + " diem");
        }
        System.out.println("=".repeat(70));
        pause();
    }

    private static void xemDanhSachHoaDon() {
        ArrayList<HoaDon> dsHD = qlHD.getDsHD();
        if (dsHD.isEmpty()) {
            System.out.println("Chua co hoa don nao.");
            return;
        }

        System.out.println("\n--- DANH SACH HOA DON ---");
        Utils.inTieuDe("Ma HD", "Ngay lap", "Nhan vien", "Khach hang", "So SP", "Tong tien");

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

    private static void thongKeDoanhThu() {
        System.out.println("Tong doanh thu: " + Utils.dinhDangTien((Double) qlHD.ThongKe("doanhthu")));
        System.out.println("So hoa don: " + qlHD.ThongKe("tongso"));
        pause();
    }
    // ================== XUAT FILE ==================
    private static void xuatFile() {
        System.out.println("1. SP | 2. HD | 3. NV | 4. KH");
        int c = nhapSo();
        switch (c) {
            case 1: qlSP.xuatFile(); break;
            case 2: qlHD.xuatFile(); break;
            case 3: qlNV.xuatFile(); break;
            case 4: qlKH.xuatFile(); break;
            default: System.out.println("Khong hop le!"); break;
        }
        pause();
    }

    // ================== HAM HO TRO ==================
    private static int nhapSo() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { System.out.print("Hay nhap so nguyen: "); }
        }
    }

    private static double nhapDouble() {
        while (true) {
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (Exception e) { System.out.print("Hay nhap so thuc: "); }
        }
    }

    private static String docChuoi() { return sc.nextLine().trim(); }

    private static void pause() { System.out.print("\nNhan Enter de tiep tuc..."); sc.nextLine(); }

    private static void clearScreen() { System.out.print("\033[H\033[2J"); System.out.flush(); }

    private static java.sql.Date nhapNgay(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) return null;
            try { return java.sql.Date.valueOf(input); }
            catch (IllegalArgumentException e) {
                System.out.println("Sai dinh dang! Vui long nhap yyyy-MM-dd (vd: 2025-12-31)");
            }
        }
    }

    private static void luuVaThoat() {
        qlSP.ghiFile(); qlHD.ghiFile(); qlNV.ghiFile(); qlKH.ghiFile();
        System.out.println("Da luu du lieu. Tam biet!");
        sc.close();
    }
}