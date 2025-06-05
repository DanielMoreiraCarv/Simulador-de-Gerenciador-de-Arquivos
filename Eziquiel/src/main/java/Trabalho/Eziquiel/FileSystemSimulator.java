package Trabalho.Eziquiel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextArea;

import Trabalho.Eziquiel.Classes.Directory;
import Trabalho.Eziquiel.Classes.Files;
import Trabalho.Eziquiel.Classes.Journal;

public class FileSystemSimulator {
    private Directory root;
    private Directory currentDirectory;
    private Journal journal;
    private static final String BASE_FILE = "filesystem_base.ser";

    public FileSystemSimulator() {
        root = loadFromBase();
        if (root == null) {
            root = new Directory("root");
        }
        currentDirectory = root;
        journal = new Journal();
        applyJournal();
    }

    public void criarFile(String fileName) {
        currentDirectory.getFiles().add(new Files(fileName));
        journal.addEntry("CREATE_FILE:" + getCurrentPath() + "/" + fileName);
    }
    
    public void deletarFile(String nome) {
    	Files delFile = currentDirectory.getFileByName(nome);
    	if(delFile!=null) {
    		currentDirectory.getFiles().remove(delFile);
    	}else {
    		System.out.println("Arquivo não encontrado no diretório \"+ currentDirectory");
    	}
    }

    public void criarDirectory(String dirName) {
        Directory newDir = new Directory(dirName);
        currentDirectory.addSubdirectory(newDir);
        journal.addEntry("CREATE_DIR:" + getCurrentPath() + "/" + dirName);
    }
    
    public void deletarDirectory(String nome) {
    	Directory delDir = currentDirectory.getSubdirectoryByName(nome);
    	if(delDir!=null) {
    		currentDirectory.getSubDirectory().remove(delDir);
    	}else {
    		System.out.println("Sub-Diretório não encontrado no diretório "+ currentDirectory);
    	}
    }

    public void mudarDirectory(String dirName) {
        if (dirName.equals("..")) {
            if (currentDirectory != root && currentDirectory.getParent() != null) {
                currentDirectory = currentDirectory.getParent();
            }
        } else {
            Directory next = currentDirectory.getSubdirectoryByName(dirName);
            if (next != null) {
                currentDirectory = next;
            } else {
                System.out.println("Diretório não encontrado.");
            }
        }
    }

    public void lstDirectoryContents() {
        System.out.println("Arquivos em " + getCurrentPath() + ":");
        for (Files file : currentDirectory.getFiles()) {
            System.out.println("  [ARQ] " + file.getNome());
        }
        for (Directory dir : currentDirectory.getSubDirectory()) {
            System.out.println("  [DIR] " + dir.getName());
        }
    }
    
    public void lstDirectoryContentsToGUI(JTextArea output) {
        output.append("Arquivos em " + getCurrentPath() + ":\n");
        for (Files file : currentDirectory.getFiles()) {
            output.append("  [ARQ] " + file.getNome() + "\n");
        }
        for (Directory dir : currentDirectory.getSubDirectory()) {
            output.append("  [DIR] " + dir.getName() + "\n");
        }
    }


    public String getCurrentPath() {
        StringBuilder path = new StringBuilder();
        Directory dir = currentDirectory;
        while (dir != null) {
            path.insert(0, "/" + dir.getName());
            dir = dir.getParent();
        }
        return path.toString();
    }

    public void resetSystem() {
        root = new Directory("root");
        currentDirectory = root;
        journal.clearJournal();
        File baseFile = new File(BASE_FILE);
        if (baseFile.exists()) {
            baseFile.delete();
        }
        System.out.println("Sistema de arquivos resetado com sucesso.");
    }

    public void finalizeAndSave() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(BASE_FILE))) {
            out.writeObject(root);
            journal.clearJournal();
            System.out.println("Sistema salvo com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar sistema: " + e.getMessage());
        }
    }

    private Directory loadFromBase() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(BASE_FILE))) {
            return (Directory) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private void applyJournal() {
        for (String entry : journal.readEntries()) {
            if (entry.startsWith("CREATE_FILE:")) {
                createPath(entry.substring(12), true);
            } else if (entry.startsWith("CREATE_DIR:")) {
                createPath(entry.substring(11), false);
            }
        }
    }

    private void createPath(String fullPath, boolean isFile) {
        String[] parts = fullPath.split("/");
        Directory dir = root;
        for (int i = 1; i < parts.length - 1; i++) {
            Directory next = dir.getSubdirectoryByName(parts[i]);
            if (next == null) {
                next = new Directory(parts[i]);
                dir.addSubdirectory(next);
            }
            dir = next;
        }
        if (isFile) {
            dir.getFiles().add(new Files(parts[parts.length - 1]));
        } else {
            dir.addSubdirectory(new Directory(parts[parts.length - 1]));
        }
    }
    
    public void renameFile(String nomeAntigo, String nomeNovo) {
    	Files file = currentDirectory.getFileByName(nomeAntigo);
    	if(file!=null) {
    		file.setNome(nomeNovo);
    	}else {
    		System.out.println("Arquivo não encontrado no diretório "+currentDirectory.getName());
    	}
    }
    
    public void renameSubDirectory(String nomeAntigo, String nomeNovo) {
    	Directory diretorio = currentDirectory.getSubdirectoryByName(nomeAntigo);
    	if(diretorio!=null) {
    		diretorio.setName(nomeNovo);
    	}else {
    		System.out.println("Sub-Diretório "+nomeAntigo + " não encontrado no diretório "+currentDirectory.getName());
    	}
    }
}
