/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author vodoa
 */
public class ChiTietHD {
    private HoaDon hoaDon;
    private ArrayList<MatHang> matHang;
    private int n;
    public ChiTietHD(){
    hoaDon = new HoaDon();
    matHang = new ArrayList<MatHang>();
    }
    public ChiTietHD(ChiTietHD ct){
    this.hoaDon = ct.hoaDon;
    this.matHang = ct.matHang;
    }
    public ChiTietHD(HoaDon hoaDon, ArrayList<MatHang> matHang){
    this.hoaDon = new HoaDon();
    this.matHang = new ArrayList<MatHang>();
    }
    
    public ChiTietHD(HoaDon hoaDon){
    this.hoaDon = new HoaDon();
    matHang = new ArrayList<MatHang>();
    }
    public int getTongTien(int soLuongHang){
    int tongTien = 0;
    for (int i = 0; i < soLuongHang; i++){
        tongTien = tongTien + matHang.get(i).getGiaTong();
        }
    return tongTien;
    }
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    public void inMatHang(){
        for (MatHang i :matHang){
        System.out.println(i.toString());
        }
    }
    public void inCTHD() {
        System.out.println("ChiTietHD: \n" + "hoaDon: " + hoaDon.toString());
        
    }

    public ArrayList<MatHang> getMatHang() {
        return matHang;
    }

    public void setMatHang(ArrayList<MatHang> matHang) {
        this.matHang = matHang;
    }
    
   
}
