package zemoa.certpersister.api

import java.security.PrivateKey
import java.security.cert.Certificate

interface CertPersister {
    fun persistKey(privateKey: PrivateKey, path: String, keyName: String): String
    fun persistCert(certificate: Certificate, path: String, keyName: String): String
}
