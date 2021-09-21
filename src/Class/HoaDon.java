/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author vodoa
 */
public class HoaDon {
    
    private int maHD;
    private int maNV;
    private Integer maKH;
    private String ngayMua;
    private int tongTien;

    public HoaDon(int maHD, int maNV, int maKH, String ngayMua, int tongTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
    }
    public HoaDon() {
        maHD = 0;
        maNV = 0;
        maKH = null;
        ngayMua = "";
        tongTien = 0;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "\nMa NV: " + maNV + ", Ma KH: " + maKH + "\nNgay Mua: " + ngayMua + ", Tong Tien: " + tongTien + " VND \n";
    }
    
}
