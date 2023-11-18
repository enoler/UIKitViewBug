package uikitview.bug.shared.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BodyMedium(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) = Text(
    text = text,
    style = MaterialTheme.typography.bodyMedium,
    color = color,
    modifier = modifier,
)

@Composable
fun BodyLarge(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) = Text(
    text = text,
    style = MaterialTheme.typography.bodyLarge,
    color = color,
    modifier = modifier,
)

@Composable
fun LabelLarge(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) = Text(
    text = text,
    style = MaterialTheme.typography.labelLarge,
    color = color,
    modifier = modifier,
)

@Composable
fun TitleLargeRegular(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) = Text(
    text = text,
    style = MaterialTheme.typography.titleLarge,
    color = color,
    modifier = modifier,
)

@Composable
fun TitleSmall(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) = Text(
    text = text,
    style = MaterialTheme.typography.titleSmall,
    color = color,
    modifier = modifier,
)
