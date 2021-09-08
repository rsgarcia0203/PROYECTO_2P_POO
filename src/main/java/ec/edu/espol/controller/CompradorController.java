/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.CompradorException;
import ec.edu.espol.model.Venta;
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
    private Label lbl_nombre;
    @FXML
    private ComboBox cbx_tipos;
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
    private ArrayList<Vehiculo> vehicles;
    @FXML
    private TextField precioOfer;
    private Vehiculo vehiculoSel;
    private ArrayList<Oferta> ofertas;
    ArrayList<Vehiculo> vehiculos;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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
            this.vehicles = Vehiculo.readListFromFileSer("src\\main\\resources\\doc\\vehiculos.ser");
            this.ofertas = Oferta.readListFromFileSer("src\\main\\resources\\doc\\oferta.ser");
            ObservableList<Vehiculo> vehiculosObs = FXCollections.observableArrayList(this.vehicles);
            tableOfertas.setItems(vehiculosObs);
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
            tableOfertas.setOnMouseClicked((MouseEvent e) -> {
                Vehiculo vehiculoSeleccionado = tableOfertas.getSelectionModel().getSelectedItem();
                this.vehiculoSel = vehiculoSeleccionado;
            });
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Documento no encontrado.");
            a.show();
        } catch (NullPointerException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "No existen vehículos para ese parámetro de búsqueda.");
            a.show();
        }

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
                    Vendedor.registrarNuevoVendedor(this.comprador.getNombres(), this.comprador.getApellidos(), this.comprador.getOrganizacion(), this.comprador.getCorreo(), this.comprador.getClave(), this.vendedores);
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
                    Vendedor.registrarNuevoVendedor(this.comprador.getNombres(), this.comprador.getApellidos(), this.comprador.getOrganizacion(), this.comprador.getCorreo(), this.comprador.getClave(), this.vendedores);
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
                    if (!contra_nueva.equals(contra_anterior)) {
                        if (contra_nueva.equals(contra_confirmada)) {
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de cambiar su contraseña actual?.");
                            a.setTitle("SYSTEM-POO");
                            a.setHeaderText("Confirmación de datos de registro");
                            Optional<ButtonType> resultado = a.showAndWait();
                            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                                this.comprador.cambiarClave(compradores, contra_nueva);
                                Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Contraseña cambiada con éxito.");
                                a2.showAndWait();
                                toMain();
                            }
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR, "La nueva contraseña no coincide con la indicada en el campo de confirmación. Escríbala de nuevo. En las contraseñas, debe escribir las letras mayúsculas y minúsculas correctamente.");
                            a.show();
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "La nueva contraseña no puede ser igual a la anterior. Escríbala de nuevo. En las contraseñas, debe escribir las letras mayúsculas y minúsculas correctamente.");
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
                    tableOfertas.setItems(FXCollections.observableArrayList(Vehiculo.vehiculosxRecorrido(vehiculos, recorridoInicial, recorridoFinal)));
                }

                if (añoInicial != 0 && añoFinal != 0) {
                    vehiculos = Vehiculo.vehiculosxAño(vehiculos, añoInicial, añoFinal);
                    tableOfertas.setItems(FXCollections.observableArrayList(Vehiculo.vehiculosxAño(vehiculos, añoInicial, añoFinal)));
                }

                if (precioInicial != 0 && precioFinal != 0) {
                    vehiculos = Vehiculo.vehiculosxPrecio(vehiculos, precioInicial, precioFinal);
                    tableOfertas.setItems(FXCollections.observableArrayList(Vehiculo.vehiculosxPrecio(vehiculos, precioInicial, precioFinal)));
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
                        ArrayList<Vehiculo> Nvehiculos = (ArrayList<Vehiculo>) tableOfertas.getItems();
                        Nvehiculos.remove(vehiculoSel);
                        Oferta.registrarNuevaOferta(vehiculoSel, comprador, this.ofertas,this.vehiculos, precioOfertado);
                        tableOfertas.setItems(FXCollections.observableArrayList(Nvehiculos));
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
    private void cambiar(ActionEvent event) {
        String item = (String) cbx_tipos.getValue();
        if (item != null) {

            if (!item.equals("Todos")) {
                vehiculos = Vehiculo.vehiculosxtipo(this.vehicles, item);
                tableOfertas.setItems(FXCollections.observableArrayList(Vehiculo.vehiculosxtipo(vehiculos, item)));
            } else {
                vehiculos = this.vehicles;
                tableOfertas.setItems(FXCollections.observableArrayList(vehiculos));
            }

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Debe elegir un tipo.");
            a.showAndWait();
        }
    }
}
