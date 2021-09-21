/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.ArrayList;

/**
 *
 * @author vodoa
 */
public class ChiTietHDDV {
    private HoaDon hoaDon;
    private ArrayList<DichVu> dv;

    public ChiTietHDDV(HoaDon hoaDon, ArrayList<DichVu> dv) {
        this.hoaDon = hoaDon;
        this.dv = dv;
    }
    public ChiTietHDDV(ChiTietHDDV hddv) {
        this.hoaDon = hddv.hoaDon;
        this.dv = hddv.dv;
    }
     public ChiTietHDDV() {
    hoaDon = new HoaDon();
    dv = new ArrayList<DichVu>();
    }
    public ChiTietHDDV(HoaDon hoaDon){
    this.hoaDon = new HoaDon();
    dv = new ArrayList<DichVu>();
    }
    public int getTongTien(){
    int tongTien = 0;
    for (DichVu dvv : dv){
        tongTien = tongTien + dvv.getGiaDV();
        }
    return tongTien;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ArrayList<DichVu> getDv() {
        return dv;
    }

    public void setDv(ArrayList<DichVu> dv) {
        this.dv = dv;
    }
    
}
