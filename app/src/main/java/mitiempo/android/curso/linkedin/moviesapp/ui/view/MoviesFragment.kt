package mitiempo.android.curso.linkedin.moviesapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.superheroapp.ui.view.adapter.MoviesAdapter
import mitiempo.android.curso.linkedin.moviesapp.data.model.dataJson.Item
import mitiempo.android.curso.linkedin.moviesapp.databinding.FragmentListMoviesBinding
import mitiempo.android.curso.linkedin.moviesapp.ui.viewModel.listMoviesViewModel

interface Communicator {

    fun passData(idSuper: String)

}
class MoviesListFragment : Fragment() {

    private var _binding: FragmentListMoviesBinding? = null
    private val binding get()=_binding!!
    private val listMoviesViewModel: listMoviesViewModel by viewModels()


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
        listMoviesViewModel.onCreate()
        setupViewModel()
    }
    private fun setupViewModel() {
        listMoviesViewModel.movies.observe(this,{
            initRecyclerView(it.items as MutableList<Item>)
        })

    }

    fun initRecyclerView(results: MutableList<Item>){
        binding.recyclerSuperHeroes.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerSuperHeroes.adapter =
            MoviesAdapter(results) /*{ selectedHero ->
                //onItemSelected(selectedHero)
            }*/

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