package com.vector.myapplication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.animation.core.Spring
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore


import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vector.myapplication.ui.theme.KotlinComposeGitHubActionsCITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinComposeGitHubActionsCITheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(
) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if(shouldShowOnboarding){
        OnBoardingScreen(OnContinueClicked = {shouldShowOnboarding = false})
    }else{
        Greetings()
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview(){
    KotlinComposeGitHubActionsCITheme {
        MyApp()
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier,
              names: List<String> = List(1000){"$it"}) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items (items = names) {name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun OnBoardingScreen(OnContinueClicked: () -> Unit, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome to the Basics Codelab!")
        Button(modifier = Modifier.padding(vertical = 24.dp),
            onClick = OnContinueClicked
        ) {Text("Continue")}
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
fun OnBoardingScreenPreview() {
    KotlinComposeGitHubActionsCITheme { OnBoardingScreen(OnContinueClicked = {},Modifier) }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable{mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        if(expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier.weight(1f).padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "Hello ")
                Text(text = "$name!", style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
                ))
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }}

    }
}


@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES, name ="GreetingPreviewDark")
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview() {
    KotlinComposeGitHubActionsCITheme {
        Greetings()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinComposeGitHubActionsCITheme {
        Greeting("Android")
    }
}

