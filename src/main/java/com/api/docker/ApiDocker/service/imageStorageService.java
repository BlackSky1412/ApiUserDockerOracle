package com.api.docker.ApiDocker.service;


import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class imageStorageService implements IStorageService {
    private final Path storageFoder = Paths.get("uploads");

    public imageStorageService() {
        try {
            Files.createDirectories(storageFoder);
        } catch (IOException exception) {
            throw new RuntimeException("Cannot initialize storage", exception);
        }
    }

    private boolean isImageFile(MultipartFile file) {
        //let check file image
        String fileExtention = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"})
                .contains(fileExtention.trim().toLowerCase());
    }

    @Override
    public String storageFile(MultipartFile file) {
        try {
            System.out.println("storageFile");
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            if (!isImageFile((file))) {
                throw new RuntimeException("You can only upload file images");
            }
            float fileSizeInMegabyte = file.getSize() / 1_000_000.0F;
            if (fileSizeInMegabyte > 5.0f) {
                throw new RuntimeException("File must be <= 5MB");
            }
            String fileExtention = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatoFileName = UUID.randomUUID().toString().replace("-", "");
            generatoFileName = generatoFileName + "." + fileExtention;
            Path destinationFilePath = this.storageFoder.resolve(
                    Paths.get(generatoFileName)
            ).normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.storageFoder.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outsite current directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatoFileName;
        } catch (IOException exception) {
            throw new RuntimeException("Failed to store file. ", exception);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try{
            return Files.walk(this.storageFoder, 1)
                    .filter(path -> !path.equals(this.storageFoder) && !path.toString().contains("._"))
                    .map(this.storageFoder::relativize);
        }catch (IOException exception)
        {
            throw new RuntimeException("Failed to load stored files", exception);
        }
    }

    @Override
    public byte[] readFileContent(String fileName) {
        try{
            Path file = storageFoder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable())
            {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }
            else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        }
        catch (IOException exception)
        {
            throw new RuntimeException("Could not read file: " + fileName, exception);
        }
    }

    @Override
    public void deleteAllFiles() {

    }
}
