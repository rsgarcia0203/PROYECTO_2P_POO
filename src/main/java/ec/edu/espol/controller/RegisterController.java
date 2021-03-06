/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.UserException;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField nombres;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField organizacion;
    @FXML
    private TextField correo;
    @FXML
    private PasswordField contra;

    private ArrayList<Comprador> compradores;
    private ArrayList<Vendedor> vendedores;
    @FXML
    private RadioButton rb_comprador;
    @FXML
    private RadioButton rb_vendedor;
    @FXML
    private RadioButton rb_ambos;
    @FXML
    private ToggleGroup tipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCompradores(ArrayList<Comprador> compradores) {
        this.compradores = compradores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public void toMain() {
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("Login");
            App.setRoot(fxmlloader);
        } catch (IOException ex) {
            Alert a3 = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo del siguiente grafo de scene");
            a3.show();
        }
    }
    

    public boolean resultado() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "??Est?? seguro que desea registrarse con esos datos?");
        a.setTitle("SYSTEM-POO");
        a.setHeaderText("Confirmaci??n de datos de registro");
        Optional<ButtonType> resultado = a.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }

    @FXML
    private void registrar(MouseEvent event) {
        String nom = nombres.getText();
        String ape = apellidos.getText();
        String org = organizacion.getText();
        String cor = correo.getText();
        String pas = contra.getText();
        if (nom.equals("") || ape.equals("") || org.equals("") || cor.equals("") || pas.equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Debe llenar todos los campos.");
            a.show();
        } else {
            try {
                if (Comprador.validarRegistro(cor, compradores) == false || Vendedor.validarRegistro(cor, vendedores) == false) {
                    if (tipo.getSelectedToggle() == rb_comprador) {
                        if (resultado() == true) {
                            Comprador.registrarNuevoComprador(nom, ape, org, cor, pas, this.compradores);
                            toMain();
                        }
                    } else if (tipo.getSelectedToggle() == rb_vendedor) {
                        if (resultado() == true) {
                            Vendedor.registrarNuevoVendedor(nom, ape, org, cor, pas, this.vendedores);
                            toMain();
                        }
                    } else if (tipo.getSelectedToggle() == rb_ambos) {
                        if (resultado() == true) {
                            Comprador.registrarNuevoComprador(nom, ape, org, cor, pas, this.compradores);
                            Vendedor.registrarNuevoVendedor(nom, ape, org, cor, pas, this.vendedores);
                            toMain();
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Debe seleccionar un rol para poder registrarse.");
                        a.setTitle("SYSTEM-POO");
                        a.setHeaderText("ERROR");
                        a.show();
                    }
                } 
            } catch (UserException | NoSuchAlgorithmException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Correo registrado anteriormente.");
                a.setTitle("SYSTEM-POO");
                a.setHeaderText("ERROR");
                a.show();
            }

        }
    }

    @FXML
    private void back(MouseEvent event) {
        toMain();
    }

}
