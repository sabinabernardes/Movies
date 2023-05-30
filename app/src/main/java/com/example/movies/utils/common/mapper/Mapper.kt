package com.example.movies.utils.common.mapper

interface Mapper<S, T> {
    fun map(source: S): T
}