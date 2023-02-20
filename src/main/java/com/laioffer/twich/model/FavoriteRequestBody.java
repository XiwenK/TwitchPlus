package com.laioffer.twich.model;

import com.laioffer.twich.db.entity.ItemEntity;

public record FavoriteRequestBody(
        ItemEntity favorite
) {
}
