1 - Text Raw information recording.

Method: POST /v1/text-management/

1.1 - Encryption is required: The application will request the following attributes:
 - textData
 - encryption
 - keySize
 - privateKeyPassword

Process:   
 - The functionality will encrypt the information in the textData attribute if the encryption attribute is set to true. 
 - The Asymmetric RSA encryption standard is executed, taking the size defined in the keySize attribute.
 - Finally, the method will encrypt the privateKeyPassword attribute in the Symmetric pattern, using the JASYPT class: Java Simplified Encryption.
 - The information will store the information in a NoSQL database (MongoDB).
 - As a result, the ID and Private Key will be displayed. The private key will be used to retrieve information when necessary.
   
1.2 - Encryption is not required: The application will request the following attributes:
 - textData
 - encryption

Process:
 - The functionality will store information in a NoSQL database (MongoDB).
 - As a result, the ID will be displayed and used to retrieve the information when necessary.

2 - Text Raw information query functionality.

Method: GET /v1/text-management/{uuid}?privateKeyPassword={pdw}&privateKey={pvtKey}
   
2.1 - Encryption was required: Text Raw was recorded encryption encrypted. The application will request the following attributes:
 - uuid
 - privateKey
 - privateKeyPassword

Process: 
 - The functionality will query the record in the database using the ID.
 - Once it locates the information, it will evaluate whether the data was recorded encrypted. If yes, it will use the private key information through the privateKey attribute to decrypt the 	Raw Text information.
 - The functionality will also check whether the requester has access to the information. It will validate whether the primary key's Password is by the original information, assigned in the recording, doing this through the privateKeyPassword attribute.
   
2.2 - Encryption was not required: Text Raw was not recorded encrypted. The application will request the following attributes:
 - uuid
 
Process: 
 - The functionality will query the record in the database using the ID. Once located, it will display the information on the screen.

3 - Implementations developed:
 - Creation of Controller and Service.
 - Creating DTO to apply the rules for presenting attributes in the response.
 - Implemented Asymmetric Encryption approach in the RSA standard for storing Text Raw.
 - Implemented Symmetric Encryption approach using Java Simplified Encryption to store the Password.
 - Logs around the main points with Logback.
 - Main Unit tests with Mockito and JUnit
 - Dockerfile provided
 - Java 17
 - Springboot 3.0
 - Mongodb# springboot-encryption-rsa
# springboot-encryption-rsa
