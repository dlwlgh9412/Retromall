package com.retro.retromall.hashtag.domain.repository

import com.retro.retromall.hashtag.domain.HashTag
import org.springframework.data.jpa.repository.JpaRepository

interface HashTagRepository : JpaRepository<HashTag, Long> {
    fun findAllByNameIn(hashtags: Set<String>): Set<HashTag>
}