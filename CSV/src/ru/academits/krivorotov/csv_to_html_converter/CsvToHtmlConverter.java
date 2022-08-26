package ru.academits.krivorotov.csv_to_html_converter;

import java.io.*;

public class CsvToHtmlConverter {
    public void convertAndWriteHtmlFile(String pathToInputFile, String pathToOutputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToInputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(pathToOutputFile))) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang=\"ru\">");
            writer.println("\t" + "<head>");
            writer.println("\t\t" + "<meta charset=\"UTF-8\" />");
            writer.println("\t\t" + "<title>Страница</title>");
            writer.println("\t" + "<head>");
            writer.println("\t" + "<body>");
            writer.println("\t\t" + "<table>");

            String line;

            boolean isDataInQuotes = false;

            while ((line = reader.readLine()) != null) {
                if (!isDataInQuotes) {
                    if (line.length() == 0) {
                        continue;
                    }

                    writer.println("\t\t\t" + "<tr>");
                    writer.write("\t\t\t\t" + "<td>");
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
                            writer.write("\t\t\t\t" + "<td>");
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
                    writer.println("\t\t\t" + "</tr>");
                } else {
                    writer.write("<br/>");
                }
            }

            writer.println("\t\t" + "</table>");
            writer.println("\t" + "</body>");
            writer.print("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }
}