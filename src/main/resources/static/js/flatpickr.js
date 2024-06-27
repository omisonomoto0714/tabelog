let Date = new Date();
Date = Date.setMonth(Date.getMonth() + 3);

flatpickr('#fromCheckinDateToCheckoutDate', {
	mode: "range",
	locale: 'ja',
	minDate: 'today',
	maxDate: maxDate
});


flatpickr('#visitTime', {
	enableTime: true,
	noCalendar: true,
	dateFormat: "H:i",
	time_24hr: true
})