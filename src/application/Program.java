package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.RentalService;
import model.services.TaxServiceUsa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc= new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.println("--------------------------------------------");
        System.out.println("Coloque os dados do aluguel:");
        System.out.println();
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada: ");
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Entrega: ");
        Date finish = sdf.parse(sc.nextLine());
        System.out.println("--------------------------------------------");
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
        System.out.print("Coloque o valor por hora: R$ ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Coloque o valor por dia: R$ ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new TaxServiceUsa());

        rentalService.processInvoice(cr);
        System.out.println("--------------------------------------------");
        System.out.println("Fatura: ");
        System.out.println("Valor sem imposto: R$ " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Valor do imposto: R$ " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Valor total a pagar: R$ " + String.format("%.2f", cr.getInvoice().getTotalPayment()));

        sc.close();
    }
}
