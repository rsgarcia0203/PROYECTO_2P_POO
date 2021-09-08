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
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.idVendedor+"|"+this.idVehiculo+"|"+this.tipo);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Venta> readFile(String nomfile){
        ArrayList<Venta> ingresos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Venta i = new Venta(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
                ingresos.add(i);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ingresos;
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
        Venta v =new Venta(ID,idvendedor,idvehiculo,vehiculo.getTipo());
        v.saveFile(nomfile); 
    }
    
    public static void link(ArrayList<Vendedor> vendedores, ArrayList<Vehiculo> vehiculos, ArrayList<Venta> ingresos){
        for(Venta i: ingresos){
            Vendedor v = Vendedor.searchByID(vendedores, i.getIdVendedor());
            Vehiculo vh = Vehiculo.searchByID(vehiculos, i.getIdVehiculo());
            v.getVehiculos().add(vh);
            vh.setVendedor(v);
            i.setVendedor(v);
            i.setVehiculo(vh);      
        }
    }
    
    public static void eliminarIngreso(ArrayList<Venta> ingresos, Vehiculo vehiculo) throws IOException
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

