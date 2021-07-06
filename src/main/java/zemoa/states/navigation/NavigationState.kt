package zemoa.states.navigation

import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import zemoa.controllers.Screen
import zemoa.states.State

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
class NavigationState : State {
    var currentScreen: BehaviorSubject<Screen> = BehaviorSubject.createDefault(Screen.HOME)
    var history: BehaviorSubject<List<Screen>> = BehaviorSubject.createDefault(listOf(Screen.HOME))
    fun changeScreen(screen: Screen) {
        if(currentScreen.value != screen) {
            currentScreen.onNext(screen)
            addToHistory(screen)
        }
    }

    fun back() {
        if(history.value.size > 1) {
            val nextHistory = history.value.toMutableList()
            currentScreen.onNext(nextHistory.removeAt(nextHistory.size - 1))
            history.onNext(nextHistory)
        }
    }

    private fun addToHistory(screen: Screen) {
        val nextHistory = history.value.toMutableList()
        nextHistory.add(screen)
        history.onNext(nextHistory)
    }
}
