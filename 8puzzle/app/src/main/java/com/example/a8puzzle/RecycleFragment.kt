package com.example.a8puzzle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecycleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_recycle, container, false)

        val players : ArrayList<String> = ArrayList()
        Log.d("debug", "onCreateView: debug")
        for (i in 1..100)
            players.add("Jim AIT $i")
        val listRecycler : RecyclerView? = rootView?.findViewById(R.id.list_recycle)
        listRecycler?.layoutManager = LinearLayoutManager(context)
//        listRecycler?.adapter = Adapter(players)
        var dao : ItemCollectionDAO?
        HttpManager.retrofitService.loadPlayerList().enqueue(object : Callback<ArrayList<PlayerItemDAO>>{
            override fun onResponse(
                call: Call<ArrayList<PlayerItemDAO>>,
                response: Response<ArrayList<PlayerItemDAO>>
            ) {
                val items : ArrayList<PlayerItemDAO>? = response.body()
//                Toast.makeText(activity, items?.get(0)?.strName, Toast.LENGTH_LONG).show()
                listRecycler?.adapter = Adapter(items)
            }

            override fun onFailure(call: Call<ArrayList<PlayerItemDAO>>, t: Throwable) {
                Log.d("debug", "onFailure: $t")
            }
        })

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecycleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecycleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}