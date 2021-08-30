/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.PasswordException;
import ec.edu.espol.model.UserException;
import ec.edu.espol.model.Vendedor;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class VendedorController implements Initializable {

    @FXML
    private Text textcorrVend;
    @FXML
    private Text textnomVend;
    @FXML
    private Text textapellVend;
    @FXML
    private Text textorgVend;
    @FXML
    private PasswordField textpasswordVend;
    @FXML
    private TextField correoVend;
    @FXML
    private TextField contraVend;
    
    private ArrayList<Comprador> compradores;
    
    private ArrayList<Vendedor> vendedores;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.compradores = Comprador.readFile("src\\main\\resources\\doc\\comprador.txt");
            this.vendedores = Vendedor.readFile("src\\main\\resources\\doc\\vendedor.txt");
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Documento no encontrado.");
            a.show();
        }
    }    

    @FXML
    private void btnIngresar(MouseEvent event) {
        String correo = correoVend.getText();
        String contra = contraVend.getText();
        
        if(correo.equals("")||contra.equals("")){
            Alert a = new Alert(Alert.AlertType.WARNING,"Complete todos los campos");
            a.show();
        }else{
            try{
                boolean validarVendedor = Vendedor.validarUsuario(correo, contra, vendedores);
                if(validarVendedor == true){
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Ingreso exitoso");
                    a.show();
                    FXMLLoader fxmlloader = App.loadFXMLoader("Vehiculo");
                    App.setRoot(fxmlloader);
                }else{
                    Alert a = new Alert(Alert.AlertType.ERROR, "Usuario o contraseña incorrecta");
                    a.show();
                }                
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"No se puede abrir el siguente grafo de scene");
                a.show();
            }catch (UserException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Correo no registrado.");
                a.show();
            } catch (PasswordException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Clave incorrecta.");
                a.show();
            }catch (NoSuchAlgorithmException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Error de clave.");
                a.show();
            }
        }
    }
    
    
}
