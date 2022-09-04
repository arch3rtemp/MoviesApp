package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class CommentEntityDomainMapper @Inject constructor() : Mapper<CommentEntity, Comment> {
    override fun from(i: CommentEntity?): Comment {
        return Comment(
            id = i?.id.orEmpty(),
            movieId = i?.movieId.orEmpty(),
            message = i?.message.orEmpty(),
            createdAt = i?.createdAt.orEmpty()
        )
    }

    override fun to(o: Comment?): CommentEntity {
        return CommentEntity(
            id = o?.id.orEmpty(),
            movieId = o?.movieId.orEmpty(),
            message = o?.message.orEmpty(),
            createdAt = o?.createdAt.orEmpty()
        )
    }
}