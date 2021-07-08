package zemoa.states.navigation

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service
import zemoa.controllers.Screen
import zemoa.states.Reducer

@Service
class NavigationReducer(val state: NavigationStateHolder): ApplicationListener<NavigationEvent<*>>, Reducer<NavigationStateHolder>(state) {
    override fun onApplicationEvent(event: NavigationEvent<*>) {
        when(event) {
            is ChangeScreenEvent -> onChangeScreen(event)
        }
    }

    private fun onChangeScreen(changeScreenEvent: ChangeScreenEvent) {
        if(state.state.value.currentScreen != changeScreenEvent.payload) {
            state.state.onNext(state.state.value.copy(currentScreen = changeScreenEvent.payload))
        }
    }
}
