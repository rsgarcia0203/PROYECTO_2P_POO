/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.CompradorException;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.model.VendedorException;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class VendedorController {

    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtOrganizacion;
    @FXML
    private VBox rol_contenedor;
    @FXML
    private RadioButton rb_comprador;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private RadioButton rb_vendedor;
    @FXML
    private RadioButton rb_ambos;
    @FXML
    private VBox contra_contenedor;
    @FXML
    private PasswordField txt_clave;
    @FXML
    private PasswordField txt_clave_nueva;
    @FXML
    private PasswordField txt_clave_confirmacion;
    @FXML
    private RadioButton btnAuto;
    @FXML
    private RadioButton btnCamioneta;
    @FXML
    private RadioButton btnMoto;
    
    private ArrayList<Vendedor> vendedores;
    
    private ArrayList<Comprador> compradores;
    
    private Vendedor vendedor;
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        txtNombres.setText(this.vendedor.getNombres());
        txtApellidos.setText(this.vendedor.getApellidos());
        txtCorreo.setText(this.vendedor.getCorreo());
        txtOrganizacion.setText(this.vendedor.getOrganizacion());

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

    @FXML
    private void PasswordChange(MouseEvent event) {
        contra_contenedor.setOpacity(1);
        contra_contenedor.setDisable(false);
        rol_contenedor.setOpacity(0);
        rol_contenedor.setDisable(true);
    }

    @FXML
    private void RoleChange(MouseEvent event) {
        rol_contenedor.setOpacity(1);
        rol_contenedor.setDisable(false);
        contra_contenedor.setOpacity(0);
        contra_contenedor.setDisable(true);
        
    }

    @FXML
    private void cambiarRol(MouseEvent event) {
        try {
            if (tipo.getSelectedToggle() == rb_vendedor) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Usted ya es vendedor.");
                a.show();
            } else if (tipo.getSelectedToggle() == rb_comprador) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar de rol?.");
                a.setTitle("SYSTEM-POO");
                a.setHeaderText("Confirmación de datos de registro");
                Optional<ButtonType> resultado = a.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Comprador.registrarNuevoComprador(this.vendedor.getNombres(), this.vendedor.getApellidos(), this.vendedor.getOrganizacion(), this.vendedor.getCorreo(), this.vendedor.getClave(),this.compradores, "src\\main\\resources\\doc\\vendedor.txt");
                    Vendedor.eliminarVendedor(vendedores, vendedor);
                    Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Rol cambiado con éxito.");
                    a2.showAndWait();
                    toMain();
                }              

            } else if (tipo.getSelectedToggle() == rb_ambos) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar de rol?.");
                a.setTitle("SYSTEM-POO");
                a.setHeaderText("Confirmación de datos de registro");
                Optional<ButtonType> resultado = a.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Comprador.registrarNuevoComprador(this.vendedor.getNombres(), this.vendedor.getApellidos(), this.vendedor.getOrganizacion(), this.vendedor.getCorreo(), this.vendedor.getClave(),this.compradores, "src\\main\\resources\\doc\\vendedor.txt");
                    Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Rol cambiado con éxito.");
                    a2.showAndWait();
                    toMain();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "No hay seleccionado ningún rol.");
                a.show();
            }
        } catch (CompradorException | VendedorException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Correo registrado anteriormente.");
            a.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo del siguiente grafo de scene");
            a.show();
        }
    }
 

    @FXML
    private void cambiarContra(MouseEvent event) {
        String contra_anterior = txt_clave.getText();
        String contra_nueva = txt_clave_nueva.getText();
        String contra_confirmada = txt_clave_confirmacion.getText();
        if (contra_anterior.equals("") || contra_nueva.equals("") || contra_confirmada.equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Debe llenar todos los campos.");
            a.show();
        } else {
            try {
                if (this.vendedor.getClave().equals(Util.toHexString(Util.getSHA(contra_anterior)))) {
                    if (contra_nueva.equals(contra_confirmada)) {
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar su contraseña actual?.");
                        a.setTitle("SYSTEM-POO");
                        a.setHeaderText("Confirmación de datos de registro");
                        Optional<ButtonType> resultado = a.showAndWait();
                        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                            this.vendedor.setClave(contra_nueva);
                            Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Contraseña cambiada con éxito.");
                            a2.showAndWait();
                            toMain();
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "La nueva contraseña no coincide con la indicada en el campo de confirmación. Escríbala de nuevo. En las contraseñas, debe escribir las letras mayúsculas y minúsculas correctamente.");
                        a.show();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "La contraseña anterior no es correcta. Escríbala de nuevo. En las contraseñas, debe escribir las letras mayúsculas y minúsculas correctamente.");
                    a.show();
                }
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
    }

    
}
