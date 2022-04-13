package mitiempo.android.curso.linkedin.moviesapp.ui.view.tv

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.superheroapp.ui.view.adapter.MoviesAdapter
import mitiempo.android.curso.linkedin.moviesapp.R
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.databinding.FragmentListMoviesBinding
import java.util.*
import android.widget.LinearLayout

import com.google.android.material.bottomsheet.BottomSheetDialog
import mitiempo.android.curso.linkedin.moviesapp.ui.view.MainActivity
import mitiempo.android.curso.linkedin.moviesapp.ui.viewModel.listTvViewModel


interface Communicator {

    fun passData(idSuper: String)
    fun invokeFilterPopular()
    fun invokeFilterRate()

}
class MoviesListFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentListMoviesBinding? = null
    private val binding get()=_binding!!
    private val listTvViewModel: listTvViewModel by viewModels()
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

        listTvViewModel.onCreate()
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
                listTvViewModel.getTopRates()
                bottomSheetDialog.dismiss()

            }
        }
        if (popular != null) {
            popular.setOnClickListener {
                Log.i("Test","ok")
                listTvViewModel.getPopular()
                bottomSheetDialog.dismiss()

            }
        }

        bottomSheetDialog.show()
    }

    private fun setupViewModel() {
        listTvViewModel.movies.observe(this,{
            initRecyclerView(it.items as MutableList<Item>)
            listTvViewModel.moviesList = it.items

        })
        listTvViewModel.moviesListFilter.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listTvViewModel.moviesListFilt = it.results
            Log.i("Test2",it.results.toString())


        })
        listTvViewModel.topRatesMovies.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listTvViewModel.moviesList = it.results as MutableList<Item>
            Log.i("TestTop",it.results.toString())
            binding.recyclerMovies.adapter=MoviesAdapter(listTvViewModel.moviesList, "")


        })
        listTvViewModel.popularMovies.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listTvViewModel.moviesList = it.results as MutableList<Item>
            Log.i("TestPopular",it.results.toString())
            binding.recyclerMovies.adapter=MoviesAdapter(listTvViewModel.moviesList, "")


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
        listTvViewModel.invokeFilter(query)
        initCharacter(listTvViewModel.moviesListFilt as MutableList<Item>, query)

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
            listTvViewModel.invokeFilter(searchText)
            binding.recyclerMovies.adapter=MoviesAdapter(listTvViewModel.moviesListFilt, newText)
        }else {
            listTvViewModel.onCreate()
            binding.recyclerMovies.layoutManager = LinearLayoutManager(this.requireContext())
            binding.recyclerMovies.adapter =
                MoviesAdapter(listTvViewModel.moviesList, "") /*{ selectedHero ->
                //onItemSelected(selectedHero)
            }*/

        }
        //searchByName(query.toLowerCase())
        binding.recyclerMovies.adapter?.notifyDataSetChanged()
        return true
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        listTvViewModel.tempSearch.clear()
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