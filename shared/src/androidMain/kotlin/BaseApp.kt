import android.app.Application
import com.eyedea.shared_core.base.KtorNetworkProvider
import com.eyedea.shared_core.util.CacheUtil
import com.eyedea.shared_core.util.KtorClientEngine
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        KtorClientEngine.context = applicationContext
        CacheUtil.context = applicationContext
    }
}