/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;


public class TaiKhoan extends NhanVien {
    private String userName;
    private String passWord;
    private String permission;

    public TaiKhoan(int maNV,String userName, String passWord, String permission) {
        super(maNV);
        this.userName = userName;
        this.passWord = passWord;
        this.permission = permission;
    }
    public TaiKhoan(int maNV) {
        super(maNV);
        userName = "";
        passWord = "";
        permission = "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    
}
