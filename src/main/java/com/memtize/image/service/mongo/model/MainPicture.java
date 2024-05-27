package com.memtize.image.service.mongo.model;

import java.io.IOException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(of = "id", callSuper = false)
@Document(collection = "MAIN_PICTURE")
@Data
public class MainPicture extends Picture {

  @Id private ObjectId id;

  public MainPicture(String cryptoId, MultipartFile file) throws IOException {
    super(cryptoId, file);
  }

  public MainPicture() {}
}
