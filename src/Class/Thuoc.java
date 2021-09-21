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
public class Thuoc {
    private int maThuoc;
    private String tenThuoc;
    private int giaThuoc;
    private int soLuong;
    private String ghiChu;

    public Thuoc(int maThuoc, String tenThuoc, int giaThuoc, int soLuong, String ghiChu) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.giaThuoc = giaThuoc;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }
    public Thuoc(int maThuoc, String tenThuoc, int giaThuoc, int soLuong) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.giaThuoc = giaThuoc;
        this.soLuong = soLuong;
    }
     public Thuoc(int maThuoc, String tenThuoc, int giaThuoc) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.giaThuoc = giaThuoc;
    }
    public Thuoc() {
        this.maThuoc = 0;
        this.tenThuoc = "";
        this.giaThuoc = 0;
        this.soLuong = 0;
        this.ghiChu = "";
    }

    /**
     * @return the maThuoc
     */
    public int getMaThuoc() {
        return maThuoc;
    }

    /**
     * @param maThuoc the maThuoc to set
     */
    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    /**
     * @return the tenThuoc
     */
    public String getTenThuoc() {
        return tenThuoc;
    }

    /**
     * @param tenThuoc the tenThuoc to set
     */
    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    /**
     * @return the giaThuoc
     */
    public int getGiaThuoc() {
        return giaThuoc;
    }

    /**
     * @param giaThuoc the giaThuoc to set
     */
    public void setGiaThuoc(int giaThuoc) {
        this.giaThuoc = giaThuoc;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "Ma Thuoc: " + maThuoc + " , Ten Thuoc: " + tenThuoc + " , Gia Thuoc: " + giaThuoc+" ";
    }
    
  
}
