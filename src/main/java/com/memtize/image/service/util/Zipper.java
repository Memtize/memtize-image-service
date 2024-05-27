package com.memtize.image.service.util;

import com.memtize.image.service.mongo.model.Picture;
import com.memtize.image.service.mongo.model.Zippable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class Zipper {

  private Zipper() {}

  public static ResponseEntity<Resource> zipPictures(List<Picture> pictures, String fileName)
      throws IOException {
    return zip(pictures.stream().map(Zippable.class::cast).collect(Collectors.toList()), fileName);
  }

  public static ResponseEntity<Resource> zip(List<Zippable> zippables, String fileName)
      throws IOException {
    byte[] zipData = packFilesToZip(zippables);
    ByteArrayResource resource = new ByteArrayResource(zipData);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, fileName + ".zip");
    headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");

    return ResponseEntity.ok().headers(headers).contentLength(zipData.length).body(resource);
  }

  private static byte[] packFilesToZip(List<Zippable> zippables) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ZipOutputStream zipOutputStream = new ZipOutputStream(baos)) {
      zippables.forEach(
          p -> {
            try {
              addFileToZip(zipOutputStream, p);
            } catch (IOException e) {
              throw new ApplicationException(ErrorType.INTERNAL_ERROR, e, e.getMessage());
            }
          });
    }
    return baos.toByteArray();
  }

  private static void addFileToZip(ZipOutputStream zipOutputStream, Zippable zippable)
      throws IOException {
    if (zippable != null) {
      try (ByteArrayInputStream inputStream = new ByteArrayInputStream(zippable.getData())) {
        ZipEntry zipEntry = new ZipEntry(zippable.getName());
        zipOutputStream.putNextEntry(zipEntry);
        IOUtils.copy(inputStream, zipOutputStream);
        zipOutputStream.closeEntry();
      }
    }
  }
}
