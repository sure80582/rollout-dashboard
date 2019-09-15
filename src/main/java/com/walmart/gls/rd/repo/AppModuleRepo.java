package com.walmart.gls.rd.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.walmart.gls.rd.entity.AppModule;

@Repository
public interface AppModuleRepo extends MongoRepository <AppModule, String> {

}
