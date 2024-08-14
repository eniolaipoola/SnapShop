package com.tei.snapshop.feature_settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tei.snapshop.R
import com.tei.snapshop.ui.theme.AppTypography

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */
@Composable
fun SettingScreen(
    paddingValues: PaddingValues,
    modifier: Modifier,
    backHandler: () -> Unit
) {

    Surface(
        color = Color.White,
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Column(
            modifier = modifier
                .fillMaxSize().verticalScroll(rememberScrollState()),
        ) {
            // Top back button and product details text
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back_button),
                    contentDescription = "Back",
                    modifier = modifier
                        .size(24.dp)
                        .clickable { backHandler() }
                )
                Spacer(modifier = modifier.width(16.dp))
                Text(
                    text = "My Account",
                    fontWeight = FontWeight.Bold,
                    style = AppTypography.titleMedium,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = stringResource(R.string.general),
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodyMedium,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = modifier.height(16.dp))

            AccountOption(
                iconId = R.drawable.icon_user,
                title = stringResource(R.string.account_details),
                subtitle = stringResource(R.string.edit_your_account_info),
                onClick = {

                }, modifier)
            AccountOption(
                iconId = R.drawable.ic_payment_card,
                title = stringResource(R.string.payment_method),
                subtitle = stringResource(R.string.add_your_credit_or_debit_card),
                onClick = {

                }, modifier)
            AccountOption(
                iconId = R.drawable.ic_location,
                title = stringResource(R.string.delivery_addresses),
                subtitle = stringResource(R.string.edit_address),
                onClick = {

                }, modifier)
            AccountOption(
                iconId = R.drawable.ic_security,
                title = stringResource(R.string.security_password),
                subtitle = stringResource(R.string.edit_password),
                onClick = {

                }, modifier)

            Spacer(modifier = Modifier.height(32.dp))

            // Settings Section
            Text(
                text = stringResource(R.string.setting),
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodyMedium,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Start

            )
            Spacer(modifier = Modifier.height(16.dp))


            AccountOption(
                iconId = R.drawable.ic_notification,
                title = stringResource(R.string.notifications),
                subtitle = stringResource(R.string.manage_notifications),
                onClick = {

                }, modifier)
            AccountOption(
                iconId = R.drawable.ic_global,
                title = stringResource(R.string.language),
                subtitle = stringResource(R.string.change_your_language), onClick = {

                }, modifier)
            AccountOption(
                iconId = R.drawable.ic_privacy,
                title = stringResource(R.string.privacy_policy),
                subtitle = stringResource(R.string.read_privacy_policies),  onClick = {

                }, modifier)
            AccountOption(
                iconId = R.drawable.ic_call,
                title = stringResource(R.string.contact_us),
                subtitle = stringResource(R.string.contact_us_info),  onClick = {

                }, modifier)

            Spacer(modifier = Modifier.height(16.dp))

            // Log Out Button
            Text(
                text = stringResource(R.string.log_out),
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle log out action */ }
                    .padding(vertical = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

        }
    }
}

@Composable
fun AccountOption(
    iconId: Int,
    title: String,
    subtitle: String,
    onClick: ()  -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable { onClick() },
            //verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = title,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    style = AppTypography.bodyMedium,
                    color = colorResource(id = R.color.black))
                Text(text = subtitle,
                    color = colorResource(id = R.color.neutral_500),
                    style = AppTypography.bodySmall,
                    fontSize = 14.sp)

            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_forward_arrow),
                contentDescription = stringResource(R.string.arrow_forward),
                modifier = Modifier.size(24.dp
                )
            )
        }

        HorizontalDivider(color = colorResource(id = R.color.neutral_300), thickness = 1.dp)

    }
    
}

@Composable
@Preview
fun PreviewSettings() {
    SettingScreen(paddingValues = PaddingValues(4.dp), modifier = Modifier, backHandler = {

    })
}
