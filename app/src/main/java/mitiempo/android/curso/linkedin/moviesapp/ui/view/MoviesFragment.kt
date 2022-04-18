package mitiempo.android.curso.linkedin.moviesapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.superheroapp.ui.view.adapter.MoviesAdapter
import mitiempo.android.curso.linkedin.moviesapp.R
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.databinding.FragmentListMoviesBinding
import mitiempo.android.curso.linkedin.moviesapp.ui.viewModel.listMoviesViewModel
import java.util.*
import android.widget.LinearLayout

import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Movies


@AndroidEntryPoint
class MoviesListFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentListMoviesBinding? = null
    private val binding get()=_binding!!
    private val listMoviesViewModel: listMoviesViewModel by viewModels()
    private var mActivity: MainActivity? = null
    private lateinit var mBehavior: BottomSheetBehavior<View>


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
        /*mBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        binding.btnOrdenar.setOnClickListener {

        }*/
        binding.button.setOnClickListener {
            showBottomSheetDialog()
        }



    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout)
        val rate = bottomSheetDialog.findViewById<LinearLayout>(R.id.rate)
        val popular = bottomSheetDialog.findViewById<LinearLayout>(R.id.popular)
        if (rate != null) {
            rate.setOnClickListener {
                Log.i("Test","ok")
                listMoviesViewModel.getTopRates()
                bottomSheetDialog.dismiss()

            }
        }
        if (popular != null) {
            popular.setOnClickListener {
                Log.i("Test","ok")
                listMoviesViewModel.getPopular()
                bottomSheetDialog.dismiss()

            }
        }

        bottomSheetDialog.show()
    }

    private fun setupViewModel() {

        listMoviesViewModel.statusFilter.observe(this,{
            if (it == true){
                Toast.makeText(requireContext(),"Se necesita Internet para esta operacion",Toast.LENGTH_SHORT).show()
            }
        })
        listMoviesViewModel.movies.observe(this,{
            initRecyclerView(it as MutableList<Item>)
            listMoviesViewModel.moviesList = it as MutableList<List<Movies>>
            binding.progressBar.visibility = View.GONE

        })
        listMoviesViewModel.moviesListFilter.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listMoviesViewModel.moviesListFilt = it.results
            Log.i("Test2",it.results.toString())


        })
        listMoviesViewModel.topRatesMovies.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listMoviesViewModel.moviesList = it as MutableList<List<Movies>>
            Log.i("TestTop",it.toString())
            binding.recyclerMovies.adapter=MoviesAdapter(listMoviesViewModel.moviesList, "")


        })
        listMoviesViewModel.popularMovies.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listMoviesViewModel.moviesList = it as MutableList<List<Movies>>
            Log.i("TestPopular",it.toString())
            binding.recyclerMovies.adapter=MoviesAdapter(listMoviesViewModel.moviesList, "")


        })

    }

    fun initRecyclerView(results: MutableList<Item>){
        binding.recyclerMovies.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerMovies.adapter =
            MoviesAdapter(results, "") /*{ selectedHero ->
                //onItemSelected(selectedHero)
            }*/



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
        binding.recyclerMovies.adapter?.notifyDataSetChanged()
        return true
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        val searchText = query!!.toLowerCase(Locale.getDefault())
        if(searchText.isNotEmpty() && searchText != "") {
            listMoviesViewModel.invokeFilter(searchText)
            binding.recyclerMovies.adapter =
                MoviesAdapter(listMoviesViewModel.moviesListFilt, query)
        }
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
            R.id.rate -> {
                Toast.makeText(requireContext(),"Test",Toast.LENGTH_LONG).show()
                true
            }
            R.id.popular -> {
                Toast.makeText(requireContext(),"Test",Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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