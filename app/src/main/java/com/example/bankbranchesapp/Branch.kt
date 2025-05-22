package com.example.bankbranchesapp

//I used enum  because branch types are fixed values that don’t change and helps avoid mistakes like typos
enum class BranchType(val displayName: String) {
    MAIN("Main"),
    ATM("ATM"),
    SERVICE("Service"),
    OTHER("Other")
}
data class Branch(
    val id: Int,
    val name: String,
    val type: BranchType,
    val address: String,
    val phone: String,
    val hours: String,
    val location: String,
    val imageUri: Int? = null

)