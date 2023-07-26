package di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initNappier(){
    Napier.base(DebugAntilog())
}