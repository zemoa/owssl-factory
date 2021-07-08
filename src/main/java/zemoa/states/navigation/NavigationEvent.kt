package zemoa.states.navigation

import zemoa.controllers.Screen
import zemoa.states.StateEvent

abstract class NavigationEvent<T>(payload: T): StateEvent<T>(payload)

class ChangeScreenEvent(screen: Screen): NavigationEvent<Screen>(screen)
