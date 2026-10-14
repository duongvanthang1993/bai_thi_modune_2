package model;

public class DienThoaiChinhHang extends DienThoai {
    private int thoiGianBaoHanh;
    private String phamViBaoHanh;

    public DienThoaiChinhHang() {
    }

    public DienThoaiChinhHang(int id, String tenDienThoai, double giaBan, int soLuong, String nhaSanXuat,
            int thoiGianBaoHanh, String phamViBaoHanh) {
        super(id, tenDienThoai, giaBan, soLuong, nhaSanXuat);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.phamViBaoHanh = phamViBaoHanh;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public String getPhamViBaoHanh() {
        return phamViBaoHanh;
    }

    public void setPhamViBaoHanh(String phamViBaoHanh) {
        this.phamViBaoHanh = phamViBaoHanh;
    }

    @Override
    public String toCSV() {
        String giaStr = (getGiaBan() == (long) getGiaBan()) ? String.format(java.util.Locale.US, "%.0f", getGiaBan())
                : String.valueOf(getGiaBan());
        return getId() + "," + getTenDienThoai() + "," + giaStr + "," + getSoLuong() + "," + getNhaSanXuat()
                + "," + thoiGianBaoHanh + "," + phamViBaoHanh;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Thoi gian bao hanh: " + thoiGianBaoHanh + " ngay" +
                ", Pham vi bao hanh: " + phamViBaoHanh + " [Chinh hang]";
    }

}
