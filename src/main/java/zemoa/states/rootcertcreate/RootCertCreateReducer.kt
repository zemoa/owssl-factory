package zemoa.states.rootcertcreate

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service
import zemoa.certcreator.api.CertCreator
import zemoa.certcreator.api.CertRequest
import zemoa.certpersister.api.CertPersister
import zemoa.states.Reducer
import zemoa.states.main.StartLoadingEvent
import zemoa.states.main.StopLoadingEvent

@Service
class RootCertCreateReducer(val state: RootCertCreateStateHolder,
                            val certCreator: CertCreator,
                            val certPersister: CertPersister,
                            private val applicationEventPublisher: ApplicationEventPublisher): ApplicationListener<RootCertCreatEvent<*>>, Reducer<RootCertCreateStateHolder>(state) {
    @Value("\${app.certrootdir}")
    lateinit var certDir: String;
    override fun onApplicationEvent(event: RootCertCreatEvent<*>) {
        when(event) {
            is StartCreationRootCertEvent -> onCreatCert(event)
        }
    }

    private fun onCreatCert(startCreationCertEvent: StartCreationRootCertEvent) {
        applicationEventPublisher.publishEvent(StartLoadingEvent())
        state.state.onNext(RootCertCreateState(creating = true, creationError = false))
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
        state.state.onNext(RootCertCreateState(creating = true))
        applicationEventPublisher.publishEvent(StopLoadingEvent())
    }
}
