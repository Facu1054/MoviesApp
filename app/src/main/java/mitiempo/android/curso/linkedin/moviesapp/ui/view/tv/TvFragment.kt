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
import com.superheroapp.ui.view.adapter.MoviesAdapter
import mitiempo.android.curso.linkedin.moviesapp.R
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import java.util.*
import android.widget.LinearLayout

import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import mitiempo.android.curso.linkedin.moviesapp.databinding.FragmentListTvBinding
import mitiempo.android.curso.linkedin.moviesapp.domain.model.Tv
import mitiempo.android.curso.linkedin.moviesapp.ui.view.MainActivity
import mitiempo.android.curso.linkedin.moviesapp.ui.viewModel.listTvViewModel


@AndroidEntryPoint
class TvListFragment : Fragment(), SearchView.OnQueryTextListener {


    private var _binding: FragmentListTvBinding? = null
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
        _binding = FragmentListTvBinding.inflate(inflater,container,false)
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

        listTvViewModel.tvListFilter.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listTvViewModel.tvListFilt = it.results
            Log.i("Test2",it.results.toString())


        })
        listTvViewModel.topRatesTv.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            listTvViewModel.tvList = it as MutableList<List<Tv>>
            Log.i("TestTop",it.toString())
            binding.recyclerMovies.adapter=MoviesAdapter(listTvViewModel.tvList, "")


        })
        listTvViewModel.popularTv.observe(this,{
            //initRecyclerView(it.items as MutableList<Item>)
            initRecyclerView(it )
            listTvViewModel.tvList = it as MutableList<List<Tv>>
            Log.i("TestPopular",it.toString())
            binding.recyclerMovies.adapter=MoviesAdapter(listTvViewModel.tvList, "")


        })

    }

    fun initRecyclerView(results: List<Any>){
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
            listTvViewModel.invokeFilter(searchText)
            binding.recyclerMovies.adapter=MoviesAdapter(listTvViewModel.tvListFilt, newText)
        }else {
            listTvViewModel.onCreate()
            binding.recyclerMovies.layoutManager = LinearLayoutManager(this.requireContext())
            binding.recyclerMovies.adapter =
                MoviesAdapter(listTvViewModel.tvList, "") /*{ selectedHero ->
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