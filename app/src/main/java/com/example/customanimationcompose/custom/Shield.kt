package com.example.customanimationcompose.custom

import android.graphics.Typeface
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customanimationcompose.ui.theme.AppTheme

private val fillPath =
    PathParser().parsePathString("M41.35,71.472C76.586,68.497 128.82,47.918 150.533,38C200.162,63.414 252.272,70.232 259.716,71.472C262.818,222.096 167.283,281.602 150.533,286.561C36.387,232.014 41.35,75.191 41.35,71.472Z")
        .toPath()
private val strokePath =
    PathParser().parsePathString(
        "M255.865,69.266C221.852,63.51 189.016,53.829 157.488,39.57C155.264,38.523 153.17,38 150.946,38C148.853,38 146.629,38.523 144.405,39.57C113.008,53.829 80.172,63.51 46.158,69.266C41.841,70.051 40.01,71.621 40.01,76.462C39.748,112.699 44.719,148.152 57.801,182.035C74.808,226.645 102.673,262.359 144.798,286.168C147.022,287.477 149.115,288 151.077,288C153.04,288 155.133,287.346 157.357,286.168C199.481,262.359 227.215,226.645 244.353,182.035C257.304,148.021 262.276,112.699 262.145,76.462C262.014,71.621 260.313,70.051 255.865,69.266ZM254.688,102.103C252.726,132.061 246.577,161.103 234.672,188.706C218.581,225.86 194.248,256.08 159.45,277.665C156.31,279.627 153.694,280.543 150.946,280.543C148.199,280.543 145.583,279.627 142.443,277.665C107.775,256.08 83.312,225.991 67.221,188.706C55.447,161.103 49.298,132.192 47.336,102.103C46.813,94.907 46.289,87.843 46.158,80.517C46.158,77.116 47.205,75.546 50.868,74.892C84.358,68.612 116.802,59.193 147.807,45.064C148.984,44.541 150.031,44.41 151.077,44.541C152.124,44.541 153.04,44.672 154.217,45.195C185.222,59.324 217.796,68.874 251.156,75.022C254.688,75.677 255.865,77.116 255.865,80.648C255.735,87.843 255.211,94.907 254.688,102.103Z"
    ).toPath()
private val inside1Path =
    PathParser().parsePathString("M245.923,95.823C245.269,101.71 244.353,111.26 243.437,120.81C243.176,124.211 241.475,125.781 237.943,125.781C227.477,125.912 217.142,124.604 206.807,122.38C204.06,121.726 202.752,120.548 203.013,117.408C204.322,104.85 205.368,92.291 204.06,79.601C203.406,73.845 205.761,72.929 210.994,74.368C220.805,77.377 230.748,79.601 240.821,81.433C246.185,82.479 246.185,82.741 245.923,95.823Z")
        .toPath()
private val inside2Path =
    PathParser().parsePathString("M103.589,210.945C137.472,210.029 166.383,199.433 185.745,169.606C190.847,161.756 194.772,153.253 197.519,144.226C198.696,140.171 200.659,138.601 205.106,139.909C214.002,142.395 223.029,143.049 232.317,143.31C236.504,143.441 238.858,145.011 237.55,149.851C225.253,197.994 201.313,238.287 159.057,266.283C153.432,270.076 149.115,270.207 143.228,266.413C123.474,253.331 107.514,236.586 94.039,217.355C92.992,215.786 90.899,214.216 91.815,212.253C92.731,210.029 95.216,211.207 97.048,211.076C99.141,210.814 101.365,210.945 103.589,210.945Z")
        .toPath()

@Composable
fun Shield(
    modifier: Modifier = Modifier,
    strokeColor: Color = AppTheme.colors.customViewColors.strokeColor,
    shieldBackground: Color = AppTheme.colors.customViewColors.shieldBackgroundColor,
    insideShieldColor: Color = AppTheme.colors.customViewColors.scanColor,
    scanColor: Int = AppTheme.colors.customViewColors.scanColor.toArgb(),
    repeatDuration: Int,
    progress: Float
) {

    val infiniteTransition = rememberInfiniteTransition()
    val animValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(repeatDuration, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val boundStrokePath = strokePath.getBounds()

    Canvas(modifier = modifier) {
        val offset = Offset(center.x, center.y)
        val scaleRatio = if (size.width < size.height) {
            size.width / boundStrokePath.width
        } else {
            size.height / boundStrokePath.height
        } * 0.7f

        withTransform(transformBlock = {
            val matrix = Matrix().apply {
                scale(scaleRatio, scaleRatio)
            }

            transform(matrix)
            translate(
                offset.x / scaleRatio - boundStrokePath.width / 2 - boundStrokePath.left,
                offset.y / scaleRatio - boundStrokePath.height / 2 - boundStrokePath.top
            )
        }) {
            drawPath(fillPath, color = shieldBackground)
            drawPath(strokePath, color = strokeColor)
            drawPath(inside1Path, color = insideShieldColor)
            drawPath(inside2Path, color = insideShieldColor)

            drawScanScale(animValue, Color(scanColor))

            drawPercent(progress, boundStrokePath)

            clipPath(fillPath) {
                drawWaveScan(
                    animValue,
                    fillPath.getBounds().inflate(fillPath.getBounds().width),
                    scanColor
                )
            }
        }
    }
}

private fun DrawScope.drawWaveScan(endAngle: Float, rect: Rect, scanColor: Int) {
    val value = endAngle * 360f

    var startAngle = if (value < 180) {
        value / 3f
    } else {
        convertValue(value, 180f, 360f, 60f, 360f)
    }
    val sweepDegree = value - startAngle
    startAngle -= 90

    drawArc(
        Color(scanColor),
        startAngle,
        sweepDegree,
        true,
        size = rect.size,
        topLeft = rect.topLeft
    )
}

private fun DrawScope.drawScanScale(value: Float, color: Color) {
    val scalePath = Path()

    scalePath.reset()
    scalePath.addPath(strokePath)

    val strokeWidth = 8.dp.value * (1 - value)
    val alpha = 1f - value

    drawPath(scalePath, style = Stroke(strokeWidth), alpha = alpha, color = color)
}

private fun DrawScope.drawPercent(progress: Float, rect: Rect) {
    val textPercent = "${(progress * 100).toInt()}%"
    val result = android.graphics.Rect()
    val paint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = 35.sp.value
        color = Color.White.toArgb()
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        measureText(textPercent)

        getTextBounds(textPercent, 0, textPercent.length, result)
    }

    drawIntoCanvas {
        it.nativeCanvas.drawText(
            textPercent,
            rect.center.x - paint.measureText(textPercent) / 2,
            rect.center.y + result.height().toFloat() / 2,
            paint
        )
    }
}

private fun convertValue(
    currValue: Float,
    min1: Float,
    max1: Float,
    min2: Float,
    max2: Float
): Float {
    return (currValue - min1) / (max1 - min1) * (max2 - min2) + min2
}

@Preview
@Composable
fun ShieldPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Shield(
            modifier = Modifier
                .background(Color.Red)
                .align(Alignment.Center)
                .width(200.dp)
                .height(260.dp),
            repeatDuration = 1500,
            progress = 1.0f
        )
    }
}