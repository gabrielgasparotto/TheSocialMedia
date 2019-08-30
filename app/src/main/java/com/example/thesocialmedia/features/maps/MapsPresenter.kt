package com.example.thesocialmedia.features.maps

import android.content.Context
import com.example.thesocialmedia.R
import com.example.thesocialmedia.util.UsuarioUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsPresenter(mapsUserView: MapsContract.MapsUserView, override var context: Context, val fragment: MapsFragment)
    : MapsContract.MapsBusiness(mapsUserView) {

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, false)
        val user = UsuarioUtils.usuario
        mapsUserView.configurarToolbar(user.name)
        configuraMapa(user.address.geo.lat, user.address.geo.lng, fragment)
    }

    override fun configuraMapa(latitude: Double, longitude: Double, fragment: MapsFragment) {
        lateinit var googleMap: GoogleMap
        try {
            val mapFragment = fragment.childFragmentManager.findFragmentById(R.id.fragmentMaps) as SupportMapFragment
            mapFragment.getMapAsync(OnMapReadyCallback {
                val latLng = LatLng(latitude, longitude)
                googleMap = it
                googleMap.apply {
                    addMarker(MarkerOptions().position(latLng))
                    animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 1f))
                }
            })
        } catch (e: Exception) {
            mapsUserView.exibeSnackbar(e.message ?: "Erro Desconhecido")
        }
    }
}