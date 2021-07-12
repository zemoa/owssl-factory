package zemoa.states.main

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import zemoa.controllers.Screen
import zemoa.states.StateHolder
import zemoa.states.StateHolderAbstract

interface MainStateHolder: StateHolder<MainState>

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
class MainStateHolderImpl : StateHolderAbstract<MainState>(), MainStateHolder {
    override val initState: MainState
        get() = MainState()
}

data class MainState(
    var loading: Boolean = false
)
