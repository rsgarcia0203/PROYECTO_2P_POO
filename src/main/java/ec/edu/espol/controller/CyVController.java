/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.CompradorException;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.PlacaException;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.model.VendedorException;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class CyVController implements Initializable {

    private Comprador comprador;
    private ArrayList<Comprador> compradores;
    private ArrayList<Vendedor> vendedores;

    @FXML
    private Label lbl_nombre;
    @FXML
    private TableView<Vehiculo> tableVehiculos;
    @FXML
    private TableColumn<Vehiculo, Integer> id_column;
    @FXML
    private TableColumn<Vehiculo, String> tipos_column;
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
    private TableColumn<Oferta, Integer> id_columnxO;
    @FXML
    private TableColumn<Oferta, String> tipos_columnxO;
    @FXML
    private TableColumn<Oferta, String> placas_columnxO;
    @FXML
    private TableColumn<Oferta, String> marca_columnxO;
    @FXML
    private TableColumn<Oferta, String> modelo_columnxO;
    @FXML
    private TableColumn<Oferta, String> motor_columnxO;
    @FXML
    private TableColumn<Oferta, Integer> anio_columnxO;
    @FXML
    private TableColumn<Oferta, Double> recorrido_columnxO;
    @FXML
    private TableColumn<Oferta, String> color_columnxO;
    @FXML
    private TableColumn<Oferta, String> combustible_columnxO;
    @FXML
    private TableColumn<Oferta, String> vidrios_columnxO;
    @FXML
    private TableColumn<Oferta, String> transmision_columnxO;
    @FXML
    private TableColumn<Oferta, String> traccion_columnxO;
    @FXML
    private TableColumn<Oferta, Double> precio_columnxO;
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
    private TableView<Oferta> tableOfertas;
    @FXML
    private TableColumn<Oferta, String> idO_column;
    @FXML
    private TableColumn<Oferta, String> tiposO_column;
    @FXML
    private TableColumn<Oferta, String> placasO_column;
    @FXML
    private TableColumn<Oferta, Double> precioV_column;
    @FXML
    private TableColumn<Oferta, Double> precioO_column;
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

    private ArrayList<Vehiculo> vehiculosFile;
    private Vendedor vendedor;
    private ArrayList<Vehiculo> vehicles;
    private ArrayList<Oferta> myofertas;
    private ArrayList<Oferta> ofertasFile;
    private Vehiculo vehiculoSel;
    private Oferta ofertaSel;
    private ArrayList<Oferta> ofertas;
    ArrayList<Vehiculo> vehiculos;
    @FXML
    private GridPane contenedor4;
    @FXML
    private TextField txt_ruta;
    @FXML
    private ImageView img_cargar;

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
            this.vehiculosFile = Vehiculo.readListFromFileSer("src\\main\\resources\\doc\\vehiculos.ser");
            this.ofertasFile = Oferta.readListFromFileSer("src\\main\\resources\\doc\\oferta.ser");
            ObservableList<Vehiculo> vehiculosxO = FXCollections.observableArrayList(this.vehicles);
            id_columnxO.setCellValueFactory(new PropertyValueFactory<>("ID"));
            tipos_columnxO.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            placas_columnxO.setCellValueFactory(new PropertyValueFactory<>("placa"));
            marca_columnxO.setCellValueFactory(new PropertyValueFactory<>("marca"));
            modelo_columnxO.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            motor_columnxO.setCellValueFactory(new PropertyValueFactory<>("tipo_motor"));
            anio_columnxO.setCellValueFactory(new PropertyValueFactory<>("año"));
            recorrido_columnxO.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
            color_columnxO.setCellValueFactory(new PropertyValueFactory<>("color"));
            combustible_columnxO.setCellValueFactory(new PropertyValueFactory<>("tipo_combustible"));
            vidrios_columnxO.setCellValueFactory(new PropertyValueFactory<>("vidrios"));
            transmision_columnxO.setCellValueFactory(new PropertyValueFactory<>("transmision"));
            traccion_columnxO.setCellValueFactory(new PropertyValueFactory<>("traccion"));
            precio_columnxO.setCellValueFactory(new PropertyValueFactory<>("precio"));
            tablexOfertar.setItems(vehiculosxO);
            tablexOfertar.setOnMouseClicked((MouseEvent e) -> {
                Vehiculo vehiculoSelecxO = tablexOfertar.getSelectionModel().getSelectedItem();
                this.vehiculoSel = vehiculoSelecxO;
            });
            if (!(this.vendedor.getVehiculos().isEmpty())) {
                this.vehicles = this.vendedor.getVehiculos();
                ObservableList<Vehiculo> misvehiculos = FXCollections.observableArrayList(this.vehicles);
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
                tableVehiculos.setItems(misvehiculos);
                this.myofertas = this.vendedor.getOfertas();
                this.myofertas.sort(Oferta::compareByPrecioOfertado);
                ObservableList<Oferta> misofertas = FXCollections.observableArrayList(this.myofertas);
                idO_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
                tiposO_column.setCellValueFactory(new PropertyValueFactory<>("tipo"));
                placasO_column.setCellValueFactory(new PropertyValueFactory<>("placas"));
                precioV_column.setCellValueFactory(new PropertyValueFactory<>("precioV"));
                precioO_column.setCellValueFactory(new PropertyValueFactory<>("precioOfertado"));
                tableOfertas.setItems(misofertas);
                tableOfertas.setOnMouseClicked((MouseEvent e) -> {
                    Oferta ofertaSeleccionada = tableOfertas.getSelectionModel().getSelectedItem();
                    if (ofertaSeleccionada != null) {
                        ofertaSel = ofertaSeleccionada;
                    } else {
                        Alert a = new Alert(Alert.AlertType.WARNING, "Debe seleccionar un vehículo.");
                        a.show();
                    }
                });
            }
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Documento no encontrado.");
            a.show();
        } catch (NumberFormatException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Debe de ingresar un precio para ofertar.");
            a.show();
        } catch (NullPointerException ex) {
             
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
            Vehiculo.registrarNuevoVehiculo(v, this.vehiculosFile);
            for (Vendedor ven : this.vendedores) {
                if (ven.equals(this.vendedor)) {
                    ven.getVehiculos().add(v);
                    Vendedor.saveListToFileSer("src\\main\\resources\\doc\\vendedores.ser", vendedores);
                }
            }
            this.vendedor.getVehiculos().add(v);
            Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Vehiculo registrado con éxito.");
            a2.show();
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

    public void activarCont4() {
        contenedor4.setOpacity(1);
        contenedor4.setDisable(false);
    }

    public void desactivarCont4() {
        contenedor4.setOpacity(0);
        contenedor4.setDisable(true);
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
                        v.setVendedor(this.vendedor);
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
                        v.setVendedor(this.vendedor);
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
                        v.setVendedor(this.vendedor);
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
    private void buscarOfertas(MouseEvent event) {

        try {
            if (!placas.getText().equals("")) {
                Vehiculo.validarPlaca(this.vendedor.getVehiculos(), placas.getText());
                ArrayList<Oferta> Nofertas = Oferta.searchByPlaca(this.myofertas, placas.getText());
                if (Nofertas.isEmpty()) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "No existen ofertas para el vehiculo de placas" + placas.getText());
                    a.showAndWait();
                } else {
                    tableOfertas.setItems(FXCollections.observableArrayList(Nofertas));
                }
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Debe ingresar una placa.");
                a.showAndWait();
            }
        } catch (NullPointerException ex) {

        } catch (PlacaException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "La placa ingresada no coincide con la placa de ninguno de los vehículos registrados a su nombre.");
            a.showAndWait();
        }
    }

    @FXML
    private void aceptarOferta(MouseEvent event) {
        if (ofertaSel != null) {
            try {
                Vehiculo vehiculoO = ofertaSel.getVehiculo();
                Oferta.Aceptar(this.ofertas, vehiculos, vehiculoO);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Oferta aceptada con éxito, se remitirá un mensaje al comprador a fin de que se ponga en contacto con usted.");
                a.showAndWait();
            } catch (IOException | NullPointerException ex) {
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Debe seleccionar una oferta");
            a.showAndWait();
        }
    }

    @FXML
    private void declinarOferta(MouseEvent event) {
        if (ofertaSel != null) {
            try {
                Vehiculo vehiculoO = ofertaSel.getVehiculo();
                Oferta.eliminarOferta(this.ofertas, vehiculos, vehiculoO);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Oferta declinada con éxito.");
                a.showAndWait();
            } catch (IOException | NullPointerException ex) {
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Debe seleccionar una oferta");
            a.showAndWait();
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
            if (tipo.getSelectedToggle() == rb_ambos) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Usted ya es vendedor y comprador.");
                a.show();
            } else if (tipo.getSelectedToggle() == rb_comprador) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar de rol?.");
                a.setTitle("SYSTEM-POO");
                a.setHeaderText("Confirmación de datos de registro");
                Optional<ButtonType> resultado = a.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Comprador.registrarNuevoComprador(this.vendedor.getNombres(), this.vendedor.getApellidos(), this.vendedor.getOrganizacion(), this.vendedor.getCorreo(), this.vendedor.getClave(), this.compradores);
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
                    Vendedor.registrarNuevoVendedor(this.vendedor.getNombres(), this.vendedor.getApellidos(), this.vendedor.getOrganizacion(), this.vendedor.getCorreo(), this.vendedor.getClave(), this.vendedores);
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
                    if (!contra_nueva.equals(contra_confirmada)) {
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

    @FXML
    private void cargarFile(MouseEvent event) {
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new ExtensionFilter("All Images", "*.*"),
                new ExtensionFilter("JPG", "*.jpg"),
                new ExtensionFilter("PNG", "*.png")
        );
        fc.setTitle("Buscar imagen...");
        File selectedFile = fc.showOpenDialog(new Stage());
        if (selectedFile != null) {
            Image image = new Image("file:" + selectedFile.getAbsolutePath());
            img_cargar.setImage(image);
            txt_ruta.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void ofertar(MouseEvent event) {
        try {
            if (this.vehiculoSel != null) {
                if (!precioOfer.getText().equals("")) {
                    double precioOfertado = Double.parseDouble(precioOfer.getText());
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea ofertar $" + precioOfertado + " por este vehículo?.");
                    a.setTitle("SYSTEM-POO");
                    a.setHeaderText("Confirmación de oferta");
                    Optional<ButtonType> resultado = a.showAndWait();
                    if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                        Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Oferta realizada con éxito.");
                        a2.showAndWait();
                        ArrayList<Vehiculo> Nvehiculos = (ArrayList<Vehiculo>) tablexOfertar.getItems();
                        Nvehiculos.remove(vehiculoSel);
                        Oferta.registrarNuevaOferta(vehiculoSel, comprador, this.ofertas, this.vehiculos, precioOfertado);
                        tablexOfertar.setItems(FXCollections.observableArrayList(Nvehiculos));
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Para ofertar debe ingresar un precio a ofertar por el vehículo seleccionado.");
                    a.show();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Debe seleccionar un vehículo para ofertar.");
                a.show();
            }
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Solo se puede ingresar números.");
            a.showAndWait();
        } catch (NullPointerException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "No existen vehículos para ese parámetro de búsqueda.");
            a.show();
        }

    }

    @FXML
    private void buscar(MouseEvent event) {
        try {
            if (recorridoIni.getText().equals("") && recorridoFin.getText().equals("") && anioIni.getText().equals("") && anioFin.getText().equals("") && precioIni.getText().equals("") && precioFin.getText().equals("") && precioOfer.getText().equals("")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Debe llenar al menos un parámetro antes de dar clic en buscar.");
                a.showAndWait();
            } else {
                double recorridoInicial = Double.parseDouble(recorridoIni.getPromptText());
                double recorridoFinal = Double.parseDouble(recorridoFin.getPromptText());
                if (!recorridoIni.getText().equals("") && !recorridoFin.getText().equals("")) {
                    recorridoInicial = Double.parseDouble(recorridoIni.getText());
                    recorridoFinal = Double.parseDouble(recorridoFin.getText());
                } else if ((!recorridoIni.getText().equals("") && recorridoFin.getText().equals("")) || (recorridoIni.getText().equals("") && !recorridoFin.getText().equals(""))) {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Debe de llenar el inicio y fin del parámetro de busqueda.");
                    a.setTitle("SYSTEM-POO");
                    a.setHeaderText("Rangos de recorridos.");
                    a.showAndWait();
                } else {
                    recorridoInicial = Double.parseDouble(recorridoIni.getPromptText());
                    recorridoFinal = Double.parseDouble(recorridoFin.getPromptText());
                }

                int añoInicial = Integer.parseInt(anioIni.getPromptText());
                int añoFinal = Integer.parseInt(anioFin.getPromptText());
                if (!anioIni.getText().equals("") && !anioFin.getText().equals("")) {
                    añoInicial = Integer.parseInt(anioIni.getText());
                    añoFinal = Integer.parseInt(anioFin.getText());
                } else if ((!anioIni.getText().equals("") && anioFin.getText().equals("")) || (anioIni.getText().equals("") && !anioFin.getText().equals(""))) {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Debe de llenar el inicio y fin del parámetro de busqueda.");
                    a.setTitle("SYSTEM-POO");
                    a.setHeaderText("Rangos de años.");
                    a.showAndWait();
                } else {
                    añoInicial = Integer.parseInt(anioIni.getPromptText());
                    añoFinal = Integer.parseInt(anioFin.getPromptText());
                }

                double precioInicial = Double.parseDouble(precioIni.getPromptText());
                double precioFinal = Double.parseDouble(precioFin.getPromptText());
                if (!precioIni.getText().equals("") && !precioFin.getText().equals("")) {
                    precioInicial = Double.parseDouble(precioIni.getText());
                    precioFinal = Double.parseDouble(precioFin.getText());
                } else if ((!precioIni.getText().equals("") && precioFin.getText().equals("")) || (precioIni.getText().equals("") && !precioFin.getText().equals(""))) {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Debe de llenar el inicio y fin del parámetro de busqueda.");
                    a.setTitle("SYSTEM-POO");
                    a.setHeaderText("Rangos de precios.");
                    a.showAndWait();
                } else {
                    precioInicial = Double.parseDouble(precioIni.getPromptText());
                    precioFinal = Double.parseDouble(precioFin.getPromptText());
                }

                if (recorridoInicial != 0 && recorridoFinal != 0) {
                    vehiculos = Vehiculo.vehiculosxRecorrido(vehiculos, recorridoInicial, recorridoFinal);
                    tablexOfertar.setItems(FXCollections.observableArrayList(Vehiculo.vehiculosxRecorrido(vehiculos, recorridoInicial, recorridoFinal)));
                }

                if (añoInicial != 0 && añoFinal != 0) {
                    vehiculos = Vehiculo.vehiculosxAño(vehiculos, añoInicial, añoFinal);
                    tablexOfertar.setItems(FXCollections.observableArrayList(Vehiculo.vehiculosxAño(vehiculos, añoInicial, añoFinal)));
                }

                if (precioInicial != 0 && precioFinal != 0) {
                    vehiculos = Vehiculo.vehiculosxPrecio(vehiculos, precioInicial, precioFinal);
                    tablexOfertar.setItems(FXCollections.observableArrayList(Vehiculo.vehiculosxPrecio(vehiculos, precioInicial, precioFinal)));
                }

                if (vehiculos.isEmpty()) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "No existen vehículos con esos parametros de búsqueda.");
                    a.showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Solo se puede ingresar números.");
            a.showAndWait();
        } catch (NullPointerException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "No existen vehículos para ese parámetro de búsqueda.");
            a.show();
        }

    }

}
