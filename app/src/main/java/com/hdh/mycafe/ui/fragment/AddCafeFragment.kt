package com.hdh.mycafe.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hdh.mycafe.MainActivity
import com.hdh.mycafe.databinding.FragmentAddCafeBinding
import com.hdh.mycafe.ui.view.AddCafeAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddCafeFragment : Fragment() {

//    private var _binding: FragmentAddCafeBinding? = null
    private var _binding: FragmentAddCafeBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    lateinit var mainActivity: MainActivity
    lateinit var addCafeAdapter : AddCafeAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) {
            mainActivity = context
        } else {
            throw RuntimeException("$context must implement AddCafeFragment")
        }

    }

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
        _binding = FragmentAddCafeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCafeAdapter = AddCafeAdapter(mainActivity)
        binding.cafeRecyclerview.layoutManager = LinearLayoutManager(mainActivity,  LinearLayoutManager.VERTICAL, false)
        binding.cafeRecyclerview.adapter = addCafeAdapter

        // 구분선 추가
        val deco = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.cafeRecyclerview.addItemDecoration(deco)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCafeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}