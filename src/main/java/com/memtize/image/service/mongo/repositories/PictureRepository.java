package com.memtize.image.service.mongo.repositories;

import com.memtize.image.service.mongo.model.Picture;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface PictureRepository<T extends Picture> extends MongoRepository<T, ObjectId> {

  @Query("{ 'cryptoId' : { '$eq': ?0 } }")
  @Update("{ '$set' : { 'archive' : true } }")
  void deleteByCryptoId(@Param("cryptoId") String cryptoId);

  @Query("{ 'cryptoId' : { '$eq': ?0 }, 'name' : { '$eq': ?1 } }")
  @Update("{ '$set' : { 'archive' : true } }")
  void deleteByCryptoIdAndName(@Param("cryptoId") String cryptoId, @Param("name") String name);

  List<Picture> findAllByCryptoIdAndArchive(
      @Param("cryptoId") String cryptoId, @Param("archive") boolean archive);
}
