package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;
	
	private BrazilTaxService taxService;

	public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
		
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental cr) {
		double minutes = Duration.between(cr.getStart(), cr.getFinish()).toMinutes();
		double hours = minutes / 60;
		
		Double basicPaymant;
		if (hours <= 12) {
			basicPaymant = pricePerHour * Math.ceil(hours);
		} else {
			basicPaymant = pricePerDay * Math.ceil(hours / 24);
		}
		Double tax = taxService.tax(basicPaymant);
		
		cr.setInvoice(new Invoice(basicPaymant, tax));
	}
	
}
