package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class CommentDtoDomainMapper @Inject constructor() : Mapper<CommentDto, Comment> {
    override fun from(i: CommentDto?): Comment {
        return Comment(
            id = i?.id.orEmpty(),
            movieId = i?.movieId.orEmpty(),
            message = i?.message.orEmpty(),
            createdAt = i?.createdAt.orEmpty()
        )
    }

    override fun to(o: Comment?): CommentDto {
        return CommentDto(
            id = o?.id.orEmpty(),
            movieId = o?.movieId.orEmpty(),
            message = o?.message.orEmpty(),
            createdAt = o?.createdAt.orEmpty()
        )
    }
}