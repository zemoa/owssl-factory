package zemoa.states

import io.reactivex.rxjava3.subjects.BehaviorSubject

interface StateHolder<T> {
    val state: BehaviorSubject<T>
}
private interface StateHolderValue<T> {
    val initState: T
}
abstract class StateHolderAbstract<T>(): StateHolderValue<T>, StateHolder<T>  {
    override val state: BehaviorSubject<T> by lazy {
        BehaviorSubject.createDefault(initState)
    }

}
