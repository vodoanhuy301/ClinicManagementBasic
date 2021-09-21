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
public class KhachHang {
    private int maKH;
    private String hoTenKH;
    private String sdtKH;
    private int diemKM;

    public KhachHang(int maKH, String hoTenKH, String sdtKH, int diemKM) {
        this.maKH = maKH;
        this.hoTenKH = hoTenKH;
        this.sdtKH = sdtKH;
        this.diemKM = diemKM;
    }
    public KhachHang() {
        maKH = 0;
        hoTenKH = "";
        sdtKH = "";
        diemKM = 0;
    }
    public KhachHang(String hoTenKH) {
        this.hoTenKH= hoTenKH;
        maKH = 0;
        sdtKH = "";
        diemKM = 0;
    }
    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public int getDiemKM() {
        return diemKM;
    }

    public void setDiemKM(int diemKM) {
        this.diemKM = diemKM;
    }
    
}
