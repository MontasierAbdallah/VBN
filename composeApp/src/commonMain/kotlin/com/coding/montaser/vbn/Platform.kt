package com.coding.montaser.vbn

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform