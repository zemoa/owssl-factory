package zemoa.states.navigation

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service
import zemoa.states.Reducer

@Service
class NavigationReducer(val state: NavigationState): ApplicationListener<NavigationEvent>, Reducer<NavigationState>(state) {
    override fun onApplicationEvent(event: NavigationEvent) {
        state.changeScreen(event.payload)
    }
}
