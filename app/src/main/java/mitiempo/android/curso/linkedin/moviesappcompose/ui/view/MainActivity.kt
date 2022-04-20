package mitiempo.android.curso.linkedin.moviesappcompose.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.ui.viewModel.listMoviesViewModel
import mitiempo.android.curso.linkedin.moviesappcompose.ui.theme.MoviesAppComposeTheme

class MainActivity : ComponentActivity() {
    private val listMoviesViewModel: listMoviesViewModel by viewModels()
    private var mActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listMoviesViewModel.onCreate()
        listMoviesViewModel.movies.observe(this,{
            listMoviesViewModel.moviesList.value = it.items
            Log.i("Test2",it.toString())

        })
        listMoviesViewModel.moviesListFilter.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listMoviesViewModel.moviesListFilt = it.results
            Log.i("Test2",it.results.toString())


        })
        setContent {
            MoviesAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Log.i("Test4",listMoviesViewModel.moviesList.toString())
                    Greeting(listMoviesViewModel.moviesList)

                }
            }
        }
    }

    private fun setupViewModel() {


    }
    @Composable
    fun Greeting(listMovies: List<Item>) {
        val scrollState = rememberLazyListState()

        Log.i("Test3",listMovies.toString())
        var text = remember { mutableStateOf("text") }
        var query = listMoviesViewModel.query.value


        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                color = MaterialTheme.colors.primary,
                elevation = 8.dp,
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(8.dp)
                        ,
                        value = query,
                        onValueChange = {
                            listMoviesViewModel.onQueryChanged(it)
                        },
                        label = { Text("Search") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),
                        leadingIcon = {
                            Icon(Icons.Filled.Search)
                        },
                        onImeActionPerformed = { action, softKeyboardController ->
                            if (action == ImeAction.Done) {
                                listMoviesViewModel.invokeFilter(query)
                                softKeyboardController?.hideSoftwareKeyboard()
                            }
                        },
                        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                        backgroundColor = MaterialTheme.colors.surface                    )
                }
            }
        }

        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(listMovies) { index, selectedFilter ->
                Log.i("selectedFilter",index.toString())
                ProfileCard(selectedFilter)



            }
        }
    }

    @Composable
    fun ProfileCard(movie: Item) {
        Card(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top),
            elevation = 8.dp,
            backgroundColor = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                ProfilePicture(movie.poster_path, movie.original_title)
                Spacer(modifier = Modifier.width(8.dp))
                ProfileContent(movie.original_title, movie.overview)
            }

        }
    }

    @Composable
    fun ProfilePicture(poster: String,nameMovie: String) {

        GlideImage(
            imageModel = "https://image.tmdb.org/t/p/w500/" + poster,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            requestOptions = {
                RequestOptions()
                    .override(256, 256)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            },
            modifier = Modifier
                .width(72.dp)
                .height(100.dp),
            contentDescription = "Imagen Pelicula $nameMovie"


        )

    }

    @Composable
    fun ProfileContent(Name: String, Description: String) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            CompositionLocalProvider(
                LocalContentAlpha provides (
                        ContentAlpha.medium)
            ) {
                Text(
                    text = Name,
                    style = MaterialTheme.typography.h5
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides (ContentAlpha.medium)) {
                Text(
                    text = Description,
                    maxLines = 3,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }

}






fun View.hideKeyboard() {
    val imm = ContextCompat.getSystemService(context, InputMethodManager::class.java) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
}

