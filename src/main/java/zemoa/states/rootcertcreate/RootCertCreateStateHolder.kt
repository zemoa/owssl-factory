package zemoa.states.rootcertcreate

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import zemoa.states.StateHolder
import zemoa.states.StateHolderAbstract

interface RootCertCreateStateHolder: StateHolder<RootCertCreateState>

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
class RootCertCreateStateHolderImpl: StateHolderAbstract<RootCertCreateState>(), RootCertCreateStateHolder {
     override val initState: RootCertCreateState
        get() = RootCertCreateState()

}

data class RootCertCreateState(
    val creating: Boolean = false,
    val creationError: Boolean = false
)

