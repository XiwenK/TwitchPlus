package com.laioffer.twich.external.model;

import java.util.List;

public record StreamResponse(
        List<Stream> data
) {
}
