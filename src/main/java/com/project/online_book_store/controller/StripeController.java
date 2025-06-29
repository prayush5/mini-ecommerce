package com.project.online_book_store.controller;

import com.project.online_book_store.dto.CartItemDTO;
import com.project.online_book_store.service.CartService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
@CrossOrigin(origins = "http://localhost:4200")
public class StripeController {



    public StripeController(){
        Stripe.apiKey = "...";
    }

    //sk_test_51RfD3HP7JdwML9HEe5fUAlQgWb1pHVHO4os8efIAbEtXQ6d1PNkWs5SmkRfFxGhbklS21PzTweuIEnAkLuoJOpw000pwhDdOpp

    @Autowired
    private CartService cartService;

    @PostMapping("/create-session")
    public ResponseEntity<Map<String, String>> createSession(@RequestBody List<CartItemDTO> items) throws Exception{
        List<SessionCreateParams.LineItem> stripeItems = new ArrayList<>();

        for(CartItemDTO item : items){
            String productName = item.getBookTitle() != null ? item.getBookTitle() : item.getMedicineName();

            stripeItems.add(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity((long) item.getQuantityDTO())
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd")
                                            .setUnitAmount(item.getPriceDTO().multiply(BigDecimal.valueOf(100)).longValue())
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName(productName)
                                                            .build()
                                            )
                                            .build()
                            )
                            .build()
            );
        }
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/success")
                .setCancelUrl("http://localhost:4200/cancel")
                .addAllLineItem(stripeItems)
                .build();

        Session session = Session.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("url", session.getUrl());
        return ResponseEntity.ok(response);
    }


}
