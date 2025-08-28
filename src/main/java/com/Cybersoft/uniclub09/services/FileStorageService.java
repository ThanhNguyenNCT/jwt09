package com.Cybersoft.uniclub09.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public void save(MultipartFile file);
    Resource loadFile(String filename);
//    public Resource load(String filename);

//    public void deleteAll();

//    public Stream<Path> loadAll();
}
