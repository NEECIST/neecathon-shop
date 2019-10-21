package com.joaomariajaneiro.neechathon.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.joaomariajaneiro.neechathon.dao.SimplePurchase;
import com.joaomariajaneiro.neechathon.model.Product;
import com.joaomariajaneiro.neechathon.model.Purchase;
import com.joaomariajaneiro.neechathon.model.Team;
import com.joaomariajaneiro.neechathon.model.User;
import com.joaomariajaneiro.neechathon.repository.ProductRepository;
import com.joaomariajaneiro.neechathon.repository.PurchaseRepository;
import com.joaomariajaneiro.neechathon.repository.TeamRepository;
import com.joaomariajaneiro.neechathon.repository.UserRepository;
import com.joaomariajaneiro.neechathon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/purchases")
@RestController
@CrossOrigin
public class PurchaseController {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<SimplePurchase> getAllPurchases(@RequestHeader Map<String, String> headers) {

        User user;
        try {
            user = userService.getUserFromToken(headers);
        } catch (Exception e) {
            return ImmutableList.of();
        }

        if (user == null) {
            return ImmutableList.of();
        }

        if (user.getTeam().isAdmin()) {
            return createSimplePurchaseList(purchaseRepository.findAll());
        } else {
            return ImmutableList.of();
        }
    }

    @RequestMapping(value = "/{teamName}", method = RequestMethod.GET)
    public List<SimplePurchase> getTransactionsFromTeamName(@PathVariable String teamName) {
        Team team;
        try {
            team = teamRepository.findByName(teamName);
        } catch (Exception e) {
            return null;
        }
        try {
            return team.getPurchases().stream().map((purchase) -> new SimplePurchase(
                    purchase.getId(),
                    purchase.getTeamName(), purchase.getProduct(), purchase.getQuantity(),
                    purchase.getTotalAmount(), purchase.getUser().getUsername(),
                    purchase.getSourceTeamCash(),
                    purchase.getTimestamp())).collect(Collectors.toList());
        } catch (Exception e) {
            return ImmutableList.of();
        }
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Iterable<SimplePurchase> getMyTransactions(@RequestHeader Map<String,
            String> headers) {
        User user;
        try {
            user = userService.getUserFromToken(headers);
        } catch (Exception e) {
            return ImmutableList.of();
        }
        if (user == null) {
            return ImmutableList.of();
        }

        Team sourceTeam;
        try {
            sourceTeam = user.getTeam();
        } catch (Exception e) {
            return ImmutableList.of();
        }

        return sourceTeam.getPurchases().stream().map((purchase) -> new SimplePurchase(
                purchase.getId(),
                purchase.getTeamName(), purchase.getProduct(), purchase.getQuantity(),
                purchase.getTotalAmount(), purchase.getUser().getUsername(),
                purchase.getSourceTeamCash(),
                purchase.getTimestamp())).collect(Collectors.toList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPurchase(@RequestBody String payload,
                                 @RequestHeader Map<String, String> headers) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(payload);

        User user;
        try {
            user = userService.getUserFromToken(headers);
        } catch (Exception e) {
            return "Bad credentials";
        }
        if (user == null) {
            return "Bad credentials";
        }

        try {
            Product product = productRepository.findByName(jsonNode.get("product").asText());
            if (product.getQuantity() < jsonNode.get("quantity").asLong()) {
                return "We are out of stock on this product";
            }
            if (jsonNode.get("sourceTeamCash").asLong() - jsonNode.get("totalAmount").asLong() < 0) {
                return "You don't have enough cash";
            }
            Purchase purchase = new Purchase(jsonNode.get("teamName").asText(),
                    product,
                    jsonNode.get("quantity").asLong(),
                    jsonNode.get("totalAmount").asLong(),
                    user,
                    jsonNode.get("sourceTeamCash").asLong() - jsonNode.get("totalAmount").asLong(),
                    LocalDateTime.now()
            );
            purchaseRepository.save(purchase);
            Team team = teamRepository.findByName(jsonNode.get("teamName").asText());
            team.setCash(team.getCash() - jsonNode.get("totalAmount").asLong());
            List<Purchase> purchases = team.getPurchases();
            purchases.add(purchase);
            team.setPurchases(purchases);
            teamRepository.save(team);
            product.setQuantity(product.getQuantity() - Math.toIntExact(jsonNode.get("quantity").asLong()));
            productRepository.save(product);
            return "Purchase Successful!";
        } catch (Exception e) {
            return "There was an error processing this purchase, please try again later";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePurchase(@RequestBody String payload,
                                 @RequestHeader Map<String, String> headers) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(payload);

        User user;
        try {
            user = userService.getUserFromToken(headers);
        } catch (Exception e) {
            return "Bad credentials";
        }
        if (user == null) {
            return "Bad credentials";
        }

        try {
            if (!jsonNode.has("timestamp")) {
                return "Values are not set";
            }
            LocalDateTime localDateTime = LocalDateTime.parse(jsonNode.get("timestamp").asText());
            purchaseRepository.delete(purchaseRepository.findByTimestamp(localDateTime));
            return "Deleted successfuly";
        } catch (Exception e) {
            return "There was an error deleting this purchase";
        }
    }

    List<SimplePurchase> createSimplePurchaseList(Iterable<Purchase> transactions) {
        List<SimplePurchase> simpleTransactions = new ArrayList<>();
        transactions.forEach((purchase) -> {
                    simpleTransactions.add(
                            new SimplePurchase(
                                    purchase.getId(),
                                    purchase.getTeamName(),
                                    purchase.getProduct(),
                                    purchase.getQuantity(),
                                    purchase.getTotalAmount(),
                                    purchase.getUser().getUsername(),
                                    purchase.getSourceTeamCash(),
                                    purchase.getTimestamp()
                            )
                    );
                }
        );
        return simpleTransactions;
    }
}
