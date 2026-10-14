package model;

public abstract class DienThoai {
    private int id;
    private String tenDienThoai;
    private double giaBan;
    private int soLuong;
    private String nhaSanXuat;

    public DienThoai() {
    }

    public DienThoai(int id, String tenDienThoai, double giaBan, int soLuong, String nhaSanXuat) {
        this.id = id;
        this.tenDienThoai = tenDienThoai;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.nhaSanXuat = nhaSanXuat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDienThoai() {
        return tenDienThoai;
    }

    public void setTenDienThoai(String tenDienThoai) {
        this.tenDienThoai = tenDienThoai;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public abstract String toCSV();

    @Override
    public String toString() {
        String giaStr = (giaBan == (long) giaBan) ? String.format(java.util.Locale.US, "%.0f", giaBan)
                : String.valueOf(giaBan);
        return "ID: " + id +
                ", Ten: " + tenDienThoai +
                ", Gia ban: " + giaStr +
                ", So luong: " + soLuong +
                ", Nha san xuat: " + nhaSanXuat;
    }
}
