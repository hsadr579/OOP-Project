package com.example.oopproject.core;

public enum Outputs {
    // ##################### user creating #####################
    SUCCESS_CREATE_USER(
            "User created successfully. Please choose a security question :\n\t• 1-What is your father’s name ?\n\t• 2-What is your favourite color ?\n\t• 3-What is the name of your school ?"),
    ERROR_EMPTY_FIELD("Some fields are empty!"),
    ERROR_DUPLICATE_USERNAME("This username has already been chosen!"),
    ERROR_INVALID_USERNAME("Your username is invalid!"),
    ERROR_PASSWORDS_NOT_SAME("The passwords are not the same!"),
            ERROR_ANSWERS_NOT_SAME("The answers are not the same!"),
    ERROR_INVALID_PASSWORD_1(
            "Your password is weak!(it should have at least 8 characters and it should have at least one lowercase and one uppercase letter and one character other than numbers and letters)"),
    ERROR_INVALID_PASSWORD_2(
            "Your password is weak!(It should have at least one lowercase and one uppercase letter and one character other than numbers and letters)"),
    ERROR_INVALID_EMAIL("Please enter the correct email!"),

    // ##################### security question #####################
    SUCCESS_PICK_QUESTION("The question and its answer were successfully received!"),
    // ##################### login #####################
    SUCCESS_LOGIN("user logged in successfully!"),
    ERROR_NO_USERNAME("Username doesn’t exist!"),
    ERROR_WRONG_PASSWORD("Password and Username don’t match!"),
    SUCCESS_LOG_OUT("user logged out successfully!"),
    // ##################### profile menu #####################
    PROFILE_MENU("You are on profile menu."),
    SUCCESS_CHANGE_USERNAME("Your username changed successfully!"),
    SUCCESS_CHANGE_NICKNAME("Your nickname changed successfully!"),
    SUCCESS_CHANGE_PASSWORD("Your password changed successfully!"),
    ERROR_SAME_PASSWORD("Please enter a new password!"),
    SUCCESS_CHANGE_EMAIL("Your email changed successfully!"),
    // ##################### main menu #####################
    MAIN_MENU("You are on main menu."),
    SUCCESS_START_GAME("Select a mode:\n\t1. Multiplayer\n\t2. Bet\n\t3. Single player\n\t4. Clan war"),
    SUCCESS_GO_TO_SHOP("You are in shop menu!"),
    SUCCESS_GO_TO_PROFILE("You are in profile menu!"),
    // ##################### shop menu #####################

    SUCCESS_BUY_CARD("Card buy successfully!"),
    SUCCESS_UPGRADE_CARD("Card upgrade successfully!"),
    LEVEL_NOT_ENOUGH("Your level is not enough to upgrade this card."),
    NOT_ENOUGH_COIN_BUY("You don't have enough coin to buy this card"),
    NOT_ENOUGH_COIN_UPGRADE("You don't have enough coin to upgrade this card"),
    // ##################### history #####################

    // ##################### admin menu #####################

    SUCCESS_LOGIN_ADMIN("Admin logged in!"),
    SUCCESS_ADD_CARD("Card added successfully!"),
    SUCCESS_EDIT_CARD_FIELD("Card edited successfully!"),
    SUCCESS_REMOVE_CARD("Card remove successfully!"),
    // ##################### mode menu #####################
    // ##################### multiplayer####################
    ERROR_GAP("You cannot do this because of the gap!"),
    ERROR_NOT_EMPTY("This place is full!"),
    OPPONENT_WIN("The opponent is the winner.\nThe host defeated!"),
    HOST_WIN("The host is the winner!"), TRY_AGAIN("Try again!")
    , NOT_ENOUGH_COIN_BET("Not enough coin!"),LOGIN_PLAYER2("Please login guest");
    

    private final String pattern;

    Outputs(String pattern) {
        this.pattern = pattern;
    }

    public String get() {
        return pattern;
    }

}
