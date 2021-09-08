/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author rsgar
 */
public class Oferta implements Serializable {

    private int ID;
    private int IDvehiculo;
    private String tipo; 
    private String placas;
    private double precioV;
    private int IDcomprador;
    private double precioOfertado;
    private Vehiculo vehiculo;
    private Comprador comprador;
    private static final long serialVersionUID = 8799656478674716638L;

    public Oferta(int ID, int IDvehiculo, String tipo, int IDcomprador, double precioOfertado) {
        this.ID = ID;
        this.IDvehiculo = IDvehiculo;
        this.tipo = tipo;
        this.IDcomprador = IDcomprador;
        this.precioOfertado = precioOfertado;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDvehiculo() {
        return this.IDvehiculo;
    }

    public void setIDvehiculo(int IDvehiculo) {
        this.IDvehiculo = IDvehiculo;
    }

    public int getIDcomprador() {
        return IDcomprador;
    }

    public void setIDcomprador(int IDcomprador) {
        this.IDcomprador = IDcomprador;
    }

    public double getPrecioOfertado() {
        return precioOfertado;
    }

    public void setPrecioOfertado(double precioOfertado) {
        this.precioOfertado = precioOfertado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public double getPrecioV() {
        return precioV;
    }

    public void setPrecioV(double precioV) {
        this.precioV = precioV;
    }

    @Override
    public String toString() {
        return "Oferta<" + this.ID + ">{IDvehiculo=" + this.IDvehiculo + ", IDtipo=" + this.tipo + ", IDcomprador=" + this.IDcomprador + ", precioOfertado=" + this.precioOfertado + ", vehiculo=" + this.vehiculo + ", comprador=" + this.comprador + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.IDvehiculo;
        hash = 23 * hash + this.IDcomprador;
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
        final Oferta other = (Oferta) obj;
        return !((this.IDvehiculo != other.IDvehiculo) && (this.IDcomprador != other.IDcomprador) && (this.precioOfertado != other.precioOfertado));
    }

    public static int nextID(ArrayList<Oferta> ofertas) {
        int max = 0;
        for (Oferta oferta : ofertas) {
            if (oferta.getID() > max) {
                max = oferta.getID();
            }
        }
        return (max + 1);
    }
    
    public static ArrayList<Oferta> searchByPlaca(ArrayList<Oferta> ofertas, String placa) throws PlacaException{
        ArrayList<Oferta> xplaca = new ArrayList <>();
        for(Oferta o: ofertas)
        {
            if(o.getPlacas().equals(placa))
            {
                xplaca.add(o);
            }
        }
        return xplaca;      
    }
    
    public static void registrarNuevaOferta(Vehiculo vehiculo, Comprador comprador, ArrayList<Oferta> ofertas, double precioOfertado) {
        int id = Oferta.nextID(ofertas);
        int IDvehiculo = vehiculo.getID();
        int IDcomprador = comprador.getID();
        String tipo = vehiculo.getTipo();
        Oferta nuevo = new Oferta(id, IDvehiculo, tipo, IDcomprador, precioOfertado);
        if (!ofertas.contains(nuevo)) {
            ofertas.add(nuevo);
            Oferta.saveListToFileSer("src\\main\\resources\\doc\\oferta.ser", ofertas);
        } else {
            throw new OfertaException("");
        }
        
    }

    public static void eliminarOferta(ArrayList<Oferta> ofertas, Vehiculo vehiculo) throws IOException {
        for (int i = 0; i < ofertas.size(); i++) {
            if (ofertas.get(i).getVehiculo().equals(vehiculo)) {
                ofertas.remove(i);
            }
        }
        Oferta.saveListToFileSer("src\\main\\resources\\doc\\oferta.ser", ofertas);
    }

    public static void saveListToFileSer(String nomfile, ArrayList<Oferta> ofertas) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(nomfile)))) {
            out.writeObject(ofertas);
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

    public static ArrayList<Oferta> readListFromFileSer(String nomfile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(nomfile)))) {
            ArrayList<Oferta> ofertas = (ArrayList<Oferta>) in.readObject();
            in.close();
            return ofertas;
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se encontró el archivo.");
            a.show();
        } catch (IOException | ClassNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo.");
            a.show();
        }
        return null;
    }
    
    public static int compareByPrecioOfertado(Oferta o1, Oferta o2){
        return Double.compare(o2.getPrecioOfertado(),o1.getPrecioOfertado());
    }

}
