package com.laioffer.twich;

import com.laioffer.twich.db.FavoriteRecordRepository;
import com.laioffer.twich.db.ItemRepository;
import com.laioffer.twich.db.entity.FavoriteRecordEntity;
import com.laioffer.twich.db.entity.ItemEntity;
import com.laioffer.twich.db.entity.UserEntity;
import com.laioffer.twich.favorite.FavoriteService;
import com.laioffer.twich.model.ItemType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTests {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private FavoriteRecordRepository favoriteRecordRepository;

    @Captor
    ArgumentCaptor<FavoriteRecordEntity> favoriteRecordArgumentCaptor;

    private FavoriteService favoriteService;

    @BeforeEach
    public void setup() {
        favoriteService = new FavoriteService(itemRepository, favoriteRecordRepository);
    }

    @Test
    public void whenItemNotExist_setFavoriteItem_shouldSaveItem() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        ItemEntity item = new ItemEntity(null, "twitchId", "title", "url", "thumb"
                                    , "broadcaster", "gameid", ItemType.VIDEO);
        ItemEntity persisted = new ItemEntity(1L, "twitchId", "title", "url", "thumb"
                                    , "broadcaster", "gameid", ItemType.VIDEO);

        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(null);
        Mockito.when(itemRepository.save(item)).thenReturn(persisted);

        favoriteService.setFavoriteItem(user, item);

        Mockito.verify(itemRepository).save(item);
    }

    @Test
    public void setFavoriteItem_shouldCreateFavoriteRecord() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        ItemEntity item = new ItemEntity(null, "twitchId", "title", "url", "thumb"
                , "broadcaster", "gameid", ItemType.VIDEO);
        ItemEntity persisted = new ItemEntity(1L, "twitchId", "title", "url", "thumb"
                , "broadcaster", "gameid", ItemType.VIDEO);

        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(persisted);

        favoriteService.setFavoriteItem(user, item);

        Mockito.verify(favoriteRecordRepository).save(favoriteRecordArgumentCaptor.capture());
        FavoriteRecordEntity favorite = favoriteRecordArgumentCaptor.getValue();
        Assertions.assertEquals(1L, favorite.itemId());
        Assertions.assertEquals(1L, favorite.userId());
    }
}
