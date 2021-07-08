package zemoa.certcreator.api

import java.security.PrivateKey
import java.security.cert.Certificate

interface CertCreator {
    fun createCACert(certRequest: CertRequest): Pair<PrivateKey, Certificate>
}
