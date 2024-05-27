package com.memtize.image.service.mongo.model;

import com.memtize.image.service.service.I18N;
import org.bson.types.ObjectId;

public interface MongoEntity {

  ObjectId getId();

  void setId(ObjectId id);

  default String getEntityName() {
    return I18N.getEntityName(this.getClass());
  }
}
