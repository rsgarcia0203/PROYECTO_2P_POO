/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author rsgar
 */
public class Venta implements Serializable{
    private int ID;
    private int idVendedor;
    private int idVehiculo;
    private String tipo;
    private Vendedor vendedor;
    private Vehiculo vehiculo;
    private static final long serialVersionUID = 8799656478674716638L;    

    public Venta(int ID, int idVendedor, int idVehiculo, String tipo) {
        this.ID = ID;
        this.idVendedor = idVendedor;
        this.idVehiculo = idVehiculo;
        this.tipo = tipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdVehiculo() {
        return this.idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.idVehiculo;
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
        final Venta other = (Venta) obj;
        if (this.idVehiculo != other.idVehiculo) {
            return false;
        }
        return true;
    }
    
    public static int nextID(ArrayList<Venta> ventas){
        int max = 0;
        for(Venta venta: ventas){
            if (venta.getID() > max) {
                max = venta.getID();
            }
        }
        return (max+1);        
    }
    
    public static void nextIngreso(Vehiculo vehiculo, String nomfile, ArrayList<Venta> ventas){
        int ID = Venta.nextID(ventas);
        int idvehiculo = vehiculo.getID();
        int idvendedor = vehiculo.getIDvendedor();
        Venta v = new Venta(ID,idvendedor,idvehiculo,vehiculo.getTipo());
        if(!ventas.contains(v)){
            Venta.saveListToFileSer("src\\main\\resources\\doc\\venta.ser", ventas);
        } else {
            throw new VentaException("");
        }
    }
    
    public static void eliminarIngreso(ArrayList<Venta> ingresos, Vehiculo vehiculo)
    {
        for(int i = 0; i < ingresos.size(); i++)
        {
            if (ingresos.get(i).getVehiculo().equals(vehiculo))
            {
                ingresos.remove(i);
            }
        }    
        Venta.saveListToFileSer("src\\main\\resources\\doc\\venta.ser", ingresos);
    }

    @Override
    public String toString() {
        return "Ingreso<" + this.ID + ">{idVendedor=" + this.idVendedor + ", idVehiculo=" + this.idVehiculo + ", idTipo=" + this.tipo + ", vendedor=" + this.vendedor + ", vehiculo=" + this.vehiculo + '}';
    }
    
    public static void saveListToFileSer(String nomfile, ArrayList<Venta> ventas){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(nomfile)))){
            out.writeObject(ventas);
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
    
    public static ArrayList<Venta> readListFromFileSer(String nomfile){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(nomfile)))){
            ArrayList<Venta> ventas = (ArrayList<Venta>)in.readObject();
            in.close();
            return ventas;
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se encontró el archivo.");
            a.show();
        } catch (IOException | ClassNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo.");
            a.show();
        }
        return null;
    }
}

