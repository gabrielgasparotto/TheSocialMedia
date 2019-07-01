package com.example.thesocialmedia.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.thesocialmedia.R
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

        configuraMapa(usuario.address.geo.lat, usuario.address.geo.lng)

        view.toolbarMaps.apply {
            toolbarMaps.setTitleTextColor(Color.WHITE)
            title = usuario.name
        }
    }

    private fun configuraMapa(latitude: Double, longitude: Double) {
        try {
            val mapFragment = childFragmentManager.findFragmentById(R.id.fragmentMaps) as SupportMapFragment
            mapFragment.getMapAsync(OnMapReadyCallback {
                googleMap = it
                val latLng = LatLng(latitude, longitude)
                googleMap.addMarker(MarkerOptions().position(latLng))
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 1f))

            })
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

    }
}
