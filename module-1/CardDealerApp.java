package com.example.cards;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import java.util.Collections;
import javafx.geometry.Pos;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Name: Nazir Knuckles
 * Date: September 13, 2026
 * Assignment: JavaFX Random Card Application
 * Purpose: Display four randomly selected cards from a 52-card deck and refresh with a new selection.
 *
 * AI assistance disclosure: This source was created with assistance from ChatGPT. The solution was
 * reviewed and organized for the assignment, including the use of a Lambda expression for the refresh button.
 * The card artwork in the cards directory was generated specifically for this project.
 */
public class CardDealerApp extends Application {

    private static final int DECK_SIZE = 52;
    private static final int CARDS_TO_DISPLAY = 4;
    private static final double CARD_WIDTH = 150;
    private static final double CARD_HEIGHT = 210;

    private final HBox cardContainer = new HBox(15);
    private Set<Integer> previousSelection = new HashSet<>();

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Random Four Cards");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        cardContainer.setAlignment(Pos.CENTER);
        cardContainer.setPadding(new Insets(10));

        Button refreshButton = new Button("Refresh");
        refreshButton.setPrefWidth(110);
        // Lambda expression: refresh the displayed cards when the button is clicked.
        refreshButton.setOnAction(event -> displayRandomCards());

        VBox root = new VBox(15, titleLabel, cardContainer, refreshButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 720, 330);
        primaryStage.setTitle("CSD430 JavaFX Card Dealer");
        primaryStage.setScene(scene);
        primaryStage.show();

        displayRandomCards();
    }

    /**
     * Randomly selects four unique cards. A refresh repeats the shuffle until the new set differs
     * from the immediately previous set.
     */
    private void displayRandomCards() {
        List<Integer> deck = new ArrayList<>();
        for (int cardNumber = 1; cardNumber <= DECK_SIZE; cardNumber++) {
            deck.add(cardNumber);
        }

        Set<Integer> newSelection;
        do {
            Collections.shuffle(deck);
            newSelection = new HashSet<>(deck.subList(0, CARDS_TO_DISPLAY));
        } while (!previousSelection.isEmpty() && newSelection.equals(previousSelection));

        previousSelection = newSelection;
        cardContainer.getChildren().clear();

        for (Integer cardNumber : deck.subList(0, CARDS_TO_DISPLAY)) {
            ImageView cardView = createCardImageView(cardNumber);
            cardContainer.getChildren().add(cardView);
        }
    }

    /**
     * Loads a card image from the resources/cards directory and sizes it for display.
     */
    private ImageView createCardImageView(int cardNumber) {
        String resourcePath = "/cards/" + cardNumber + ".png";
        InputStream cardStream = getClass().getResourceAsStream(resourcePath);

        if (cardStream == null) {
            throw new IllegalStateException("Card image not found: " + resourcePath);
        }

        Image cardImage = new Image(cardStream);
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitWidth(CARD_WIDTH);
        cardView.setFitHeight(CARD_HEIGHT);
        cardView.setPreserveRatio(true);
        return cardView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}