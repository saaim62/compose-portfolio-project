package org.portfolio.project

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import io.ktor.server.http.content.*
import com.fasterxml.jackson.databind.SerializationFeature

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        // Root endpoint
        get("/") {
            call.respond(ItemStorage.getAll())
        }

        // CRUD Endpoints for Item
        route("/items") {
            // Get all items
            get {
                call.respond(ItemStorage.getAll())
            }

            // Get item by ID
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid or missing id")
                    return@get
                }
                val item = ItemStorage.getById(id)
                if (item == null) {
                    call.respond(HttpStatusCode.NotFound, "Item not found")
                } else {
                    call.respond(item)
                }
            }

            // Create a new item
            post {
                val item = call.receive<Item>()
                val createdItem = ItemStorage.add(item)
                call.respond(HttpStatusCode.Created, createdItem)
            }

            // Update an existing item
            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid or missing id")
                    return@put
                }
                val updatedItem = call.receive<Item>()
                val success = ItemStorage.update(id, updatedItem)
                if (success) {
                    call.respond(HttpStatusCode.OK, updatedItem.copy(id = id))
                } else {
                    call.respond(HttpStatusCode.NotFound, "Item not found")
                }
            }

            // Delete an item
            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid or missing id")
                    return@delete
                }
                val success = ItemStorage.delete(id)
                if (success) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Item not found")
                }
            }
        }

        static("/swagger-ui") {
            resources("swagger-ui")
            defaultResource("swagger-ui.html", "swagger-ui")
        }

        get("/openapi.json") {
            val openApi = generateOpenApi()
            call.respondText(openApi, ContentType.Application.Json)
        }
    }
}

fun generateOpenApi(): String {
    return """
    {
      "openapi": "3.0.1",
      "info": {
        "title": "Item API",
        "version": "1.0",
        "description": "API for managing items"
      },
      "paths": {
        "/items": {
          "get": {
            "summary": "Get all items",
            "responses": {
              "200": {
                "description": "A list of items",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "object",
                        "properties": {
                          "id": { "type": "integer" },
                          "name": { "type": "string" },
                          "description": { "type": "string" }
                        },
                        "required": ["name", "description"]
                      }
                    }
                  }
                }
              }
            }
          },
          "post": {
            "summary": "Create a new item",
            "requestBody": {
              "required": true,
              "content": {
                "application/json": {
                  "schema": {
                    "type": "object",
                    "properties": {
                      "name": { "type": "string" },
                      "description": { "type": "string" }
                    },
                    "required": ["name", "description"]
                  }
                }
              }
            },
            "responses": {
              "201": {
                "description": "Item created",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "object",
                      "properties": {
                        "id": { "type": "integer" },
                        "name": { "type": "string" },
                        "description": { "type": "string" }
                      },
                      "required": ["id", "name", "description"]
                    }
                  }
                }
              }
            }
          }
        },
        "/items/{id}": {
          "get": {
            "summary": "Get an item by ID",
            "parameters": [
              {
                "name": "id",
                "in": "path",
                "required": true,
                "schema": { "type": "integer" }
              }
            ],
            "responses": {
              "200": {
                "description": "Item details",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "object",
                      "properties": {
                        "id": { "type": "integer" },
                        "name": { "type": "string" },
                        "description": { "type": "string" }
                      },
                      "required": ["id", "name", "description"]
                    }
                  }
                }
              },
              "404": { "description": "Item not found" }
            }
          },
          "put": {
            "summary": "Update an existing item",
            "parameters": [
              {
                "name": "id",
                "in": "path",
                "required": true,
                "schema": { "type": "integer" }
              }
            ],
            "requestBody": {
              "required": true,
              "content": {
                "application/json": {
                  "schema": {
                    "type": "object",
                    "properties": {
                      "name": { "type": "string" },
                      "description": { "type": "string" }
                    },
                    "required": ["name", "description"]
                  }
                }
              }
            },
            "responses": {
              "200": {
                "description": "Item updated",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "object",
                      "properties": {
                        "id": { "type": "integer" },
                        "name": { "type": "string" },
                        "description": { "type": "string" }
                      },
                      "required": ["id", "name", "description"]
                    }
                  }
                }
              },
              "404": { "description": "Item not found" }
            }
          },
          "delete": {
            "summary": "Delete an item",
            "parameters": [
              {
                "name": "id",
                "in": "path",
                "required": true,
                "schema": { "type": "integer" }
              }
            ],
            "responses": {
              "204": { "description": "Item deleted" },
              "404": { "description": "Item not found" }
            }
          }
        }
      }
    }
    """.trimIndent()
}

class Greeting {
    fun greet(): String = "Welcome to the Ktor Application!"
}
