/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private TextField textusuarioVend;
    @FXML
    private TextField textcontraVend;
    @FXML
    private Button btnIngresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
