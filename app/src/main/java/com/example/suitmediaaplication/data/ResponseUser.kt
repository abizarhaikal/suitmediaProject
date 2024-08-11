package com.example.suitmediaaplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Model untuk data respons API
data class ResponseUser(
	@field:SerializedName("per_page")
	val perPage: Int,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("support")
	val support: Support
)

// Model untuk item pengguna, yang juga menjadi tabel di Room Database
@Entity(tableName = "list_user")
data class DataItem(
	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("avatar")
	val avatar: String,

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("email")
	val email: String
)

// Model untuk informasi dukungan di respons API
data class Support(
	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("url")
	val url: String
)
