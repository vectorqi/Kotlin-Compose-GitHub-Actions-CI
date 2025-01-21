package com.vector.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier,
          names: List<String> = listOf("WakakaLlala","Huhaha")) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
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
        MyApp(Modifier.fillMaxSize())
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
    var expanded by remember{mutableStateOf(false)}
    var extraPadding = if(expanded) 48.dp else 0.dp
    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier.weight(1f).padding(bottom = extraPadding)) {
                Text(text = "Hello ")
                Text(text = "$name!")
            }
            ElevatedButton(modifier = Modifier,
                onClick = {expanded = !expanded}
            ) { Text(if(expanded)"Show Less" else "Show More")}
        }

    }
}

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