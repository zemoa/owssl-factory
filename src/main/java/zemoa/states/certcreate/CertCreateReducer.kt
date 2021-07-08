package zemoa.states.certcreate

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service
import zemoa.certcreator.api.CertCreator
import zemoa.certcreator.api.CertRequest
import zemoa.certpersister.api.CertPersister
import zemoa.states.Reducer

@Service
class CertCreateReducer(val state: CertCreateStateHolder, val certCreator: CertCreator, val certPersister: CertPersister): ApplicationListener<CertCreatEvent<*>>, Reducer<CertCreateStateHolder>(state) {
    @Value("\${app.certrootdir}")
    lateinit var certDir: String;
    override fun onApplicationEvent(event: CertCreatEvent<*>) {
        when(event) {
            is StartCreationCertEvent -> onCreatCert(event)
        }
    }

    private fun onCreatCert(startCreationCertEvent: StartCreationCertEvent) {
        state.state.onNext(CertCreateState(creating = true, creationError = false))
        val certPair = certCreator.createCACert(CertRequest(
            commonName = startCreationCertEvent.payload.commonName,
            secret= startCreationCertEvent.payload.secret,
            duration= startCreationCertEvent.payload.duration,
            countryName= startCreationCertEvent.payload.countryName,
            state= startCreationCertEvent.payload.state,
            locality= startCreationCertEvent.payload.locality,
            organization= startCreationCertEvent.payload.organization,
            organizationUnit= startCreationCertEvent.payload.organizationUnit,
            email=startCreationCertEvent.payload.email
        ))
        certPersister.persistKey(certPair.first, certDir,startCreationCertEvent.payload.name)
        certPersister.persistCert(certPair.second, certDir,startCreationCertEvent.payload.name)
        state.state.onNext(CertCreateState(creating = true))
    }
}
