package com.example.review

data class Transaction(
    val id: String,
    val accountId: String,
    val amountCents: Long,
    val currency: String,
    val description: String,
    val createdAtEpochMillis: Long,
    var synced: Boolean = false,
)

data class User(
    val id: String,
    val email: String,
    val fullName: String,
    val cardNumber: String,
)

data class Session(
    val user: User,
    val accessToken: String,
    val refreshToken: String,
    val expiresAtEpochSeconds: Long,
)

/** Key used to cache one page of transactions for one account. */
class PageKey(val accountId: String, val page: Int) {
    override fun toString(): String = "PageKey($accountId, $page)"
}

class TransferRequest(
    val fromAccount: String,
    val toAccount: String,
    val amountCents: Long,
    val note: String,
)
