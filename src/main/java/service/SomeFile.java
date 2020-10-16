package service;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SomeFile extends File {

    private String lastModify;
    private Long size;

    public SomeFile(Path path){
        super(path.toString());
        lastModify = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(path.toFile().lastModified());
        size = path.toFile().length();
    }

    public String getLastModify(){return lastModify;}

    public Long getSize(){return size;}

    public List<SomeFile> getFiles(Path path){
        List<SomeFile> someFiles = new ArrayList<SomeFile>();
        for(File file : path.toFile().listFiles()){
            SomeFile someFile = new SomeFile(file.toPath());
            someFiles.add(someFile);
        }
        return someFiles;
    }
}
