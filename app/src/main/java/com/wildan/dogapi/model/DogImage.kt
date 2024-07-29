package com.wildan.dogapi.model

data class DogImage(
    val breeds: List<Breed>,
    val id: String,
    val url: String
)

data class Breed(
    val name: String,
    val bred_for: String?,
    val breed_group: String?,
    val temperament: String?,
    val life_span: String?
)