/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinfo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Saiful Islam
 */
public class FXMLDocumentController implements Initializable {
    
    private final String USERNAME = "root";
    private final String HOSTNAME = "localhost";
    private final String DBNAME = "java2015";
    private final String PASSWORD = "saif2015";
    private final String DBURL = "jdbc:mysql://" + HOSTNAME + "/" + DBNAME;

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField brandNameField;
    @FXML
    private TextField unitPriceField;
    @FXML
    private TableView<Product> productTableView;
    private ObservableList<Product> productList;
    @FXML
    private TableColumn<Product, Number> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> brandNameColumn;
    @FXML
    private TableColumn<Product, Number> unitPriceColumn;
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productList = FXCollections.observableArrayList();
        productTableView.setItems(productList);
        
        productIdColumn
                .setCellValueFactory(
                    data->
                        new SimpleIntegerProperty(data.getValue().getProductId()));
        productNameColumn
                .setCellValueFactory(
                        data
                        -> new SimpleStringProperty(data.getValue().getProductName()));
        brandNameColumn
                .setCellValueFactory(
                        data
                        -> new SimpleStringProperty(data.getValue().getBrandName()));
        unitPriceColumn
                .setCellValueFactory(
                        data
                        -> new SimpleDoubleProperty(data.getValue().getUnitPrice()));
        
        try {
            Connection connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "select * from productInfo;";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                int productId = resultSet.getInt("produuctId");
                String productName = resultSet.getString("productName");
                String brandName = resultSet.getString("brandName");
                double unitPrice = resultSet.getDouble("unitPrice");
                Product p = new Product(productId, productName, brandName, unitPrice);
                productList.add(p);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException occured!" + ex);
        }
        
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            
            int productId = Integer.parseInt(productIdField.getText());
            String productName = productNameField.getText();
            String brandName = brandNameField.getText();
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            
            String query = "insert into productInfo values(" + productId + ",'" + productName + "', '" + brandName + "'," + unitPrice + ");";
            
            int s = statement.executeUpdate(query);
            productList.add(new Product(productId, productName,brandName,unitPrice));
            resetForm();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    @FXML
    private void handleSelectionAction(MouseEvent event) {
        Product p = productTableView.getSelectionModel().getSelectedItem();
        
        productIdField.setText(String.valueOf(p.getProductId()));
        productNameField.setText(p.getProductName());
        brandNameField.setText(p.getBrandName());
        unitPriceField.setText(String.valueOf(p.getUnitPrice()));
       
    }
    @FXML
    private void handleUpdateAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            
            int productId = Integer.parseInt(productIdField.getText());
            String productName = productNameField.getText();
            String brandName = brandNameField.getText();
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            
            String query = "update productInfo set produuctId = " 
                    + productId + "," + "productName = '" + productName + "', brandName = '" 
                        + brandName + "', unitPrice = " + unitPrice + " where produuctId = " + productId + ";";
            
            int s = statement.executeUpdate(query);
            //productList.add(new Product(productId, productName,brandName,unitPrice));
            resetForm();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private void resetForm(){
        productIdField.clear();
        productNameField.clear();
        brandNameField.clear();
        unitPriceField.clear();
    }

    
    
}
