package Trabalho.Eziquiel.Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Journal {
    private static final String JOURNAL_FILE = "journal.txt";

    public void addEntry(String entry) {
        try (FileWriter fw = new FileWriter(JOURNAL_FILE, true)) {
            fw.write(entry + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no journal: " + e.getMessage());
        }
    }

    public List<String> readEntries() {
        List<String> entries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(JOURNAL_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
        } catch (IOException e) {
            System.out.println("");
        }
        return entries;
    }

    public void clearJournal() {
        try (PrintWriter pw = new PrintWriter(JOURNAL_FILE)) {
            pw.print("");
        } catch (IOException e) {
            System.out.println("Erro ao limpar o journal: " + e.getMessage());
        }
    }
}
