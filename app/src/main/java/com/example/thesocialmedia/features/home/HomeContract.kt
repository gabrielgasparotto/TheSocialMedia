package com.example.thesocialmedia.features.home

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView

class HomeContract {

    interface HomeUserView : BaseUserView<HomeBusiness> {
        fun reposicionaFragment(fragment: Fragment)
    }

    abstract class HomeBusiness(val homeUserView: HomeUserView): BaseBusiness {
        abstract fun escolheAbasSelecionadas(): BottomNavigationView.OnNavigationItemSelectedListener
    }
}