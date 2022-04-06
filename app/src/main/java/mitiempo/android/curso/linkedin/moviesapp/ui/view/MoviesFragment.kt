package mitiempo.android.curso.linkedin.moviesapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.superheroapp.ui.view.adapter.MoviesAdapter
import mitiempo.android.curso.linkedin.moviesapp.R
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.databinding.FragmentListMoviesBinding
import mitiempo.android.curso.linkedin.moviesapp.ui.viewModel.listMoviesViewModel
import java.util.*

interface Communicator {

    fun passData(idSuper: String)

}
class MoviesListFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentListMoviesBinding? = null
    private val binding get()=_binding!!
    private val listMoviesViewModel: listMoviesViewModel by viewModels()
    private var mActivity: MainActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMoviesBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity?.supportActionBar?.title = "Test"
        setHasOptionsMenu(true)

        listMoviesViewModel.onCreate()
        setupViewModel()
        binding.searchBreed.setOnQueryTextListener(this)


    }
    private fun setupViewModel() {
        listMoviesViewModel.movies.observe(this,{
            initRecyclerView(it.items as MutableList<Item>)
            listMoviesViewModel.moviesList = it.items

        })
        listMoviesViewModel.moviesListFilter.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listMoviesViewModel.moviesListFilt = it.results
            Log.i("Test2",it.results.toString())


        })

    }

    fun initRecyclerView(results: MutableList<Item>){
        binding.recyclerMovies.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerMovies.adapter =
            MoviesAdapter(results, "") /*{ selectedHero ->
                //onItemSelected(selectedHero)
            }*/



    }
    private fun searchByName (query: String) {
        listMoviesViewModel.invokeFilter(query)
        initCharacter(listMoviesViewModel.moviesListFilt as MutableList<Item>, query)

        /*doAsync {
            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images").execute()
            val puppies = call.body() as DogsResponse
            uiThread {
                if(puppies.status == "success") {
                    initCharacter(listMoviesViewModel.moviesList as MutableList<Item>, query)
                }else{
                    //showErrorDialog()
                }
                //hideKeyboard()
            }
        }*/


    }


    private fun initCharacter(movies: MutableList<Item>, query: String) {


        binding.recyclerMovies.layoutManager = LinearLayoutManager(this.requireContext())
        Log.i("Test",query)
        binding.recyclerMovies.adapter =
            MoviesAdapter(movies, query)
        //binding.recyclerSuperHeroes.adapter = MoviesAdapter(movies)

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //listMoviesViewModel.moviesList.clear()
        val searchText = newText!!.toLowerCase(Locale.getDefault())
        if(searchText.isNotEmpty() && searchText != ""){
            listMoviesViewModel.invokeFilter(searchText)
            binding.recyclerMovies.adapter=MoviesAdapter(listMoviesViewModel.moviesListFilt, newText)
        }else {
            listMoviesViewModel.onCreate()
            binding.recyclerMovies.layoutManager = LinearLayoutManager(this.requireContext())
            binding.recyclerMovies.adapter =
                MoviesAdapter(listMoviesViewModel.moviesList, "") /*{ selectedHero ->
                //onItemSelected(selectedHero)
            }*/

        }
        //searchByName(query.toLowerCase())
        binding.recyclerMovies.adapter?.notifyDataSetChanged()
        return true
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        listMoviesViewModel.tempSearch.clear()
        binding.root.hideKeyboard()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                mActivity?.onBackPressed()
                true
            }


            R.id.rate -> {
                Snackbar.make(binding.root,"Rate",Snackbar.LENGTH_LONG)
                true
            }
            R.id.popular -> {
                Snackbar.make(binding.root,"Popular",Snackbar.LENGTH_LONG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

        // return super.onOptionsItemSelected(item)
    }

    fun View.hideKeyboard() {
        val imm = ContextCompat.getSystemService(context, InputMethodManager::class.java) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    /*fun onItemSelected(superHero: Result){
        val superHe = SuperHero(superHero.id.toString(),
            superHero.name,superHero.description,
      superHero.thumbnail.path+"."+superHero.thumbnail.extension,
            superHero.comics.available.toString())
        //Se adjunta un objeto de tipo SuperHero
        val bundle = bundleOf("idSuper" to superHe)
        //Se hace la navegacion pero aparte se le envia un argumento
        view?.let { Navigation.findNavController(it).navigate(R.id.infoHeroFragment, bundle) }
    }*/

}