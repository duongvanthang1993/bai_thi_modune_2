package model;

public class DienThoaiXachTay extends DienThoai {
    private String quocGiaXachTay;
    private String trangThai;

    public DienThoaiXachTay() {
    }

    public DienThoaiXachTay(int id, String tenDienThoai, double giaBan, int soLuong, String nhaSanXuat,
            String quocGiaXachTay, String trangThai) {
        super(id, tenDienThoai, giaBan, soLuong, nhaSanXuat);
        this.quocGiaXachTay = quocGiaXachTay;
        this.trangThai = trangThai;
    }

    public String getQuocGiaXachTay() {
        return quocGiaXachTay;
    }

    public void setQuocGiaXachTay(String quocGiaXachTay) {
        this.quocGiaXachTay = quocGiaXachTay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toCSV() {
        String giaStr = (getGiaBan() == (long) getGiaBan()) ? String.format(java.util.Locale.US, "%.0f", getGiaBan())
                : String.valueOf(getGiaBan());
        return getId() + "," + getTenDienThoai() + "," + giaStr + "," + getSoLuong() + "," + getNhaSanXuat()
                + "," + quocGiaXachTay + "," + trangThai;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Quoc gia xach tay: " + quocGiaXachTay +
                ", Trang thai: " + trangThai + " [Xach tay]";
    }
}
