package com.tei.snapshop.feature_authentication.sign_in.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tei.snapshop.R
import com.tei.snapshop.ui.AuthButtonComposable
import com.tei.snapshop.ui.theme.AppTypography

/**
 * Class Description
 * Created by Eniola Ipoola on 15/05/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun SocialAuthButtons(
    modifier: Modifier,
    onClick: () -> Unit,
    googleButtonClicked: () -> Unit,
    firebaseButtonClicked: () -> Unit,
    proTipText: String?)
{
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = modifier
            .background(colorResource(id = R.color.text_field_container_color))
            .height(1.dp)
            .weight(1f)
        )
        Text(
            text = stringResource(R.string.or),
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxHeight()
                .weight(0.4f),
            style = AppTypography.bodySmall,
            color = colorResource(id = R.color.black)
        )
        Box(modifier = modifier
            .background(colorResource(id = R.color.text_field_container_color))
            .height(1.dp)
            .weight(1f)
        )
    }

    Spacer(modifier.height(5.dp))
    AuthButtonComposable(
        modifier,
        buttonText = stringResource(id = R.string.continue_with_firebase),
        onButtonClick = {},
        image = painterResource(id = R.drawable.firebase_logo)
    )

    Spacer(modifier.height(15.dp))

    AuthButtonComposable(
        modifier,
        buttonText = stringResource(id = R.string.continue_with_google),
        onButtonClick = {},
        image = painterResource(id = R.drawable.google_logo)
    )



    proTipText?.let {
        Text(
        text = it,
        textAlign = TextAlign.Start,
        modifier = modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        style = AppTypography.bodySmall,
        color = colorResource(id = R.color.text_color_light))
    }
}

@Preview
@Composable
fun PreviewFooter() {
    ChangeAuthModeText(
        modifier = Modifier,
        onClick = { /*TODO*/ },
        questionString = "Sign Up" ,
        actionString = "" ,
        textStyle = TextStyle(textAlign = TextAlign.Start)
    )
}

@Composable
fun ChangeAuthModeText(
    modifier: Modifier,
    onClick: () -> Unit,
    questionString: String,
    actionString: String?,
    textStyle: TextStyle
) {
    val context = LocalContext.current

    val annotatedText = buildAnnotatedString {
        append(questionString)
        pushStringAnnotation(
            tag = stringResource(R.string.question),
            annotation = stringResource(R.string.question)
        )
        withStyle(
            style = SpanStyle(
                color = colorResource(id = R.color.color_primary)
            )
        ) {
            append(actionString)
        }
        pop()
    }
    ClickableText(
        text = annotatedText,
        style = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = context.getString(R.string.question),
                start = offset,
                end = offset
            ).firstOrNull()?.tag?.let {
                onClick()

            }
        }
    )

}
