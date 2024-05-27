package com.memtize.image.service.api.controllers;

import com.memtize.image.service.api.interfaces.PicturesApi;
import com.memtize.image.service.mongo.model.MainPicture;
import com.memtize.image.service.mongo.model.Picture;
import com.memtize.image.service.service.PicturesService;
import com.memtize.image.service.util.ApplicationException;
import com.memtize.image.service.util.ErrorType;
import com.memtize.image.service.util.Zipper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/crypto/{id}/pictures")
public class PicturesController implements PicturesApi {

  private final PicturesService picturesService;

  @Override
  @PostMapping(path = "/main", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> addMainPicture(
      @PathVariable String id, @RequestPart(name = "file") MultipartFile file) throws IOException {
    picturesService.addMainPicture(new MainPicture(id, file));
    return ResponseEntity.noContent().build();
  }

  @Override
  @GetMapping(path = "/main", produces = "application/zip")
  public ResponseEntity<Resource> getMainPicture(
      @PathVariable String id, @RequestParam(defaultValue = "false") Boolean archive)
      throws IOException {
    List<Picture> pictures =
        picturesService.getMainPicture(id, Boolean.TRUE.equals(archive));

    return Zipper.zipPictures(pictures, "ProfilePictures" + id);
  }

  @Override
  @DeleteMapping(path = "/main")
  public ResponseEntity<Void> deleteMainPicture(@PathVariable String id) {
    picturesService.deleteMainPicture(id);
    return ResponseEntity.noContent().build();
  }
}
