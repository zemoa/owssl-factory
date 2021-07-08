package zemoa.certcreator.business

import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.springframework.stereotype.Service
import zemoa.certcreator.api.CertCreator
import zemoa.certcreator.api.CertRequest
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.Security
import java.security.cert.Certificate
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.annotation.PostConstruct


@Service
class CertCreatorImpl: CertCreator {
    private val bcProvider = BouncyCastleProvider.PROVIDER_NAME
    @PostConstruct
    fun init() {
        Security.addProvider(BouncyCastleProvider())
    }
    override fun createCACert(certRequest: CertRequest): Pair<PrivateKey, Certificate> {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA", bcProvider)
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()
        val dnName = X500Name(generateIssuer(certRequest))
        val certSerialNumber = BigInteger.valueOf(System.currentTimeMillis())
        val signatureAlgorithm = "SHA256WithRSA"
        val contentSigner = JcaContentSignerBuilder(signatureAlgorithm)
            .build(keyPair.private)
        val startDate = Instant.now()
        val endDate = startDate.plus(certRequest.duration, ChronoUnit.DAYS)
        val certBuilder = JcaX509v3CertificateBuilder(
            dnName, certSerialNumber, Date.from(startDate), Date.from(endDate), dnName,
            keyPair.public
        )
        val certificate = JcaX509CertificateConverter().setProvider(bcProvider)
            .getCertificate(certBuilder.build(contentSigner))
        return Pair(keyPair.private, certificate)
    }

    private fun generateIssuer(certRequest: CertRequest) : String {
        var issuer = "";
        if(certRequest.countryName != null) {
            issuer += "C=${certRequest.countryName},"
        }
        if(certRequest.state != null) {
            issuer += "ST=${certRequest.state},"
        }
        if(certRequest.locality != null) {
            issuer += "L=${certRequest.locality},"
        }
        if(certRequest.organization != null) {
            issuer += "O=${certRequest.organization},"
        }
        if(certRequest.organizationUnit != null) {
            issuer += "OU=${certRequest.organizationUnit},"
        }
        issuer += "CN=${certRequest.commonName}"
        if(certRequest.email != null) {
            issuer += ",emailAddress=${certRequest.email}"
        }
        return issuer
    }
}
