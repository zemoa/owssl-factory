package zemoa.states.navigation

import zemoa.controllers.Screen
import zemoa.states.StateEvent

class NavigationEvent(screen: Screen): StateEvent<Screen>(screen) {
}
