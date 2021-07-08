package zemoa.states.navigation

import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import zemoa.controllers.Screen
import zemoa.states.StateHolder
import zemoa.states.StateHolderAbstract
import zemoa.states.certcreate.CertCreateState

interface NavigationStateHolder: StateHolder<NavigationState>

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
class NavigationStateHolderImpl : StateHolderAbstract<NavigationState>(), NavigationStateHolder {
    override val initState: NavigationState
        get() = NavigationState()
}

data class NavigationState(
    var currentScreen: Screen = Screen.HOME
)
