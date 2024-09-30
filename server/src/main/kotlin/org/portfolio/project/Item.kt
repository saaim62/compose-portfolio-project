package org.portfolio.project


import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data class representing an Item.")
data class Item(
    @Schema(description = "Unique identifier of the item.", example = "1")
    val id: Int,

    @Schema(description = "Name of the item.", example = "Sample Item")
    val name: String,

    @Schema(description = "Description of the item.", example = "This is a sample item.")
    val description: String
)
