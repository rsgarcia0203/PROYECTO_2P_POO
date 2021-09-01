/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Automovil;
import ec.edu.espol.model.Camioneta;
import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.CompradorException;
import ec.edu.espol.model.Ingreso;
import ec.edu.espol.model.Motocicleta;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.model.VendedorException;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class CompradorController implements Initializable {

    private Comprador comprador;
    private ArrayList<Comprador> compradores;
    private ArrayList<Vendedor> vendedores;
    @FXML
    private TextField txt_correo;
    @FXML
    private TextField txt_nombres;
    @FXML
    private TextField txt_apellidos;
    @FXML
    private TextField txt_organizacion;
    @FXML
    private VBox contra_contenedor;
    @FXML
    private PasswordField txt_clave;
    @FXML
    private PasswordField txt_clave_nueva;
    @FXML
    private PasswordField txt_clave_confirmacion;
    @FXML
    private VBox rol_contenedor;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private RadioButton rb_comprador;
    @FXML
    private RadioButton rb_vendedor;
    @FXML
    private RadioButton rb_ambos;
    @FXML
    private TableView<Vehiculo> tableOfertas;
    @FXML
    private TableColumn<Vehiculo, Integer> id_column;
    @FXML
    private TableColumn<Vehiculo, String> placas_column;
    @FXML
    private TableColumn<Vehiculo, String> marca_column;
    @FXML
    private TableColumn<Vehiculo, String> modelo_column;
    @FXML
    private TableColumn<Vehiculo, String> motor_column;
    @FXML
    private TableColumn<Vehiculo, Integer> anio_column;
    @FXML
    private TableColumn<Vehiculo, Double> recorrido_column;
    @FXML
    private TableColumn<Vehiculo, String> color_column;
    @FXML
    private TableColumn<Vehiculo, String> combustible_column;
    @FXML
    private TableColumn<Vehiculo, String> vidrios_column;
    @FXML
    private TableColumn<Vehiculo, String> transmision_column;
    @FXML
    private TableColumn<Vehiculo, String> traccion_column;
    @FXML
    private TableColumn<Vehiculo, Double> precio_column;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Eliminar essto para poder cargar la ventana
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        ArrayList<Automovil> automoviles = Automovil.readFile("src\\main\\resources\\doc\\automovil.txt");
        ArrayList<Camioneta> camionetas = Camioneta.readFile("src\\main\\resources\\doc\\camioneta.txt");
        ArrayList<Motocicleta> motocicletas = Motocicleta.readFile("src\\main\\resources\\doc\\motocicleta.txt"); 
        ArrayList<Ingreso> ingresos = Ingreso.readFile("src\\main\\resources\\doc\\ingreso.txt");
        ArrayList<Oferta> ofertas = Oferta.readFile("src\\main\\resources\\doc\\oferta.txt");
        Oferta.link(compradores, automoviles, camionetas, motocicletas, ofertas);
        Ingreso.link(vendedores, vehiculos, automoviles, camionetas, motocicletas, ingresos); 
        System.out.println(vehiculos);
        ObservableList<Vehiculo> vehiculosList = FXCollections.observableArrayList(vehiculos);
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        placas_column.setCellValueFactory(new PropertyValueFactory<>("placa"));
        marca_column.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modelo_column.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        motor_column.setCellValueFactory(new PropertyValueFactory<>("tipo_motor"));
        anio_column.setCellValueFactory(new PropertyValueFactory<>("año"));
        recorrido_column.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
        color_column.setCellValueFactory(new PropertyValueFactory<>("color"));
        combustible_column.setCellValueFactory(new PropertyValueFactory<>("tipo_combustible"));
        vidrios_column.setCellValueFactory(new PropertyValueFactory<>("vidrios"));
        transmision_column.setCellValueFactory(new PropertyValueFactory<>("transmision"));
        traccion_column.setCellValueFactory(new PropertyValueFactory<>("traccion"));
        precio_column.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tableOfertas.setItems(vehiculosList);
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
        txt_nombres.setText(this.comprador.getNombres());
        txt_apellidos.setText(this.comprador.getApellidos());
        txt_correo.setText(this.comprador.getCorreo());
        txt_organizacion.setText(this.comprador.getOrganizacion());

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
            if (tipo.getSelectedToggle() == rb_comprador) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Usted ya es comprador.");
                a.show();
            } else if (tipo.getSelectedToggle() == rb_vendedor) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar de rol?.");
                a.setTitle("SYSTEM-POO");
                a.setHeaderText("Confirmación de datos de registro");
                Optional<ButtonType> resultado = a.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Vendedor.registrarNuevoVendedor(this.comprador.getNombres(), this.comprador.getApellidos(), this.comprador.getOrganizacion(), this.comprador.getCorreo(), this.comprador.getClave(), this.vendedores, "src\\main\\resources\\doc\\vendedor.txt");
                    Comprador.eliminarComprador(compradores, comprador);
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
                    Vendedor.registrarNuevoVendedor(this.comprador.getNombres(), this.comprador.getApellidos(), this.comprador.getOrganizacion(), this.comprador.getCorreo(), this.comprador.getClave(), this.vendedores, "src\\main\\resources\\doc\\vendedor.txt");
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
                if (this.comprador.getClave().equals(Util.toHexString(Util.getSHA(contra_anterior)))) {
                    if (contra_nueva.equals(contra_confirmada)) {
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar su contraseña actual?.");
                        a.setTitle("SYSTEM-POO");
                        a.setHeaderText("Confirmación de datos de registro");
                        Optional<ButtonType> resultado = a.showAndWait();
                        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                            this.comprador.setClave(contra_nueva);
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
