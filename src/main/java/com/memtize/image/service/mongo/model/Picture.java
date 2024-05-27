package com.memtize.image.service.mongo.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public abstract class Picture implements Zippable, MongoEntity {

  private String cryptoId;

  private byte[] data;

  private String originalFileName;

  private String name;

  private String contentType;

  private long size;

  private LocalDateTime created;

  private boolean archive;

  protected Picture(String cryptoId, MultipartFile file) throws IOException {
    this.cryptoId = cryptoId;
    this.name =
        UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
    this.data = file.getBytes();
    this.originalFileName = file.getOriginalFilename();
    this.contentType = file.getContentType();
    this.size = file.getSize();
    this.created = LocalDateTime.now();
  }

  protected Picture() {}

  public abstract ObjectId getId();
}
