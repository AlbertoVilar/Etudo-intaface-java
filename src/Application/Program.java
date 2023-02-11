package Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Invoice;
import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		System.out.println("Enter data car: ");
		System.out.print("Model: ");
		String model = sc.next();
		sc.nextLine();
		System.out.print("Start of the date rental (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt); 	
		System.out.print("Finish of the date rental (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt); 	
		CarRental carRental = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("Enter Price per hour: ");
		Double pricePerHour = sc.nextDouble();
		System.out.print("Enter Price per Day: ");
		Double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(carRental);
		System.out.println("INVOICE:");
		System.out.println("Basic Paymant: $ " + String.format("%.2f",carRental.getInvoice().getBasicPaymant()));
		System.out.println("Tax: $ " + String.format("%.2f",carRental.getInvoice().getTax()));
		System.out.println("Total Paymant: $ " + String.format("%.2f",carRental.getInvoice().getTotalPaymant()));
		
		sc.close();

	}

}
