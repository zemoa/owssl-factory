package zemoa.states

import org.springframework.context.ApplicationEvent

abstract class StateEvent<T: Any>(val payload: T): ApplicationEvent(payload) {
}
