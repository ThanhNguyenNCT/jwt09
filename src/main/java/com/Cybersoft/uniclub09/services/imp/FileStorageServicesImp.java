package com.Cybersoft.uniclub09.services.imp;

import com.Cybersoft.uniclub09.services.FileStorageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;

@Service
public class FileStorageServicesImp implements FileStorageService {
    @Value("${file.root-path}")
    private  String rootPath;

    @Override
    public void save(MultipartFile file) {
        Path root = Paths.get(rootPath);
        try {
            if(!Files.exists(root)) {
                Files.createDirectories(root);
            }
            Files.copy(file.getInputStream(), root.resolve(Objects.requireNonNull(file.getOriginalFilename())),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource loadFile(String filename) {

        try {
            Path root = Paths.get(rootPath);
            Path file = root.resolve(filename);
            Resource resource = new     UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
