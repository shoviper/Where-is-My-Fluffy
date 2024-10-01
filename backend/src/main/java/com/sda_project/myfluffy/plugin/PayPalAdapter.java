// package com.sda_project.myfluffy.plugin;

// import com.paypal.api.payments.*;
// import com.paypal.base.rest.APIContext;
// import com.paypal.base.rest.PayPalRESTException;
// import com.sda_project.myfluffy.user.User;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.util.ArrayList;
// import java.util.List;

// @Component
// public class PayPalAdapter implements RewardPlugin {
    
//     // @Value("${paypal.client.id}")
//     private String clientId = "AVruIeOva3ebVB04VVCjIGqGoWIdyU-IuEssTM9lTaeDwD1T5BwmSeHS_sWfbp-aI68EtzXvp8J3t6UE";
    
//     // @Value("${paypal.client.secret}")
//     private String clientSecret = "EB6f8W1ogYwE5AynT8ZfmR0MYBuHtcWiIpsZuYEEhuXZORPYcgVz0tHlzXJDcHlpgSEmWctckyYU0eby";
    
//     // @Value("${paypal.mode}")
//     private String mode = "sandbox";

//     @Override
//     public String createPayment(User sender, User recipient, double amount) {
//         Payment payment = buildPaymentObject(sender, recipient, amount);
        
//         try {
//             APIContext apiContext = new APIContext(clientId, clientSecret, mode);
//             Payment createdPayment = payment.create(apiContext);
            
//             return getApprovalLink(createdPayment);
//         } catch (PayPalRESTException e) {
//             e.printStackTrace();
//         }
        
//         return null;
//     }

//     @Override
//     public boolean executePayment(String paymentId, String payerId) {
//         try {
//             APIContext apiContext = new APIContext(clientId, clientSecret, mode);
//             Payment payment = new Payment();
//             payment.setId(paymentId);

//             PaymentExecution paymentExecution = new PaymentExecution();
//             paymentExecution.setPayerId(payerId);

//             Payment executedPayment = payment.execute(apiContext, paymentExecution);
            
//             return executedPayment.getState().equals("approved");
//         } catch (PayPalRESTException e) {
//             e.printStackTrace();
//         }
        
//         return false;
//     }

//     private Payment buildPaymentObject(User sender, User recipient, double amount) {
//         Amount paymentAmount = new Amount();
//         paymentAmount.setCurrency("USD");
//         paymentAmount.setTotal(String.format("%.2f", amount));

//         Transaction transaction = new Transaction();
//         transaction.setAmount(paymentAmount);
//         transaction.setDescription("Pet reward payment");

//         List<Transaction> transactions = new ArrayList<>();
//         transactions.add(transaction);

//         Payer payer = new Payer();
//         payer.setPaymentMethod("paypal");

//         Payment payment = new Payment();
//         payment.setIntent("sale");
//         payment.setPayer(payer);
//         payment.setTransactions(transactions);

//         RedirectUrls redirectUrls = new RedirectUrls();
//         redirectUrls.setCancelUrl("http://localhost:8080/cancel");
//         redirectUrls.setReturnUrl("http://localhost:8080/success");
//         payment.setRedirectUrls(redirectUrls);

//         return payment;
//     }

//     private String getApprovalLink(Payment createdPayment) {
//         for (Links link : createdPayment.getLinks()) {
//             if (link.getRel().equalsIgnoreCase("approval_url")) {
//                 return link.getHref();
//             }
//         }
//         return null;
//     }
// }