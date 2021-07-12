package zemoa.states.main

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service
import zemoa.controllers.Screen
import zemoa.states.Reducer

@Service
class MainReducer(val state: MainStateHolder): ApplicationListener<MainEvent<*>>, Reducer<MainStateHolder>(state) {
    override fun onApplicationEvent(event: MainEvent<*>) {
        when(event) {
            is StartLoadingEvent -> onStartLoading(event)
            is StopLoadingEvent -> onStopLoading(event)
        }
    }
    private fun onStartLoading(startLoadingEvent: StartLoadingEvent) {
        if(!state.state.value.loading) {
            state.state.onNext(state.state.value.copy(loading = true))
        }
    }

    private fun onStopLoading(stopLoadingEvent: StopLoadingEvent) {
        if(state.state.value.loading) {
            state.state.onNext(state.state.value.copy(loading = false))
        }
    }
}
