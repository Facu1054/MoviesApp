package mitiempo.android.curso.linkedin.moviesapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import mitiempo.android.curso.linkedin.moviesapp.R
import mitiempo.android.curso.linkedin.moviesapp.databinding.ActivityMoviesBinding
import mitiempo.android.curso.linkedin.moviesapp.databinding.FragmentListMoviesBinding
import mitiempo.android.curso.linkedin.moviesapp.ui.viewModel.listMoviesViewModel

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    //private val binding get()=_binding!!
    private val listMoviesViewModel: listMoviesViewModel by viewModels()
    private var mActivity: MainActivity? = null
    private lateinit var mBehavior: BottomSheetBehavior<View>
    companion object {
        const val TAG = "ModalBottomSheet"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ShowBottomSheetFragment()
        /*binding.btnOrdenar.setOnClickListener {
            supportFragmentManager.let {
                bottomSheet.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }


        }*/

    }

    /*fun ShowBottomSheetFragment() {
        val mBottomSheetFragment = ModalBottomSheet()
        mBottomSheetFragment.show(supportFragmentManager, "MY_BOTTOM_SHEET")
    }*/

}