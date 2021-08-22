/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Vendedor;
import ec.edu.espol.model.VendedorException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class VendedorRegisterController implements Initializable {


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
    
    private ArrayList<Vendedor> vendedores;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setVendedores(ArrayList<Vendedor> vendedores){
        this.vendedores = vendedores;
    }
    
    @FXML
    private void registrar(MouseEvent event) {
        try{
            Vendedor.registrarNuevoVendedor(nombres.getText(),apellidos.getText(), organizacion.getText(), correo.getText(), contra.getText(),vendedores, "comprador.txt");
    
        } catch (VendedorException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "Correo registrado anteriormente.");
            a.show();
        }
    }

}
