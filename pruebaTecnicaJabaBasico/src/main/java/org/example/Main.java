package org.example;

import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import org.example.controllers.ClienteController;
import org.example.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();
        int opcion = 0;
        while (true) {
            System.out.println("\n1. Agregar nuevo cliente.");
            System.out.println("2. Listar todos los clientes.");
            System.out.println("3. Actualizar información de un cliente.");
            System.out.println("4. Eliminar un cliente.");
            System.out.println("5. Buscar cliente por ciudad.");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    clienteController.agregarCliente();
                    System.out.println("Cliente añadido con exito.");
                    break;
                case 2:
                    clienteController.listarClientes();
                    break;
                case 3:
                    clienteController.actualizarCliente();
                    System.out.println("Datos actualizados correctamente.");
                    break;
                case 4:
                    clienteController.eliminarCliente();
                    break;
                case 5:
                    clienteController.listarPorCiudad();
                    break;
                default:
                    return;
            }
        }
    }
}