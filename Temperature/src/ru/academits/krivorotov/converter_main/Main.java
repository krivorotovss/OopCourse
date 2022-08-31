package ru.academits.krivorotov.converter_main;

import ru.academits.krivorotov.converter.Controller;
import ru.academits.krivorotov.converter.View;

public class Main {
    public static void main(String[] args) {
        View view = new View("Temperature converter");
        Controller controller = new Controller(view);
        controller.initController();
    }
}