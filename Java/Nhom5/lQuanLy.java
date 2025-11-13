package Nhom5;

/**
 * Interface quản lý chung cho hệ thống siêu thị mini
 * Các lớp triển khai: QuanLySanPham, QuanLyHoaDon, QuanLyNhanVien,...
 */
interface lQuanLy {

    /**
     * Thêm một đối tượng mới vào danh sách
     * @param obj đối tượng cần thêm
     */
    void Them(Object obj);

    /**
     * Sửa thông tin đối tượng theo mã
     * @param ma mã định danh cần sửa
     */
    void Sua(String ma);

    /**
     * Xóa đối tượng theo mã
     * @param ma mã cần xóa
     */
    boolean Xoa(String ma);

    /**
     * Tìm kiếm đối tượng theo từ khóa
     * @param tuKhoa từ khóa tìm kiếm
     * @return đối tượng tìm thấy hoặc null
     */
    Object TimKiem(String tuKhoa);

    /**
     * Thống kê theo điều kiện
     * @param dieuKien điều kiện thống kê
     * @return kết quả thống kê
     */
    Object ThongKe(String dieuKien);
}