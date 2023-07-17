package com.eyedea.shared_core.base

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.resources.ImageResource

abstract class BaseTab : ComposeContent {
    abstract val activeIcon : ImageResource
    abstract val inActiveIcon : ImageResource
    abstract val label : String
    open var navigator : Navigator? = null
    open val router: RouterContract? = null
    @Composable
    override fun ComposeContent(){
        val navigator = LocalNavigator.currentOrThrow
        router?.initiateRouter(navigator)
    }
}

interface RouterContract {
    fun initiateRouter(navigator: Navigator)
}

interface ComposeContent{
    @Composable abstract fun ComposeContent()
}

abstract class BaseRouter : RouterContract {

    private var navigator: Navigator? = null

    override fun initiateRouter(navigator: Navigator) {
        this.navigator = navigator
    }

    protected fun push(screen : Screen){
        navigator?.push(screen)
    }
}