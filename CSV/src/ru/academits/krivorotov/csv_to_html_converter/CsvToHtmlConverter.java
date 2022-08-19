package ru.academits.krivorotov.csv_to_html_converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvToHtmlConverter {
    public void convertAndWriteHtmlFile(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("<table>");

        String line;

        boolean isDataInQuotes = false;

        while ((line = reader.readLine()) != null) {
            if (!isDataInQuotes) {
                writer.println("\t" + "<tr>");
                writer.write("\t\t" + "<td>");
            }

            for (int i = 0; i < line.length(); ++i) {
                char symbol = line.charAt(i);

                if (symbol == '"') {
                    if (!isDataInQuotes) {
                        isDataInQuotes = true;
                    } else {
                        if (i != line.length() - 1 && line.charAt(i + 1) == '"') {
                            writer.write("\"");
                            i++;
                        } else {
                            isDataInQuotes = false;
                        }
                    }
                } else if (symbol == ',') {
                    if (!isDataInQuotes) {
                        writer.println("</td>");
                        writer.write("\t\t" + "<td>");
                    } else {
                        writer.write(symbol);
                    }
                } else if (symbol == '>') {
                    writer.write("&gt;");
                } else if (symbol == '<') {
                    writer.write("&lt;");
                } else if (symbol == '&') {
                    writer.write("&amp;");
                } else {
                    writer.write(symbol);
                }
            }

            if (!isDataInQuotes) {
                writer.println("</td>");
                writer.println("\t" + "</tr>");
            } else {
                writer.write("<br/>");
            }
        }

        writer.println("</table>");
    }
}