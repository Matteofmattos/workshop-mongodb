package com.matteof_mattos.workshopMongo.models.dtos;

import com.matteof_mattos.workshopMongo.models.embededs.Author;
import com.matteof_mattos.workshopMongo.models.embededs.Comment;

import java.time.Instant;
import java.util.List;

public record PostDTO(

         String id,

         String title,

         String body,

         Instant moment,

         Author author,

         List<Comment> comments) {
}
