package com.example.bankbranchesapp

import androidx.compose.runtime.mutableStateOf


object BranchRepository {
    val branches = listOf(
        Branch(
            id = 1,
            name = "Kuwait City Branch",
            type = BranchType.MAIN,
            address = "Block 1, Kuwait City",
            phone = "+965 1800 111",
            hours = "8 AM - 3 PM",
            location = "https://maps.google.com/?q=Kuwait+City",
            imageUri =R.drawable.branch1
        ),
        Branch(
            id = 2,
            name = "Alshuhada ATM Branch",
            type = BranchType.ATM,
            address = "Alshuhada Street 5",
            phone = "+965 1800 222",
            hours = "24/7",
            location = "https://www.google.com/maps?q=Alshuhada+Street+Kuwait",
            imageUri = null
        ),
        Branch(
            id = 1,
            name = "Adailiya Branch",
            type = BranchType.SERVICE,
            address = "Block 3, Adailiya Street, Kuwait City",
            phone = "+965 2245 1234",
            hours = "Sun-Thu 8:30 AM - 3:00 PM",
            location = "https://maps.google.com/?q=Adailiya+Kuwait",
            imageUri = null
        ),
        Branch(
            id = 11,
            name = "AlYarmouk Business Center",
            type = BranchType.OTHER,
            address = "Block 4, Al Yarmouk Street, Kuwait City",
            phone = "+965 2246 3344",
            hours = "Sun-Thu 9:00 AM - 5:00 PM",
            location = "https://maps.google.com/?q=Al+Yarmouk+Kuwait",
            imageUri = null
        )


    )
    //sorts the branches alphabetically by name
    fun sortBranchesByName() {
        branchList.value = branchList.value.sortedBy { it.name }
    }
    //filters branches that are open 24 hours
    fun filterBranchesBy24Hours() {
        branchList.value = branches.filter { it.hours.contains("24", ignoreCase = true) }
    }
    //resets the list back to all branches
    fun resetBranches() {
        branchList.value = branches
    }
    //search
    fun searchBranches(query: String): List<Branch> {
        if (query.isBlank()) return branches //if no input return all branches
        return branches.filter { it.name.contains(query, ignoreCase = true) }
    }
    var branchList = mutableStateOf(branches)
    var favoriteBranchId = mutableStateOf<Int?>(null)
}
