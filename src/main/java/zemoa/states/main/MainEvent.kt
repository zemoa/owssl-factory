package zemoa.states.main

import zemoa.states.StateEvent

abstract class MainEvent<T>(payload: T): StateEvent<T>(payload)

class StartLoadingEvent(): MainEvent<Boolean>(true)
class StopLoadingEvent(): MainEvent<Boolean>(false)
