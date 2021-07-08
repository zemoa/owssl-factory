package zemoa.states.certcreate

import zemoa.states.StateEvent

abstract class CertCreatEvent<T>(payload: T): StateEvent<T>(payload)

class StartCreationCertEvent(payload: Payload): CertCreatEvent<StartCreationCertEvent.Payload>(payload) {
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
