package Trabalho.Eziquiel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystemSimulator fs = new FileSystemSimulator();
        Scanner scanner = new Scanner(System.in);
        String comando;

        System.out.println("Simulador de Sistema de Arquivos iniciado.");
        System.out.println("Digite comandos ou 'sair' para encerrar.");

        while (true) {
            System.out.print(fs.getCurrentPath() + " > ");
            comando = scanner.nextLine();

            if (comando.equals("sair")) {
                fs.finalizeAndSave();
                break;

            } else if (comando.startsWith("criar arquivo ")) {
                fs.criarFile(comando.substring(14).trim());

            } else if (comando.startsWith("criar diretorio ")) {
                fs.criarDirectory(comando.substring(16).trim());

            } else if (comando.startsWith("apagar arquivo ")) {
                fs.deletarFile(comando.substring(15).trim());

            } else if (comando.startsWith("apagar diretorio ")) {
                fs.deletarDirectory(comando.substring(17).trim());

            } else if (comando.startsWith("renomear arquivo ")) {
                String[] partes = comando.substring(18).trim().split(" ");
                if (partes.length == 2) {
                    fs.renameFile(partes[0], partes[1]);
                } else {
                    System.out.println("Uso: renomear arquivo <nome_antigo> <novo_nome>");
                }

            } else if (comando.startsWith("renomear diretorio ")) {
                String[] partes = comando.substring(19).trim().split(" ");
                if (partes.length == 2) {
                    fs.renameSubDirectory(partes[0], partes[1]);
                } else {
                    System.out.println("Uso: renomear diretorio <nome_antigo> <novo_nome>");
                }

            } else if (comando.startsWith("cd ")) {
                fs.mudarDirectory(comando.substring(3).trim());

            } else if (comando.equals("listar")) {
                fs.lstDirectoryContents();

            } else if (comando.equals("reset")) {
                fs.resetSystem();

            } else if (comando.equals("salvar")) {
                fs.finalizeAndSave();

            } else if (comando.equals("clear")) {
                System.out.print("\033[H\033[2J"); 
                System.out.flush();

            } else if (comando.equals("help")) {
                System.out.println("Comandos disponíveis: "
                        + "\n criar arquivo <nome>               - Criar um novo arquivo"
                        + "\n criar diretorio <nome>             - Criar um novo diretório"
                        + "\n apagar arquivo <nome>              - Apagar um arquivo"
                        + "\n apagar diretorio <nome>            - Apagar um diretório"
                        + "\n renomear arquivo <antigo> <novo>   - Renomear um arquivo"
                        + "\n renomear diretorio <antigo> <novo> - Renomear um diretório"
                        + "\n cd <nome>/..                       - Navegar entre diretórios"
                        + "\n listar                             - Listar conteúdo do diretório atual"
                        + "\n salvar                             - Salvar alterações no sistema"
                        + "\n reset                              - Zerar sistema de arquivos"
                        + "\n clear                              - Limpar o console"
                        + "\n sair                               - Salvar e sair do simulador"
                        + "\n help                               - Mostrar esta ajuda");

            } else {
                System.out.println("Comando inválido. Digite 'help' para ver os comandos.");
            }
        }

        scanner.close();
        System.out.println("Sistema encerrado com sucesso.");
    }
}
