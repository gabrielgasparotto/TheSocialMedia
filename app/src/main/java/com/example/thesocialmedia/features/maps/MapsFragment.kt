package com.example.thesocialmedia.features.maps

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thesocialmedia.R
import com.example.thesocialmedia.util.SnackbarUtils
import kotlinx.android.synthetic.main.fragment_maps.view.*

class MapsFragment : Fragment(), MapsContract.MapsUserView {

    lateinit var root: View
    override lateinit var business: MapsContract.MapsBusiness

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_maps, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configurarBusiness(MapsPresenter(this, context!!))
    }

    override fun capturaFragment(): MapsFragment = this

    override fun configurarToolbar(titulo: String) {
        root.toolbarMaps.apply {
            toolbarMaps.setTitleTextColor(Color.WHITE)
            title = titulo
        }
    }

    override fun exibeSnackbar(mensagem: String) {
        SnackbarUtils().showSnack(mensagem, root.toolbarMaps , activity!!.applicationContext)
    }
}