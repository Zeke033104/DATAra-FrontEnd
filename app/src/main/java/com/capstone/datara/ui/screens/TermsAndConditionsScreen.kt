package com.capstone.datara.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.theme.DangerRed
import com.capstone.datara.ui.theme.PrimaryGreen

@Composable
fun TermsAndConditionsScreen(
    onAcceptClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "DATAra Terms and Conditions",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Last Updated: May 06, 2026",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to DATAra. By accessing or using the DATAra mobile application (\"App\"), you agree to be bound by these Terms and Conditions. If you do not agree, please do not use the App.",
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("1. Overview of the Service")
            SectionBody("DATAra is a mobile application designed to help users monitor and predict mobile data consumption using machine learning. The App analyzes aggregated usage patterns to provide insights and predictions regarding data usage.")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("2. Data Collection and Usage")
            SectionBody("DATAra collects only the following types of data:\n• Mobile data usage (e.g., total data consumed)\n• Timestamp information (e.g., when data is used)\n• Phone number (for OTP verification)\n\nDATAra does not collect or monitor specific applications used on your device. All data collected is aggregated and used solely for analysis and prediction purposes.")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("3. Machine Learning and Predictions")
            SectionBody("DATAra uses machine learning models to estimate and predict user data consumption.\n\nThese models:\n• Operate primarily on-device\n• May utilize the user local data to enhance globally trained model for improved accuracy\n\nPredictions are estimation only and may not always be accurate. DATAra does not guarantee the correctness of predictions.")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("4. User Accounts and Security")
            SectionBody("Users are required to register using their phone number. This information is used for:\n• Account verification\n• Security purposes\n\nUsers are responsible for maintaining the confidentiality of their account information.")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("5. Data Storage and User Control")
            SectionBody("• User data is primarily stored locally on the device\n• Users have the right to delete their account and associated data at any time\n\nUsers has the option to share their data usage with DATAra. The app will always ask for permission before anything is sent. Only usage data is shared—users phone number and name are never included. This is:\n• Optional\n• Done only with explicit user consent")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("6. Privacy Commitment")
            SectionBody("DATAra respects user privacy. Specifically:\n• The App does not track specific app usage\n• The App does not access personal content or files\n• Only aggregated data usage is analyzed")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("7. Free Service Disclaimer")
            SectionBody("DATAra is currently provided as a free application for helping User's budget and save their Mobile data. Features and availability may change without notice.")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("8. Limitation of Liability")
            SectionBody("DATAra is provided \"as is\" without warranties of any kind. The developers are not liable for:\n• Inaccurate predictions\n• Data loss\n• Any damages arising from use of the App")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("9. Geographic Use")
            SectionBody("DATAra is currently intended for users in Cagayan De oro City Phillipines. Usage outside this region may not be fully supported.")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("10. Changes to Terms")
            SectionBody("These Terms and Conditions may be updated at any time with or without notice. Continued use of the App after changes constitutes acceptance of the updated terms.")

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("11. Contact")
            SectionBody("For questions or concerns regarding these Terms, please contact the development team.\nBy using DATAra, you acknowledge that you have read and understood these Terms and Conditions.")

            Spacer(modifier = Modifier.height(32.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onCancelClick,
                colors = ButtonDefaults.buttonColors(containerColor = DangerRed),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Cancel", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = onAcceptClick,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text(
                    text = "Accept Terms\nand Conditions",
                    fontSize = 11.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun SectionBody(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSurface,
        lineHeight = 20.sp
    )
}
