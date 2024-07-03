package com.example.nagoyamesi.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	public boolean isWithinOpeningTime(String fromReservationTime, String openingTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime parsedOpeningTime = LocalTime.parse(openingTime, formatter);
		LocalTime parsedFromReservationTime = LocalTime.parse(fromReservationTime, formatter);
		return parsedFromReservationTime.isAfter(parsedOpeningTime)
				|| parsedFromReservationTime.equals(parsedOpeningTime);
	}

	public boolean isWithinClosingTime(String fromReservationTime, String closing) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime parsedClosingTime = LocalTime.parse(closing, formatter);
		LocalTime parsedFromReservationTime = LocalTime.parse(fromReservationTime, formatter);
		return parsedFromReservationTime.isBefore(parsedClosingTime)
				|| parsedFromReservationTime.equals(parsedClosingTime);
	}

}
