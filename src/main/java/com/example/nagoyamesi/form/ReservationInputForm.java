package com.example.nagoyamesi.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "来店日を選択してください。")
	private String fromReservationDate;

	@NotBlank(message = "来店時間を選択して下さい。")
	private String fromReservationTime;

	@NotNull(message = "来店人数を入力してください。")
	@Min(value = 1, message = "来店人数は1人以上に設定してください。")
	private Integer numberOfPeople;

	// 来店日を取得する
	public LocalDate getReservationDate() {
		String reservationDate = getFromReservationDate();
		return LocalDate.parse(reservationDate);
	}

	// 来店時間を取得する
	public LocalTime getReservationTime() {
		String reservationTime = getFromReservationTime();
		return LocalTime.parse(reservationTime);
	}

}
