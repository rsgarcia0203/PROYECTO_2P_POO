/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.PasswordException;
import ec.edu.espol.model.UserException;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.model.Venta;
import ec.edu.espol.proyecto2p.App;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class LoginController implements Initializable {

    @FXML
    private TextField correo;
    @FXML
    private Button btn_ingreso;
    @FXML
    private Button btn_registro;
    @FXML
    private PasswordField contra;

    private ArrayList<Comprador> compradores;
    private ArrayList<Vendedor> vendedores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.compradores = Comprador.readListFromFileSer("src\\main\\resources\\doc\\compradores.ser");
            this.vendedores = Vendedor.readListFromFileSer("src\\main\\resources\\doc\\vendedores.ser");
            
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Documento no encontrado.");
            a.show();
        }
    }

    @FXML
    private void login(MouseEvent event) {
        String user = correo.getText();
        String pass = contra.getText();
        if (user.equals("") || pass.equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Debe llenar todos los campos.");
            a.show();
        } else {
            try {
                Comprador validarComprador = Comprador.validarUsuario(user, pass, this.compradores);
                Vendedor validarVendedor = Vendedor.validarUsuario(user, pass, this.vendedores);
                if (validarComprador != null && validarVendedor != null) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingreso exitoso.");
                    a.show();
                    FXMLLoader fxmlloader = App.loadFXMLoader("CyV");
                    App.setRoot(fxmlloader);
                } else if (validarComprador != null) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingreso exitoso.");
                    a.show();
                    FXMLLoader fxmlloader = App.loadFXMLoader("Comprador");
                    App.setRoot(fxmlloader);
                    CompradorController cc = fxmlloader.getController();
                    cc.setComprador(validarComprador);
                    cc.setCompradores(compradores);
                    cc.setVendedores(vendedores);
                } else if (validarVendedor != null) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingreso exitoso.");
                    a.show();
                    FXMLLoader fxmlloader = App.loadFXMLoader("Vendedor");
                    App.setRoot(fxmlloader);
                    VendedorController vc = fxmlloader.getController();
                    vc.setVendedores(vendedores);
                    vc.setCompradores(compradores);
                    vc.setVendedor(validarVendedor);
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Usuario no registrado.");
                    a.show();
                }
            } catch (UserException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Correo no registrado.");
                a.show();
            } catch (PasswordException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Clave incorrecta.");
                a.show();
            } catch (NoSuchAlgorithmException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Error de clave.");
                a.show();
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo del siguiente grafo de scene");
                a.show();
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void register(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("Register");
            App.setRoot(fxmlloader);
            RegisterController rc = fxmlloader.getController();
            rc.setCompradores(compradores);
            rc.setVendedores(vendedores);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo del siguiente grafo de scene");
            a.show();
        }
    }

}
