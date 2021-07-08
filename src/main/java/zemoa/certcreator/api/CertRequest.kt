package zemoa.certcreator.api

data class CertRequest(
    val commonName: String,
    val secret: String,
    val duration: Long,
    val countryName: String?,
    val state: String?,
    val locality: String?,
    val organization: String?,
    val organizationUnit: String?,
    val email: String?
)
