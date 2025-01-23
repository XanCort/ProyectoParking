package com.example.proyectoparking.utils;

public class CalculoUtils {

    //Método para calcular el precio de la retirada del vehículo
    public static  double calcularPrecio(double tiempo){
        //si el tiempo es menor de una hora se cobran 3€ si es mayor se cobra 3€ la hora
        return tiempo*3<3 ? 3.00: tiempo*3;
    }
}
