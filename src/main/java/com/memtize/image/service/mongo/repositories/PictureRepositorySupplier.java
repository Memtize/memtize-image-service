package com.memtize.image.service.mongo.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class PictureRepositorySupplier {

  private final MainPictureRepository mainPictureRepository;

}
