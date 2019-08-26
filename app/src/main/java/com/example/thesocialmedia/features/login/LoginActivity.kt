package com.example.thesocialmedia.features.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.thesocialmedia.R
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.view.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.LoginUserView {
    override lateinit var business: LoginContract.LoginBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        configurarBusiness(LoginPresenter(this), applicationContext)

        buttonLogin.setOnClickListener {
            business.aoConsultarUsuario(usernameLogin.text.toString())
        }
    }

    override fun exibeSnackbar(mensagem: String) {
        SnackbarUtils().showSnack(mensagem, buttonLogin, applicationContext)
    }

    override fun obterEstadoCheckboxPermanecerLogado() = checkboxLogin.isChecked

    override fun onDestroy() {
        super.onDestroy()
        business.aoFinalizar()
    }

    override fun irParaTelaInicial() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
