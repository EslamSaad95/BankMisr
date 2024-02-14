package com.app.bank_misr.presentation.common

import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 13/08/2023
 */
@Composable
fun SpannableText(
  text: String,
  spanText: String,
  modifier: Modifier = Modifier,
  spanStyle: SpanStyle = SpanStyle(color = Color.Blue),
  color: Color = Color.Unspecified,
  fontSize: TextUnit = TextUnit.Unspecified,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  lineHeight: TextUnit = TextUnit.Unspecified,
  overflow: TextOverflow = TextOverflow.Clip,
  softWrap: Boolean = true,
  maxLines: Int = Int.MAX_VALUE,
  inlineContent: Map<String, InlineTextContent> = mapOf(),
  onTextLayout: (TextLayoutResult) -> Unit = {},
  style: TextStyle = LocalTextStyle.current,
) {

  val annotatedString = buildSpannable(text, spanText, spanStyle)

  Text(
    text = annotatedString,
    modifier = modifier,
    color = color,
    fontSize = fontSize,
    fontStyle = fontStyle,
    fontWeight = fontWeight,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
    lineHeight = lineHeight,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    inlineContent = inlineContent,
    onTextLayout = onTextLayout,
    style = style
  )
}

@Composable
fun SpannableText(
  text: String,
  spanText: String,
  modifier: Modifier = Modifier,
  spanStyle: SpanStyle = SpanStyle(textDecoration = TextDecoration.Underline),
  style: TextStyle = LocalTextStyle.current,
  overflow: TextOverflow = TextOverflow.Clip,
  softWrap: Boolean = true,
  maxLines: Int = Int.MAX_VALUE,
  onTextLayout: (TextLayoutResult) -> Unit = {},
  onClick: (String) -> Unit,
) {

  val annotatedString = buildSpannable(text, spanText, spanStyle)

  ClickableText(
    text = annotatedString,
    modifier = modifier,
    style = style,
    softWrap = softWrap,
    overflow = overflow,
    maxLines = maxLines,
    onTextLayout = onTextLayout,
    onClick = { offset ->
      annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.let { span ->
        onClick.invoke(span.item)
      }
    }
  )
}

private fun buildSpannable(
  text: String,
  spanText: String,
  spanStyle: SpanStyle
) = buildAnnotatedString {
  append("$text ")
  withStyle(style = spanStyle) {
    pushStringAnnotation(tag = spanText, annotation = spanText)
    append(spanText)
  }
}