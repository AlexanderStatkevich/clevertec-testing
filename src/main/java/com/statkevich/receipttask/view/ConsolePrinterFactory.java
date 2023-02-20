package com.statkevich.receipttask.view;

public class ConsolePrinterFactory implements PrinterFactory{

    @Override
    public Printer createPrinter() {
        return new ConsolePrinter();
    }
}
