```mermaid
flowchart LR
    subgraph "RMI Server"
        A["RMI Registry
        port: 1099"]
        B["Spring Boot
        Application"]
        C["Service
        Implementation"]
    end
    
    subgraph "Client"
        D["RMI Client"]
        E["Service
        Interface"]
    end
    
    B --> A
    B --> C
    A --> D
    D --> E
    
    style A fill:#f9f,stroke:#333,color:#000
    style B fill:#bbf,stroke:#333,color:#000
    style C fill:#9f9,stroke:#333,color:#000
    style D fill:#ff9,stroke:#333,color:#000
    style E fill:#ff9,stroke:#333,color:#000
```

## Setup Instructions

### Server

After having moved a zipped jar file to remote VM and unzipping it, we do the following:

```
# start rmi registry
cd rmi/src
rmiregistry &

# compile server
java -cp out hotel.BookingServer
```

### Client

In the main class of the client, we have to specify host IP address.

```
Registry registry = LocateRegistry.getRegistry("IP.ADD.RE.SS");
```

Locally, we have to compile the client and subsequently, send requests.

```
javac -d out -sourcepath src src/staff/BookingClient.java

java -cp out staff.BookingClient
```
