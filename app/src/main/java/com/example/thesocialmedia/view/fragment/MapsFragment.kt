package com.example.thesocialmedia.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.UsuarioUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.view.*

class MapsFragment : Fragment() {

    lateinit var googleMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuario = UsuarioUtils.usuario
        configurarMapa(usuario.address.geo.lat, usuario.address.geo.lng)
        configurarToolbar(view, usuario)
    }

    private fun configurarToolbar(view: View, usuario: Users) {
        view.toolbarMaps.apply {
            toolbarMaps.setTitleTextColor(Color.WHITE)
            title = usuario.name
        }
    }

    private fun configurarMapa(latitude: Double, longitude: Double) {
        try {
            val mapFragment = childFragmentManager.findFragmentById(R.id.fragmentMaps) as SupportMapFragment
            mapFragment.getMapAsync(OnMapReadyCallback {
                val latLng = LatLng(latitude, longitude)
                googleMap = it
                googleMap.apply {
                    addMarker(MarkerOptions().position(latLng))
                    animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 1f))
                }
            })
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

    }
}
