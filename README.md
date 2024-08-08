Encryption with Asymmetric RSA.

1 - Text Raw information recording.

Method: POST /v1/text-management/

1.1 - Encryption is required: The application will request the following attributes:
 - textData
 - keySize

Process:   
 - The functionality will encrypt the information in the textData. 
 - The Asymmetric RSA encryption standard is executed, taking the size defined in the keySize attribute.
 - The information will store the information in a NoSQL database (MongoDB).
 - As a result, the ID and Private Key will be displayed. The private key will be used to retrieve information when necessary.

2 - Text Raw information query functionality.

Method: GET /v1/text-management/{uuid}?privateKey={pvtKey}
   
2.1 - Encryption was required: Text Raw was recorded encryption encrypted. The application will request the following attributes:
 - uuid
 - privateKey

Process: 
 - The functionality will query the record in the database using the ID.
 - Once it locates the information, it will use the private key information through the privateKey attribute to decrypt the Raw Text information.

3 - Implementations developed:
 - Creation of Controller and Service.
 - Creating DTO to apply the rules for presenting attributes in the response.
 - Implemented Asymmetric Encryption approach in the RSA standard for storing Text Raw.
 - Logs around the main points with Logback.
 - Main Unit tests with Mockito and JUnit
 - Dockerfile provided
 - Java 17
 - Springboot 3.0
 - Mongodb
