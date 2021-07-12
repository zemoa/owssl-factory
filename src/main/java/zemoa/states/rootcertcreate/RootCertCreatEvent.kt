package zemoa.states.rootcertcreate

import zemoa.states.StateEvent

abstract class RootCertCreatEvent<T>(payload: T): StateEvent<T>(payload)

class StartCreationRootCertEvent(payload: Payload): RootCertCreatEvent<StartCreationRootCertEvent.Payload>(payload) {
    data class Payload(
        val commonName: String,
        val secret: String,
        val duration: Long,
        val name: String,
        val countryName: String?,
        val state: String?,
        val locality: String?,
        val organization: String?,
        val organizationUnit: String?,
        val email: String?
    )
}
