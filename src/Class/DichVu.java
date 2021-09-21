/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

public class DichVu {
    private int maDV;
    private String tenDV;
    private int giaDV;
    private String ghiChu;

    public DichVu(int maDV, String tenDV, int giaDV, String ghiChu) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.ghiChu = ghiChu;
    }

    public DichVu() {
        this.maDV = 0;
        this.tenDV = "";
        this.giaDV = 0;
        this.ghiChu = "";
    }

    /**
     * @return the maDV
     */
    public int getMaDV() {
        return maDV;
    }

    /**
     * @param maDV the maDV to set
     */
    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    /**
     * @return the tenDV
     */
    public String getTenDV() {
        return tenDV;
    }

    /**
     * @param tenDV the tenDV to set
     */
    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    /**
     * @return the giaDV
     */
    public int getGiaDV() {
        return giaDV;
    }

    /**
     * @param giaDV the giaDV to set
     */
    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
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
        return "Ma DV: " + maDV + ", Ten DV: " + tenDV + ", Gia DV: " + giaDV + ", Ghi Chu: " + ghiChu;
    }
    
    
}