package com.example.thesocialmedia.features.maps

import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView

class MapsContract {

    interface MapsUserView : BaseUserView<MapsBusiness> {
        fun exibeSnackbar(mensagem: String)
        fun configurarToolbar(titulo: String)
    }

    abstract class MapsBusiness(val mapsUserView: MapsUserView) : BaseBusiness {
        abstract fun configuraMapa(latitude: Double, longitude: Double, fragment: MapsFragment)
    }
}