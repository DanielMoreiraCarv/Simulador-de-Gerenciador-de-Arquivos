package Trabalho.Eziquiel.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Directory implements Serializable{
	
	private static final long serialVersionUID = -6696756157905932939L;
	private String name;
	private List<Files> files;
	private List<Directory> subDirectory;
	private Directory parent;
	
	

	public Directory getParent() {
		return parent;
	}

	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}
	
	public void setName(String directory) {
		this.name = directory;
	}

	public Directory(List<Files> files) {
		super();
		this.files = files;
	}

	public List<Directory> getSubDirectory() {
		return subDirectory;
	}

	public void setSubDirectory(List<Directory> subDirectory) {
		this.subDirectory = subDirectory;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Diretório "+name + " com os seguintes arquivos :");
		for(int parametro = 0; parametro < files.size() ; parametro++) {
			sb.append("\n"+files.get(parametro).getNome());
		}
		return sb.toString();
	}
	
	public Directory(String name, List<Files> files, List<Directory> subDirectory, Directory parent) {
		super();
		this.name = name;
		this.parent = parent;
		this.files = files;
		this.subDirectory = subDirectory;
	}

	public Directory(String name) {
		super();
		this.name = name;
		this.parent = null;
		this.files = new ArrayList<>();
		this.subDirectory = new ArrayList<>();
		
	}

    public Files getFileByName(String fileName) {
        for (Files file : files) {
            if (file.getNome().equals(fileName)) {
            	return file;
            }
        }
        return null;
    }

    public Directory getSubdirectoryByName(String dirName) {
        for (Directory dir : subDirectory) {
            if (dir.getName().equals(dirName)) {
            	return dir;
            }
        }
        return null;
    }
    
    public void addSubdirectory(Directory dir) {
        dir.setParent(this);
        subDirectory.add(dir);      
    }
	
	

}
