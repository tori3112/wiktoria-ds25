To test SOAP service using XML files, we can use `curl`.

```
curl --header "content-type: txt/xml" -d @requestfile.xml http://IP.ADD.RE.SS:8081/ws
```

Sending and processing sent requests is illustrated in the sequence diagram below.

```mermaid
sequenceDiagram
    participant Local as Local Machine
    participant Remote as Remote VM
    participant Service as SOAP Service
    
    Note over Local,Service: Testing SOAP Service with XML Files
    
    Local->>Remote: 1. Transfer XML files via SCP
    Note over Local,Remote: scp request.xml user@remote-vm:/path/to/files/
    
    Local->>Remote: 2. Send XML request via curl
    Note over Local,Remote: curl -X POST -H "Content-Type: text/xml" -d @request.xml
    
    Remote->>Service: 3. Process SOAP request
    Note over Remote,Service: Java -jar service.jar handles request
    
    Service-->>Remote: 4. Generate SOAP response
    Note over Service,Remote: XML response generated
    
    Remote-->>Local: 5. Return response
    Note over Remote,Local: Response received by curl
```
