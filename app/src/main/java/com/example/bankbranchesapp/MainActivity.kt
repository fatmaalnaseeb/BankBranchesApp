package com.example.bankbranchesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bankbranchesapp.ui.theme.BankBranchesAppTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            BankBranchesAppTheme {
//                // Move 'remember' inside the setContent composable block
//                val selectedBranchState = remember { mutableStateOf<Branch?>(null) }
//                val selectedBranch = selectedBranchState.value
//                val favoriteId = BranchRepository.favoriteBranchId.value
//
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    if (selectedBranch == null) {
//                        BranchListScreen(
//                            onBranchClick = { branch -> selectedBranchState.value = branch },
//                            favoriteBranchId = favoriteId,
//                            modifier = Modifier.padding(innerPadding)
//                        )
//                    } else {
//                        BranchDetailsScreen(
//                            branch = selectedBranch,
//                            isFavorite = selectedBranch.id == favoriteId,
//                            onSetFavorite = { id -> BranchRepository.favoriteBranchId.value = id },
//                            onBack = { selectedBranchState.value = null },
//                            modifier = Modifier.padding(innerPadding)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankBranchesAppTheme {
                val selectedBranchState = remember { mutableStateOf<Branch?>(null) } //holds currently selected branch or null if none selected
                val selectedBranch = selectedBranchState.value //reads the current selected branch
                val favoriteId = BranchRepository.favoriteBranchId.value //get sthe id or the user fav branch or null

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //if no branch is selected show the branch list

                    if (selectedBranch == null) {
                        BranchListScreen(
                            onBranchClick = { branch -> selectedBranchState.value = branch }, //updated selected branch when clicked
                            favoriteBranchId = favoriteId, //pass current fav branch id
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    } else {
                        //if a branch is selected show the detail screen
                        BranchDetailsScreen(
                            branch = selectedBranch, //show detail for the branch
                            isFavorite = selectedBranch.id == favoriteId, //check iof its the favorite
                            onSetFavorite = { id -> BranchRepository.favoriteBranchId.value = id }, //when button is clicked save it as a favorite
                            onBack = { selectedBranchState.value = null }, //back to list when back button is clicked
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
