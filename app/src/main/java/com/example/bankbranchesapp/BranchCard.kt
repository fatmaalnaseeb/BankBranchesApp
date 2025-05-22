package com.example.bankbranchesapp



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


// this ui function displays a bank branch's image, name, type, and phone number
//if the branch is marked as a favorite it shows a heart emoji
//i used padding, spacing, rounded corners, and elevation to make the card look nice
@Composable
fun BranchCard(
    branch: Branch,
    isFavorite: Boolean = false,
    onClick: () -> Unit
) {

    //if the branch is a favorite change the card color to light blue
    val cardColor = if (isFavorite) Color(0xFFE3F2FD) else MaterialTheme.colorScheme.surface

    Card(
        modifier = Modifier
            .padding(vertical = 6.dp) // Adds space between cards vertically
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = cardColor), //background color
        shape = RoundedCornerShape(12.dp), //rounds the card corners
        elevation = CardDefaults.cardElevation(6.dp)  //adds shadow
    ) {
        Column {
            //display the branch image if it's null show a default image
            val imageRes = branch.imageUri ?: R.drawable.default_branch
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Branch Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f) // widescreen ratio for nicer display
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    //name of the branch
                    Text(
                        text = branch.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    //emoji if this is a favorite branch
                    if (isFavorite) {
                        Text("❤️", style = MaterialTheme.typography.titleMedium)
                    }
                }
                Spacer(modifier = Modifier.height(6.dp)) //space between elements
                // branch type
                Text("Type: ${branch.type.displayName}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(2.dp))
                //branch phone number
                Text("Phone: ${branch.phone}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
