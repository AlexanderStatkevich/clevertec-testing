package com.statkevich.receipttask.view;

import java.io.PrintWriter;

public class ConsolePrinter implements Printer {
    @Override
    public void print(String receipt) {
        PrintWriter writer = new PrintWriter(System.out);
        writer.print(receipt);
        writer.flush();
    }
}
