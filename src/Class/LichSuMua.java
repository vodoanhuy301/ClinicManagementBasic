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
public class LichSuMua {
    private int maKH;
    private String ngayMua;
    private String thuocMua;
    private int maHD;

    public LichSuMua(int maKH, String ngayMua, String thuocMua, int maHD) {
        this.maKH = maKH;
        this.ngayMua = ngayMua;
        this.thuocMua = thuocMua;
        this.maHD = maHD;
    }
    public LichSuMua() {
        maKH = 0;
        ngayMua = "";
        thuocMua = "";
        maHD = 0;
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

    public String getThuocMua() {
        return thuocMua;
    }

    public void setThuocMua(String thuocMua) {
        this.thuocMua = thuocMua;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }
    
}
