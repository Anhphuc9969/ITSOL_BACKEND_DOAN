package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.Image;
import com.itsol.recruit_managerment.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public interface ImageService {
    public void saveImage(Image image);
    Optional<Image> findByName(String name);
    public  void saveUser(User users);

    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
