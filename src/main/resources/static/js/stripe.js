const stripe = Stripe('sk_test_51PHcNwP3y0uycL0Qt9Uo2tf3xC89UHfeEZCGn4klr7BZyzguVgeQJsBbqlz6ZiCuUGscW69Ou3fR6ucg4RVWpfl500uGYkoVwj');
 const paymentButton = document.querySelector('#paymentButton');
 
 paymentButton.addEventListener('click', () => {
   stripe.redirectToCheckout({
     sessionId: sessionId
   })
 });