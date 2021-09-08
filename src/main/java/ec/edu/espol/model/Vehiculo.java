/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author rsgar
 */
public class Vehiculo implements Serializable{
    private int ID;
    private String tipo;
    private int IDvendedor;
    private String placa;
    private String marca;
    private String modelo;
    private String tipo_motor;
    private int año;
    private double recorrido;
    private String color;
    private String tipo_combustible;
    private double precio;
    private String traccion; // solo camionetas
    private String transmision; //solo autos
    private String vidrios;  // solo autos
    private Vendedor vendedor;
    private ArrayList<Oferta> ofertas;
    private static final long serialVersionUID = 8799656478674716638L;
    
    // constructor automovil
    public Vehiculo(int ID, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio, String transmision, String vidrios){
        this.IDvendedor = IDvendedor;
        this.tipo = "AUTOMOVIL";
        this.ID = ID;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.traccion = "NO APLICA";
        this.transmision = transmision;
        this.vidrios = vidrios;
    }
    
    //contrustor moto
    public Vehiculo(int ID, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio){
        this.IDvendedor = IDvendedor;
        this.tipo = "MOTOCICLETA";
        this.ID = ID;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.traccion = "NO APLICA";
        this.transmision = "NO APLICA";
        this.vidrios = "NO APLICA";
    }

    // constructor camioneta
    public Vehiculo(int ID, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio, String transmision, String vidrios, String traccion){
        this.IDvendedor = IDvendedor;
        this.tipo = "CAMIONETA";
        this.ID = ID;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.traccion = traccion;
        this.transmision = transmision;
        this.vidrios = vidrios;
    }
    
    public Vehiculo(int ID, String tipo, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, String transmision, String vidrios, String traccion, double precio){
        this.IDvendedor = IDvendedor;
        this.tipo = tipo;
        this.ID = ID;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.traccion = traccion;
        this.transmision = transmision;
        this.vidrios = vidrios;
    }
    
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIDvendedor() {
        return this.IDvendedor;
    }

    public void setIDvendedor(int IDvendedor) {
        this.IDvendedor = IDvendedor;
    }
       
    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_motor() {
        return this.tipo_motor;
    }

    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    public int getAño() {
        return this.año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getRecorrido() {
        return this.recorrido;
    }

    public void setRecorrido(float recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_combustible() {
        return this.tipo_combustible;
    }

    public void setTipo_combustible(String tipo_combustible) {
        this.tipo_combustible = tipo_combustible;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }
    
    public Vendedor getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }    

    public ArrayList<Oferta> getOfertas() {
        return this.ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        return Objects.equals(this.placa, other.placa);
    }
    
    public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int id)
    {
        for(Vehiculo v : vehiculos)
        {
            if(v.ID == id)
                return v;
        }
        return null;
    }
    
    public static void validarPlaca(ArrayList<Vehiculo> vehiculos, String placa)throws PlacaException{
        boolean val = false;
        for(Vehiculo v: vehiculos)
        {
            if(v.getPlaca().equals(placa))
            {
                val = true;
            }
        }
        if (val == false){
            throw new PlacaException("");
        }
    }
    
    public static ArrayList<Vehiculo> searchByPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        ArrayList<Vehiculo> xplaca = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getPlaca().equals(placa))
            {
                xplaca.add(v);
            }
        }
        return xplaca;
    }
    
    public static ArrayList<Vehiculo> vehiculosxtipo(ArrayList<Vehiculo> vehiculos, String tipo)
    {
        ArrayList<Vehiculo> xtipo = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getTipo().equals(tipo.toUpperCase()))
            {
                xtipo.add(v);
            }
        }
        return xtipo;
    }
        
    public static ArrayList<Vehiculo> vehiculosxRecorrido(ArrayList<Vehiculo> vehiculos, double recorridoInicio, double recorridoFinal)
    {
        ArrayList<Vehiculo> xRecorrido = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getRecorrido() >= recorridoInicio && v.getRecorrido() <= recorridoFinal)
            {
                xRecorrido.add(v);
            }
        }
        return xRecorrido;
    }
    
    public static ArrayList<Vehiculo> vehiculosxAño(ArrayList<Vehiculo> vehiculos, int añoInicio, int añoFinal)
    {
        ArrayList<Vehiculo> xAño = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getAño() >= añoInicio && v.getAño() <= añoFinal)
            {
                xAño.add(v);
            }
        }
        return xAño;
    }
    
    public static ArrayList<Vehiculo> vehiculosxPrecio(ArrayList<Vehiculo> vehiculos, double precioInicio, double precioFinal)
    {
        ArrayList<Vehiculo> xPrecio = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getPrecio() >= precioInicio && v.getPrecio() <= precioFinal)
            {
                xPrecio.add(v);
            }
        }
        return xPrecio;
    }
        
      public static int nextID(ArrayList<Vehiculo> vehiculos){
        int max = 0;
        for(Vehiculo vehiculo: vehiculos){
            if (vehiculo.getID() > max) {
                max = vehiculo.getID();
            }
        }
        return (max+1);        
    }
     
    public static void eliminarVehiculo(ArrayList<Vehiculo> vehiculos, Vehiculo vehiculo) throws IOException
    {
        for(int i = 0; i < vehiculos.size(); i++)
        {
            if (vehiculos.get(i).equals(vehiculo))
            {
                vehiculos.remove(i);
            }
        }
        Vehiculo.saveListToFileSer("src\\main\\resources\\doc\\vehiculos.ser", vehiculos);

    }
        
    public static void registrarNuevoVehiculo(Vehiculo vehiculo, ArrayList<Vehiculo> vehiculos) throws CompradorException{
        if (vehiculos.contains(vehiculo) == false) {
            vehiculos.add(vehiculo);
            Vehiculo.saveListToFileSer("src\\main\\resources\\doc\\vehiculos.ser", vehiculos);
        } else {
            throw new VehiculoException("");
        }

    }
    
    public static void saveListToFileSer(String nomfile, ArrayList<Vehiculo> vehiculos){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(nomfile)))){
            out.writeObject(vehiculos);
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se encontró el archivo.");
            a.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo.");
            a.show();
        } 
    }
    
    public static ArrayList<Vehiculo> readListFromFileSer(String nomfile) throws FileNotFoundException{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(nomfile)))){
            ArrayList<Vehiculo> vendedores = (ArrayList<Vehiculo>)in.readObject();
            in.close();
            return vendedores;
        } catch (IOException | ClassNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo.");
            a.show();
        }
        return null;
    }
}
