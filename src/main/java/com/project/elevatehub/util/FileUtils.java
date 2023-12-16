package com.project.elevatehub.util;

import com.project.elevatehub.constants.FileConstants;
import com.project.elevatehub.exception.DataValidationException;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private Pattern pattern;
    private Matcher matcher;

    private final Tika defaultTika = new Tika();

    public String getFileExtensionFromFileName(String fileName){
        String fileExtension = "";
        try {
            fileExtension = fileName.substring(fileName.lastIndexOf("."));
            if(fileExtension.equals(".jpeg"))
                fileExtension = ".jpg";
        } catch (Exception e) {
            fileExtension = "";
        }
        return fileExtension;
    }

    public void createDirectory(Path fileStorageLocation) {
        try {
            if(!Files.isDirectory(fileStorageLocation))
                Files.createDirectory(fileStorageLocation);
        } catch (Exception e) {
            log.error("Could not create the directory {} where the uploaded files will be stored.", fileStorageLocation.toUri(), e);
            throw new DocumentStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public void deleteFile(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Couldn't delete the file from the given location: {}",filePath.toUri(), e);
            throw new DocumentStorageException("Couldn't delete the file from the given location: " + filePath.toUri(), e);
        }
    }

    /**
     * Identify file type of file with provided name using
     * Tika's default configuration.
     *
     * @param fileName Name of file for which file type is desired.
     * @return Type of file for which file name was provided.
     */
    private String identifyFileType(final String fileName)
    {
        return defaultTika.detect(fileName);
    }

    private void matchImageFileNamePattern(String imageFile) {
        pattern = Pattern.compile(FileConstants.ACCEPTED_IMAGE_PATTERN);
        matcher = pattern.matcher(imageFile);
        if(!matcher.matches())
            throw new DataValidationException("Try again! Only [jpg, png, jpeg] image formats are accepted.");
    }

    public void verifyFileTypeMatch(String imageFile) {
        matchImageFileNamePattern(imageFile);

        String detectedFileType = "";
        switch (identifyFileType(imageFile)) {
            case "image/jpeg":
                detectedFileType = ".jpg";
                break;
            case "image/png":
                detectedFileType = ".png";
                break;
            default:
                detectedFileType = "";
        }
        String fileExtension = getFileExtensionFromFileName(imageFile);

        if(!detectedFileType.equalsIgnoreCase(fileExtension))
            throw new DataValidationException("Try again! Different Image format detected. Only [jpg, png, jpeg] image formats are accepted.");

    }

}
