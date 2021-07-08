package zemoa.states.certcreate

import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import zemoa.states.StateHolder
import zemoa.states.StateHolderAbstract

interface CertCreateStateHolder: StateHolder<CertCreateState>

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
class CertCreateStateHolderImpl: StateHolderAbstract<CertCreateState>(), CertCreateStateHolder {
     override val initState: CertCreateState
        get() = CertCreateState()

}

data class CertCreateState(
    val creating: Boolean = false,
    val creationError: Boolean = false
)

