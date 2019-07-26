package com.example.thesocialmedia.view.activity

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.example.thesocialmedia.R
import com.example.thesocialmedia.app.Constants
import org.hamcrest.Matchers.allOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    companion object {
        val USERNAME_CORRETO: String = "Bret"
        val USERNAME_ERRADO: String = "Gaspa"
        val ERRO_USERNAME_INVALIDO: String = "User not found"
        val ERRO_CAMPO_VAZIO: String = "Null or Blank"
    }

    @Rule
    @JvmField
    var rule = IntentsTestRule(LoginActivity::class.java)

    @Before
    fun prepare() {
        clearAppSharedPref()
    }

    @Test
    fun logarComUsernameCorreto() {
        //Busca o campo de usuário e preenche com username
        val usernameLogin = onView(withId(R.id.usernameLogin))
        usernameLogin.perform(scrollTo(), replaceText(USERNAME_CORRETO), closeSoftKeyboard())

        //Busca botao e faz o click
        val buttonLogar = onView(withId(R.id.buttonLogin))
        buttonLogar.perform(scrollTo(), click())

        Thread.sleep(1200)

        //Verifica a Intent que foi acionada após o click
        Intents.intended(
            allOf(
                IntentMatchers.hasComponent(MainActivity::class.java.name)
            )
        )
    }

    @Test
    fun logarComUsernameErrado() {
        //Busca o campo de usuário e preenche com username
        val usernameLogin = onView(withId(R.id.usernameLogin))
        usernameLogin.perform(scrollTo(), replaceText(USERNAME_ERRADO), closeSoftKeyboard())

        //Busca botao e faz o click
        val buttonLogar = onView(withId(R.id.buttonLogin))
        buttonLogar.perform(scrollTo(), click())

        Thread.sleep(1200)

        //Busca snack pelo texto de Usuario não encontrado
        val snackError = onView(withText(ERRO_USERNAME_INVALIDO))
        snackError.check(matches(isDisplayed()))
    }

    @Test
    fun logarSemUsername() {
        //Busca botao e faz o click
        val buttonLogar = onView(withId(R.id.buttonLogin))
        buttonLogar.perform(scrollTo(), click())

        Thread.sleep(1200)

        //Busca snack pelo texto de campo vazio
        val snackError = onView(withText(ERRO_CAMPO_VAZIO))
        snackError.check(matches(isDisplayed()))
    }

    @Test
    fun logarComCheckBoxAtivada() {
        //Busca o campo de usuário e preenche com username
        val usernameLogin = onView(withId(R.id.usernameLogin))
        usernameLogin.perform(scrollTo(), replaceText(USERNAME_CORRETO), closeSoftKeyboard())

        //Busca checkbox e faz o click
        val checkboxLogin = onView(withId(R.id.checkboxLogin))
        checkboxLogin.perform(scrollTo(), click())

        //Busca botao e faz o click
        val buttonLogar = onView(withId(R.id.buttonLogin))
        buttonLogar.perform(scrollTo(), click())

        Thread.sleep(1200)

        //Verifica a Intent que foi acionada após o click
        Intents.intended(
            allOf(
                IntentMatchers.hasComponent(MainActivity::class.java.name)
            )
        )

        //Pega shared preferences e verifica o valor
        val sharedPref = getAppSharedPref()
        Assert.assertTrue(sharedPref.getBoolean(Constants.userPermanece, false))
    }

    private fun clearAppSharedPref() = getAppSharedPref()
        .edit()
        .clear()
        .apply()

    private fun getAppSharedPref() = InstrumentationRegistry
        .getTargetContext()
        .getSharedPreferences(Constants.permaneceDB, Context.MODE_PRIVATE)

}
