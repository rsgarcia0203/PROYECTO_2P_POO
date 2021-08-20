/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.PasswordException;
import ec.edu.espol.model.UserException;
import ec.edu.espol.model.Vendedor;
import java.io.FileNotFoundException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class vendedorLoginController implements Initializable {

    @FXML
    private GridPane gpane;
    @FXML
    private Button btn_ingreso;
    @FXML
    private Button btn_registro;
    @FXML
    private TextField correo;
    @FXML
    private PasswordField contra;
    @FXML
    private ImageView btn_home;

    private ArrayList<Vendedor> vendedores;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            this.vendedores = Vendedor.readFile("vendedor.txt");
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Documento no encontrado.");
            a.show();
        }
    }

    @FXML
    private void login(MouseEvent event) {
        String user = correo.getText();
        String pass = contra.getText();
        if (user.equals("") || contra.equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Debe llenar todos los campos.");
            a.show();
        } else {
            try {
                boolean validar = Vendedor.validarUsuario(user, pass, this.vendedores);
                if (validar == true) {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Ingreso exitoso.");
                    a.show();
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
            }
        }
    }

    @FXML
    private void register(MouseEvent event) {
    }

    @FXML
    private void menuPrincipal(MouseEvent event) {
    }

}
