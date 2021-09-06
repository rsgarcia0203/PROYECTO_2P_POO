/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.CompradorException;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.model.VendedorException;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class CyVController implements Initializable {

    @FXML
    private Label lbl_nombre;
    @FXML
    private TableView<?> tableVehiculos;
    @FXML
    private TableColumn<?, ?> id_column;
    @FXML
    private TableColumn<?, ?> tipos_column;
    @FXML
    private TableColumn<?, ?> placas_column;
    @FXML
    private TableColumn<?, ?> marca_column;
    @FXML
    private TableColumn<?, ?> modelo_column;
    @FXML
    private TableColumn<?, ?> motor_column;
    @FXML
    private TableColumn<?, ?> anio_column;
    @FXML
    private TableColumn<?, ?> recorrido_column;
    @FXML
    private TableColumn<?, ?> color_column;
    @FXML
    private TableColumn<?, ?> combustible_column;
    @FXML
    private TableColumn<?, ?> vidrios_column;
    @FXML
    private TableColumn<?, ?> transmision_column;
    @FXML
    private TableColumn<?, ?> traccion_column;
    @FXML
    private TableColumn<?, ?> precio_column;
    @FXML
    private ComboBox cbx_tipos;
    @FXML
    private GridPane contenedor1;
    @FXML
    private TextField txt_placa;
    @FXML
    private TextField txt_marca;
    @FXML
    private TextField txt_modelo;
    @FXML
    private TextField txt_motor;
    @FXML
    private TextField txt_anio;
    @FXML
    private TextField txt_recorrido;
    @FXML
    private TextField txt_color;
    @FXML
    private TextField txt_combustible;
    @FXML
    private TextField txt_precio;
    @FXML
    private GridPane contenedor2;
    @FXML
    private TextField txt_transmision;
    @FXML
    private TextField txt_vidrios;
    @FXML
    private GridPane contenedor3;
    @FXML
    private TextField txt_traccion;
    @FXML
    private Button btn_registrar;
    @FXML
    private TableView<Vehiculo> tablexOfertar;
    @FXML
    private TableColumn<Vehiculo, Integer> id_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> tipos_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> placas_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> marca_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> modelo_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> motor_columnxO;
    @FXML
    private TableColumn<Vehiculo, Integer> anio_columnxO;
    @FXML
    private TableColumn<Vehiculo, Double> recorrido_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> color_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> combustible_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> vidrios_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> transmision_columnxO;
    @FXML
    private TableColumn<Vehiculo, String> traccion_columnxO;
    @FXML
    private TableColumn<Vehiculo, Double> precio_columnxO;
    @FXML
    private ComboBox cbx_tiposxO;
    @FXML
    private TextField recorridoIni;
    @FXML
    private TextField recorridoFin;
    @FXML
    private TextField anioIni;
    @FXML
    private TextField anioFin;
    @FXML
    private TextField precioIni;
    @FXML
    private TextField precioFin;
    @FXML
    private TextField precioOfer;
    @FXML
    private TableView<?> tableOfertas;
    @FXML
    private TableColumn<?, ?> idO_column;
    @FXML
    private TableColumn<?, ?> tiposO_column;
    @FXML
    private TableColumn<?, ?> placasO_column;
    @FXML
    private TableColumn<?, ?> marcaO_column;
    @FXML
    private TableColumn<?, ?> modeloO_column;
    @FXML
    private TableColumn<?, ?> anioO_column;
    @FXML
    private TableColumn<?, ?> colorO_column;
    @FXML
    private TableColumn<?, ?> precioV_column;
    @FXML
    private TableColumn<?, ?> precioO_column;
    @FXML
    private GridPane contenedor31;
    @FXML
    private TextField placas;
    @FXML
    private Button btn_registrar1;
    @FXML
    private TextField txt_correo;
    @FXML
    private TextField txt_nombres;
    @FXML
    private TextField txt_apellidos;
    @FXML
    private TextField txt_organizacion;
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
    private Comprador comprador;
    private ArrayList<Comprador> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Vehiculo> vehicles;
    private ArrayList<Vehiculo> vehiculosFile;
    private Vendedor vendedor;
    private Vehiculo vehiculoSel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<String> tipos = new ArrayList<>();
            tipos.add("Todos");
            tipos.add("Automovil");
            tipos.add("Motocicleta");
            tipos.add("Camioneta");
            cbx_tipos.setItems(FXCollections.observableArrayList(tipos));
            this.vehicles = Vehiculo.readFile("src\\main\\resources\\doc\\vehiculo.txt");
            ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList(this.vehicles);
            id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
            tipos_column.setCellValueFactory(new PropertyValueFactory<>("tipo"));
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
            tablexOfertar.setItems(vehiculos);
            tableOfertas.setOnMouseClicked((MouseEvent e) -> {
                Vehiculo vehiculoSel = tablexOfertar.getSelectionModel().getSelectedItem();
                if (vehiculoSel != null) {
                    if (!precioOfer.getText().equals("")) {
                        double precioOfertado = Double.parseDouble(precioOfer.getText());
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea ofertar $" + precioOfertado + " por este vehículo?.");
                        a.setTitle("SYSTEM-POO");
                        a.setHeaderText("Confirmación de oferta");
                        Optional<ButtonType> resultado = a.showAndWait();
                        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                            Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Rol cambiado con éxito.");
                            a2.showAndWait();
                            ArrayList<Vehiculo> Nvehiculos = (ArrayList<Vehiculo>) tableOfertas.getItems();
                            Nvehiculos.remove(vehiculoSel);
                            Oferta.registrarNuevaOferta(vehiculoSel, comprador, precioOfertado, "oferta.txt");
                            tablexOfertar.setItems(FXCollections.observableArrayList(Nvehiculos));
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.WARNING, "Para ofertar debe ingresar un precio a ofertar por el vehículo seleccionado.");
                        a.show();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Debe seleccionar un vehículo.");
                    a.show();
                }
            });
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Documento no encontrado.");
            a.show();
        } catch (NumberFormatException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Debe de ingresar un precio para ofertar.");
            a.show();
        }
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        txt_nombres.setText(this.vendedor.getNombres());
        txt_apellidos.setText(this.vendedor.getApellidos());
        txt_correo.setText(this.vendedor.getCorreo());
        txt_organizacion.setText(this.vendedor.getOrganizacion());
        lbl_nombre.setText(this.vendedor.getNombres() + " " + this.vendedor.getApellidos());
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
        txt_nombres.setText(this.comprador.getNombres());
        txt_apellidos.setText(this.comprador.getApellidos());
        txt_correo.setText(this.comprador.getCorreo());
        txt_organizacion.setText(this.comprador.getOrganizacion());
        lbl_nombre.setText(this.comprador.getNombres() + " " + this.comprador.getApellidos());
    }

    public void setCompradores(ArrayList<Comprador> compradores) {
        this.compradores = compradores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculoSel = vehiculo;
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
    private void cambiar(ActionEvent event) {
        String item = (String) cbx_tipos.getValue();
        if (item != null) {
            btn_registrar.setOpacity(1);
            btn_registrar.setDisable(false);
            if (item.equals("Motocicleta")) {
                activarCont1();
                desactivarCont2();
                desactivarCont3();
            } else if (item.equals("Automovil")) {
                activarCont1();
                activarCont2();
                desactivarCont3();
            } else if (item.equals("Camioneta")) {
                activarCont1();
                activarCont2();
                activarCont3();
            }
        }
    }

    public void registrarVehiculo(Vehiculo v) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de registrar el vehículo con esos datos?.");
        a.setTitle("SYSTEM-POO");
        a.setHeaderText("Confirmación de datos de registro");
        Optional<ButtonType> resultado = a.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            v.saveFile("src\\main\\resources\\doc\\vehiculo.txt");
            Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Vehiculo registrado con éxito.");
            a2.showAndWait();
            toMain();
        }
    }

    public void activarCont1() {
        contenedor1.setOpacity(1);
        contenedor1.setDisable(false);
    }

    public void activarCont2() {
        contenedor2.setOpacity(1);
        contenedor2.setDisable(false);
    }

    public void desactivarCont2() {
        contenedor2.setOpacity(0);
        contenedor2.setDisable(true);
    }

    public void activarCont3() {
        contenedor3.setOpacity(1);
        contenedor3.setDisable(false);
    }

    public void desactivarCont3() {
        contenedor3.setOpacity(0);
        contenedor3.setDisable(true);
    }

    @FXML
    private void Registrar(MouseEvent event) {
        try {
            String item = (String) cbx_tipos.getValue();
            if (item != null) {
                int id = vehiculosFile.size() + 1;
                if (item.equals("Motocicleta")) {
                    String placa = txt_placa.getText();
                    String marca = txt_marca.getText();
                    String modelo = txt_modelo.getText();
                    String motor = txt_motor.getText();
                    int año = Integer.parseInt(txt_anio.getText());
                    double recorrido = Double.parseDouble(txt_recorrido.getText());
                    String color = txt_color.getText();
                    String combustible = txt_combustible.getText();
                    double precio = Double.parseDouble(txt_precio.getText());
                    if (placa.equals("") || marca.equals("") || modelo.equals("") || motor.equals("") || (txt_anio.getText()).equals("") || (txt_recorrido.getText()).equals("") || color.equals("") || combustible.equals("") || (txt_precio.getText()).equals("")) {
                        Alert a = new Alert(Alert.AlertType.WARNING, "Debe llenar todos los campos.");
                        a.show();
                    } else {
                        Vehiculo v = new Vehiculo(id, this.vendedor.getID(), placa, marca, modelo, motor, año, recorrido, color, combustible, precio);
                        registrarVehiculo(v);
                    }
                } else if (item.equals("Automovil")) {
                    String placa = txt_placa.getText();
                    String marca = txt_marca.getText();
                    String modelo = txt_modelo.getText();
                    String motor = txt_motor.getText();
                    int año = Integer.parseInt(txt_anio.getText());
                    double recorrido = Double.parseDouble(txt_recorrido.getText());
                    String color = txt_color.getText();
                    String combustible = txt_combustible.getText();
                    double precio = Double.parseDouble(txt_precio.getText());
                    String transmision = txt_transmision.getText();
                    String vidrios = txt_vidrios.getText();
                    if (placa.equals("") || marca.equals("") || modelo.equals("") || motor.equals("") || (txt_anio.getText()).equals("") || (txt_recorrido.getText()).equals("") || color.equals("") || combustible.equals("") || (txt_precio.getText()).equals("") || transmision.equals("") || vidrios.equals("")) {
                        Alert a = new Alert(Alert.AlertType.WARNING, "Debe llenar todos los campos.");
                        a.show();
                    } else {
                        Vehiculo v = new Vehiculo(id, this.vendedor.getID(), placa, marca, modelo, motor, año, recorrido, color, combustible, precio, transmision, vidrios);
                        registrarVehiculo(v);
                    }
                } else if (item.equals("Camioneta")) {
                    String placa = txt_placa.getText();
                    String marca = txt_marca.getText();
                    String modelo = txt_modelo.getText();
                    String motor = txt_motor.getText();
                    int año = Integer.parseInt(txt_anio.getText());
                    double recorrido = Double.parseDouble(txt_recorrido.getText());
                    String color = txt_color.getText();
                    String combustible = txt_combustible.getText();
                    double precio = Double.parseDouble(txt_precio.getText());
                    String transmision = txt_transmision.getText();
                    String vidrios = txt_vidrios.getText();
                    String traccion = txt_traccion.getText();
                    if (placa.equals("") || marca.equals("") || modelo.equals("") || motor.equals("") || (txt_anio.getText()).equals("") || (txt_recorrido.getText()).equals("") || color.equals("") || combustible.equals("") || (txt_precio.getText()).equals("") || transmision.equals("") || vidrios.equals("") || traccion.equals("")) {
                        Alert a = new Alert(Alert.AlertType.WARNING, "Debe llenar todos los campos.");
                        a.show();
                    } else {
                        Vehiculo v = new Vehiculo(id, this.vendedor.getID(), placa, marca, modelo, motor, año, recorrido, color, combustible, precio, transmision, vidrios, traccion);
                        registrarVehiculo(v);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Solo se puede ingresar números.");
            a.showAndWait();
        }
    }

    @FXML
    private void buscar(MouseEvent event) {
    }

    @FXML
    private void aceptarOferta(MouseEvent event) {
    }

    @FXML
    private void declinarOferta(MouseEvent event) {
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
            if (tipo.getSelectedToggle() == rb_ambos) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Usted ya es vendedor y comprador.");
                a.show();
            } else if (tipo.getSelectedToggle() == rb_comprador) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar de rol?.");
                a.setTitle("SYSTEM-POO");
                a.setHeaderText("Confirmación de datos de registro");
                Optional<ButtonType> resultado = a.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Comprador.registrarNuevoComprador(this.vendedor.getNombres(), this.vendedor.getApellidos(), this.vendedor.getOrganizacion(), this.vendedor.getCorreo(), this.vendedor.getClave(), this.compradores, "src\\main\\resources\\doc\\vendedor.txt");
                    Vendedor.eliminarVendedor(vendedores, vendedor);
                    Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Rol cambiado con éxito.");
                    a2.showAndWait();
                    toMain();
                }
            } else if (tipo.getSelectedToggle() == rb_vendedor) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar de rol?.");
                a.setTitle("SYSTEM-POO");
                a.setHeaderText("Confirmación de datos de registro");
                Optional<ButtonType> resultado = a.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Vendedor.registrarNuevoVendedor(this.vendedor.getNombres(), this.vendedor.getApellidos(), this.vendedor.getOrganizacion(), this.vendedor.getCorreo(), this.vendedor.getClave(), this.vendedores, "src\\main\\resources\\doc\\vendedor.txt");
                    Comprador.eliminarComprador(compradores, comprador);
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

            }
        }
    }


}
