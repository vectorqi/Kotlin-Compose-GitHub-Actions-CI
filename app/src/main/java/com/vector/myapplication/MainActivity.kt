package com.vector.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for(name in names){
        Greeting(name)
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello ")
                Text(text = "$name!")
            }
            ElevatedButton(modifier = Modifier, onClick = {}) { Text("Show More")}
        }

    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    KotlinComposeGitHubActionsCITheme {
        MyApp()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinComposeGitHubActionsCITheme {
        Greeting("Android")
    }
}