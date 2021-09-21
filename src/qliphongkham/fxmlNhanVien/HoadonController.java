/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlNhanVien;

import Class.ChiTietHD;
import Class.ChiTietHDDV;
import Class.DichVu;
import Class.HoaDon;
import Class.KhachHang;
import Class.MatHang;
import Class.Thuoc;
import com.jfoenix.controls.JFXButton;
import java.awt.Insets;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import myUtil.DBconnection;
import qliphongkham.fxmlChu.DichvuController;
import qliphongkham.fxmlChu.ThuocController;

/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class HoadonController implements Initializable {

    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnReload;
    @FXML
    private TextField tfSearchThuoc;
    @FXML
    private TableColumn<Thuoc, Integer> maThuoc;
    @FXML
    private TableColumn<Thuoc, String> tenThuoc;
    @FXML
    private TableColumn<Thuoc, Integer> giaThuoc;
    @FXML
    private TableColumn<Thuoc, Integer> soLuong;
    @FXML
    private JFXButton btnSearch1;
    @FXML
    private JFXButton btnAdd1;
    @FXML
    private JFXButton btnReload1;

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    int soLuongHang;
    HoaDon hoaDon;
    ChiTietHD ctHD;
    ChiTietHDDV hddv;
    ArrayList<MatHang> mh;
    ArrayList<DichVu> dv;
    boolean checkHoaDon = false;
    boolean checkChonHang = true;
    boolean checkLichSuMua = false;
    boolean checkHoaDonDV = false;
    boolean checkChonDV = true;
    boolean checkLichSuDV = false;
    ObservableList<Thuoc> matHangThuocList = FXCollections.observableArrayList();
    ObservableList<DichVu> dichVuList = FXCollections.observableArrayList();
    @FXML
    private TableView<Thuoc> matHangThuoc;
    @FXML
    private JFXButton btnXemHD;
    private Stage stage;
    @FXML
    private TextField tfSearchDV;
    @FXML
    private JFXButton btnAdd11;
    @FXML
    private JFXButton btnXemHD1;
    @FXML
    private TableView<DichVu> matHangDV;
    @FXML
    private TableColumn<DichVu, Integer> maDichVu;
    @FXML
    private TableColumn<DichVu, String> tenDichVu;
    @FXML
    private TableColumn<DichVu, Integer> giaDichVu;
    @FXML
    private TableColumn<DichVu, String> ghiChu;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initMatHangTable();
            addButtonToTable();
            initMatHangDV();
            addButtonHDDV();
            
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initMatHangTable() throws SQLException {
        connection = DBconnection.getConnect();
        try {
            rs = connection.createStatement().executeQuery("select * from thuoc");

            while (rs.next()) {
                matHangThuocList.add(new Thuoc(rs.getInt("idThuoc"), rs.getString("tenThuoc"), rs.getInt("giaThuoc"), rs.getInt("soluong")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
        tenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
        giaThuoc.setCellValueFactory(new PropertyValueFactory<>("giaThuoc"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        matHangThuoc.setItems(matHangThuocList);
        connection.close();
    }

    private void initMatHangDV() throws SQLException {
        connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from dichvu");
        
            while (rs.next()){
                dichVuList.add(new DichVu(rs.getInt("idDV"),rs.getString("tenDV"),rs.getInt("giaDV"),rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maDichVu.setCellValueFactory(new PropertyValueFactory<>("maDV"));
        tenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDV"));
        giaDichVu.setCellValueFactory(new PropertyValueFactory<>("giaDV"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        matHangDV.setItems(dichVuList);
    }

    private void addButtonToTable() {
        TableColumn<Thuoc, Void> colBtn = new TableColumn("Action Column");

        Callback<TableColumn<Thuoc, Void>, TableCell<Thuoc, Void>> cellFactory = new Callback<TableColumn<Thuoc, Void>, TableCell<Thuoc, Void>>() {
            @Override
            public TableCell<Thuoc, Void> call(final TableColumn<Thuoc, Void> param) {
                final TableCell<Thuoc, Void> cell = new TableCell<Thuoc, Void>() {

                    private final Button btn = new Button("Thêm vào hóa đơn");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            String maNV = (String) thisStage.getUserData();
                            Thuoc thuocRow = getTableView().getItems().get(getIndex());
                            if (checkHoaDon != true) {
                                JOptionPane.showMessageDialog(null, "Vui lòng tạo Hóa Đơn trước khi chọn sản phẩm!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                TextInputDialog td = new TextInputDialog();
                                td.setTitle("Nhập dữ liệu");
                                td.setHeaderText("Thêm Mặt Hàng:" + thuocRow.getTenThuoc());
                                td.setContentText("Nhập vào số lượng: ");
                                Optional<String> result = td.showAndWait();
                                int soLuongMua = parseInt(result.get());
                                if (thuocRow.getSoLuong() == 0 || soLuongMua > thuocRow.getSoLuong()) {
                                    JOptionPane.showMessageDialog(null, "Sản phẩm này đã hết hoặc số lượng còn lại không đủ!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    int maThuocR = thuocRow.getMaThuoc();
                                    int soLuongThuocCon = (thuocRow.getSoLuong() - soLuongMua);
                                    String tenThuocR = thuocRow.getTenThuoc();
                                    int giaThuocR = thuocRow.getGiaThuoc();
                                    Thuoc thuocR = new Thuoc(maThuocR, tenThuocR, giaThuocR);
                                    addToCart(thuocR, soLuongMua);
                                    try {
                                        updateThuocConLai(maThuocR, soLuongThuocCon);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    System.out.println("Nhan vien ban hang: " + maNV);
                                }
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        matHangThuoc.getColumns().add(colBtn);

    }

    
    private void addButtonHDDV(){
        TableColumn<DichVu, Void> colBtn = new TableColumn("Action Column");
        Callback<TableColumn<DichVu, Void>, TableCell<DichVu, Void>> cellFactory = new Callback<TableColumn<DichVu, Void>, TableCell<DichVu, Void>>() {
            @Override
            public TableCell<DichVu, Void> call(final TableColumn<DichVu, Void> param) {
                final TableCell<DichVu, Void> cell = new TableCell<DichVu, Void>() {

                    private final Button btn = new Button("Thêm vào hóa đơn");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            String maNV = (String) thisStage.getUserData();
                            DichVu dvu = getTableView().getItems().get(getIndex());
                            System.out.println(dvu.toString());
                            if (checkHoaDonDV != true) {
                                JOptionPane.showMessageDialog(null, "Vui lòng tạo Hóa Đơn trước khi chọn sản phẩm!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                        int giaTong = dvu.getGiaDV();
                                        
                                        addDVToCart(dvu);
                                }
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        matHangDV.getColumns().add(colBtn);

    }
    private void addToCart(Thuoc thuocR, int soLuong) {
        checkChonHang = true;
        MatHang mhR = new MatHang(thuocR, soLuong);
        System.out.println("thuocR: " + mhR.toString());
        mh.add(mhR);
        System.out.println("So luong hien tai: " + mh.size());
        ctHD.getHoaDon().setTongTien(ctHD.getTongTien(mh.size()));
        updateTongTien();
    }
    private void addDVToCart(DichVu dvItem) {
        checkChonDV = true;
        System.out.println("thuocR: " + dvItem.toString());
        dv.add(dvItem);
        System.out.println("So luong hien tai: " + dv.size());
        System.out.println("Tong: " + hddv.getTongTien());
        hddv.getHoaDon().setTongTien(hddv.getTongTien());
        updateTongTienDV();
    }
    private void updateTongTien() {
        try {
            int tongTien = ctHD.getTongTien(mh.size());
            int maHD = ctHD.getHoaDon().getMaHD();
            connection = DBconnection.getConnect();
            connection.createStatement().executeUpdate("update hoadon set tongtien='" + tongTien + "' where idHD=" + maHD + ";");
            congDiem(tongTien);
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateTongTienDV() {
        try {
            int tongTien = hddv.getTongTien();
            System.out.println(tongTien);
            int maHD = hddv.getHoaDon().getMaHD();
            connection = DBconnection.getConnect();
            connection.createStatement().executeUpdate("update hoadon set tongtien='" + tongTien + "' where idHD=" + maHD + ";");
            congDiemDV(tongTien);
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void congDiem(int tongTien) throws SQLException{
        int diemKM = 0;
        int tongDiem=0;
        int maKh = ctHD.getHoaDon().getMaKH();
        connection = DBconnection.getConnect();
        rs= connection.createStatement().executeQuery("select diemKM from khachhang where idKH="+maKh);
        while (rs.next()){
            diemKM = rs.getInt("diemKM");
        }
        
        if (tongTien < 100000){
                tongDiem = 1 + diemKM;    
        }
        else if(tongTien > 100000){
                tongDiem = 2 + diemKM;
        }
            else if(tongTien > 500000){
                tongDiem = 10 + diemKM;
            }
            else tongDiem = 20 + diemKM;
        connection.createStatement().executeUpdate("update khachhang set diemKM='"+tongDiem+"' where idKH="+maKh+";");
        connection.close();
    }
    private void congDiemDV(int tongTien) throws SQLException{
        int diemKM = 0;
        int tongDiem=0;
        int maKh = hddv.getHoaDon().getMaKH();
        connection = DBconnection.getConnect();
        rs= connection.createStatement().executeQuery("select diemKM from khachhang where idKH="+maKh);
        while (rs.next()){
            diemKM = rs.getInt("diemKM");
        }
        
        if (tongTien < 100000){
                tongDiem = 1 + diemKM;    
        }
        else if(tongTien > 100000){
                tongDiem = 2 + diemKM;
        }
            else if(tongTien > 500000){
                tongDiem = 10 + diemKM;
            }
            else tongDiem = 20 + diemKM;
        connection.createStatement().executeUpdate("update khachhang set diemKM='"+tongDiem+"' where idKH="+maKh+";");
        connection.close();
    }
    private void updateThuocConLai(int maThuoc, int soThuocCon) throws SQLException {
        try {
            connection = DBconnection.getConnect();
            connection.createStatement().executeUpdate("update thuoc set soluong='" + soThuocCon + "' where idThuoc=" + maThuoc + ";");
            matHangThuoc.getItems().clear();
            initMatHangTable();
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
    }
    
    @FXML
    private void addHoaDon(MouseEvent event) throws IOException {
        checkHoaDon = true;
        if (checkChonHang == true) {
            hoaDon = new HoaDon();
            ctHD = new ChiTietHD();
            ctHD.setHoaDon(hoaDon);
            mh = new ArrayList<MatHang>();
            ctHD.setMatHang(mh);
            System.out.println("So luong hien tai: " + mh.size());
            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            String maNV = (String) thisStage.getUserData();
            if (maNV==null){
                JOptionPane.showMessageDialog(null, "Vui lòng chuyển tài khoản nv để tạo hóa đơn!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
            }
            maNV = maNV.replaceAll("nv", "");
            hoaDon.setMaNV(parseInt(maNV));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            hoaDon.setNgayMua(dtf.format(now));
            System.out.println(hoaDon.getNgayMua());
            TextInputDialog td = new TextInputDialog();
            td.setTitle("Nhập dữ liệu");
            td.setHeaderText("Thêm Hóa Đơn");
            td.setContentText("Nhập vào mã KH: ");
            Optional<String> result = td.showAndWait();
            hoaDon.setMaKH(parseInt(result.get()));
            System.out.println(hoaDon.getMaKH());
            insertHD(hoaDon.getMaNV(), hoaDon.getMaKH(), hoaDon.getNgayMua(), hoaDon.getTongTien());
            try {
                int maHD = getMaHD(hoaDon.getMaNV(), hoaDon.getMaKH(), hoaDon.getNgayMua(), hoaDon.getTongTien());
                hoaDon.setMaHD(maHD);
            } catch (SQLException ex) {
                Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            checkChonHang = false;
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cho hóa đơn: " + hoaDon.getMaHD() + "!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    
    private void insertHD(int maNV, Integer maKH, String ngayMua, int tongTien) {
        try {
            connection = DBconnection.getConnect();
            preparedStatement = connection.prepareStatement("INSERT INTO hoadon(idNV,idKH,ngaymua,tongtien) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, maNV);
            preparedStatement.setInt(2, maKH);
            preparedStatement.setString(3, ngayMua);
            preparedStatement.setInt(4, tongTien);
            preparedStatement.execute();

//            Stage thisStage = (Stage) tfTenThuoc.getScene().getWindow();
//            thisStage.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getMaHD(int maNV, Integer maKH, String ngayMua, int tongTien) throws SQLException {
        int maHD = 0;
        if (maKH != null) {
            rs = connection.createStatement().executeQuery("select idHD from hoadon where idNV=" + maNV + " and idKH=" + maKH + " and ngaymua='" + ngayMua + "' and tongtien=" + tongTien + ";");
        } else {
            rs = connection.createStatement().executeQuery("select idHD from hoadon where idNV=" + maNV + " and idKH is null and ngaymua='" + ngayMua + "' and tongtien=" + tongTien + ";");
        }
        if (rs.next()) {
            maHD = rs.getInt("idHD");
        }
        return maHD;
    }

    @FXML
    private void searchThuoc(MouseEvent event) throws SQLException {
        String searchWord = tfSearchThuoc.getText();
        matHangThuoc.getItems().clear();
        ObservableList<Thuoc> searchThuocList = FXCollections.observableArrayList();
        connection = DBconnection.getConnect();
        try {
            rs = connection.createStatement().executeQuery("select * from thuoc WHERE MATCH(tenThuoc) AGAINST('" + searchWord + "' IN NATURAL LANGUAGE MODE)");

            while (rs.next()) {
                searchThuocList.add(new Thuoc(rs.getInt("idThuoc"), rs.getString("tenThuoc"), rs.getInt("giaThuoc"), rs.getInt("soluong")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
        tenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
        giaThuoc.setCellValueFactory(new PropertyValueFactory<>("giaThuoc"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        matHangThuoc.setItems(searchThuocList);
        connection.close();
    }

    @FXML
    private void reloadThuocEvent(MouseEvent event) throws SQLException {
        matHangThuoc.getItems().clear();
        initMatHangTable();
    }

//    @FXML
//    private void reloadHDEvent(MouseEvent event) throws SQLException {
//        HoaDonTable.getItems().clear();
//        initHoaDon();
//    }

    @FXML
    private void xemChiTietHoaDon(MouseEvent event) throws IOException {
        if (checkHoaDon == true) {
            Parent fxml = FXMLLoader.load(getClass().getResource("view_ChiTietHD.fxml"));
            Scene scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Xem dữ liệu");
            stage.setScene(scene);
            stage.show();
            stage.setUserData(ctHD);
            System.out.println(checkLichSuMua);
            if (checkLichSuMua == false) {
                
                addLichSuMua();
            } 
        }
        else {
                JOptionPane.showMessageDialog(null, "Không có hóa đơn để xem!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
            }
    }

    private void addLichSuMua() {
        checkLichSuMua = true;
        Integer maKH = ctHD.getHoaDon().getMaKH();
        String ngayMua = ctHD.getHoaDon().getNgayMua();
        int maHD = ctHD.getHoaDon().getMaHD();
        String thuocMua = "";
        for (MatHang i : mh) {
            thuocMua = thuocMua + i.getThuoc().getTenThuoc() + ", ";
        }

        try {
            connection = DBconnection.getConnect();
            preparedStatement = connection.prepareStatement("INSERT INTO khmuathuoc(idKH,ngaymua,thuocmua,idHD) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, maKH);
            preparedStatement.setString(2, ngayMua);
            preparedStatement.setString(3, thuocMua);
            preparedStatement.setInt(4, maHD);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void addLichSuDV() {
        checkLichSuDV = true;
        Integer maKH = hddv.getHoaDon().getMaKH();
        String ngayMua = hddv.getHoaDon().getNgayMua();
        int maHD = hddv.getHoaDon().getMaHD();
        
        String dvTT = "";
        System.out.println("da them LS Dv");
        for (DichVu i : dv) {
            dvTT = dvTT + i.getTenDV() + ", ";
        }

        try {
            connection = DBconnection.getConnect();
            preparedStatement = connection.prepareStatement("INSERT INTO khkhambenh(idKH,ngaykham,noidungkham,idHD) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, maKH);
            preparedStatement.setString(2, ngayMua);
            preparedStatement.setString(3, dvTT);
            preparedStatement.setInt(4, maHD);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void searchDV(MouseEvent event) {
        String searchWord = tfSearchDV.getText();
          matHangDV.getItems().clear();
          ObservableList<DichVu> searchDichVuList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from dichvu WHERE MATCH(tenDV) AGAINST('"+searchWord+"' IN NATURAL LANGUAGE MODE)");
        
            while (rs.next()){
                searchDichVuList.add(new DichVu(rs.getInt("idDV"),rs.getString("tenDV"),rs.getInt("giaDV"),rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DichvuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maDichVu.setCellValueFactory(new PropertyValueFactory<>("maDV"));
        tenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDV"));
        giaDichVu.setCellValueFactory(new PropertyValueFactory<>("giaDV"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        matHangDV.setItems(searchDichVuList);
    }

    @FXML
    private void reloadDVEvent(MouseEvent event) throws SQLException {
        matHangDV.getItems().clear();
        initMatHangDV();
        
    }

    @FXML
    private void addHoaDonDV(MouseEvent event) {
            checkHoaDonDV = true;
        if (checkChonDV == true) {
            hoaDon = new HoaDon();
            hddv = new ChiTietHDDV();
            hddv.setHoaDon(hoaDon);
            dv = new ArrayList<DichVu>();
            hddv.setDv(dv);
            System.out.println("So luong hien tai: " + dv.size());
            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            String maNV = (String) thisStage.getUserData();
            if (maNV==null){
                JOptionPane.showMessageDialog(null, "Vui lòng chuyển tài khoản nv để tạo hóa đơn!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
            }
            maNV = maNV.replaceAll("nv", "");
            System.out.println(maNV);
            hoaDon.setMaNV(parseInt(maNV));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            hoaDon.setNgayMua(dtf.format(now));
            System.out.println(hoaDon.getNgayMua());
            TextInputDialog td = new TextInputDialog();
            td.setTitle("Nhập dữ liệu");
            td.setHeaderText("Thêm Hóa Đơn");
            td.setContentText("Nhập vào mã KH: ");
            Optional<String> result = td.showAndWait();
            hoaDon.setMaKH(parseInt(result.get()));
            insertHD(hoaDon.getMaNV(), hoaDon.getMaKH(), hoaDon.getNgayMua(), hoaDon.getTongTien());
            try {
                int maHD = getMaHD(hoaDon.getMaNV(), hoaDon.getMaKH(), hoaDon.getNgayMua(), hoaDon.getTongTien());
                hoaDon.setMaHD(maHD);
            } catch (SQLException ex) {
                Logger.getLogger(HoadonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            checkChonDV = false;
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cho hóa đơn: " + hoaDon.getMaHD() + "!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    private void xemChiTietHoaDonDV(MouseEvent event) throws IOException {
        if (checkHoaDonDV == true) {
            Parent fxml = FXMLLoader.load(getClass().getResource("view_ChiTietDV.fxml"));
            Scene scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Xem dữ liệu");
            stage.setScene(scene);
            stage.show();
            stage.setUserData(hddv);
            if (checkLichSuDV == false) {
                addLichSuDV();
            } 

        }
        else {
                JOptionPane.showMessageDialog(null, "Không có hóa đơn để xem!", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
            }
    }

}
