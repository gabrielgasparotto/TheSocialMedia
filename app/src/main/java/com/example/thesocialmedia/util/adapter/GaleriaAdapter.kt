package com.example.thesocialmedia.util.adapter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Photos
import com.example.thesocialmedia.view.fragment.DialogPhotoFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_galeria.view.*
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent


class GaleriaAdapter(val fotos: ArrayList<Photos>)
    : RecyclerView.Adapter<ViewHolderGaleria>() {

    lateinit var context: Context
    lateinit var runnable: Runnable
    lateinit var handler: Handler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGaleria {
        val viewHolder = ViewHolderGaleria(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_galeria, parent, false)
        )
        context = parent.context

        initTouchEventHandler();

        return viewHolder
    }

    override fun getItemCount(): Int = fotos.size

    override fun onBindViewHolder(holder: ViewHolderGaleria, position: Int) {
        val foto = fotos.get(position)

        //Carregar a imagem utilizando Picasso
        Picasso
            .get()
            .load(foto.thumbnailUrl)
            .into(holder.imagemItem)


        //Abrindo DialogFragment com a imagem
        /*holder.imagemItem.setOnLongClickListener {
            val dialogFragment = DialogPhotoFragment()
            openDialogFragment(foto, dialogFragment)
            true
        }*/

        val dialogFragment = DialogPhotoFragment()
        holder.imagemItem.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    hideDialog(dialogFragment)
                }
                MotionEvent.ACTION_DOWN -> {
                    showDialog(foto, dialogFragment)
                }

                MotionEvent.ACTION_CANCEL -> {
                    hideDialog(dialogFragment)
                }
            }
            true
        }

    }

    private fun openDialogFragment(foto: Photos,
                                   dialogFragment: DialogPhotoFragment) {
        val bundle = Bundle()
        bundle.putSerializable("foto", foto)
        dialogFragment.arguments = bundle

        val manager = (context as AppCompatActivity).supportFragmentManager
        val ft = manager.beginTransaction()
        val prev = manager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)

        dialogFragment.show(ft, "dialog")
    }

    private fun initTouchEventHandler() {
        handler = Handler()
    }

    private fun showDialogWithDelay() {
        handler.postDelayed(runnable, 200);
    }

    private fun showDialog(foto: Photos,
                           dialogFragment: DialogPhotoFragment) {
        runnable = Runnable { openDialogFragment(foto, dialogFragment) }
        showDialogWithDelay()
    }

    private fun cancelRunnableTask() {
        handler.removeCallbacks(runnable)
    }

    private fun hideDialog(dialog: DialogPhotoFragment) {
        cancelRunnableTask()

        if (dialog.isVisible) {
            dialog.dismiss()
        }
    }
}



class ViewHolderGaleria(view: View) : RecyclerView.ViewHolder(view) {
    val imagemItem = view.imagemGaleria
}