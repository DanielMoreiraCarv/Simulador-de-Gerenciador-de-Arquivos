package Trabalho.Eziquiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1663542630777450389L;
    private JTextArea consoleOutput;
    private JTextField commandInput;
    private FileSystemSimulator fs;

    public MainGUI() {
        super("Simulador de Sistema de Arquivos");
        fs = new FileSystemSimulator();

        
        consoleOutput = new JTextArea(20, 50);
        consoleOutput.setEditable(false);
        consoleOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(consoleOutput);

        
        commandInput = new JTextField();
        commandInput.addActionListener(new CommandHandler());

        JLabel promptLabel = new JLabel(" > ");
        promptLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(promptLabel, BorderLayout.WEST);
        inputPanel.add(commandInput, BorderLayout.CENTER);

        
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        printWelcomeMessage();
        commandInput.requestFocusInWindow();
    }

    private void printToConsole(String text) {
        consoleOutput.append(text + "\n");
    }

    private void printWelcomeMessage() {
        printToConsole("Simulador de Sistema de Arquivos iniciado.");
        printToConsole("Comandos disponíveis:");
        printToConsole("  criar arquivo <nome>");
        printToConsole("  criar diretorio <nome>");
        printToConsole("  renomear arquivo <antigo> <novo>");
        printToConsole("  renomear diretorio <antigo> <novo>");
        printToConsole("  cd <nome>/..");
        printToConsole("  listar");
        printToConsole("  salvar");
        printToConsole("  reset");
        printToConsole("  sair");
    }

    private class CommandHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String comando = commandInput.getText().trim();
            commandInput.setText("");
            printToConsole(fs.getCurrentPath() + " > " + comando);

            if (comando.equals("sair")) {
                fs.finalizeAndSave();
                printToConsole("Sistema encerrado com sucesso.");
                System.exit(0);

            } else if (comando.startsWith("criar arquivo ")) {
                fs.criarFile(comando.substring(14).trim());

            } else if (comando.startsWith("criar diretorio ")) {
                fs.criarDirectory(comando.substring(16).trim());

            } else if (comando.startsWith("cd ")) {
                fs.mudarDirectory(comando.substring(3).trim());

            } else if (comando.equals("listar")) {
                fs.lstDirectoryContentsToGUI(consoleOutput);

            } else if (comando.equals("reset")) {
                fs.resetSystem();

            } else if (comando.equals("salvar")) {
                fs.finalizeAndSave();

            } else if (comando.startsWith("renomear arquivo ")) {
                String[] partes = comando.substring(18).trim().split(" ");
                if (partes.length == 2) {
                    fs.renameFile(partes[0], partes[1]);
                } else {
                    printToConsole("Uso: renomear arquivo <antigo> <novo>");
                }

            } else if (comando.startsWith("renomear diretorio ")) {
                String[] partes = comando.substring(20).trim().split(" ");
                if (partes.length == 2) {
                    fs.renameSubDirectory(partes[0], partes[1]);
                } else {
                    printToConsole("Uso: renomear diretorio <antigo> <novo>");
                }

            } else {
                printToConsole("Comando inválido. Use os comandos listados acima.");
            }

            commandInput.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
