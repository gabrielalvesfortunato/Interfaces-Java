package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter rental data ");
		
		System.out.print("Car Model: ");
		String carModel = scan.nextLine();
		System.out.print("PickUp (dd/MM/yyyy HH:ss): ");
		Date start = dateformat.parse(scan.nextLine());
		System.out.print("Return (dd/MM/yyyy HH:ss): ");
		Date finish = dateformat.parse(scan.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = scan.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = scan.nextDouble();
		
		RentalService rentalservice = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalservice.processInvoice(cr);
		
		System.out.println("INVOICE: ");
		System.out.println("Basic Payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total Payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		
		scan.close();
	}
}