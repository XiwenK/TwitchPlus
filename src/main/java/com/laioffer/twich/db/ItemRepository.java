package com.laioffer.twich.db;

import com.laioffer.twich.db.entity.ItemEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ItemRepository extends ListCrudRepository<ItemEntity, Long> {

    ItemEntity findByTwitchId(String twitchId);
}
