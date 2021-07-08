package zemoa.states

import org.springframework.context.ApplicationEvent
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

abstract class StateEvent<T>(val payload: T): ApplicationEvent(JvmType.Object("obj"))
