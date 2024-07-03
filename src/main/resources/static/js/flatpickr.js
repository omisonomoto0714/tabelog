let maxDate = new Date();
maxDate = maxDate.setMonth(maxDate.getMonth() + 3);

flatpickr('#fromReservationDate', {
	mode: "range",
	locale: 'ja',
	minDate: 'today',
//	maxDate: maxDate
});


flatpickr('#fromReservationTime', {
	enableTime: true,
	noCalendar: true,
	dateFormat: "H:i",
	time_24hr: true
})