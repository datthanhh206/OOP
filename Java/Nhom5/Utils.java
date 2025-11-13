package Nhom5;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static final DecimalFormat df = new DecimalFormat("#,##0đ");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String dinhDangTien(double soTien) {
        return df.format(soTien);
    }

    public static String dinhDangNgay(Date date) {
        return date != null ? sdf.format(date) : "N/A";
    }

    public static boolean kiemTraMaHopLe(String ma, java.util.List<String> danhSach) {
        return ma != null && !ma.trim().isEmpty() && !danhSach.contains(ma);
    }

    public static String taoMaTuDong(String prefix, java.util.List<?> danhSach) {
        int soThuTu = danhSach.size() + 1;
        return String.format("%s%03d", prefix, soThuTu);
    }

    public static int tinhDiemTichLuy(double soTien) {
        return (int) (soTien / 100000);
    }

    // ────────────────────────────── BẢNG ASCII ──────────────────────────────

    public static void inTieuDe(String... headers) {
        int colWidth = 20;
        System.out.print("┌");
        for (int i = 0; i < headers.length; i++) {
            System.out.print("─".repeat(colWidth));
            if (i < headers.length - 1) System.out.print("┬");
        }
        System.out.println("┐");

        System.out.print("│");
        for (String h : headers) {
            String display = h.length() > colWidth - 2 ? h.substring(0, colWidth - 5) + "..." : h;
            System.out.printf(" %-" + (colWidth - 2) + "s │", display);
        }
        System.out.println();

        System.out.print("├");
        for (int i = 0; i < headers.length; i++) {
            System.out.print("─".repeat(colWidth));
            if (i < headers.length - 1) System.out.print("┼");
        }
        System.out.println("┤");
    }

    public static void inDong(String... values) {
        int colWidth = 20;
        System.out.print("│");
        for (String v : values) {
            String display = v.length() > colWidth - 2 ? v.substring(0, colWidth - 5) + "..." : v;
            System.out.printf(" %-" + (colWidth - 2) + "s │", display);
        }
        System.out.println();
    }

    public static void inKetThuc(int colCount) {
        int colWidth = 20;
        System.out.print("└");
        for (int i = 0; i < colCount; i++) {
            System.out.print("─".repeat(colWidth));
            if (i < colCount - 1) System.out.print("┴");
        }
        System.out.println("┘");
    }
}