package com.app.bank_misr.presentation.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.app.bank_misr.R
import com.app.bank_misr.presentation.theme.Black
import com.app.bank_misr.presentation.theme.CelestialBlue
import com.app.bank_misr.presentation.theme.Cerulean
import com.intuit.sdp.R.dimen._10sdp
import com.intuit.sdp.R.dimen._15sdp
import com.intuit.sdp.R.dimen._1sdp
import com.intuit.sdp.R.dimen._20sdp
import com.intuit.sdp.R.dimen._40sdp
import com.intuit.sdp.R.dimen._4sdp
import com.intuit.sdp.R.dimen._50sdp
import com.intuit.ssp.R.dimen._14ssp

@Composable
fun Loading(
  indicatorSize: Dp = dimensionResource(id = _50sdp),
  animationDuration: Int = 400
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    ProgressIndicator(indicatorSize, animationDuration)
  }
}

@Composable
fun LoadingDialog(
  cornerRadius: Dp = dimensionResource(id = _10sdp),
  paddingVertical: Dp = dimensionResource(id = _20sdp),
  paddingHorizontal: Dp = dimensionResource(id = _40sdp),
) {

  Dialog(
    onDismissRequest = {}
  ) {

    Surface(
      shadowElevation = dimensionResource(id = _4sdp),
      shape = RoundedCornerShape(cornerRadius)
    ) {

      Column(
        modifier = Modifier
          .wrapContentSize()
          .padding(
            vertical = paddingVertical,
            horizontal = paddingHorizontal
          ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {

        ProgressIndicator(indicatorSize = dimensionResource(id = _50sdp))
      }
    }
  }
}

@Composable
fun ProgressIndicator(
  indicatorSize: Dp = dimensionResource(id = _50sdp),
  animationDuration: Int = 400
) {

  val circleColors = listOf(
    CelestialBlue,
    Cerulean
  )

  val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")

  val rotateAnimation by infiniteTransition.animateFloat(
    label = "FloatAnimation",
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(
      animation = tween(
        durationMillis = animationDuration,
        easing = LinearEasing
      )
    )
  )

  CircularProgressIndicator(
    modifier = Modifier
      .size(size = indicatorSize)
      .rotate(degrees = rotateAnimation)
      .border(
        width = dimensionResource(id = _4sdp),
        brush = Brush.sweepGradient(circleColors),
        shape = CircleShape
      ),
    progress = 1f,
    strokeWidth = dimensionResource(id = _1sdp),
    color = MaterialTheme.colorScheme.background
  )
}