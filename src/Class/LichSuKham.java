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
public class LichSuKham {
    private int maKH;
    private String ngayKham;
    private String noiDungKham;
    private int maHD;

    public LichSuKham(int maKH, String ngayKham, String noiDungKham, int maHD) {
        this.maKH = maKH;
        this.ngayKham = ngayKham;
        this.noiDungKham = noiDungKham;
        this.maHD = maHD;
    }
    public LichSuKham() {
        maKH = 0;
        ngayKham = "";
        noiDungKham = "";
        maHD = 0;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getNoiDungKham() {
        return noiDungKham;
    }

    public void setNoiDungKham(String noiDungKham) {
        this.noiDungKham = noiDungKham;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }
    
    
}
