package com.example.nagoyamesi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.nagoyamesi.repository.RoleRepository;
import com.example.nagoyamesi.repository.UserRepository;
import com.example.nagoyamesi.security.UserDetailsImpl;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
	@Value("${stripe.api-key}")
	private String stripeApiKey;

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Autowired
	public StripeService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public String createStripeSession(UserDetailsImpl userDetailsImpl, HttpServletRequest httpServletRequest)
			throws StripeException {
		Stripe.apiKey = stripeApiKey;
		String requestUrl = new String(httpServletRequest.getRequestURL());
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.addLineItem(SessionCreateParams.LineItem.builder()
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName("NAGOYAMESHI有料会員").build())
								.setUnitAmount(300L)
								.setCurrency("jpy").build())
						.setQuantity(1L).build())
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.setSuccessUrl(
						requestUrl.replaceAll("/api/checkout-session", "")
								+ "/api/success?session_id={CHECKOUT_SESSION_ID}")
				.setCancelUrl(requestUrl.replace("/api/checkout-session", ""))
				.setPaymentIntentData(
						SessionCreateParams.PaymentIntentData.builder()
								.putMetadata("userId", userDetailsImpl.getUser().getId().toString())
								.putMetadata("amount", "300")
								.build())
				.build();
		 try {
             Session session = Session.create(params);
             return session.getId();
         } catch (StripeException e) {
             e.printStackTrace();
             return "";
         }
}
}