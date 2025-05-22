package com.example.bankbranchesapp

import android.view.WindowInsets
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect


@Composable
fun BranchListScreen(
    onBranchClick: (Branch) -> Unit,
    favoriteBranchId: Int?,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val displayedBranches by BranchRepository.branchList

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        SearchBar(
            searchText = searchText,
            onTextChange = {
                searchText = it
                BranchRepository.searchBranches(it)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { BranchRepository.sortBranchesByName() }) {
                Text("Sort by Name")
            }
            Button(onClick = { BranchRepository.filterBranchesBy24Hours() }) {
                Text("Filter 24/7")
            }
            Button(onClick = {
                searchText = "" //clear search text
                BranchRepository.resetBranches() //show all branches again
            }) {
                Text("Reset")
            }
        }



        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //takes the current list of branches and displays each one using branchCard
            items(displayedBranches) { branch ->
                BranchCard(branch = branch, isFavorite = branch.id == favoriteBranchId, onClick = { onBranchClick(branch) })
            }
        }
    }
}

@Composable
fun SearchBar(
    searchText: String,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onTextChange,
        placeholder = { Text("Search branches...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        singleLine = true
    )
}



//@Composable
//fun SearchBar(
//    searchText: String,
//    onTextChange: (String) -> Unit
//) {
//    OutlinedTextField(
//        value = searchText,
//        onValueChange = onTextChange,
//        placeholder = { Text("Search branches...") },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        singleLine = true
//    )
//}


