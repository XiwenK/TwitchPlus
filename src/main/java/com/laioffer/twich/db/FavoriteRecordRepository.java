package com.laioffer.twich.db;

import com.laioffer.twich.db.entity.FavoriteRecordEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import javax.xml.crypto.Data;
import java.util.List;

public interface FavoriteRecordRepository extends ListCrudRepository<FavoriteRecordEntity, Long> {

    List<FavoriteRecordEntity> findAllByUserId(Long userId);

    @Query("SELECT item_id FROM favorite_records WHERE user_id = :userId")
    List<Long> findFavoriteItemIdsByUserId(Long userId);

    // When you use this annotation, you are telling Spring Data JPA that
    // the method is not just a simple read operation, but that it will
    // also update, insert, or delete data from the database. So the query
    // above should not be annotated with @Modifying
    @Modifying
    @Query("DELETE FROM favorite_records where user_id = :userId AND item_id = :itemId")
    void delete(Long userId, Long itemId);
}
