package zemoa.certpersister.business

import org.bouncycastle.util.io.pem.PemObject
import org.bouncycastle.util.io.pem.PemWriter
import org.springframework.stereotype.Service
import zemoa.certpersister.api.CertPersister
import java.io.FileWriter
import java.io.StringWriter
import java.nio.charset.Charset
import java.security.PrivateKey
import java.security.cert.Certificate
import java.util.*
import kotlin.io.path.Path

@Service
class CertPersisterImpl : CertPersister {
    override fun persistKey(privateKey: PrivateKey, path: String, keyName: String): String {
        return "toto"
    }

    override fun persistCert(certificate: Certificate, path: String, keyName: String): String {
        val certificateWriter = StringWriter()
        certificateWriter.write("-----BEGIN CERTIFICATE-----\n")
        certificateWriter.write(Base64.getEncoder().encodeToString(certificate.encoded))
        certificateWriter.write("\n-----END CERTIFICATE-----\n")
        val fileWriter = FileWriter(Path(path, "$keyName.pem").toString());
        fileWriter.write(certificateWriter.toString())
        fileWriter.close()
        return Path(path, keyName, ".pem").toString()
    }
}
