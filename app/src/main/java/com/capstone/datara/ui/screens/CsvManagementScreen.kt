package com.capstone.datara.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.components.EmptyState
import com.capstone.datara.ui.components.PrimaryButton
import com.capstone.datara.ui.theme.DarkBackground
import com.capstone.datara.ui.theme.DarkSurface
import com.capstone.datara.ui.theme.PrimaryBlue
import com.capstone.datara.ui.theme.PrimaryGreen

// Mock CSV file data model
data class CsvFile(
    val name: String,
    val size: String,
    val date: String
)

@Composable
fun CsvManagementScreen(
    onBackClick: () -> Unit,
    onExportComplete: () -> Unit = {},
    onImportComplete: () -> Unit = {},
    onDeleteComplete: () -> Unit = {}
) {

    // Mock list of CSV files — starts with sample data
    var csvFiles by remember {
        mutableStateOf(
            listOf(
                CsvFile("usage_log_july_2025.csv", "1.2 MB", "Jul 10, 2025"),
                CsvFile("usage_log_june_2025.csv", "980 KB", "Jun 30, 2025"),
                CsvFile("usage_log_may_2025.csv", "1.1 MB", "May 31, 2025")
            )
        )
    }

    // Loading state for button simulation
    var isDownloading by remember { mutableStateOf(false) }
    var isUploading by remember { mutableStateOf(false) }

    // Delete confirmation dialog state
    var fileToDelete by remember { mutableStateOf<CsvFile?>(null) }

    // Simulate download: adds a new entry after 1.5s
    LaunchedEffect(isDownloading) {
        if (isDownloading) {
            kotlinx.coroutines.delay(1500)
            val newFile = CsvFile(
                "usage_log_today_${csvFiles.size + 1}.csv",
                "430 KB",
                "Jul 14, 2025"
            )
            csvFiles = listOf(newFile) + csvFiles
            isDownloading = false
            onExportComplete()
        }
    }

    // Simulate upload: briefly shows loading then stops
    LaunchedEffect(isUploading) {
        if (isUploading) {
            kotlinx.coroutines.delay(1500)
            isUploading = false
            onImportComplete()
        }
    }

    // Delete Confirmation Dialog
    if (fileToDelete != null) {
        AlertDialog(
            onDismissRequest = { fileToDelete = null },
            containerColor = Color(0xFF1E2233),
            title = {
                Text(
                    "Delete File",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            text = {
                Text(
                    "Are you sure you want to delete \"${fileToDelete!!.name}\"? This cannot be undone.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        csvFiles = csvFiles.filter { it != fileToDelete }
                        fileToDelete = null
                        onDeleteComplete()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Delete", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { fileToDelete = null },
                    shape = RoundedCornerShape(20.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF3A3F55))
                ) {
                    Text("Cancel", color = Color.Gray)
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // ── Header ──────────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF13171F))
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    "CSV Management",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )
                Text(
                    "Import and export data usage logs",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        // ── Action Buttons ──────────────────────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Download button
                Button(
                    onClick = { if (!isDownloading && !isUploading) isDownloading = true },
                    enabled = !isDownloading && !isUploading,
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryGreen,
                        disabledContainerColor = PrimaryGreen.copy(alpha = 0.6f)
                    )
                ) {
                    if (isDownloading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Exporting...", color = Color.White, fontSize = 13.sp)
                    } else {
                        Text("⬇  Export CSV", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }

                // Upload button
                Button(
                    onClick = { if (!isDownloading && !isUploading) isUploading = true },
                    enabled = !isDownloading && !isUploading,
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlue,
                        disabledContainerColor = PrimaryBlue.copy(alpha = 0.6f)
                    )
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Importing...", color = Color.White, fontSize = 13.sp)
                    } else {
                        Text("⬆  Import CSV", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }

        Divider(color = Color(0xFF2A2F45), thickness = 1.dp)

        // ── File List ──────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Saved Files",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                "${csvFiles.size} file${if (csvFiles.size != 1) "s" else ""}",
                color = Color.Gray,
                fontSize = 13.sp
            )
        }

        if (csvFiles.isEmpty()) {
            EmptyState(
                icon = Icons.Default.List,
                title = "No CSV Files Yet",
                subtitle = "Export your data logs or import an existing CSV file to get started.",
                iconTint = Color(0xFF3A3F55)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                itemsIndexed(csvFiles) { _, file ->
                    CsvFileRow(
                        file = file,
                        onDeleteClick = { fileToDelete = file }
                    )
                }
            }
        }
    }
}

@Composable
private fun CsvFileRow(
    file: CsvFile,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // File icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(PrimaryGreen.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("CSV", color = PrimaryGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(14.dp))

            // File info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = file.name,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${file.size}  ·  ${file.date}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Delete icon
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete ${file.name}",
                    tint = Color(0xFFFF4444),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
