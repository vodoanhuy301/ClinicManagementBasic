/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.Date;

public class NhanVien {
    int maNV;
    private String tenNV;
    private String ngaySinh;
    private String sdtNV;

    public NhanVien(int maNV, String tenNV, String ngaySinh, String sdtNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.sdtNV = sdtNV;
    }
    public NhanVien() {
        this.maNV = 0;
        this.tenNV = "";
        this.ngaySinh = "";
        this.sdtNV = "";
    }
    public NhanVien(int maNV) {
        this.maNV = maNV;
        this.tenNV = "";
        this.ngaySinh = "";
        this.sdtNV = "";
    }
    

    /**
     * @return the maNV
     */
    public int getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the tenNV
     */
    public String getTenNV() {
        return tenNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    /**
     * @return the ngaySinh
     */
    public String getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the sdtNV
     */
    public String getSdtNV() {
        return sdtNV;
    }

    /**
     * @param sdtNV the sdtNV to set
     */
    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }
    
}
