package com.api.v1.domain.repositories;

import com.api.v1.domain.entities.Book;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, ObjectId> {

}
