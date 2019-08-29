package com.example.thesocialmedia.features.maps

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.util.UsuarioUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
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
        val usuario = UsuarioUtils.usuario
        configurarBusiness(MapsPresenter(this, context!!))
        business.configuraMapa(usuario.address.geo.lat, usuario.address.geo.lng, this)
        configurarToolbar(view, usuario)
    }

    private fun configurarToolbar(view: View, usuario: Users) {
        root.toolbarMaps.apply {
            toolbarMaps.setTitleTextColor(Color.WHITE)
            title = usuario.name
        }
    }

    override fun exibeSnackbar(mensagem: String) {
        SnackbarUtils().showSnack(mensagem, root.toolbarMaps , activity!!.applicationContext)
    }
}
