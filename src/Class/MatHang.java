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
public class MatHang{
    private int soMatHang;
    private Thuoc thuoc;
    public MatHang(Thuoc thuoc,int soMatHang) {
        this.thuoc = thuoc;
        this.soMatHang = soMatHang;
    }
    public MatHang() {
        thuoc = new Thuoc();
        soMatHang = 0;
    }
    public int getGiaTong(){
        return (thuoc.getGiaThuoc()*soMatHang);
    }

    public int getSoMatHang() {
        return soMatHang;
    }

    public void setSoMatHang(int soMatHang) {
        this.soMatHang = soMatHang;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    @Override
    public String toString() {
        return thuoc.toString()+" | So luong "+soMatHang+" | Gia: "+String.valueOf(new MatHang(thuoc,soMatHang).getGiaTong())+" VND\n";
    }
    
    
}
